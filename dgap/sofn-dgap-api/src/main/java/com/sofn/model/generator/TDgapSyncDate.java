package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

@SuppressWarnings("serial")
public class TDgapSyncDate extends BaseModel {
    private String data;

    private String lastSyncDate;

    private String mark;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLastSyncDate() {
        return lastSyncDate;
    }

    public void setLastSyncDate(String lastSyncDate) {
        this.lastSyncDate = lastSyncDate;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TDgapSyncDate that = (TDgapSyncDate) o;

        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (lastSyncDate != null ? !lastSyncDate.equals(that.lastSyncDate) : that.lastSyncDate != null) return false;
        return mark != null ? mark.equals(that.mark) : that.mark == null;

    }

    @Override
    public int hashCode() {
        int result = data != null ? data.hashCode() : 0;
        result = 31 * result + (lastSyncDate != null ? lastSyncDate.hashCode() : 0);
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TDgapSyncDate{" +
                "data='" + data + '\'' +
                ", lastSyncDate='" + lastSyncDate + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }
}