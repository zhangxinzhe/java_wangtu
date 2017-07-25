package net.zdsoft.common.dao.queryCondition;

import java.io.Serializable;

/**
 * 相等的查询条件
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
public class EqualCondition implements QueryCondition, Serializable {
	private String column;
	private Object value;
	private int type;

	public EqualCondition(String column, Object value, int type) {
		this.column = column;
		this.value = value;
		this.type = type;
	}

	@Override
    public String getCondition() {
		return column + "=" + "?";
	}

	@Override
    public Object[] getParameters() {
		Object[] parameters = { value };
		return parameters;
	}

	@Override
    public int[] getTypes() {
		return new int[] { type };
	}
}
