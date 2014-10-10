<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns="http://progettoicar.it/AP3interprotoQry/types">
	<xsl:output method="text"/>
	<xsl:template match="/">
		INSERT INTO documenti (mittente, esito, mimetype, nome_doc_inf, contenuto_documento) values(
		'R_SICILI',
		'true',
		'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_InterrogaDoc/AP3InterrogaDocResponse/mimetype"/>',
		'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_InterrogaDoc/AP3InterrogaDocResponse/nomeDocInf"/>',
		'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_InterrogaDoc/AP3InterrogaDocResponse/base64"/>'
		)  ${endStatement}
	</xsl:template>
</xsl:stylesheet>