<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns="http://www.intemaweb.com/iron/datatypes">
	<xsl:output method="text"/>
	<xsl:template match="/">
	<xsl:for-each select ="/soapenv:Envelope/soapenv:Body/getRilevazioni/rilevazioniXML/rilevazionitype/rilevazioneitem">
	INSERT INTO rilevazione (idrilevazione, codimpianto, datarilevazione, codistatregione, superifcie, numero_addetti, numero_dipendenti, tipologia, att_commericale, autolavaggio, self_services, stato, bandiera) VALUES( ${id.rilevazione<xsl:apply-templates select = "./cod_impianto"/>},
 <xsl:apply-templates select = "./cod_impianto"/>,
 '<xsl:value-of select="/soapenv:Envelope/soapenv:Body/getRilevazioni/rilevazioniXML/@data_rilevazione"/>',
		   '<xsl:value-of select="/soapenv:Envelope/soapenv:Body/getRilevazioni/rilevazioniXML/@codistatregione"/>',	
		   <xsl:apply-templates select = "./superficie"/>,
		   <xsl:apply-templates select = "./addetti/n"/>,
		   <xsl:apply-templates select = "./dipendenti/n"/>,
		   '<xsl:apply-templates select = "./tipologia"/>',
		   '<xsl:apply-templates select = "./att_commerciali"/>',
		   '<xsl:apply-templates select = "./autolavaggio"/>',
		   '<xsl:apply-templates select = "./self_service"/>',
		   '<xsl:apply-templates select = "./stato"/>',
		   <xsl:choose>
		   <xsl:when test="./bandiera/marchio/nome">'<xsl:value-of select="./bandiera/marchio/nome"/>'
		   </xsl:when>
		   <xsl:when test="./bandiera/anonimo">'anonimo'
		   </xsl:when>
		    <xsl:when test="./bandiera/non_rilevato">'non_rilevato'
		    </xsl:when>
		   </xsl:choose>
		   ) ${endStatement} 
<xsl:for-each select ="./pagamenti_disponibili/pagamento">INSERT INTO rilevazione_has_pagamento (idrilevazione, pagamento) VALUES( ${refId.rilevazione<xsl:apply-templates select = "../.././cod_impianto"/>}, ' <xsl:value-of select = "."/>') ${endStatement}    
</xsl:for-each>
<xsl:for-each select ="./officine_disponibili/officina">INSERT INTO rilevazione_has_officina (idrilevazione, officina) VALUES( ${refId.rilevazione<xsl:apply-templates select = "../.././cod_impianto"/>}, '<xsl:value-of select = "."/>') ${endStatement} 
</xsl:for-each>
<xsl:for-each select ="./carburanti_disponibili/carburantedisponibile /gpl">
INSERT INTO rilevazione_has_carburante (idrilevazione, carburante, serbatoio) VALUES( ${refId.rilevazione<xsl:apply-templates select = "../../.././cod_impianto"/>}, 'gpl', <xsl:value-of select = "."/>) ${endStatement} 
</xsl:for-each>
<xsl:for-each select ="./carburanti_disponibili/carburantedisponibile /benzina">
INSERT INTO rilevazione_has_carburante (idrilevazione, carburante, serbatoio) VALUES( ${refId.rilevazione<xsl:apply-templates select = "../../.././cod_impianto"/>},'benzina', <xsl:value-of select = "."/>) ${endStatement} 
</xsl:for-each>
<xsl:for-each select ="./carburanti_disponibili/carburantedisponibile /gasolio">
INSERT INTO rilevazione_has_carburante (idrilevazione, carburante, serbatoio) VALUES( ${refId.rilevazione<xsl:apply-templates select = "../../.././cod_impianto"/>},  'gasolio', <xsl:value-of select = "."/>) ${endStatement} 
</xsl:for-each>
<xsl:for-each select ="./carburanti_disponibili/carburantedisponibile /metano">
INSERT INTO rilevazione_has_carburante (idrilevazione, carburante, serbatoio) VALUES( ${refId.rilevazione<xsl:apply-templates select = "../../.././cod_impianto"/>}, 'metano', <xsl:value-of select = "."/>) ${endStatement} 
</xsl:for-each>
 </xsl:for-each>
</xsl:template>
</xsl:stylesheet>