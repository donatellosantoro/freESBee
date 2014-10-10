--
-- TOC entry 1603 (class 0 OID 43015)
-- Dependencies: 1203
-- Data for Name: accordodicollaborazione; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE accordodicollaborazione DISABLE TRIGGER ALL;

INSERT INTO accordodicollaborazione (id, wsdlerogatore, wsdlfruitore, cartellafiles, filetypes, nome) VALUES (98304, 'AP6-ADS-ErogatoreLogicoRilevazioni.wsdl', NULL, '98304-AP6-getRilevazioni', 'AP6WSService_schema1.xsd', 'AP6-getRilevazioni');
INSERT INTO accordodicollaborazione (id, wsdlerogatore, wsdlfruitore, cartellafiles, filetypes, nome) VALUES (98305, 'AP6-ADS-ErogatoreLogicoTitolari.wsdl', NULL, '98305-AP6-getTitolari', 'AP6WSService_schema1.xsd', 'AP6-getTitolari');


ALTER TABLE accordodicollaborazione ENABLE TRIGGER ALL;

--
-- TOC entry 1605 (class 0 OID 43025)
-- Dependencies: 1205
-- Data for Name: dati; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE dati DISABLE TRIGGER ALL;

INSERT INTO dati (id, payloadcostante, datisql_id, datiinterattivi_id, daticostanti_id) VALUES (131072, NULL, 98304, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datisql_id, datiinterattivi_id, daticostanti_id) VALUES (131074, NULL, 98305, NULL, 98304);
INSERT INTO dati (id, payloadcostante, datisql_id, datiinterattivi_id, daticostanti_id) VALUES (131073, '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws.ap6.progettoicar.it/">
   <soapenv:Header/>
   <soapenv:Body>
      <ws:getTitolariResponse>
         <return xmlvalido="true" anno="2005" codistatregione="ab22" recordricevuti="2" recordinseriti="2" recordscartati="0"/>
      </ws:getTitolariResponse>
   </soapenv:Body>
</soapenv:Envelope>', NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datisql_id, datiinterattivi_id, daticostanti_id) VALUES (131075, NULL, 98306, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datisql_id, datiinterattivi_id, daticostanti_id) VALUES (131077, NULL, 98307, NULL, 98305);
INSERT INTO dati (id, payloadcostante, datisql_id, datiinterattivi_id, daticostanti_id) VALUES (131076, '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws.ap6.progettoicar.it/">
   <soapenv:Header/>
   <soapenv:Body>
      <ws:getRilevazioniResponse>
         <return xmlvalido="true" anno="2008" codistatregione="ab22" recordricevuti="2" recordinseriti="2" recordscartati="0">
         </return>
      </ws:getRilevazioniResponse>
   </soapenv:Body>
</soapenv:Envelope>', NULL, NULL, NULL);


ALTER TABLE dati ENABLE TRIGGER ALL;

--
-- TOC entry 1606 (class 0 OID 43032)
-- Dependencies: 1206
-- Data for Name: daticostanti; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE daticostanti DISABLE TRIGGER ALL;

INSERT INTO daticostanti (id) VALUES (98304);
INSERT INTO daticostanti (id) VALUES (98305);


ALTER TABLE daticostanti ENABLE TRIGGER ALL;

--
-- TOC entry 1607 (class 0 OID 43036)
-- Dependencies: 1207
-- Data for Name: datiinterattivi; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE datiinterattivi DISABLE TRIGGER ALL;



ALTER TABLE datiinterattivi ENABLE TRIGGER ALL;

--
-- TOC entry 1608 (class 0 OID 43040)
-- Dependencies: 1208
-- Data for Name: datisql; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE datisql DISABLE TRIGGER ALL;

INSERT INTO datisql (id, select_id) VALUES (98304, 98304);
INSERT INTO datisql (id, select_id) VALUES (98305, 98305);
INSERT INTO datisql (id, select_id) VALUES (98306, 98306);
INSERT INTO datisql (id, select_id) VALUES (98307, 98307);


ALTER TABLE datisql ENABLE TRIGGER ALL;

--
-- TOC entry 1609 (class 0 OID 43044)
-- Dependencies: 1209
-- Data for Name: datoprimitivo; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE datoprimitivo DISABLE TRIGGER ALL;

INSERT INTO datoprimitivo (id, nome, tipo, valore, daticostanti_id, datiinterattivi_id) VALUES (98304, 'data_rilevazione', NULL, '2008-07-10', 98304, NULL);
INSERT INTO datoprimitivo (id, nome, tipo, valore, daticostanti_id, datiinterattivi_id) VALUES (98305, 'codistatregione', NULL, 'ab22', 98304, NULL);
INSERT INTO datoprimitivo (id, nome, tipo, valore, daticostanti_id, datiinterattivi_id) VALUES (98306, 'data_rilevazione', NULL, '2008-07-10', 98305, NULL);
INSERT INTO datoprimitivo (id, nome, tipo, valore, daticostanti_id, datiinterattivi_id) VALUES (98307, 'codistatregione', NULL, 'ab22', 98305, NULL);


ALTER TABLE datoprimitivo ENABLE TRIGGER ALL;

--
-- TOC entry 1620 (class 0 OID 43214)
-- Dependencies: 1220
-- Data for Name: hibernate_sequences; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE hibernate_sequences DISABLE TRIGGER ALL;

INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Configurazione', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('DatiInterattivi', 2);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('AccordoDiCollaborazione', 4);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('PortType', 4);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Operation', 4);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Message', 4);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('IstanzaAccordoDiCollaborazione', 5);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('IstanzaPortType', 5);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('IstanzaOperation', 5);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Dati', 5);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('DatiSQL', 4);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Query', 4);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('DatiCostanti', 4);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('DatoPrimitivo', 4);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Messaggio', 5);


ALTER TABLE hibernate_sequences ENABLE TRIGGER ALL;

--
-- TOC entry 1610 (class 0 OID 43048)
-- Dependencies: 1210
-- Data for Name: istanzaaccordodicollaborazione; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE istanzaaccordodicollaborazione DISABLE TRIGGER ALL;

INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (131072, E'98305-AP6-getTitolari\\ISTANZE\\131072-AP6-Erogatore-Titolari\\', 'AP6-Erogatore-Titolari', 'EROGATORE', 98305);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (131073, E'98305-AP6-getTitolari\\ISTANZE\\131073-AP6-Fruitore-Titolari\\', 'AP6-Fruitore-Titolari', 'FRUITORE', 98305);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (131074, E'98304-AP6-getRilevazioni\\ISTANZE\\131074-AP6-Erogatore-Rilevazioni\\', 'AP6-Erogatore-Rilevazioni', 'EROGATORE', 98304);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (131075, E'98304-AP6-getRilevazioni\\ISTANZE\\131075-AP6-Fruitore-Rilevazioni\\', 'AP6-Fruitore-Rilevazioni', 'FRUITORE', 98304);


ALTER TABLE istanzaaccordodicollaborazione ENABLE TRIGGER ALL;

--
-- TOC entry 1611 (class 0 OID 43054)
-- Dependencies: 1211
-- Data for Name: istanzaoperation; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE istanzaoperation DISABLE TRIGGER ALL;

INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, istanzaporttype_id, operation_id, datirisposta_id, datirichiesta_id) VALUES (131072, NULL, NULL, NULL, NULL, 'trasformazioneRichiesta.xslt', true, false, NULL, false, 131072, 98305, 131073, 131072);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, istanzaporttype_id, operation_id, datirisposta_id, datirichiesta_id) VALUES (131073, 'trasformazioneRichiesta.xslt', NULL, NULL, NULL, NULL, false, false, NULL, false, 131073, 98305, NULL, 131074);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, istanzaporttype_id, operation_id, datirisposta_id, datirichiesta_id) VALUES (131074, NULL, NULL, NULL, NULL, 'trasformazioneRichiesta.xslt', true, false, NULL, false, 131074, 98304, 131076, 131075);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, elaborarichiesta, elaborarisposta, indirizzorispostaasincronoerogatore, rispostaautomatica, istanzaporttype_id, operation_id, datirisposta_id, datirichiesta_id) VALUES (131075, 'trasformazioneRichiesta.xslt', NULL, NULL, NULL, NULL, false, false, NULL, false, 131075, 98304, NULL, 131077);


ALTER TABLE istanzaoperation ENABLE TRIGGER ALL;

--
-- TOC entry 1612 (class 0 OID 43058)
-- Dependencies: 1212
-- Data for Name: istanzaporttype; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE istanzaporttype DISABLE TRIGGER ALL;

INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (131072, NULL, 'EROGAZIONE', 131072, 98305);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (131073, 'http://localhost:9192/EROGATORE/AP6WS_131072/', 'FRUIZIONE', 131073, 98305);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (131074, NULL, 'EROGAZIONE', 131074, 98304);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (131075, 'http://localhost:9192/EROGATORE/AP6WS_131074/', 'FRUIZIONE', 131075, 98304);


ALTER TABLE istanzaporttype ENABLE TRIGGER ALL;

--
-- TOC entry 1613 (class 0 OID 43062)
-- Dependencies: 1213
-- Data for Name: message; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE message DISABLE TRIGGER ALL;

INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (98304, 'http://ws.ap6.progettoicar.it/', 'getRilevazioni', 'getRilevazioni', 98304);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (98305, 'http://ws.ap6.progettoicar.it/', 'getRilevazioniResponse', 'getRilevazioniResponse', 98304);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (98306, 'http://ws.ap6.progettoicar.it/', 'getTitolari', 'getTitolari', 98305);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (98307, 'http://ws.ap6.progettoicar.it/', 'getTitolariResponse', 'getTitolariResponse', 98305);


ALTER TABLE message ENABLE TRIGGER ALL;

--
-- TOC entry 1614 (class 0 OID 43066)
-- Dependencies: 1214
-- Data for Name: messaggio; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE messaggio DISABLE TRIGGER ALL;

ALTER TABLE messaggio ENABLE TRIGGER ALL;

--
-- TOC entry 1615 (class 0 OID 43073)
-- Dependencies: 1215
-- Data for Name: messaggiosbloccomanuale; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE messaggiosbloccomanuale DISABLE TRIGGER ALL;



ALTER TABLE messaggiosbloccomanuale ENABLE TRIGGER ALL;

--
-- TOC entry 1616 (class 0 OID 43080)
-- Dependencies: 1216
-- Data for Name: operation; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE operation DISABLE TRIGGER ALL;

INSERT INTO operation (id, nome, profilodicollaborazione, messagein_id, messageout_id, porttype_id, messagefault_id) VALUES (98304, 'getRilevazioni', 'SINCRONO', 98304, 98305, 98304, NULL);
INSERT INTO operation (id, nome, profilodicollaborazione, messagein_id, messageout_id, porttype_id, messagefault_id) VALUES (98305, 'getTitolari', 'SINCRONO', 98306, 98307, 98305, NULL);


ALTER TABLE operation ENABLE TRIGGER ALL;

--
-- TOC entry 1617 (class 0 OID 43084)
-- Dependencies: 1217
-- Data for Name: porttype; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE porttype DISABLE TRIGGER ALL;

INSERT INTO porttype (id, nome, ruolo, accordodicollaborazione_id) VALUES (98304, 'AP6WS', 'EROGATORE', 98304);
INSERT INTO porttype (id, nome, ruolo, accordodicollaborazione_id) VALUES (98305, 'AP6WS', 'EROGATORE', 98305);


ALTER TABLE porttype ENABLE TRIGGER ALL;

--
-- TOC entry 1618 (class 0 OID 43088)
-- Dependencies: 1218
-- Data for Name: query; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE query DISABLE TRIGGER ALL;


INSERT INTO query (id, indirizzodb, nomedb, nomeutente, "password", query, tipodb) VALUES (98304, 'localhost', 'ap6InterReg', 'silvio', '**silvio**', NULL, 'PostgreSQL');
INSERT INTO query (id, indirizzodb, nomedb, nomeutente, "password", query, tipodb) VALUES (98305, 'localhost', 'ap6Reg', 'silvio', '**silvio**', 'select * from titolare', 'PostgreSQL');
INSERT INTO query (id, indirizzodb, nomedb, nomeutente, "password", query, tipodb) VALUES (98306, 'localhost', 'ap6InterReg', 'silvio', '**silvio**', NULL, 'PostgreSQL');
INSERT INTO query (id, indirizzodb, nomedb, nomeutente, "password", query, tipodb) VALUES (98307, 'localhost', 'ap6Reg', 'silvio', '**silvio**', 'select rilevazione.idrilevazione, rilevazione.codimpianto, superifcie, numero_addetti, numero_dipendenti, tipologia, att_commericale, autolavaggio, officina, self_services, stato, bandiera,
       rilevazione_has_carburante.carburante,  rilevazione_has_carburante.serbatoio,
       rilevazione_has_pagamento.pagamento, rilevazione_has_servizio.servizio, rilevazione_has_servizioaccessorio.servizioaccessorio 
from rilevazione 
join rilevazione_has_carburante on(rilevazione.idrilevazione=rilevazione_has_carburante.idrilevazione)
join rilevazione_has_pagamento on(rilevazione.idrilevazione=rilevazione_has_pagamento.idrilevazione)
join rilevazione_has_servizio on(rilevazione.idrilevazione=rilevazione_has_servizio.idrilevazione)
join rilevazione_has_servizioaccessorio on(rilevazione.idrilevazione=rilevazione_has_servizioaccessorio.idrilevazione)', 'PostgreSQL');


ALTER TABLE query ENABLE TRIGGER ALL;

--
-- TOC entry 1619 (class 0 OID 43095)
-- Dependencies: 1219
-- Data for Name: utente; Type: TABLE DATA; Schema: public; Owner: silvio
--

--
-- PostgreSQL database dump complete
--

