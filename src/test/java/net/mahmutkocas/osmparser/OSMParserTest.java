package net.mahmutkocas.osmparser;

import org.junit.Test;

public class OSMParserTest {

    @Test
    public void parseXML() {
        OSMDocument osmDocument = null;
        try {
            osmDocument = OSMParser.parseXML("map.xml");
        } catch (Exception e) { e.printStackTrace(); }
        assert osmDocument != null;
    }
}