package it.unibas.silvio.web.vista.pm;

import it.unibas.silvio.modello.Dati;
import it.unibas.silvio.modello.DatiCostanti;
import it.unibas.silvio.modello.DatiInterattivi;
import it.unibas.silvio.modello.DatiSQL;
import it.unibas.silvio.modello.DatoPrimitivo;
import it.unibas.silvio.persistenza.jdbc.DBConnectorFactory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.faces.model.SelectItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.component.UIDataTable;

public class DatiPM implements Serializable{
    private Dati dati = new Dati();
    private boolean datiDBSelezionati;
    private boolean datiCostantiSelezionati;
    private boolean datiInterattiviSelezionati;
    private boolean datiPayloadSelezionati;
    private DatiSQL datiDB = new DatiSQL();
    private DatiSQL datiDBFruitoreRisposta = new DatiSQL();
    private DatiSQL datiDBErogatoreRisposta = new DatiSQL();
    private DatiCostanti datiCostanti = new DatiCostanti();
    private DatiInterattivi datiInterattivi = new DatiInterattivi();
    private UIDataTable listaSelectGrid;
    private UIDataTable listaSelectGridFruitore;
    private UIDataTable listaDatiPrimitiviGrid;
    private UIDataTable listaDatiPrimitiviGridFruitore;
    private UIDataTable listaDatiPrimitiviCostantiGrid;
    private UIDataTable listaDatiPrimitiviCostantiGridFruitore;
    private List<String> listaSelect = new ArrayList<String>();
    private List<SelectItem> listaTipiDB = new ArrayList<SelectItem>();
    private List<SelectItem> listaSblocco = new ArrayList<SelectItem>();
    private DatoPrimitivo datoPrimitivo = new DatoPrimitivo();
    private DatoPrimitivo datoPrimitivoCostante = new DatoPrimitivo();
    private String select;
    private String selectEliminare;
    private String payloadCostante;
    private DatoPrimitivo datoInterattivoEliminare;
    private DatoPrimitivo datoCostanteEliminare;    
    private Log logger = LogFactory.getLog(this.getClass());

    public Dati getDati() {
        return dati;
    }

    public void setDati(Dati dati) {
        this.dati = dati;
    }

    public DatiCostanti getDatiCostanti() {
        return datiCostanti;
    }

    public void setDatiCostanti(DatiCostanti datiCostanti) {
        this.datiCostanti = datiCostanti;
    }

    public boolean isDatiCostantiSelezionati() {
        return datiCostantiSelezionati;
    }

    public void setDatiCostantiSelezionati(boolean datiCostantiSelezionati) {
        this.datiCostantiSelezionati = datiCostantiSelezionati;
    }

    public DatiSQL getDatiDB() {
        return datiDB;
    }

    public void setDatiDB(DatiSQL datiDB) {
        this.datiDB = datiDB;
    }

    public boolean isDatiDBSelezionati() {
        return datiDBSelezionati;
    }

    public void setDatiDBSelezionati(boolean datiDBSelezionati) {
        this.datiDBSelezionati = datiDBSelezionati;
    }

    public DatiInterattivi getDatiInterattivi() {
        return datiInterattivi;
    }

    public void setDatiInterattivi(DatiInterattivi datiInterattivi) {
        this.datiInterattivi = datiInterattivi;
    }

    public boolean isDatiInterattiviSelezionati() {
        return datiInterattiviSelezionati;
    }

    public void setDatiInterattiviSelezionati(boolean datiInterattiviSelezionati) {
        this.datiInterattiviSelezionati = datiInterattiviSelezionati;
    }

    public DatoPrimitivo getDatoPrimitivo() {
        return datoPrimitivo;
    }

    public void setDatoPrimitivo(DatoPrimitivo datoPrimitivo) {
        this.datoPrimitivo = datoPrimitivo;
    }

    public DatoPrimitivo getDatoPrimitivoCostante() {
        return datoPrimitivoCostante;
    }

    public void setDatoPrimitivoCostante(DatoPrimitivo datoPrimitivoCostante) {
        this.datoPrimitivoCostante = datoPrimitivoCostante;
    }

    public UIDataTable getListaDatiPrimitiviCostantiGrid() {
        return listaDatiPrimitiviCostantiGrid;
    }

    public void setListaDatiPrimitiviCostantiGrid(UIDataTable listaDatiPrimitiviCostantiGrid) {
        this.listaDatiPrimitiviCostantiGrid = listaDatiPrimitiviCostantiGrid;
    }

    public UIDataTable getListaDatiPrimitiviGrid() {
        return listaDatiPrimitiviGrid;
    }

    public void setListaDatiPrimitiviGrid(UIDataTable listaDatiPrimitiviGrid) {
        this.listaDatiPrimitiviGrid = listaDatiPrimitiviGrid;
    }

    public List<String> getListaSelect() {
        return listaSelect;
    }

    public void setListaSelect(List<String> listaSelect) {
        this.listaSelect = listaSelect;
    }

    public UIDataTable getListaSelectGrid() {
        return listaSelectGrid;
    }

    public void setListaSelectGrid(UIDataTable listaSelectGrid) {
        this.listaSelectGrid = listaSelectGrid;
    }
    
    public List<SelectItem> getListaTipiDB() {
        if(listaTipiDB.isEmpty()){
            Set<String> connettori = DBConnectorFactory.getDBSupportati();
            for (String string : connettori) {
                listaTipiDB.add(new SelectItem(string));
            }
        }
        return listaTipiDB;
    }

    public void setListaTipiDB(List<SelectItem> listaTipiDB) {
        this.listaTipiDB = listaTipiDB;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getSelectEliminare() {
        return selectEliminare;
    }

    public void setSelectEliminare(String selectEliminare) {
        this.selectEliminare = selectEliminare;
    }

    public DatoPrimitivo getDatoCostanteEliminare() {
        return datoCostanteEliminare;
    }

    public void setDatoCostanteEliminare(DatoPrimitivo datoCostanteEliminare) {
        this.datoCostanteEliminare = datoCostanteEliminare;
    }

    public DatoPrimitivo getDatoInterattivoEliminare() {
        return datoInterattivoEliminare;
    }

    public void setDatoInterattivoEliminare(DatoPrimitivo datoInterattivoEliminare) {
        this.datoInterattivoEliminare = datoInterattivoEliminare;
    }

    public List<SelectItem> getListaSblocco() {
        if(listaSblocco.isEmpty()){
            listaSblocco.add(new SelectItem("AUTOMATICA"));
            listaSblocco.add(new SelectItem("MANUALE"));
        }
        return listaSblocco;
    }

    public void setListaSblocco(List<SelectItem> listaSblocco) {
        this.listaSblocco = listaSblocco;
    }

    public UIDataTable getListaSelectGridFruitore() {
        return listaSelectGridFruitore;
    }

    public void setListaSelectGridFruitore(UIDataTable listaSelectGridFruitore) {
        this.listaSelectGridFruitore = listaSelectGridFruitore;
    }

    public UIDataTable getListaDatiPrimitiviCostantiGridFruitore() {
        return listaDatiPrimitiviCostantiGridFruitore;
    }

    public void setListaDatiPrimitiviCostantiGridFruitore(UIDataTable listaDatiPrimitiviCostantiGridFruitore) {
        this.listaDatiPrimitiviCostantiGridFruitore = listaDatiPrimitiviCostantiGridFruitore;
    }

    public UIDataTable getListaDatiPrimitiviGridFruitore() {
        return listaDatiPrimitiviGridFruitore;
    }

    public void setListaDatiPrimitiviGridFruitore(UIDataTable listaDatiPrimitiviGridFruitore) {
        this.listaDatiPrimitiviGridFruitore = listaDatiPrimitiviGridFruitore;
    }

    public DatiSQL getDatiDBFruitoreRisposta() {
        return datiDBFruitoreRisposta;
    }

    public void setDatiDBFruitoreRisposta(DatiSQL datiDBFruitoreRisposta) {
        this.datiDBFruitoreRisposta = datiDBFruitoreRisposta;
    }

    public DatiSQL getDatiDBErogatoreRisposta() {
        return datiDBErogatoreRisposta;
    }

    public void setDatiDBErogatoreRisposta(DatiSQL datiDBErogatoreRisposta) {
        this.datiDBErogatoreRisposta = datiDBErogatoreRisposta;
    }

    public boolean isDatiPayloadSelezionati() {
        return datiPayloadSelezionati;
    }

    public void setDatiPayloadSelezionati(boolean datiPayloadSelezionati) {
        this.datiPayloadSelezionati = datiPayloadSelezionati;
    }

    public String getPayloadCostante() {
        return payloadCostante;
    }

    public void setPayloadCostante(String payloadCostante) {
        this.payloadCostante = payloadCostante;
    }    
}
