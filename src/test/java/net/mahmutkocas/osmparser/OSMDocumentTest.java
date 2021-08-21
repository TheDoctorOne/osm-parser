package net.mahmutkocas.osmparser;

import net.mahmutkocas.osmparser.osm.BaseRouteModel;
import net.mahmutkocas.osmparser.osm.child.Tag;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class OSMDocumentTest {

    @Test
    public void getType() throws IOException, SAXException, ParserConfigurationException {
        OSMDocument osmDocument = OSMParser.parseXML("map.xml");
        Tag.Type type = Tag.Type.HIGHWAY;
        List<BaseRouteModel> ret = osmDocument.getType(type);
        assert ret.size() > 0;
    }
}