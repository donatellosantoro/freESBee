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
                <div class="titoloTabelle">Dati Veicolo</div>
                <div class="tabella" >
                    <table >
                        <tr>
                            <td>Targa</td>
                            <td>Telaio</td>
                            <td>Data immatr.</td>
                            <td>Modello</td>
                            <td>Cavalli</td>
                            <td>Kw</td>
                            <td>Classe</td>
                            <td>Uso</td>
                            <td>Alimentazione</td>
                            <td>Posti</td>
                        </tr>
                        <tr>
                            <td >
                                ${immatricolazione.targa}
                            </td>
                            <td>
                                ${immatricolazione.telaio}
                            </td>

                            <td>
                                <fmt:formatDate value="${dataImmatricolazione.time}" type="date" dateStyle="medium"></fmt:formatDate>
                                </td>
                                <td>${immatricolazione.modello}</td>
                            <td >
                                ${immatricolazione.cavalli}
                            </td>
                            <td>
                                ${immatricolazione.kw}
                            </td>
                            <td>
                                ${immatricolazione.classe}
                            </td>
                            <td>
                                ${immatricolazione.uso}
                            </td>
                            <td>
                                ${immatricolazione.alimentazione}
                            </td>
                            <td>
                                ${immatricolazione.posti}
                            </td>
                        </tr>
                    </table>
                </div>  
                <div class="titoloTabelle">Dati Proprietario</div> 
                <div class="tabella" >
                    <table >
                        <tr>
                            <td>Cod. Fiscale</td>
                            <td>Nome</td>
                            <td>Cognome</td>
                            <td>Data Nascita</td>
                            <td>Comune Nascita</td>
                            <td>Comune Residenza</td>
                            <td>Via</td>
                        </tr>
                        <tr>
                            <td >
                                ${immatricolazione.codiceFiscale}
                            </td>
                            <td>
                                ${immatricolazione.nome}
                            </td>
                            <td>
                                ${immatricolazione.cognome}
                            </td>
                            <td>
                                <fmt:formatDate value="${dataNascita.time}" type="date" dateStyle="medium"></fmt:formatDate>
                                </td>
                                <td>${immatricolazione.comuneNascita}</td>
                            <td>${immatricolazione.comuneResidenza}</td>
                            <td>${immatricolazione.via}</td>                          
                        </tr>
                    </table>
                </div>

            </div>
            <div class="footer">
            </div>
        </div>
    </body>
</html>
