package net.mahmutkocas.osmparser;

import org.w3c.dom.Document;
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
		NodeList nodeList = document.getDocumentElement().getChildNodes();
	}
	
}
