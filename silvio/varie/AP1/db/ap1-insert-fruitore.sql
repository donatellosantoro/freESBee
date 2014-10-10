ALTER TABLE datiammissione DISABLE TRIGGER ALL;

INSERT INTO datiammissione (id, numero_scheda, regime_ricovero, data_ricovero, provenienza_paziente, tipo_ricovero, motivo_ricovero, traumatismi_e_intossicazioni, struttura_ricovero, paziente_cf) VALUES (1, '08000001', 1, '13/05/2008', 1, 1, 1, 1, 1, 'rssmr');
INSERT INTO datiammissione (id, numero_scheda, regime_ricovero, data_ricovero, provenienza_paziente, tipo_ricovero, motivo_ricovero, traumatismi_e_intossicazioni, struttura_ricovero, paziente_cf) VALUES (3, '08000003', 2, '12/06/2008', 4, 3, 3, 9, 2, 'bnchnrc');
INSERT INTO datiammissione (id, numero_scheda, regime_ricovero, data_ricovero, provenienza_paziente, tipo_ricovero, motivo_ricovero, traumatismi_e_intossicazioni, struttura_ricovero, paziente_cf) VALUES (2, '08000002', 2, '24/04/2008', 2, 2, 2, 3, 1, 'vrdchr');
INSERT INTO datiammissione (id, numero_scheda, regime_ricovero, data_ricovero, provenienza_paziente, tipo_ricovero, motivo_ricovero, traumatismi_e_intossicazioni, struttura_ricovero, paziente_cf) VALUES (4, '08000004', 1, '15/03/2008', 9, 4, 3, 9, 2, 'spngl');
INSERT INTO datiammissione (id, numero_scheda, regime_ricovero, data_ricovero, provenienza_paziente, tipo_ricovero, motivo_ricovero, traumatismi_e_intossicazioni, struttura_ricovero, paziente_cf) VALUES (5, '08000005', 2, '17/005/2008', 7, 1, 1, 4, 3, 'nppcrl');

ALTER TABLE datiammissione ENABLE TRIGGER ALL;



ALTER TABLE medico DISABLE TRIGGER ALL;

INSERT INTO medico (id, cognome, nome, comune_residenza, codice_fiscale, codice_regionale, telefono, cellulare) VALUES (1, 'Basso', 'Giulio', 'Molfetta', 'bssgl           ', '3', NULL, NULL);

ALTER TABLE medico ENABLE TRIGGER ALL;



ALTER TABLE paziente DISABLE TRIGGER ALL;

INSERT INTO paziente (id, cognome, nome, codice_fiscale, data_nascita, sesso, comune_residenza, asl_residenza, medico, codice_ssr) VALUES (1, 'Rossi', 'Mario', 'rssmr', '13/05/1955', 'M', 'Barile', 2, 1, NULL);
INSERT INTO paziente (id, cognome, nome, codice_fiscale, data_nascita, sesso, comune_residenza, asl_residenza, medico, codice_ssr) VALUES (2, 'Bianchi', 'Enrico', 'bnchnrc', '02/11/1968', 'M', 'Genzano di L.', 1, 1, NULL);
INSERT INTO paziente (id, cognome, nome, codice_fiscale, data_nascita, sesso, comune_residenza, asl_residenza, medico, codice_ssr) VALUES (3, 'Verdi', 'Chiara', 'vrdchr', '12/12/1980', 'F', 'Potenza', 2, 1, NULL);
INSERT INTO paziente (id, cognome, nome, codice_fiscale, data_nascita, sesso, comune_residenza, asl_residenza, medico, codice_ssr) VALUES (4, 'Sapinia', 'Giulia', 'spngl', '28/02/1995', 'F', 'Cancellara', 2, 1, NULL);
INSERT INTO paziente (id, cognome, nome, codice_fiscale, data_nascita, sesso, comune_residenza, asl_residenza, medico, codice_ssr) VALUES (5, 'Nappi', 'Carlo', 'nppcrl', '02/11/1947', 'M', 'Lagonegro',3, 1, NULL);
INSERT INTO paziente (id, cognome, nome, codice_fiscale, data_nascita, sesso, comune_residenza, asl_residenza, medico, codice_ssr) VALUES (6, 'Felice', 'Giuseppe', 'flcgsp', '01/03/1982', 'M', 'Scanzano', 4, 1, NULL);

ALTER TABLE paziente ENABLE TRIGGER ALL;



ALTER TABLE ricetta DISABLE TRIGGER ALL;

INSERT INTO ricetta (numero, diagnosi, data, posizione_utente_ticket, importo_ticket, importo_totale, codice_struttura_erogatrice, codice_prestazione, codifica_nomenclatore, quantita, importo, paziente) VALUES (2, 'ICD9-CM 1997', '28/03/2008', '2', 54, 58, 2, 1234568, 1, 1, 58, 2);
INSERT INTO ricetta (numero, diagnosi, data, posizione_utente_ticket, importo_ticket, importo_totale, codice_struttura_erogatrice, codice_prestazione, codifica_nomenclatore, quantita, importo, paziente) VALUES (3, 'ICD9-CM 1997', '09/05/2008', '2', 102, 105, 1, 1234569, 1, 1, 105, 3);
INSERT INTO ricetta (numero, diagnosi, data, posizione_utente_ticket, importo_ticket, importo_totale, codice_struttura_erogatrice, codice_prestazione, codifica_nomenclatore, quantita, importo, paziente) VALUES (4, 'ICD9-CM 1997', '01/06/2008', '3', 15.550000000000001, 18.550000000000001, 3, 1234560, 1, 1, 18.550000000000001, 4);
INSERT INTO ricetta (numero, diagnosi, data, posizione_utente_ticket, importo_ticket, importo_totale, codice_struttura_erogatrice, codice_prestazione, codifica_nomenclatore, quantita, importo, paziente) VALUES (5, 'ICD9-CM 1997', '15/06/2008', '2', 34.200000000000003, 37.200000000000003, 3, 1234566, 1, 1, 37.200000000000003, 5);
INSERT INTO ricetta (numero, diagnosi, data, posizione_utente_ticket, importo_ticket, importo_totale, codice_struttura_erogatrice, codice_prestazione, codifica_nomenclatore, quantita, importo, paziente) VALUES (6, 'ICD9-CM 1997', '12/02/2008', '1', 78, 98.030000000000001, 1, 1234567, 1, 1, 98.030000000000001, 1);

ALTER TABLE ricetta ENABLE TRIGGER ALL;
