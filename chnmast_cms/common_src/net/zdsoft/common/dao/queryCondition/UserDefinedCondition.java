package net.zdsoft.common.dao.queryCondition;

import java.io.Serializable;

/**
 * 自定义查询条件
 * 
 * @author YUYI
 * 
 */
@SuppressWarnings("serial")
public class UserDefinedCondition implements QueryCondition, Serializable {
    private String column;
    private Object[] params;
    private int[] types;

    public UserDefinedCondition(String column, Object[] params, int[] types) {
        this.column = column;
        this.params = params;
        this.types = types;
    }

    @Override
    public String getCondition() {
        return column;
    }

    @Override
    public Object[] getParameters() {
        return params;
    }

    @Override
    public int[] getTypes() {
        return types;
    }
}
