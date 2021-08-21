package net.mahmutkocas.osmparser.osm;

import net.mahmutkocas.osmparser.model.LatLon;
import net.mahmutkocas.osmparser.osm.attr.BaseAttribute;
import net.mahmutkocas.osmparser.osm.child.Tag;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseRootModel<T extends BaseAttribute> {
	protected T attribute;
	protected List<Tag> Tags = new ArrayList<>();
	protected List<Tag.Type> tagTypes = new ArrayList<>();

	public T getAttribute() {
		return attribute;
	}

	public List<Tag> getTags() {
		return Tags;
	}

	public long getId() { return getAttribute().ID; }

	public void parseAttr(Node node) { getAttribute().parse(node); }

	public abstract List<LatLon> getPath();

	public List<Tag.Type> getTypes() {
		if(tagTypes.size() == 0) {
			for(Tag tag : getTags())
				tagTypes.add(tag.type);
			if(tagTypes.size() > 1) {
				// Filtering out Tag.Type.OTHER if has any other property.
				tagTypes.removeAll(Collections.singleton(Tag.Type.OTHER));
			}
		}
		return new ArrayList<>(tagTypes);
	}

	public boolean isType(Tag.Type type) {
		return getTypes().contains(type);
	}

	public boolean isTypeAll(List<Tag.Type> types) {
		int counter = 0;
		List<Tag.Type> mTypes = getTypes();
		for(Tag.Type type : types) {
			if(mTypes.contains(type))
				counter++;
		}
		return counter == types.size();
	}

	public boolean isTypeAny(List<Tag.Type> types) {
		List<Tag.Type> mTypes = getTypes();
		for(Tag.Type type : types) {
			if(mTypes.contains(type))
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" +
				"attribute=" + attribute +
				", Tags=" + Tags +
				"}\n";
	}
}
