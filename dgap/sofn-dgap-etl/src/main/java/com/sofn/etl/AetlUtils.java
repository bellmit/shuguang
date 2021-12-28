package com.sofn.etl;

import com.apusic.etl.server.api.IRepositoryApi;
import com.apusic.etl.server.api.ITransApi;
import com.apusic.etl.server.api.client.IAETLServerApiClient;
import com.apusic.etl.server.api.client.factory.AETLServerApiClientFactory;
import com.apusic.etl.server.api.exception.AETLServerApiException;
import com.apusic.etl.server.api.model.WebResult;
import com.apusic.etl.server.api.model.repository.ResourceNode;
import com.apusic.etl.server.api.model.repository.ResourceType;
import java.util.List;

/** Created by helong on 17-5-23. */
public class AetlUtils {

  /**
   * 创建客服端
   *
   * @param host
   * @param port
   * @param username
   * @param password
   * @return
   */
  public static IAETLServerApiClient getClient(
      String host, int port, String username, String password) {
    return AETLServerApiClientFactory.createServletApiClient(host, port, username, password);
  }

  /**
   * 添加转换到默认资源库
   *
   * @param client
   * @param path
   * @param transformation
   * @return
   * @throws AETLServerApiException
   */
  public static WebResult addTransformation(
      IAETLServerApiClient client, String transformation, String path)
      throws AETLServerApiException {
    ITransApi transApi = client.getTransApi();
    return transApi.addTransToRepo(transformation, path);
  }

  /**
   * 部署转换
   *
   * @param client
   * @param path
   * @param transName
   * @return
   * @throws AETLServerApiException
   */
  public static WebResult deployTransFromLocalRepo(
      IAETLServerApiClient client, String transName, String path, String metaID)
      throws AETLServerApiException {
    ITransApi transApi = client.getTransApi();
    WebResult webResult = transApi.deployTransFromLocalRepo(transName, path, metaID);
    return webResult;
  }

  /**
   * 卸载转换
   *
   * @param client
   * @param transName
   * @param relRepoPath
   * @param transMetaId
   * @return
   * @throws AETLServerApiException
   */
  public static WebResult unDeployTrans(
      IAETLServerApiClient client, String transName, String relRepoPath, String transMetaId)
      throws AETLServerApiException {
    ITransApi transApi = client.getTransApi();
    WebResult webResult = transApi.unDeployTransFromServer(transName, relRepoPath, null);
    return webResult;
  }

  /**
   * 移除转换
   *
   * @param client
   * @param transName
   * @param relRepoPath
   * @return
   * @throws AETLServerApiException
   */
  public static WebResult removeTransFromRepo(
      IAETLServerApiClient client, String transName, String relRepoPath)
      throws AETLServerApiException {
    ITransApi transApi = client.getTransApi();
    WebResult webResult = transApi.removeTransFromRepo(transName, relRepoPath);
    return webResult;
  }

  /**
   * 转换列表
   *
   * @param client
   * @param path
   * @return
   * @throws AETLServerApiException
   */
  public static List<ResourceNode> listTransformation(IAETLServerApiClient client, String path)
      throws AETLServerApiException {
    IRepositoryApi repositoryApi = client.getRepositoryApi();
    List<ResourceNode> allResourceNode =
        repositoryApi.getAllResourceNode(path, ResourceType.TRANSFORMATION, null);
    return allResourceNode;
  }

  public static WebResult execTrans(IAETLServerApiClient client, String transName, String metaid)
      throws AETLServerApiException {
    ITransApi transApi = client.getTransApi();
    WebResult webResult = transApi.execTransFromServer(transName, metaid, null);
    return webResult;
  }
}
