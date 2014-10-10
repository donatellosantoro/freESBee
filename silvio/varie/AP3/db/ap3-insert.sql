
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;


SELECT pg_catalog.setval('amministrazione_id_seq', 1, false);



SELECT pg_catalog.setval('area_organizzativa_omogenea_id_seq', 1, false);



SELECT pg_catalog.setval('classifica_id_seq', 1, false);



SELECT pg_catalog.setval('"documentoFascicolo_id_seq"', 1, false);



SELECT pg_catalog.setval('documento_id_seq', 8, true);



SELECT pg_catalog.setval('fascicolo_id_seq', 1, false);



SELECT pg_catalog.setval('localita_id_seq', 1, false);



SELECT pg_catalog.setval('persona_id_seq', 1, false);



SELECT pg_catalog.setval('responsabiletrattamento_id_seq', 1, false);



SELECT pg_catalog.setval('serviziotelematico_id_seq_amministrazione', 1, false);



SELECT pg_catalog.setval('serviziotelematico_id_seq_areaorganizzativaomogenea', 1, false);



SELECT pg_catalog.setval('serviziotelematico_id_seq_unitaorganizzativa', 1, false);



SELECT pg_catalog.setval('unitaorganizzativa_id_seq', 1, false);



ALTER TABLE amministrazione DISABLE TRIGGER ALL;

INSERT INTO amministrazione (id, codice, nome, indirizzo, email, dominio_pec, sito_istituzionale, responsabile) VALUES (1, '00000001', 'Amministrazione 01', 1, 'amministrazione01@pa.it', 'pa.ca.it', 'http://www.amministrazione01.pa.it', 1);
INSERT INTO amministrazione (id, codice, nome, indirizzo, email, dominio_pec, sito_istituzionale, responsabile) VALUES (2, '00000002', 'Amministrazione 02', 2, 'amministrazione02@pa.it', 'pa.ca.it', 'http://www.amministrazione02.pa.it', 2);
INSERT INTO amministrazione (id, codice, nome, indirizzo, email, dominio_pec, sito_istituzionale, responsabile) VALUES (3, '00000003', 'Amministrazione 03', 3, 'amministrazione03@pa.it', 'pa.ca.it', 'http://www.amministrazione03.pa.it', 3);
INSERT INTO amministrazione (id, codice, nome, indirizzo, email, dominio_pec, sito_istituzionale, responsabile) VALUES (4, '00000004', 'Amministrazione 04', 6, 'amministrazione04@pa.it', 'pa.ca.it', 'http://www.amministrazione04.pa.it', 6);
INSERT INTO amministrazione (id, codice, nome, indirizzo, email, dominio_pec, sito_istituzionale, responsabile) VALUES (5, '00000005', 'Amministrazione 05', 9, 'amministrazione05@pa.it', 'pa.ca.it', 'http://www.amministrazione05.pa.it', 9);


ALTER TABLE amministrazione ENABLE TRIGGER ALL;


ALTER TABLE areaorganizzativaomogenea DISABLE TRIGGER ALL;

INSERT INTO areaorganizzativaomogenea (id, codice_aoo, nome, responsabile, data_istituzione, data_soppressione, certification_autority, email, telefono, fax, indirizzo, id_amministrazione) VALUES (1, '00000001', 'Area Organizzativa Omogenea 01', 1, '2008-05-13', NULL, 'http://www.pa.ca.it', 'aoo01@pa.it', '097132981', '097132982', 1, 1);
INSERT INTO areaorganizzativaomogenea (id, codice_aoo, nome, responsabile, data_istituzione, data_soppressione, certification_autority, email, telefono, fax, indirizzo, id_amministrazione) VALUES (2, '00000002', 'Area Organizzativa Omogenea 02', 2, '2008-09-04', NULL, 'http://www.pa.ca.it', 'aoo02@pa.it', '097145218', '097145219', 2, 2);
INSERT INTO areaorganizzativaomogenea (id, codice_aoo, nome, responsabile, data_istituzione, data_soppressione, certification_autority, email, telefono, fax, indirizzo, id_amministrazione) VALUES (3, '00000003', 'Area Organizzativa Omogenea 03', 3, '2008-01-02', NULL, 'http://www.pa.ca.it
', 'aoo03@pa.it', '0971671256', '0971671257', 3, 3);
INSERT INTO areaorganizzativaomogenea (id, codice_aoo, nome, responsabile, data_istituzione, data_soppressione, certification_autority, email, telefono, fax, indirizzo, id_amministrazione) VALUES (4, '00000004', 'Area Organizzativa Omogenea 04', 6, '2008-01-15', NULL, 'http://www.pa.ca.it

', 'aoo04@pa.it', '075431278', '075431279', 6, 4);
INSERT INTO areaorganizzativaomogenea (id, codice_aoo, nome, responsabile, data_istituzione, data_soppressione, certification_autority, email, telefono, fax, indirizzo, id_amministrazione) VALUES (5, '00000005', 'Area Organizzativa Omogenea 05', 9, '2008-01-21', NULL, 'http://www.pa.ca.it


', 'aoo05@pa.it', '051671290', '051671291', 9, 5);


ALTER TABLE areaorganizzativaomogenea ENABLE TRIGGER ALL;


ALTER TABLE classifica DISABLE TRIGGER ALL;

INSERT INTO classifica (id, livello_classifica, codice_classifica, descrizione_classifica) VALUES (1, 'livelloClassifica_01', 'codiceClassifica_01', 'descrizioneClassifica_01');
INSERT INTO classifica (id, livello_classifica, codice_classifica, descrizione_classifica) VALUES (2, 'livelloClassifica_02', 'codiceClassifica_02', 'descrizioneClassifica_02');
INSERT INTO classifica (id, livello_classifica, codice_classifica, descrizione_classifica) VALUES (3, 'livelloClassifica_03', 'codiceClassifica_03', 'descrizioneClassifica_03');
INSERT INTO classifica (id, livello_classifica, codice_classifica, descrizione_classifica) VALUES (4, 'livelloClassifica_04', 'codiceClassifica_04', 'descrizioneClassifica_04');
INSERT INTO classifica (id, livello_classifica, codice_classifica, descrizione_classifica) VALUES (5, 'livelloClassifica_05', 'codiceClassifica_05', 'descrizioneClassifica_05');


ALTER TABLE classifica ENABLE TRIGGER ALL;


ALTER TABLE documento DISABLE TRIGGER ALL;

INSERT INTO documento (id, codice_documento, documento, nome_documento, id_documentofascicolo) VALUES (1, '00000001_codice00000001', 'contenuto documento 00000001 presente nell''amministrazione 00000001', 'documento00000001', 1);
INSERT INTO documento (id, codice_documento, documento, nome_documento, id_documentofascicolo) VALUES (2, '00000001_codice00000002
', 'contenuto documento 00000002 presente nell''amministrazione 00000001
', 'documento00000002
', 1);
INSERT INTO documento (id, codice_documento, documento, nome_documento, id_documentofascicolo) VALUES (3, '00000001_codice00000003', 'contenuto documento 00000003 presente nell''amministrazione 00000001', 'documento00000003

', 1);
INSERT INTO documento (id, codice_documento, documento, nome_documento, id_documentofascicolo) VALUES (4, '00000002_codice00000001', 'contenuto documento 00000001 presente nell''amministrazione 00000002', 'documento00000001', 2);
INSERT INTO documento (id, codice_documento, documento, nome_documento, id_documentofascicolo) VALUES (5, '00000002_codice00000002
', 'contenuto documento 00000002 presente nell''amministrazione 00000002', 'documento00000002', 2);
INSERT INTO documento (id, codice_documento, documento, nome_documento, id_documentofascicolo) VALUES (6, '00000003_codice00000001', 'contenuto documento 00000001 presente nell''amministrazione 00000003', 'documento00000001', 3);
INSERT INTO documento (id, codice_documento, documento, nome_documento, id_documentofascicolo) VALUES (7, '00000004_codice00000001', 'contenuto documento 00000001 presente nell''amministrazione 00000004', 'documento00000001
', 4);
INSERT INTO documento (id, codice_documento, documento, nome_documento, id_documentofascicolo) VALUES (8, '00000005_codice00000001', 'contenuto documento 00000001 presente nell''amministrazione 00000005', 'documento00000001', 5);


ALTER TABLE documento ENABLE TRIGGER ALL;


ALTER TABLE documentofascicolo DISABLE TRIGGER ALL;

INSERT INTO documentofascicolo (id, descrizione_corrispondente, data_registrazione, estremiprotocollo_anno, estremiprotocollo_numero, arrivopartenza, codice_registro, descrizione_registro, id_amministrazione, id_areaorganizzativaomogenea, id_fascicolo) VALUES (1, 'descrizioneCorrispondente_01', '2007-10-11', '2007', 'estremiProtocollo_00000001', 'arrivoPartenza_01', 'codiceRegistro_01', 'descrizioneRegistro_01', 1, 1, 1);
INSERT INTO documentofascicolo (id, descrizione_corrispondente, data_registrazione, estremiprotocollo_anno, estremiprotocollo_numero, arrivopartenza, codice_registro, descrizione_registro, id_amministrazione, id_areaorganizzativaomogenea, id_fascicolo) VALUES (2, 'descrizioneCorrispondente_02', '2007-11-13', '2007', 'estremiProtocollo_00000002', 'arrivoPartenza_02', 'codiceRegistro_02', 'descrizioneRegistro_02', 2, 2, 2);
INSERT INTO documentofascicolo (id, descrizione_corrispondente, data_registrazione, estremiprotocollo_anno, estremiprotocollo_numero, arrivopartenza, codice_registro, descrizione_registro, id_amministrazione, id_areaorganizzativaomogenea, id_fascicolo) VALUES (3, 'descrizioneCorrispondente_03', '2007-12-23', '2007', 'estremiProtocollo_00000003', 'arrivoPartenza_03', 'codiceRegistro_03', 'descrizioneRegistro_03', 3, 3, 3);
INSERT INTO documentofascicolo (id, descrizione_corrispondente, data_registrazione, estremiprotocollo_anno, estremiprotocollo_numero, arrivopartenza, codice_registro, descrizione_registro, id_amministrazione, id_areaorganizzativaomogenea, id_fascicolo) VALUES (4, 'descrizioneCorrispondente_04', '2008-05-31', '2008', 'estremiProtocollo_00000004', 'arrivoPartenza_04', 'codiceRegistro_04', 'descrizioneRegistro_04', 4, 4, 4);
INSERT INTO documentofascicolo (id, descrizione_corrispondente, data_registrazione, estremiprotocollo_anno, estremiprotocollo_numero, arrivopartenza, codice_registro, descrizione_registro, id_amministrazione, id_areaorganizzativaomogenea, id_fascicolo) VALUES (5, 'descrizioneCorrispondente_05', '2008-06-03', '2008', 'estremiProtocollo_00000005', 'arrivoPartenza_05', 'codiceRegistro_05', 'descrizioneRegistro_05', 5, 5, 5);


ALTER TABLE documentofascicolo ENABLE TRIGGER ALL;


ALTER TABLE fascicolo DISABLE TRIGGER ALL;

INSERT INTO fascicolo (id, codice_ufficio, descrizione_ufficio, anno, numero, subnumero, id_classifica) VALUES (1, 'codiceUfficio_01', 'descrizioneUfficio_01', '2007', '00000001', '001', 1);
INSERT INTO fascicolo (id, codice_ufficio, descrizione_ufficio, anno, numero, subnumero, id_classifica) VALUES (2, 'codiceUfficio_02', 'descrizioneUfficio_02', '2007', '00000002', '002', 2);
INSERT INTO fascicolo (id, codice_ufficio, descrizione_ufficio, anno, numero, subnumero, id_classifica) VALUES (3, 'codiceUfficio_03', 'descrizioneUfficio_03', '2007', '00000003', '003', 3);
INSERT INTO fascicolo (id, codice_ufficio, descrizione_ufficio, anno, numero, subnumero, id_classifica) VALUES (4, 'codiceUfficio_04', 'descrizioneUfficio_04', '2007', '00000004', '004', 4);
INSERT INTO fascicolo (id, codice_ufficio, descrizione_ufficio, anno, numero, subnumero, id_classifica) VALUES (5, 'codiceUfficio_05', 'descrizioneUfficio_05', '2007', '00000005', '005', 5);


ALTER TABLE fascicolo ENABLE TRIGGER ALL;


ALTER TABLE localita DISABLE TRIGGER ALL;

INSERT INTO localita (id, via, numero_civico, cap, citta, sigla_provincia, regione) VALUES (1, 'Addone', '34', '85100', 'Potenza', 'PZ', 'BAS');
INSERT INTO localita (id, via, numero_civico, cap, citta, sigla_provincia, regione) VALUES (2, 'Anzio', '58', '85100', 'Potenza', 'PZ', 'BAS');
INSERT INTO localita (id, via, numero_civico, cap, citta, sigla_provincia, regione) VALUES (3, 'Bertazzoni', '2', '85100', 'Potenza', 'PZ', 'BAS');
INSERT INTO localita (id, via, numero_civico, cap, citta, sigla_provincia, regione) VALUES (4, 'Angilla Vecchia', '91', '85100', 'Potenza', 'PZ', 'BAS');
INSERT INTO localita (id, via, numero_civico, cap, citta, sigla_provincia, regione) VALUES (5, 'Isca del Pioppo', '15', '85100', 'Potenza', 'PZ', 'BAS');
INSERT INTO localita (id, via, numero_civico, cap, citta, sigla_provincia, regione) VALUES (6, 'Ghandi', '21', '06010', 'Perugia', 'PG', 'UMB');
INSERT INTO localita (id, via, numero_civico, cap, citta, sigla_provincia, regione) VALUES (7, 'Campo di Marte', '18', '06010', 'Perugia', 'PG', 'UMB');
INSERT INTO localita (id, via, numero_civico, cap, citta, sigla_provincia, regione) VALUES (8, 'Giovanni Vicini', '39', '40100', 'Bologna', 'BO', 'EMR');
INSERT INTO localita (id, via, numero_civico, cap, citta, sigla_provincia, regione) VALUES (9, 'Paolo Fabbri', '17', '40100', 'Bologna', 'BO', 'EMR');


ALTER TABLE localita ENABLE TRIGGER ALL;


ALTER TABLE persona DISABLE TRIGGER ALL;

INSERT INTO persona (nome, cognome, titolo, email, telefono, id) VALUES ('Giuseppe', 'Finizi', 'Dottore', 'giuseppe.finizi@gmail.com', '0971509823', 1);
INSERT INTO persona (nome, cognome, titolo, email, telefono, id) VALUES ('Antonio', 'Geraldi', 'Avvocato', 'geranto@interfree.it', '097156124', 2);
INSERT INTO persona (nome, cognome, titolo, email, telefono, id) VALUES ('Nicola', 'De Filippo', 'Medico', 'defil@yahoo.com', '0971189238', 3);
INSERT INTO persona (nome, cognome, titolo, email, telefono, id) VALUES ('Pietro', 'Sassuolo', 'Ingegnere', 'p.sassuolo@tiscali.it', '0971231452', 4);
INSERT INTO persona (nome, cognome, titolo, email, telefono, id) VALUES ('Fabrizio', 'Giuolo', 'Ingegnere', 'fagiuolo@alice.it', '097123785', 5);
INSERT INTO persona (nome, cognome, titolo, email, telefono, id) VALUES ('Michele', 'Ansone', 'Medico', 'ansone@gmail.com', '075124324', 6);
INSERT INTO persona (nome, cognome, titolo, email, telefono, id) VALUES ('Carmelo', 'Squinto', 'Avvocato', 'squinto@alice.it', '075435612', 7);
INSERT INTO persona (nome, cognome, titolo, email, telefono, id) VALUES ('Carlo', 'Porento', 'Architetto', 'c.porento@alice.it', '051431265', 8);
INSERT INTO persona (nome, cognome, titolo, email, telefono, id) VALUES ('Alessandro', 'Sfanello', 'Dottore', 'sfalessio@gmail.com', '051235416', 9);


ALTER TABLE persona ENABLE TRIGGER ALL;


ALTER TABLE responsabiletrattamento DISABLE TRIGGER ALL;

INSERT INTO responsabiletrattamento (id, codice_responsabiletrattamento, descrizioneresponsabiletrattamento, id_documentofascicolo) VALUES (1, 'responsabileTrattamento_00000001', 'descrizioneResponsabileTrattamento_00000001', 1);
INSERT INTO responsabiletrattamento (id, codice_responsabiletrattamento, descrizioneresponsabiletrattamento, id_documentofascicolo) VALUES (2, 'responsabileTrattamento_00000002', 'descrizioneResponsabileTrattamento_00000002', 2);
INSERT INTO responsabiletrattamento (id, codice_responsabiletrattamento, descrizioneresponsabiletrattamento, id_documentofascicolo) VALUES (3, 'responsabileTrattamento_00000003', 'descrizioneResponsabileTrattamento_00000003', 3);
INSERT INTO responsabiletrattamento (id, codice_responsabiletrattamento, descrizioneresponsabiletrattamento, id_documentofascicolo) VALUES (4, 'responsabileTrattamento_00000004', 'descrizioneResponsabileTrattamento_00000004', 4);
INSERT INTO responsabiletrattamento (id, codice_responsabiletrattamento, descrizioneresponsabiletrattamento, id_documentofascicolo) VALUES (5, 'responsabileTrattamento_00000005', 'descrizioneResponsabileTrattamento_00000005', 5);


ALTER TABLE responsabiletrattamento ENABLE TRIGGER ALL;


ALTER TABLE serviziotelematico_amministrazione DISABLE TRIGGER ALL;

INSERT INTO serviziotelematico_amministrazione (id, nome, descrizione, telefono, url, email, codice_ufficio, id_amministrazione) VALUES (1, 'Servizio Telematico 01', 'Servizio Telematico 01
', '097132876', 'http://www.serviziotelematico01.pa.it', 'serviziotelematico01@pa.it', '00000001', 1);
INSERT INTO serviziotelematico_amministrazione (id, nome, descrizione, telefono, url, email, codice_ufficio, id_amministrazione) VALUES (2, 'Servizio Telematico 02', 'Servizio Telematico 02', '097132896', 'http://www.serviziotelematico02.pa.it', 'serviziotelematico02@pa.it', '00000002', 2);
INSERT INTO serviziotelematico_amministrazione (id, nome, descrizione, telefono, url, email, codice_ufficio, id_amministrazione) VALUES (3, 'Servizio Telematico 03', 'Servizio Telematico 03', '097132821', 'http://www.serviziotelematico03.pa.it', 'serviziotelematico03@pa.it', '00000003', 3);
INSERT INTO serviziotelematico_amministrazione (id, nome, descrizione, telefono, url, email, codice_ufficio, id_amministrazione) VALUES (4, 'Servizio Telematico 04', 'Servizio Telematico 04', '075219837', 'http://www.serviziotelematico04.pa.it', 'serviziotelematico04@pa.it', '00000004', 4);
INSERT INTO serviziotelematico_amministrazione (id, nome, descrizione, telefono, url, email, codice_ufficio, id_amministrazione) VALUES (5, 'Servizio Telematico 05', 'Servizio Telematico 05', '051983267', 'http://www.serviziotelematico05.pa.it', 'serviziotelematico05@pa.it', '00000005', 5);


ALTER TABLE serviziotelematico_amministrazione ENABLE TRIGGER ALL;


ALTER TABLE serviziotelematico_areaorganizzativaomogenea DISABLE TRIGGER ALL;

INSERT INTO serviziotelematico_areaorganizzativaomogenea (id, nome, descrizione, telefono, url, email, codice_ufficio, id_areaorganizzativaomogenea) VALUES (1, 'Servizio Telematico 01', 'Servizio Telematico 01', '097132876', 'http://www.serviziotelematico01.pa.it', 'serviziotelematico01@pa.it', '00000001', 1);
INSERT INTO serviziotelematico_areaorganizzativaomogenea (id, nome, descrizione, telefono, url, email, codice_ufficio, id_areaorganizzativaomogenea) VALUES (2, 'Servizio Telematico 02', 'Servizio Telematico 02', '097132896', 'http://www.serviziotelematico02.pa.it', 'serviziotelematico02@pa.it', '00000002', 2);
INSERT INTO serviziotelematico_areaorganizzativaomogenea (id, nome, descrizione, telefono, url, email, codice_ufficio, id_areaorganizzativaomogenea) VALUES (3, 'Servizio Telematico 03', 'Servizio Telematico 03', '097132821', 'http://www.serviziotelematico03.pa.it', 'serviziotelematico03@pa.it', '00000003', 3);
INSERT INTO serviziotelematico_areaorganizzativaomogenea (id, nome, descrizione, telefono, url, email, codice_ufficio, id_areaorganizzativaomogenea) VALUES (4, 'Servizio Telematico 04', 'Servizio Telematico 04', '075219837', 'http://www.serviziotelematico04.pa.it', 'serviziotelematico04@pa.it', '00000004', 4);
INSERT INTO serviziotelematico_areaorganizzativaomogenea (id, nome, descrizione, telefono, url, email, codice_ufficio, id_areaorganizzativaomogenea) VALUES (5, 'Servizio Telematico 05', 'Servizio Telematico 05', '051983267', 'http://www.serviziotelematico05.pa.it', 'serviziotelematico05@pa.it', '00000005', 5);


ALTER TABLE serviziotelematico_areaorganizzativaomogenea ENABLE TRIGGER ALL;


ALTER TABLE serviziotelematico_unitaorganizzativa DISABLE TRIGGER ALL;

INSERT INTO serviziotelematico_unitaorganizzativa (id, nome, descrizione, telefono, url, email, codice_ufficio, id_unitaorganizzativa) VALUES (1, 'Servizio Telematico 01', 'Servizio Telematico 01', '097132876', 'http://www.serviziotelematico01.pa.it', 'serviziotelematico01@pa.it', '00000001', 1);
INSERT INTO serviziotelematico_unitaorganizzativa (id, nome, descrizione, telefono, url, email, codice_ufficio, id_unitaorganizzativa) VALUES (2, 'Servizio Telematico 02', 'Servizio Telematico 02', '097132896', 'http://www.serviziotelematico02.pa.it', 'serviziotelematico02@pa.it', '00000002', 2);
INSERT INTO serviziotelematico_unitaorganizzativa (id, nome, descrizione, telefono, url, email, codice_ufficio, id_unitaorganizzativa) VALUES (3, 'Servizio Telematico 03', 'Servizio Telematico 03', '097132821', 'http://www.serviziotelematico03.pa.it', 'serviziotelematico03@pa.it', '00000003', 3);
INSERT INTO serviziotelematico_unitaorganizzativa (id, nome, descrizione, telefono, url, email, codice_ufficio, id_unitaorganizzativa) VALUES (4, 'Servizio Telematico 04', 'Servizio Telematico 04', '075219837', 'http://www.serviziotelematico04.pa.it', 'serviziotelematico04@pa.it', '00000004', 4);
INSERT INTO serviziotelematico_unitaorganizzativa (id, nome, descrizione, telefono, url, email, codice_ufficio, id_unitaorganizzativa) VALUES (5, 'Servizio Telematico 05', 'Servizio Telematico 05', '051983267', 'http://www.serviziotelematico05.pa.it', 'serviziotelematico05@pa.it', '00000005', 5);


ALTER TABLE serviziotelematico_unitaorganizzativa ENABLE TRIGGER ALL;


ALTER TABLE unitaorganizzativa DISABLE TRIGGER ALL;

INSERT INTO unitaorganizzativa (id, codice_uo, nome_ufficio, codice_uo_livello_superiore, codice_aoo, indirizzo, responsabile, email, email_pec, telefono, fax, url_ca, id_amministrazione) VALUES (1, '00000001', 'Unita Organizzativa 01', '00000000
', '00000001
', 1, 1, 'uo01@pa.it
', 'uo01@pa.cert.it
', '097121453
', '097121454
', 'http://www.unitaorganizzativa01.paweb.it', 1);
INSERT INTO unitaorganizzativa (id, codice_uo, nome_ufficio, codice_uo_livello_superiore, codice_aoo, indirizzo, responsabile, email, email_pec, telefono, fax, url_ca, id_amministrazione) VALUES (2, '00000002', 'Unita Organizzativa 02', '00000000', '00000002', 2, 2, 'uo02@pa.it', 'uo02@pa.cert.it', '097154127', '097154128', 'http://www.unitaorganizzativa02.paweb.it', 2);
INSERT INTO unitaorganizzativa (id, codice_uo, nome_ufficio, codice_uo_livello_superiore, codice_aoo, indirizzo, responsabile, email, email_pec, telefono, fax, url_ca, id_amministrazione) VALUES (3, '00000003', 'Unita Organizzativa 03', '00000000', '00000003', 3, 3, 'uo03@pa.it', 'uo03@pa.cert.it
', '0971128723', '0971128724', 'http://www.unitaorganizzativa03.paweb.it
', 3);
INSERT INTO unitaorganizzativa (id, codice_uo, nome_ufficio, codice_uo_livello_superiore, codice_aoo, indirizzo, responsabile, email, email_pec, telefono, fax, url_ca, id_amministrazione) VALUES (4, '00000004', 'Unita Organizzativa 04', '00000000
', '00000004', 6, 6, 'uo04@pa.it', 'uo04@pa.cert.it', '075436512', '075436513', 'http://www.unitaorganizzativa04.paweb.it', 4);
INSERT INTO unitaorganizzativa (id, codice_uo, nome_ufficio, codice_uo_livello_superiore, codice_aoo, indirizzo, responsabile, email, email_pec, telefono, fax, url_ca, id_amministrazione) VALUES (5, '00000005', 'Unita Organizzativa 05', '00000000

', '00000005', 9, 9, 'uo05@pa.it', 'uo05@pa.cert.it', '051783490', '051783491', 'http://www.unitaorganizzativa05.paweb.it', 5);


ALTER TABLE unitaorganizzativa ENABLE TRIGGER ALL;


