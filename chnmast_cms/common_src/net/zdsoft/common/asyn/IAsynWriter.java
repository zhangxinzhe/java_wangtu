package net.zdsoft.common.asyn;

/**
 * 异步输出接口
 * 
 * @author wenchu.cenwc
 * 
 * @param T
 */
public interface IAsynWriter<T> {
	/**
	 * 将内容写出到指定的队列中，由Consumer自行获取并异步写出
	 * 
	 * @param content
	 */
	public void write(T content, ProcessCallback<T> callable);

	/**
	 * 设置AsynWriter的名称
	 * 
	 * @param name
	 */
	public void setName(String name);

	/**
	 * 获取AsynWriter的名称
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * 将内部数据全部写出
	 */
	public void flush();

	/**
	 * 停止异步写出线程执行
	 */
	public void stop();
}
