<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns="http://www.intemaweb.com/iron/datatypes" exclude-result-prefixes="xs" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:p891="http://sica.spcoop.it/servizi/QualificazionePDDWS/types" xmlns:tra="http://www.cnipa.it/schemas/2003/eGovIT/Tracciamento1_0/" xmlns:SOAP_ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:eGov_IT="http://www.cnipa.it/schemas/2003/eGovIT/Busta1_0/">
	
	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>	
	<xsl:template match="/datiRisposta">
		<p891:risposta_RichiestaRispostaSincrona_getTraccia>
			<p891:Esito>RISPOSTA_OK</p891:Esito>
			<p891:TokenSessione>
				<xsl:value-of select="datiRichiesta/p891:richiesta_RichiestaRispostaSincrona_getTraccia/p891:TokenSessione"/>
			</p891:TokenSessione>
			<xsl:for-each select="/datiRisposta/datiCompleti/datiDB">
				<p891:TracciaQualificazione>
 					<xsl:apply-templates select="."/>
 				</p891:TracciaQualificazione>
			</xsl:for-each>
		</p891:risposta_RichiestaRispostaSincrona_getTraccia>
	</xsl:template>
	
	<xsl:template match="datiDB">
			&lt;tra:traccia xmlns:tra="http://www.cnipa.it/schemas/2003/eGovIT/Tracciamento1_0/" xmlns:SOAP_ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:eGov_IT="http://www.cnipa.it/schemas/2003/eGovIT/Busta1_0/"&gt;
				&lt;tra:GDO&gt;2001-12-17T09:30:47.0Z&lt;/tra:GDO&gt;
				&lt;tra:IdentificativoPorta&gt;RegioneBasilicataSPCoopIT&lt;/tra:IdentificativoPorta&gt;
				&lt;tra:TipoMessaggio&gt;<xsl:value-of select="messaggio.tipomessaggio"/>&lt;/tra:TipoMessaggio&gt;
				&lt;eGov_IT:Intestazione SOAP_ENV:actor="http://www.cnipa.it/eGov_it/portadominio" SOAP_ENV:mustUnderstand="1"&gt;
					<xsl:variable name="VmarkerloopdatiDB" select="."/>&lt;eGov_IT:IntestazioneMessaggio&gt;
						&lt;eGov_IT:Mittente&gt;
							&lt;eGov_IT:IdentificativoParte tipo="<xsl:value-of select="messaggio.tipofruitore"/>"&gt;<xsl:value-of select="messaggio.fruitore"/>&lt;/eGov_IT:IdentificativoParte&gt;
						&lt;/eGov_IT:Mittente&gt;
						&lt;eGov_IT:Destinatario&gt;
							&lt;eGov_IT:IdentificativoParte tipo="<xsl:value-of select="messaggio.tipoerogatore"/>"&gt;<xsl:value-of select="messaggio.erogatore"/>&lt;/eGov_IT:IdentificativoParte&gt;
						&lt;/eGov_IT:Destinatario&gt;
						<xsl:for-each select="messaggio.profilocollaborazione">&lt;eGov_IT:ProfiloCollaborazione&gt;<xsl:value-of select="."/>&lt;/eGov_IT:ProfiloCollaborazione&gt;</xsl:for-each>
						<xsl:if test="messaggio.collaborazione[text()]">
							<xsl:for-each select="messaggio.collaborazione">
								&lt;eGov_IT:Collaborazione&gt;<xsl:value-of select="."/>&lt;/eGov_IT:Collaborazione&gt;
							</xsl:for-each>
						</xsl:if>
						&lt;eGov_IT:Servizio tipo="<xsl:value-of select="messaggio.tiposervizio"/>"&gt;<xsl:value-of select="messaggio.servizio"/>&lt;/eGov_IT:Servizio&gt;
						<xsl:if test="messaggio.azione[text()]">
							<xsl:for-each select="messaggio.azione">&lt;eGov_IT:Azione&gt;<xsl:value-of select="."/>&lt;/eGov_IT:Azione&gt;</xsl:for-each>
						</xsl:if>
						&lt;eGov_IT:Messaggio&gt;<xsl:for-each select="messaggio.idmessaggio">
							&lt;eGov_IT:Identificatore&gt;<xsl:value-of select="."/>&lt;/eGov_IT:Identificatore&gt;</xsl:for-each>
							<xsl:if test="messaggio.oraregistrazione[text()]">
								<xsl:for-each select="messaggio.oraregistrazione">
							&lt;eGov_IT:OraRegistrazione tempo="EGOV_IT_SPC"&gt;<xsl:value-of select="."/>&lt;/eGov_IT:OraRegistrazione&gt;</xsl:for-each>
							</xsl:if>
							<xsl:if test="messaggio.riferimentomessaggio[text()]">
							&lt;eGov_IT:RiferimentoMessaggio&gt;<xsl:value-of select="messaggio.riferimentomessaggio"/>&lt;/eGov_IT:RiferimentoMessaggio&gt;</xsl:if>
							<xsl:if test="messaggio.scadenza[text()]">
							&lt;eGov_IT:Scadenza&gt;<xsl:value-of select="messaggio.scadenza"/>	&lt;/eGov_IT:Scadenza&gt;
							</xsl:if>
						&lt;/eGov_IT:Messaggio&gt;
					&lt;/eGov_IT:IntestazioneMessaggio&gt;
					<xsl:if test="eccezione.codiceeccezione[text()]">
						&lt;eGov_IT:ListaEccezioni&gt;
							&lt;eGov_IT:Eccezione 
							contestoCodifica="<xsl:value-of select="eccezione.contestocodifica"/>"
							codiceEccezione="EGOV_IT_<xsl:value-of select="eccezione.codiceeccezione"/>"
							rilevanza="<xsl:value-of select="eccezione.rilevanza"/>"
							posizione="<xsl:value-of select="eccezione.posizione"/>"
							/&gt;
						&lt;/eGov_IT:ListaEccezioni&gt;
					</xsl:if>
				&lt;/eGov_IT:Intestazione&gt;
			&lt;/tra:traccia&gt;
	</xsl:template>
	
	
	
</xsl:stylesheet>
