<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:tns="http://sica.spcoop.it/servizi/QualificazionePDDWS/types" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:ns0="http://sica.spcoop.it/servizi/QualificazionePDDWS/types" exclude-result-prefixes="tns xs">
	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/datiRisposta">
		<ns0:risposta_RichiestaRispostaSincrona_testSincrono>
			<ns0:Esito>
				<xsl:value-of select="'RISPOSTA_OK'"/>
			</ns0:Esito>
			<xsl:for-each select="datiRichiesta/tns:richiesta_RichiestaRispostaSincrona_testSincrono">
				<xsl:for-each select="tns:TokenSessione">
					<ns0:TokenSessione>
						<xsl:value-of select="."/>
					</ns0:TokenSessione>
				</xsl:for-each>
			</xsl:for-each>
		</ns0:risposta_RichiestaRispostaSincrona_testSincrono>
	</xsl:template>
</xsl:stylesheet>