import org.dom4j.DocumentException;
import org.w3c.dom.*;
import org.xml.sax.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    //Dom解析测试
    private void testDom() throws Exception {
        Dom dom = new Dom();
        System.out.println("--------------------------------------------------------------------------------------");
        dom.findNodeByTag(dom.getDocument(),"teacher");
        System.out.println("--------------------------------------------------------------------------------------");

        dom.findNodeByAttrValue(dom.getDocument().getDocumentElement(),"name","华");
        System.out.println("--------------------------------------------------------------------------------------");

        Element e2 = (Element)dom.findNodeById(dom.getDocument(),"x121");
        System.out.println(e2.getTagName());
        System.out.println("--------------------------------------------------------------------------------------");

        // m.deleteNodeById(dom.getDocument(),"id");
    }
    //SAX解析测试
    private void testSAX() throws DocumentException {
        Sax sax = new Sax();
        sax.listAll();
        System.out.println("--------------------------------------------------------------------------------------");
    }

    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.testDom();
        m.testSAX();
    }

}
