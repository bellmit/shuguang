package com.sofn;

import com.sofn.common.clearlogicaldeletedata.ClearLogicalDeleteData;
import com.sofn.common.utils.RedisHelper;
import com.sofn.common.utils.RegionCacheUtils;
import com.sofn.sys.SysServiceApplication;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SysServiceApplication.class)
public class SysServiceApplicationTests {

	@Autowired(required= false)
	private ClearLogicalDeleteData clearLogicalDeleteData;

	@Autowired
	RedisHelper redisHelper;

	@Test
	public void contextLoads() {
		String[] regionCodesByNames = RegionCacheUtils.getRegionCodesByNames("北京市","市辖区","东城区");
		System.out.println(regionCodesByNames);


	}

	@Test
	@Rollback
	public void testClearDeleteData(){
		if(clearLogicalDeleteData != null){
			clearLogicalDeleteData.clearData();
		}else{
			log.info("未获取到清理数据对象");
		}

	}

	@Test
	@Rollback
	public void ddd(){
		redisHelper.delByPattern("");

	}


}
