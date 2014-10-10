BEGIN TRANSACTION;
SET CONSTRAINTS ALL DEFERRED;

INSERT INTO accordoservizio (id, descrizione, nome, oraregistrazione, privato, profilocollaborazione, profiloegov_id) VALUES (98304, '', 'ASLoopback', '2008-03-27 12:00:00', false, 'EGOV_IT_ServizioSincrono', 98304);
INSERT INTO configurazione (id, nica, connettoreregistroservizi, indirizzoportaapplicativa, indirizzoportadelegata, inviaanica, registrofreesbee ,tempo, soggettoerogatorenica_id, soggettoerogatoreregistroservizi_id) VALUES (1, false, '', 'http://0.0.0.0:8196/PA/', 'http://0.0.0.0:8192/PD/', false, true, 'EGOV_IT_SPC', NULL, NULL);
--INSERT INTO sincronizzazione (id, indirizzo, nome, freesbee) VALUES (1, 'http://icarlab.unibas.it/nica-reg/Inquiry', 'SPC', false);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Configurazione', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Soggetto', 5);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('ProfiloEGov', 4);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('AccordoServizio', 4);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Servizio', 4);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Azione', 4);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('PortaDelegata', 8);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('ServizioApplicativo', 4);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Messaggio', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('PortaApplicativa', 5);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Sincronizzazione', 2);
INSERT INTO azione (id, nome, accordoservizio_id, profiloegov_id, profilocollaborazione) VALUES (1, 'echoService', 98304, NULL, 'EGOV_IT_ServizioSincrono');
INSERT INTO portaapplicativa (id, descrizione, nome, servizio_id, azione_id, servizioapplicativo_id) VALUES (131072, '', 'PALoopback', 98304, 1, 65536);
INSERT INTO portadelegata (id, descrizione, nome, soggettofruitore_id, azione_id, servizio_id,fruitoreQueryString) VALUES (229376, '', 'EchoService', 131072, 1, 98304,false);
INSERT INTO profiloegov (id, confermaricezione, consegnainordine, gestioneduplicati, idcollaborazione, scadenza) VALUES (98304, false, false, true, false, NULL);
INSERT INTO servizio (id, correlato, nome, oraregistrazione, privato, tipo, erogatore_id, accordoservizio_id) VALUES (98304, false, 'TestServizioLoopback', '2008-03-27 12:00:00', false, 'SPC', 131073, 98304);
INSERT INTO servizio_soggetto (listaservizifruitore_id, fruitori_id) VALUES (98304, 131072);
INSERT INTO servizioapplicativo (id, connettore, descrizione, nome) VALUES (65536, 'http://localhost:8191/ws/echoService', '', 'EchoService');
INSERT INTO soggetto (id, descrizione, nome, oraregistrazione, portadominio, tipo) VALUES (131072, '', 'FruitoreLoopback', '2008-03-27 12:00:00', 'http://localhost:8196/PA/', 'SPC');
INSERT INTO soggetto (id, descrizione, nome, oraregistrazione, portadominio, tipo) VALUES (131073, '', 'ErogatoreLoopback', '2008-03-27 12:00:00', 'http://localhost:8196/PA/', 'SPC');
INSERT INTO utente VALUES (1 , 'admin' , '21232f297a57a5a743894a0e4a801fc3', 'amministratore');
INSERT INTO utente VALUES (2 , 'user' , 'ee11cbb19052e40b07aac0ca060c23ee', 'user');

COMMIT TRANSACTION;

