package com.example.crudproject.entity;

import com.example.crudproject.entity.base.AbstractModel;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *  数据字典
 * @author 小雨点 2020-12-27
 */
public class DictionaryEntity implements Serializable {

    /**
     * 字典编码
     */
    private String dictCode;

    /**
     * 父级字典id -1:根目录，其他:父级目录id
     */
    private Long parentId;

    /**
     * 字典英文名称
     */
    private String enName;

    /**
     * 字典中文名称
     */
    private String chName;

    /**
     * 使用状态
     */
    private Integer enabled;

    /**
     * id
     */
    private Long id;

    /**
     * 备注
     */
    private String memo;

    /**
     * 新增人
     */
    private String create;

    /**
     * 新增时间
     */
    private Date createDate;

    /**
     * 修改人
     */
    private String updater;

    /**
     * 修改时间
     */
    private Date updaterDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdaterDate() {
        return updaterDate;
    }

    public void setUpdaterDate(Date updaterDate) {
        this.updaterDate = updaterDate;
    }


    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

}
