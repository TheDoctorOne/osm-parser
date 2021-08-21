# Open Street Map XML/.OSM Parser
## Purpose
Parsing ".osm" file which is either exported from "openstreetmap.org" or generated in similar manner with Open Street Map.
## How to use
OSMParser has static function called "parseXML(String)", by passing file path one can easily parse the XML file.

```java
    OSMDocument osmDocument = OSMParser.parseXML(file);
```
OSMDocument provides parsed OSM Nodes, Ways and Relations with getter methods.

```java
    Map<Long, OSMNode> nodeMap = osmDocument.getNodes(); // Returns nodes.
    Map<Long, OSMWay> wayMap = osmDocument.getWays(); // Returns ways.
    Map<Long, OSMRelation> relationMap = osmDocument.getRelations(); // Returns relations.
```
Returned values are maps. Keys are the IDs of the elements. By calling ".values()" method, one can get values.

```java
    List<OSMNode> nodes = nodeMap.values(); // Raw Node List.
```
### Filtering by Tags
Ex:

```java
List<BasicRouteModel> routeModels = osmDocument.getType(Type.Tag.HIGHWAY) // Only returns highways.
```
Possible BasicRouteModels are OSMNode, OSMWay, OSMRelation.  Also see "isTypeAll" and "isTypeAny".
### Getting Vector Data of the Route Model(Lat Long)
Ex:
```java
// Assuming routeModels contain at least one item.
List<LatLon> highway = routeModels.get(0).getPath();
```


 

