<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:typ="http://sica.spcoop.it/servizi/QualificazionePDDWS/types" exclude-result-prefixes="tns xs">
	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/">
      <typ:richiesta_RichiestaRispostaSincrona_getTraccia>
         <typ:TokenSessione><xsl:value-of select="/datiCompleti/datiInterattivi/TokenSessione"/></typ:TokenSessione>
      </typ:richiesta_RichiestaRispostaSincrona_getTraccia>
	</xsl:template>
</xsl:stylesheet>
