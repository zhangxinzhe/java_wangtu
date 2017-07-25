package net.zdsoft.common.entity.system;

import net.zdsoft.common.entity.BaseEntity;

/**
 * 系统常量表
 *
 * @author wangzb
 *
 */
public class SystemConfig extends BaseEntity {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4962847432008986165L;

    private String code; // 编号
    private String name; // 含义
    private String value; // 值
    private String description; // 说明
    private String validate; // 验证规则
    private boolean canView;
    private boolean canEdit;
    private int orderNo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getCanView() {
        return canView;
    }

    public void setCanView(boolean canView) {
        this.canView = canView;
    }

    public boolean getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

}
