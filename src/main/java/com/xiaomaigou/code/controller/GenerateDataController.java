package com.xiaomaigou.code.controller;

import com.xiaomaigou.code.dto.Result;
import com.xiaomaigou.code.dto.TemplateData;
import com.xiaomaigou.code.service.GenerateDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Description
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/8/16 18:17
 */
@Api(tags = "生成数据", value = "生成数据")
@RestController
@RequestMapping("codeGenerator/generateData")
public class GenerateDataController {

    private static final Logger logger = LoggerFactory.getLogger(GenerateDataController.class);

    @Autowired
    private GenerateDataService generateDataService;

    @ApiOperation(value = "生成数据", notes = "生成数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tables", value = "表名 List", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "useTemplateName", value = "使用的模板名称", paramType = "query", required = false, dataType = "String")
    })
    @GetMapping("generateTemplateData")
    public Result<List<TemplateData>> generateTemplateData(@RequestParam(value = "tables", required = true) String tables,
                                                           @RequestParam(value = "useTemplateName", required = false) String useTemplateName) {
        Result<List<TemplateData>> result = new Result<>();
        List<TemplateData> templateDataList = generateDataService.generateTemplateData(Arrays.asList(tables.split(",")), useTemplateName);
        return result.success(templateDataList);
    }
}
