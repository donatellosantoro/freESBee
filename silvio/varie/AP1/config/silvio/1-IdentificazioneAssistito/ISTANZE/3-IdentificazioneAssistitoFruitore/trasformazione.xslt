<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns="http://www.intemaweb.com/iron/datatypes" exclude-result-prefixes="xs">
	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/datiCompleti">
		<richiesta_RichiestaRispostaSincrona_IdentificazioneAssistito>
			<xsl:attribute name="xsi:schemaLocation">
				<xsl:value-of select="'http://www.intemaweb.com/iron/datatypes C:/Users/Donatello/Desktop/AP1/mapping/IdentificazioneAssistitoAS/fruitore/target.xsd'"/>
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
						<xsl:for-each select="CodiceAsl">
							<CodiceAsl>
								<xsl:value-of select="."/>
							</CodiceAsl>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:for-each select="datiCostanti">
						<xsl:for-each select="IdOperatore">
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
					<xsl:for-each select="datiInterattivi">
						<xsl:for-each select="IdOperatoreDestinatario">
							<IdOperatore>
								<xsl:value-of select="."/>
							</IdOperatore>
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
			<IdentificazioneAssistito>
				<xsl:for-each select="datiInterattivi">
					<xsl:for-each select="CodiceAslResidenza">
						<CodiceAslResidenza>
							<xsl:value-of select="."/>
						</CodiceAslResidenza>
					</xsl:for-each>
				</xsl:for-each>
				<xsl:for-each select="datiInterattivi">
					<xsl:for-each select="CodiceAslAssistenza">
						<CodiceAslAssistenza>
							<xsl:value-of select="."/>
						</CodiceAslAssistenza>
					</xsl:for-each>
				</xsl:for-each>
				<DataRiconoscimento>
					<xsl:value-of select="'1967-08-13'"/>
				</DataRiconoscimento>
				<xsl:for-each select="datiInterattivi">
					<xsl:for-each select="CodiceFiscale">
						<CodiceFiscale>
							<xsl:value-of select="."/>
						</CodiceFiscale>
					</xsl:for-each>
				</xsl:for-each>
			</IdentificazioneAssistito>
		</richiesta_RichiestaRispostaSincrona_IdentificazioneAssistito>
	</xsl:template>
</xsl:stylesheet>
