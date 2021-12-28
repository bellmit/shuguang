package com.sofn.web.dgap;

import com.sofn.model.dgap.DataImportResult;
import com.sofn.wsClient.samplebeans.GreenCertification;
import com.sofn.wsClient.ex.DataxImportClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/spyb/dataimport")
public class SpybImportDemoController {

  @PostMapping(value = "/addData")
  public DataImportResult addData(@RequestParam String token,
                                  @RequestParam String serviceUrl,
                                  @RequestBody GreenCertification data) {
    DataxImportClient client = new DataxImportClient(serviceUrl);
    DataImportResult result = client.addData(token, data);
    return result;
  }

  @PostMapping(value = "/updateData")
  public DataImportResult updateData(@RequestParam String token,
                                  @RequestParam String serviceUrl,
                                  @RequestBody GreenCertification data) {
    DataxImportClient client = new DataxImportClient(serviceUrl);
    DataImportResult result = client.updateData(token, data);
    return result;
  }

  @PostMapping(value = "/deleteData")
  public DataImportResult deleteData(@RequestParam String token,
                                  @RequestParam String serviceUrl,
                                  @RequestBody GreenCertification data) {
    DataxImportClient client = new DataxImportClient(serviceUrl);
    DataImportResult result = client.deleteData(token, data);
    return result;
  }

  @PostMapping(value = "/addBatch")
  public DataImportResult addBatch(@RequestParam String token,
                                     @RequestParam String serviceUrl,
                                     @RequestBody List<GreenCertification> data) {
    DataxImportClient client = new DataxImportClient(serviceUrl);
    DataImportResult result = client.addBatch(token, data);
    return result;
  }

}
