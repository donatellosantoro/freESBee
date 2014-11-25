<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Documento senza titolo</title>

<link href="/clientWSComune2/styleIndex.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="contenitore">
  <div class="header"></div>
  
  <div id='cssmenu'>
<ul>
   <li class='active '><a href='/clientWSComune2/paginaIniziale.jsp'><span>Home</span></a></li>
   <li><a href='https://sp.comune.pz:8443/freesbeesp/schermoLogin.faces?autenticazione=UTENTE&uriDiDesti
nazione=http://localhost:8080/clientWSComune2/paginaCerca.jsp&silSessionId=${sessione}&risorsa=https://sp.comune.pz/PD_CERCA_IMMATRICOLAZIONE'><span>Cerca</span></a></li>
   <li><a href='https://sp.comune.pz:8443/freesbeesp/schermoLogin.faces?autenticazione=UTENTE&uriDiDesti
nazione=http://localhost:8080/clientWSComune2/paginaNuovaImmatricolazione.jsp&silSessionId=${sessione}&risorsa=https://sp.comune.pz/PD_CERCA_CITTADINO'><span>Immatricolazione</span></a></li>
   <li><a href='#'><span>Modifica</span></a></li>
   <li><a href='#'><span>Elimina</span></a></li>
   <li><a href='#'><span>Info</span></a></li>
   <li><a href='#'><span>Esci</span></a></li>
</ul>
</div>
  <div class="corpo">
  <div class="textBox">
 
<html:form action="/cercaCittadino"> 
   <html:errors/>
   <span class="testo"> Inserisci il numero di targa: </span>
  <html:text property="targa" styleClass="inputText"></html:text>
  <html:submit value="Cerca" styleClass="bottoneCerca"></html:submit>
  <html:hidden property="freesbeeSPSessionId" value="${param.freesbeeSPSessionId}"></html:hidden>   
</html:form>
  
  </div>
  </div>
  <div class="footer">
  </div>
</div>
</body>
</html>
