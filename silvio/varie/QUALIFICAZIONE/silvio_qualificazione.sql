ALTER TABLE accordodicollaborazione DISABLE TRIGGER ALL;
DELETE FROM accordodicollaborazione;
INSERT INTO accordodicollaborazione (id, wsdlerogatore, wsdlfruitore, cartellafiles, filetypes, nome) VALUES (1, 'QualificazionePDD_AdsParteComune//QualificazionePDD_AdsParteComune//componentiAds//parteComune//specificaInterfacciaParteComune//QualificazionePDD_ErogatoreLogico.wsdl', 'QualificazionePDD_AdsParteComune//QualificazionePDD_AdsParteComune//componentiAds//parteComune//specificaInterfacciaParteComune//QualificazionePDD_FruitoreLogico.wsdl', '1-QualificazionePDD', NULL, 'QualificazionePDD');
ALTER TABLE accordodicollaborazione ENABLE TRIGGER ALL;

ALTER TABLE datiinterattivi DISABLE TRIGGER ALL;
DELETE FROM datiinterattivi;
INSERT INTO datiinterattivi (id) VALUES (1);
INSERT INTO datiinterattivi (id) VALUES (2);
INSERT INTO datiinterattivi (id) VALUES (4);
INSERT INTO datiinterattivi (id) VALUES (5);
INSERT INTO datiinterattivi (id) VALUES (32768);
INSERT INTO datiinterattivi (id) VALUES (32769);
INSERT INTO datiinterattivi (id) VALUES (32770);
INSERT INTO datiinterattivi (id) VALUES (32771);
INSERT INTO datiinterattivi (id) VALUES (32772);
ALTER TABLE datiinterattivi ENABLE TRIGGER ALL;

ALTER TABLE query DISABLE TRIGGER ALL;
DELETE FROM query;
INSERT INTO query (id, indirizzodb, nomedb, nomeutente, password, query, tipodb) VALUES (1, 'localhost', 'freesbee', 'freesbee', '**freesbee**', 'select * from messaggio left join eccezione on eccezione.messaggio_id = messaggio.id', 'PostgreSQL');
ALTER TABLE query ENABLE TRIGGER ALL;


ALTER TABLE datisql DISABLE TRIGGER ALL;
DELETE FROM datisql;
INSERT INTO datisql (id, select_id) VALUES (1, 1);
ALTER TABLE datisql ENABLE TRIGGER ALL;


ALTER TABLE dati DISABLE TRIGGER ALL;
DELETE FROM dati;
INSERT INTO dati (id, payloadcostante, daticostanti_id, datiinterattivi_id, datisql_id) VALUES (163840, NULL, NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, daticostanti_id, datiinterattivi_id, datisql_id) VALUES (294912, NULL, NULL, NULL, 1);
INSERT INTO dati (id, payloadcostante, daticostanti_id, datiinterattivi_id, datisql_id) VALUES (327684, NULL, NULL, 5, NULL);
INSERT INTO dati (id, payloadcostante, daticostanti_id, datiinterattivi_id, datisql_id) VALUES (360451, NULL, NULL, 32770, NULL);
INSERT INTO dati (id, payloadcostante, daticostanti_id, datiinterattivi_id, datisql_id) VALUES (229376, NULL, NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, daticostanti_id, datiinterattivi_id, datisql_id) VALUES (327680, NULL, NULL, 1, NULL);
INSERT INTO dati (id, payloadcostante, daticostanti_id, datiinterattivi_id, datisql_id) VALUES (1, NULL, NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, daticostanti_id, datiinterattivi_id, datisql_id) VALUES (360452, NULL, NULL, 32771, NULL);
INSERT INTO dati (id, payloadcostante, daticostanti_id, datiinterattivi_id, datisql_id) VALUES (327683, NULL, NULL, 4, NULL);
INSERT INTO dati (id, payloadcostante, daticostanti_id, datiinterattivi_id, datisql_id) VALUES (360448, NULL, NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, daticostanti_id, datiinterattivi_id, datisql_id) VALUES (262145, NULL, NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, daticostanti_id, datiinterattivi_id, datisql_id) VALUES (327681, NULL, NULL, 2, NULL);
INSERT INTO dati (id, payloadcostante, daticostanti_id, datiinterattivi_id, datisql_id) VALUES (65536, NULL, NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, daticostanti_id, datiinterattivi_id, datisql_id) VALUES (32768, NULL, NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, daticostanti_id, datiinterattivi_id, datisql_id) VALUES (360453, NULL, NULL, 32772, NULL);
INSERT INTO dati (id, payloadcostante, daticostanti_id, datiinterattivi_id, datisql_id) VALUES (360449, NULL, NULL, 32768, NULL);
INSERT INTO dati (id, payloadcostante, daticostanti_id, datiinterattivi_id, datisql_id) VALUES (360450, NULL, NULL, 32769, NULL);
INSERT INTO dati (id, payloadcostante, daticostanti_id, datiinterattivi_id, datisql_id) VALUES (196608, NULL, NULL, NULL, NULL);
ALTER TABLE dati ENABLE TRIGGER ALL;

ALTER TABLE datoprimitivo DISABLE TRIGGER ALL;
DELETE FROM datoprimitivo;
INSERT INTO datoprimitivo (id, nome, tipo, valore, datiinterattivi_id, daticostanti_id) VALUES (1, 'TokenSessione', NULL, NULL, 1, NULL);
INSERT INTO datoprimitivo (id, nome, tipo, valore, datiinterattivi_id, daticostanti_id) VALUES (2, 'TokenSessione', NULL, NULL, 2, NULL);
INSERT INTO datoprimitivo (id, nome, tipo, valore, datiinterattivi_id, daticostanti_id) VALUES (3, 'TokenSessione', NULL, NULL, 4, NULL);
INSERT INTO datoprimitivo (id, nome, tipo, valore, datiinterattivi_id, daticostanti_id) VALUES (4, 'TokenSessione', NULL, NULL, 5, NULL);
INSERT INTO datoprimitivo (id, nome, tipo, valore, datiinterattivi_id, daticostanti_id) VALUES (32768, 'TokenSessione', NULL, NULL, 32768, NULL);
INSERT INTO datoprimitivo (id, nome, tipo, valore, datiinterattivi_id, daticostanti_id) VALUES (32769, 'TokenSessione', NULL, NULL, 32769, NULL);
INSERT INTO datoprimitivo (id, nome, tipo, valore, datiinterattivi_id, daticostanti_id) VALUES (32770, 'TokenSessione', NULL, NULL, 32770, NULL);
INSERT INTO datoprimitivo (id, nome, tipo, valore, datiinterattivi_id, daticostanti_id) VALUES (32771, 'TokenSessione', NULL, NULL, 32771, NULL);
INSERT INTO datoprimitivo (id, nome, tipo, valore, datiinterattivi_id, daticostanti_id) VALUES (32772, 'TokenSessione', NULL, NULL, 32772, NULL);
ALTER TABLE datoprimitivo ENABLE TRIGGER ALL;


ALTER TABLE hibernate_sequences DISABLE TRIGGER ALL;
DELETE FROM hibernate_sequences;
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('AccordoDiCollaborazione', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('PortType', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Operation', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Message', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('MessaggioSbloccoManuale', 2);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('DatiSQL', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Query', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Messaggio', 56);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('IstanzaAccordoDiCollaborazione', 12);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('IstanzaPortType', 12);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('IstanzaOperation', 12);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Dati', 12);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('DatiInterattivi', 2);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('DatoPrimitivo', 2);
ALTER TABLE hibernate_sequences ENABLE TRIGGER ALL;


ALTER TABLE istanzaaccordodicollaborazione DISABLE TRIGGER ALL;
DELETE FROM istanzaaccordodicollaborazione;
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (1, '1-QualificazionePDD//ISTANZE//1-QualificazionePdDStartErog//', 'QualificazionePdDStartErog', 'EROGATORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (32768, '1-QualificazionePDD//ISTANZE//32768-QualificazionePDD_sincrono//', 'QualificazionePdDSincronoErog', 'EROGATORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (65536, '1-QualificazionePDD//ISTANZE//65536-QualificazionePdDAsincronoSimmErog//', 'QualificazionePdDAsincronoSimmErog', 'EROGATORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (163840, '1-QualificazionePDD//ISTANZE//163840-QualificazionePdDOneWayErog//', 'QualificazionePdDOneWayErog', 'EROGATORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (196608, '1-QualificazionePDD//ISTANZE//196608-QualificazionePdDEndErog//', 'QualificazionePdDEndErog', 'EROGATORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (229376, '1-QualificazionePDD//ISTANZE//229376-QualificazionePdDAsincAsimErog//', 'QualificazionePdDAsincAsimErog', 'EROGATORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (262145, '1-QualificazionePDD//ISTANZE//262145-QualificazionePdDCheckAsincAsimErog//', 'QualificazionePdDCheckAsincAsimErog', 'EROGATORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (294912, '1-QualificazionePDD//ISTANZE//294912-QualificazionePdDGetTracciaErogatore//', 'QualificazionePdDGetTracciaErogatore', 'EROGATORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (327680, '1-QualificazionePDD/ISTANZE/327680-QualificazionePdDStartFruit/', 'QualificazionePdDStartFruit', 'FRUITORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (327681, '1-QualificazionePDD/ISTANZE/327681-QualificazionePdDAsincronoSimmFruit/', 'QualificazionePdDAsincronoSimmFruit', 'FRUITORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (327683, '1-QualificazionePDD/ISTANZE/327683-QualificazionePdDRiceviRispostaAsincronoSimmFruit/', 'QualificazionePdDRiceviRispostaAsincronoSimmFruit', 'FRUITORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (327684, '1-QualificazionePDD/ISTANZE/327684-QualificazionePdDSincronoFruit/', 'QualificazionePdDSincronoFruit', 'FRUITORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (360448, '1-QualificazionePDD/ISTANZE/360448-QualificazionePdDRiceviRispostaAsincronoSimmErog/', 'QualificazionePdDRiceviRispostaAsincronoSimmErog', 'EROGATORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (360449, '1-QualificazionePDD/ISTANZE/360449-QualificazionePdDOneWayFruit/', 'QualificazionePdDOneWayFruit', 'FRUITORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (360450, '1-QualificazionePDD/ISTANZE/360450-QualificazionePdDEndFruit/', 'QualificazionePdDEndFruit', 'FRUITORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (360451, '1-QualificazionePDD/ISTANZE/360451-QualificazionePdDAsincAsimFrui/', 'QualificazionePdDAsincAsimFrui', 'FRUITORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (360452, '1-QualificazionePDD/ISTANZE/360452-QualificazionePdDCheckAsincAsimFrui/', 'QualificazionePdDCheckAsincAsimFrui', 'FRUITORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (360453, '1-QualificazionePDD/ISTANZE/360453-QualificazionePdDGetTracciaFruit/', 'QualificazionePdDGetTracciaFruit', 'FRUITORE', 1);
ALTER TABLE istanzaaccordodicollaborazione ENABLE TRIGGER ALL;


ALTER TABLE porttype DISABLE TRIGGER ALL;
DELETE FROM porttype;
INSERT INTO porttype (id, nome, ruolo, accordodicollaborazione_id) VALUES (1, 'QualificazionePDD', 'EROGATORE', 1);
INSERT INTO porttype (id, nome, ruolo, accordodicollaborazione_id) VALUES (2, 'QualificazionePDDCorrelato', 'EROGATORE', 1);
ALTER TABLE porttype ENABLE TRIGGER ALL;


ALTER TABLE istanzaporttype DISABLE TRIGGER ALL;
DELETE FROM istanzaporttype;
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (1, NULL, 'EROGAZIONE', 1, 1);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (32768, NULL, 'EROGAZIONE', 32768, 1);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (65536, NULL, 'EROGAZIONE', 65536, 1);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (163840, NULL, 'EROGAZIONE', 163840, 1);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (196608, NULL, 'EROGAZIONE', 196608, 1);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (229376, NULL, 'EROGAZIONE', 229376, 1);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (262145, NULL, 'EROGAZIONE', 262145, 1);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (294912, NULL, 'EROGAZIONE', 294912, 1);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (327680, 'http://localhost:9192/EROGATORE/QualificazionePDD_1/', 'FRUIZIONE', 327680, 1);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (327681, 'http://localhost:9192/EROGATORE/QualificazionePDD_65536/', 'FRUIZIONE', 327681, 1);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (327684, 'http://localhost:9192/EROGATORE/QualificazionePDD_32768/', 'FRUIZIONE', 327684, 1);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (360448, NULL, 'EROGAZIONE', 360448, 2);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (327683, 'http://localhost:9192/EROGATORE/QualificazionePDDCorrelato_360448/', 'FRUIZIONE', 327683, 2);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (360449, 'http://localhost:9192/EROGATORE/QualificazionePDD_163840/', 'FRUIZIONE', 360449, 1);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (360450, 'http://localhost:9192/EROGATORE/QualificazionePDD_196608/', 'FRUIZIONE', 360450, 1);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (360451, 'http://localhost:9192/EROGATORE/QualificazionePDD_229376/', 'FRUIZIONE', 360451, 1);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (360452, 'http://localhost:9192/EROGATORE/QualificazionePDD_262145/', 'FRUIZIONE', 360452, 1);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (360453, 'http://localhost:9192/EROGATORE/QualificazionePDD_294912/', 'FRUIZIONE', 360453, 1);
ALTER TABLE istanzaporttype ENABLE TRIGGER ALL;


ALTER TABLE message DISABLE TRIGGER ALL;
DELETE FROM message;
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
DELETE FROM operation;
INSERT INTO operation (id, nome, profilodicollaborazione, messagefault_id, messagein_id, messageout_id, porttype_id) VALUES (4, 'start', 'SINCRONO', NULL, 7, 8, 1);
INSERT INTO operation (id, nome, profilodicollaborazione, messagefault_id, messagein_id, messageout_id, porttype_id) VALUES (5, 'end', 'SINCRONO', NULL, 9, 10, 1);
INSERT INTO operation (id, nome, profilodicollaborazione, messagefault_id, messagein_id, messageout_id, porttype_id) VALUES (6, 'testSincrono', 'SINCRONO', NULL, 11, 12, 1);
INSERT INTO operation (id, nome, profilodicollaborazione, messagefault_id, messagein_id, messageout_id, porttype_id) VALUES (7, 'getTraccia', 'SINCRONO', NULL, 13, 14, 1);
INSERT INTO operation (id, nome, profilodicollaborazione, messagefault_id, messagein_id, messageout_id, porttype_id) VALUES (8, 'testOneWay', 'ONE_WAY', NULL, 15, NULL, 1);
INSERT INTO operation (id, nome, profilodicollaborazione, messagefault_id, messagein_id, messageout_id, porttype_id) VALUES (3, 'testAsincronoSimmetrico', 'SINCRONO', NULL, 5, 6, 1);
INSERT INTO operation (id, nome, profilodicollaborazione, messagefault_id, messagein_id, messageout_id, porttype_id) VALUES (9, 'riceviRispostaTestAsincronoSimmetrico', 'SINCRONO', NULL, 16, 17, 2);
INSERT INTO operation (id, nome, profilodicollaborazione, messagefault_id, messagein_id, messageout_id, porttype_id) VALUES (1, 'testAsincronoAsimmetrico', 'SINCRONO', NULL, 1, 2, 1);
INSERT INTO operation (id, nome, profilodicollaborazione, messagefault_id, messagein_id, messageout_id, porttype_id) VALUES (2, 'checkTestAsincronoAsimmetrico', 'SINCRONO', NULL, 3, 4, 1);
ALTER TABLE operation ENABLE TRIGGER ALL;


ALTER TABLE istanzaoperation DISABLE TRIGGER ALL;
DELETE FROM istanzaoperation;
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, istanzaporttype_id, operation_id, datirichiesta_id, datirisposta_id, parametrisla_id) VALUES (1, NULL, NULL, 'trasformazioneRichiesta.xslt', NULL, NULL, NULL, false, false, false, false, NULL, NULL, false, NULL, NULL, false, NULL, 1, 4, NULL, 1, NULL);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, istanzaporttype_id, operation_id, datirichiesta_id, datirisposta_id, parametrisla_id) VALUES (32768, NULL, NULL, 'trasformazioneRichiesta.xslt', NULL, NULL, NULL, false, false, false, false, NULL, NULL, false, NULL, NULL, false, NULL, 32768, 6, NULL, 32768, NULL);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, istanzaporttype_id, operation_id, datirichiesta_id, datirisposta_id, parametrisla_id) VALUES (65536, NULL, NULL, 'trasformazioneRichiesta.xslt', NULL, NULL, NULL, false, false, false, false, NULL, NULL, false, NULL, NULL, false, NULL, 65536, 3, NULL, 65536, NULL);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, istanzaporttype_id, operation_id, datirichiesta_id, datirisposta_id, parametrisla_id) VALUES (163840, NULL, NULL, 'trasformazioneVuota.xslt', NULL, NULL, NULL, false, false, false, false, NULL, NULL, false, NULL, NULL, false, NULL, 163840, 8, NULL, 163840, NULL);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, istanzaporttype_id, operation_id, datirichiesta_id, datirisposta_id, parametrisla_id) VALUES (196608, NULL, NULL, 'trasformazioneRichiesta.xslt', NULL, NULL, NULL, false, false, false, false, NULL, NULL, false, NULL, NULL, false, NULL, 196608, 5, NULL, 196608, NULL);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, istanzaporttype_id, operation_id, datirichiesta_id, datirisposta_id, parametrisla_id) VALUES (229376, NULL, NULL, 'trasformazioneRichiesta.xslt', NULL, NULL, NULL, false, false, false, false, NULL, NULL, false, NULL, NULL, false, NULL, 229376, 1, NULL, 229376, NULL);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, istanzaporttype_id, operation_id, datirichiesta_id, datirisposta_id, parametrisla_id) VALUES (262145, NULL, NULL, 'trasformazioneRichiesta.xslt', NULL, NULL, NULL, false, false, false, false, NULL, NULL, false, NULL, NULL, false, NULL, 262145, 2, NULL, 262145, NULL);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, istanzaporttype_id, operation_id, datirichiesta_id, datirisposta_id, parametrisla_id) VALUES (294912, NULL, NULL, 'trasformazione_risposta.xslt', NULL, 'estrai_messaggi_db.xslt', NULL, false, false, false, false, NULL, NULL, false, NULL, NULL, false, NULL, 294912, 7, NULL, 294912, NULL);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, istanzaporttype_id, operation_id, datirichiesta_id, datirisposta_id, parametrisla_id) VALUES (327680, 'trasformazioneRichiesta.xslt', NULL, NULL, NULL, NULL, NULL, false, false, false, false, NULL, NULL, false, NULL, NULL, false, NULL, 327680, 4, 327680, NULL, NULL);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, istanzaporttype_id, operation_id, datirichiesta_id, datirisposta_id, parametrisla_id) VALUES (327681, 'trasformazioneRichiesta.xslt', NULL, NULL, NULL, NULL, NULL, false, false, false, false, NULL, NULL, false, NULL, NULL, false, NULL, 327681, 3, 327681, NULL, NULL);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, istanzaporttype_id, operation_id, datirichiesta_id, datirisposta_id, parametrisla_id) VALUES (327684, 'trasformazioneRichiesta.xslt', NULL, NULL, NULL, NULL, NULL, false, false, false, false, NULL, NULL, false, NULL, NULL, false, NULL, 327684, 6, 327684, NULL, NULL);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, istanzaporttype_id, operation_id, datirichiesta_id, datirisposta_id, parametrisla_id) VALUES (360448, NULL, NULL, 'trasformazioneRichiesta.xslt', NULL, NULL, NULL, false, false, false, false, NULL, NULL, false, NULL, NULL, false, NULL, 360448, 9, NULL, 360448, NULL);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, istanzaporttype_id, operation_id, datirichiesta_id, datirisposta_id, parametrisla_id) VALUES (327683, 'trasformazioneRichiesta.xslt', NULL, NULL, NULL, NULL, NULL, false, false, false, false, NULL, NULL, false, NULL, NULL, false, NULL, 327683, 9, 327683, NULL, NULL);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, istanzaporttype_id, operation_id, datirichiesta_id, datirisposta_id, parametrisla_id) VALUES (360449, 'trasformazioneRichiesta.xslt', NULL, NULL, NULL, NULL, NULL, false, false, false, false, NULL, NULL, false, NULL, NULL, false, NULL, 360449, 8, 360449, NULL, NULL);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, istanzaporttype_id, operation_id, datirichiesta_id, datirisposta_id, parametrisla_id) VALUES (360450, 'trasformazioneRichiesta.xslt', NULL, NULL, NULL, NULL, NULL, false, false, false, false, NULL, NULL, false, NULL, NULL, false, NULL, 360450, 5, 360450, NULL, NULL);

//INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, istanzaporttype_id, operation_id, datirichiesta_id, datirisposta_id, parametrisla_id) VALUES (360451, 'trasformazioneRichiesta.xslt', NULL, NULL, NULL, NULL, NULL, false, false, false, false, NULL, NULL, false, NULL, NULL, false, NULL, 360451, 3, 360451, NULL, NULL);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, istanzaporttype_id, operation_id, datirichiesta_id, datirisposta_id, parametrisla_id) VALUES (360451, 'trasformazioneRichiesta.xslt', NULL, NULL, NULL, NULL, NULL, false, false, false, false, NULL, NULL, false, NULL, NULL, false, NULL, 360451, 1, 360451, NULL, NULL);

INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, istanzaporttype_id, operation_id, datirichiesta_id, datirisposta_id, parametrisla_id) VALUES (360452, 'trasformazioneRichiesta.xslt', NULL, NULL, NULL, NULL, NULL, false, false, false, false, NULL, NULL, false, NULL, NULL, false, NULL, 360452, 2, 360452, NULL, NULL);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, istanzaporttype_id, operation_id, datirichiesta_id, datirisposta_id, parametrisla_id) VALUES (360453, 'trasformazioneRichiesta.xslt', NULL, NULL, NULL, NULL, NULL, false, false, false, false, NULL, NULL, false, NULL, NULL, false, NULL, 360453, 7, 360453, NULL, NULL);
ALTER TABLE istanzaoperation ENABLE TRIGGER ALL;