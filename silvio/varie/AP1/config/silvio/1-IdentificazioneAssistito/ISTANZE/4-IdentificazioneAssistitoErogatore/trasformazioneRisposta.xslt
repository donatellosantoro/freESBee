<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns="http://www.intemaweb.com/iron/datatypes" exclude-result-prefixes="xs">
	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/datiRisposta">
		<risposta_RichiestaRispostaSincrona_IdentificazioneAssistito>
			<xsl:attribute name="xsi:schemaLocation">
				<xsl:value-of select="'http://www.intemaweb.com/iron/datatypes C:/Users/Donatello/Desktop/AP1/mapping/IdentificazioneAssistitoAS/erogatore/target.xsd'"/>
			</xsl:attribute>
			<Intestazione>
				<xsl:for-each select="datiRichiesta">
					<xsl:for-each select="richiesta_RichiestaRispostaSincrona_IdentificazioneAssistito">
						<xsl:for-each select="Intestazione">
							<xsl:for-each select="InfoDestinatario">
								<InfoMittente>
									<xsl:for-each select="Regione">
										<Regione>
											<xsl:value-of select="."/>
										</Regione>
									</xsl:for-each>
									<xsl:for-each select="CodiceAsl">
										<CodiceAsl>
											<xsl:value-of select="."/>
										</CodiceAsl>
									</xsl:for-each>
									<xsl:for-each select="IdOperatore">
										<IdOperatore>
											<xsl:value-of select="."/>
										</IdOperatore>
									</xsl:for-each>
								</InfoMittente>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</xsl:for-each>
				<xsl:for-each select="datiRichiesta">
					<xsl:for-each select="richiesta_RichiestaRispostaSincrona_IdentificazioneAssistito">
						<xsl:for-each select="Intestazione">
							<xsl:for-each select="InfoMittente">
								<InfoDestinatario>
									<xsl:for-each select="Regione">
										<Regione>
											<xsl:value-of select="."/>
										</Regione>
									</xsl:for-each>
									<xsl:for-each select="CodiceAsl">
										<CodiceAsl>
											<xsl:value-of select="."/>
										</CodiceAsl>
									</xsl:for-each>
									<xsl:for-each select="IdOperatore">
										<IdOperatore>
											<xsl:value-of select="."/>
										</IdOperatore>
									</xsl:for-each>
								</InfoDestinatario>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</xsl:for-each>
				<xsl:for-each select="datiRichiesta">
					<xsl:for-each select="richiesta_RichiestaRispostaSincrona_IdentificazioneAssistito">
						<xsl:for-each select="Intestazione">
							<xsl:for-each select="DataOra">
								<DataOra>
									<xsl:value-of select="."/>
								</DataOra>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</xsl:for-each>
				<xsl:for-each select="datiRichiesta">
					<xsl:for-each select="richiesta_RichiestaRispostaSincrona_IdentificazioneAssistito">
						<xsl:for-each select="Intestazione">
							<xsl:for-each select="IdMessaggio">
								<IdMessaggio>
									<xsl:value-of select="."/>
								</IdMessaggio>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</xsl:for-each>
				<xsl:for-each select="datiRichiesta">
					<xsl:for-each select="richiesta_RichiestaRispostaSincrona_IdentificazioneAssistito">
						<xsl:for-each select="Intestazione">
							<xsl:for-each select="IdComunicazione">
								<IdComunicazione>
									<xsl:value-of select="."/>
								</IdComunicazione>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</xsl:for-each>
			</Intestazione>
			<DatiAnagraficiAssistito>
				<xsl:for-each select="datiCompleti">
					<xsl:for-each select="datiDB">
						<xsl:for-each select="paziente.codice_fiscale">
							<CodiceFiscale>
								<xsl:value-of select="."/>
							</CodiceFiscale>
						</xsl:for-each>
					</xsl:for-each>
				</xsl:for-each>
				<DatiAnagraficiBase>
					<xsl:for-each select="datiCompleti">
						<xsl:for-each select="datiDB">
							<xsl:for-each select="paziente.cognome">
								<Cognome>
									<xsl:value-of select="."/>
								</Cognome>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:for-each select="datiCompleti">
						<xsl:for-each select="datiDB">
							<xsl:for-each select="paziente.nome">
								<Nome>
									<xsl:value-of select="."/>
								</Nome>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:for-each select="datiCompleti">
						<xsl:for-each select="datiDB">
							<xsl:for-each select="paziente.data_nascita">
								<DataDiNascita>
									<xsl:value-of select="."/>
								</DataDiNascita>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:for-each select="datiCompleti">
						<xsl:for-each select="datiDB">
							<xsl:for-each select="paziente.comune_residenza">
								<ComuneNascita>
									<xsl:value-of select="."/>
								</ComuneNascita>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</DatiAnagraficiBase>
				<xsl:for-each select="datiCompleti">
					<xsl:for-each select="datiDB">
						<xsl:for-each select="paziente.sesso">
							<Sesso>
								<xsl:value-of select="."/>
							</Sesso>
						</xsl:for-each>
					</xsl:for-each>
				</xsl:for-each>
				<xsl:for-each select="datiCompleti">
					<xsl:for-each select="datiDB">
						<xsl:for-each select="paziente.comune_residenza">
							<ComuneResidenza>
								<xsl:value-of select="."/>
							</ComuneResidenza>
						</xsl:for-each>
					</xsl:for-each>
				</xsl:for-each>
				<xsl:for-each select="datiCompleti">
					<xsl:for-each select="datiDB">
						<xsl:for-each select="paziente.asl_residenza">
							<AslResidenza>
								<xsl:value-of select="."/>
							</AslResidenza>
						</xsl:for-each>
					</xsl:for-each>
				</xsl:for-each>
				<MedicoBase>
					<xsl:for-each select="datiCompleti">
						<xsl:for-each select="datiDB">
							<xsl:for-each select="medico.cognome">
								<Cognome>
									<xsl:value-of select="."/>
								</Cognome>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:for-each select="datiCompleti">
						<xsl:for-each select="datiDB">
							<xsl:for-each select="medico.nome">
								<Nome>
									<xsl:value-of select="."/>
								</Nome>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:for-each select="datiCompleti">
						<xsl:for-each select="datiDB">
							<xsl:for-each select="medico.comune_residenza">
								<ComuneResidenza>
									<xsl:value-of select="."/>
								</ComuneResidenza>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:for-each select="datiCompleti">
						<xsl:for-each select="datiDB">
							<xsl:for-each select="medico.codice_fiscale">
								<CodiceFiscale>
									<xsl:value-of select="."/>
								</CodiceFiscale>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:for-each select="datiCompleti">
						<xsl:for-each select="datiDB">
							<xsl:for-each select="medico.codice_regionale">
								<CodiceRegionale>
									<xsl:value-of select="."/>
								</CodiceRegionale>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:for-each select="datiCompleti">
						<xsl:for-each select="datiDB">
							<xsl:for-each select="medico.telefono">
								<Telefono>
									<xsl:value-of select="."/>
								</Telefono>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<xsl:for-each select="datiCompleti">
						<xsl:for-each select="datiDB">
							<xsl:for-each select="medico.cellulare">
								<Cellulare>
									<xsl:value-of select="."/>
								</Cellulare>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</MedicoBase>
			</DatiAnagraficiAssistito>
		</risposta_RichiestaRispostaSincrona_IdentificazioneAssistito>
	</xsl:template>
</xsl:stylesheet>
