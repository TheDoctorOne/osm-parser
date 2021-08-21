package net.mahmutkocas.osmparser.osm;

import net.mahmutkocas.osmparser.OSMKeys;
import net.mahmutkocas.osmparser.model.LatLon;
import net.mahmutkocas.osmparser.osm.attr.NodeAttribute;
import net.mahmutkocas.osmparser.osm.child.Tag;
import org.w3c.dom.Node;

import java.util.Collections;
import java.util.List;

public class OSMNode extends BaseRouteModel<NodeAttribute> {
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

	@Override
	public List<LatLon> getPath() {
		return Collections.singletonList(LatLon.fromDegrees(getAttribute().latDegree, getAttribute().lonDegree));
	}
}
