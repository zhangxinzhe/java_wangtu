package net.zdsoft.common.asyn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.zdsoft.common.asyn.schedule.ConsumeScheduleImpl;
import net.zdsoft.common.asyn.schedule.ConsumeScheduleJDK15Impl;
import net.zdsoft.common.asyn.schedule.IConsumeSchedule;

/**
 *
 * 异步输出数据接口默认实现类
 *
 * @author wenchu.cenwc
 *
 * @param <T>
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AsynWriter<T> implements IAsynWriter<T> {

    private static final Logger log = LoggerFactory.getLogger(AsynWriter.class);

    /**
     * 数据队列
     */
    private LinkedBlockingQueue<Record> dataQueue;

    /**
     * 创建ConsumeSchedule的服务执行器，根据配置决定创建多少个Consumer， 由Consumer来检测dataQueue获取数据
     */
    private ExecutorService createConsumerService;

    /**
     * 内部创建的Consumer
     */
    private IConsumeSchedule[] consumers;

    /**
     * 消费线程控制句柄
     */
    private Future<?>[] consumersHandler;

    /**
     * Writer的名称
     */
    private String name;

    public AsynWriter() {
        dataQueue = new LinkedBlockingQueue<Record>();
        createConsumerService = Executors.newFixedThreadPool(AsynConfig.getInstance().getCreateConsumerThreadCount());

        createConsumer();
    }

    public void createConsumer() {
        int consumerCount = AsynConfig.getInstance().getCreateConsumerThreadCount();
        String jvmVersion = System.getProperty("java.version");

        consumers = new IConsumeSchedule[consumerCount];
        consumersHandler = new Future[consumerCount];

        for (int i = 0; i < consumerCount; i++) {
            if (jvmVersion != null && jvmVersion.startsWith("1.6")) {
                consumers[i] = new ConsumeScheduleImpl();
            }
            else {
                consumers[i] = new ConsumeScheduleJDK15Impl();
            }

            consumers[i].setName(new StringBuffer().append("consumerSchedule").append(i).toString());
            consumers[i].setQueue(dataQueue);

            consumersHandler[i] = createConsumerService.submit(consumers[i]);
        }
    }

    @Override
    public void write(T content, ProcessCallback<T> callable) {
        dataQueue.add(new Record(content, callable));
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
    public void flush() {
        try {
            for (int i = 0; i < consumers.length; i++) {
                consumers[i].flush();
            }
        }
        catch (InterruptedException e) {
            log.error("flush cause InterruptedException !");
        }
        catch (Exception ex) {
            log.error("flush error !", ex);
        }

    }

    @Override
    public void stop() {
        flush();

        for (int i = 0; i < consumers.length; i++) {
            consumersHandler[i].cancel(true);
            consumers[i].stop();
        }

        if (createConsumerService != null) {
            createConsumerService.shutdown();
        }
    }

}
