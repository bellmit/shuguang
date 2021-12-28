//package com.sofn.core.oid;
//
//import com.sofn.core.support.sequence.IdGenrator;
//import org.junit.Test;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Semaphore;
//
//import static org.junit.Assert.*;
//
///**
// * Created by wangzg on 2016/11/17.
// */
//public class IdGeneratorTest {
//
//    private static int thread_num = 50;
//    private static int client_num = 2000;
//
//    @Test
//    public void main() {
//        // TODO Auto-generated method stub
//        ExecutorService exec = Executors.newCachedThreadPool();
//        // 50个线程可以同时访问
//        final Semaphore semp = new Semaphore(thread_num);
//        // 模拟2000个客户端访问
//        for (int index = 0; index < client_num; index++) {
//            final int NO = index;
//            Runnable run = new Runnable() {
//                public void run() {
//                    try {
//                        // 获取许可
//                        semp.acquire();
//                        IdGenrator idWorker = new IdGenrator(0, 0);
//                        long id = idWorker.getId();
//                        System.out.println("第：" + NO + " 个");
//                        System.out.println(id);
//                        // Thread.sleep((long) (Math.random()) * 1000);
//                        // 释放
//                        semp.release();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            };
//            exec.execute(run);
//        }
//        // 退出线程池
//        exec.shutdown();
//}
//
//   /* @Test
//    public void main1() throws Exception {
//        ExecutorService exec = Executors.newCachedThreadPool();
//        // thread_num个线程可以同时访问
//        final Semaphore semp = new Semaphore(thread_num);
//        // 模拟client_num个客户端访问
//        for (int index = 0; index < client_num; index++) {
//            final int NO = index;
//            Runnable run = new Runnable() {
//                public void run() {
//                    try {
//                        // 获取许可
//                        semp.acquire();
//
//                        System.out.println("Thread并发>>>"+ NO);
//
//                        try {
//
//                            System.err.println("接口调用返回结果：" );
//
//                            //测试获取各种码
//                            IdGenerator idGen=new IdGenerator();
//                            //主体身份码
//                            String mainBodyIdentityCode=idGen.getMainBodyIdentityCode(IdGenerator.ServiceType.Retrospect,IdGenerator.MainBodyCategories.MainManagementBody, IdGenerator.OrganizationForms.Individual,"123456789012345678");
//                            //主体用户码
//                            String mainBodyUserCode=idGen.getMainBodyUserCode(IdGenerator.ServiceType.Retrospect,IdGenerator.MainBodyCategories.MainManagementBody, IdGenerator.OrganizationForms.Individual,"123456789012345678",1);
//                            //生产批次码
//                            String productBatchCode=idGen.getProductBatchCode(IdGenerator.ServiceType.Retrospect,IdGenerator.MainBodyCategories.MainManagementBody, IdGenerator.OrganizationForms.Individual,"123456789012345678","12345678");
//                            //产品追溯码
//                            String productTraceabilityCode=idGen.getProductTraceabilityCode(IdGenerator.ServiceType.Retrospect,IdGenerator.MainBodyCategories.MainManagementBody, IdGenerator.OrganizationForms.Individual,"123456789012345678","12345678");
//                            //入市追溯凭证
//                            String intoCityTraceability=idGen.getIntoCityTraceability(IdGenerator.ServiceType.Retrospect,IdGenerator.MainBodyCategories.MainManagementBody, IdGenerator.OrganizationForms.Individual,"123456789012345678","12345678");
//                            //临时主体身份码
//                            String temporaryIdentityCode=idGen.getTemporaryIdentityCode(IdGenerator.ServiceType.Retrospect,IdGenerator.MainBodyCategories.MainManagementBody, IdGenerator.OrganizationForms.Individual,"123456789012345678");
//                            //产品临时码
//                            String productTemporaryCode=idGen.getProductTemporaryCode(IdGenerator.ServiceType.Retrospect,IdGenerator.MainBodyCategories.MainManagementBody, IdGenerator.OrganizationForms.Individual,"123456789012345678","12345678");
//
//                            System.out.println("count:"+mainBodyIdentityCode.replace(".","").length()+"     主体身份码:"+mainBodyIdentityCode);
//                            System.out.println("count:"+mainBodyUserCode.replace(".","").length()+"     主体用户码:"+mainBodyUserCode);
//                            System.out.println("count:"+productBatchCode.replace(".","").length()+"     生产批次码:"+productBatchCode);
//                            System.out.println("count:"+productTraceabilityCode.replace(".","").length()+"     产品追溯码:"+productTraceabilityCode);
//                            System.out.println("count:"+intoCityTraceability.replace(".","").length()+"     入市追溯凭证:"+intoCityTraceability);
//                            System.out.println("count:"+temporaryIdentityCode.replace(".","").length()+"     临时主体身份码:"+temporaryIdentityCode);
//                            System.out.println("count:"+productTemporaryCode.replace(".","").length()+"     产品临时码:"+productTemporaryCode);
//                            IdGenrator idWorker = new IdGenrator(0, 0);
//                            for (int i = 0; i < 5; i++) {
//                                long id = idWorker.getId();
//                                System.out.println(id);
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                        semp.release();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            };
//            exec.execute(run);
//        }
//        // 退出线程池
//        exec.shutdown();
//    }*/
//}