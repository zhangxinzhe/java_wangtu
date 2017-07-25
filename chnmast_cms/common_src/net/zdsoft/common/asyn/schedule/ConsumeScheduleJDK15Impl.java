package net.zdsoft.common.asyn.schedule;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.zdsoft.common.asyn.AsynConfig;
import net.zdsoft.common.asyn.Record;
import net.zdsoft.common.asyn.RecordBundle;

/**
 * 为jdk1.5作的实现
 *
 * @author wenchu.cenwc
 *
 */
@SuppressWarnings("rawtypes")
public class ConsumeScheduleJDK15Impl implements IConsumeSchedule {

    private static final Logger log = LoggerFactory.getLogger(ConsumeScheduleJDK15Impl.class);

    /**
     * 输出数据队列
     */
    private BlockingQueue<Record> queue;

    /**
     * 名称
     */
    private String name;

    /**
     * 负责的分页对象
     */
    private RecordBundle bundle;

    /**
     * 输出操作线程工作池
     */
    private ExecutorService writeBundleService;

    /**
     * 内部检查
     *
     * @return
     */
    public boolean checkInnerElement() {
        if (queue == null || name == null) {
            return false;
        }
        else {
            return true;
        }
    }

    public void init() {
        if (!checkInnerElement()) {
            log.error("ConsumerSchedule start Error!,please check innerElement");
            throw new java.lang.RuntimeException("ConsumerSchedule start Error!,please check innerElement");
        }

        bundle = new RecordBundle(AsynConfig.getInstance().getFlushInterval());
        writeBundleService = Executors.newFixedThreadPool(AsynConfig.getInstance().getWriterMaxCount());
    }

    @Override
    public void run() {
        init();

        while (true) {
            try {
                consume(queue);
            }
            catch (ExecutionException e) {
                log.error("ConsumerSchedule ExecutionException!", e);
            }
            catch (TimeoutException e) {
                log.error("ConsumerSchedule TimeoutException!", e);
            }
            catch (InterruptedException e) {
                log.error("ConsumerSchedule is stop!");
            }
            catch (Exception e) {
                log.error("ConsumerSchedule error!", e);
            }
        }

    }

    @Override
    public void consume(BlockingQueue<Record> queue) throws Exception {
        try {
            if (needFlush(bundle)) {
                flush();
            }

            Record node = queue.poll();

            if (node != null) {
                bundle.add(node);
                return;
            }

            Thread.sleep(100);

        }
        catch (Exception ex) {
            log.error("ConsumerSchedule  consume error!", ex);
        }
    }

    @Override
    public boolean needFlush(RecordBundle bundle) {
        boolean result = false;

        // 到了输出间隔时间
        if (bundle.getCount() > 0 && bundle.getFlushTime().before(new Date())) {
            result = true;
        }

        // 创建输出任务去输出
        if (bundle.getCount() >= AsynConfig.getInstance().getBundleMaxCount()) {
            result = true;
        }

        return result;
    }

    public BlockingQueue<Record> getQueue() {
        return queue;
    }

    @Override
    public void setQueue(BlockingQueue<Record> queue) {
        this.queue = queue;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void flush() throws Exception {
        IWriteSchedule writeSchedule = new DefaultWriteSchedule();

        writeSchedule.setBundle(bundle);

        writeBundleService.submit(writeSchedule).get(2, TimeUnit.MINUTES);

        bundle.reset(AsynConfig.getInstance().getFlushInterval());
    }

    @Override
    public void stop() {
        if (writeBundleService != null) {
            writeBundleService.shutdown();
        }
    }

}
