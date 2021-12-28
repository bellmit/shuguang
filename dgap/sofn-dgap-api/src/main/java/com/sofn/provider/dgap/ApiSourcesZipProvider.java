package com.sofn.provider.dgap;

import com.sofn.exception.dgap.DataImportApplyExeption;
import com.sofn.model.dgap.DataImportResult;
import com.sofn.model.dgap.RowData;

import java.io.IOException;
import java.util.List;

public interface ApiSourcesZipProvider {

    byte[] getZipFileContent(String connectionURL, String username, String password,
                             String schema, String[] tableNames) throws IOException;

}
