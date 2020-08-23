package com.xiaomaigou.code.controller;

import com.xiaomaigou.code.dto.Result;
import com.xiaomaigou.code.service.TemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/8/23 14:21
 */
@Api(tags = "生成模板", value = "生成模板")
@RestController
@RequestMapping("codeGenerator/template")
public class TemplateController {

    private static final Logger logger = LoggerFactory.getLogger(TemplateController.class);

    @Autowired
    private TemplateService templateService;

    @ApiOperation(value = "所有模板", notes = "获取所有模板")
    @GetMapping("getAllTemplateName")
    public Result<List<String>> getAllTemplateName() {
        Result<List<String>> result = new Result<>();
        List<String> allTemplateName = templateService.getAllTemplateName();
        return result.success(allTemplateName);
    }

}
