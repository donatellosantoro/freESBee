<?xml version="1.0" encoding="UTF-8"?>
<!--
This file was generated by Altova MapForce 2008

YOU SHOULD NOT MODIFY THIS FILE, BECAUSE IT WILL BE
OVERWRITTEN WHEN YOU RE-RUN CODE GENERATION.

Refer to the Altova MapForce Documentation for further details.
http://www.altova.com/mapforce
-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns="http://progettoicar.it/AP3interprotoQry/types" exclude-result-prefixes="xs">
	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/datiCompleti">
		<richiesta_RichiestaRispostaSincrona_InterrogaDoc>
			<xsl:attribute name="xsi:schemaLocation">
				<xsl:value-of select="'http://progettoicar.it/AP3interprotoQry/types C:/Users/Smx/Desktop/mapping_ap3/InterrogaDoc/targetRichiestaFruitore.xsd'"/>
			</xsl:attribute>
			<AP3InterrogaDocRequest>
				<codiceRichiedente xmlns="">
					<xsl:for-each select="datiInterattivi">
						<xsl:for-each select="codice_amministrazione_richiedente">
							<codiceAmm>
								<xsl:value-of select="."/>
							</codiceAmm>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:for-each select="datiInterattivi">
						<xsl:for-each select="codice_aoo_richiedente">
							<codiceAoo>
								<xsl:value-of select="."/>
							</codiceAoo>
						</xsl:for-each>
					</xsl:for-each>
				</codiceRichiedente>
				<codiceInterrogato xmlns="">
					<xsl:for-each select="datiInterattivi">
						<xsl:for-each select="codice_amministrazione_interrogato">
							<codiceAmm>
								<xsl:value-of select="."/>
							</codiceAmm>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:for-each select="datiInterattivi">
						<xsl:for-each select="codice_aoo_interrogato">
							<codiceAoo>
								<xsl:value-of select="."/>
							</codiceAoo>
						</xsl:for-each>
					</xsl:for-each>
				</codiceInterrogato>
				<xsl:for-each select="datiInterattivi">
					<xsl:for-each select="codice_doc_inf">
						<codiceDocInf xmlns="">
							<xsl:value-of select="."/>
						</codiceDocInf>
					</xsl:for-each>
				</xsl:for-each>
			</AP3InterrogaDocRequest>
		</richiesta_RichiestaRispostaSincrona_InterrogaDoc>
	</xsl:template>
</xsl:stylesheet>