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
                    <li><a href='http://localhost:8080/clientWSComune2/paginaCerca.jsp'><span>Cerca </span></a></li>
                    <li><a href='http://localhost:8080/clientWSComune2/paginaNuovaImmatricolazione.jsp'><span>Immatricolazione</span></a></li>
                    <!--   <li><a href='https://sp.comune.pz:8443/freesbeesp/schermoLogin.faces?autenticazione=UTENTE&uriDiDesti
                    nazione=http://localhost:8080/clientWSComune2/paginaCerca.jsp&silSessionId=${sessione}&risorsa=https://sp.comune.pz/PD_CERCA_IMMATRICOLAZIONE'><span>Cerca</span></a></li>
                       <li><a href='https://sp.comune.pz:8443/freesbeesp/schermoLogin.faces?autenticazione=UTENTE&uriDiDesti
                    nazione=http://localhost:8080/clientWSComune2/paginaNuovaImmatricolazione.jsp&silSessionId=${sessione}&risorsa=https://sp.comune.pz/PD_CERCA_CITTADINO'><span>Immatricolazione</span></a></li>-->
                    <li><a href='#'><span>Modifica</span></a></li>
                    <li><a href='#'><span>Elimina</span></a></li>
                    <li><a href='#'><span>Info</span></a></li>
                    <li><a href='#'><span>Esci</span></a></li>
                </ul>
            </div>
            <div class="corpo">

                <div class="titolo">Nuova Immatricolazione</div>

                <html:form action="/nuovaImmatricolazione">
                    <html:errors/>
                    <table class="tabellaImmatricolazione">
                        <tr>
                            <td>
                                <span class="testo">Codice fiscale : </span>
                            </td>
                            <td>
                                <html:text property="codiceFiscale" styleClass="textInput" maxlength="16"></html:text>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span class="testo">Targa : </span>
                                </td>
                                <td>
                                <html:text property="targa" styleClass="textInput" maxlength="8"></html:text>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span class="testo">Telaio : </span>
                                </td>
                                <td>
                                <html:text property="telaio" styleClass="textInput" maxlength="17"></html:text>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span class="testo">Modello : </span>
                                </td>
                                <td>
                                <html:text property="modello" styleClass="textInput"></html:text>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span class="testo">Cavalli: </span>
                                </td>
                                <td>
                                <html:text property="cavalli" styleClass="textInput" maxlength="3"></html:text>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span class="testo">KW : </span>
                                </td>
                                <td>
                                <html:text property="kw" styleClass="textInput" maxlength="3"></html:text>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span class="testo">Classe : </span>
                                </td>
                                <td>
                                <html:select property="classe" styleClass="selectBox">
                                    <html:options property="classi"></html:options>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span class="testo">Uso : </span>
                            </td>
                            <td>
                                <html:select property="uso" styleClass="selectBox">
                                    <html:options property="usi"></html:options>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span class="testo">Alimentazione : </span>
                            </td>
                            <td>
                                <html:select property="alimentazione" styleClass="selectBox">
                                    <html:options property="alimentazioni"></html:options>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span class="testo">Posti : </span>
                            </td>
                            <td>
                                <html:select property="posti" styleClass="selectBox">
                                    <html:options property="postiDisponibili"></html:options>
                                </html:select>
                            </td>
                        </tr>
                    </table>
                    <div class="divBotton">
                        <html:submit value="Inserisci" styleClass="bottoneInserisci"></html:submit>
                        </div>
                    <html:hidden property="freesbeeSPSessionId" value="${param.freesbeeSPSessionId}"></html:hidden> 
                </html:form>
            </div>
            <div class="footer">
            </div>
        </div>
    </body>
</html>
