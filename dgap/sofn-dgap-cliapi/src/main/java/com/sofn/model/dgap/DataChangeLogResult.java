package com.sofn.model.dgap;

/**
 * Created by weiq on 2016/12/7.
 */
public class DataChangeLogResult extends DataExportResult {
    private String mark;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }


    @Override
    public String toString() {
        return "DataChangeLogResult{" +
                "mark='" + mark + '\'' +
                '}';
    }
}
