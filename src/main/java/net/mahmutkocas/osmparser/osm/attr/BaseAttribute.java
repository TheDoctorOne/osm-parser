package net.mahmutkocas.osmparser.osm.attr;

import net.mahmutkocas.osmparser.OSMKeys;
import net.mahmutkocas.osmparser.Utils;
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

		String ID 			= Utils.checkIfAttrAvailable(OSMKeys.ATTR.ID, attr, "0");
		String VERSION 		= Utils.checkIfAttrAvailable(OSMKeys.ATTR.VERSION, attr, "0");
		USER 				= Utils.checkIfAttrAvailable(OSMKeys.ATTR.USER, attr, "anonymous");
		String UID 			= Utils.checkIfAttrAvailable(OSMKeys.ATTR.UID, attr, "0");
		String VISIBLE 		= Utils.checkIfAttrAvailable(OSMKeys.ATTR.VISIBLE, attr, "true");
		CHANGE_SET		 	= Utils.checkIfAttrAvailable(OSMKeys.ATTR.CHANGE_SET, attr, "");
		TIMESTAMP 			= Utils.checkIfAttrAvailable(OSMKeys.ATTR.TIMESTAMP, attr, "");

		this.ID = Long.parseLong(ID);
		this.VERSION = Double.parseDouble(VERSION);
		this.UID = Long.parseLong(UID);
		this.VISIBLE = Boolean.parseBoolean(VISIBLE);

		return attr;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" +
				"ID=" + ID +
				", VERSION=" + VERSION +
				", USER='" + USER + '\'' +
				", UID=" + UID +
				", VISIBLE=" + VISIBLE +
				", CHANGE_SET='" + CHANGE_SET + '\'' +
				", TIMESTAMP='" + TIMESTAMP + '\'' +
				'}';
	}
}
