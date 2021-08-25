package net.mahmutkocas.osmparser.model;

import net.mahmutkocas.osmparser.osm.child.Member;

public class MemberNotExistsException extends NullPointerException {

    public final Member member;
    public MemberNotExistsException(Member member) {
        super();
        this.member = member;
    }
}
