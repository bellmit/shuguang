package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.fdpi.mapper.PapersMapper;
import com.sofn.fdpi.mapper.SignboardMapper;
import com.sofn.fdpi.model.Papers;
import com.sofn.fdpi.model.Signboard;
import com.sofn.fdpi.service.PapersService;
import com.sofn.fdpi.service.SignboardSaveBatchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("signboardSaveBatchService")
public class SignboardSaveBatchServiceImpl extends ServiceImpl<SignboardMapper, Signboard> implements SignboardSaveBatchService {

}
