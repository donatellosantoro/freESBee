<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns="http://progettoicar.it/AP3interprotoQry/types" exclude-result-prefixes="xs">
	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/datiRisposta">
		<risposta_RichiestaRispostaSincrona_InterrogaDoc>
			<xsl:attribute name="xsi:schemaLocation">
				<xsl:value-of select="'http://progettoicar.it/AP3interprotoQry/types ./targetRispostaSQLtoXML.xsd'"/>
			</xsl:attribute>
			<AP3InterrogaDocResponse>
				<mimeType>
					<xsl:value-of select="datiCompleti/datiDB/documento.documento"/>
				</mimeType>
				<nomeDocInf>
					<xsl:value-of select="datiCompleti/datiDB/documento.nome_documento"/>
				</nomeDocInf>
			</AP3InterrogaDocResponse>
		</risposta_RichiestaRispostaSincrona_InterrogaDoc>
	</xsl:template>
</xsl:stylesheet>