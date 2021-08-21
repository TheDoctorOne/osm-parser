package net.mahmutkocas.osmparser.model;

import net.mahmutkocas.osmparser.OSMKeys;
import net.mahmutkocas.osmparser.Utils;
import net.mahmutkocas.osmparser.attr.NodeAttribute;
import net.mahmutkocas.osmparser.attr.WayAttribute;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OSMWay extends BaseRootModel<WayAttribute> {
	private List<OSMNode> OSMNodes = new ArrayList<>();


	public OSMWay() {
		attribute = new WayAttribute();
	}

	public static OSMWay PARSE(Node node, Map<Long, OSMNode> osmNodeMap) {

		if(node.getNodeName().equals(OSMKeys.ROOT.WAY)) {
			OSMWay osmWay = new OSMWay();
			osmWay.parseAttr(node);
			if(node.hasChildNodes()) {
				NodeList cNodeList = node.getChildNodes();
				for(int i=0;i<cNodeList.getLength();i++) {
					Node cNode = cNodeList.item(i);
					String cNodeName = cNode.getNodeName();
					if(cNodeName.equals(OSMKeys.CHILD_ROOT.ND)) {
						long id = Long.parseLong(Utils.checkIfAttrAvailable(OSMKeys.ND_ATTR.REF, cNode.getAttributes(), "0"));
						OSMNode osmNode = osmNodeMap.get(id);
						if(osmNode != null) {
							osmWay.addNode(osmNode);
						}
					} else if(cNodeName.equals(OSMKeys.CHILD_ROOT.TAG)) {
						osmWay.getTags().add(Tag.PARSE(cNode));
					}
				}
			}

			return osmWay;
		}
		return null;
	}

	public void addNode(OSMNode OSMNode) {
		OSMNodes.add(OSMNode);
	}
}
