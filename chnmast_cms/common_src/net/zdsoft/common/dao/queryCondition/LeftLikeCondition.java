package net.zdsoft.common.dao.queryCondition;

import java.io.Serializable;
import java.sql.Types;

import org.apache.commons.lang.StringUtils;

/**
 * 左匹配
 * 
 * @author fangb
 * 
 */
@SuppressWarnings("serial")
public class LeftLikeCondition implements QueryCondition, Serializable {
	private String column;
	private String value;

	public LeftLikeCondition(String column, String value) {
		this.column = column;
		this.value = value;
	}

	@Override
    public String getCondition() {
		if (StringUtils.isBlank(column) || StringUtils.isBlank(value))
			return StringUtils.EMPTY;

		StringBuilder buf = new StringBuilder();

		buf.append(column);
		buf.append(" LIKE ?");

		return buf.toString();
	}

	@Override
    public Object[] getParameters() {
		Object[] parameters = { value + "%" };
		return parameters;
	}

	@Override
    public int[] getTypes() {
		return new int[] { Types.VARCHAR };
	}

}