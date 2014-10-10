Procedura per generare gli stub del registro servizi del NICA
-------------------------------------------------------------

Estrarre a mano attaverso il browser i due seguenti WSDL:

@1 = http://localhost:8191/ws/registroServizi?wsdl
@2 = http://localhost:8191/ws/registroServizi?wsdl=IWSRegistroServizi.wsdl

Salvare il @1 in un file chiamato registroServizi_1.wsdl
Salvare il @2 in un file chiamato registroServizi_2.wsdl

Creare un nuovo file e salvarlo come registroServizi_3.wsdl, metterci all'interno @1

Sostituire in registroServizi_3.wsdl l'import con la seguente:

  <wsdl:import location="./registroServizi_2.wsdl" namespace="http://icar.unibas.it/freesbee/">
    </wsdl:import>

Ricercare in @2 'indirizzoRegistro' e cancellare il tag:

<xs:element name="indirizzoRegistro" type="xs:string"/>


Procedere con la generazione degli STUB:

./wsdl2java -p it.unibas.icar.freesbee.ws.registroservizi.client.stub  -all -ant <path_al_file>/registroServizi_3.wsdl


Sovrascrivere i vecchi stub con i nuovi appena generati (generalmente il tool wsdl2java li mette nella cartella bin di CXF)