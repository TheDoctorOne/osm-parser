package net.mahmutkocas.osmparser.model;

import net.mahmutkocas.osmparser.OSMKeys;
import net.mahmutkocas.osmparser.attr.NodeAttribute;
import org.w3c.dom.Node;

public class OSMNode extends BaseRootModel<NodeAttribute> {

	public OSMNode() {
		attribute = new NodeAttribute();
	}

	public static OSMNode PARSE(Node node) {

		if(node.getNodeName().equals(OSMKeys.ROOT.NODE)) {
			OSMNode osmNode = new OSMNode();
			osmNode.parseAttr(node);
			if(node.hasChildNodes()) {
				osmNode.getTags().addAll(Tag.PARSE_TAGS(node.getChildNodes()));
			}
			return osmNode;
		}
		return null;
	}

}
