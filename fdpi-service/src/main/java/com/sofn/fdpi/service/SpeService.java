package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.Spe;
import com.sofn.fdpi.vo.SelectVo;
import com.sofn.fdpi.vo.SpeExcel;
import com.sofn.fdpi.vo.SpeInfo;
import com.sofn.fdpi.vo.SpeNameLevelVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Auther:
 * @Date: 2019/11/28 11:53
 * @Description:
 */

public interface SpeService extends IService<Spe> {

   /**
    * 根据id获取物种信息
    * @param Id
    * @return
    */
   String add(List<SpeExcel>  importList);

   public Spe getSpeBySpeId(String Id);


   PageUtils<Spe> listSpeByPage(Map<String,Object> params, int pageNo, int pageSize);


   /**
    *    修改物种信息
    * @param speInfo
    * @return
    */
   int updateSpeInfo(SpeInfo speInfo);
   //新增物种
   String saveCheckingSpe(SpeInfo speInfo);
   String deleteSpeInfo(String id);

   /**
    * 根据物种名字获取物种信息
    * wuXY
    * 2019-12-30 16:23:33
    * @param speName 物种名字
    * @return Spe物种对象
    */
   Spe getSpeciesByName(String speName);

   /**
    * 获取二级保护动物
    * @return
    */
   List<SpeNameLevelVo> getSecondLevel();
   /**
    * 获取物种名字下拉
    * @return
    */
   List<SpeNameLevelVo> getSpeciesName(String type);

   /**
    * 通过物种名和保护级别查看当前物种是否存在
    * @param map
    * @return
    */
   Spe getSpe(Map map);

   /**
    * 获取所有的物种数据，返回一个map
    * wXY
    * 2020-7-30 18:45:17
    * @return map
    */
   Map<String,String> getMapForAllSpecies();

   String importData(MultipartFile file,SpeService speService);

   void saveSpeBatch(List<Spe> speList);

   /**
    * 建立物种名缓存
    */
   void saveNameredis();

    Spe getSpeByCode(String speCode);

   void export(Map<String, Object> params, HttpServletResponse response);

   String getSpeTypeByName(String speName);
}
