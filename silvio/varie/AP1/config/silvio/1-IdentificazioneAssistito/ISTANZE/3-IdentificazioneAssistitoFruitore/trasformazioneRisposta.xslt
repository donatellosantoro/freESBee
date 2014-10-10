<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns="http://www.intemaweb.com/iron/datatypes">
	<xsl:output method="text"/>
	<xsl:template match="/">	
	INSERT INTO paziente (id,medico,nome, cognome, codice_fiscale, data_nascita, sesso, comune_residenza, asl_residenza, codice_ssr) VALUES(${id.Paziente},${refId.Medico},
		<!-- elemento radice -->	
		   '<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_IdentificazioneAssistito/DatiAnagraficiAssistito/DatiAnagraficiBase/Nome"/>',
			'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_IdentificazioneAssistito/DatiAnagraficiAssistito/DatiAnagraficiBase/Cognome"/>',
			'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_IdentificazioneAssistito/DatiAnagraficiAssistito/CodiceFiscale"/>',
			'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_IdentificazioneAssistito/DatiAnagraficiAssistito/DatiAnagraficiBase/DataDiNascita"/>',
			'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_IdentificazioneAssistito/DatiAnagraficiAssistito/Sesso"/>',
			'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_IdentificazioneAssistito/DatiAnagraficiAssistito/ComuneResidenza"/>',
			'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_IdentificazioneAssistito/DatiAnagraficiAssistito/AslResidenza"/>',
			'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_IdentificazioneAssistito/DatiAnagraficiAssistito/CodiceSSR"/>'
			)  ${endStatement}
			
			
	INSERT INTO medico (id,nome, cognome, codice_fiscale, comune_residenza, codice_regionale) VALUES(${id.Medico},
		<!-- elemento radice -->	
		   '<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_IdentificazioneAssistito/DatiAnagraficiAssistito/MedicoBase/Nome"/>',
			'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_IdentificazioneAssistito/DatiAnagraficiAssistito/MedicoBase/Cognome"/>',
			'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_IdentificazioneAssistito/DatiAnagraficiAssistito/MedicoBase/CodiceFiscale"/>',
			'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_IdentificazioneAssistito/DatiAnagraficiAssistito/MedicoBase/ComuneResidenza"/>',
			'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_IdentificazioneAssistito/DatiAnagraficiAssistito/MedicoBase/CodiceRegionale"/>'
			)  ${endStatement}
	</xsl:template>
	<xsl:template match="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_IdentificazioneAssistito/DatiAnagraficiAssistito/DatiAnagraficiBase/Cognome">
		<!-- elemento nome -->
		<xsl:value-of select="text()"/>
	</xsl:template>
	<xsl:template match="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_IdentificazioneAssistito/DatiAnagraficiAssistito/DatiAnagraficiBase/Nome">
		<!-- elemento nome -->
		<xsl:value-of select="text()"/>
	</xsl:template>
	<xsl:template match="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_IdentificazioneAssistito/DatiAnagraficiAssistito/CodiceFiscale">
		<!-- elemento nome -->
		<xsl:value-of select="text()"/>
	</xsl:template>
	<xsl:template match="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_IdentificazioneAssistito/DatiAnagraficiAssistito/DatiAnagraficiBase/DataDiNascita">
		<!-- elemento nome -->
		<xsl:value-of select="text()"/>
	</xsl:template>
	<xsl:template match="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_IdentificazioneAssistito/DatiAnagraficiAssistito/Sesso">
		<!-- elemento nome -->
		<xsl:value-of select="text()"/>
	</xsl:template>
	<xsl:template match="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_IdentificazioneAssistito/DatiAnagraficiAssistito/ComuneResidenza">
		<!-- elemento nome -->
		<xsl:value-of select="text()"/>
	</xsl:template>
	<xsl:template match="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_IdentificazioneAssistito/DatiAnagraficiAssistito/CodiceSSR">
		<!-- elemento nome -->
		<xsl:value-of select="text()"/>
	</xsl:template>
	
	<xsl:template match="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_IdentificazioneAssistito/DatiAnagraficiAssistito/MedicoBase/Nome">
		<!-- elemento nome -->
		<xsl:value-of select="text()"/>
	</xsl:template>
	<xsl:template match="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_IdentificazioneAssistito/DatiAnagraficiAssistito/MedicoBase/Cognome">
		<!-- elemento nome -->
		<xsl:value-of select="text()"/>
	</xsl:template>
	<xsl:template match="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_IdentificazioneAssistito/DatiAnagraficiAssistito/MedicoBase/CodiceFiscale">
		<!-- elemento nome -->
		<xsl:value-of select="text()"/>
	</xsl:template>
	<xsl:template match="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_IdentificazioneAssistito/DatiAnagraficiAssistito/MedicoBase/ComuneResidenza">
		<!-- elemento nome -->
		<xsl:value-of select="text()"/>
	</xsl:template>
	<xsl:template match="/soapenv:Envelope/soapenv:Body/risposta_RichiestaRispostaSincrona_IdentificazioneAssistito/DatiAnagraficiAssistito/MedicoBase/CodiceRegionale">
		<!-- elemento nome -->
		<xsl:value-of select="text()"/>
	</xsl:template>
</xsl:stylesheet>
