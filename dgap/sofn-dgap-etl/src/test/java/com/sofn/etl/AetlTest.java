//package com.sofn.etl;
//
//import static org.junit.Assert.*;
//
//import com.apusic.etl.server.api.IRepositoryApi;
//import com.apusic.etl.server.api.ITransApi;
//import com.apusic.etl.server.api.client.IAETLServerApiClient;
//import com.apusic.etl.server.api.client.factory.AETLServerApiClientFactory;
//import com.apusic.etl.server.api.exception.AETLServerApiException;
//import com.apusic.etl.server.api.model.WebResult;
//import com.apusic.etl.server.api.model.repository.ResourceNode;
//import com.apusic.etl.server.api.model.repository.ResourceType;
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//import org.apache.commons.io.FileUtils;
//import org.junit.Before;
//import org.junit.Test;
//
///** Created by helong on 17-5-19. */
//public class AetlTest {
//
//  IAETLServerApiClient client;
//
//  @Before
//  public void setUp() throws Exception {
//    client = AETLServerApiClientFactory.createServletApiClient("10.0.50.10", 6888, "aetl", "aetl");
//  }
//
//  @Test
//  public void testTransList() throws AETLServerApiException {
//    IRepositoryApi repositoryApi = client.getRepositoryApi();
//    if (repositoryApi == null) return;
//
//    List<ResourceNode> allResourceNode =
//        repositoryApi.getAllResourceNode(null, ResourceType.TRANSFORMATION, null);
//    if (allResourceNode == null) return;
//
//    for (ResourceNode node : allResourceNode) {
//      System.out.println(node.getId());
//      System.out.println(node.getName());
//      System.out.println(node.getPath());
//      System.out.println(node.getDesc());
//      System.out.println(node.getModifiedDate());
//      System.out.println(node.getModifiedUser());
//      System.out.println(node.getType());
//      System.out.println(node.getVersion());
//    }
//  }
//
//  @Test
//  public void testAddTrans() throws AETLServerApiException, IOException {
//    ITransApi transApi = client.getTransApi();
//    File transFile = new File("/home/helong/he/lky/csv2db.ktr");
//    WebResult result =
//        transApi.addTransToRepo(FileUtils.readFileToString(transFile, "UTF-8"), "/helong/");
//    System.out.println("result:" + result.toString());
//  }
//
//  @Test
//  public void testDeployTrans() throws AETLServerApiException, IOException {
//    ITransApi transApi = client.getTransApi();
//    WebResult result = transApi.deployTransFromLocalRepo("csv2db", "/helong/", null);
//    System.out.println("result:" + result.toString());
//
//    result = transApi.deployTransFromLocalRepo("csv2db", "/helong/", null);
//    System.out.println("result:" + result.toString());
//  }
//
//  @Test
//  public void testRunTrans() throws AETLServerApiException, IOException {
//    ITransApi transApi = client.getTransApi();
//    WebResult result =
//        transApi.execTransFromServer("csv2db", "edc4fa43-0ed5-4000-a010-7e6cd963221d", null);
//    System.out.println("result:" + result.toString());
//
//    result =
//        transApi.deployTransFromLocalRepo("csv2db", "274ce250-8a84-4209-a6f1-569d760516ea", null);
//    System.out.println("result:" + result.toString());
//
//    result = transApi.execTransFromServer("csv2db", "edc4fa43-0ed5-4000-a010-7e6cd963221d", null);
//    System.out.println("result:" + result.toString());
//
//    result =
//        transApi.deployTransFromLocalRepo("csv2db", "274ce250-8a84-4209-a6f1-x69d760516ea", null);
//    System.out.println("result:" + result.toString());
//
//    result = transApi.deployTransFromLocalRepo(null, "274ce250-8a84-4209-a6f1-569d760516ea", null);
//    System.out.println("result:" + result.toString());
//  }
//
//  @Test
//  public void testUndeployTrans() throws AETLServerApiException, IOException {
//    ITransApi transApi = client.getTransApi();
//    WebResult result =
//        transApi.unDeployTransFromServer(
//            "csv2db", "/helong/", "ab800e48-41cc-4f42-aaa0-5e73543b1a26");
//    System.out.println("result:" + result.toString());
//    // "edc4fa43-0ed5-4000-a010-7e6cd963221d"
//    // "274ce250-8a84-4209-a6f1-569d760516ea"
//  }
//
//  @Test
//  public void testDeleteTrans() throws AETLServerApiException, IOException {
//    ITransApi transApi = client.getTransApi();
//    WebResult result = transApi.removeTransFromRepo("csv2db", "/helong/");
//    System.out.println("result:" + result.toString());
//  }
//
//  @Test
//  public void testRepository() throws AETLServerApiException {}
//}
