(defproject soap-box "0.1.0-SNAPSHOT"
  :description "An example soap server"
  :url "http://github.com/slipset/soap-box"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [javax.ws.rs/javax.ws.rs-api "2.0"]
                 [org.clojure/tools.logging "0.3.1"]]
  :main ^:skip-aot soap-box.server
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
