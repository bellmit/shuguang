package com.sofn.fdpi.util;

import com.sofn.fdpi.vo.*;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description 溯源图格式转换的工具类
 * @Author wg
 * @Date 2021/6/4 10:41
 **/
public class Trace2SourceUtil {
    public static SourceInfoVo trace2Source(ImportTraceToSourceVo importTraceToSourceVo) {
        SourceInfoVo sourceInfoVo = new SourceInfoVo();
        //转换公司信息
        List<Source> sources = importTraceToSourceVo.getSources();
        ArrayList<Nodes> nodes = new ArrayList<>(sources.size());
        for (Source source : sources) {
            Nodes node = new Nodes();
            node.setId(String.valueOf(source.getId()));
            node.setLabel(source.getCompName());
            nodes.add(node);
        }
        //设值node
        sourceInfoVo.setNodes(nodes);
        //完成关系链的转换
        List<Target> targets = importTraceToSourceVo.getTargets();
        ArrayList<Edges> edges = new ArrayList<>(targets.size());
        for (Target target : targets) {
            Edges edge = new Edges();
            edge.setSource(String.valueOf(target.getSourceId()));
            edge.setTarget(String.valueOf(target.getTargetId()));
            edge.setLabel(target.getDealNum() + "吨");
            edge.setDataType(target.getDataType());
            edge.setSubLabel(date2String(target.getSubLabel()));
            edges.add(edge);
        }
        //设值edge
        sourceInfoVo.setEdges(edges);
        return sourceInfoVo;
    }

    public static String date2String(Date date) {
        LocalDate localDate = date.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDate();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = localDate.format(dateTimeFormatter);
        return format;
    }

    public static Node source2Node(SourceInfoVo sourceInfoVo, String type) {
        List<Nodes> nodes = sourceInfoVo.getNodes();
        //进口企业为起点
        Node node0 = new Node();
        //因为第一个节点就是进口企业,所以直接取
        Nodes nodei = nodes.get(0);
        node0.setId(nodei.getLabel());//进口企业的信息
        if (type.equals("0")) {//进口企业的溯源图
            //遍历设置其他养殖企业的信息
            List<Node> nodesbr = new ArrayList<>(nodes.size() - 1);
            for (int i = 1; i < nodes.size(); i++) {
                Node node = new Node();
                node.setId(nodes.get(i).getLabel());
                nodesbr.add(node);
            }
            node0.setNode(nodesbr);
        } else if (type.equals("1")) { //养殖企业的溯源图
            //养殖企业的话第二个节点就是
            Nodes nodesbr = nodes.get(1);
            if (nodesbr != null) {
                Node node1 = new Node();
                node1.setId(nodesbr.getLabel());//养殖企业的信息设置完毕
                //进口企业包装养殖企业
                node0.setNode(Arrays.asList(node1));
                ArrayList<Node> nodespr = new ArrayList<>();//加工企业的信息
                //遍历查找加工企业
                for (int i = 2; i < nodes.size(); i++) {
                    Node node = new Node();
                    node.setId(nodes.get(i).getLabel());
                    nodespr.add(node);
                }
                //养殖企业包装加工企业
                node1.setNode(nodespr);
            }
        } else if (type.equals("2")) { //加工企业的溯源图
            Node node1 = new Node();//养殖企业的节点
            node1.setId(nodes.get(1).getLabel());
            node0.setNode(Arrays.asList(node1));//进口企业包装养殖企业
            Node node2 = new Node(); //加工企业的节点
            node2.setId(nodes.get(2).getLabel());
            node1.setNode(Arrays.asList(node2));//养殖企业包装加工企业
        }
        return node0;
    }
}
