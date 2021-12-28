package com.sofn.provider.dgap;

import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.UUIDUtil;
import com.sofn.dao.dgap.DataImportExpandMapper;
import com.sofn.dao.dgap.TDgapResourceApplicationExpandMapper;
import com.sofn.exception.dgap.DataImportApplyExeption;
import com.sofn.model.dgap.Constants;
import com.sofn.model.dgap.DataImportResult;
import com.sofn.model.dgap.FieldData;
import com.sofn.model.dgap.RowData;
import com.sofn.model.generator.TDgapDataImportField;
import com.sofn.model.generator.TDgapDataImportTable;
import com.sofn.service.apigen.ApiGenerator;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@DubboService(interfaceClass = ApiSourcesZipProvider.class)
public class ApiSourcesZipProviderImpl implements ApiSourcesZipProvider {

  private static final Logger logger =
          LoggerFactory.getLogger(ApiSourcesZipProviderImpl.class.getName());

  @Override
  public byte[] getZipFileContent(String connectionURL, String username, String password,
                                  String schema, String[] tableNames) throws IOException {
      return ApiGenerator.genApiJar(connectionURL,username,password,
              "com.sofn.wsClient.beans","/tmp/dgap-api",schema,tableNames,true);
  }
}
