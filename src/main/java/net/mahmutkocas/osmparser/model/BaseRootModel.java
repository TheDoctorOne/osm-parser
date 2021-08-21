package net.mahmutkocas.osmparser.model;

import net.mahmutkocas.osmparser.attr.BaseAttribute;
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

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" +
				"attribute=" + attribute +
				", Tags=" + Tags +
				"}\n";
	}
}
