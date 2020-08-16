package com.xiaomaigou.code.service.impl;

import com.xiaomaigou.code.service.TemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/8/16 18:53
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    private static final Logger logger = LoggerFactory.getLogger(TemplateServiceImpl.class);

    @Override
    public List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();
        templates.add("Controller.java.ftl");
        templates.add("DTO.java.ftl");
        templates.add("Entity.java.ftl");
        templates.add("Mapper.java.ftl");
        templates.add("Mapper.xml.ftl");
        templates.add("Service.java.ftl");
        templates.add("ServiceImpl.java.ftl");
        return templates;
    }
}
