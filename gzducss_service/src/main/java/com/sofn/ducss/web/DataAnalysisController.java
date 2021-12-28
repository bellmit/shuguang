package com.sofn.ducss.web;//package com.sofn.ducss.web;
//
//import com.sofn.ducss.log.SofnLog;
//import com.sofn.ducss.model.basemodel.Result;
//import com.sofn.ducss.web.BaseController;
//import com.sofn.ducss.service.DataAnalysisService;
//import com.sofn.ducss.vo.DataAnalysisQueryVo;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author huanghao
// * @version 1.0
// * @date 2020/8/3  9:34
// *  数据分析页面的相关接口信息
// **/
//@RestController
//@Api(tags = "数据分析接口", value = "数据分析页面的表现层")
//@Slf4j
//@RequestMapping("/dataAnalysis")
//public class DataAnalysisController extends BaseController {
//
////    @Autowired
////    private DataAnalysisService dataAnalysisService;
//
//    @ApiOperation(value = "数据分析列表查询")
//    @SofnLog("数据分析列表查询")
//    @PostMapping("/getList")
//    public Result getList(
//            @Validated
//            @RequestBody
//            @ApiParam(name = "数据分析条件查询封装类", value = "dataAnalysisQueryVo")
//                    DataAnalysisQueryVo dataAnalysisQueryVo, BindingResult result) {
//        if (result.hasErrors()) {
//            return Result.error(getErrMsg(result));
//        }
//        return Result.ok(dataAnalysisService.getList(dataAnalysisQueryVo));
//    }
//}
