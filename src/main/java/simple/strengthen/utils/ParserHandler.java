package simple.strengthen.utils;

import lombok.Data;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import simple.strengthen.aop.Aop;
import simple.strengthen.model.AdviceModel;
import simple.strengthen.model.AopModel;

/**
 * Created by huang on 17-6-2.
 */
@Data
public class ParserHandler extends DefaultHandler {

    private AopModel model;

    public ParserHandler(AopModel model) {
        this.model = model;
    }

    /**
     * 遍历xml的开始标签
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        // 解析pointcut元素
        if ("pointcut".equals(qName)) {
            String classValue = attributes.getValue("class");
            model.setClassName(classValue);
            String executValue = attributes.getValue("execut");
            model.setExecut(executValue);
        }

        if ("before".equals(qName)) {
            AdviceModel advice = new AdviceModel();
            advice.setLocaltion("before");
            String className = attributes.getValue("class");
            advice.setClassName(className);
            String methodName = attributes.getValue("method");
            advice.setMethodName(methodName);
            model.addAdvice(advice);
        }

        if ("after".equals(qName)) {
            AdviceModel advice = new AdviceModel();
            advice.setLocaltion("after");
            String className = attributes.getValue("class");
            advice.setClassName(className);
            String methodName = attributes.getValue("method");
            advice.setMethodName(methodName);
            model.addAdvice(advice);
        }
    }

    /**
     * 遍历xml的结束标签
     * @param uri
     * @param localName
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        if ("pointcut".equals(qName)) {
        }
    }

    /**
     * 标志解析开始
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    /**
     * 标志解析结束
     * @throws SAXException
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    /**
     * 获取节点值
     * @param ch
     * @param start
     * @param length
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String value = new String(ch, start, length);
        if (!value.trim().equals("")) {
            System.out.println(value);

        }
    }
}
