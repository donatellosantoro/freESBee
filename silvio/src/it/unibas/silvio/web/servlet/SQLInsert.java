package it.unibas.silvio.web.servlet;

import it.unibas.silvio.modello.DatiDB;
import it.unibas.silvio.modello.DatoPrimitivo;
import it.unibas.silvio.modello.Query;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.sql.ResultSetParser;
import it.unibas.silvio.sql.ResultSetUtil;
import it.unibas.silvio.transql.SQLParser;
import it.unibas.silvio.xml.XmlJDomUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Document;
import org.jdom.Element;

public class SQLInsert extends HttpServlet {

    private ResultSetUtil rsUtil = new ResultSetUtil();
    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String tipoDB = httpRequest.getParameter("tipoDB");
        String indirizzoDB = httpRequest.getParameter("indirizzoDB");
        String nomeUtente = httpRequest.getParameter("nomeUtente");
        String password = httpRequest.getParameter("password");
        String nomeDB = httpRequest.getParameter("nomeDB");
        String query = httpRequest.getParameter("query");
        if (nomeUtente == null || password == null || nomeDB == null || query == null) {
            throw new ServletException("Per eseguire la select bisogna fornirgli il nome utente, la password, il nome del db e la query da eseguire");
        }
        if (tipoDB == null) {
            tipoDB = "PostgreSQL";
        }
        if (indirizzoDB == null) {
            indirizzoDB = "localhost";
        }

        Query datiQuery = new Query();
        datiQuery.setTipoDB(tipoDB);
        datiQuery.setIndirizzoDB(indirizzoDB);
        datiQuery.setNomeUtente(nomeUtente);
        datiQuery.setPassword(password);
        datiQuery.setNomeDB(nomeDB);
        it.unibas.silvio.transql.SQLExecutor sqlExec = new it.unibas.silvio.transql.SQLExecutor(datiQuery);
        logger.info("Eseguo l'insert: " + query);
        try {
            List<it.unibas.silvio.transql.SQLInsert> listaInsert = SQLParser.parse(query);
            String risultato = sqlExec.esegui(listaInsert);
            logger.info("Risultato: " + risultato);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Impossibile eseguire l'insert " + query + ". Errore" + ex);
            throw new ServletException("Impossibile eseguire l'insert " + query + ". Errore" + ex);
        } finally {
            rsUtil.chiudiConnessioni();
        }


        response.setContentType("application/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<result>Insert eseguita correttamente</result>");
        } finally {
            out.close();
        }
    }

    private String rsToString(ResultSet rs) throws DAOException {
        Element rootElement = new Element("results");
        Document datiCompletiDocument = new Document(rootElement);
        List<DatiDB> listaDati = ResultSetParser.estraiDatiDB(rs);
        for (DatiDB datiDB : listaDati) {
            rootElement.addContent(aggiungiDati(datiDB.getDatiDB(), "result"));
        }
        String results = XmlJDomUtil.stampaXML(datiCompletiDocument);
        return results;
    }

    private Element aggiungiDati(List<DatoPrimitivo> datiCostanti, String string) {
        Element elementDati = new Element(string);
        for (DatoPrimitivo datoPrimitivo : datiCostanti) {
            Element dato = new Element(datoPrimitivo.getNome());
            dato.setText(datoPrimitivo.getValore());
            elementDati.addContent(dato);
        }
        return elementDati;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Execute any sql script";
    }// </editor-fold>
}
