package net.zdsoft.common.asyn;

/**
 * 
 * @author fangb
 * 
 * @param <T>
 */
public class Record<T> {

	private T object;

	private ProcessCallback<T> callable;

	public Record(T object, ProcessCallback<T> callable) {
		this.object = object;
		this.callable = callable;
	}

	public T getObject() {
		return object;
	}

	public ProcessCallback<T> getCallable() {
		return callable;
	}

}
