package net.mahmutkocas.osmparser;

import org.w3c.dom.NamedNodeMap;

public class Utils {
    public static String checkIfAttrAvailable(String key, NamedNodeMap map, String defaultValue) {
        try {
            String s = map.getNamedItem(key).getNodeValue();
            if (s !=null && !s.trim().equals("")){
                return s;
            }
        } catch (Exception ignored) {}
        return defaultValue;
    }
}
