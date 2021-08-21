package net.mahmutkocas.osmparser.osm;

import net.mahmutkocas.osmparser.model.LatLon;
import net.mahmutkocas.osmparser.osm.attr.DummyAttribute;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DummyRouteModel extends BaseRouteModel<DummyAttribute> {

    @Test
    public void TestRouteModel() {
        BaseRouteModel baseRouteModel = new DummyRouteModel();
    }

    @Override
    public List<LatLon> getPath() {
        return null;
    }
}