package com.sofn.model.dgap;

//import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by helong on 16-11-22.
 */
public class RowData {
    //@XmlElement(required=true)
    private String id;
    private List<FieldData> fieldsData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<FieldData> getFieldsData() {
        return fieldsData;
    }

    public void setFieldsData(List<FieldData> fieldsData) {
        this.fieldsData = fieldsData;
    }

    @Override
    public String toString() {
        return "RowData{" +
                "id='" + id + '\'' +
                ", fieldsData=" + fieldsData +
                '}';
    }
}
