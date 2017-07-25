package net.zdsoft.common.asyn;

/**
 * 
 * 输出处理的回调接口，对象一旦从输出队列出来即调用该接口来做实际处理的业务
 * 
 * @author fangb
 * 
 * @param <T>
 */
public interface ProcessCallback<T> {

	public void process(T content);
}
