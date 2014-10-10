ALTER TABLE accordodicollaborazione DISABLE TRIGGER ALL;

INSERT INTO accordodicollaborazione (id, wsdlerogatore, wsdlfruitore, cartellafiles, filetypes, nome) VALUES (65536, 'WS_ErogatoreLogico.wsdl', 'WS_FruitoreLogico.wsdl', '65536-AP3-AggiornamentoIPA', 'Types.xsd', 'AP3-AggiornamentoIPA');
INSERT INTO accordodicollaborazione (id, wsdlerogatore, wsdlfruitore, cartellafiles, filetypes, nome) VALUES (65537, 'WS_ErogatoreLogico.wsdl', NULL, '65537-AP3-InterrogaFascicolo', 'Types.xsd', 'AP3-InterrogaFascicolo');
INSERT INTO accordodicollaborazione (id, wsdlerogatore, wsdlfruitore, cartellafiles, filetypes, nome) VALUES (65538, 'WS_ErogatoreLogico.wsdl', NULL, '65538-AP3-InterrogaDocumentazione', 'Types.xsd', 'AP3-InterrogaDocumentazione');
INSERT INTO accordodicollaborazione (id, wsdlerogatore, wsdlfruitore, cartellafiles, filetypes, nome) VALUES (65539, 'WS_ErogatoreLogico.wsdl', NULL, '65539-AP3-Ricerca', 'Types.xsd', 'AP3-Ricerca');


ALTER TABLE accordodicollaborazione ENABLE TRIGGER ALL;

ALTER TABLE dati DISABLE TRIGGER ALL;

INSERT INTO dati (id, payloadcostante, datisql_id, datiinterattivi_id, daticostanti_id) VALUES (98304, NULL, 65536, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datisql_id, datiinterattivi_id, daticostanti_id) VALUES (98305, NULL, NULL, NULL, 65536);
INSERT INTO dati (id, payloadcostante, datisql_id, datiinterattivi_id, daticostanti_id) VALUES (98307, NULL, 65538, NULL, 65537);
INSERT INTO dati (id, payloadcostante, datisql_id, datiinterattivi_id, daticostanti_id) VALUES (98306, NULL, 65537, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datisql_id, datiinterattivi_id, daticostanti_id) VALUES (98309, NULL, 65540, NULL, 65538);
INSERT INTO dati (id, payloadcostante, datisql_id, datiinterattivi_id, daticostanti_id) VALUES (98308, NULL, 65539, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datisql_id, datiinterattivi_id, daticostanti_id) VALUES (98311, NULL, 65542, NULL, 65539);
INSERT INTO dati (id, payloadcostante, datisql_id, datiinterattivi_id, daticostanti_id) VALUES (98310, NULL, 65541, NULL, NULL);


ALTER TABLE dati ENABLE TRIGGER ALL;


ALTER TABLE daticostanti DISABLE TRIGGER ALL;

INSERT INTO daticostanti (id) VALUES (65536);
INSERT INTO daticostanti (id) VALUES (65537);
INSERT INTO daticostanti (id) VALUES (65538);
INSERT INTO daticostanti (id) VALUES (65539);


ALTER TABLE daticostanti ENABLE TRIGGER ALL;


ALTER TABLE datiinterattivi DISABLE TRIGGER ALL;



ALTER TABLE datiinterattivi ENABLE TRIGGER ALL;


ALTER TABLE datisql DISABLE TRIGGER ALL;

INSERT INTO datisql (id, select_id) VALUES (65536, 65536);
INSERT INTO datisql (id, select_id) VALUES (65538, 65538);
INSERT INTO datisql (id, select_id) VALUES (65537, 65537);
INSERT INTO datisql (id, select_id) VALUES (65540, 65540);
INSERT INTO datisql (id, select_id) VALUES (65539, 65539);
INSERT INTO datisql (id, select_id) VALUES (65542, 65542);
INSERT INTO datisql (id, select_id) VALUES (65541, 65541);


ALTER TABLE datisql ENABLE TRIGGER ALL;


ALTER TABLE datoprimitivo DISABLE TRIGGER ALL;

INSERT INTO datoprimitivo (id, nome, tipo, valore, datiinterattivi_id, daticostanti_id) VALUES (65536, 'Correzione', NULL, 'Operazione eseguita con successo', NULL, 65536);
INSERT INTO datoprimitivo (id, nome, tipo, valore, datiinterattivi_id, daticostanti_id) VALUES (65537, 'codiceAmm', NULL, '00000002', NULL, 65537);
INSERT INTO datoprimitivo (id, nome, tipo, valore, datiinterattivi_id, daticostanti_id) VALUES (65538, 'codiceAoo', NULL, '00000002', NULL, 65537);
INSERT INTO datoprimitivo (id, nome, tipo, valore, datiinterattivi_id, daticostanti_id) VALUES (65539, 'anno', NULL, '2007', NULL, 65537);
INSERT INTO datoprimitivo (id, nome, tipo, valore, datiinterattivi_id, daticostanti_id) VALUES (65540, 'numero', NULL, '00000002', NULL, 65537);
INSERT INTO datoprimitivo (id, nome, tipo, valore, datiinterattivi_id, daticostanti_id) VALUES (65541, 'codiceAmm', NULL, '00000002', NULL, 65538);
INSERT INTO datoprimitivo (id, nome, tipo, valore, datiinterattivi_id, daticostanti_id) VALUES (65542, 'codiceAoo', NULL, '00000002', NULL, 65538);
INSERT INTO datoprimitivo (id, nome, tipo, valore, datiinterattivi_id, daticostanti_id) VALUES (65543, 'codiceDocInf', NULL, '00000002_codice00000001', NULL, 65538);
INSERT INTO datoprimitivo (id, nome, tipo, valore, datiinterattivi_id, daticostanti_id) VALUES (65544, 'dataDa', NULL, '2007-07-01', NULL, 65539);
INSERT INTO datoprimitivo (id, nome, tipo, valore, datiinterattivi_id, daticostanti_id) VALUES (65545, 'dataA', NULL, '2008-08-01', NULL, 65539);
INSERT INTO datoprimitivo (id, nome, tipo, valore, datiinterattivi_id, daticostanti_id) VALUES (65546, 'codiceAmm', NULL, '00000002', NULL, 65539);
INSERT INTO datoprimitivo (id, nome, tipo, valore, datiinterattivi_id, daticostanti_id) VALUES (65547, 'codiceAoo', NULL, '00000002', NULL, 65539);
INSERT INTO datoprimitivo (id, nome, tipo, valore, datiinterattivi_id, daticostanti_id) VALUES (65548, 'anno', NULL, '2007', NULL, 65539);
INSERT INTO datoprimitivo (id, nome, tipo, valore, datiinterattivi_id, daticostanti_id) VALUES (65549, 'numero', NULL, '00000002', NULL, 65539);
INSERT INTO datoprimitivo (id, nome, tipo, valore, datiinterattivi_id, daticostanti_id) VALUES (65550, 'oggetto', NULL, 'oggetto', NULL, 65539);


ALTER TABLE datoprimitivo ENABLE TRIGGER ALL;


--ALTER TABLE hibernate_sequences DISABLE TRIGGER ALL;

--INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Configurazione', 1);
--INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('DatiInterattivi', 2);
--INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('AccordoDiCollaborazione', 3);
--INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('PortType', 3);
--INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Operation', 3);
--INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Message', 3);
--INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('IstanzaAccordoDiCollaborazione', 4);
--INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('IstanzaPortType', 4);
--INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('IstanzaOperation', 4);
--INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Dati', 4);
--INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('DatiSQL', 3);
--INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Query', 3);
--INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('DatiCostanti', 3);
--INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('DatoPrimitivo', 3);
--INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Messaggio', 4);


--ALTER TABLE hibernate_sequences ENABLE TRIGGER ALL;


ALTER TABLE istanzaaccordodicollaborazione DISABLE TRIGGER ALL;

INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (98304, '65536-AP3-AggiornamentoIPA\\ISTANZE\\98304-AP3-AggiornamentoIPA-Fruitore\\', 'AP3-AggiornamentoIPA-Fruitore', 'FRUITORE', 65536);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (98305, '65536-AP3-AggiornamentoIPA\\ISTANZE\\98305-AP3-AggiornamentoIPA-Erogatore\\', 'AP3-AggiornamentoIPA-Erogatore', 'EROGATORE', 65536);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (98306, '65537-AP3-InterrogaFascicolo\\ISTANZE\\98306-AP3-InterrogaFascicolo-Erogatore\\', 'AP3-InterrogaFascicolo-Erogatore', 'EROGATORE', 65537);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (98307, '65537-AP3-InterrogaFascicolo\\ISTANZE\\98307-AP3-InterrogaFascicolo-Fruitore\\', 'AP3-InterrogaFascicolo-Fruitore', 'FRUITORE', 65537);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (98308, '65538-AP3-InterrogaDocumentazione\\ISTANZE\\98308-AP3-InterrogaDocumentazione-Erogatore\\', 'AP3-InterrogaDocumentazione-Erogatore', 'EROGATORE', 65538);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (98309, '65538-AP3-InterrogaDocumentazione\\ISTANZE\\98309-AP3-InterrogaDocumentazione-Fruitore\\', 'AP3-InterrogaDocumentazione-Fruitore', 'FRUITORE', 65538);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (98310, '65539-AP3-Ricerca\\ISTANZE\\98310-AP3-Ricerca-Erogatore\\', 'AP3-Ricerca-Erogatore', 'EROGATORE', 65539);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (98311, '65539-AP3-Ricerca\\ISTANZE\\98311-AP3-Ricerca-Fruitore\\', 'AP3-Ricerca-Fruitore', 'FRUITORE', 65539);


ALTER TABLE istanzaaccordodicollaborazione ENABLE TRIGGER ALL;


ALTER TABLE istanzaoperation DISABLE TRIGGER ALL;

INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, operation_id, istanzaporttype_id, datirisposta_id, datirichiesta_id) VALUES (98305, NULL, NULL, NULL, NULL, NULL, false, false, NULL, false, 65537, 98305, NULL, NULL);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, operation_id, istanzaporttype_id, datirisposta_id, datirichiesta_id) VALUES (98306, NULL, NULL, 'trasformazioneXMLtoXML.xslt', NULL, NULL, false, false, 'http://localhost:9192/FRUITORE/AggiornamentoIPA_WS_98305/', true, 65536, 98306, 98305, NULL);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, operation_id, istanzaporttype_id, datirisposta_id, datirichiesta_id) VALUES (98304, 'trasformazioneSQLtoXML.xslt', NULL, NULL, NULL, NULL, false, false, NULL, false, 65536, 98304, NULL, 98304);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, operation_id, istanzaporttype_id, datirisposta_id, datirichiesta_id) VALUES (98307, NULL, NULL, 'trasformazioneSQLtoXMLRisposta.xslt', 'trasformazioneXMLtoSELECTRisposta.xslt', NULL, false, false, NULL, false, 65538, 98307, 98306, NULL);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, operation_id, istanzaporttype_id, datirisposta_id, datirichiesta_id) VALUES (98308, 'trasformazioneSQLtoXMLRichiesta.xslt', NULL, NULL, NULL, NULL, false, false, NULL, false, 65538, 98308, NULL, 98307);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, operation_id, istanzaporttype_id, datirisposta_id, datirichiesta_id) VALUES (98309, NULL, NULL, 'trasformazioneSQLtoXMLRisposta.xslt', 'trasformazioneXMLtoSELECTRisposta.xslt', NULL, false, false, NULL, false, 65539, 98309, 98308, NULL);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, operation_id, istanzaporttype_id, datirisposta_id, datirichiesta_id) VALUES (98310, 'trasformazioneSQLtoXMLRichiesta.xslt', NULL, NULL, NULL, NULL, false, false, NULL, false, 65539, 98310, NULL, 98309);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, operation_id, istanzaporttype_id, datirisposta_id, datirichiesta_id) VALUES (98311, NULL, NULL, 'trasformazioneSQLtoXMLRisposta.xslt', 'trasformazioneXMLtoSELECTRisposta.xslt', NULL, false, false, NULL, false, 65540, 98311, 98310, NULL);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, operation_id, istanzaporttype_id, datirisposta_id, datirichiesta_id) VALUES (98312, 'trasformazioneSQLtoXMLRichiesta.xslt', NULL, NULL, NULL, NULL, false, false, NULL, false, 65540, 98312, NULL, 98311);


ALTER TABLE istanzaoperation ENABLE TRIGGER ALL;


ALTER TABLE istanzaporttype DISABLE TRIGGER ALL;

INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (98305, NULL, 'EROGAZIONE', 98304, 65537);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (98306, NULL, 'EROGAZIONE', 98305, 65536);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (98304, '', 'FRUIZIONE', 98304, 65536);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (98307, NULL, 'EROGAZIONE', 98306, 65538);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (98308, '', 'FRUIZIONE', 98307, 65538);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (98309, NULL, 'EROGAZIONE', 98308, 65539);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (98310, '', 'FRUIZIONE', 98309, 65539);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (98311, NULL, 'EROGAZIONE', 98310, 65540);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (98312, '', 'FRUIZIONE', 98311, 65540);


ALTER TABLE istanzaporttype ENABLE TRIGGER ALL;


ALTER TABLE message DISABLE TRIGGER ALL;

INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (65536, 'http://www.progettoicar.it/ipa', 'richiesta_RichiestaRispostaAsincrona_AggiornamentoIPA_Msg', 'richiesta_RichiestaRispostaAsincrona_AggiornamentoIPA', 65536);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (65537, 'http://www.progettoicar.it/ipa', 'risposta_RichiestaRispostaAsincrona_AggiornamentoIPA_Msg', 'risposta_RichiestaRispostaAsincrona_AggiornamentoIPA', 65537);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (65538, 'http://progettoicar.it/AP3interprotoQry/interrogaFascWS', 'richiesta_RichiestaRispostaSincrona_InterrogaFasc_Msg', 'richiesta_RichiestaRispostaSincrona_InterrogaFasc', 65538);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (65539, 'http://progettoicar.it/AP3interprotoQry/interrogaFascWS', 'risposta_RichiestaRispostaSincrona_InterrogaFasc_Msg', 'risposta_RichiestaRispostaSincrona_InterrogaFasc', 65538);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (65540, 'http://progettoicar.it/AP3interprotoQry/interrogaDocWS', 'richiesta_RichiestaRispostaSincrona_InterrogaDoc_Msg', 'richiesta_RichiestaRispostaSincrona_InterrogaDoc', 65539);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (65541, 'http://progettoicar.it/AP3interprotoQry/interrogaDocWS', 'risposta_RichiestaRispostaSincrona_InterrogaDoc_Msg', 'risposta_RichiestaRispostaSincrona_InterrogaDoc', 65539);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (65542, 'http://progettoicar.it/AP3interprotoQry/ricercaWS', 'richiesta_RichiestaRispostaSincrona_Ricerca_Msg', 'richiesta_RichiestaRispostaSincrona_Ricerca', 65540);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (65543, 'http://progettoicar.it/AP3interprotoQry/ricercaWS', 'risposta_RichiestaRispostaSincrona_Ricerca_Msg', 'risposta_RichiestaRispostaSincrona_Ricerca', 65540);


ALTER TABLE message ENABLE TRIGGER ALL;


ALTER TABLE messaggio DISABLE TRIGGER ALL;


ALTER TABLE messaggio ENABLE TRIGGER ALL;


ALTER TABLE messaggiosbloccomanuale DISABLE TRIGGER ALL;



ALTER TABLE messaggiosbloccomanuale ENABLE TRIGGER ALL;


ALTER TABLE operation DISABLE TRIGGER ALL;

INSERT INTO operation (id, nome, profilodicollaborazione, messagein_id, porttype_id, messagefault_id, messageout_id) VALUES (65536, 'RichiestaRispostaAsincrona_AggiornamentoIPA', 'ASINCRONO', 65536, 65536, NULL, NULL);
INSERT INTO operation (id, nome, profilodicollaborazione, messagein_id, porttype_id, messagefault_id, messageout_id) VALUES (65537, 'RichiestaRispostaAsincrona_AggiornamentoIPA', 'ASINCRONO', 65537, 65537, NULL, NULL);
INSERT INTO operation (id, nome, profilodicollaborazione, messagein_id, porttype_id, messagefault_id, messageout_id) VALUES (65538, 'RichiestaRispostaSincrona_InterrogaFasc', 'SINCRONO', 65538, 65538, NULL, 65539);
INSERT INTO operation (id, nome, profilodicollaborazione, messagein_id, porttype_id, messagefault_id, messageout_id) VALUES (65539, 'RichiestaRispostaSincrona_InterrogaDoc', 'SINCRONO', 65540, 65539, NULL, 65541);
INSERT INTO operation (id, nome, profilodicollaborazione, messagein_id, porttype_id, messagefault_id, messageout_id) VALUES (65540, 'RichiestaRispostaSincrona_Ricerca', 'SINCRONO', 65542, 65540, NULL, 65543);


ALTER TABLE operation ENABLE TRIGGER ALL;


ALTER TABLE porttype DISABLE TRIGGER ALL;

INSERT INTO porttype (id, nome, ruolo, accordodicollaborazione_id) VALUES (65536, 'AggiornamentoIPA_WS', 'EROGATORE', 65536);
INSERT INTO porttype (id, nome, ruolo, accordodicollaborazione_id) VALUES (65537, 'AggiornamentoIPA_WS', 'FRUITORE', 65536);
INSERT INTO porttype (id, nome, ruolo, accordodicollaborazione_id) VALUES (65538, 'InterrogaFascWS', 'EROGATORE', 65537);
INSERT INTO porttype (id, nome, ruolo, accordodicollaborazione_id) VALUES (65539, 'InterrogaDocWS', 'EROGATORE', 65538);
INSERT INTO porttype (id, nome, ruolo, accordodicollaborazione_id) VALUES (65540, 'RicercaWS', 'EROGATORE', 65539);


ALTER TABLE porttype ENABLE TRIGGER ALL;


ALTER TABLE query DISABLE TRIGGER ALL;

INSERT INTO query (id, indirizzodb, nomedb, nomeutente, "password", query, tipodb) VALUES (65536, 'localhost', 'ap3', 'silvio', '**silvio**', 'SELECT * FROM amministrazione JOIN localita ON amministrazione.indirizzo = localita.id JOIN persona ON amministrazione.responsabile = persona.id JOIN serviziotelematico_amministrazione ON serviziotelematico_amministrazione.id_amministrazione = amministrazione.id JOIN areaorganizzativaomogenea ON areaorganizzativaomogenea.id_amministrazione = amministrazione.id JOIN unitaorganizzativa ON unitaorganizzativa.id_amministrazione = amministrazione.id WHERE amministrazione.codice = ?', 'PostgreSQL');
INSERT INTO query (id, indirizzodb, nomedb, nomeutente, "password", query, tipodb) VALUES (65538, 'localhost', 'ap3', 'silvio', '**silvio**', 'SELECT amministrazione.codice,areaorganizzativaomogenea.codice_aoo FROM areaorganizzativaomogenea JOIN amministrazione ON areaorganizzativaomogenea.id_amministrazione = amministrazione.id WHERE amministrazione.codice = ?', 'PostgreSQL');
INSERT INTO query (id, indirizzodb, nomedb, nomeutente, "password", query, tipodb) VALUES (65537, 'localhost', 'ap3', 'silvio', '**silvio**', 'SELECT * FROM fascicolo JOIN classifica ON fascicolo.id_classifica = classifica.id JOIN documentofascicolo ON documentofascicolo.id_fascicolo = fascicolo.id JOIN areaorganizzativaomogenea ON documentofascicolo.id_areaorganizzativaomogenea = areaorganizzativaomogenea.id JOIN amministrazione ON areaorganizzativaomogenea.id_amministrazione = amministrazione.id JOIN documento ON documento.id_documentofascicolo = documentofascicolo.id JOIN responsabiletrattamento ON responsabiletrattamento.id_documentofascicolo = documentofascicolo.id', 'PostgreSQL');
INSERT INTO query (id, indirizzodb, nomedb, nomeutente, "password", query, tipodb) VALUES (65540, 'localhost', 'ap3', 'silvio', '**silvio**', 'SELECT amministrazione.codice,areaorganizzativaomogenea.codice_aoo FROM areaorganizzativaomogenea JOIN amministrazione ON areaorganizzativaomogenea.id_amministrazione = amministrazione.id WHERE amministrazione.codice = ?', 'PostgreSQL');
INSERT INTO query (id, indirizzodb, nomedb, nomeutente, "password", query, tipodb) VALUES (65539, 'localhost', 'ap3', 'silvio', '**silvio**', 'SELECT * FROM documento', 'PostgreSQL');
INSERT INTO query (id, indirizzodb, nomedb, nomeutente, "password", query, tipodb) VALUES (65542, 'localhost', 'ap3', 'silvio', '**silvio**', 'SELECT * FROM amministrazione JOIN areaorganizzativaomogenea ON amministrazione.id = areaorganizzativaomogenea.id_amministrazione JOIN documentofascicolo ON areaorganizzativaomogenea.id = documentofascicolo.id_areaorganizzativaomogenea JOIN fascicolo ON fascicolo.id = documentofascicolo.id_fascicolo where amministrazione.codice = ?', 'PostgreSQL');
INSERT INTO query (id, indirizzodb, nomedb, nomeutente, "password", query, tipodb) VALUES (65541, 'localhost', 'ap3', 'silvio', '**silvio**', 'SELECT * FROM amministrazione JOIN areaorganizzativaomogenea ON amministrazione.id = areaorganizzativaomogenea.id_amministrazione JOIN documentofascicolo ON areaorganizzativaomogenea.id = documentofascicolo.id_areaorganizzativaomogenea JOIN fascicolo ON documentofascicolo.id_fascicolo = fascicolo.id JOIN classifica ON fascicolo.id_classifica = classifica.id JOIN documento ON documento.id_documentofascicolo = documentofascicolo.id JOIN responsabiletrattamento ON responsabiletrattamento.id_documentofascicolo = documentofascicolo.id', 'PostgreSQL');


ALTER TABLE query ENABLE TRIGGER ALL;

ALTER TABLE utente ENABLE TRIGGER ALL;


