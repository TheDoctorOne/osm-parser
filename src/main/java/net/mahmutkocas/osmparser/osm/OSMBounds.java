package net.mahmutkocas.osmparser.osm;

import net.mahmutkocas.osmparser.OSMKeys;
import net.mahmutkocas.osmparser.Utils;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Special Node.
 * */
public class OSMBounds {
    public final double minLat;
    public final double minLon;
    public final double maxLat;
    public final double maxLon;

    public OSMBounds(double minLat, double minLon, double maxLat, double maxLon) {
        this.minLat = minLat;
        this.minLon = minLon;
        this.maxLat = maxLat;
        this.maxLon = maxLon;
    }

    public static OSMBounds PARSE(Node node) {
        if(node.getNodeName().equals(OSMKeys.ROOT.BOUNDS)) {
            NamedNodeMap map = node.getAttributes();
            double minLat = Double.parseDouble(Utils.checkIfAttrAvailable(OSMKeys.BOUNDS_ATTR.MIN_LAT, map, "0"));
            double minLon = Double.parseDouble(Utils.checkIfAttrAvailable(OSMKeys.BOUNDS_ATTR.MIN_LON, map, "0"));
            double maxLat = Double.parseDouble(Utils.checkIfAttrAvailable(OSMKeys.BOUNDS_ATTR.MAX_LAT, map, "0"));
            double maxLon = Double.parseDouble(Utils.checkIfAttrAvailable(OSMKeys.BOUNDS_ATTR.MAX_LON, map, "0"));
            return new OSMBounds(minLat, minLon, maxLat, maxLon);
        }
        return null;
    }
}
