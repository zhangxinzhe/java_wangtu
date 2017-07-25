package net.zdsoft.common.exoperate;

import java.util.Map;

@SuppressWarnings("rawtypes")
public interface ExoperateProcessor {
	/**
	 * 实际的后续处理方法
	 * @param values
	 */
	void process(String operateType,Map values);
	
}
