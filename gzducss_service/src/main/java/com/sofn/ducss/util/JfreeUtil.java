package com.sofn.ducss.util;

import lombok.extern.slf4j.Slf4j;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static com.xiaoleilu.hutool.util.ClassUtil.getClassLoader;

/**
 * @ClassName JfreeUtil
 * @Description 各种报表图的生成
 * @Author Chlf
 * @Date 2020/11/24 9:14
 * Version1.0
 **/
@Slf4j
public class JfreeUtil {
    //private static String tempImgPath="D:/tempJfree.jpg";
    private static final String tempImgPath = getClassLoader().getResource("static/").getPath();

    /**
     * 将图片转化为字节数组
     * @return 字节数组
     */
    private static byte[] imgToByte(String filePath){
        File file = new File(filePath);
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        //删除临时文件
        file.delete();
        return buffer;
    }

    /**
    *@Description //饼状图
    *@Date 10:12 2020/11/24
    *@Param [title-图标题, datas-显示数据, width-所保存饼状图的宽, height-所保存饼状图的高]
    *@Return base64 String
    **/
    public static byte[] pieChart(String title, Map<String, Double> datas, int width, int height) {

        //创建主题样式
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        //设置标题字体
        standardChartTheme.setExtraLargeFont(new Font("宋体", Font.BOLD, 20));
        //设置图例的字体
        standardChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));
        //设置轴向的字体
        standardChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15));
        //设置主题样式
        ChartFactory.setChartTheme(standardChartTheme);

        //根据jfree生成一个本地饼状图
        DefaultPieDataset pds = new DefaultPieDataset();
        datas.forEach(pds::setValue);
        //图标标题、数据集合、是否显示图例标识、是否显示tooltips、是否支持超链接
        JFreeChart chart = ChartFactory.createPieChart(title, pds, true, false, false);
        //设置抗锯齿
        chart.setTextAntiAlias(false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setNoDataMessage("暂无数据");
        //忽略无值的分类
        plot.setIgnoreNullValues(true);
        plot.setBackgroundAlpha(0f);
        //设置标签阴影颜色
        plot.setShadowPaint(new Color(255,255,255));
        //设置标签生成器(默认{0})
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({2})"));
        String filePath = tempImgPath +"/" + (int) (Math.random() * 100000) + "ducss.png";
        try {
            ChartUtils.saveChartAsJPEG(new File(filePath), chart, width, height);
        } catch (IOException e1) {
            log.error("生成饼状图失败！");
        }
        return imgToByte(filePath);

    }

    /**
     * 提供静态方法：获取报表图形2：柱状图
     * @param title        标题
     * @param datas        数据
     * @param type         分类（第一季，第二季.....）
     * @param danwei       柱状图的数量单位
     */
    public static byte[] barChart(String title,Map<String,Map<String,Double>> datas,String type,String danwei,  int width, int height){
        //种类数据集
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        //获取迭代器：
        Set<Map.Entry<String, Map<String, Double>>> set1 =  datas.entrySet();    //总数据
        Iterator iterator1 = set1.iterator();                        //第一次迭代
        Iterator iterator2 = null;
        HashMap<String, Double> map =  null;
        Set<Map.Entry<String,Double>> set2=null;
        Map.Entry entry1=null;
        Map.Entry entry2=null;

        while(iterator1.hasNext()){
            entry1=(Map.Entry) iterator1.next();                    //遍历分类

            map=(HashMap<String, Double>) entry1.getValue();   //得到每次分类的详细信息
            set2=map.entrySet();                               //获取键值对集合
            iterator2=set2.iterator();                         //再次迭代遍历
            while (iterator2.hasNext()) {
                entry2= (Map.Entry) iterator2.next();
                ds.setValue(Double.parseDouble(entry2.getValue().toString()),//每次统计数量
                        entry2.getKey().toString(),                          //名称
                        entry1.getKey().toString());                         //分类
            }
        }

        //创建主题样式
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        //设置标题字体
        standardChartTheme.setExtraLargeFont(new Font("宋体", Font.BOLD, 20));
        //设置图例的字体
        standardChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));
        //设置轴向的字体
        standardChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15));
        //设置主题样式
        ChartFactory.setChartTheme(standardChartTheme);
        //创建柱状图,柱状图分水平显示和垂直显示两种
        JFreeChart chart = ChartFactory.createBarChart(title, type, danwei, ds, PlotOrientation.VERTICAL, true, true, true);

//        //设置整个图片的标题字体
//        chart.getTitle().setFont(font);
//
//        //设置提示条字体
//        font = new Font("宋体", Font.BOLD, 15);
//        chart.getLegend().setItemFont(font);

        //得到绘图区
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        //得到绘图区的域轴(横轴),设置标签的字体
//        plot.getDomainAxis().setLabelFont(font);
//
//        //设置横轴标签项字体
//        plot.getDomainAxis().setTickLabelFont(font);
//
//        //设置范围轴(纵轴)字体
//        plot.getRangeAxis().setLabelFont(font);

        LegendTitle legendTitle = new LegendTitle(plot);//创建图例
        legendTitle.setPosition(RectangleEdge.TOP); //设置图例的位置
        plot.setForegroundAlpha(1.0f);

        String filePath = tempImgPath +"/" + (int) (Math.random() * 100000) + "ducss.png";
        try {
            ChartUtils.saveChartAsJPEG(new File(filePath), chart, width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgToByte(filePath);

    }

    public static void main(String[] args) {
//        Map<String, Integer> datas = new HashMap<>();
//        datas.put("ibm", 100);
//        datas.put("sun", 300);
//        datas.put("bea", 500);
//        datas.put("oracle", 700);
//        pieChart("占比情况", datas, 700, 400);


        Map<String, Map<String, Double>> datas =new HashMap<String, Map<String,Double>>();
        Map<String, Double> map1=new HashMap<>();
        Map<String, Double> map2=new HashMap<>();
        Map<String, Double> map3=new HashMap<>();
        Map<String, Double> map4=new HashMap<>();

        map1.put("花生", 68.4);
        map2.put("玉米", 40.8);
        map3.put("其他", 32.3);
        map4.put("大豆", 25.04);

        //压入数据
        datas.put("花生", map1);
        datas.put("玉米", map2);
        datas.put("其他", map3);
        datas.put("大豆", map4);

        barChart("农户分散利用比例",datas,"东北区各大农作物农户分散利用比例","数量单位（%）",700,400);


    }

}
