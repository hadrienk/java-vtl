
[codacy-link]: https://www.codacy.com/app/hadrien-kohl/ssb-java-vtl/dashboard
[travis-link]: https://travis-ci.org/statisticsnorway/java-vtl/branches
[gitter-link]: https://gitter.im/java-vtl/Lobby?utm_source=share-link&utm_medium=link&utm_campaign=share-link

[codacy-img]: https://img.shields.io/codacy/grade/e187c210f99b4c179550b9bcb1c92860/develop.svg
[codacy-cov-img]: https://img.shields.io/codacy/coverage/e187c210f99b4c179550b9bcb1c92860/develop.svg
[travis-img]: https://img.shields.io/travis/statisticsnorway/java-vtl/develop.svg
[gitter-img]: https://img.shields.io/gitter/room/java-vtl/Lobby.svg


[![Build Status][travis-img]][travis-link]
[![Codacy Badge][codacy-img]][codacy-link]
[![Codacy coverage][codacy-cov-img]][codacy-link]
[![Gitter][gitter-img]][gitter-link]

# Java VTL: Java implementation of VTL

The Java VTL project is an open source java implementation of the
[VTL 1.1 draft specification](https://sdmx.org/?page_id=5096). It follows
the JSR-223 Java Scripting API and exposes a simple connector interface
one can implement in order to integrate with any data stores.

## Modules

The project is divided in modules;

- java-vtl-parent
    - java-vtl-parser, contains the lexer and parser for VTL.
    - java-vtl-model, VTL data model.
    - java-vtl-script, JSR-223 implementation.
    - java-vtl-connector, connector API.
    - java-vtl-tools, various tools.

## Usage

Add a dependency to the maven project

```xml
<dependency>
    <groupId>no.ssb.vtl</groupId>
    <artifactId>java-vtl-script</artifactId>
    <version>[VERSION]</version>
</dependency>
```

## Evaluate VTL expressions

```java
ScriptEngine engine = new VTLScriptEngine(connector);

Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
engine.eval("ds1 := get(\"foo\")" +
            "ds2 := get(\"bar\")" +
            "ds3 := [ds1, ds2] {" +
            "   filter ds1.id = \"string\"," +
            "   sum := ds1.measure + ds2.measure" +
            "}");

System.out.println(bindings.get("ds3"))
```

## Connect to external systems

VTL Java uses the `no.ssb.vtl.connector.Connector` interface to access and
export data to external systems.

The Connector interface defines three methods:

```java
public interface Connector {

    boolean canHandle(String identifier);

    Dataset getDataset(String identifier) throws ConnectorException;

    Dataset putDataset(String identifier, Dataset dataset) throws ConnectorException;

}
```

The method `canHandle(String identifier)` is used by the engine to find
 which connector is able to provide a Dataset for a given identifier.

The method `getDataset(String identifier)` is then called to get the dataset.
Example implementations can be found in the `java-vtl-ssb-api-connector` module
but a very crude implementation could be as such:

```java
class StaticDataset implements Dataset {

    private final DataStructure structure = DataStructure.builder()
            .put("id", Role.IDENTIFIER, String.class)
            .put("period", Role.IDENTIFIER, Instant.class)
            .put("measure", Role.MEASURE, Integer.class)
            .put("attribute", Role.ATTRIBUTE, String.class)
            .build();

    @Override
    public Stream<DataPoint> getData() {

        List<Map<String, Object>> data = new ArrayList<>();
        HashMap<String, Object> row = new HashMap<>();
        Instant period = Instant.now();
        for (int i = 0; i < 100; i++) {
            row.put("id", "id #" + i);
            row.put("period", period);
            row.put("measure", i);
            row.put("attribute", "attribute #" + i);
            data.add(row);
        }

        return data.stream().map(structure::wrap);
    }

    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        return Optional.empty();
    }

    @Override
    public Optional<Long> getSize() {
        return Optional.of(100L);
    }

    @Override
    public DataStructure getDataStructure() {
        return structure;
    }
}
```

# Implementation roadmap

This is an overview of the implementation progress.

[done]: http://progressed.io/bar/100?title=done "Done"

Group|Operators|Progress|Comment
---|---|---|---
General purpose|round parenthesis|
General purpose|assignment|![done][done]
General purpose|membership|![done][done]
General purpose|get|![usable](http://progressed.io/bar/20)|The keep, filter and aggregate are not yet reflected in the connector interface.
General purpose|put|![usable](http://progressed.io/bar/90)|The Connector interface is defined but expressions are not recognized yet.
Clauses|rename|![done][done]
Clauses|filter|
Clauses|keep|
Clauses|calc|
Clauses|attrcalc|
Clauses|aggregate|
Conditional|if-then-else|
Conditional|nvl|![usable](http://progressed.io/bar/50)|Dataset as input not implemented.
Validation|Comparisons (>,<,>=,<=,=,<>)|
Validation|in,not in, between|
Validation|isnull|![done][done]|Implemented syntax are `isnull(value)`, `value is null` and `value is not null`|
Validation|exist_in, not_exist_in|
Validation|exist_in_all, not_exist_in_all|
Validation|check|
Validation|match_characters|
Validation|match_values|
Statistical|min, max|
Statistical|hierarchy|
Statistical|aggregate|
Relational|union|
Relational|intersect|
Relational|symdiff|
Relational|setdiff|
Relational|merge|
Boolean|and|
Boolean|or|
Boolean|xor|
Boolean|not|
Mathematical|unary plus and minus|
Mathematical|addition, substraction|
Mathematical|multiplication, division|
Mathematical|round|
Mathematical|abs|
Mathematical|trunc|
Mathematical|power, exp, nroot|
Mathematical|in, log|
Mathematical|mod|
String|length|
String|concatenation|
String|trim|
String|upper/lower case|
String|substring|
String|indexof|
String|date_from_string|![usable](http://progressed.io/bar/25)|Dataset as input not implemented. Only YYYY date format accepted.


















