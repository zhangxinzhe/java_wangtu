package net.zdsoft.common.asyn.schedule;

import java.util.concurrent.BlockingQueue;

import net.zdsoft.common.asyn.Record;
import net.zdsoft.common.asyn.RecordBundle;

/**
 * 获取异步写出数据的任务接口定义,被用于ExecutorService定时执行 或者周期性执行，内嵌入获取数据业务逻辑
 * 
 * @author wenchu.cenwc
 * 
 */
@SuppressWarnings({ "rawtypes"})
public interface IConsumeSchedule extends ISchedule {
	/**
	 * 从阻塞队列中获取需要输出的数据,根据needFlush方法判断是否需要输出
	 * 
	 * @param 阻塞队列中
	 */
	public void consume(BlockingQueue<Record> queue) throws Exception;

	/**
	 * 判断是否需要输出Bundle中的数据
	 * 
	 * @param 内部管理的页对象
	 * @return
	 */
	public boolean needFlush(RecordBundle bundle);

	/**
	 * 设置Consumer监听的数据输入队列
	 * 
	 * @param queue
	 */
	public void setQueue(BlockingQueue<Record> queue);

	/**
	 * 设置Consumer的名称
	 * 
	 * @param name
	 */
	public void setName(String name);

	/**
	 * 获取Consumer的名称
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * 将内部数据全部写出
	 */
	public void flush() throws Exception;

	/**
	 * 停止任务
	 */
	public void stop();
}
