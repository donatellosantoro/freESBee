Procedura per generare gli stub del registro servizi del NICA
-------------------------------------------------------------


Procedere con la generazione degli STUB:

./wsdl2java -p it.unibas.freesbee.websla.ws.web.stub -all -ant http://localhost:28080/freesbee-Sla/ws/servizio/ServiceServizio?wsdl
./wsdl2java -p it.unibas.freesbee.websla.ws.web.stub -all -ant http://localhost:28080/freesbee-Sla/ws/soggetto/ServiceSoggetto?wsdl


Sovrascrivere i vecchi stub con i nuovi appena generati (generalmente il tool wsdl2java li mette nella cartella bin di CXF)