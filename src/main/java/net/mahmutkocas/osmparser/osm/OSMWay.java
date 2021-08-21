package net.mahmutkocas.osmparser.osm;

import net.mahmutkocas.osmparser.OSMKeys;
import net.mahmutkocas.osmparser.Utils;
import net.mahmutkocas.osmparser.model.LatLon;
import net.mahmutkocas.osmparser.osm.attr.WayAttribute;
import net.mahmutkocas.osmparser.osm.child.Tag;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OSMWay extends BaseRouteModel<WayAttribute> {



	private List<OSMNode> OSMNodes = new ArrayList<>();
	private List<LatLon> latLons = new ArrayList<>();


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

	@Override
	public List<LatLon> getPath() {
		if(latLons.size() > 0)
			return new ArrayList<>(latLons);
		return new ArrayList<>(calculatePath());
	}

	protected synchronized List<LatLon> calculatePath() {
		if(latLons.size() > 0)
			return latLons;

		ArrayList<LatLon> latLons = new ArrayList<>();
		for(OSMNode node : OSMNodes) {
			latLons.addAll(node.getPath());
		}
		this.latLons = latLons;

		return latLons;
	}
}
