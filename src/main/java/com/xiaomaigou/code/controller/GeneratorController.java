package com.xiaomaigou.code.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiaomaigou.code.dto.GenerateCodeTemplateDataDTO;
import com.xiaomaigou.code.service.GenerateCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 代码生成
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/5/30 17:51
 */
@Api(tags = "代码生成", value = "代码生成")
@RestController
@RequestMapping("codeGenerator/generator")
public class GeneratorController {

    private static final Logger logger = LoggerFactory.getLogger(GeneratorController.class);

    @Autowired
    private GenerateCodeService generateCodeService;

    @ApiOperation(value = "生成代码", notes = "通过表名生成代码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tables", value = "表名(多个表名之间使用逗号\",\"分隔)", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "useTemplateName", value = "使用的模板名称", paramType = "query", required = false, dataType = "String")
    })
    @GetMapping("generateCode")
    public void generateCode(@RequestParam(value = "tables", required = true) String tables,
                             @RequestParam(value = "useTemplateName", required = false) String useTemplateName,
                             HttpServletResponse response) throws IOException {

        logger.info(String.format("通过表名生成代码:tables=[%s],useTemplateName=[%s]", tables, useTemplateName));

        byte[] data = generateCodeService.generateCode(Arrays.asList(tables.split(",")), useTemplateName);
        String fileName = "xiaomaigou_code_generator.zip";
        // 修改文件名编码，否则会乱码
        fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; fileName=" + fileName);
        response.addHeader("Content-Length", "" + data.length);

        IOUtils.write(data, response.getOutputStream());

    }

    @ApiOperation(value = "生成代码", notes = "通过生成代码模板数据生成代码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "generateCodeTemplateDataDTO", value = "生成代码模板数据", paramType = "body", required = true, dataType = "GenerateCodeTemplateDataDTO")
    })
    @PostMapping("generateCode")
    public void generateCode(@RequestBody GenerateCodeTemplateDataDTO generateCodeTemplateDataDTO, HttpServletResponse response) throws IOException {

        logger.info(String.format("通过代码模板数据生成代码:generateCodeTemplateDataDTO=[%s]", JSONObject.toJSONString(generateCodeTemplateDataDTO)));

        byte[] data = generateCodeService.generateCode(generateCodeTemplateDataDTO);
        String fileName = "xiaomaigou_code_generator.zip";
        // 修改文件名编码，否则会乱码
        fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; fileName=" + fileName);
        response.addHeader("Content-Length", "" + data.length);

        IOUtils.write(data, response.getOutputStream());

    }

}
