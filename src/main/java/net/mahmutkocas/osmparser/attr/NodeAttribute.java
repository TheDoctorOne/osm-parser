package net.mahmutkocas.osmparser.attr;

import net.mahmutkocas.osmparser.OSMKeys;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class NodeAttribute extends BaseAttribute {
	public double latDegree;
	public double lonDegree;

	@Override
	public NamedNodeMap parse(Node node) {
		NamedNodeMap attr = super.parse(node);

		if(attr.getLength() < 1) {
			return attr;
		}

		latDegree = Double.parseDouble(checkAttribute(OSMKeys.NODE_ATTR.LAT, attr, "0"));
		lonDegree = Double.parseDouble(checkAttribute(OSMKeys.NODE_ATTR.LON, attr, "0"));

		return attr;
	}
}
