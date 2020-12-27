package com.example.crudproject.entity.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 79018
 */
public class AbstractModel implements Serializable {

    private static final long serialVersionUID = 1L;
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

}