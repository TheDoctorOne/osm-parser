package net.mahmutkocas.osmparser.osm;

import net.mahmutkocas.osmparser.model.LatLon;
import net.mahmutkocas.osmparser.osm.attr.BaseAttribute;
import net.mahmutkocas.osmparser.osm.child.Tag;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRootModel<T extends BaseAttribute> {
	protected T attribute;
	protected List<Tag> Tags = new ArrayList<>();

	public T getAttribute() {
		return attribute;
	}

	public List<Tag> getTags() {
		return Tags;
	}

	public long getId() { return getAttribute().ID; }

	public void parseAttr(Node node) { getAttribute().parse(node); }

	public abstract List<LatLon> getPath();

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" +
				"attribute=" + attribute +
				", Tags=" + Tags +
				"}\n";
	}
}
