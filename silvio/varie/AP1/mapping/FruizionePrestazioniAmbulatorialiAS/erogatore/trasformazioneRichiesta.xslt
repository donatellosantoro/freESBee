<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns="http://www.intemaweb.com/iron/datatypes">
	<xsl:output method="text"/>
	<xsl:template match="/">
	INSERT INTO ricetta (numero, diagnosi, data, posizione_utente_ticket, importo_ticket, importo_totale, codice_struttura_erogatrice, codice_prestazione, codifica_nomenclatore, quantita, importo, paziente) VALUES( ${id.ricetta},
		<!-- elemento radice -->	
		   '<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiPrestazioniAmbulatoriali/DatiRicetta/Diagnosi"/>',
			'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiPrestazioniAmbulatoriali/DatiRicetta/Data"/>',
			'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiPrestazioniAmbulatoriali/DatiRicetta/PosizioneUtenteTicket"/>',
			<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiPrestazioniAmbulatoriali/DatiRicetta/ImportoTicket"/>,
			<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiPrestazioniAmbulatoriali/DatiRicetta/ImportoTotale"/>,
			<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiPrestazioniAmbulatoriali/DatiPrestazioniRicetta/CodiceStrutturaErogatrice"/>,
			<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiPrestazioniAmbulatoriali/DatiPrestazioniRicetta/Prestazione/CodicePrestazione"/>,
			<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiPrestazioniAmbulatoriali/DatiPrestazioniRicetta/Prestazione/CodificaNomenclatore"/>,
			<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiPrestazioniAmbulatoriali/DatiPrestazioniRicetta/Prestazione/Quantita"/>,
			<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiPrestazioniAmbulatoriali/DatiPrestazioniRicetta/Prestazione/Importo"/>,					
			${refId.paziente}) ${endStatement} 
			
			INSERT INTO paziente (id,nome, cognome, codice_fiscale, data_nascita, sesso, comune_residenza, codice_ssr) VALUES( ${id.paziente},
		<!-- elemento radice -->	
		   '<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiAnagrafici/DatiAnagraficiBase/Nome"/>',
			'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiAnagrafici/DatiAnagraficiBase/Cognome"/>',
			'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiAnagrafici/CodiceFiscale"/>',
			'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiAnagrafici/DatiAnagraficiBase/DataDiNascita"/>',
			'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiAnagrafici/Sesso"/>',
			'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiAnagrafici/ComuneResidenza"/>',
			'<xsl:apply-templates select="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiAnagrafici/CodiceSSR"/>'
			) ${endStatement}
	</xsl:template>
	<xsl:template match="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiPrestazioniAmbulatoriali/DatiRicetta/Diagnosi">
		<!-- elemento nome -->			
           <xsl:value-of select="text()"/> 
   </xsl:template>
   	<xsl:template match="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiPrestazioniAmbulatoriali/DatiRicetta/Data">
		<!-- elemento nome -->			
          <xsl:value-of select="text()"/> 
   </xsl:template>
   	<xsl:template match="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiPrestazioniAmbulatoriali/DatiRicetta/PosizioneUtenteTicket">
		<!-- elemento nome -->			
          <xsl:value-of select="text()"/> 
   </xsl:template>
   	<xsl:template match="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiPrestazioniAmbulatoriali/DatiRicetta/ImportoTicket">
		<!-- elemento nome -->			
          <xsl:value-of select="text()"/> 
   </xsl:template>
   	<xsl:template match="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiPrestazioniAmbulatoriali/DatiRicetta/ImportoTotale">
		<!-- elemento nome -->			
          <xsl:value-of select="text()"/> 
   </xsl:template>
   	<xsl:template match="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiPrestazioniAmbulatoriali/DatiPrestazioniRicetta/CodiceStrutturaErogatrice">
		<!-- elemento nome -->			
          <xsl:value-of select="text()"/> 
   </xsl:template>
   	<xsl:template match="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiPrestazioniAmbulatoriali/DatiPrestazioniRicetta/Prestazione/CodicePrestazione">
		<!-- elemento nome -->			
          <xsl:value-of select="text()"/> 
   </xsl:template>
   <xsl:template match="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiPrestazioniAmbulatoriali/DatiPrestazioniRicetta/Prestazione/CodificaNomenclatore">
		<!-- elemento nome -->			
          <xsl:value-of select="text()"/> 
   </xsl:template>
   <xsl:template match="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiPrestazioniAmbulatoriali/DatiPrestazioniRicetta/Prestazione/Quantita">
		<!-- elemento nome -->			
          <xsl:value-of select="text()"/> 
   </xsl:template>
   <xsl:template match="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiPrestazioniAmbulatoriali/DatiPrestazioniRicetta/Prestazione/Importo">
		<!-- elemento nome -->			
          <xsl:value-of select="text()"/> 
   </xsl:template>
   <xsl:template match="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiPrestazioniAmbulatoriali/DatiPrestazioniRicetta/ID">
		<!-- elemento nome -->			
          <xsl:value-of select="text()"/> 
   </xsl:template>
   
   <xsl:template match="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiAnagrafici/DatiAnagraficiBase/Cognome">
		<!-- elemento nome -->			
           <xsl:value-of select="text()"/> 
   </xsl:template>
   	<xsl:template match="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiAnagrafici/DatiAnagraficiBase/Nome">
		<!-- elemento nome -->			
          <xsl:value-of select="text()"/> 
   </xsl:template>
   	<xsl:template match="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiAnagrafici/CodiceFiscale">
		<!-- elemento nome -->			
          <xsl:value-of select="text()"/> 
   </xsl:template>
   	<xsl:template match="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiAnagrafici/DatiAnagraficiBase/DataDiNascita">
		<!-- elemento nome -->			
          <xsl:value-of select="text()"/> 
   </xsl:template>
   	<xsl:template match="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiAnagrafici/Sesso">
		<!-- elemento nome -->			
          <xsl:value-of select="text()"/> 
   </xsl:template>
   	<xsl:template match="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiAnagrafici/ComuneResidenza">
		<!-- elemento nome -->			
          <xsl:value-of select="text()"/> 
   </xsl:template>
   	<xsl:template match="/soapenv:Envelope/soapenv:Body/richiesta_RichiestaRispostaAsincrona_SegnalazioneFruizionePrestazioniAmbulatoriali/SegnalazioneFruizionePrestazioniAmbulatoriali/DatiAnagrafici/CodiceSSR">
		<!-- elemento nome -->			
          <xsl:value-of select="text()"/> 
   </xsl:template>
</xsl:stylesheet>
