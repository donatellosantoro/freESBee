<?xml version="1.0" encoding="iso-8859-1" ?>

<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="intestazione.jsp">
    <c:param name="titolo" value="Login" />
</c:import>

<h2>Pagina Iniziale</h2>

<html:errors />

<p>Inserisci nome utente e password per accedere al sistema: </p>

<html:form action="/login">
    <table>
        <tr>
            <td><strong class="label">Nome utente:</strong></td>
            <td><html:text property="nomeUtente" /></td>
        </tr>
        <tr>
            <td><strong class="label">Password:</strong></td>
            <td><html:password redisplay="false" property="password" /></td>
        </tr>
    </table>

    <html:submit value="Login" />
</html:form>

<p><html:link action="login.do?nomeUtente=luca&password=marini" transaction="true">Ingresso rapido come luca marini</html:link><br />


<c:import url="pieDiPagina1.jsp" />
