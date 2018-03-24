package simple.strengthen.utils;

import org.xml.sax.SAXException;
import simple.strengthen.model.AopModel;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Description.
 *
 * @author: huang
 * Date: 18-3-24
 */
public class AopXmlUtil {

    /**
     * 将xml文件中的aop配置部分解析为AopModel类.
     * @param xmlPath xml路径
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static AopModel parserXml(String xmlPath) throws IOException, SAXException, ParserConfigurationException {
        xmlPath = "src/main/" + xmlPath;
        // 创建 SAXParserFactory
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        AopModel model = new AopModel();
        // 获取SAXParser解析器
        SAXParser parser = parserFactory.newSAXParser();
        // 创建ParsersHandler
        ParserHandler handler = new ParserHandler(model);
        // 解析xml
        parser.parse(xmlPath, handler);
        return model;
    }

}
