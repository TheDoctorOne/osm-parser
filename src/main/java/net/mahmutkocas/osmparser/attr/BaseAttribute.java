package net.mahmutkocas.osmparser.attr;

import net.mahmutkocas.osmparser.OSMKeys;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public abstract class BaseAttribute {
	public long ID = 0;
	public double VERSION = 0;
	public String USER = "anonymous";
	public long UID = 0;
	public boolean VISIBLE = true;
	public String CHANGE_SET = "";
	public String TIMESTAMP = "";

	public BaseAttribute() {}

	public NamedNodeMap parse(Node node) {
		NamedNodeMap attr = node.getAttributes();

		if (attr.getLength() < 1) {
			return attr;
		}

		String ID 			= checkAttribute(OSMKeys.ATTR.ID, attr, "0");
		String VERSION 		= checkAttribute(OSMKeys.ATTR.VERSION, attr, "0");
		USER 				= checkAttribute(OSMKeys.ATTR.USER, attr, "anonymous");
		String UID 			= checkAttribute(OSMKeys.ATTR.UID, attr, "0");
		String VISIBLE 		= checkAttribute(OSMKeys.ATTR.VISIBLE, attr, "true");
		CHANGE_SET		 	= checkAttribute(OSMKeys.ATTR.CHANGE_SET, attr, "");
		TIMESTAMP 			= checkAttribute(OSMKeys.ATTR.TIMESTAMP, attr, "");

		this.ID = Long.parseLong(ID);
		this.VERSION = Double.parseDouble(VERSION);
		this.UID = Long.parseLong(UID);
		this.VISIBLE = Boolean.parseBoolean(VISIBLE);

		return attr;
	}

	protected String checkAttribute(String key, NamedNodeMap map, String defaultValue) {
		try {
			String s = map.getNamedItem(key).getNodeValue();
			if (s !=null && !s.trim().equals("")){
				return s;
			}
		} catch (Exception ignored) {}
		return defaultValue;
	}
}
