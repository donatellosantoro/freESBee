<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns="http://progettoicar.it/AP3interprotoQry/types" exclude-result-prefixes="xs">
	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/datiCompleti">
		<richiesta_RichiestaRispostaSincrona_InterrogaFasc>
			<xsl:attribute name="xsi:schemaLocation">
				<xsl:value-of select="'http://progettoicar.it/AP3interprotoQry/types ./targetSQLtoXMLRichiesta.xsd'"/>
			</xsl:attribute>
			<AP3InterrogaFascRequest>
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
						<xsl:value-of select="datiCostanti/codiceAmm"/>
					</codiceAmm>
					<codiceAoo>
						<xsl:value-of select="datiCostanti/codiceAoo"/>
					</codiceAoo>
				</codiceInterrogato>
				<estremiProtocolloDest>
					<anno>
						<xsl:value-of select="datiCostanti/anno"/>
					</anno>
					<numero>
						<xsl:value-of select="datiCostanti/numero"/>
					</numero>
				</estremiProtocolloDest>
			</AP3InterrogaFascRequest>
		</richiesta_RichiestaRispostaSincrona_InterrogaFasc>
	</xsl:template>
</xsl:stylesheet>
