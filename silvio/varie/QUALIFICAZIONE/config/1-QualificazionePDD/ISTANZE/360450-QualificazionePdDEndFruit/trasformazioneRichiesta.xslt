<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:tns="http://sica.spcoop.it/servizi/QualificazionePDDWS/types" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns="http://sica.spcoop.it/servizi/QualificazionePDDWS" xmlns:types="http://sica.spcoop.it/servizi/QualificazionePDDWS/types" exclude-result-prefixes="tns xs">
	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/">
		<types:richiesta_RichiestaRispostaSincrona_end>
			<types:TokenSessione>
				<xsl:value-of select="/datiCompleti/datiInterattivi/TokenSessione"/>
			</types:TokenSessione>
			<types:isInError>0</types:isInError>
		</types:richiesta_RichiestaRispostaSincrona_end>
	</xsl:template>
</xsl:stylesheet>