package net.zdsoft.common.dao.queryCondition;

import java.io.Serializable;
import java.sql.Types;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * IN
 * 
 * @author xiezb
 * 
 */
@SuppressWarnings("serial")
public class InNumberCondition implements QueryCondition, Serializable {
	private String column;
	private List<Long> values;

	public InNumberCondition(String column, List<Long> values) {
		this.column = column;
		this.values = values;
	}

	@Override
    public String getCondition() {
		if (StringUtils.isBlank(column) || null == values || values.isEmpty())
			return StringUtils.EMPTY;

		StringBuilder buf = new StringBuilder();
		buf.append(column);
		buf.append(" IN (");
		for (int i = 0; i < values.size(); i++) {
			buf.append("?");
			if (i != values.size() - 1) {
				buf.append(",");
			}
		}
		buf.append(")");

		return buf.toString();
	}

	@Override
    public Object[] getParameters() {
		return values.toArray();
	}

	@Override
    public int[] getTypes() {
	    int[] types = new int[values.size()];
        for (int i = 0; i < types.length; i++) {
            types[i] = Types.BIGINT;
        }
        return types;
	}

}
