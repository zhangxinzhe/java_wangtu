package net.zdsoft.common.exoperate;

import java.io.File;
import java.io.IOException;
import java.io.StringBufferInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import net.zdsoft.common.asyn.AsynWriterTemplate;
import net.zdsoft.common.asyn.ProcessCallback;

@SuppressWarnings({ "rawtypes", "deprecation", "unused" })
@Service("exoperateProcessorDispatcher")
public class ExoperateProcessorDispatcherImpl implements ExoperateProcessorDispatcher {

    private static final Logger log = LoggerFactory.getLogger(ExoperateProcessorDispatcherImpl.class);

    private static final String EXOPERATE_DATA_SPEC = "conf//exoperate//exoperate-data-spec.xml";
    private static final String EXOPERATE_SERVICE = "conf//exoperate//exoperate-processor-spec.xml";

    private final AsynWriterTemplate<OperateParameterWrapper> writeTemplate = new AsynWriterTemplate<OperateParameterWrapper>();
    private final ProcessCallback<OperateParameterWrapper> callback = new ProcessCallback<OperateParameterWrapper>() {
        @Override
        public void process(OperateParameterWrapper parameter) {
            parameter.processor.process(parameter.operateType, parameter.values);
        }
    };

    /**
     * 业务规则和其对应的业务处理集合
     */
    private Map<String, List<ExoperateProcessor>> processorsMap;

    /**
     * 是否已经初始化了
     */
    private boolean isInitialize;

    /**
     * 将后续处理的业务进行转发
     *
     * @param type
     * @param values
     */
    @Override
    public void dispatchProcessor(String operateType, Map values) {
        if (!isInitialize) {
            synchronized (this) {
                if (!isInitialize) {
                    initialize();
                }
            }
        }

        List<ExoperateProcessor> processors = processorsMap.get(operateType);
        if (CollectionUtils.isEmpty(processors)) {
            log.error("没有对业务类型：[{}]进行附加业务处理的实现", operateType);
            return;
        }

        for (ExoperateProcessor processor : processors) {
            pushPipeLine(operateType, values, processor);
        }

    }

    /**
     * 初始化管道信息
     */
    private void initialize() {
        processorsMap = new HashMap<String, List<ExoperateProcessor>>();

        Document serviceDoc = null;

        URL file = ExoperateProcessorDispatcherImpl.class.getClassLoader().getResource(EXOPERATE_SERVICE);
        try {
            serviceDoc = readDocument(new File(file.getPath()));
        }
        catch (DocumentException e) {
            log.error("获得exoperate的processors定义失败", e);
            return;
        }

        // operate的节点列表
        List<Element> operateElements = getChildElementsByTagName(serviceDoc.getRootElement(), "operate");
        for (Element operateElement : operateElements) {
            String operateType = operateElement.attributeValue("type");

            processorsMap.put(operateType, getProcessors(operateElement));
        }

        // 初始化完成后才改变状态
        isInitialize = true;
    }

    private List<ExoperateProcessor> getProcessors(Element operateElement) {
        String operateType = operateElement.attributeValue("type");

        List<ExoperateProcessor> processors = new ArrayList<ExoperateProcessor>();

        // 得到该operate的service节点
        List<Element> servicesElement = getChildElementsByTagName(operateElement, "processors");
        if (!servicesElement.isEmpty()) {
            Element serviceElement = servicesElement.get(0);

            // 得到service的节点集合
            List<Element> processorElements = getChildElementsByTagName(serviceElement, "processor");

            if (!processorElements.isEmpty()) {
                for (Element processor : processorElements) {
                    String clazz = processor.attributeValue("class");
                    Object processorClass = null;
                    try {
                        processorClass = ApplicationContextHolder.getApplicationContext().getBean(clazz);
                    }
                    catch (Throwable e) {
                        log.error("从ApplicationContext获取bean:[{}]失败", clazz, e);
                    }

                    if (processorClass == null) {
                        log.error("operate：[{}]的processor：[{}]无法从ApplicationContext中得到实现实例", operateType, clazz);
                        continue;
                    }
                    if (!(processorClass instanceof ExoperateProcessor)) {
                        log.error("operate：[{}]的processor：[{}]不是ExoperateProcessor的子类", operateType, clazz);
                        continue;
                    }

                    processors.add((ExoperateProcessor) processorClass);
                }
            }
        }

        if (processors.isEmpty()) {
            log.error("operate：[{}]没有配置processors", operateType);
        }

        return processors;
    }

    private Document readDocument(File file) throws DocumentException {
        SAXReader reader = new SAXReader();
        reader.setEntityResolver(new EntityResolver() {

            @Override
            public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
                return new InputSource(new StringBufferInputStream(""));
            }
        });

        Document document = reader.read(file);

        return document;
    }

    /**
     * 得到文档的某个标签的所有元素
     *
     * @param document
     * @param tagName
     * @return
     */
    private List<Element> getElementsByTagName(Document document, String tagName) {
        List<Element> elements = new ArrayList<Element>();

        elements.addAll(getChildElementsByTagName(document.getRootElement(), tagName));

        return elements;
    }

    /**
     * 得到一个元素的某个标签的所有子元素
     *
     * @param element
     * @param tagName
     * @return
     */
    private List<Element> getChildElementsByTagName(Element element, String tagName) {
        List<Element> elements = new ArrayList<Element>();

        for (Object child : element.elements()) {
            Element childElement = (Element) child;
            if (childElement.getName().equals(tagName)) {
                elements.add(childElement);
            }
            else {
                elements.addAll(getChildElementsByTagName(childElement, tagName));
            }
        }

        return elements;
    }

    /**
     * 将要处理的数据放到管道
     *
     * @param operateType
     * @param values
     */
    private void pushPipeLine(String operateType, Map values, ExoperateProcessor processor) {
        writeTemplate.write(new OperateParameterWrapper(operateType, values, processor), callback);
    }

    static class OperateParameterWrapper {
        private final String operateType;
        private final Map values;
        private final ExoperateProcessor processor;

        public OperateParameterWrapper(String operateType, Map values, ExoperateProcessor processor) {
            this.operateType = operateType;
            this.values = values;
            this.processor = processor;
        }
    }

}
