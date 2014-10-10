<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns="http://www.intemaweb.com/iron/datatypes">
	<xsl:output method="text"/>
	<xsl:template match="/">
	SELECT * FROM paziente join medico on paziente.medico = medico.id WHERE paziente.codice_fiscale = 
			'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaSincrona_IdentificazioneAssistito/IdentificazioneAssistito/CodiceFiscale"/>'
	</xsl:template>
   	<xsl:template match="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaSincrona_IdentificazioneAssistito/IdentificazioneAssistito/CodiceFiscale">
<xsl:value-of select="text()"/> 
   	</xsl:template>
</xsl:stylesheet>
