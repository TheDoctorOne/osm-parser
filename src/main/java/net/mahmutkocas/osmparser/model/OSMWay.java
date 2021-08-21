package net.mahmutkocas.osmparser.model;

import net.mahmutkocas.osmparser.attr.NodeAttribute;
import net.mahmutkocas.osmparser.attr.WayAttribute;

import java.util.ArrayList;
import java.util.List;

public class OSMWay extends BaseRootModel<WayAttribute> {
	private List<OSMNode> OSMNodes = new ArrayList<>();


	public OSMWay() {
		attribute = new WayAttribute();
	}

	public void addNode(OSMNode OSMNode) {
		OSMNodes.add(OSMNode);
	}
}
