ALTER TABLE medico DISABLE TRIGGER ALL;

INSERT INTO medico (id, cognome, nome, comune_residenza, codice_fiscale, codice_regionale, telefono, cellulare) VALUES (1, 'Basso', 'Giulio', 'Molfetta', 'bssgl           ', '3', NULL, NULL);
INSERT INTO medico (id, cognome, nome, comune_residenza, codice_fiscale, codice_regionale, telefono, cellulare) VALUES (2, 'Sarli', 'Michele', 'Diamante', 'srlmchl         ', '2', NULL, NULL);
INSERT INTO medico (id, cognome, nome, comune_residenza, codice_fiscale, codice_regionale, telefono, cellulare) VALUES (3, 'Chiaromonte', 'Antonio', 'Senise', 'chrmntntn       ', '1', NULL, NULL);

ALTER TABLE medico ENABLE TRIGGER ALL;



ALTER TABLE paziente DISABLE TRIGGER ALL;

INSERT INTO paziente (id, cognome, nome, codice_fiscale, data_nascita, sesso, comune_residenza,  asl_residenza, medico, codice_ssr) VALUES (7, 'Celeste', 'Rossella', 'clstrssll       ', '13/05/1975', 'F', 'Pisticci', 5, 3, NULL);
INSERT INTO paziente (id, cognome, nome, codice_fiscale, data_nascita, sesso, comune_residenza,  asl_residenza, medico, codice_ssr) VALUES (8, 'Venitti', 'Pietro', 'vnttptr         ', '05/05/1959', 'M', 'Tolve', 2, 2, NULL);
INSERT INTO paziente (id, cognome, nome, codice_fiscale, data_nascita, sesso, comune_residenza,  asl_residenza, medico, codice_ssr) VALUES (9, 'Carlotti', 'Susanna', 'crlttssnn       ', '15/12/1989', 'F', 'Potenza', 2, 3, NULL);
INSERT INTO paziente (id, cognome, nome, codice_fiscale, data_nascita, sesso, comune_residenza,  asl_residenza, medico, codice_ssr) VALUES (10, 'Sanza', 'Giuseppina', 'snzgsppn        ', '18/06/1985', 'F', 'VaglioB.', 2, 2, NULL);
INSERT INTO paziente (id, cognome, nome, codice_fiscale, data_nascita, sesso, comune_residenza,  asl_residenza, medico, codice_ssr) VALUES (11, 'Seppi', 'Marcello', 'sppmrcll        ', '22/11/1972', 'M', 'Sapri', 3, 2, NULL);
INSERT INTO paziente (id, cognome, nome, codice_fiscale, data_nascita, sesso, comune_residenza,  asl_residenza, medico, codice_ssr) VALUES (12, 'Colli', 'Andrea', 'cllndr          ', '11/08/1992', 'M', 'Ferrandina', 4, 1, NULL);

ALTER TABLE paziente ENABLE TRIGGER ALL;
