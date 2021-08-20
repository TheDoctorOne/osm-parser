package net.mahmutkocas.osmparser.model;

import java.util.ArrayList;
import java.util.List;

public class OSMWay extends BaseRootModel {
	private List<OSMNode> OSMNodes = new ArrayList<>();
	
	
	public void addNode(OSMNode OSMNode) {
		OSMNodes.add(OSMNode);
	}
}
