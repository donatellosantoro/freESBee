<?xml version="1.0" encoding="UTF-8"?>
<!--
This file was generated by Altova MapForce 2008

YOU SHOULD NOT MODIFY THIS FILE, BECAUSE IT WILL BE
OVERWRITTEN WHEN YOU RE-RUN CODE GENERATION.

Refer to the Altova MapForce Documentation for further details.
http://www.altova.com/mapforce
-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:tns="http://progettoicar.it/AP3interprotoQry/types" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns="http://progettoicar.it/AP3interprotoQry/types" exclude-result-prefixes="tns xs">
	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/datiRisposta">
		<risposta_RichiestaRispostaSincrona_InterrogaDoc>
			<xsl:attribute name="xsi:schemaLocation">
				<xsl:value-of select="'http://progettoicar.it/AP3interprotoQry/types C:/Users/Smx/Desktop/mapping_ap3/InterrogaDoc/targetRispostaErogatore.xsd'"/>
			</xsl:attribute>
			<AP3InterrogaDocResponse>
				<xsl:for-each select="datiCompleti">
					<xsl:for-each select="datiDB">
						<xsl:for-each select="documento.mime_type">
							<mimeType xmlns="">
								<xsl:value-of select="."/>
							</mimeType>
						</xsl:for-each>
					</xsl:for-each>
				</xsl:for-each>
				<xsl:for-each select="datiCompleti">
					<xsl:for-each select="datiDB">
						<xsl:for-each select="documento.nome_documento">
							<nomeDocInf xmlns="">
								<xsl:value-of select="."/>
							</nomeDocInf>
						</xsl:for-each>
					</xsl:for-each>
				</xsl:for-each>
				<xsl:for-each select="datiCompleti">
					<xsl:for-each select="datiDB">
						<xsl:for-each select="documento.contenuto_documento">
							<base64 xmlns="">
								<xsl:value-of select="."/>
							</base64>
						</xsl:for-each>
					</xsl:for-each>
				</xsl:for-each>
			</AP3InterrogaDocResponse>
		</risposta_RichiestaRispostaSincrona_InterrogaDoc>
	</xsl:template>
</xsl:stylesheet>
