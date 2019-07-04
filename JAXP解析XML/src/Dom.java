import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Dom解析
 */
public class Dom {

    //DOM 解析器的工厂，解析器对象，要解析的文件输入流，解析后的Document对象
    private DocumentBuilderFactory builderFactory;
    private DocumentBuilder builder;
    private InputStream is;
    private Document document;

    List<Node> list = new ArrayList<>();//储存节点

    public Dom() throws Exception{
        this.builderFactory = DocumentBuilderFactory.newInstance();
        this.builder  =  builderFactory.newDocumentBuilder();
        this.is = new FileInputStream("java.xml");
        this.document = builder.parse(is);
    }


    public void test() throws ParserConfigurationException, SAXException, IOException {
        //获取根节点的元素对象
        Element root = document.getDocumentElement();
        //遍历根节点下面的所有子节点
        listNodes(root);
        is.close();
    }

    /**
     * 遍历该节点
     * @param node 要遍历的节点
     */
    public void listNodes(Node node) {
        // 节点类型
        if (node.getNodeType() == Node.ELEMENT_NODE) {  // 判断是否是元素节点
            Element element = (Element) node; //继承Node接口
            //判断此元素节点是否有属性
            if(element.hasAttributes()){
                //获取属性节点的集合
                NamedNodeMap namenm = element.getAttributes();//Node
                //遍历属性节点的集合
                for(int i=0;i<namenm.getLength();i++){
                    //获取具体的某个属性节点
                    Attr attr = (Attr) namenm.item(i);
                    System.out.println("属性名称==="+attr.getNodeName()+"\t 属性value==="
                            +attr.getNodeValue()+"\t 属性种类==="+attr.getNodeType());
                }
                System.out.println("=====================================================");
            }

            //获取元素节点的所有子节点（递归遍历）
            NodeList listnode = element.getChildNodes();
            //遍历
            for (int i = 0; i < listnode.getLength(); i++) {
                //得到某个具体的节点对象
                Node nd = listnode.item(i);
                //#text代表文本节点
                if(nd.getNodeName() != "#text") {
                    System.out.println("节点名称===" + nd.getNodeName() + "\t 节点内容==="
                            + nd.getNodeValue() + "\t 节点种类===" + nd.getNodeType());
                }
                //重新调用遍历节点的操作的方法
                listNodes(nd);
            }
        }
    }

    /**
     * 标签名查找节点，并输出该标签名称
     * @param document 要操作的文档
     * @param nodeTag 操作的节点名称
     */
    public void findNodeByTag(Document document,String nodeTag) {
        //根据标签获得节点对象集合
        NodeList nodelist = document.getElementsByTagName(nodeTag);
        //遍历
        for (int i = 0; i < nodelist.getLength(); i++) {
            //得到具体的某个节点对象
            Node node = nodelist.item(i);
            System.out.println(node.getNodeName());
        }
    }

    /**
     * 根据属性的值 查询某个节点对象
     * @param root 要查找节点
     * @param attrKey 属性名称
     * @param attrValue 属性value
     */
    public void findNodeByAttrValue(Node root, String attrKey, String attrValue) {
        NodeList nodeList = root.getChildNodes();
        //本节点
        Element n = (Element) root;
        if (n.hasAttribute(attrKey)) {
            Attr a = n.getAttributeNode(attrKey);
            if (a.getNodeValue().equals(attrValue)) {
                list.add(n);
                System.out.println(n.getTagName());
            }
        }
        //遍历子节点
        for (int i = 0; i < nodeList.getLength(); i++) {
            //判断是否是元素节点
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element node = (Element) nodeList.item(i);
                //递归遍历
                findNodeByAttrValue(nodeList.item(i), attrKey, attrValue);
            }
        }
    }

    /**
     * 根据id获取某个节点对象
     * @param document 要操作的文档
     * @param id 节点的id属性
     */
    public Node findNodeById(Document document, String id) {
        return document.getElementById(id);
    }

    /**
     * 根据id删除某个节点对象
     */
    public void deleteNodeById(Document document, String id)
            throws TransformerException {
        //获取删除的节点对象
        Node node = document.getElementById(id);
        // 调用父节点方法删除子节点
        Node node1 = node.getParentNode().removeChild(node);

        writeXML(document);
    }

    /**
     * 修改student节点的id，子节点内容
     */
    public void updateNodeById(Document document, String id)
            throws TransformerException {
        //根据id获取元素指定的元素节点对象
        Element node = document.getElementById(id);
        //获取元素节点的id属性节点对象
        Attr attr = node.getAttributeNode("id");
        //修改元素节点的属性值
        attr.setValue("x122");

        //获取该节点对象的所有孩子节点对象name、age、sex节点
        NodeList nodelist = node.getChildNodes();
        //遍历
        for (int i = 0; i < nodelist.getLength(); i++) {
            //得到具体的节点对象
            Node n = nodelist.item(i);
            //判断是否是元素节点对象
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                //看是否是name节点
                if (n.getNodeName().equals("name")) {
                    n.setTextContent("君君");//修改其值
                } else if (n.getNodeName().equals("age")) {//看看是否是age节点
                    n.setTextContent("80");//修改其值
                } else if (n.getNodeName().equals("sex")) {//看看是否是sex节点
                    n.setTextContent("男");//修改其值
                } else {
                    System.out.println("不做处理");
                }
            }
        }

        writeXML(document);
    }

    /**
     * 在指定的节点下方添加新得某个节点
     *
     * @param document
     * @param id
     * @throws TransformerException
     */
    public void addNodeById(Document document, String id)
            throws TransformerException {
        //获取要添加位置节点的兄弟节点对象（为了得到父节点）
        Element node = document.getElementById(id);
        //获取其父节点对象（插入新节点操作需要用到）
        Node parentNode = node.getParentNode();
        //创建元素节点（<student></student>）
        Element nd = document.createElement("student");
        //设置元素节点的属性值
        nd.setAttribute("id", "x123");
        //创建name元素节点
        Node name = document.createElement("name");
        //设置name节点的文本值（创建文本节点）
        name.appendChild(document.createTextNode("陈红军"));
        //创建age元素节点
        Node age = document.createElement("age");
        //设置age节点的文本值
        age.appendChild(document.createTextNode("20"));
        //创建sex元素节点
        Node sex = document.createElement("sex");
        //设置sex节点的文本值
        sex.appendChild(document.createTextNode("男"));
        //在nd节点中添加3个子节点
        nd.appendChild(name);
        nd.appendChild(age);
        nd.appendChild(sex);
        //在父节点中添加nd节点
        parentNode.appendChild(nd);

        writeXML(document);
    }


    /**
     * 将Document对象写入xml中
     * ………………未写入DTD引用………………
     */
    private void writeXML(Document document) throws TransformerException {
        //创建TransformerFactory对象
        TransformerFactory transformerFactory = TransformerFactory
                .newInstance();
        //Transformer类用于把代表XML文件的Document对象转换为某种格式后进行输出
        //Transformer对象通过TransformerFactory获得
        Transformer transformer = transformerFactory.newTransformer();
        //把Document对象又重新写入到一个XML文件中。
        transformer.transform(new DOMSource(document), new StreamResult(
                new File("java.xml")));
    }












































    public DocumentBuilderFactory getBuilderFactory() {
        return builderFactory;
    }

    public DocumentBuilder getBuilder() {
        return builder;
    }

    public InputStream getIs() {
        return is;
    }

    public Document getDocument() {
        return document;
    }
}
