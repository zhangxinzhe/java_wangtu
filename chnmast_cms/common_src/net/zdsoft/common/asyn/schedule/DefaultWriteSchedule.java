package net.zdsoft.common.asyn.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.zdsoft.common.asyn.Record;
import net.zdsoft.common.asyn.RecordBundle;

/**
 * @author wenchu.cenwc
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DefaultWriteSchedule implements IWriteSchedule {

    private static final Logger log = LoggerFactory.getLogger(DefaultWriteSchedule.class);

    protected RecordBundle bundle;

    @Override
    public void setBundle(RecordBundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public void write(RecordBundle bundle) {
        while (bundle.getRecords().size() > 0) {
            int i = bundle.getRecords().size();
            Record record = bundle.getRecords().get(i - 1);
            bundle.getRecords().remove(record);
            try {
                record.getCallable().process(record.getObject());
            }
            catch (Throwable e) {
                // 这地方可以调用一个处理失败的业务接口
                log.error("处理数据：{} 失败!", record.getObject(), e);
            }

        }
    }

    @Override
    public void run() {
        write(bundle);
    }

}
