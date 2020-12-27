package com.example.crudproject.dao;

import com.example.crudproject.entity.DictionaryEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 数据字典
 * @author 大狼狗
 * @date 2020/12/27
 */
@Mapper
@Repository
public interface DictionaryMapper {

    /**
     * [新增]
     * @author 大狼狗
     * @date 2020/12/27
     **/
    int insert(DictionaryEntity rainDictionary);

    /**
     * [刪除]
     * @author 大狼狗
     * @date 2020/12/27
     **/
    int delete(int id);

    /**
     * [更新]
     * @author 大狼狗
     * @date 2020/12/27
     **/
    int update(DictionaryEntity rainDictionary);

    /**
     * [查询] 根据主键 id 查询
     * @author 大狼狗
     * @date 2020/12/27
     **/
    DictionaryEntity load(int id);

    /**
     * [查询] 分页查询
     * @author 大狼狗
     * @date 2020/12/27
     **/
    List<DictionaryEntity> pageList(int offset, int pagesize);

    /**
     * [查询] 分页查询 count
     * @author 大狼狗
     * @date 2020/12/27
     **/
    int pageListCount(int offset,int pagesize);

}
