package net.mahmutkocas.osmparser;

import net.mahmutkocas.osmparser.model.OSMBounds;
import net.mahmutkocas.osmparser.model.OSMNode;
import net.mahmutkocas.osmparser.model.OSMRelation;
import net.mahmutkocas.osmparser.model.OSMWay;

import java.util.HashMap;
import java.util.Map;

public class OSMDocument {
    private Map<Long, OSMNode> nodes = new HashMap<>();
    private Map<Long, OSMWay> ways = new HashMap<>();
    private Map<Long, OSMRelation> relations = new HashMap<>();
    private OSMBounds bounds = null;

    public void addNode(OSMNode node) {
        nodes.put(node.getId(), node);
    }

    public void addWay(OSMWay way) {
        ways.put(way.getId(), way);
    }

    public void addRelation(OSMRelation relation) {
        relations.put(relation.getId(), relation);
    }

    public OSMNode getNodeById(long id) {
        return nodes.get(id);
    }

    public OSMWay getWayById(long id) {
        return ways.get(id);
    }

    public OSMRelation getRelationById(long id) {
        return relations.get(id);
    }

    public Map<Long, OSMNode> getNodes() {
        return nodes;
    }

    public Map<Long, OSMWay> getWays() {
        return ways;
    }

    public Map<Long, OSMRelation> getRelations() {
        return relations;
    }

    public OSMBounds getBounds() {
        return bounds;
    }

    protected void setBounds(OSMBounds bounds) {
        this.bounds = bounds;
    }
}
