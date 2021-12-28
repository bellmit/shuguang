package com.sofn.fdpi;
/**
 * 项目启动后加载缓存数据,后面改成调用TbCompController中loadCompAndUserDataToCache接口
 * wXY
 * 2020-7-30 09:28:22
 */
public class LoadCacheDataRunner{

}

//@Component
//@Order(value = 1)
//public class LoadCacheDataRunner implements CommandLineRunner {
//
//    @Autowired
//    private TbCompService tbCompService;
//
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println(">>>>>>>>>>>>>加载缓存数据开始<<<<<<<<<<<<<<<");
//        List<CompAndUserVo> list = tbCompService.listForAllCompAndUser();
//        if (!CollectionUtils.isEmpty(list)) {
//            list.forEach(v -> {
//                RedisCompUtil.addRegisterCompInCacheForHash(v.getCompName());
//                RedisCompUtil.addRegisterUserNameInCacheForHash(v.getUserName());
//            });
//        }
//        System.out.println(">>>>>>>>>>>>>加载缓存数据结束<<<<<<<<<<<<<<<");
//    }
//}
