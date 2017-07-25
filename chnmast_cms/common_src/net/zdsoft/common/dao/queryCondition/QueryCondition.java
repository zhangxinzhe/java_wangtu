package net.zdsoft.common.dao.queryCondition;

/**
 * 查询条件接口
 * 
 * @author xiezb
 * 
 */
public interface QueryCondition {

	/**
	 * 获取条件字符串
	 * 
	 * @return
	 */
	public String getCondition();

	/**
	 * 获取查询参数
	 * 
	 * @return
	 */
	public Object[] getParameters();

	/**
	 * 获取参数类型
	 * 
	 * @return
	 */
	public int[] getTypes();
}
