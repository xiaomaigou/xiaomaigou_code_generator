package com.xiaomaigou.code.controller;

import com.xiaomaigou.code.service.GeneratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * 代码生成
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/5/30 17:51
 */
@Api(tags = "代码生成", description = "代码生成")
@RestController
@RequestMapping("codeGenerator/generator")
public class GeneratorController {

    private static final Logger logger = LoggerFactory.getLogger(GeneratorController.class);

    @Autowired
    private GeneratorService generatorService;

    @ApiOperation(value = "生成代码", notes = "生成代码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tableList", value = "表名 List", paramType = "query", required = true, dataType = "String")
    })
    @GetMapping("generatorCode")
    public void generatorCode(String tables, HttpServletResponse response) throws IOException {

        logger.info(String.format("生成代码:tables=[%s]", tables));

        System.out.println("tables:" + tables);
        byte[] data = generatorService.generatorCode(Arrays.asList(tables.split(",")));

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"generator.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());

    }

}
