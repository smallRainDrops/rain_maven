package com.example.crudproject.service;

import com.example.crudproject.entity.DictionaryEntity;

import java.util.Map;

/**
 * 数据字典
 * @author 大狼狗
 * @date 2020/12/27
 */
public interface DictionaryService {

    /**
     * 新增
     */
    public int insert(DictionaryEntity rainDictionary);

    /**
     * 删除
     */
    public String delete(int id);

    /**
     * 更新
     */
    public String update(DictionaryEntity rainDictionary);

    /**
     * 根据主键 id 查询
     */
    public DictionaryEntity load(int id);

    /**
     * 分页查询
     */
    public Map<String,Object> pageList(int offset, int pagesize);

}
