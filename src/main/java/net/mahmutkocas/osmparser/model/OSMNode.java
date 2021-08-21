package net.mahmutkocas.osmparser.model;

import net.mahmutkocas.osmparser.OSMKeys;
import net.mahmutkocas.osmparser.attr.NodeAttribute;
import org.w3c.dom.Node;

public class OSMNode extends BaseRootModel<NodeAttribute> {

	public OSMNode() {
		attribute = new NodeAttribute();
	}

	public static OSMNode PARSE(Node node) {
		OSMNode osmNode = new OSMNode();

		if(node.getNodeName().equals(OSMKeys.ROOT.NODE)) {
			osmNode.parseAttr(node);
			return osmNode;
		}
		return null;
	}
}
