ALTER TABLE accordodicollaborazione DISABLE TRIGGER ALL;
INSERT INTO accordodicollaborazione (id, wsdlerogatore, wsdlfruitore, cartellafiles, filetypes, nome) VALUES (1, 'QualificazionePDD_AdsParteComune//QualificazionePDD_AdsParteComune//componentiAds//parteComune//specificaInterfacciaParteComune//QualificazionePDD_ErogatoreLogico.wsdl', 'QualificazionePDD_AdsParteComune//QualificazionePDD_AdsParteComune//componentiAds//parteComune//specificaInterfacciaParteComune//QualificazionePDD_FruitoreLogico.wsdl', '1-QualificazionePDD', NULL, 'QualificazionePDD');
ALTER TABLE accordodicollaborazione ENABLE TRIGGER ALL;

ALTER TABLE dati DISABLE TRIGGER ALL;
INSERT INTO dati (id, payloadcostante, datiinterattivi_id, datisql_id, daticostanti_id) VALUES (65537, '<p891:richiesta_RichiestaRispostaAsincrona_testAsincronoSimmetrico xmlns:p891="http://sica.spcoop.it/servizi/QualificazionePDDWS/types"> 
			<p891:TokenSessione>1ab955960a3281e965c0393ad0071f1e</p891:TokenSessione>  
		</p891:richiesta_RichiestaRispostaAsincrona_testAsincronoSimmetrico> ', NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datiinterattivi_id, datisql_id, daticostanti_id) VALUES (2, '<p891:richiesta_RichiestaRispostaSincrona_start xmlns:p891="http://sica.spcoop.it/servizi/QualificazionePDDWS/types"> 
			<p891:TokenSessione>1ab955960a3281e965c0393ad0071f1e</p891:TokenSessione>  
		</p891:richiesta_RichiestaRispostaSincrona_start> ', NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datiinterattivi_id, datisql_id, daticostanti_id) VALUES (32769, '<p891:richiesta_RichiestaRispostaSincrona_testSincrono xmlns:p891="http://sica.spcoop.it/servizi/QualificazionePDDWS/types"> 
			<p891:TokenSessione>1ab955960a3281e965c0393ad0071f1e</p891:TokenSessione>  
		</p891:richiesta_RichiestaRispostaSincrona_testSincrono> ', NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datiinterattivi_id, datisql_id, daticostanti_id) VALUES (131073, '		<p891:segnalazione_NotificazioneRisposta_riceviRispostaTestAsincronoSimmetrico xmlns:p891="http://sica.spcoop.it/servizi/QualificazionePDDWS/types"> 
			<p891:Esito>RISPOSTA_OK</p891:Esito>  
			<p891:TokenSessione>1ab955960a3281e965c0393ad0071f1e</p891:TokenSessione>  
		</p891:segnalazione_NotificazioneRisposta_riceviRispostaTestAsincronoSimmetrico> ', NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datiinterattivi_id, datisql_id, daticostanti_id) VALUES (163840, NULL, NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datiinterattivi_id, datisql_id, daticostanti_id) VALUES (163841, '
		<p891:richiesta_RichiestaSenzaRisposta_testOneWay xmlns:p891="http://sica.spcoop.it/servizi/QualificazionePDDWS/types"> 
			<p891:TokenSessione>1ab955960a3281e965c0393ad0071f1e</p891:TokenSessione>  
		</p891:richiesta_RichiestaSenzaRisposta_testOneWay> ', NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datiinterattivi_id, datisql_id, daticostanti_id) VALUES (196609, '<p891:richiesta_RichiestaRispostaSincrona_end xmlns:p891="http://sica.spcoop.it/servizi/QualificazionePDDWS/types"> 
<p891:TokenSessione>1ab955960a3281e965c0393ad0071f1e</p891:TokenSessione>  
<p891:isInError>0</p891:isInError>  
</p891:richiesta_RichiestaRispostaSincrona_end> ', NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datiinterattivi_id, datisql_id, daticostanti_id) VALUES (229377, '<p891:richiesta_RichiestaRispostaAsincrona_testAsincronoAsimmetrico xmlns:p891="http://sica.spcoop.it/servizi/QualificazionePDDWS/types"> 
<p891:TokenSessione>1ab955960a3281e965c0393ad0071f1e</p891:TokenSessione>  
</p891:richiesta_RichiestaRispostaAsincrona_testAsincronoAsimmetrico> ', NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datiinterattivi_id, datisql_id, daticostanti_id) VALUES (262146, '<p891:richiesta_RichiestaRispostaAsincrona_checkTestAsincronoAsimmetrico xmlns:p891="http://sica.spcoop.it/servizi/QualificazionePDDWS/types"> 
<p891:TokenSessione>1ab955960a3281e965c0393ad0071f1e</p891:TokenSessione>  
</p891:richiesta_RichiestaRispostaAsincrona_checkTestAsincronoAsimmetrico> ', NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datiinterattivi_id, datisql_id, daticostanti_id) VALUES (294913, '		<p891:richiesta_RichiestaRispostaSincrona_getTraccia xmlns:p891="http://sica.spcoop.it/servizi/QualificazionePDDWS/types">
			<p891:TokenSessione>1ab955960a3281e965c0393ad0071f1e</p891:TokenSessione>
		</p891:richiesta_RichiestaRispostaSincrona_getTraccia>', NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datiinterattivi_id, datisql_id, daticostanti_id) VALUES (294912, NULL, NULL, 1, NULL);
INSERT INTO dati (id, payloadcostante, datiinterattivi_id, datisql_id, daticostanti_id) VALUES (1, NULL, NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datiinterattivi_id, datisql_id, daticostanti_id) VALUES (32768, NULL, NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datiinterattivi_id, datisql_id, daticostanti_id) VALUES (229376, NULL, NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datiinterattivi_id, datisql_id, daticostanti_id) VALUES (262145, NULL, NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datiinterattivi_id, datisql_id, daticostanti_id) VALUES (65536, NULL, NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datiinterattivi_id, datisql_id, daticostanti_id) VALUES (131072, NULL, NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datiinterattivi_id, datisql_id, daticostanti_id) VALUES (196608, NULL, NULL, NULL, NULL);
ALTER TABLE dati ENABLE TRIGGER ALL;

ALTER TABLE datisql DISABLE TRIGGER ALL;
INSERT INTO datisql (id, select_id) VALUES (1, 1);
ALTER TABLE datisql ENABLE TRIGGER ALL;

DELETE FROM hibernate_sequences;

ALTER TABLE hibernate_sequences DISABLE TRIGGER ALL;
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('AccordoDiCollaborazione', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('PortType', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Operation', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Message', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('MessaggioSbloccoManuale', 2);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('IstanzaAccordoDiCollaborazione', 10);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('IstanzaPortType', 10);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('IstanzaOperation', 10);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Dati', 10);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('DatiSQL', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Query', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Messaggio', 54);

ALTER TABLE hibernate_sequences ENABLE TRIGGER ALL;

ALTER TABLE istanzaaccordodicollaborazione DISABLE TRIGGER ALL;
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (1, '1-QualificazionePDD//ISTANZE//1-QualificazionePdDStartErog//', 'QualificazionePdDStartErog', 'EROGATORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (32768, '1-QualificazionePDD//ISTANZE//32768-QualificazionePDD_sincrono//', 'QualificazionePdDSincronoErog', 'EROGATORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (2, '1-QualificazionePDD//ISTANZE//2-FruitoreStartCostante//', 'QualificazionePdDStartFruitCostante', 'FRUITORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (32769, '1-QualificazionePDD//ISTANZE//32769-QualificazionePDDFruitoreSincrono//', 'QualificazionePdDSincronoFruitCostante', 'FRUITORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (65536, '1-QualificazionePDD//ISTANZE//65536-QualificazionePdDAsincronoSimmErog//', 'QualificazionePdDAsincronoSimmErog', 'EROGATORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (65537, '1-QualificazionePDD//ISTANZE//65537-QualificazionePdDAsincronoSimmFruitCostante//', 'QualificazionePdDAsincronoSimmFruitCostante', 'FRUITORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (131072, '1-QualificazionePDD//ISTANZE//131072-QualificazionePdDRiceviRispostaAsincronoSimmErog//', 'QualificazionePdDRiceviRispostaAsincronoSimmErog', 'EROGATORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (131073, '1-QualificazionePDD//ISTANZE//131073-QualificazionePdDRiceviRispostaAsincronoSimmFruitCostante//', 'QualificazionePdDRiceviRispostaAsincronoSimmFruitCostante', 'FRUITORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (163840, '1-QualificazionePDD//ISTANZE//163840-QualificazionePdDOneWayErog//', 'QualificazionePdDOneWayErog', 'EROGATORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (163841, '1-QualificazionePDD//ISTANZE//163841-QualificazionePdDOneWayFruitCostante//', 'QualificazionePdDOneWayFruitCostante', 'FRUITORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (196608, '1-QualificazionePDD//ISTANZE//196608-QualificazionePdDEndErog//', 'QualificazionePdDEndErog', 'EROGATORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (196609, '1-QualificazionePDD//ISTANZE//196609-QualificazionePdDEndFruit//', 'QualificazionePdDEndFruit', 'FRUITORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (229376, '1-QualificazionePDD//ISTANZE//229376-QualificazionePdDAsincAsimErog//', 'QualificazionePdDAsincAsimErog', 'EROGATORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (229377, '1-QualificazionePDD//ISTANZE//229377-QualificazionePdDAsincAsimFrui//', 'QualificazionePdDAsincAsimFrui', 'FRUITORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (262145, '1-QualificazionePDD//ISTANZE//262145-QualificazionePdDCheckAsincAsimErog//', 'QualificazionePdDCheckAsincAsimErog', 'EROGATORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (262146, '1-QualificazionePDD//ISTANZE//262146-QualificazionePdDCheckAsincAsimFrui//', 'QualificazionePdDCheckAsincAsimFrui', 'FRUITORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (294912, '1-QualificazionePDD//ISTANZE//294912-QualificazionePdDGetTracciaErogatore//', 'QualificazionePdDGetTracciaErogatore', 'EROGATORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (294913, '1-QualificazionePDD//ISTANZE//294913-QualificazionePdDGetTracciaFruitCostante//', 'QualificazionePdDGetTracciaFruitCostante', 'FRUITORE', 1);
ALTER TABLE istanzaaccordodicollaborazione ENABLE TRIGGER ALL;


ALTER TABLE istanzaoperation DISABLE TRIGGER ALL;
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, datirichiesta_id, operation_id, datirisposta_id, istanzaporttype_id,autenticazionefederata,avviorapido,inviamail) VALUES (1, NULL, NULL, 'trasformazioneRichiesta.xslt', NULL, NULL, false, false, NULL, false, NULL, 4, 1, 1,false,false,false);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, datirichiesta_id, operation_id, datirisposta_id, istanzaporttype_id,autenticazionefederata,avviorapido,inviamail) VALUES (32768, NULL, NULL, 'trasformazioneRichiesta.xslt', NULL, NULL, false, false, NULL, false, NULL, 6, 32768, 32768,false,false,false);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, datirichiesta_id, operation_id, datirisposta_id, istanzaporttype_id,autenticazionefederata,avviorapido,inviamail) VALUES (65536, NULL, NULL, 'trasformazioneRichiesta.xslt', NULL, NULL, false, false, NULL, false, NULL, 3, 65536, 65536,false,false,false);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, datirichiesta_id, operation_id, datirisposta_id, istanzaporttype_id,autenticazionefederata,avviorapido,inviamail) VALUES (32769, NULL, NULL, NULL, NULL, NULL, false, false, NULL, false, 32769, 6, NULL, 32769,false,false,false);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, datirichiesta_id, operation_id, datirisposta_id, istanzaporttype_id,autenticazionefederata,avviorapido,inviamail) VALUES (2, NULL, NULL, NULL, NULL, NULL, false, false, NULL, false, 2, 4, NULL, 2,false,false,false);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, datirichiesta_id, operation_id, datirisposta_id, istanzaporttype_id,autenticazionefederata,avviorapido,inviamail) VALUES (65537, NULL, NULL, NULL, NULL, NULL, false, false, NULL, false, 65537, 3, NULL, 65537,false,false,false);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, datirichiesta_id, operation_id, datirisposta_id, istanzaporttype_id,autenticazionefederata,avviorapido,inviamail) VALUES (131072, NULL, NULL, 'trasformazioneRichiesta.xslt', NULL, NULL, false, false, NULL, false, NULL, 9, 131072, 131072,false,false,false);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, datirichiesta_id, operation_id, datirisposta_id, istanzaporttype_id,autenticazionefederata,avviorapido,inviamail) VALUES (131073, NULL, NULL, NULL, NULL, NULL, false, false, NULL, false, 131073, 9, NULL, 131073,false,false,false);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, datirichiesta_id, operation_id, datirisposta_id, istanzaporttype_id,autenticazionefederata,avviorapido,inviamail) VALUES (163840, NULL, NULL, 'trasformazioneVuota.xslt', NULL, NULL, false, false, NULL, false, NULL, 8, 163840, 163840,false,false,false);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, datirichiesta_id, operation_id, datirisposta_id, istanzaporttype_id,autenticazionefederata,avviorapido,inviamail) VALUES (163841, NULL, NULL, NULL, NULL, NULL, false, false, NULL, false, 163841, 8, NULL, 163841,false,false,false);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, datirichiesta_id, operation_id, datirisposta_id, istanzaporttype_id,autenticazionefederata,avviorapido,inviamail) VALUES (196608, NULL, NULL, 'trasformazioneRichiesta.xslt', NULL, NULL, false, false, NULL, false, NULL, 5, 196608, 196608,false,false,false);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, datirichiesta_id, operation_id, datirisposta_id, istanzaporttype_id,autenticazionefederata,avviorapido,inviamail) VALUES (196609, NULL, NULL, NULL, NULL, NULL, false, false, NULL, false, 196609, 5, NULL, 196609,false,false,false);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, datirichiesta_id, operation_id, datirisposta_id, istanzaporttype_id,autenticazionefederata,avviorapido,inviamail) VALUES (229376, NULL, NULL, 'trasformazioneRichiesta.xslt', NULL, NULL, false, false, NULL, false, NULL, 1, 229376, 229376,false,false,false);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, datirichiesta_id, operation_id, datirisposta_id, istanzaporttype_id,autenticazionefederata,avviorapido,inviamail) VALUES (229377, NULL, NULL, NULL, NULL, NULL, false, false, NULL, false, 229377, 1, NULL, 229377,false,false,false);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, datirichiesta_id, operation_id, datirisposta_id, istanzaporttype_id,autenticazionefederata,avviorapido,inviamail) VALUES (262145, NULL, NULL, 'trasformazioneRichiesta.xslt', NULL, NULL, false, false, NULL, false, NULL, 2, 262145, 262145,false,false,false);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, datirichiesta_id, operation_id, datirisposta_id, istanzaporttype_id,autenticazionefederata,avviorapido,inviamail) VALUES (262146, NULL, NULL, NULL, NULL, NULL, false, false, NULL, false, 262146, 2, NULL, 262146,false,false,false);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, datirichiesta_id, operation_id, datirisposta_id, istanzaporttype_id,autenticazionefederata,avviorapido,inviamail) VALUES (294912, NULL, NULL, 'trasformazione_risposta.xslt', 'estrai_messaggi_db.xslt', NULL, false, false, NULL, false, NULL, 7, 294912, 294912,false,false,false);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, datirichiesta_id, operation_id, datirisposta_id, istanzaporttype_id,autenticazionefederata,avviorapido,inviamail) VALUES (294913, NULL, NULL, NULL, NULL, NULL, false, false, NULL, false, 294913, 7, NULL, 294913,false,false,false);
ALTER TABLE istanzaoperation ENABLE TRIGGER ALL;


ALTER TABLE istanzaporttype DISABLE TRIGGER ALL;
INSERT INTO istanzaporttype (id, urlinvio, tipo, porttype_id, istanzaaccordo_id) VALUES (1, NULL, 'EROGAZIONE', 1, 1);
INSERT INTO istanzaporttype (id, urlinvio, tipo, porttype_id, istanzaaccordo_id) VALUES (32768, NULL, 'EROGAZIONE', 1, 32768);
INSERT INTO istanzaporttype (id, urlinvio, tipo, porttype_id, istanzaaccordo_id) VALUES (65536, NULL, 'EROGAZIONE', 1, 65536);
INSERT INTO istanzaporttype (id, urlinvio, tipo, porttype_id, istanzaaccordo_id) VALUES (32769, 'http://localhost:9192/EROGATORE/QualificazionePDD_32768/', 'FRUIZIONE', 1, 32769);
INSERT INTO istanzaporttype (id, urlinvio, tipo, porttype_id, istanzaaccordo_id) VALUES (2, 'http://localhost:9192/EROGATORE/QualificazionePDD_1/', 'FRUIZIONE', 1, 2);
INSERT INTO istanzaporttype (id, urlinvio, tipo, porttype_id, istanzaaccordo_id) VALUES (65537, 'http://localhost:9192/EROGATORE/QualificazionePDD_65536/', 'FRUIZIONE', 1, 65537);
INSERT INTO istanzaporttype (id, urlinvio, tipo, porttype_id, istanzaaccordo_id) VALUES (131072, NULL, 'EROGAZIONE', 2, 131072);
INSERT INTO istanzaporttype (id, urlinvio, tipo, porttype_id, istanzaaccordo_id) VALUES (131073, 'http://localhost:9192/EROGATORE/QualificazionePDDCorrelato_131072/', 'FRUIZIONE', 2, 131073);
INSERT INTO istanzaporttype (id, urlinvio, tipo, porttype_id, istanzaaccordo_id) VALUES (163840, NULL, 'EROGAZIONE', 1, 163840);
INSERT INTO istanzaporttype (id, urlinvio, tipo, porttype_id, istanzaaccordo_id) VALUES (163841, 'http://localhost:9192/EROGATORE/QualificazionePDD_163840/', 'FRUIZIONE', 1, 163841);
INSERT INTO istanzaporttype (id, urlinvio, tipo, porttype_id, istanzaaccordo_id) VALUES (196608, NULL, 'EROGAZIONE', 1, 196608);
INSERT INTO istanzaporttype (id, urlinvio, tipo, porttype_id, istanzaaccordo_id) VALUES (196609, 'http://localhost:9192/EROGATORE/QualificazionePDD_196608/', 'FRUIZIONE', 1, 196609);
INSERT INTO istanzaporttype (id, urlinvio, tipo, porttype_id, istanzaaccordo_id) VALUES (229376, NULL, 'EROGAZIONE', 1, 229376);
INSERT INTO istanzaporttype (id, urlinvio, tipo, porttype_id, istanzaaccordo_id) VALUES (229377, 'http://localhost:9192/EROGATORE/QualificazionePDD_229376/', 'FRUIZIONE', 1, 229377);
INSERT INTO istanzaporttype (id, urlinvio, tipo, porttype_id, istanzaaccordo_id) VALUES (262145, NULL, 'EROGAZIONE', 1, 262145);
INSERT INTO istanzaporttype (id, urlinvio, tipo, porttype_id, istanzaaccordo_id) VALUES (262146, 'http://localhost:9192/EROGATORE/QualificazionePDD_262145/', 'FRUIZIONE', 1, 262146);
INSERT INTO istanzaporttype (id, urlinvio, tipo, porttype_id, istanzaaccordo_id) VALUES (294912, NULL, 'EROGAZIONE', 1, 294912);
INSERT INTO istanzaporttype (id, urlinvio, tipo, porttype_id, istanzaaccordo_id) VALUES (294913, 'http://localhost:9192/EROGATORE/QualificazionePDD_294912/', 'FRUIZIONE', 1, 294913);
ALTER TABLE istanzaporttype ENABLE TRIGGER ALL;

ALTER TABLE message DISABLE TRIGGER ALL;
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (1, 'http://sica.spcoop.it/servizi/QualificazionePDDWS', 'richiesta_RichiestaRispostaAsincrona_testAsincronoAsimmetrico_Msg', 'richiesta_RichiestaRispostaAsincrona_testAsincronoAsimmetrico', 1);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (2, 'http://sica.spcoop.it/servizi/QualificazionePDDWS', 'risposta_RichiestaRispostaAsincrona_testAsincronoAsimmetrico_Msg', 'risposta_RichiestaRispostaAsincrona_testAsincronoAsimmetrico', 1);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (3, 'http://sica.spcoop.it/servizi/QualificazionePDDWS', 'richiesta_RichiestaRispostaAsincrona_checkTestAsincronoAsimmetrico_Msg', 'richiesta_RichiestaRispostaAsincrona_checkTestAsincronoAsimmetrico', 2);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (4, 'http://sica.spcoop.it/servizi/QualificazionePDDWS', 'risposta_RichiestaRispostaAsincrona_checkTestAsincronoAsimmetrico_Msg', 'risposta_RichiestaRispostaAsincrona_checkTestAsincronoAsimmetrico', 2);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (5, 'http://sica.spcoop.it/servizi/QualificazionePDDWS', 'richiesta_RichiestaRispostaAsincrona_testAsincronoSimmetrico_Msg', 'richiesta_RichiestaRispostaAsincrona_testAsincronoSimmetrico', 3);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (6, 'http://sica.spcoop.it/servizi/QualificazionePDDWS', 'risposta_RichiestaRispostaAsincrona_testAsincronoSimmetrico_Msg', 'risposta_RichiestaRispostaAsincrona_testAsincronoSimmetrico', 3);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (7, 'http://sica.spcoop.it/servizi/QualificazionePDDWS', 'richiesta_RichiestaRispostaSincrona_start_Msg', 'richiesta_RichiestaRispostaSincrona_start', 4);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (8, 'http://sica.spcoop.it/servizi/QualificazionePDDWS', 'risposta_RichiestaRispostaSincrona_start_Msg', 'risposta_RichiestaRispostaSincrona_start', 4);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (9, 'http://sica.spcoop.it/servizi/QualificazionePDDWS', 'richiesta_RichiestaRispostaSincrona_end_Msg', 'richiesta_RichiestaRispostaSincrona_end', 5);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (10, 'http://sica.spcoop.it/servizi/QualificazionePDDWS', 'risposta_RichiestaRispostaSincrona_end_Msg', 'risposta_RichiestaRispostaSincrona_end', 5);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (11, 'http://sica.spcoop.it/servizi/QualificazionePDDWS', 'richiesta_RichiestaRispostaSincrona_testSincrono_Msg', 'richiesta_RichiestaRispostaSincrona_testSincrono', 6);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (12, 'http://sica.spcoop.it/servizi/QualificazionePDDWS', 'risposta_RichiestaRispostaSincrona_testSincrono_Msg', 'risposta_RichiestaRispostaSincrona_testSincrono', 6);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (13, 'http://sica.spcoop.it/servizi/QualificazionePDDWS', 'richiesta_RichiestaRispostaSincrona_getTraccia_Msg', 'richiesta_RichiestaRispostaSincrona_getTraccia', 7);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (14, 'http://sica.spcoop.it/servizi/QualificazionePDDWS', 'risposta_RichiestaRispostaSincrona_getTraccia_Msg', 'risposta_RichiestaRispostaSincrona_getTraccia', 7);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (15, 'http://sica.spcoop.it/servizi/QualificazionePDDWS', 'richiesta_RichiestaSenzaRisposta_testOneWay_Msg', 'richiesta_RichiestaSenzaRisposta_testOneWay', 8);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (16, 'http://sica.spcoop.it/servizi/QualificazionePDDWS', 'segnalazione_NotificazioneRisposta_riceviRispostaTestAsincronoSimmetrico_Msg', 'segnalazione_NotificazioneRisposta_riceviRispostaTestAsincronoSimmetrico', 9);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (17, 'http://sica.spcoop.it/servizi/QualificazionePDDWS', 'risposta_NotificazioneRisposta_riceviRispostaTestAsincronoSimmetrico_Msg', 'risposta_NotificazioneRisposta_riceviRispostaTestAsincronoSimmetrico', 9);
ALTER TABLE message ENABLE TRIGGER ALL;

ALTER TABLE operation DISABLE TRIGGER ALL;
INSERT INTO operation (id, nome, profilodicollaborazione, messagein_id, messageout_id, messagefault_id, porttype_id) VALUES (4, 'start', 'SINCRONO', 7, 8, NULL, 1);
INSERT INTO operation (id, nome, profilodicollaborazione, messagein_id, messageout_id, messagefault_id, porttype_id) VALUES (5, 'end', 'SINCRONO', 9, 10, NULL, 1);
INSERT INTO operation (id, nome, profilodicollaborazione, messagein_id, messageout_id, messagefault_id, porttype_id) VALUES (6, 'testSincrono', 'SINCRONO', 11, 12, NULL, 1);
INSERT INTO operation (id, nome, profilodicollaborazione, messagein_id, messageout_id, messagefault_id, porttype_id) VALUES (7, 'getTraccia', 'SINCRONO', 13, 14, NULL, 1);
INSERT INTO operation (id, nome, profilodicollaborazione, messagein_id, messageout_id, messagefault_id, porttype_id) VALUES (8, 'testOneWay', 'ONE_WAY', 15, NULL, NULL, 1);
INSERT INTO operation (id, nome, profilodicollaborazione, messagein_id, messageout_id, messagefault_id, porttype_id) VALUES (3, 'testAsincronoSimmetrico', 'SINCRONO', 5, 6, NULL, 1);
INSERT INTO operation (id, nome, profilodicollaborazione, messagein_id, messageout_id, messagefault_id, porttype_id) VALUES (9, 'riceviRispostaTestAsincronoSimmetrico', 'SINCRONO', 16, 17, NULL, 2);
INSERT INTO operation (id, nome, profilodicollaborazione, messagein_id, messageout_id, messagefault_id, porttype_id) VALUES (1, 'testAsincronoAsimmetrico', 'SINCRONO', 1, 2, NULL, 1);
INSERT INTO operation (id, nome, profilodicollaborazione, messagein_id, messageout_id, messagefault_id, porttype_id) VALUES (2, 'checkTestAsincronoAsimmetrico', 'SINCRONO', 3, 4, NULL, 1);
ALTER TABLE operation ENABLE TRIGGER ALL;


ALTER TABLE porttype DISABLE TRIGGER ALL;
INSERT INTO porttype (id, nome, ruolo, accordodicollaborazione_id) VALUES (1, 'QualificazionePDD', 'EROGATORE', 1);
INSERT INTO porttype (id, nome, ruolo, accordodicollaborazione_id) VALUES (2, 'QualificazionePDDCorrelato', 'EROGATORE', 1);
ALTER TABLE porttype ENABLE TRIGGER ALL;


ALTER TABLE query DISABLE TRIGGER ALL;
INSERT INTO query (id, indirizzodb, nomedb, nomeutente, password, query, tipodb) VALUES (1, 'localhost', 'freesbee', 'freesbee', '**freesbee**', 'select * from messaggio left join eccezione on eccezione.messaggio_id = messaggio.id', 'PostgreSQL');
ALTER TABLE query ENABLE TRIGGER ALL;