<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns="http://progettoicar.it/AP3interprotoQry/types">
	<xsl:output method="text"/>
	<xsl:template match="/">
		SELECT * FROM documento WHERE codice_documento = 
		'<xsl:value-of select="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaSincrona_InterrogaDoc/AP3InterrogaDocRequest/codiceDocInf"/>'
	</xsl:template>
</xsl:stylesheet>