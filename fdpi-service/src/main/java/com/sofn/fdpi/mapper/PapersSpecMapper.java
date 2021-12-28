package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.PapersSpec;
import com.sofn.fdpi.vo.PapersSpecVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-23 9:23
 */
public interface PapersSpecMapper extends BaseMapper<PapersSpec> {

   void delByPapersId(@Param("papersId")String papersId);

   /**
    * 通过证书id，获取证书物种列表
    *  wuXY
    *  2020-6-23 14:58:48
    * @param papersId 证书id
    * @return list
    */
   List<PapersSpecVo> listForCondition(@Param("papersId") String papersId);

   List<PapersSpecVo> listByPapersIds(@Param("papersIdList") List<String> papersIdList);


   /**
    *
    */
   PapersSpec checkPapersSpe(@Param("papersId") String papersId,@Param("speId")String speId);

}
