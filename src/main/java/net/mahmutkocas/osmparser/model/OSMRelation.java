package net.mahmutkocas.osmparser.model;

import net.mahmutkocas.osmparser.OSMKeys;
import net.mahmutkocas.osmparser.attr.RelationAttribute;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OSMRelation extends BaseRootModel<RelationAttribute> {
    private List<Member> members = new ArrayList<>();
//    private final Map<Long, OSMNode> nodes;
//    private final Map<Long, OSMWay> ways;

    public OSMRelation() {
        attribute = new RelationAttribute();
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void addMembers(List<Member> members) {
        this.members.addAll(members);
    }

    public static OSMRelation PARSE(Node node) {
        if(node.getNodeName().equals(OSMKeys.ROOT.RELATION)) {
            OSMRelation osmRelation = new OSMRelation();
            osmRelation.parseAttr(node);
            if(node.hasChildNodes()) {
                NodeList cNodes = node.getChildNodes();
                osmRelation.addMembers(Member.PARSE_MEMBERS(cNodes));
                osmRelation.getTags().addAll(Tag.PARSE_TAGS(cNodes));
            }
            return osmRelation;
        }
        return null;
    }

    @Override
    public String toString() {
        return "OSMRelation{" +
                "members=" + members +
                ", attribute=" + attribute +
                ", Tags=" + Tags +
                '}';
    }
}
