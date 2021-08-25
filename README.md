# Open Street Map XML/.OSM Parser
## Purpose
Parsing ".osm" file which is either exported from "openstreetmap.org" or generated in similar manner with Open Street Map.
## Gradle
[![Maven Central](https://img.shields.io/maven-central/v/net.mahmutkocas/osmparser.svg)](https://search.maven.org/search?q=g:net.mahmutkocas)
[![License: The MIT](https://img.shields.io/badge/License-MIT-orange.svg)](https://opensource.org/licenses/MIT)
```groovy
implementation group: 'net.mahmutkocas', name: 'osmparser', version: '[VERSION]'
```
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
List<BasicRouteModel> routeModels = osmDocument.getType(Tag.Type.HIGHWAY) // Only returns highways.
```
Possible BasicRouteModels are OSMNode, OSMWay, OSMRelation.  Also see "isTypeAll" and "isTypeAny".
### Getting Vector Data of the Route Model(Lat Long)
Ex:
```java
// Assuming routeModels contain at least one item.
List<LatLon> highway = routeModels.get(0).getPath();
```
Note: OSM's XML file may not contain all the information about "Relation"s it provides. So "getPath()" method may throw NullPointerException for these relations.


 

