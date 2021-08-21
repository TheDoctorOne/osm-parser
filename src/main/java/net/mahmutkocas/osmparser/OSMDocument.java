package net.mahmutkocas.osmparser;

import net.mahmutkocas.osmparser.osm.*;
import net.mahmutkocas.osmparser.osm.child.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        relation.setOwner(this);
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

    /**
     * If model contains the type, gets returned.
     * Possible RouteModels are {@link OSMNode}, {@link OSMWay}, {@link OSMRelation}
     * */
    public List<BaseRouteModel> getType(Tag.Type type) {
        List<BaseRouteModel> models = new ArrayList<>();

        for(OSMNode node : getNodes().values()) {
            if(node.isType(type))
                models.add(node);
        }

        for(OSMWay way : getWays().values()) {
            if(way.isType(type)) {
                models.add(way);
            }
        }

        for(OSMRelation relation : getRelations().values()) {
            if(relation.isType(type)) {
                models.add(relation);
            }
        }

        return models;
    }

    /**
     * If model contains one of type in the passed list, gets returned.
     * Possible RouteModels are {@link OSMNode}, {@link OSMWay}, {@link OSMRelation}
     * */
    public List<BaseRouteModel> getTypeAny(List<Tag.Type> types) {
        List<BaseRouteModel> models = new ArrayList<>();

        for(OSMNode node : getNodes().values()) {
            if(node.isTypeAny(types))
                models.add(node);
        }

        for(OSMWay way : getWays().values()) {
            if(way.isTypeAny(types)) {
                models.add(way);
            }
        }

        for(OSMRelation relation : getRelations().values()) {
            if(relation.isTypeAny(types)) {
                models.add(relation);
            }
        }

        return models;
    }

    /**
     * If model contains all of types in the passed list, gets returned.
     * Possible RouteModels are {@link OSMNode}, {@link OSMWay}, {@link OSMRelation}
     * */
    public List<BaseRouteModel> getTypeAll(List<Tag.Type> types) {
        List<BaseRouteModel> models = new ArrayList<>();

        for(OSMNode node : getNodes().values()) {
            if(node.isTypeAll(types))
                models.add(node);
        }

        for(OSMWay way : getWays().values()) {
            if(way.isTypeAll(types)) {
                models.add(way);
            }
        }

        for(OSMRelation relation : getRelations().values()) {
            if(relation.isTypeAll(types)) {
                models.add(relation);
            }
        }

        return models;
    }

}
