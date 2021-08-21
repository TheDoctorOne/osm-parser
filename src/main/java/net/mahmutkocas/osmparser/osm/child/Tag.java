package net.mahmutkocas.osmparser.osm.child;

import net.mahmutkocas.osmparser.OSMKeys;
import net.mahmutkocas.osmparser.Utils;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.*;

public class Tag {
	public enum Type {
		AERIALWAY,
		AEROWAY,
		AMENITY,
		BARRIER,
		BOUNDARY,
		BUILDING,
		CRAFT,
		EMERGENCY,
		GEOLOGICAL,
		HEALTHCARE,
		HIGHWAY,
		HISTORIC,
		LANDUSE,
		LEISURE,
		MAN_MADE,
		MILITARY,
		NATURAL,
		OFFICE,
		PLACE,
		POWER,
		PUBLIC_TRANSPORT,
		RAILWAY,
		ROUTE,
		SHOP,
		SPORT,
		TELECOM,
		TOURISM,
		WATER,
		WATERWAY,
		OTHER,
		;

		@Override
		public String toString() {
			return super.toString().toLowerCase(Locale.ENGLISH);
		}
	}

	public static Map<String, Type> TagTypeMap = new HashMap<>();
	static {
		for(Type t : Type.values()) {
			TagTypeMap.put(t.toString(), t);
		}
	}

	public final Type type;
	public final String key;
	public final String value;
	
	public Tag(String key, String value) {
		this.key = key;
		this.value = value;
		type = TagTypeMap.getOrDefault(key, Type.OTHER);
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
