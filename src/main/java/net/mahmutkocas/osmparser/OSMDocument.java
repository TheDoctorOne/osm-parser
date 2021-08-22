package net.mahmutkocas.osmparser;

import net.mahmutkocas.osmparser.osm.*;
import net.mahmutkocas.osmparser.osm.child.Tag;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OSMDocument {
    public static double VERSION = 0.6;

    public static class META {
        public final double version;
        public final String generator;
        public final String copyright;
        public final String attribution;
        public final String license;

        public META(double version, String generator, String copyright, String attribution, String license) {
            this.version = version;
            this.generator = generator;
            this.copyright = copyright;
            this.attribution = attribution;
            this.license = license;
        }

        public static META PARSE(Node node) {
            if(node.getNodeName().equals(OSMKeys.ROOT.OSM)) {
                NamedNodeMap map = node.getAttributes();
                double version = Double.parseDouble(Utils.checkIfAttrAvailable(OSMKeys.META.VERSION, map, "0"));
                String generator = Utils.checkIfAttrAvailable(OSMKeys.META.GENERATOR, map, "0");
                String copyright = Utils.checkIfAttrAvailable(OSMKeys.META.COPYRIGHT, map, "0");
                String attribution = Utils.checkIfAttrAvailable(OSMKeys.META.ATTRIBUTION, map, "0");
                String license = Utils.checkIfAttrAvailable(OSMKeys.META.LICENSE, map, "0");
                return new META(version, generator, copyright, attribution, license);
            }
            return null;
        }
    }

    private META meta = null;
    private OSMBounds bounds = null;
    private Map<Long, OSMNode> nodes = new HashMap<>();
    private Map<Long, OSMWay> ways = new HashMap<>();
    private Map<Long, OSMRelation> relations = new HashMap<>();

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

    public META getMeta() {
        return meta;
    }

    protected void setMeta(META meta) {
        this.meta = meta;
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
