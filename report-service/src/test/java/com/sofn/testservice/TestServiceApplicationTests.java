package com.sofn.reportservice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofn.common.model.Dict;
import com.sofn.report.ReportServiceApplication;
import com.sofn.report.mapper.DictMapper;
import com.sofn.report.model.TDict;
import com.sofn.report.model.TOrder;
import com.sofn.report.model.TOrderItem;
import com.sofn.report.service.OrderItemService;
import com.sofn.report.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReportServiceApplication.class)
public class TestServiceApplicationTests {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    DictMapper dictMapper;

    @Test
    public void contextLoads() throws InterruptedException {
        TOrder order0 = new TOrder();
        order0.setOrderExpress("顺丰");
        order0.setOrderPayWay("微信");
        order0.setOrderPayDatetime(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        order0.setOrderPlaceDatetime(calendar.getTime());
        order0.setOrderStatus("已付款");

        order0.setUserId(new Random().nextInt(10));
        orderService.save(order0);

        order0.setUserId(new Random().nextInt(10));
        orderService.save(order0);

        order0.setUserId(new Random().nextInt(10));
        orderService.save(order0);

        order0.setUserId(new Random().nextInt(10));
        orderService.save(order0);

        order0.setUserId(new Random().nextInt(10));
        orderService.save(order0);

        order0.setUserId(new Random().nextInt(10));
        orderService.save(order0);

        order0.setUserId(new Random().nextInt(10));
        orderService.save(order0);

        order0.setUserId(new Random().nextInt(10));
        orderService.save(order0);

        order0.setUserId(new Random().nextInt(10));
        orderService.save(order0);

        order0.setUserId(new Random().nextInt(10));
        orderService.save(order0);

    }

    @Test
    public void getOrder(){
        List<TOrder> list = orderService.list(new QueryWrapper<TOrder>()
            .eq("user_id",6)
            .eq("order_id",528264895506939904L)
        );

        List<TOrder> list1 = orderService.list(new QueryWrapper<TOrder>()
            .eq("user_id",6)
        );
    }

    @Test
    public void testAddBindOrder(){
        TOrderItem item = new TOrderItem();
        item.setOrderId(528263046284443648L);
        item.setUserId(6);
        item.setGoodName("钢铁是怎样炼成的");

        orderItemService.save(item);
    }

    @Test
    public void testSelectBindOrder(){
        List<TOrderItem> itemList = orderItemService.list(new QueryWrapper<TOrderItem>()
            .eq("user_id", 6)
        );

        List<TOrder> orderList = orderService.getOrderItemList(528263046284443648L);
    }

    @Test
    public void testBroadcastDict(){
        TDict dict = new TDict();
        dict.setDictName("一千");
        dict.setDictValue("1000");

        dictMapper.insert(dict);
    }

    @Test
    public void testReadBroadcastDict(){
        TDict tDict = dictMapper.selectOne(new QueryWrapper<TDict>().eq("dict_value","1000"));
        System.out.println(tDict.toString());
    }
}
