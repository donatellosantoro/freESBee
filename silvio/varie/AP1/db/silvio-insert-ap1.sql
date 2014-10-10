ALTER TABLE accordodicollaborazione DISABLE TRIGGER ALL;

INSERT INTO accordodicollaborazione (id, wsdlerogatore, wsdlfruitore, cartellafiles, filetypes, nome) VALUES (1, 'logicoErogatore.wsdl', NULL, '1-IdentificazioneAssistito', 'Types2.2.xsd', 'IdentificazioneAssistito');
INSERT INTO accordodicollaborazione (id, wsdlerogatore, wsdlfruitore, cartellafiles, filetypes, nome) VALUES (2, 'logicoErogatore.wsdl', 'logicoFruitore.wsdl', '2-MobilitaSanitaria', 'Types2.2.xsd', 'MobilitaSanitaria');
INSERT INTO accordodicollaborazione (id, wsdlerogatore, wsdlfruitore, cartellafiles, filetypes, nome) VALUES (32768, 'ImplementativoErogatore.wsdl', 'ImplementativoFruitore.wsdl', '32768-PrestazioniAmbulatoriali', 'Types2.2.xsd', 'PrestazioniAmbulatoriali');


ALTER TABLE accordodicollaborazione ENABLE TRIGGER ALL;


ALTER TABLE dati DISABLE TRIGGER ALL;

INSERT INTO dati (id, payloadcostante, datisql_id, daticostanti_id, datiinterattivi_id) VALUES (1, '<?xml version="1.0" encoding="UTF-8"?>
<NS:richiesta_RichiestaRispostaSincrona_IdentificazioneAssistito version="2.2" xsi:schemaLocation="http://www.intemaweb.com/iron/datatypes Types2.2.xsd" xmlns:NS="http://www.intemaweb.com/iron/datatypes" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<NS:Intestazione>
		<NS:InfoMittente>
			<NS:Regione>170</NS:Regione>
			<NS:CodiceAsl>102</NS:CodiceAsl>
			<NS:IdOperatore>0</NS:IdOperatore>
		</NS:InfoMittente>
		<NS:InfoDestinatario>
			<NS:Regione>000</NS:Regione>
			<NS:CodiceAsl>000</NS:CodiceAsl>
			<NS:IdOperatore>0</NS:IdOperatore>
		</NS:InfoDestinatario>
		<NS:DataOra>2001-12-17T09:30:47.0Z</NS:DataOra>
		<NS:IdMessaggio>0</NS:IdMessaggio>
		<NS:IdComunicazione>0</NS:IdComunicazione>
	</NS:Intestazione>
	<NS:IdentificazioneAssistito>
		<NS:CodiceAslResidenza>102</NS:CodiceAslResidenza>
		<NS:CodiceAslAssistenza>000</NS:CodiceAslAssistenza>
		<NS:DataRiconoscimento>1967-08-13</NS:DataRiconoscimento>
		<NS:DatiAnagrafici>
			<NS:Cognome>MARIO</NS:Cognome>
			<NS:Nome>ROSSI</NS:Nome>
			<NS:DataDiNascita>1967-08-13</NS:DataDiNascita>
			<NS:ComuneNascita>000001</NS:ComuneNascita>
		</NS:DatiAnagrafici>
	</NS:IdentificazioneAssistito>
</NS:richiesta_RichiestaRispostaSincrona_IdentificazioneAssistito>
', NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datisql_id, daticostanti_id, datiinterattivi_id) VALUES (2, '<?xml version="1.0" encoding="UTF-8"?>
<NS:risposta_RichiestaRispostaSincrona_IdentificazioneAssistito version="2.2" xsi:schemaLocation="http://www.intemaweb.com/iron/datatypes Types2.2.xsd" xmlns:NS="http://www.intemaweb.com/iron/datatypes" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<NS:Intestazione>
		<NS:InfoMittente>
			<NS:Regione>000</NS:Regione>
			<NS:CodiceAsl>000</NS:CodiceAsl>
			<NS:IdOperatore>0</NS:IdOperatore>
		</NS:InfoMittente>
		<NS:InfoDestinatario>
			<NS:Regione>000</NS:Regione>
			<NS:CodiceAsl>000</NS:CodiceAsl>
			<NS:IdOperatore>0</NS:IdOperatore>
		</NS:InfoDestinatario>
		<NS:DataOra>2001-12-17T09:30:47.0Z</NS:DataOra>
		<NS:IdMessaggio>0</NS:IdMessaggio>
		<NS:IdComunicazione>0</NS:IdComunicazione>
	</NS:Intestazione>
	<NS:DatiAnagraficiAssistito>
		<NS:CodiceFiscale>AAAAAA00A00A000A</NS:CodiceFiscale>
		<NS:CodiceSSR>a</NS:CodiceSSR>
		<NS:DatiAnagraficiBase>
			<NS:Cognome> </NS:Cognome>
			<NS:Nome> </NS:Nome>
			<NS:DataDiNascita>1967-08-13</NS:DataDiNascita>
			<NS:ComuneNascita>000000</NS:ComuneNascita>
		</NS:DatiAnagraficiBase>
		<NS:Sesso>1</NS:Sesso>
		<NS:ComuneResidenza>000000</NS:ComuneResidenza>
		<NS:AslResidenza>000</NS:AslResidenza>
		<NS:CFValidatoMEF>true</NS:CFValidatoMEF>
		<NS:IndirizzoResidenza>a</NS:IndirizzoResidenza>
		<NS:Telefono>+</NS:Telefono>
		<NS:Cellulare>+</NS:Cellulare>
		<NS:Email>A0@00.AA</NS:Email>
		<NS:MedicoBase>
			<NS:Cognome> </NS:Cognome>
			<NS:Nome> </NS:Nome>
			<NS:ComuneResidenza>000000</NS:ComuneResidenza>
			<NS:IndirizzoResidenza>a</NS:IndirizzoResidenza>
			<NS:CodiceFiscale>AAAAAA00A00A000A</NS:CodiceFiscale>
			<NS:CodiceRegionale>a</NS:CodiceRegionale>
			<NS:Telefono>+</NS:Telefono>
			<NS:Cellulare>+</NS:Cellulare>
			<NS:Email>A0@00.AA</NS:Email>
		</NS:MedicoBase>
	</NS:DatiAnagraficiAssistito>
</NS:risposta_RichiestaRispostaSincrona_IdentificazioneAssistito>
', NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datisql_id, daticostanti_id, datiinterattivi_id) VALUES (3, NULL, NULL, 1, 1);
INSERT INTO dati (id, payloadcostante, datisql_id, daticostanti_id, datiinterattivi_id) VALUES (4, NULL, 1, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datisql_id, daticostanti_id, datiinterattivi_id) VALUES (5, NULL, 2, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datisql_id, daticostanti_id, datiinterattivi_id) VALUES (65536, '<NS:risposta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali version="2.2" xsi:schemaLocation="http://www.intemaweb.com/iron/datatypes Types2.2.xsd" xmlns:NS="http://www.intemaweb.com/iron/datatypes" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">

	<NS:Intestazione>

		<NS:InfoMittente>

			<NS:Regione>000</NS:Regione>

		</NS:InfoMittente>

		<NS:InfoDestinatario>

			<NS:Regione>000</NS:Regione>

		</NS:InfoDestinatario>

		<NS:DataOra>2001-12-17T09:30:47.0Z</NS:DataOra>

		<NS:IdMessaggio>0</NS:IdMessaggio>

		<NS:IdComunicazione>0</NS:IdComunicazione>

	</NS:Intestazione>

	<NS:SegnalazioneFruizionePrestazioniAmbulatoriali>OK</NS:SegnalazioneFruizionePrestazioniAmbulatoriali>

</NS:risposta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali>', NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datisql_id, daticostanti_id, datiinterattivi_id) VALUES (65538, NULL, 32769, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datisql_id, daticostanti_id, datiinterattivi_id) VALUES (65539, NULL, NULL, 32769, NULL);
INSERT INTO dati (id, payloadcostante, datisql_id, daticostanti_id, datiinterattivi_id) VALUES (32768, '<NS:richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali version="2.2" xsi:schemaLocation="http://www.intemaweb.com/iron/datatypes Types2.2.xsd" xmlns:NS="http://www.intemaweb.com/iron/datatypes" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">

	<NS:Intestazione>

		<NS:InfoMittente>

			<NS:Regione>000</NS:Regione>

		</NS:InfoMittente>

		<NS:InfoDestinatario>

			<NS:Regione>000</NS:Regione>

		</NS:InfoDestinatario>

		<NS:DataOra>2001-12-17T09:30:47.0Z</NS:DataOra>

		<NS:IdMessaggio>0</NS:IdMessaggio>

		<NS:IdComunicazione>0</NS:IdComunicazione>

	</NS:Intestazione>

	<NS:SegnalazioneFruizionePrestazioniAmbulatoriali>

		<NS:DatiAnagrafici>

			<NS:Sesso>1</NS:Sesso>

			<NS:ComuneResidenza>000000</NS:ComuneResidenza>

		</NS:DatiAnagrafici>

		<NS:DatiPrestazioniAmbulatoriali>

			<NS:DatiRicetta>

				<NS:Data>1967-08-13</NS:Data>

				<NS:PosizioneUtenteTicket>1</NS:PosizioneUtenteTicket>

				<NS:ImportoTicket>,00</NS:ImportoTicket>

				<NS:ImportoTotale>,00</NS:ImportoTotale>

			</NS:DatiRicetta>

			<NS:DatiPrestazioniRicetta>

				<NS:Prestazione progressivo="2">

					<NS:CodicePrestazione>a</NS:CodicePrestazione>

					<NS:CodificaNomenclatore>N</NS:CodificaNomenclatore>

					<NS:Quantita>1</NS:Quantita>

					<NS:Importo>,00</NS:Importo>

				</NS:Prestazione>

				<NS:ID>aaaaaaaaaaaaaaaaaaaa</NS:ID>

			</NS:DatiPrestazioniRicetta>

		</NS:DatiPrestazioniAmbulatoriali>

	</NS:SegnalazioneFruizionePrestazioniAmbulatoriali>

</NS:richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali>

', NULL, NULL, NULL);
INSERT INTO dati (id, payloadcostante, datisql_id, daticostanti_id, datiinterattivi_id) VALUES (65537, NULL, 32768, 32768, 32768);


ALTER TABLE dati ENABLE TRIGGER ALL;

--
-- Data for Name: daticostanti; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE daticostanti DISABLE TRIGGER ALL;

INSERT INTO daticostanti (id) VALUES (1);
INSERT INTO daticostanti (id) VALUES (32768);
INSERT INTO daticostanti (id) VALUES (32769);


ALTER TABLE daticostanti ENABLE TRIGGER ALL;

--
-- Data for Name: datiinterattivi; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE datiinterattivi DISABLE TRIGGER ALL;

INSERT INTO datiinterattivi (id) VALUES (1);
INSERT INTO datiinterattivi (id) VALUES (32768);


ALTER TABLE datiinterattivi ENABLE TRIGGER ALL;

--
-- Data for Name: datisql; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE datisql DISABLE TRIGGER ALL;

INSERT INTO datisql (id, select_id) VALUES (1, 1);
INSERT INTO datisql (id, select_id) VALUES (2, 2);
INSERT INTO datisql (id, select_id) VALUES (32769, 32769);
INSERT INTO datisql (id, select_id) VALUES (32768, 32768);


ALTER TABLE datisql ENABLE TRIGGER ALL;

--
-- Data for Name: datoprimitivo; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE datoprimitivo DISABLE TRIGGER ALL;

INSERT INTO datoprimitivo (id, nome, tipo, valore, daticostanti_id, datiinterattivi_id) VALUES (1, 'RegioneMittente', NULL, '170', 1, NULL);
INSERT INTO datoprimitivo (id, nome, tipo, valore, daticostanti_id, datiinterattivi_id) VALUES (2, 'CodiceAsl', NULL, '102', 1, NULL);
INSERT INTO datoprimitivo (id, nome, tipo, valore, daticostanti_id, datiinterattivi_id) VALUES (3, 'IdOperatore', NULL, '0', 1, NULL);
INSERT INTO datoprimitivo (id, nome, tipo, valore, daticostanti_id, datiinterattivi_id) VALUES (4, 'DataOra', NULL, '2001-12-17T09:30:47.0Z', 1, NULL);
INSERT INTO datoprimitivo (id, nome, tipo, valore, daticostanti_id, datiinterattivi_id) VALUES (5, 'IdMessaggio', NULL, '1', 1, NULL);
INSERT INTO datoprimitivo (id, nome, tipo, valore, daticostanti_id, datiinterattivi_id) VALUES (6, 'IdComunicazione', NULL, '8', 1, NULL);
INSERT INTO datoprimitivo (id, nome, tipo, valore, daticostanti_id, datiinterattivi_id) VALUES (7, 'CodiceFiscale', NULL, NULL, NULL, 1);
INSERT INTO datoprimitivo (id, nome, tipo, valore, daticostanti_id, datiinterattivi_id) VALUES (8, 'RegioneDestinatario', NULL, NULL, NULL, 1);
INSERT INTO datoprimitivo (id, nome, tipo, valore, daticostanti_id, datiinterattivi_id) VALUES (9, 'CodiceAslDestinatario', NULL, NULL, NULL, 1);
INSERT INTO datoprimitivo (id, nome, tipo, valore, daticostanti_id, datiinterattivi_id) VALUES (10, 'IdOperatoreDestinatario', NULL, NULL, NULL, 1);
INSERT INTO datoprimitivo (id, nome, tipo, valore, daticostanti_id, datiinterattivi_id) VALUES (11, 'CodiceAslResidenza', NULL, NULL, NULL, 1);
INSERT INTO datoprimitivo (id, nome, tipo, valore, daticostanti_id, datiinterattivi_id) VALUES (12, 'CodiceAslAssistenza', NULL, NULL, NULL, 1);
INSERT INTO datoprimitivo (id, nome, tipo, valore, daticostanti_id, datiinterattivi_id) VALUES (32776, 'conferma', NULL, 'ACK', 32769, NULL);
INSERT INTO datoprimitivo (id, nome, tipo, valore, daticostanti_id, datiinterattivi_id) VALUES (32768, 'RegioneMittente', NULL, '170', 32768, NULL);
INSERT INTO datoprimitivo (id, nome, tipo, valore, daticostanti_id, datiinterattivi_id) VALUES (32769, 'CodiceAslMittente', NULL, '102', 32768, NULL);
INSERT INTO datoprimitivo (id, nome, tipo, valore, daticostanti_id, datiinterattivi_id) VALUES (32770, 'IdOperatoreMittente', NULL, '8', 32768, NULL);
INSERT INTO datoprimitivo (id, nome, tipo, valore, daticostanti_id, datiinterattivi_id) VALUES (32771, 'DataOra', NULL, '2001-12-17T09:30:47.0Z', 32768, NULL);
INSERT INTO datoprimitivo (id, nome, tipo, valore, daticostanti_id, datiinterattivi_id) VALUES (32772, 'IdMessaggio', NULL, '0', 32768, NULL);
INSERT INTO datoprimitivo (id, nome, tipo, valore, daticostanti_id, datiinterattivi_id) VALUES (32773, 'IdComunicazione', NULL, '0', 32768, NULL);
INSERT INTO datoprimitivo (id, nome, tipo, valore, daticostanti_id, datiinterattivi_id) VALUES (32774, 'RegioneDestinatario', NULL, NULL, NULL, 32768);
INSERT INTO datoprimitivo (id, nome, tipo, valore, daticostanti_id, datiinterattivi_id) VALUES (32775, 'CodiceAslDestinatario', NULL, NULL, NULL, 32768);


ALTER TABLE datoprimitivo ENABLE TRIGGER ALL;


DELETE FROM hibernate_sequences;

ALTER TABLE hibernate_sequences DISABLE TRIGGER ALL;

INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Configurazione', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('AccordoDiCollaborazione', 2);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('PortType', 2);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Operation', 2);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Message', 2);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('IstanzaAccordoDiCollaborazione', 3);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('IstanzaPortType', 3);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('IstanzaOperation', 3);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Dati', 3);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('DatiCostanti', 2);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('DatoPrimitivo', 2);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('DatiInterattivi', 2);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('DatiSQL', 2);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Query', 2);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Messaggio', 3);

ALTER TABLE hibernate_sequences ENABLE TRIGGER ALL;

--
-- Data for Name: istanzaaccordodicollaborazione; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE istanzaaccordodicollaborazione DISABLE TRIGGER ALL;

INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (1, '1-IdentificazioneAssistito//ISTANZE/1-IdentificatoreAssistitoCostante/', 'IdentificatoreAssistitoCostante', 'FRUITORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (2, '1-IdentificazioneAssistito/ISTANZE/2-IdentificazioneAssistitoErogatoreCostante/', 'IdentificazioneAssistitoErogatoreCostante', 'EROGATORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (3, '1-IdentificazioneAssistito/ISTANZE/3-IdentificazioneAssistitoFruitore/', 'IdentificazioneAssistitoFruitore', 'FRUITORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (4, '1-IdentificazioneAssistito/ISTANZE/4-IdentificazioneAssistitoErogatore/', 'IdentificazioneAssistitoErogatore', 'EROGATORE', 1);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (32768, '32768-PrestazioniAmbulatoriali/ISTANZE/32768-PrestazioniAmbulatorialiFruitoreCostante/', 'PrestazioniAmbulatorialiFruitoreCostante', 'FRUITORE', 32768);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (65536, '32768-PrestazioniAmbulatoriali/ISTANZE/65536-PrestazioniAmbulatorialiErogatoreCostante/', 'PrestazioniAmbulatorialiErogatoreCostante', 'EROGATORE', 32768);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (65537, '32768-PrestazioniAmbulatoriali/ISTANZE/65537-FruizionePrestazioniAmbulatorialiFruitore/', 'FruizionePrestazioniAmbulatorialiFruitore', 'FRUITORE', 32768);
INSERT INTO istanzaaccordodicollaborazione (id, cartellafiles, nome, ruolo, accordo_id) VALUES (65538, '32768-PrestazioniAmbulatoriali/ISTANZE/65538-PrestazioniAmbulatorialiErogatore/', 'PrestazioniAmbulatorialiErogatore', 'EROGATORE', 32768);


ALTER TABLE istanzaaccordodicollaborazione ENABLE TRIGGER ALL;

--
-- Data for Name: istanzaoperation; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE istanzaoperation DISABLE TRIGGER ALL;

INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, datirichiesta_id, datirisposta_id, istanzaporttype_id, parametrisla_id, operation_id) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, false, false, false, false, NULL, NULL, false, NULL, NULL, false, NULL, 1, NULL, 1, NULL, 1);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, datirichiesta_id, datirisposta_id, istanzaporttype_id, parametrisla_id, operation_id) VALUES (2, NULL, NULL, NULL, NULL, NULL, NULL, false, false, false, false, NULL, NULL, false, NULL, NULL, false, NULL, NULL, 2, 2, NULL, 1);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, datirichiesta_id, datirisposta_id, istanzaporttype_id, parametrisla_id, operation_id) VALUES (4, NULL, NULL, 'trasformazioneRisposta.xslt', NULL, 'trasformazioneRichiestaToSelect.xslt', NULL, false, false, false, false, NULL, NULL, false, NULL, NULL, false, NULL, NULL, 5, 4, NULL, 1);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, datirichiesta_id, datirisposta_id, istanzaporttype_id, parametrisla_id, operation_id) VALUES (32769, NULL, NULL, NULL, NULL, NULL, NULL, false, false, false, false, NULL, NULL, false, NULL, NULL, false, NULL, NULL, NULL, 32769, NULL, 32770);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, datirichiesta_id, datirisposta_id, istanzaporttype_id, parametrisla_id, operation_id) VALUES (65536, NULL, NULL, NULL, NULL, NULL, NULL, false, false, false, false, NULL, 'http://localhost:9192/FRUITORE/ServizioFruizionePrestazioniAmbulatoriali_32769/', false, NULL, NULL, true, NULL, NULL, 65536, 65536, NULL, 32768);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, datirichiesta_id, datirisposta_id, istanzaporttype_id, parametrisla_id, operation_id) VALUES (65538, NULL, NULL, NULL, NULL, NULL, NULL, false, false, false, false, NULL, NULL, false, NULL, NULL, false, NULL, NULL, NULL, 65538, NULL, 32770);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, datirichiesta_id, datirisposta_id, istanzaporttype_id, parametrisla_id, operation_id) VALUES (65539, NULL, NULL, 'trasformazioneRisposta.xslt', NULL, NULL, 'trasformazioneRichiesta.xslt', false, false, true, false, NULL, 'http://localhost:9192/FRUITORE/ServizioFruizionePrestazioniAmbulatoriali_65538/', false, NULL, NULL, true, NULL, 65538, 65539, 65539, NULL, 32768);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, datirichiesta_id, datirisposta_id, istanzaporttype_id, parametrisla_id, operation_id) VALUES (32768, NULL, NULL, NULL, NULL, NULL, NULL, false, false, false, false, NULL, NULL, false, NULL, NULL, false, NULL, 32768, NULL, 32768, NULL, 32768);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, datirichiesta_id, datirisposta_id, istanzaporttype_id, parametrisla_id, operation_id) VALUES (65537, 'trasformazioneRichiesta.xslt', NULL, NULL, NULL, NULL, NULL, false, true, false, false, NULL, NULL, false, 'Prestazioni Ambulatoriali', NULL, false, '@SILVIO.DIR.CONFIG@/SKIN/variazioneAnagrafica/', 65537, NULL, 65537, NULL, 32768);
INSERT INTO istanzaoperation (id, xsltcompletitosoapfruitore, xsltdatitosqlfruitore, xsltdatitosoaperogatore, xsltsoaptomail, xsltsoaptosqlerogatore, xsltsoaptosqlinserterogatore, autenticazionefederata, avviorapido, elaborarichiesta, elaborarisposta, indirizzomail, indirizzorispostaasincronoerogatore, inviamail, nomeapp, oggettomail, rispostaautomatica, skindir, datirichiesta_id, datirisposta_id, istanzaporttype_id, parametrisla_id, operation_id) VALUES (3, 'trasformazione.xslt', 'trasformazioneRisposta.xslt', NULL, NULL, NULL, NULL, false, true, false, true, NULL, NULL, false, 'Identificazione Assistito', NULL, false, '@SILVIO.DIR.CONFIG@/SKIN/idAssistito/', 3, 4, 3, NULL, 1);


ALTER TABLE istanzaoperation ENABLE TRIGGER ALL;

--
-- Data for Name: istanzaporttype; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE istanzaporttype DISABLE TRIGGER ALL;

INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (2, NULL, 'EROGAZIONE', 2, 1);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (4, NULL, 'EROGAZIONE', 4, 1);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (1, 'http://localhost:9192/EROGATORE/ServizioIdentificazioneAssistito_2/', 'FRUIZIONE', 1, 1);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (3, 'http://localhost:9192/EROGATORE/ServizioIdentificazioneAssistito_4/', 'FRUIZIONE', 3, 1);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (32769, NULL, 'EROGAZIONE', 32768, 32769);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (65536, NULL, 'EROGAZIONE', 65536, 32768);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (65538, NULL, 'EROGAZIONE', 65537, 32769);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (65539, NULL, 'EROGAZIONE', 65538, 32768);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (32768, 'http://localhost:9192/EROGATORE/ServizioFruizionePrestazioniAmbulatoriali_65536/', 'FRUIZIONE', 32768, 32768);
INSERT INTO istanzaporttype (id, urlinvio, tipo, istanzaaccordo_id, porttype_id) VALUES (65537, 'http://localhost:9192/EROGATORE/ServizioFruizionePrestazioniAmbulatoriali_65539/', 'FRUIZIONE', 65537, 32768);


ALTER TABLE istanzaporttype ENABLE TRIGGER ALL;

--
-- Data for Name: message; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE message DISABLE TRIGGER ALL;

INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (1, 'http://www.intemaweb.com/services/identificazioneassistito', 'richiesta_RichiestaRispostaSincrona_IdentificazioneAssistito_Msg', 'richiesta_RichiestaRispostaSincrona_IdentificazioneAssistito', 1);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (2, 'http://www.intemaweb.com/services/identificazioneassistito', 'risposta_RichiestaRispostaSincrona_IdentificazioneAssistito_Msg', 'risposta_RichiestaRispostaSincrona_IdentificazioneAssistito', 1);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (3, 'http://www.intemaweb.com/services/mobsan', 'richiesta_RichiestaRispostaAsincrona_SegnalazioneRicovero_Msg', 'richiesta_RichiestaRispostaAsincrona_SegnalazioneRicovero', 2);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (4, 'http://www.intemaweb.com/services/mobsan', 'richiesta_RichiestaRispostaAsincrona_AggiornamentoMobilita_Msg', 'richiesta_RichiestaRispostaAsincrona_AggiornamentoMobilita', 3);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (5, 'http://www.intemaweb.com/services/mobsan', 'richiesta_RichiestaRispostaAsincrona_SegnalazioneDimissione_Msg', 'richiesta_RichiestaRispostaAsincrona_SegnalazioneDimissione', 4);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (6, 'http://www.intemaweb.com/services/mobsan', 'richiesta_RichiestaRispostaAsincrona_TrasmissioneDrg_Msg', 'richiesta_RichiestaRispostaAsincrona_TrasmissioneDrg', 5);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (7, 'http://www.intemaweb.com/services/mobsan', 'richiesta_RichiestaRispostaAsincrona_CancellazioneRicovero_Msg', 'richiesta_RichiestaRispostaAsincrona_CancellazioneRicovero', 6);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (8, 'http://www.intemaweb.com/services/mobsan', 'richiesta_RichiestaRispostaAsincrona_CancellazioneDimissione_Msg', 'richiesta_RichiestaRispostaAsincrona_CancellazioneDimissione', 7);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (9, 'http://www.intemaweb.com/services/mobsan', 'risposta_RichiestaRispostaAsincrona_SegnalazioneRicovero_Msg', 'risposta_RichiestaRispostaAsincrona_SegnalazioneRicovero', 8);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (10, 'http://www.intemaweb.com/services/mobsan', 'risposta_RichiestaRispostaAsincrona_AggiornamentoMobilita_Msg', 'risposta_RichiestaRispostaAsincrona_AggiornamentoMobilita', 9);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (11, 'http://www.intemaweb.com/services/mobsan', 'risposta_RichiestaRispostaAsincrona_SegnalazioneDimissione_Msg', 'risposta_RichiestaRispostaAsincrona_SegnalazioneDimissione', 10);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (12, 'http://www.intemaweb.com/services/mobsan', 'risposta_RichiestaRispostaAsincrona_TrasmissioneDrg_Msg', 'risposta_RichiestaRispostaAsincrona_TrasmissioneDrg', 11);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (13, 'http://www.intemaweb.com/services/mobsan', 'risposta_RichiestaRispostaAsincrona_CancellazioneRicovero_Msg', 'risposta_RichiestaRispostaAsincrona_CancellazioneRicovero', 12);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (14, 'http://www.intemaweb.com/services/mobsan', 'risposta_RichiestaRispostaAsincrona_CancellazioneDimissione_Msg', 'risposta_RichiestaRispostaAsincrona_CancellazioneDimissione', 13);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (32768, 'http://www.intemaweb.com/services/fruizioneprestazioniambulatoriali', 'richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali_Msg', 'richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali', 32768);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (32769, 'http://www.intemaweb.com/services/fruizioneprestazioniambulatoriali', 'richiesta_RichiestaRispostaAsincrona_CancellazionePrestazioniAmbulatoriali_Msg', 'richiesta_RichiestaRispostaAsincrona_CancellazionePrestazioniAmbulatoriali', 32769);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (32770, 'http://www.intemaweb.com/services/fruizioneprestazioniambulatoriali', 'risposta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali_Msg', 'risposta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali', 32770);
INSERT INTO message (id, namespace, nome, types, operation_id) VALUES (32771, 'http://www.intemaweb.com/services/fruizioneprestazioniambulatoriali', 'risposta_RichiestaRispostaAsincrona_CancellazionePrestazioniAmbulatoriali_Msg', 'risposta_RichiestaRispostaAsincrona_CancellazionePrestazioniAmbulatoriali', 32771);


ALTER TABLE message ENABLE TRIGGER ALL;

--
-- Data for Name: messaggio; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE messaggio DISABLE TRIGGER ALL;



ALTER TABLE messaggio ENABLE TRIGGER ALL;

--
-- Data for Name: messaggiosbloccomanuale; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE messaggiosbloccomanuale DISABLE TRIGGER ALL;



ALTER TABLE messaggiosbloccomanuale ENABLE TRIGGER ALL;

--
-- Data for Name: operation; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE operation DISABLE TRIGGER ALL;

INSERT INTO operation (id, nome, profilodicollaborazione, porttype_id, messageout_id, messagein_id, messagefault_id) VALUES (1, 'RichiestaRispostaSincrona_IdentificazioneAssistito', 'SINCRONO', 1, 2, 1, NULL);
INSERT INTO operation (id, nome, profilodicollaborazione, porttype_id, messageout_id, messagein_id, messagefault_id) VALUES (2, 'RichiestaRispostaAsincrona_SegnalazioneRicovero', 'ASINCRONO', 2, NULL, 3, NULL);
INSERT INTO operation (id, nome, profilodicollaborazione, porttype_id, messageout_id, messagein_id, messagefault_id) VALUES (3, 'RichiestaRispostaAsincrona_AggiornamentoMobilita', 'ASINCRONO', 2, NULL, 4, NULL);
INSERT INTO operation (id, nome, profilodicollaborazione, porttype_id, messageout_id, messagein_id, messagefault_id) VALUES (4, 'RichiestaRispostaAsincrona_SegnalazioneDimissione', 'ASINCRONO', 2, NULL, 5, NULL);
INSERT INTO operation (id, nome, profilodicollaborazione, porttype_id, messageout_id, messagein_id, messagefault_id) VALUES (5, 'RichiestaRispostaAsincrona_TrasmissioneDrg', 'ASINCRONO', 2, NULL, 6, NULL);
INSERT INTO operation (id, nome, profilodicollaborazione, porttype_id, messageout_id, messagein_id, messagefault_id) VALUES (6, 'RichiestaRispostaAsincrona_CancellazioneRicovero', 'ASINCRONO', 2, NULL, 7, NULL);
INSERT INTO operation (id, nome, profilodicollaborazione, porttype_id, messageout_id, messagein_id, messagefault_id) VALUES (7, 'RichiestaRispostaAsincrona_CancellazioneDimissione', 'ASINCRONO', 2, NULL, 8, NULL);
INSERT INTO operation (id, nome, profilodicollaborazione, porttype_id, messageout_id, messagein_id, messagefault_id) VALUES (8, 'RichiestaRispostaAsincrona_SegnalazioneRicovero', 'ASINCRONO', 3, NULL, 9, NULL);
INSERT INTO operation (id, nome, profilodicollaborazione, porttype_id, messageout_id, messagein_id, messagefault_id) VALUES (9, 'RichiestaRispostaAsincrona_AggiornamentoMobilita', 'ASINCRONO', 3, NULL, 10, NULL);
INSERT INTO operation (id, nome, profilodicollaborazione, porttype_id, messageout_id, messagein_id, messagefault_id) VALUES (10, 'RichiestaRispostaAsincrona_SegnalazioneDimissione', 'ASINCRONO', 3, NULL, 11, NULL);
INSERT INTO operation (id, nome, profilodicollaborazione, porttype_id, messageout_id, messagein_id, messagefault_id) VALUES (11, 'RichiestaRispostaAsincrona_TrasmissioneDrg', 'ASINCRONO', 3, NULL, 12, NULL);
INSERT INTO operation (id, nome, profilodicollaborazione, porttype_id, messageout_id, messagein_id, messagefault_id) VALUES (12, 'RichiestaRispostaAsincrona_CancellazioneRicovero', 'ASINCRONO', 3, NULL, 13, NULL);
INSERT INTO operation (id, nome, profilodicollaborazione, porttype_id, messageout_id, messagein_id, messagefault_id) VALUES (13, 'RichiestaRispostaAsincrona_CancellazioneDimissione', 'ASINCRONO', 3, NULL, 14, NULL);
INSERT INTO operation (id, nome, profilodicollaborazione, porttype_id, messageout_id, messagein_id, messagefault_id) VALUES (32768, 'RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali', 'ASINCRONO', 32768, NULL, 32768, NULL);
INSERT INTO operation (id, nome, profilodicollaborazione, porttype_id, messageout_id, messagein_id, messagefault_id) VALUES (32769, 'RichiestaRispostaAsincrona_CancellazionePrestazioniAmbulatoriali', 'ASINCRONO', 32768, NULL, 32769, NULL);
INSERT INTO operation (id, nome, profilodicollaborazione, porttype_id, messageout_id, messagein_id, messagefault_id) VALUES (32770, 'RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali', 'ASINCRONO', 32769, NULL, 32770, NULL);
INSERT INTO operation (id, nome, profilodicollaborazione, porttype_id, messageout_id, messagein_id, messagefault_id) VALUES (32771, 'RichiestaRispostaAsincrona_CancellazionePrestazioniAmbulatoriali', 'ASINCRONO', 32769, NULL, 32771, NULL);


ALTER TABLE operation ENABLE TRIGGER ALL;

--
-- Data for Name: porttype; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE porttype DISABLE TRIGGER ALL;

INSERT INTO porttype (id, nome, ruolo, accordodicollaborazione_id) VALUES (1, 'ServizioIdentificazioneAssistito', 'EROGATORE', 1);
INSERT INTO porttype (id, nome, ruolo, accordodicollaborazione_id) VALUES (2, 'ServizioMobSan', 'EROGATORE', 2);
INSERT INTO porttype (id, nome, ruolo, accordodicollaborazione_id) VALUES (3, 'ServizioMobSan', 'FRUITORE', 2);
INSERT INTO porttype (id, nome, ruolo, accordodicollaborazione_id) VALUES (32768, 'ServizioFruizionePrestazioniAmbulatoriali', 'EROGATORE', 32768);
INSERT INTO porttype (id, nome, ruolo, accordodicollaborazione_id) VALUES (32769, 'ServizioFruizionePrestazioniAmbulatoriali', 'FRUITORE', 32768);


ALTER TABLE porttype ENABLE TRIGGER ALL;

--
-- Data for Name: query; Type: TABLE DATA; Schema: public; Owner: silvio
--

ALTER TABLE query DISABLE TRIGGER ALL;

INSERT INTO query (id, indirizzodb, nomedb, nomeutente, password, query, tipodb) VALUES (1, 'localhost', 'ap1fruitore', 'silvio', '**silvio**', NULL, 'PostgreSQL');
INSERT INTO query (id, indirizzodb, nomedb, nomeutente, password, query, tipodb) VALUES (2, 'localhost', 'ap1erogatore', 'silvio', '**silvio**', 'SELECT * FROM paziente join medico on paziente.medico = medico.id', 'PostgreSQL');
INSERT INTO query (id, indirizzodb, nomedb, nomeutente, password, query, tipodb) VALUES (32769, 'localhost', 'ap1erogatore', 'silvio', '**silvio**', NULL, 'PostgreSQL');
INSERT INTO query (id, indirizzodb, nomedb, nomeutente, password, query, tipodb) VALUES (32768, 'localhost', 'ap1fruitore', 'silvio', '**silvio**', 'SELECT * FROM RICETTA JOIN PAZIENTE ON RICETTA.PAZIENTE = PAZIENTE.ID WHERE RICETTA.NUMERO = ?', 'PostgreSQL');


ALTER TABLE query ENABLE TRIGGER ALL;
