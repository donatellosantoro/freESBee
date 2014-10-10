BEGIN TRANSACTION;
SET CONSTRAINTS ALL DEFERRED;

DELETE FROM accordoservizio;
INSERT INTO accordoservizio (id, descrizione, nome, oraregistrazione, privato, profilocollaborazione, profiloegov_id) VALUES (131072, '', 'ASQualificazionePDD_Erogatore', '2009-01-19 16:57:39.544', false, 'EGOV_IT_ServizioSincrono', 131072);
INSERT INTO accordoservizio (id, descrizione, nome, oraregistrazione, privato, profilocollaborazione, profiloegov_id) VALUES (327680, '', 'ASQualificazionePDD_Fruitore', '2009-01-19 16:58:11.134', false, 'EGOV_IT_ServizioSincrono', 327680);

DELETE FROM azione;
INSERT INTO azione (id, nome, accordoservizio_id, profiloegov_id, profilocollaborazione) VALUES (1, 'start', 131072, NULL, 'EGOV_IT_ServizioSincrono');
INSERT INTO azione (id, nome, accordoservizio_id, profiloegov_id, profilocollaborazione) VALUES (2, 'end', 131072, NULL, 'EGOV_IT_ServizioSincrono');
INSERT INTO azione (id, nome, accordoservizio_id, profiloegov_id, profilocollaborazione) VALUES (3, 'testOneWay', 131072, NULL, 'EGOV_IT_MessaggioSingoloOneWay');
INSERT INTO azione (id, nome, accordoservizio_id, profiloegov_id, profilocollaborazione) VALUES (4, 'testSincrono', 131072, NULL, 'EGOV_IT_ServizioSincrono');
INSERT INTO azione (id, nome, accordoservizio_id, profiloegov_id, profilocollaborazione) VALUES (5, 'testAsincronoAsimmetrico', 131072, NULL, 'EGOV_IT_ServizioAsincronoAsimmetrico');
INSERT INTO azione (id, nome, accordoservizio_id, profiloegov_id, profilocollaborazione) VALUES (6, 'checkTestAsincronoAsimmetrico', 131072, NULL, 'EGOV_IT_ServizioAsincronoAsimmetrico');
INSERT INTO azione (id, nome, accordoservizio_id, profiloegov_id, profilocollaborazione) VALUES (7, 'testAsincronoSimmetrico', 131072, NULL, 'EGOV_IT_ServizioAsincronoSimmetrico');
INSERT INTO azione (id, nome, accordoservizio_id, profiloegov_id, profilocollaborazione) VALUES (8, 'riceviRispostaTestAsincronoSimmetrico', 131072, NULL, 'EGOV_IT_ServizioAsincronoSimmetrico');
INSERT INTO azione (id, nome, accordoservizio_id, profiloegov_id, profilocollaborazione) VALUES (327680, 'start', 327680, NULL, 'EGOV_IT_ServizioSincrono');
INSERT INTO azione (id, nome, accordoservizio_id, profiloegov_id, profilocollaborazione) VALUES (327681, 'end', 327680, NULL, 'EGOV_IT_ServizioSincrono');
INSERT INTO azione (id, nome, accordoservizio_id, profiloegov_id, profilocollaborazione) VALUES (327682, 'testOneWay', 327680, NULL, 'EGOV_IT_MessaggioSingoloOneWay');
INSERT INTO azione (id, nome, accordoservizio_id, profiloegov_id, profilocollaborazione) VALUES (327683, 'testSincrono', 327680, NULL, 'EGOV_IT_ServizioSincrono');
INSERT INTO azione (id, nome, accordoservizio_id, profiloegov_id, profilocollaborazione) VALUES (327684, 'testAsincronoAsimmetrico', 327680, NULL, 'EGOV_IT_ServizioAsincronoAsimmetrico');
INSERT INTO azione (id, nome, accordoservizio_id, profiloegov_id, profilocollaborazione) VALUES (327685, 'checkTestAsincronoAsimmetrico', 327680, NULL, 'EGOV_IT_ServizioAsincronoAsimmetrico');
INSERT INTO azione (id, nome, accordoservizio_id, profiloegov_id, profilocollaborazione) VALUES (327686, 'testAsincronoSimmetrico', 327680, NULL, 'EGOV_IT_ServizioAsincronoSimmetrico');
INSERT INTO azione (id, nome, accordoservizio_id, profiloegov_id, profilocollaborazione) VALUES (327687, 'riceviRispostaTestAsincronoSimmetrico', 327680, NULL, 'EGOV_IT_ServizioAsincronoSimmetrico');

DELETE FROM configurazione;
INSERT INTO configurazione (id, nica, connettoreregistroservizi, indirizzoportaapplicativa, indirizzoportadelegata, inviaanica, registrofreesbee, tempo, soggettoerogatoreregistroservizi_id, soggettoerogatorenica_id) VALUES (1, false, '', 'http://localhost:8196/PA/', 'http://localhost:8192/PD/', false, true, 'EGOV_IT_SPC', NULL, NULL);

DELETE FROM hibernate_sequences;
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Sincronizzazione', 10);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Soggetto', 10);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('ServizioApplicativo', 15);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('ProfiloEGov', 11);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('AccordoServizio', 11);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Servizio', 12);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Azione', 11);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('PortaApplicativa', 18);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('PortaDelegata', 12);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Messaggio', 33);

DELETE FROM portaapplicativa;
INSERT INTO portaapplicativa (id, descrizione, nome, stringaazione, stringaerogatore, stringaservizio, stringatipoerogatore, stringatiposervizio, servizio_id, servizioapplicativo_id, azione_id) VALUES (163840, '', 'PA_QualificazionePDD_start', NULL, NULL, NULL, NULL, NULL, 131072, 131072, 1);
INSERT INTO portaapplicativa (id, descrizione, nome, stringaazione, stringaerogatore, stringaservizio, stringatipoerogatore, stringatiposervizio, servizio_id, servizioapplicativo_id, azione_id) VALUES (163841, '', 'PA_QualificazionePDD_sincrono', NULL, NULL, NULL, NULL, NULL, 131072, 131073, 4);
INSERT INTO portaapplicativa (id, descrizione, nome, stringaazione, stringaerogatore, stringaservizio, stringatipoerogatore, stringatiposervizio, servizio_id, servizioapplicativo_id, azione_id) VALUES (163843, '', 'PA_QualificazionePDD_asincrono', NULL, NULL, NULL, NULL, NULL, 131072, 131074, 7);
INSERT INTO portaapplicativa (id, descrizione, nome, stringaazione, stringaerogatore, stringaservizio, stringatipoerogatore, stringatiposervizio, servizio_id, servizioapplicativo_id, azione_id) VALUES (360448, '', 'PA_QualificazionePDDCorrelato_riceviRisposta', NULL, NULL, NULL, NULL, NULL, 327680, 327680, 8);
INSERT INTO portaapplicativa (id, descrizione, nome, stringaazione, stringaerogatore, stringaservizio, stringatipoerogatore, stringatiposervizio, servizio_id, servizioapplicativo_id, azione_id) VALUES (393216, '', 'PA_QualificazionePDD_end', NULL, NULL, NULL, NULL, NULL, 131072, 360448, 2);
INSERT INTO portaapplicativa (id, descrizione, nome, stringaazione, stringaerogatore, stringaservizio, stringatipoerogatore, stringatiposervizio, servizio_id, servizioapplicativo_id, azione_id) VALUES (425984, '', 'PA_QualificazionePDD_asincrono_asimmetrico', NULL, NULL, NULL, NULL, NULL, 131072, 393216, 5);
INSERT INTO portaapplicativa (id, descrizione, nome, stringaazione, stringaerogatore, stringaservizio, stringatipoerogatore, stringatiposervizio, servizio_id, servizioapplicativo_id, azione_id) VALUES (458752, '', 'PA_QualificazionePDD_check_asincrono_asimmetrico', NULL, NULL, NULL, NULL, NULL, 131072, 425984, 6);
INSERT INTO portaapplicativa (id, descrizione, nome, stringaazione, stringaerogatore, stringaservizio, stringatipoerogatore, stringatiposervizio, servizio_id, servizioapplicativo_id, azione_id) VALUES (163842, '', 'PA_QualificazionePDD_one_way', NULL, NULL, NULL, NULL, NULL, 131072, 458752, 3);

DELETE FROM portadelegata;
INSERT INTO portadelegata (id, descrizione, fruitorequerystring, nome, stringaazione, stringaerogatore, stringafruitore, stringaservizio, stringatipoerogatore, stringatipofruitore, stringatiposervizio, soggettofruitore_id, servizio_id, azione_id) VALUES (360448, '', true, 'PD_QualificazionePDD', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO portadelegata (id, descrizione, fruitorequerystring, nome, stringaazione, stringaerogatore, stringafruitore, stringaservizio, stringatipoerogatore, stringatipofruitore, stringatiposervizio, soggettofruitore_id, servizio_id, azione_id) VALUES (360449, '', true, 'PD_QualificazionePDD_Correlato', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

DELETE FROM profiloegov;
INSERT INTO profiloegov (id, confermaricezione, consegnainordine, gestioneduplicati, idcollaborazione, scadenza) VALUES (131072, false, false, true, false, NULL);
INSERT INTO profiloegov (id, confermaricezione, consegnainordine, gestioneduplicati, idcollaborazione, scadenza) VALUES (327680, false, false, true, false, NULL);

DELETE FROM servizio;
INSERT INTO servizio (id, correlato, nome, oraregistrazione, privato, tipo, erogatore_id, accordoservizio_id, azioni) VALUES (131072, false, 'QualificazionePDD', '2009-01-08 15:37:38.002', false, 'SPC', 163841, 131072, 'start, end, testOneWay, testSincrono, testAsincronoAsimmetrico, checkTestAsincronoAsimmetrico, testAsincronoSimmetrico');
INSERT INTO servizio (id, correlato, nome, oraregistrazione, privato, tipo, erogatore_id, accordoservizio_id, azioni) VALUES (327680, true, 'QualificazionePDDCorrelato', '2009-01-14 13:00:55.679', false, 'SPC', 163840, 131072, 'riceviRispostaTestAsincronoSimmetrico');
INSERT INTO servizio (id, correlato, nome, oraregistrazione, privato, tipo, erogatore_id, accordoservizio_id, azioni) VALUES (360448, false, 'QualificazionePDD', '2009-01-19 16:11:12.752', false, 'SPC', 163840, 327680, 'start, end, testOneWay, testSincrono, testAsincronoAsimmetrico, checkTestAsincronoAsimmetrico, testAsincronoSimmetrico');
INSERT INTO servizio (id, correlato, nome, oraregistrazione, privato, tipo, erogatore_id, accordoservizio_id, azioni) VALUES (360449, true, 'QualificazionePDDCorrelato', '2009-01-19 16:12:01.222', false, 'SPC', 163841, 327680, 'riceviRispostaTestAsincronoSimmetrico');

DELETE FROM servizio_soggetto;
INSERT INTO servizio_soggetto (listaservizifruitore_id, fruitori_id) VALUES (131072, 163840);
INSERT INTO servizio_soggetto (listaservizifruitore_id, fruitori_id) VALUES (327680, 163841);
INSERT INTO servizio_soggetto (listaservizifruitore_id, fruitori_id) VALUES (360448, 163841);
INSERT INTO servizio_soggetto (listaservizifruitore_id, fruitori_id) VALUES (360449, 163840);

DELETE FROM servizioapplicativo;
INSERT INTO servizioapplicativo (id, connettore, descrizione, nome) VALUES (131072, 'http://localhost:9192/EROGATORE/QualificazionePDD_1/', '', 'SILVIO-Qualifica_start');
INSERT INTO servizioapplicativo (id, connettore, descrizione, nome) VALUES (131073, 'http://localhost:9192/EROGATORE/QualificazionePDD_32768/', '', 'SILVIO-Qualifica_sincrono');
INSERT INTO servizioapplicativo (id, connettore, descrizione, nome) VALUES (131074, 'http://localhost:9192/EROGATORE/QualificazionePDD_65536/', '', 'SILVIO-Qualifica_asincrono');
INSERT INTO servizioapplicativo (id, connettore, descrizione, nome) VALUES (327680, 'http://localhost:9192/EROGATORE/QualificazionePDDCorrelato_360448/', '', 'SILVIO-Qualifica_RiceviRisposta');
INSERT INTO servizioapplicativo (id, connettore, descrizione, nome) VALUES (360448, 'http://localhost:9192/EROGATORE/QualificazionePDD_196608/', '', 'SILVIO-Qualifica_end');
INSERT INTO servizioapplicativo (id, connettore, descrizione, nome) VALUES (393216, 'http://localhost:9192/EROGATORE/QualificazionePDD_229376/', '', 'SILVIO-Qualifica_asincrono_asimmetrico');
INSERT INTO servizioapplicativo (id, connettore, descrizione, nome) VALUES (425984, 'http://localhost:9192/EROGATORE/QualificazionePDD_262145/', '', 'SILVIO-Qualifica_check_asincrono_asimmetrico');
INSERT INTO servizioapplicativo (id, connettore, descrizione, nome) VALUES (458752, 'http://localhost:9192/EROGATORE/QualificazionePDD_163840/', '', 'SILVIO-Qualifica_one_way');

DELETE FROM soggetto;
INSERT INTO soggetto (id, descrizione, nome, oraregistrazione, portadominio, tipo) VALUES (163841, '', 'PAGenerica', '2009-01-08 15:15:23.28', 'http://localhost:18196/PA/', 'SPC');
INSERT INTO soggetto (id, descrizione, nome, oraregistrazione, portadominio, tipo) VALUES (163840, '', 'CNIPA', '2009-01-19 16:18:17.119', 'http://localhost:18196/PA/', 'SPC');

DELETE FROM utente;
INSERT INTO utente VALUES (1 , 'admin' , '21232f297a57a5a743894a0e4a801fc3', 'amministratore');

COMMIT TRANSACTION;