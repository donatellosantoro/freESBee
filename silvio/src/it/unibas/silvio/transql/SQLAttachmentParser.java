package it.unibas.silvio.transql;

import it.unibas.silvio.util.StringUtil;
import java.util.ArrayList;
import java.util.List;

public class SQLAttachmentParser {

    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass());
    public static String UNION = " UNION ";
    public static String SELECT_ATTACHMENT = "SELECT.ATTACHMENT";
    private List<String> selectAttachment = new ArrayList<String>();

    public String parse(String listaSQL) throws SQLParserException {
        if (StringUtil.isVuota(listaSQL)) {
            return null;
        }
        List<String> listaSelectStandard = new ArrayList<String>();
        String[] insert = listaSQL.split(UNION);
        for (int i = 0; i < insert.length; i++) {
            String selectControllare = insert[i].trim();
            if (!isSelectAttachment(selectControllare)) {
                listaSelectStandard.add(selectControllare);
            }
        }
        if (listaSelectStandard.isEmpty()) {
            return null;
        }
        if (listaSelectStandard.size() == 1) {
            return listaSelectStandard.get(0);
        }
        String stringaUnion = "";
        for (int i = 0; i < listaSelectStandard.size() - 1; i++) {
            String string = insert[i];
            stringaUnion += string + UNION;
        }
        stringaUnion += listaSelectStandard.get(listaSelectStandard.size() - 1);
        return stringaUnion;
    }

    public List<String> getSelectAttachment() {
        return selectAttachment;
    }

    private boolean isSelectAttachment(String selectControllare) throws SQLParserException {
        if(!selectControllare.toUpperCase().startsWith(SELECT_ATTACHMENT)){
            return false;
        }
        int posizioneFrom = selectControllare.toUpperCase().indexOf(" FROM ");
        if(posizioneFrom==-1){
            throw new SQLParserException("La select " + selectControllare + " non contiene la clausola FROM");
        }
        String parametriSelect = selectControllare.substring(SELECT_ATTACHMENT.length(),posizioneFrom);
        if(parametriSelect.contains("*") || parametriSelect.contains(",")){
            throw new SQLParserException("La select degli attachment " + selectControllare + " può contenere solo un parametro della select.");
        }

        String selectRipulita = selectControllare.substring(SELECT_ATTACHMENT.length());
        selectRipulita = "SELECT" + selectRipulita;

        selectAttachment.add(selectRipulita);
        return true;
    }
}
