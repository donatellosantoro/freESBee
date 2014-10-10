<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:tns="http://sica.spcoop.it/servizi/QualificazionePDDWS/types" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns="http://sica.spcoop.it/servizi/QualificazionePDDWS" xmlns:types="http://sica.spcoop.it/servizi/QualificazionePDDWS/types" exclude-result-prefixes="tns xs">
	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/datiRisposta">
		<types:risposta_RichiestaRispostaSincrona_end>
			<xsl:attribute name="xsi:schemaLocation">
				<xsl:value-of select="'http://sica.spcoop.it/servizi/QualificazionePDDWS/types targetRispostaErogatore.xsd'"/>
			</xsl:attribute>
			<types:Esito>
				<xsl:value-of select="'RISPOSTA_OK'"/>
			</types:Esito>
			<xsl:for-each select="datiRichiesta/tns:richiesta_RichiestaRispostaSincrona_end">
				<xsl:for-each select="tns:TokenSessione">
					<types:TokenSessione>
						<xsl:value-of select="."/>
					</types:TokenSessione>
				</xsl:for-each>
			</xsl:for-each>
		</types:risposta_RichiestaRispostaSincrona_end>
	</xsl:template>
</xsl:stylesheet>