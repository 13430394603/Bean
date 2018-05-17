package com.Bean.AnalyXML;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TestXML extends AnalyXMLV {
	public void doDea(Object doc, Navig naivg) {
		NodeList nodes;
		System.out.println(naivg.name + "-----------------------");
		if (doc instanceof Document){
			nodes = ((Document) doc).getElementsByTagName(naivg.name);
			System.out.println("Document!!!!");
		}else
			nodes = ((Element) doc).getChildNodes(); 
		doDeal(nodes, naivg);
	}

	public void doDeal(NodeList nodes, Navig naivg) {
		int len = nodes.getLength();
		if(len > 0){
			for (int i = 0; i < len; i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == 1 && node.getNodeName().equals(naivg.name)) {
					Element element = (Element) node;
					dealAttrs(element.getAttributes()); // 解析attrs
					System.out.println();
					if (naivg.next != null && naivg.next.length > 0) {
						for (Navig naivg1 : naivg.next) {
							System.out.println(naivg1.name);
							doDea(element, naivg1);
						}
					}
				}
			}
		}
	}

	public void dealAttrs(NamedNodeMap attrMaps) {
		int attrLen = attrMaps.getLength();
		for (int i = 0; i < attrLen; i++) {
			String attr = attrMaps.item(i).toString(); // 得到的数据是 key=value
			int sepIndex = attr.indexOf("=");
			String value = attr.substring(sepIndex + 1, attr.length());
			System.out.print(attr.substring(0, sepIndex) + " - " + getType(value));
			System.out.print("    ");
		}
	}

	public Object getType(String value) {
		if (value.indexOf("\"") != -1)
			return value.replaceAll("\"", "");
		else if (value.equals("false") || value.equals("true"))
			return Boolean.parseBoolean(value);
		else
			return Integer.parseInt(value);
	}

	@Override
	public List<Element> targetElements() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object putConfObject(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args)
			throws SAXException, IOException, URISyntaxException, ParserConfigurationException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbFactory.newDocumentBuilder();
		System.out.println(builder);
		System.out.println(TestXML.class);
		File flie = new File(TestXML.class.getResource("/context.xml").toURI());
		System.out.println(flie);
		Document doc = builder.parse(flie);

		Navig service = new Navig("service", null);
		Navig container = new Navig("container", null);
		Navig container2 = new Navig("container", null);
		container.next = new Navig[] { service, container2 };
		container2.next = new Navig[] { service, container };
		Navig context = new Navig("context", null);
		context.next = new Navig[] { container };

		new TestXML().doDea(doc, context);
	}

}
