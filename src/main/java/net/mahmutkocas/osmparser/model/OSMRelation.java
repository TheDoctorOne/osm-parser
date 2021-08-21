package net.mahmutkocas.osmparser.model;

import net.mahmutkocas.osmparser.attr.NodeAttribute;
import net.mahmutkocas.osmparser.attr.RelationAttribute;

public class OSMRelation extends BaseRootModel<RelationAttribute> {
    public OSMRelation() {
        attribute = new RelationAttribute();
    }

}
