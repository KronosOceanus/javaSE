import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * SAX解析
 */
public class Sax {

    //读取xml文件，解析后的文档，根元素，所有子元素
    private SAXReader saxReader;
    private Document document;
    private Element rootElement;
    private List<Element> childElements;

    public Sax() throws DocumentException {
        saxReader = new SAXReader();
        document = saxReader.read(new File("java.xml"));
        rootElement = document.getRootElement();
        childElements = rootElement.elements();
    }

    //遍历所有
    public void listAll(){

        for (Element childElement : childElements){
            //打印元素名
            String childEleName = childElement.getName();
            System.out.println(childEleName);

            //处理某个标签
            if ("teachers".equals(childEleName)){
                //获取元素U对象，然后获取文本
                Element teacherNameElement = childElement.element("teacher");
                String teacherName = teacherNameElement.getText();
                System.out.println("\t" + teacherName);

                //直接获取文本
                String teacherClass = childElement.elementText("teacher");
                System.out.println("\t" + teacherClass);

                //然后处理子元素
            }
        }
    }





















    public SAXReader getSaxReader() {
        return saxReader;
    }

    public Document getDocument() {
        return document;
    }

    public Element getRootElement() {
        return rootElement;
    }

    public List<Element> getChildElements() {
        return childElements;
    }
}
