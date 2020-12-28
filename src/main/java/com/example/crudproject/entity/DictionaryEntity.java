package com.example.crudproject.entity;

import com.example.crudproject.entity.base.AbstractModel;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *  数据字典
 * @author 小雨点 2020-12-27
 */

@ApiOperation(value = "数据字典", notes = "数据字典")
@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", required = false, dataType = "Long"),
        @ApiImplicitParam(name = "memo", value = "备注", required = false, dataType = "String"),
        @ApiImplicitParam(name = "create", value = "新增人", required = false, dataType = "String"),
        @ApiImplicitParam(name = "createDate", value = "新增时间", required = false, dataType = "Date"),
        @ApiImplicitParam(name = "updater", value = "修改人", required = false, dataType = "String"),
        @ApiImplicitParam(name = "updaterDate", value = "修改时间", required = false, dataType = "Date"),
        @ApiImplicitParam(name = "dictCode", value = "字典编码", required = false, dataType = "String"),
        @ApiImplicitParam(name = "parentId", value = "父级字典id -1:根目录，其他:父级目录id", required = false, dataType = "Long"),
        @ApiImplicitParam(name = "enName", value = "字典英文名称", required = false, dataType = "String"),
        @ApiImplicitParam(name = "chName", value = "字典中文名称", required = false, dataType = "String"),
        @ApiImplicitParam(name = "enabled", value = "使用状态", required = false, dataType = "Integer")
}
)
public class DictionaryEntity extends AbstractModel {

   @ApiModelProperty("字典编码")
    private String dictCode;

    @ApiModelProperty("父级字典id -1:根目录，其他:父级目录id")
    private Long parentId;

    @ApiModelProperty("字典英文名称")
    private String enName;

    @ApiModelProperty("字典中文名称")
    private String chName;

    @ApiModelProperty("使用状态")
    private Integer enabled;

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
