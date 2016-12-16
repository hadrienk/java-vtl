Java implementation of the Validation Transformation Language
# Status
[![Build Status](https://travis-ci.org/hadrienk/java-vtl.svg?branch=master)](https://travis-ci.org/hadrienk/java-vtl)

# Implementation roadmap


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
Conditional|nvl|
Validation|Comparisons (>,<,>=,<=,=,<>)|
Validation|in,not in, between|
Validation|isnull|
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


















