package net.mahmutkocas.osmparser.model;

import net.mahmutkocas.osmparser.attr.NodeAttribute;

public class OSMNode extends BaseRootModel {
	private NodeAttribute attribute;
	
	public NodeAttribute getAttribute() {
		return attribute;
	}
}
