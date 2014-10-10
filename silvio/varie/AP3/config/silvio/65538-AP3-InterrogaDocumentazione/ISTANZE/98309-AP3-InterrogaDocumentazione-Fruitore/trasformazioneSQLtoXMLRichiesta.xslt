<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns="http://progettoicar.it/AP3interprotoQry/types" exclude-result-prefixes="xs">
	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/datiCompleti">
		<richiesta_RichiestaRispostaSincrona_InterrogaDoc>
			<xsl:attribute name="xsi:schemaLocation">
				<xsl:value-of select="'http://progettoicar.it/AP3interprotoQry/types ./targetRichiestaSQLtoXML.xsd'"/>
			</xsl:attribute>
			<AP3InterrogaDocRequest>
				<codiceRichiedente>
					<codiceAmm>
						<xsl:value-of select="datiDB/amministrazione.codice"/>
					</codiceAmm>
					<codiceAoo>
						<xsl:value-of select="datiDB/areaorganizzativaomogenea.codice_aoo"/>
					</codiceAoo>
				</codiceRichiedente>
				<codiceInterrogato>
					<codiceAmm>
						<xsl:value-of select="datiCostanti/codiceAmmInterrogato"/>
					</codiceAmm>
					<codiceAoo>
						<xsl:value-of select="datiCostanti/codiceAooInterrogato"/>
					</codiceAoo>
				</codiceInterrogato>
				<codiceDocInf>
					<xsl:value-of select="datiCostanti/codiceDocInf"/>
				</codiceDocInf>
			</AP3InterrogaDocRequest>
		</richiesta_RichiestaRispostaSincrona_InterrogaDoc>
	</xsl:template>
</xsl:stylesheet>
