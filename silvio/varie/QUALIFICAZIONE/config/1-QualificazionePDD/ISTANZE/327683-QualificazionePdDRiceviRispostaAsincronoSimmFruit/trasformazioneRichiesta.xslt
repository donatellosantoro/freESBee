<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:tns="http://sica.spcoop.it/servizi/QualificazionePDDWS/types" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns="http://sica.spcoop.it/servizi/QualificazionePDDWS" xmlns:types="http://sica.spcoop.it/servizi/QualificazionePDDWS/types" exclude-result-prefixes="tns xs">
	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/">
		<types:segnalazione_NotificazioneRisposta_riceviRispostaTestAsincronoSimmetrico>
			<types:Esito>RISPOSTA_OK</types:Esito> 
			<types:TokenSessione>
				<xsl:value-of select="/datiCompleti/datiInterattivi/TokenSessione"/>
			</types:TokenSessione>
		</types:segnalazione_NotificazioneRisposta_riceviRispostaTestAsincronoSimmetrico>
	</xsl:template>
</xsl:stylesheet>