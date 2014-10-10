<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns="http://www.intemaweb.com/iron/datatypes" exclude-result-prefixes="xs">
	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/datiCompleti">
		<richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali>
			<xsl:attribute name="xsi:schemaLocation">
				<xsl:value-of select="'http://www.intemaweb.com/iron/datatypes D:/svn/icar/codice/silvio/varie/AP1/mapping/FruizionePrestazioniAmbulatorialiAS/fruitore/target.xsd'"/>
			</xsl:attribute>
			<Intestazione>
				<InfoMittente>
					<xsl:for-each select="datiCostanti">
						<xsl:for-each select="RegioneMittente">
							<Regione>
								<xsl:value-of select="."/>
							</Regione>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:for-each select="datiCostanti">
						<xsl:for-each select="CodiceAslMittente">
							<CodiceAsl>
								<xsl:value-of select="."/>
							</CodiceAsl>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:for-each select="datiCostanti">
						<xsl:for-each select="IdOperatoreMittente">
							<IdOperatore>
								<xsl:value-of select="."/>
							</IdOperatore>
						</xsl:for-each>
					</xsl:for-each>
				</InfoMittente>
				<InfoDestinatario>
					<xsl:for-each select="datiInterattivi">
						<xsl:for-each select="RegioneDestinatario">
							<Regione>
								<xsl:value-of select="."/>
							</Regione>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:for-each select="datiInterattivi">
						<xsl:for-each select="CodiceAslDestinatario">
							<CodiceAsl>
								<xsl:value-of select="."/>
							</CodiceAsl>
						</xsl:for-each>
					</xsl:for-each>
				</InfoDestinatario>
				<xsl:for-each select="datiCostanti">
					<xsl:for-each select="DataOra">
						<DataOra>
							<xsl:value-of select="."/>
						</DataOra>
					</xsl:for-each>
				</xsl:for-each>
				<xsl:for-each select="datiCostanti">
					<xsl:for-each select="IdMessaggio">
						<IdMessaggio>
							<xsl:value-of select="."/>
						</IdMessaggio>
					</xsl:for-each>
				</xsl:for-each>
				<xsl:for-each select="datiCostanti">
					<xsl:for-each select="IdComunicazione">
						<IdComunicazione>
							<xsl:value-of select="."/>
						</IdComunicazione>
					</xsl:for-each>
				</xsl:for-each>
			</Intestazione>
			<SegnalazioneFruizionePrestazioniAmbulatoriali>
				<DatiAnagrafici>
					<xsl:for-each select="datiDB">
						<xsl:for-each select="paziente.codice_fiscale">
							<CodiceFiscale>
								<xsl:value-of select="."/>
							</CodiceFiscale>
						</xsl:for-each>
					</xsl:for-each>
					<DatiAnagraficiBase>
						<xsl:for-each select="datiDB">
							<xsl:for-each select="paziente.cognome">
								<Cognome>
									<xsl:value-of select="."/>
								</Cognome>
							</xsl:for-each>
						</xsl:for-each>
						<xsl:for-each select="datiDB">
							<xsl:for-each select="paziente.nome">
								<Nome>
									<xsl:value-of select="."/>
								</Nome>
							</xsl:for-each>
						</xsl:for-each>
						<xsl:for-each select="datiDB">
							<xsl:for-each select="paziente.data_nascita">
								<DataDiNascita>
									<xsl:value-of select="."/>
								</DataDiNascita>
							</xsl:for-each>
						</xsl:for-each>
						<xsl:for-each select="datiDB">
							<xsl:for-each select="paziente.comune_residenza">
								<ComuneNascita>
									<xsl:value-of select="."/>
								</ComuneNascita>
							</xsl:for-each>
						</xsl:for-each>
					</DatiAnagraficiBase>
					<xsl:for-each select="datiDB">
						<xsl:for-each select="paziente.sesso">
							<Sesso>
								<xsl:value-of select="."/>
							</Sesso>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:for-each select="datiDB">
						<xsl:for-each select="paziente.comune_residenza">
							<ComuneResidenza>
								<xsl:value-of select="."/>
							</ComuneResidenza>
						</xsl:for-each>
					</xsl:for-each>
				</DatiAnagrafici>
				<DatiPrestazioniAmbulatoriali>
					<DatiRicetta>
						<xsl:for-each select="datiDB">
							<xsl:for-each select="ricetta.numero">
								<NumeroRicetta>
									<xsl:value-of select="."/>
								</NumeroRicetta>
							</xsl:for-each>
						</xsl:for-each>
						<xsl:for-each select="datiDB">
							<xsl:for-each select="ricetta.diagnosi">
								<Diagnosi>
									<xsl:value-of select="."/>
								</Diagnosi>
							</xsl:for-each>
						</xsl:for-each>
						<xsl:for-each select="datiDB">
							<xsl:for-each select="ricetta.data">
								<Data>
									<xsl:value-of select="."/>
								</Data>
							</xsl:for-each>
						</xsl:for-each>
						<xsl:for-each select="datiDB">
							<xsl:for-each select="ricetta.posizione_utente_ticket">
								<PosizioneUtenteTicket>
									<xsl:value-of select="."/>
								</PosizioneUtenteTicket>
							</xsl:for-each>
						</xsl:for-each>
						<xsl:for-each select="datiDB">
							<xsl:for-each select="ricetta.importo_ticket">
								<ImportoTicket>
									<xsl:value-of select="."/>
								</ImportoTicket>
							</xsl:for-each>
						</xsl:for-each>
						<xsl:for-each select="datiDB">
							<xsl:for-each select="ricetta.importo_totale">
								<ImportoTotale>
									<xsl:value-of select="."/>
								</ImportoTotale>
							</xsl:for-each>
						</xsl:for-each>
					</DatiRicetta>
					<DatiPrestazioniRicetta>
						<xsl:for-each select="datiDB">
							<xsl:for-each select="ricetta.codice_struttura_erogatrice">
								<CodiceStrutturaErogatrice>
									<xsl:value-of select="."/>
								</CodiceStrutturaErogatrice>
							</xsl:for-each>
						</xsl:for-each>
						<Prestazione>
							<xsl:for-each select="datiDB">
								<xsl:for-each select="ricetta.codice_prestazione">
									<CodicePrestazione>
										<xsl:value-of select="."/>
									</CodicePrestazione>
								</xsl:for-each>
							</xsl:for-each>
							<xsl:for-each select="datiDB">
								<xsl:for-each select="ricetta.codifica_nomenclatore">
									<CodificaNomenclatore>
										<xsl:value-of select="."/>
									</CodificaNomenclatore>
								</xsl:for-each>
							</xsl:for-each>
							<xsl:for-each select="datiDB">
								<xsl:for-each select="ricetta.quantita">
									<Quantita>
										<xsl:value-of select="."/>
									</Quantita>
								</xsl:for-each>
							</xsl:for-each>
							<xsl:for-each select="datiDB">
								<xsl:for-each select="ricetta.importo">
									<Importo>
										<xsl:value-of select="."/>
									</Importo>
								</xsl:for-each>
							</xsl:for-each>
						</Prestazione>
					</DatiPrestazioniRicetta>
				</DatiPrestazioniAmbulatoriali>
			</SegnalazioneFruizionePrestazioniAmbulatoriali>
		</richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali>
	</xsl:template>
</xsl:stylesheet>
