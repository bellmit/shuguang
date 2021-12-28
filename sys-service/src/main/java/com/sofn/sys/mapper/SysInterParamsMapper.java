package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysInterParamsMapper extends BaseMapper<SysInterParams> {



	void deleteByInterId(String interId);


	List<SysInterBase> showReqParams(String interId);
}
