package net.mahmutkocas.osmparser.model;

import net.mahmutkocas.osmparser.OSMKeys;
import net.mahmutkocas.osmparser.Utils;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Member {
    public enum MemberType {
        NODE,
        WAY,
        OTHER;

        public static MemberType fromString(String string) {
            if(string.equals(OSMKeys.MEMBER_TYPE_ATTR.NODE))
                return MemberType.NODE;
            if(string.equals(OSMKeys.MEMBER_TYPE_ATTR.WAY))
                return MemberType.WAY;
            return MemberType.OTHER;
        }
    }

    public final MemberType type;
    public final long ref;
    public final String role;

    public Member(MemberType type, long ref, String role) {
        this.type = type;
        this.ref = ref;
        this.role = role;
    }

    public static List<Member> PARSE_MEMBERS(NodeList nodes) {
        List<Member> members = new ArrayList<>();
        for(int i=0;i<nodes.getLength();i++) {
            Member member = Member.PARSE(nodes.item(i));
            if(member != null)
                members.add(member);
        }
        return members;
    }

    public static Member PARSE(Node node) {
        if(node.getNodeName().equals(OSMKeys.CHILD_ROOT.MEMBER)) {
            NamedNodeMap namedNodeMap = node.getAttributes();
            MemberType memType = MemberType.fromString(Utils.checkIfAttrAvailable(OSMKeys.MEMBER_ATTR.TYPE, namedNodeMap, "other"));
            long ref = Long.parseLong(Utils.checkIfAttrAvailable(OSMKeys.MEMBER_ATTR.REF, namedNodeMap, "0"));
            String role = Utils.checkIfAttrAvailable(OSMKeys.MEMBER_ATTR.ROLE, namedNodeMap, "");
            return new Member(memType, ref, role);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Member{" +
                "type=" + type.toString() +
                ", ref=" + ref +
                ", role='" + role + '\'' +
                '}';
    }
}
