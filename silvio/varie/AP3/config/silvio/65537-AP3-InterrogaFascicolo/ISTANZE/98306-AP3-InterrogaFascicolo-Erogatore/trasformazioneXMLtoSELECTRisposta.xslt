<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns="http://progettoicar.it/AP3interprotoQry/types">
	<xsl:output method="text"/>
	<xsl:template match="/">
		SELECT * FROM fascicolo JOIN classifica ON fascicolo.id_classifica = classifica.id JOIN documentofascicolo ON documentofascicolo.id_fascicolo = fascicolo.id JOIN areaorganizzativaomogenea ON documentofascicolo.id_areaorganizzativaomogenea = areaorganizzativaomogenea.id JOIN amministrazione ON areaorganizzativaomogenea.id_amministrazione = amministrazione.id JOIN documento ON documento.id_documentofascicolo = documentofascicolo.id JOIN responsabiletrattamento ON responsabiletrattamento.id_documentofascicolo = documentofascicolo.id WHERE fascicolo.anno = 
		'<xsl:value-of select="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaSincrona_InterrogaFasc/AP3InterrogaFascRequest/estremiProtocolloDest/anno"/>' 
		AND fascicolo.numero = 
		'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaSincrona_InterrogaFasc/AP3InterrogaFascRequest/estremiProtocolloDest/numero"/>' 
		AND amministrazione.codice = 
		'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaSincrona_InterrogaFasc/AP3InterrogaFascRequest/codiceInterrogato/codiceAmm"/>' 
		AND areaorganizzativaomogenea.codice_aoo = 
		'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaSincrona_InterrogaFasc/AP3InterrogaFascRequest/codiceInterrogato/codiceAoo"/>' 
	</xsl:template>
</xsl:stylesheet>