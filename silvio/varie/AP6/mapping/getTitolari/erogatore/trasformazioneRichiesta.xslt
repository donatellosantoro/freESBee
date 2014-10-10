<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns="http://www.intemaweb.com/iron/datatypes">
	<xsl:output method="text"/>
		<xsl:template match="/">
	<xsl:for-each select ="/soapenv:Envelope/soapenv:Body/getTitolari/titolariXML/titolaritype/titolareitem">INSERT INTO titolare (idtitolare, datarilevazione, codistatregione, iva_cf, denominazione, via, codistat_pvcomune, cap) VALUES( ${id.titolare},
	<!-- elemento radice -->	
		   '<xsl:value-of select="/soapenv:Envelope/soapenv:Body/getTitolari/titolariXML/@data_rilevazione"/>',
		   '<xsl:value-of select="/soapenv:Envelope/soapenv:Body/getTitolari/titolariXML/@codistatregione"/>',	
		   '<xsl:value-of select="/soapenv:Envelope/soapenv:Body/getTitolari/titolariXML/titolaritype/titolareitem/iva_cf"/>',
		   '<xsl:value-of select="/soapenv:Envelope/soapenv:Body/getTitolari/titolariXML/titolaritype/titolareitem/denominazione"/>',
		   '<xsl:value-of select="/soapenv:Envelope/soapenv:Body/getTitolari/titolariXML/titolaritype/titolareitem/indirizzo/via"/>',
		   '<xsl:value-of select="/soapenv:Envelope/soapenv:Body/getTitolari/titolariXML/titolaritype/titolareitem/indirizzo/codistat_pvcomune"/>',
		   <xsl:value-of select="/soapenv:Envelope/soapenv:Body/getTitolari/titolariXML/titolaritype/titolareitem/indirizzo/cap"/>) ${endStatement}  
	</xsl:for-each>	
	</xsl:template>
</xsl:stylesheet>

  