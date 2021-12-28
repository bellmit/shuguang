package com.sofn.model.dgap;

//import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by CHENGDONG on 18-12-19.
 */
public class OpretaRowData extends RowData{

    private String operata;

    public String getOperata() {
        return operata;
    }

    public void setOperata(String operata) {
        this.operata = operata;
    }

    @Override
    public String toString() {
        return "OpretaRowData{" +
                "operata='" + operata + '\'' +
                '}';
    }
}
