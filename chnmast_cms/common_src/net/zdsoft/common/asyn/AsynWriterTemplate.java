package net.zdsoft.common.asyn;

/**
 * 方便使用异步输出的模板类
 * 
 * @author wenchu.cenwc
 * 
 * @param <T>
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AsynWriterTemplate<T> {

	private static IAsynWriter writer;

	static {
		writer = new AsynWriter();

		writer.setName(new StringBuffer("AsynWriter").toString());
	}

	public AsynWriterTemplate() {
	}

	/**
	 * 选择输出器输出数据
	 * 
	 * @param content
	 */
	public void write(T content, ProcessCallback<T> callable) {
		writer.write(content, callable);
	}
}
