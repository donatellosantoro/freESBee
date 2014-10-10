<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns="http://progettoicar.it/AP3interprotoQry/types" exclude-result-prefixes="xs">
	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/datiRisposta">
		<risposta_RichiestaRispostaSincrona_Ricerca>
			<xsl:attribute name="xsi:schemaLocation">
				<xsl:value-of select="'http://progettoicar.it/AP3interprotoQry/types ./targetRispostaSQLtoXML.xsd'"/>
			</xsl:attribute>
			<AP3RicercaResponse>
				<documenti>
					<documento>
						<dataRegistrazione>
							<xsl:value-of select="datiCompleti/datiDB/documentofascicolo.data_registrazione"/>
						</dataRegistrazione>
						<estremiProtocolloEstesi>
							<estremiProtocollo>
								<anno>
									<xsl:value-of select="datiCompleti/datiDB/documentofascicolo.estremiprotocollo_anno"/>
								</anno>
								<numero>
									<xsl:value-of select="datiCompleti/datiDB/documentofascicolo.estremiprotocollo_numero"/>
								</numero>
							</estremiProtocollo>
							<arrivoPartenza>
								<xsl:value-of select="datiCompleti/datiDB/documentofascicolo.arrivopartenza"/>
							</arrivoPartenza>
						</estremiProtocolloEstesi>
						<classifica>
							<livelli>
								<livelloClassifica>
									<xsl:value-of select="datiCompleti/datiDB/classifica.livello_classifica"/>
								</livelloClassifica>
							</livelli>
							<codiceClassifica>
								<xsl:value-of select="datiCompleti/datiDB/classifica.codice_classifica"/>
							</codiceClassifica>
							<descrizioneClassifica>
								<xsl:value-of select="datiCompleti/datiDB/classifica.descrizione_classifica"/>
							</descrizioneClassifica>
						</classifica>
						<estremiFascicolo>
							<codiceUfficio>
								<xsl:value-of select="datiCompleti/datiDB/fascicolo.codice_ufficio"/>
							</codiceUfficio>
							<descrizioneUfficio>
								<xsl:value-of select="datiCompleti/datiDB/fascicolo.descrizione_ufficio"/>
							</descrizioneUfficio>
							<classifica>
								<livelli>
									<livelloClassifica>
										<xsl:value-of select="datiCompleti/datiDB/classifica.livello_classifica"/>
									</livelloClassifica>
								</livelli>
								<codiceClassifica>
									<xsl:value-of select="datiCompleti/datiDB/classifica.codice_classifica"/>
								</codiceClassifica>
								<descrizioneClassifica>
									<xsl:value-of select="datiCompleti/datiDB/classifica.descrizione_classifica"/>
								</descrizioneClassifica>
							</classifica>
							<anno>
								<xsl:value-of select="datiCompleti/datiDB/fascicolo.anno"/>
							</anno>
							<numero>
								<xsl:value-of select="datiCompleti/datiDB/fascicolo.numero"/>
							</numero>
							<subnumero>
								<xsl:value-of select="datiCompleti/datiDB/fascicolo.subnumero"/>
							</subnumero>
						</estremiFascicolo>
						<documentiInformatici>
							<xsl:for-each select="datiCompleti/datiDB">
								<documentoInformatico>
									<codiceDocumentoInformatico>
										<xsl:value-of select="documento.codice_documento"/>
									</codiceDocumentoInformatico>
									<flPrimario>
										<xsl:value-of select="documento.documento"/>
									</flPrimario>
									<descrizioneDocumentoInformatico>
										<xsl:value-of select="documento.nome_documento"/>
									</descrizioneDocumentoInformatico>
								</documentoInformatico>
							</xsl:for-each>
						</documentiInformatici>
						<responsabiliTrattamento>
							<xsl:for-each select="datiCompleti/datiDB">
								<responsabileTrattamento>
									<codice_RresponsabileTrattamento>
										<xsl:value-of select="responsabiletrattamento.codice_responsabiletrattamento"/>
									</codice_RresponsabileTrattamento>
									<descrizioneResponsabileTrattamento>
										<xsl:value-of select="responsabiletrattamento.descrizioneresponsabiletrattamento"/>
									</descrizioneResponsabileTrattamento>
								</responsabileTrattamento>
							</xsl:for-each>
						</responsabiliTrattamento>
					</documento>
				</documenti>
			</AP3RicercaResponse>
		</risposta_RichiestaRispostaSincrona_Ricerca>
	</xsl:template>
</xsl:stylesheet>
