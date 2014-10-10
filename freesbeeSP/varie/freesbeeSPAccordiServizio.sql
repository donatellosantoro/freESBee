--
-- PostgreSQL database dump
--

-- Started on 2009-02-03 11:52:52

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

--
-- TOC entry 1693 (class 0 OID 65260)
-- Dependencies: 1291
-- Data for Name: accordoservizio; Type: TABLE DATA; Schema: public; Owner: freesbee
--

ALTER TABLE accordoservizio DISABLE TRIGGER ALL;

INSERT INTO accordoservizio (id, descrizione, nome, oraregistrazione, privato, profilocollaborazione, profiloegov_id) VALUES (98304, '', 'ASLoopback', '2008-03-27 12:00:00', false, 'EGOV_IT_ServizioSincrono', 98304);
INSERT INTO accordoservizio (id, descrizione, nome, oraregistrazione, privato, profilocollaborazione, profiloegov_id) VALUES (131072, '', 'accordoServizioSP', '2009-01-09 12:38:37.04', false, 'EGOV_IT_ServizioSincrono', 131072);


ALTER TABLE accordoservizio ENABLE TRIGGER ALL;

--
-- TOC entry 1694 (class 0 OID 65271)
-- Dependencies: 1292
-- Data for Name: azione; Type: TABLE DATA; Schema: public; Owner: freesbee
--

ALTER TABLE azione DISABLE TRIGGER ALL;



ALTER TABLE azione ENABLE TRIGGER ALL;

--
-- TOC entry 1695 (class 0 OID 65275)
-- Dependencies: 1293
-- Data for Name: configurazione; Type: TABLE DATA; Schema: public; Owner: freesbee
--

ALTER TABLE configurazione DISABLE TRIGGER ALL;

INSERT INTO configurazione (id, nica, connettoreregistroservizi, indirizzoportaapplicativa, indirizzoportadelegata, inviaanica, registrofreesbee, tempo, soggettoerogatoreregistroservizi_id, soggettoerogatorenica_id) VALUES (1, false, '', 'http://localhost:8196/PA/', 'http://localhost:8192/PD/', false, true, 'EGOV_IT_Locale', NULL, NULL);


ALTER TABLE configurazione ENABLE TRIGGER ALL;

--
-- TOC entry 1707 (class 0 OID 65439)
-- Dependencies: 1305
-- Data for Name: hibernate_sequences; Type: TABLE DATA; Schema: public; Owner: freesbee
--

ALTER TABLE hibernate_sequences DISABLE TRIGGER ALL;

INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Configurazione', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Sincronizzazione', 2);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Soggetto', 6);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('ProfiloEGov', 5);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('AccordoServizio', 5);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Servizio', 5);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('PortaDelegata', 9);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('ServizioApplicativo', 5);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('PortaApplicativa', 6);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Messaggio', 9);


ALTER TABLE hibernate_sequences ENABLE TRIGGER ALL;

--
-- TOC entry 1696 (class 0 OID 65282)
-- Dependencies: 1294
-- Data for Name: messaggio; Type: TABLE DATA; Schema: public; Owner: freesbee
--

ALTER TABLE messaggio DISABLE TRIGGER ALL;

INSERT INTO messaggio (id, idmessaggio, idsil, oraregistrazione, riferimentomessaggio, silrelatesto, tipo) VALUES (32768, 'null_nullSPCoopIT_0000004_2009-01-09_13:19', NULL, '2009-01-09T13:19:58.229', NULL, NULL, 'RICEVUTO');
INSERT INTO messaggio (id, idmessaggio, idsil, oraregistrazione, riferimentomessaggio, silrelatesto, tipo) VALUES (65536, 'null_nullSPCoopIT_0000002_2009-01-09_13:26', NULL, '2009-01-09T13:26:50.293', NULL, NULL, 'RICEVUTO');
INSERT INTO messaggio (id, idmessaggio, idsil, oraregistrazione, riferimentomessaggio, silrelatesto, tipo) VALUES (98304, 'fruitoreSP_fruitoreSPSPCoopIT_0000001_2009-01-09_15:19', NULL, '2009-01-09T15:19:20.817', NULL, NULL, 'INVIATO');
INSERT INTO messaggio (id, idmessaggio, idsil, oraregistrazione, riferimentomessaggio, silrelatesto, tipo) VALUES (131072, 'fruitoreSP_fruitoreSPSPCoopIT_0000001_2009-01-09_17:14', NULL, '2009-01-09T17:14:35.095', NULL, NULL, 'INVIATO');
INSERT INTO messaggio (id, idmessaggio, idsil, oraregistrazione, riferimentomessaggio, silrelatesto, tipo) VALUES (131073, 'fruitoreSP_fruitoreSPSPCoopIT_0000001_2009-01-09_17:14', NULL, '2009-01-09T17:14:35.095', NULL, NULL, 'RICEVUTO');
INSERT INTO messaggio (id, idmessaggio, idsil, oraregistrazione, riferimentomessaggio, silrelatesto, tipo) VALUES (131074, 'erogatoreSP_erogatoreSPSPCoopIT_0000002_2009-01-09_17:14', NULL, '2009-01-09T17:14:36.455', 'fruitoreSP_fruitoreSPSPCoopIT_0000001_2009-01-09_17:14', NULL, 'INVIATO');
INSERT INTO messaggio (id, idmessaggio, idsil, oraregistrazione, riferimentomessaggio, silrelatesto, tipo) VALUES (163840, 'fruitoreSP_fruitoreSPSPCoopIT_0000001_2009-01-09_17:56', NULL, '2009-01-09T17:56:05.495', NULL, NULL, 'INVIATO');
INSERT INTO messaggio (id, idmessaggio, idsil, oraregistrazione, riferimentomessaggio, silrelatesto, tipo) VALUES (163841, 'fruitoreSP_fruitoreSPSPCoopIT_0000001_2009-01-09_17:56', NULL, '2009-01-09T17:56:05.495', NULL, NULL, 'RICEVUTO');
INSERT INTO messaggio (id, idmessaggio, idsil, oraregistrazione, riferimentomessaggio, silrelatesto, tipo) VALUES (163842, 'erogatoreSP_erogatoreSPSPCoopIT_0000002_2009-01-09_17:56', NULL, '2009-01-09T17:56:06.525', 'fruitoreSP_fruitoreSPSPCoopIT_0000001_2009-01-09_17:56', NULL, 'INVIATO');
INSERT INTO messaggio (id, idmessaggio, idsil, oraregistrazione, riferimentomessaggio, silrelatesto, tipo) VALUES (196608, 'fruitoreSP_fruitoreSPSPCoopIT_0000001_2009-01-09_18:08', NULL, '2009-01-09T18:08:18.383', NULL, NULL, 'INVIATO');
INSERT INTO messaggio (id, idmessaggio, idsil, oraregistrazione, riferimentomessaggio, silrelatesto, tipo) VALUES (196609, 'fruitoreSP_fruitoreSPSPCoopIT_0000001_2009-01-09_18:08', NULL, '2009-01-09T18:08:18.383', NULL, NULL, 'RICEVUTO');
INSERT INTO messaggio (id, idmessaggio, idsil, oraregistrazione, riferimentomessaggio, silrelatesto, tipo) VALUES (196610, 'erogatoreSP_erogatoreSPSPCoopIT_0000002_2009-01-09_18:08', NULL, '2009-01-09T18:08:19.542', 'fruitoreSP_fruitoreSPSPCoopIT_0000001_2009-01-09_18:08', NULL, 'RICEVUTO');
INSERT INTO messaggio (id, idmessaggio, idsil, oraregistrazione, riferimentomessaggio, silrelatesto, tipo) VALUES (196611, 'fruitoreSP_fruitoreSPSPCoopIT_0000001_2009-01-09_18:12', NULL, '2009-01-09T18:12:02.041', NULL, NULL, 'INVIATO');
INSERT INTO messaggio (id, idmessaggio, idsil, oraregistrazione, riferimentomessaggio, silrelatesto, tipo) VALUES (196612, 'fruitoreSP_fruitoreSPSPCoopIT_0000001_2009-01-09_18:12', NULL, '2009-01-09T18:12:02.041', NULL, NULL, 'RICEVUTO');
INSERT INTO messaggio (id, idmessaggio, idsil, oraregistrazione, riferimentomessaggio, silrelatesto, tipo) VALUES (196613, 'erogatoreSP_erogatoreSPSPCoopIT_0000002_2009-01-09_18:12', NULL, '2009-01-09T18:12:02.516', 'fruitoreSP_fruitoreSPSPCoopIT_0000001_2009-01-09_18:12', NULL, 'RICEVUTO');
INSERT INTO messaggio (id, idmessaggio, idsil, oraregistrazione, riferimentomessaggio, silrelatesto, tipo) VALUES (229376, 'fruitoreSP_fruitoreSPSPCoopIT_0000001_2009-01-09_18:46', NULL, '2009-01-09T18:46:29.073', NULL, NULL, 'INVIATO');
INSERT INTO messaggio (id, idmessaggio, idsil, oraregistrazione, riferimentomessaggio, silrelatesto, tipo) VALUES (229377, 'fruitoreSP_fruitoreSPSPCoopIT_0000001_2009-01-09_18:46', NULL, '2009-01-09T18:46:29.073', NULL, NULL, 'RICEVUTO');
INSERT INTO messaggio (id, idmessaggio, idsil, oraregistrazione, riferimentomessaggio, silrelatesto, tipo) VALUES (229378, 'erogatoreSP_erogatoreSPSPCoopIT_0000002_2009-01-09_18:46', NULL, '2009-01-09T18:46:29.973', 'fruitoreSP_fruitoreSPSPCoopIT_0000001_2009-01-09_18:46', NULL, 'INVIATO');
INSERT INTO messaggio (id, idmessaggio, idsil, oraregistrazione, riferimentomessaggio, silrelatesto, tipo) VALUES (229379, 'erogatoreSP_erogatoreSPSPCoopIT_0000002_2009-01-09_18:46', NULL, '2009-01-09T18:46:29.973', 'fruitoreSP_fruitoreSPSPCoopIT_0000001_2009-01-09_18:46', NULL, 'RICEVUTO');
INSERT INTO messaggio (id, idmessaggio, idsil, oraregistrazione, riferimentomessaggio, silrelatesto, tipo) VALUES (262144, 'fruitoreSP_fruitoreSPSPCoopIT_0000001_2009-01-09_18:56', NULL, '2009-01-09T18:56:21.452', NULL, NULL, 'INVIATO');
INSERT INTO messaggio (id, idmessaggio, idsil, oraregistrazione, riferimentomessaggio, silrelatesto, tipo) VALUES (262145, 'fruitoreSP_fruitoreSPSPCoopIT_0000001_2009-01-09_18:56', NULL, '2009-01-09T18:56:21.452', NULL, NULL, 'RICEVUTO');
INSERT INTO messaggio (id, idmessaggio, idsil, oraregistrazione, riferimentomessaggio, silrelatesto, tipo) VALUES (262146, 'erogatoreSP_erogatoreSPSPCoopIT_0000002_2009-01-09_18:56', NULL, '2009-01-09T18:56:22.511', 'fruitoreSP_fruitoreSPSPCoopIT_0000001_2009-01-09_18:56', NULL, 'INVIATO');
INSERT INTO messaggio (id, idmessaggio, idsil, oraregistrazione, riferimentomessaggio, silrelatesto, tipo) VALUES (262147, 'erogatoreSP_erogatoreSPSPCoopIT_0000002_2009-01-09_18:56', NULL, '2009-01-09T18:56:22.511', 'fruitoreSP_fruitoreSPSPCoopIT_0000001_2009-01-09_18:56', NULL, 'RICEVUTO');


ALTER TABLE messaggio ENABLE TRIGGER ALL;

--
-- TOC entry 1697 (class 0 OID 65291)
-- Dependencies: 1295
-- Data for Name: messaggiodiagnostico; Type: TABLE DATA; Schema: public; Owner: freesbee
--

ALTER TABLE messaggiodiagnostico DISABLE TRIGGER ALL;



ALTER TABLE messaggiodiagnostico ENABLE TRIGGER ALL;

--
-- TOC entry 1698 (class 0 OID 65298)
-- Dependencies: 1296
-- Data for Name: portaapplicativa; Type: TABLE DATA; Schema: public; Owner: freesbee
--

ALTER TABLE portaapplicativa DISABLE TRIGGER ALL;

INSERT INTO portaapplicativa (id, descrizione, nome, stringaazione, stringaservizio, stringatiposervizio, azione_id, servizio_id, servizioapplicativo_id) VALUES (131072, '', 'PALoopback', NULL, NULL, NULL, NULL, 98304, 65536);
INSERT INTO portaapplicativa (id, descrizione, nome, stringaazione, stringaservizio, stringatiposervizio, azione_id, servizio_id, servizioapplicativo_id) VALUES (163840, '', 'portaApplicativaSP', NULL, NULL, NULL, NULL, 131072, 131072);


ALTER TABLE portaapplicativa ENABLE TRIGGER ALL;

--
-- TOC entry 1699 (class 0 OID 65307)
-- Dependencies: 1297
-- Data for Name: portadelegata; Type: TABLE DATA; Schema: public; Owner: freesbee
--

ALTER TABLE portadelegata DISABLE TRIGGER ALL;

INSERT INTO portadelegata (id, descrizione, nome, stringaazione, stringaerogatore, stringafruitore, stringaservizio, stringatipoerogatore, stringatipofruitore, stringatiposervizio, azione_id, soggettofruitore_id, servizio_id) VALUES (229376, '', 'SincronoLoopback', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 131072, 98304);
INSERT INTO portadelegata (id, descrizione, nome, stringaazione, stringaerogatore, stringafruitore, stringaservizio, stringatipoerogatore, stringatipofruitore, stringatiposervizio, azione_id, soggettofruitore_id, servizio_id) VALUES (262145, '', 'portaDelegataSP', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 163840, 131072);


ALTER TABLE portadelegata ENABLE TRIGGER ALL;

--
-- TOC entry 1700 (class 0 OID 65314)
-- Dependencies: 1298
-- Data for Name: profiloegov; Type: TABLE DATA; Schema: public; Owner: freesbee
--

ALTER TABLE profiloegov DISABLE TRIGGER ALL;

INSERT INTO profiloegov (id, confermaricezione, consegnainordine, gestioneduplicati, idcollaborazione, scadenza) VALUES (98304, false, false, false, false, NULL);
INSERT INTO profiloegov (id, confermaricezione, consegnainordine, gestioneduplicati, idcollaborazione, scadenza) VALUES (131072, false, false, false, false, NULL);


ALTER TABLE profiloegov ENABLE TRIGGER ALL;

--
-- TOC entry 1701 (class 0 OID 65318)
-- Dependencies: 1299
-- Data for Name: servizio; Type: TABLE DATA; Schema: public; Owner: freesbee
--

ALTER TABLE servizio DISABLE TRIGGER ALL;

INSERT INTO servizio (id, correlato, nome, oraregistrazione, privato, tipo, erogatore_id, accordoservizio_id) VALUES (98304, false, 'ServizioLoopback', '2008-03-27 12:00:00', false, 'TEST', 131073, 98304);
INSERT INTO servizio (id, correlato, nome, oraregistrazione, privato, tipo, erogatore_id, accordoservizio_id) VALUES (131072, false, 'servizioSP', '2009-01-09 12:39:17.96', false, 'SPC', 163841, 131072);


ALTER TABLE servizio ENABLE TRIGGER ALL;

--
-- TOC entry 1703 (class 0 OID 65336)
-- Dependencies: 1301
-- Data for Name: servizio_soggetto; Type: TABLE DATA; Schema: public; Owner: freesbee
--

ALTER TABLE servizio_soggetto DISABLE TRIGGER ALL;

INSERT INTO servizio_soggetto (listaservizifruitore_id, fruitori_id) VALUES (98304, 131072);
INSERT INTO servizio_soggetto (listaservizifruitore_id, fruitori_id) VALUES (131072, 163840);


ALTER TABLE servizio_soggetto ENABLE TRIGGER ALL;

--
-- TOC entry 1702 (class 0 OID 65327)
-- Dependencies: 1300
-- Data for Name: servizioapplicativo; Type: TABLE DATA; Schema: public; Owner: freesbee
--

ALTER TABLE servizioapplicativo DISABLE TRIGGER ALL;

INSERT INTO servizioapplicativo (id, connettore, descrizione, nome) VALUES (65536, 'http://localhost:8191/ws/verificaInstallazione', '', 'ServizioLoopback');
INSERT INTO servizioapplicativo (id, connettore, descrizione, nome) VALUES (131072, 'http://localhost:8765/xacml', '', 'servizioApplicativoSP');


ALTER TABLE servizioapplicativo ENABLE TRIGGER ALL;

--
-- TOC entry 1704 (class 0 OID 65338)
-- Dependencies: 1302
-- Data for Name: sincronizzazione; Type: TABLE DATA; Schema: public; Owner: freesbee
--

ALTER TABLE sincronizzazione DISABLE TRIGGER ALL;



ALTER TABLE sincronizzazione ENABLE TRIGGER ALL;

--
-- TOC entry 1705 (class 0 OID 65345)
-- Dependencies: 1303
-- Data for Name: soggetto; Type: TABLE DATA; Schema: public; Owner: freesbee
--

ALTER TABLE soggetto DISABLE TRIGGER ALL;

INSERT INTO soggetto (id, descrizione, nome, oraregistrazione, portadominio, tipo) VALUES (131072, '', 'FruitoreLoopback', '2008-03-27 12:00:00', 'http://localhost:8196/PA/', 'TEST');
INSERT INTO soggetto (id, descrizione, nome, oraregistrazione, portadominio, tipo) VALUES (131073, '', 'ErogatoreLoopback', '2008-03-27 12:00:00', 'http://localhost:8196/PA/', 'TEST');
INSERT INTO soggetto (id, descrizione, nome, oraregistrazione, portadominio, tipo) VALUES (163841, '', 'erogatoreSP', '2009-01-09 16:55:55.532', 'http://localhost:8196/PA/', 'SPC');
INSERT INTO soggetto (id, descrizione, nome, oraregistrazione, portadominio, tipo) VALUES (163840, '', 'fruitoreSP', '2009-01-09 16:56:03.771', 'http://localhost:8196/PA/', 'SPC');


ALTER TABLE soggetto ENABLE TRIGGER ALL;

--
-- TOC entry 1706 (class 0 OID 65352)
-- Dependencies: 1304
-- Data for Name: utente; Type: TABLE DATA; Schema: public; Owner: freesbee
--

ALTER TABLE utente DISABLE TRIGGER ALL;

INSERT INTO utente (id, nomeutente, "password", ruolo) VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'amministratore');


ALTER TABLE utente ENABLE TRIGGER ALL;

-- Completed on 2009-02-03 11:52:53

--
-- PostgreSQL database dump complete
--

