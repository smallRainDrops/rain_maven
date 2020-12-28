package com.example.crudproject.service.Impl;

import com.example.crudproject.dao.DictionaryMapper;
import com.example.crudproject.entity.DictionaryEntity;
import com.example.crudproject.service.DictionaryService;
import com.example.crudproject.utils.Constant;
import com.example.crudproject.utils.R;
import org.springframework.stereotype.Service;
import sun.plugin2.util.SystemUtil;

import javax.annotation.Resource;
import java.util.*;

/**
 * 数据字典
 * @author 大狼狗
 * @date 2020/12/27
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Resource
    private DictionaryMapper rainDictionaryMapper;


    @Override
    public int insert(DictionaryEntity rainDictionary) {

        // valid
        if (rainDictionary == null) {
            return  R.FAIL;
        }
        rainDictionary.setId(System.currentTimeMillis());
        rainDictionary.setCreate(this.getClass().getName());
        rainDictionary.setUpdater(this.getClass().getName());
        rainDictionary.setCreateDate(new Date());
        rainDictionary.setUpdaterDate(new Date());
        rainDictionaryMapper.insert(rainDictionary);
        return R.SUCCESS;
    }


    @Override
    public String delete(int id) {
        int ret = rainDictionaryMapper.delete(id);
        return ret>0? Constant.SUCCESS:Constant.FAIL;
    }


    @Override
    public String update(DictionaryEntity rainDictionary) {
        int ret = rainDictionaryMapper.update(rainDictionary);
        return ret>0?Constant.SUCCESS:Constant.FAIL;
    }


    @Override
    public DictionaryEntity load(int id) {
        return rainDictionaryMapper.load(id);
    }


    @Override
    public Map<String,Object> pageList(int offset, int pagesize) {

        List<DictionaryEntity> pageList = rainDictionaryMapper.pageList(offset, pagesize);
        int totalCount = rainDictionaryMapper.pageListCount(offset, pagesize);

        // result
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("pageList", pageList);
        result.put("totalCount", totalCount);

        return result;
    }

}
