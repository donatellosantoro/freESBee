<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns="http://www.progettoicar.it/ipa/types" exclude-result-prefixes="xs">
	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/datiCompleti">
		<richiesta_RichiestaRispostaAsincrona_AggiornamentoIPA>
			<xsl:attribute name="xsi:schemaLocation">
				<xsl:value-of select="'http://www.progettoicar.it/ipa/types ./targetSQLtoXML.xsd'"/>
			</xsl:attribute>
			<amministrazione>
				<nome>
					<xsl:value-of select="datiDB/amministrazione.nome"/>
				</nome>
				<indirizzo>
					<via>
						<xsl:value-of select="datiDB/localita.via"/>
					</via>
					<numero_civico>
						<xsl:value-of select="datiDB/localita.numero_civico"/>
					</numero_civico>
					<cap>
						<xsl:value-of select="datiDB/localita.cap"/>
					</cap>
					<citta>
						<xsl:value-of select="datiDB/localita.citta"/>
					</citta>
					<sigla_provincia>
						<xsl:value-of select="datiDB/localita.sigla_provincia"/>
					</sigla_provincia>
					<regione>
						<xsl:value-of select="datiDB/localita.regione"/>
					</regione>
				</indirizzo>
				<email>
					<xsl:value-of select="datiDB/amministrazione.email"/>
				</email>
				<dominio_pec>
					<xsl:value-of select="datiDB/amministrazione.dominio_pec"/>
				</dominio_pec>
				<sito_istituzionale>
					<xsl:value-of select="datiDB/amministrazione.sito_istituzionale"/>
				</sito_istituzionale>
				<responsabile>
					<nome>
						<xsl:value-of select="datiDB/persona.nome"/>
					</nome>
					<cognome>
						<xsl:value-of select="datiDB/persona.cognome"/>
					</cognome>
					<titolo>
						<xsl:value-of select="datiDB/persona.titolo"/>
					</titolo>
					<email>
						<xsl:value-of select="datiDB/persona.email"/>
					</email>
					<telefono>
						<xsl:value-of select="datiDB/persona.telefono"/>
					</telefono>
				</responsabile>
				<servizi_telematici>
					<xsl:for-each select="datiDB">
						<servizio_telematico>
							<nome>
								<xsl:value-of select="serviziotelematico_amministrazione.nome"/>
							</nome>
							<descrizione>
								<xsl:value-of select="serviziotelematico_amministrazione.descrizione"/>
							</descrizione>
							<telefono>
								<xsl:value-of select="serviziotelematico_amministrazione.telefono"/>
							</telefono>
							<url>
								<xsl:value-of select="serviziotelematico_amministrazione.url"/>
							</url>
							<email>
								<xsl:value-of select="serviziotelematico_amministrazione.email"/>
							</email>
							<codice_ufficio>
								<xsl:value-of select="serviziotelematico_amministrazione.codice_ufficio"/>
							</codice_ufficio>
						</servizio_telematico>
					</xsl:for-each>
				</servizi_telematici>
				<aree_organizzative_omogenee>
					<xsl:for-each select="datiDB">
						<area_organizzativa_omogenea>
							<codice_aoo>
								<xsl:value-of select="areaorganizzativaomogenea.codice_aoo"/>
							</codice_aoo>
							<nome>
								<xsl:value-of select="areaorganizzativaomogenea.nome"/>
							</nome>
							<data_istituzione>
								<xsl:value-of select="areaorganizzativaomogenea.data_istituzione"/>
							</data_istituzione>
							<data_soppressione>
								<xsl:value-of select="areaorganizzativaomogenea.data_soppressione"/>
							</data_soppressione>
							<certification_autority>
								<xsl:value-of select="areaorganizzativaomogenea.certification_autority"/>
							</certification_autority>
							<email>
								<xsl:value-of select="areaorganizzativaomogenea.email"/>
							</email>
							<telefono>
								<xsl:value-of select="areaorganizzativaomogenea.telefono"/>
							</telefono>
							<fax>
								<xsl:value-of select="areaorganizzativaomogenea.fax"/>
							</fax>
						</area_organizzativa_omogenea>
					</xsl:for-each>
				</aree_organizzative_omogenee>
				<unita_organizzative>
					<xsl:for-each select="datiDB">
						<unita_organizzativa>
							<codice_uo>
								<xsl:value-of select="unitaorganizzativa.codice_uo"/>
							</codice_uo>
							<nome_ufficio>
								<xsl:value-of select="unitaorganizzativa.nome_ufficio"/>
							</nome_ufficio>
							<codice_uo_livello_superiore>
								<xsl:value-of select="unitaorganizzativa.codice_uo_livello_superiore"/>
							</codice_uo_livello_superiore>
							<codice_aoo>
								<xsl:value-of select="unitaorganizzativa.codice_aoo"/>
							</codice_aoo>
							<email>
								<xsl:value-of select="unitaorganizzativa.email"/>
							</email>
							<email_pec>
								<xsl:value-of select="unitaorganizzativa.email_pec"/>
							</email_pec>
							<telefono>
								<xsl:value-of select="unitaorganizzativa.telefono"/>
							</telefono>
							<fax>
								<xsl:value-of select="unitaorganizzativa.fax"/>
							</fax>
							<url_ca>
								<xsl:value-of select="unitaorganizzativa.url_ca"/>
							</url_ca>
						</unita_organizzativa>
					</xsl:for-each>
				</unita_organizzative>
			</amministrazione>
		</richiesta_RichiestaRispostaAsincrona_AggiornamentoIPA>
	</xsl:template>
</xsl:stylesheet>
