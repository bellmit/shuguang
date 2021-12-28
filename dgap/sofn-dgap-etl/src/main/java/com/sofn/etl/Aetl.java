package com.sofn.etl;

import com.apusic.etl.server.api.IRepositoryApi;
import com.apusic.etl.server.api.client.IAETLServerApiClient;
import com.apusic.etl.server.api.client.factory.AETLServerApiClientFactory;
import com.apusic.etl.server.api.exception.AETLServerApiException;
import com.apusic.etl.server.api.model.repository.ResourceNode;
import com.apusic.etl.server.api.model.repository.ResourceType;
import java.io.IOException;
import java.util.List;

/** Created by wangt on 2017/5/18. */
public class Aetl {
  public static void main(String[] args) throws AETLServerApiException, IOException {
    IAETLServerApiClient client =
        AETLServerApiClientFactory.createServletApiClient("localhost", 6888, "aetl", "aetl");
    //        ISlaveServerApi serverApi = client.getSlaveServerApi();
    //        SlaveServerStatus status = serverApi.getSlaveServerStatus();
    //        List<JobStatus> jobStatusList = status.getJobStatusList();
    //        for (JobStatus job:jobStatusList){
    //            System.out.println(job.getJobName());
    //        }
    //        List<TransStatus> transStatusList = status.getTransStatusList();
    //        for(TransStatus tra:transStatusList){
    //            System.out.println(tra.getTransName());
    //        }
    //
    //        //获取指定资源的元数据xml
    IRepositoryApi repositoryApi = client.getRepositoryApi();
    //        String ktrResource = repositoryApi.getResource("extractData", "/",
    // ResourceType.TRANSFORMATION, null);
    //        System.out.println("TRA的元数据xml" + ktrResource);
    //        String kjbResource = repositoryApi.getResource("0522_作业", "/", ResourceType.JOB,
    // null);
    //        System.out.println("JOB的元数据xml" + kjbResource);
    // 获取指定目录直接子资源节点列表
    List<ResourceNode> allResourceNode =
        repositoryApi.getAllResourceNode("/", ResourceType.TRANSFORMATION, null);

    String path = "";
    for (ResourceNode resourceNode : allResourceNode) {
      path = resourceNode.getPath();
      System.out.println("资源库所有任务:" + path.substring(path.lastIndexOf("/") + 1));
    }

    //        //获取某个资源的树结构的列表(包括资源本身)
    //        List<ResourceNode> extractData = repositoryApi.getResourceNodeTreePath("extractData",
    // "/", ResourceType.TRANSFORMATION, null);
    //        for (ResourceNode node : extractData) {
    //            System.out.println("资源的树结构的列表" + node.getName() + "-------" + node.getPath());
    //        }
    //        //获取指定目录直接子资源节点列表
    //        List<ResourceNode> resourceNodeList = repositoryApi.getResourceNodeList("/",
    // ResourceType.TRANSFORMATION, null);
    //        for (ResourceNode node : resourceNodeList) {
    //            System.out.println("直接子资源节点列表" + node.getName());
    //        }
    //
    //        ITransApi transApi = client.getTransApi();
    //        byte[] transImage = transApi.getTransImage("extractData");
    //        System.out.println("转换实例信息的图像字节"+transImage.length);
    //        //获取转换调度实例信息
    //        List<ExecuteInstance> executeInstanceList = transApi.getTransInstances("extractData");
    //        for (ExecuteInstance executeInstance : executeInstanceList) {
    //            //获取某个转换中处于正在运行状态的实例的个数
    //            int runningCount = transApi.getTransRunningCount("extractData",
    // executeInstance.getMetaId());
    //            System.out.println("转换中处于正在运行状态的实例的个数" + runningCount);
    //            System.out.println("------转换的实例信息列表" + executeInstance.getName()
    //                    + "------转换的实例信息列表的metaId:" + executeInstance.getMetaId()
    //                    + "------转换的实例信息列表的instId:" + executeInstance.getInstId());
    //            //获取转换运行实例的状态
    //            TransStatus transStatus = transApi.getTransStatus("extractData",
    // executeInstance.getInstId());
    //            System.out.println("转换实例运行状态:" + transStatus);
    //        }
    //// 获取作业调度实例信息
    //        List<ExecuteInstance> jobInstances = client.getJobApi().getJobInstances("0522_作业");
    //        for (ExecuteInstance executeInstance : jobInstances) {
    //            //获取某个转换中处于正在运行状态的实例的个数
    ////            int runningCount = transApi.getTransRunningCount("0522_作业",
    // executeInstance.getMetaId());
    ////            System.out.println("作业中处于正在运行状态的实例的个数" + runningCount);
    //            System.out.println("------作业的实例信息列表" + executeInstance.getName()
    //                    + "------作业的实例信息列表的metaId:" + executeInstance.getMetaId()
    //                    + "------作业的实例信息列表的instId:" + executeInstance.getInstId());
    //
    //            //获取某个作业所有实例的状态的 Map
    //            Map<String, JobStatus> statusMap = client.getJobApi().getJobStatusMap("0522_作业",
    // executeInstance.getMetaId());
    //            for (Map.Entry<String,JobStatus> entry:statusMap.entrySet()){
    //                System.out.println("key为:"+entry.getKey()+"值为:"+entry.getValue());
    //            }
    //            //获取转换运行实例的状态
    //            JobStatus jobStatus = client.getJobApi().getJobStatus("0522_作业",
    // executeInstance.getInstId());
    //            System.out.println("实例运行状态:" + jobInstances);
    //            String statusDesc = executeInstance.getStatusDesc();
    //            System.out.println("运行实例状态:"+statusDesc);
    //        }
    //
    //        //添加job到资源库
    //        IJobApi jobApi = client.getJobApi();
    ////        WebResult result1 = jobApi.unDeployJob("0522_作业", "/", null);
    ////        System.out.println(result1);
    //
    //        File transFile = new File("C:\\Users\\wangt\\Desktop\\AETL_REP\\0522_作业.kjb");
    //        WebResult result =
    //                jobApi.addJobToRepo(FileUtils.readFileToString(transFile,
    //                        "UTF-8"), null);
    // 部署server本地资源库的作业
    //        WebResult webResult = jobApi.deployJobFromLocalRepo("0522_作业", null, null);
    //        System.out.println(webResult);
    //        //运行server的job
    //        WebResult webResult1 = jobApi.execJobFromServer("0522_作业", null);
    //        System.out.println("运行job的结果信息"+webResult1);
    //        //获取job运行实例的个数
    //        List<ExecuteInstance> instances = jobApi.getJobInstances("0522_作业");
    //        System.out.println("运行实例的个数:"+instances.size());
    //        byte[] image = jobApi.getJobImage("0522_作业");
    //        System.out.println("job图像字节信息"+image.length);
  }
}
