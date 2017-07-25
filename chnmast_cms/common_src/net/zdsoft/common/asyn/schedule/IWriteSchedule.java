package net.zdsoft.common.asyn.schedule;

import net.zdsoft.common.asyn.RecordBundle;

/**
 * 输出任务接口定义,被用于ExecutorService定时执行 或者周期性执行，内嵌入输出业务逻辑
 * 
 * @author wenchu.cenwc
 * 
 */
public interface IWriteSchedule extends ISchedule {
	/**
	 * 将bundle中的数据输出
	 * 
	 * @param bundle
	 */
	public void write(RecordBundle bundle);

	/**
	 * 设置Bundle
	 * 
	 * @param bundle
	 */
	public void setBundle(RecordBundle bundle);

}
