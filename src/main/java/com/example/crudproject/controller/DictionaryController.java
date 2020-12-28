package com.example.crudproject.controller;

import com.example.crudproject.entity.DictionaryEntity;
import com.example.crudproject.service.DictionaryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 数据字典
 * @author 大狼狗
 * @date 2020/12/27
 */
@RestController
@RequestMapping(value = "/RainDictionary")
public class DictionaryController {

    @Resource
    private DictionaryService dictionaryService;

    /**
     * [新增]
     * @author 大狼狗
     * @date 2020/12/27
     **/
    @PostMapping("/insert")
    public int insert(DictionaryEntity rainDictionary){
        return dictionaryService.insert(rainDictionary);
    }

    /**
     * [刪除]
     * @author 大狼狗
     * @date 2020/12/27
     **/
    @GetMapping("/delete")
    public String delete(int id){
        return dictionaryService.delete(id);
    }

    /**
     * [更新]
     * @author 大狼狗
     * @date 2020/12/27
     **/
    @PostMapping("/update")
    public String update(DictionaryEntity rainDictionary){
        return dictionaryService.update(rainDictionary);
    }

    /**
     * [查询] 根据主键 id 查询
     * @author 大狼狗
     * @date 2020/12/27
     **/
    @GetMapping("/load")
    public DictionaryEntity load(int id){
        return dictionaryService.load(id);
    }

    /**
     * [查询] 分页查询
     * @author 大狼狗
     * @date 2020/12/27
     **/
    @PostMapping("/pageList")
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                                        @RequestParam(required = false, defaultValue = "10") int pagesize) {
        return dictionaryService.pageList(offset, pagesize);
    }

}
