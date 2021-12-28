package com.sofn.common.utils;

import com.google.common.collect.Lists;
import com.sofn.common.exception.SofnException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 通过分页方式获取所有的信息
 * 使用方法参考 支撑平台test包下：com.sofn.sys.service.TestGetAllInfoByPageUtil
 *
 * @author heyongjie
 * @date 2020/7/14 9:32
 */
@Slf4j
public class GetAllInfoByPageUtil<T> {

    /**
     * 每次执行查询的数量  //800;
     */
    private int execuQueryNumber = 800;

    public GetAllInfoByPageUtil(){}

    /**
     * 构造器
     * @param execuQueryNumber  每次查询的数量
     */
    public GetAllInfoByPageUtil(int execuQueryNumber){
        this.execuQueryNumber = execuQueryNumber;
    }



    public interface Predicate<T> {

        /**
         * 执行查询
         *
         * @param params 查询参数
         * @return List<T>
         */
        List<T> execuQuery(Object... params);
    }

//
//    /**
//     * 参数不是Map
//     * @param function   需要执行的方法
//     * @param inQueryParamIndex   in参数的位置
//     * @param params   查询参数
//     * @return  List<T>
//     */
//    public List<T> getAllInfo(Predicate<T> function, Integer inQueryParamIndex, Object... params){
//        return getAllInfo(function,inQueryParamIndex,false,"",params);
//    }

    /**
     * 分页获取信息，用于在方法中有in的情况，因为oracle in有数目限制，超出这个范围会报错，所以这里使用分页查询处理
     *
     * @param function          执行方法
     * @param inQueryParamIndex in参数在params中所在的索引位置
     * @param isMap             该位置是否是Map，如果是Map需要传入Map的key
     * @param mapKey            Map的键
     * @param params            查询参数，请按照service方法或者mapper方法中的参数顺序传递
     * @return List<T>          获取的所有信息
     */
    public List<T> getAllInfo(Predicate<T> function, Integer inQueryParamIndex, boolean isMap, String mapKey, Object... params) {
        if (inQueryParamIndex == null || inQueryParamIndex < 0) {
            throw new SofnException("inQueryParamIndex参数不能为空");
        }

        if (params == null || params.length == 0) {
            throw new SofnException("查询参数不能为空");
        }

        List<T> resultList = Lists.newArrayList();
        List list = null;
        try {
            if (isMap) {
                Map<String, Object> p = (Map<String, Object>) params[inQueryParamIndex];
                if (!CollectionUtils.isEmpty(p)) {
                    if(p.get(mapKey) instanceof List){
                        list = (List) p.get(mapKey);
                    }else if(p.get(mapKey) instanceof Set) {
                        list = Lists.newArrayList((Set)p.get(mapKey));
                    }
                }
            } else {
                if(params[inQueryParamIndex] instanceof List){
                    list = (List) params[inQueryParamIndex];
                }else if(params[inQueryParamIndex] instanceof Set){
                    list = Lists.newArrayList( (Set)params[inQueryParamIndex]);
                }

            }
        } catch (Exception e) {
            // 没有拿到list直接执行原始方法
            e.printStackTrace();
            log.error("getAllInfo查询参数：{}", params);
            return function.execuQuery(params);
        }

        try {
            if (CollectionUtils.isEmpty(list)) {
                // 如果没有获取到list就说明
                return function.execuQuery(params);
            } else {
                // 判断参数大小是否符合条件
                List<List> lists = subList(list, execuQueryNumber);
                Object[] newParams = new Object[params.length];
                System.arraycopy(params, 0, newParams, 0, params.length);
                if (!CollectionUtils.isEmpty(lists)) {
                    lists.forEach(item -> {
                        // 如果索引位置是Map需要将Map更改为新的Map
                        if (isMap) {
                            Map<String, Object> realParams = (Map<String, Object>) newParams[inQueryParamIndex];
                            realParams.put(mapKey, item);
                            newParams[inQueryParamIndex] = realParams;
                        } else {
                            newParams[inQueryParamIndex] = item;
                        }
                        List<T> ts = function.execuQuery(newParams);
                        resultList.addAll(ts);
                    });
                } else {
                    return function.execuQuery(params);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 如果没有拿到分页参数就直接执行方法
            return function.execuQuery(params);
        }
        return resultList;
    }


    /**
     * 将集合按照大小拆分为不同大小的集合
     *
     * @param target 目标集合
     * @param size   大小
     * @return List<List>
     */
    public static List<List> subList(List target, int size) {
        List<List> listArr = Lists.newArrayList();
        if (CollectionUtils.isEmpty(target)) {
            return null;
        }
        if (target.size() > size) {
            int number = (target.size() % size == 0 ? target.size() / size : target.size() / size + 1);
            for (int i = 0; i < number; i++) {
                int start = i * size;
                int end = i * size + size;
                if (end > target.size()) {
                    end = target.size();
                }
                List subList = target.subList(start, end);
                listArr.add(subList);
            }
        } else {
            listArr.add(target);
        }

        return listArr;

    }


}
