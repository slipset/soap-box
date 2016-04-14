(ns soap-box.server
    (:import [javax.jws
              WebService
              WebParam]
     javax.xml.ws.Endpoint)
    (:require [clojure.tools.logging :as log])
    (:gen-class))

;; First we need to define a java interface
;; It has one method, divide, which takes two Integers and returns a Long
;; Its java signature would be something like this
;;
;; public Long divide(Integer numerator, Integer denominator);
;;
;; Since we'll want to name the parameters that we expose in the
;; webservice, we need to add the type hints in the interface.
(definterface IDivisorWS
  (^Long divide [^Integer numerator ^Integer denominator]))


;; Then we need to define the implementing class
;; It implements the IDivisorWS interface. The java implementation would
;; look something like this (with logging removed):
;;
;; @WebService (targetNamespace = "http://github.com/slipset/soap-box/")
;; public class DivisorWS implements IDivisorWS {
;;
;;    public Long divide(@WebParam(name = "numerator") Integer numerator,
;;                       @WebParam(name = "denominator") Integer denominator) {
;;        return numerator / denominator;
;;    }
;; }
;;
;; The important bit here is to get the annotations and the type-hints in the
;; correct order.
(deftype ^{WebService {:targetNamespace "http://github.com/slipset/soap-box/"}} DivisorWS []
  IDivisorWS
  (divide [this
           ;; This is equivalent to java
           ;; @WebParam(name = "numerator")
           #^{WebParam {:name "numerator"}}
           numerator

           ;; This is equivalent to java
           ;; @WebParam(name = "denominator")
           #^{WebParam {:name "denominator"}}
           denominator]
   (log/info "dividing" numerator "by" denominator)
   (log/spyf :info "The answer is %d" (/ numerator denominator))))

(def endpoint (atom nil))

(defn -main [& args]
  (log/info "Starting up DivisorWSService" )
  (let [url "http://localhost:8080/DivisorWSService"]
    (if-not (nil? @endpoint)
      (.stop @endpoint))
    (log/info "Publishing DivisorWSService at" url)
    (log/info "wsdl is located at " (str url "?wsdl"))
    (reset! endpoint
            ;; here the endpoint is published
            (Endpoint/publish url (DivisorWS.)))))
