package net.mahmutkocas.osmparser;

public class OSMKeys {
	public static class META {
		public static String VERSION = "version";
		public static String GENERATOR = "generator";
		public static String COPYRIGHT = "copyright";
		public static String ATTRIBUTION = "attribution";
		public static String LICENSE = "license";
	}

	public static class ROOT {
		public static String OSM = "osm";
		public static String BOUNDS = "bounds";
		public static String NODE = "node";
		public static String WAY = "way";
		public static String RELATION = "relation";
	}
	
	public static class CHILD_ROOT {
		public static String TAG = "tag";
		public static String ND = "nd";
		public static String MEMBER = "member";
	}
	
	public static class ATTR {
		public static String ID = "id";
		public static String VERSION = "version";
		public static String USER = "user";
		public static String UID = "uid";
		public static String VISIBLE = "visible";
		public static String CHANGE_SET = "changeset";
		public static String TIMESTAMP = "timestamp";
	}
	
	public static class BOUNDS_ATTR {
		public static String MIN_LAT = "minlat";
		public static String MIN_LON = "minlon";
		public static String MAX_LAT = "maxlat";
		public static String MAX_LON = "maxlon";
	}
	public static class NODE_ATTR {
		public static String LAT = "lat";
		public static String LON = "lon";
	}
	
	public static class ND_ATTR {
		public static String REF = "ref";
	}

	public static class MEMBER_ATTR {
		public static String TYPE = "type";
		public static String REF = "ref";
		public static String ROLE = "role";
		
	}
	
	public static class MEMBER_TYPE_ATTR {
		public static String NODE = "node";
		public static String WAY = "way";
	}
	
	public static class TAG_ATTR {
		public static String KEY = "k";
		public static String VALUE = "v";
	}
}
