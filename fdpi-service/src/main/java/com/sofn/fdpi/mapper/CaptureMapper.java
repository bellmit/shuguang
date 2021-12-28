package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.Capture;
import com.sofn.fdpi.vo.CapCacheVo;
import com.sofn.fdpi.vo.CaptureForm;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2019-12-30 9:34
 */
@Repository
public interface CaptureMapper extends BaseMapper<Capture> {
    /**
     * 添加特许捕获证
     * @param capture
     * @return
     */
    int  addCapture(Capture capture);

    /**
     * 根据证书编号查看证书是否存在
     * @param papersNumber
     * @return
     */
    Capture getCaptureBypapersNumber(@Param("papersNumber") String papersNumber);



    /**
     * 特许捕获证列表
     * @return
     */
    List<Capture> getCapture(Map map);

    List<Capture> list(Map map);

    /**
     * 移除证书
     * @param id
     * @return
     */
    int removeCapture(@Param("id") String id);

    /**
     * 修改特许捕获证信息
     * @param capture
     * @return
     */
    int updateCapture(Capture capture);

    /**
     * 验证证书编号不重复
     * @param map
     * @return
     */
    Capture    getOneByNumber(Map map);
    void printCap(String id);

    /**
     * 获取未被删除的证书编号和id
     * @return
     */
    List<CapCacheVo> getCapCache();

}
