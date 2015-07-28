package it.unibas.icar.updatefreesbee.modello;

import java.util.HashMap;
import java.util.Map;

public class Modello {

    private Map<String, Object> mappaBean = new HashMap<String, Object>();

    public void putBean(String key, Object value) {
        this.mappaBean.put(key, value);
    }

    public Object getBean(String key) {
        return mappaBean.get(key);
    }
}
