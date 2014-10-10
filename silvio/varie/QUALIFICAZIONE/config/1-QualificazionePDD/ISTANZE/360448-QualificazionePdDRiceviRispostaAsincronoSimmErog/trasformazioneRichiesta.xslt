<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:tns="http://sica.spcoop.it/servizi/QualificazionePDDWS/types" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns="http://sica.spcoop.it/servizi/QualificazionePDDWS" xmlns:types="http://sica.spcoop.it/servizi/QualificazionePDDWS/types" exclude-result-prefixes="tns xs">
	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/datiRisposta">
		<types:risposta_NotificazioneRisposta_riceviRispostaTestAsincronoSimmetrico>
			<xsl:attribute name="xsi:schemaLocation">
				<xsl:value-of select="'http://sica.spcoop.it/servizi/QualificazionePDDWS/types targetRispostaErogatore.xsd'"/>
			</xsl:attribute>
			<types:PresaInCarico>
				<xsl:value-of select="'OK'"/>
			</types:PresaInCarico>
			<xsl:for-each select="datiRichiesta/tns:segnalazione_NotificazioneRisposta_riceviRispostaTestAsincronoSimmetrico">
				<xsl:for-each select="tns:TokenSessione">
					<types:TokenSessione>
						<xsl:value-of select="."/>
					</types:TokenSessione>
				</xsl:for-each>
			</xsl:for-each>
		</types:risposta_NotificazioneRisposta_riceviRispostaTestAsincronoSimmetrico>
	</xsl:template>
</xsl:stylesheet>