package net.mahmutkocas.osmparser;

import net.mahmutkocas.osmparser.osm.OSMBounds;
import net.mahmutkocas.osmparser.osm.OSMNode;
import net.mahmutkocas.osmparser.osm.OSMRelation;
import net.mahmutkocas.osmparser.osm.OSMWay;
import net.mahmutkocas.osmparser.osm.child.Tag;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public final class OSMParser {
	private static class XMLParser {
		static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		private static Document parseXML(File file) throws ParserConfigurationException, IOException, SAXException {
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.parse(file);
		}
	}
	
	public static OSMDocument parseXML(String filePath) throws ParserConfigurationException, SAXException, IOException {
		return parseXML(new File(filePath));
	}
	
	public static OSMDocument parseXML(File file) throws IOException, SAXException, ParserConfigurationException {
		return parseDocument(XMLParser.parseXML(file));
	}
	
	public static OSMDocument parseDocument(Document document) {
		OSMDocument osmDocument = new OSMDocument();
		NodeList nodeList = document.getDocumentElement().getChildNodes();
		for(int i=0;i<nodeList.getLength();i++) {
			Node node = nodeList.item(i);
			if(osmDocument.getBounds() == null) {
				OSMBounds bounds = OSMBounds.PARSE(node);
				if(bounds != null) {
					osmDocument.setBounds(bounds);
				}
			}
			OSMNode parsedNode = OSMNode.PARSE(node);
			if(parsedNode != null) {
				osmDocument.addNode(parsedNode);
				continue;
			}
			OSMWay parsedWay = OSMWay.PARSE(node, osmDocument.getNodes());
			if(parsedWay != null) {
				osmDocument.addWay(parsedWay);
				continue;
			}
			OSMRelation osmRelation = OSMRelation.PARSE(node);
			if(osmRelation != null) {
				osmDocument.addRelation(osmRelation);
			}
		}
		return osmDocument;
	}

	public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
		System.out.println(Tag.Type.AMENITY.toString());
		parseXML("map.xml");
	}
	
}
