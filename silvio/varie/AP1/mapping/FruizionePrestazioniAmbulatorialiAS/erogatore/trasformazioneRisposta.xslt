<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns="http://www.intemaweb.com/iron/datatypes" exclude-result-prefixes="xs">
	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/datiRisposta">
		<risposta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali>
			<xsl:attribute name="xsi:schemaLocation">
				<xsl:value-of select="'http://www.intemaweb.com/iron/datatypes D:/svn/icar/codice/silvio/varie/AP1/mapping/FruizionePrestazioniAmbulatorialiAS/erogatore/targetRisposta.xsd'"/>
			</xsl:attribute>
			<Intestazione>
				<xsl:for-each select="datiRichiesta">
					<xsl:for-each select="richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali">
						<xsl:for-each select="Intestazione">
							<xsl:for-each select="InfoDestinatario">
								<InfoMittente>
									<xsl:for-each select="Regione">
										<Regione>
											<xsl:value-of select="."/>
										</Regione>
									</xsl:for-each>
									<xsl:for-each select="CodiceAsl">
										<CodiceAsl>
											<xsl:value-of select="."/>
										</CodiceAsl>
									</xsl:for-each>
									<xsl:for-each select="IdOperatore">
										<IdOperatore>
											<xsl:value-of select="."/>
										</IdOperatore>
									</xsl:for-each>
								</InfoMittente>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</xsl:for-each>
				<xsl:for-each select="datiRichiesta">
					<xsl:for-each select="richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali">
						<xsl:for-each select="Intestazione">
							<xsl:for-each select="InfoMittente">
								<InfoDestinatario>
									<xsl:for-each select="Regione">
										<Regione>
											<xsl:value-of select="."/>
										</Regione>
									</xsl:for-each>
									<xsl:for-each select="CodiceAsl">
										<CodiceAsl>
											<xsl:value-of select="."/>
										</CodiceAsl>
									</xsl:for-each>
									<xsl:for-each select="IdOperatore">
										<IdOperatore>
											<xsl:value-of select="."/>
										</IdOperatore>
									</xsl:for-each>
								</InfoDestinatario>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</xsl:for-each>
				<xsl:for-each select="datiRichiesta">
					<xsl:for-each select="richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali">
						<xsl:for-each select="Intestazione">
							<xsl:for-each select="DataOra">
								<DataOra>
									<xsl:value-of select="."/>
								</DataOra>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</xsl:for-each>
				<xsl:for-each select="datiRichiesta">
					<xsl:for-each select="richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali">
						<xsl:for-each select="Intestazione">
							<xsl:for-each select="IdMessaggio">
								<IdMessaggio>
									<xsl:value-of select="."/>
								</IdMessaggio>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</xsl:for-each>
				<xsl:for-each select="datiRichiesta">
					<xsl:for-each select="richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali">
						<xsl:for-each select="Intestazione">
							<xsl:for-each select="IdComunicazione">
								<IdComunicazione>
									<xsl:value-of select="."/>
								</IdComunicazione>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</xsl:for-each>
			</Intestazione>
			<xsl:for-each select="datiCompleti">
				<xsl:for-each select="datiCostanti">
					<xsl:for-each select="conferma">
						<SegnalazioneFruizionePrestazioniAmbulatoriali>
							<xsl:value-of select="."/>
						</SegnalazioneFruizionePrestazioniAmbulatoriali>
					</xsl:for-each>
				</xsl:for-each>
			</xsl:for-each>
		</risposta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali>
	</xsl:template>
</xsl:stylesheet>
