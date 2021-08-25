package net.mahmutkocas.osmparser.osm;

import net.mahmutkocas.osmparser.OSMDocument;
import net.mahmutkocas.osmparser.OSMKeys;
import net.mahmutkocas.osmparser.model.LatLon;
import net.mahmutkocas.osmparser.model.MemberNotExistsException;
import net.mahmutkocas.osmparser.osm.attr.RelationAttribute;
import net.mahmutkocas.osmparser.osm.child.Member;
import net.mahmutkocas.osmparser.osm.child.Tag;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OSMRelation extends BaseRouteModel<RelationAttribute> {
    private final List<Member> members = new ArrayList<>();
    private OSMDocument owner = null;
    private List<LatLon> latLons = new ArrayList<>();

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

    public void setOwner(OSMDocument owner) {
        this.owner = owner;
    }

    public OSMDocument getOwner() {
        return owner;
    }


    /**
     * If Member ID not present in the current {@link OSMDocument} throws {@link MemberNotExistsException}
     * Also see getPath(boolean)
     * */
    @Override
    public List<LatLon> getPath() {
        return getPath(false);
    }

    /**
     * If Member ID not present in the current {@link OSMDocument} and the input boolean is false throws {@link MemberNotExistsException}
     * */
    public List<LatLon> getPath(boolean ignoreException) {
        if(latLons.size() > 0)
            return new ArrayList<>(latLons);
        return new ArrayList<>(calculatePath(ignoreException));
    }

    protected synchronized List<LatLon> calculatePath(boolean ignoreException) {
        if(latLons.size() > 0)
            return latLons;

        ArrayList<LatLon> latLons = new ArrayList<>();
        Map<Long, OSMNode> nodeMap = getOwner().getNodes();
        Map<Long, OSMWay> wayMap = getOwner().getWays();
        for(Member member : members) {
            switch (member.type) {
                case NODE:
                    OSMNode node = nodeMap.get(member.ref);
                    if(node == null)
                        if(ignoreException)
                            continue;
                        else
                            throw new MemberNotExistsException(member);
                    latLons.addAll(node.getPath());
                    break;
                case WAY:
                    OSMWay way = wayMap.get(member.ref);
                    if(way == null)
                        if(ignoreException)
                            continue;
                        else
                            throw new MemberNotExistsException(member);
                    latLons.addAll(wayMap.get(member.ref).getPath());
                    break;
            }
        }
        this.latLons = latLons;

        return latLons;
    }

    /**
     * Members are not mapped but Member Objects have reference id. <br>
     * Using the parsed {@link OSMDocument}, members can be resolved.
     * But its not guaranteed that {@link OSMDocument} will contain all referenced IDs.
     * */
    public List<Member> getMembers() {
        return members;
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
