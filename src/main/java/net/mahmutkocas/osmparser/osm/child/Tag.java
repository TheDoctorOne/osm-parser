package net.mahmutkocas.osmparser.osm.child;

import net.mahmutkocas.osmparser.OSMKeys;
import net.mahmutkocas.osmparser.Utils;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Tag {
	public final String key;
	public final String value;
	
	public Tag(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public static List<Tag> PARSE_TAGS(NodeList nodes) {
		List<Tag> tags = new ArrayList<>();
		for(int i=0;i<nodes.getLength();i++) {
			Node node = nodes.item(i);
			if(node.getNodeName().equals(OSMKeys.CHILD_ROOT.TAG)) {
				tags.add(Tag.PARSE(node));
			}
		}
		return tags;
	}

	public static Tag PARSE(Node node) {
		NamedNodeMap map = node.getAttributes();
		String key = Utils.checkIfAttrAvailable(OSMKeys.TAG_ATTR.KEY, map, "");
		String value = Utils.checkIfAttrAvailable(OSMKeys.TAG_ATTR.VALUE, map, "");
		return new Tag(key, value);
	}

	@Override
	public String toString() {
		return "Tag{" +
				"key='" + key + '\'' +
				", value='" + value + '\'' +
				'}';
	}
}
