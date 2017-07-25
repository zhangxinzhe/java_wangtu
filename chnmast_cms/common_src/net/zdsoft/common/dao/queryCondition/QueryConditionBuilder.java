package net.zdsoft.common.dao.queryCondition;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 查询条件构造器
 * 
 * @author xiezb
 * 
 */
public class QueryConditionBuilder {
	private static final String AND = " AND ";
	private static final String BLANK = "";

	private List<QueryCondition> queryConditions;

	public QueryConditionBuilder() {
		queryConditions = new ArrayList<QueryCondition>();
	}

	/**
	 * 添加一个搜索条件
	 * 
	 * @param condition
	 */
	public void addCondition(QueryCondition condition) {
		this.queryConditions.add(condition);
	}

	/**
	 * 添加多个搜索条件
	 * 
	 * @param conditions
	 */
	public void addConditions(List<QueryCondition> conditions) {
		if (conditions == null)
			return;
		this.queryConditions.addAll(conditions);
	}

	/**
	 * 组装条件内容 <br>
	 * 返回的条件内容没有where关键字。 <br>
	 * 如果没有条件，则返回空的字符串。
	 * 
	 * @return
	 */
	public String buildCondition() {
		List<String> conditionStr = new ArrayList<String>();

		for (QueryCondition condition : queryConditions) {
			if (condition == null
					|| StringUtils.isBlank(condition.getCondition()))
				continue;
			conditionStr.add(condition.getCondition());
		}
		if (conditionStr.isEmpty())
			return BLANK;

		return StringUtils.join(conditionStr, AND);
	}

	/***
	 * 组装参数内容<br>
	 * 如果没有条件，则返回NULL
	 * 
	 * @return
	 */
	public Object[] buildParameters() {
		if (queryConditions.isEmpty()) {
			return null;
		}

		List<Object> parameters = new ArrayList<Object>();
		for (QueryCondition condition : queryConditions) {
			Object[] eachParams = condition.getParameters();
			for (Object param : eachParams)
				parameters.add(param);
		}

		return parameters.toArray();
	}

	/***
	 * 组装参数类型<br>
	 * 如果没有条件，则返回NULL
	 * 
	 * @return
	 */
	public int[] buildTypes() {
		if (queryConditions.isEmpty()) {
			return null;
		}

		List<Integer> parameters = new ArrayList<Integer>();
		for (QueryCondition condition : queryConditions) {
			int[] eachTypes = condition.getTypes();
			for (int type : eachTypes)
				parameters.add(type);
		}

		int[] types = new int[parameters.size()];
		for (int i = 0; i < types.length; i++) {
			types[i] = parameters.get(i);
		}

		return types;
	}
}
