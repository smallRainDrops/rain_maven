/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.example.crudproject.controller;

import com.example.crudproject.dao.AreaMapper;
import com.example.crudproject.entity.Area;
import com.example.crudproject.entity.AreaExample;
import com.example.crudproject.service.SysGeneratorService;
import com.example.crudproject.utils.PageUtils;
import com.example.crudproject.utils.Query;
import com.example.crudproject.utils.R;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author Mark sunlightcs@gmail.com
 */
@Controller
public class TestController {
    @Autowired
    private AreaMapper areaMapper;


    /**
     * 测试接口
     */
    @ApiOperation(value = "获取用户详细信息", notes = "注意别出错")
    @ResponseBody
    @RequestMapping(value = "/dictUrl", method = RequestMethod.GET)
    public String health01() {
        return "redirect:dictList.html";
    }



    /**
     * 测试接口
     */
    @ApiOperation(value = "获取用户详细信息", notes = "注意别出错")
    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String health() {
        return "OK";
    }


    /**
     * 测试接口
     */
    @ApiOperation(value = "获取用户详细信息", notes = "注意别出错")
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Area> getAreaList() {
        AreaExample areaExample = new AreaExample();
        AreaExample.Criteria criteria = areaExample.createCriteria();
        return areaMapper.selectByExample(areaExample);
    }

}
