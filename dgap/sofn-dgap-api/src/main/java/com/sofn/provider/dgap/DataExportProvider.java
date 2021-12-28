package com.sofn.provider.dgap;

import com.sofn.model.dgap.DataExportResult;

import java.util.List;

/**
 * @description:
 * @author: dongql
 * @date: 2017/8/31 15:31
 */
public interface DataExportProvider {
    DataExportResult getData(String accessToken, int start, int finish, List<String> fields, String condition);
    public String fixAppliedData(String resourceId);
}
