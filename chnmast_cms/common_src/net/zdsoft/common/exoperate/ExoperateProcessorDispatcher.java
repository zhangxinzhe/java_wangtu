package net.zdsoft.common.exoperate;

import java.util.Map;

@SuppressWarnings("rawtypes")
public interface ExoperateProcessorDispatcher {

	public void dispatchProcessor(String operateType, Map values);

}
