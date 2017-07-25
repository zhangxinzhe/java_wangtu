package net.zdsoft.common.dao.queryCondition;

import java.io.Serializable;
import java.sql.Types;
import java.util.Date;

/**
 * 时间小于等于条件
 * 
 * @author xiezb
 * 
 */
@SuppressWarnings("serial")
public class LessEqualCondition implements QueryCondition, Serializable {
	private String column;
	private Date value;

	public LessEqualCondition(String column, Date value) {
		this.column = column;
		this.value = value;
	}

	@Override
    public String getCondition() {
		return column + "<=" + "?";
	}

	@Override
    public Object[] getParameters() {
		return new Object[] { value };
	}

	@Override
    public int[] getTypes() {
		return new int[] { Types.TIMESTAMP };
	}
}
