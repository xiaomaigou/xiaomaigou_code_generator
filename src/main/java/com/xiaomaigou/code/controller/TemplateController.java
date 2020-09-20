package com.xiaomaigou.code.controller;

import com.xiaomaigou.code.dto.Result;
import com.xiaomaigou.code.service.TemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

    @ApiOperation(value = "上传模板", notes = "上传模板")
    @PostMapping("uploadTemplate")
    public Result<String> uploadTemplate(@RequestParam("file") MultipartFile multipartFile) {
        return templateService.uploadTemplate(multipartFile);
    }

    @ApiOperation(value = "下载模板", notes = "根据模板名称下载模板文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "templateName", value = "模板名称", paramType = "query", required = false, dataType = "String")
    })
    @GetMapping("downloadTemplateByTemplateName")
    public void downloadTemplateByTemplateName(@RequestParam(value = "templateName", required = true) String templateName,
                                               HttpServletResponse response) throws IOException {

        logger.info(String.format("根据模板名称下载模板文件:templateName=[%s]", templateName));

        byte[] data = templateService.downloadTemplateByTemplateName(templateName);
        String fileName = templateName + ".zip";
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
