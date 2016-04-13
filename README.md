# soap-box

This is a very simple webservice which exposes a method which divides two numbers:
```xml
<x:Envelope xmlns:x="http://schemas.xmlsoap.org/soap/envelope/" xmlns:soa="http://github.com/slipset/soap-box/">
    <x:Header/>
    <x:Body>
        <soa:divide>
            <numerator>9</numerator>
            <denominator>3</denominator>
        </soa:divide>
    </x:Body>
</x:Envelope>
```
The request itself should look like
```
POST http://localhost:8080/DivisorWSService HTTP/1.1

Content-Type: text/xml; charset=utf-8

<x:Envelope xmlns:x="http://schemas.xmlsoap.org/soap/envelope/" xmlns:soa="http://github.com/slipset/soap-box/">
    <x:Header/>
    <x:Body>
        <soa:divide>
            <numerator>9</numerator>
            <denominator>3</denominator>
        </soa:divide>
    </x:Body>
</x:Envelope>
```

This will give you a response like:
```xml
<?xml version="1.0" ?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
    <S:Body>
        <ns2:divideResponse xmlns:ns2="http://github.com/slipset/soap-box/">
            <return>3</return>
        </ns2:divideResponse>
    </S:Body>
</S:Envelope>
```
in http:
```
HTTP/1.1 200 "OK" 43ms

Date: Wed, 13 Apr 2016 19:10:16 GMT
Transfer-encoding: chunked
Content-type: text/xml; charset=utf-8

<?xml version="1.0" ?><S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/"><S:Body><ns2:divideResponse xmlns:ns2="http://github.com/slipset/soap-box/"><return>3</return></ns2:divideResponse></S:Body></S:Envelope>

```


## Installation

	$ git clone git@github.com:slipset/soap-box.git

## Usage

	$ lein uberjar
    $ java -jar soap-box-0.1.0-standalone.jar

