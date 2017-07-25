package net.zdsoft.common.dao.queryCondition;

import java.io.Serializable;

/**
 * 不为NULL的查询条件
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
public class NotNullCondition implements QueryCondition, Serializable {
	private String column;

	public NotNullCondition(String column) {
		this.column = column;
	}

	@Override
    public String getCondition() {
		return column + " IS NOT NULL";
	}

	@Override
    public Object[] getParameters() {
		return new Object[] {};
	}

	@Override
    public int[] getTypes() {
		return new int[] {};
	}
}
