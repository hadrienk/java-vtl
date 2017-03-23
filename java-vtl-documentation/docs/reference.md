# VTL Reference

## Lexical structure

### Identifier

### Comments

### White space

### Literals

Numbers, String, Dates, Periods etc.

## Data types

### any

### Scalars

#### scalar

#### integers

integer
integer [a:]
integer [:b]
integer [a:b]
integer {x1, ..., xn}

#### float

#### null

#### number

#### boolean

#### date

#### strings

string [a]
string [a:b]
string {s1, ..., sn}

### Collections

#### list

#### set

#### collection

### Datasets

### Record

### Product

### Function

### ()

## Join expressions

### Inner join

<div vtl-example>
    <vtl-code>
innerJoin := [inner left, right] {
    filter true
}
    </vtl-code>
    <vtl-dataset name="left">
id[I,String],measure[M,String],attribute[A,String]
3,left value 3, left attribute 3
4,left value 4, left attribute 4
5,left value 5, left attribute 5
6,left value 6, left attribute 6
    </vtl-dataset>
    <vtl-dataset name="right">
id[I,String],measure[M,String],attribute[A,String]
1,right value 1, right attribute 1
2,right value 2, right attribute 2
3,right value 3, right attribute 3
4,right value 4, right attribute 4
    </vtl-dataset>
    <vtl-data datasets="datasets" errors="errors"></vtl-data>
</div>

### Outer join

<div vtl-example>
    <vtl-code>
outerJoin := [outer left, right] {
    filter true
}
    </vtl-code>
    <vtl-dataset name="left">
id1[I,String],id2[I,String],measure[M,String],attribute[A,String]
1,3,left value 3, left attribute 3
1,4,left value 4, left attribute 4
1,5,left value 5, left attribute 5
1,6,left value 6, left attribute 6
    </vtl-dataset>
    <vtl-dataset name="right">
id1[I,String],id2[I,String],measure[M,String],attribute[A,String]
1,1,right value 1, right attribute 1
1,2,right value 2, right attribute 2
1,3,right value 3, right attribute 3
1,4,right value 4, right attribute 4
    </vtl-dataset>
    <vtl-data datasets="datasets" errors="errors"></vtl-data>
</div>

### Fold clause

Fold transposes a single data point of the input Dataset into several
data points. It adds Identifier dim and measure msr to the resulting
Dataset, inserts into the resulting Dataset a data point for each value
A in the element list and assigns to the inserted data point dim = A and
msr = value of measure A in the input Dataset.

When measure A is null then fold does not create a data point for that
measure. Note that in general unfolding and folding are not exactly
symmetric operations, i.e. in some cases the fold operation applied to
the unfolded Dataset does not recreate exactly the original Dataset
(before unfolding).

<div vtl-example>
    <vtl-code>
folded := [population] {
    fold '0-14', '15-24', '25-64', '65+' to age, percent
}
unfolded := [folded] {
    unfold age, percent to "0-14", "15-24"
}
    </vtl-code>
    <vtl-dataset name="population">
country[I,String],0-14[M,String],15-24[M,String],25-64[M,String],65+[M,String]
France ,18.1%,12.2%,51.0%,18.7%
Norway ,18.6%,13.0%,52.0%,16.4%
Italy  ,14.0%,9.8%,54.4%,21.7%
Sweden ,17.3%,12.2%,50.5%,20.0%
    </vtl-dataset>
    <vtl-data datasets="datasets" errors="errors"></vtl-data>
</div>

### Unfold

<div vtl-example>
    <vtl-code>
unfolded := [colors] {
    unfold color, wavelength to "indigo", "blue", "yellow"
}
    </vtl-code>
    <vtl-dataset name="colors">
color[I,String],wavelength[M,String]
blue, 475nm
indigo, 445nm
orange, 570nm
red, 650nm
violet, 400 nm
yellow, 510nm
    </vtl-dataset>
    <vtl-data datasets="datasets" errors="errors"></vtl-data>
</div>

## Check operators

The check operators are used to validate data sets. Three check operator
 variants are available, check with rule sets, check with hierarchical
 rule sets and check with single boolean rule.

The return value of the check function depend of parameters.
When used with the parameter "condition", the resulting dataset will only
 contain a condition measure of type Boolean indicating if the DataPoint
 is valid according to the rule set, hierarchical rule set or boolean
 expression.
With the parameter "measures", the resulting dataset contains all the
measures of the input dataset.

### Check with single rule
<div vtl-example>
    <vtl-code>
folded := check(data.valid <> "na")
    </vtl-code>
    <vtl-dataset name="data">
country[I,String],population[M,String]
France , 64M
Norway , 5M
Italy  , na
Sweden , 9M
    </vtl-dataset>
    <vtl-data datasets="datasets" errors="errors"></vtl-data>
</div>

## Conditional operators

### nvl
The operator nvl replaces null values with a value given as a parameter.
<div vtl-example>
    <vtl-code>
join := [left, left] {
    b = nvl(population, 0)
}
    </vtl-code>
    <vtl-dataset name="left">
country[I,String],population[M,String]
France , 64M
Norway , 5M
Italy  , null
Sweden , 9M
    </vtl-dataset>
    <vtl-data datasets="datasets" errors="errors"></vtl-data>
</div>

## Boolean operators

### Null operators

VTL adopts 3VL (three-value logic); null is not considered a "value",
 but rather a marker (or placeholder) indicating the absence of value.

| *p*     | *q*     | *p*  OR *q* | *p*  AND *q* | *p*  = *q* |
|---------|---------|-------------|--------------|------------|
| True    | True    | True        | True         | True       |
| True    | False   | True        | False        | False      |
| True    | Unknown | True        | Unknown      | Unknown    |
| False   | True    | True        | False        | False      |
| False   | False   | False       | False        | True       |
| False   | Unknown | Unknown     | False        | Unknown    |
| Unknown | True    | True        | Unknown      | Unknown    |
| Unknown | False   | Unknown     | False        | Unknown    |
| Unknown | Unknown | Unknown     | Unknown      | Unknown    |

| *p*     | NOT *p* |
|:--------|:--------|
| True    | False   |
| False   | True    |
| Unknown | Unknown |

Null in boolean operators evaluates to false. In order to test whether
 or not a value is null one can use the postfix operator `is null` or
 `is not null` as well as the functional equivalents `isnull()` or
 `not(isnull())`.

<div vtl-example>
    <vtl-code>
isnotnull := [data] {
    filter value is not null
}
isnull := {
    filter value is null
}
    </vtl-code>
    <vtl-dataset name="data">
country[I,String],value[M,String]
Null , null
NotNull , value
    </vtl-dataset>
    <vtl-data datasets="datasets" errors="errors"></vtl-data>
</div>

## Hierarchy operator

The hierarchy operator replaces an identifier component by using a
hierarchical structure.

- World
    - Europe
        - Eastern Europe
            - Poland
            - Ukraine
            - Romania
            - Hungary
            - Slovakia
            - ...
        - Northern Europe
            - Denmark
            - Norway
            - Sweden
            - Iceland
            - ...
        - Southern Europe
            - Italy
            - Spain
            - Portugal
            - ...
        - Western Europe
            - Belgium
            - France
            - ...

<div vtl-example>
    <vtl-code>
result := hierarchy(data, data.region, world, true)
    </vtl-code>
    <vtl-dataset name="data">
year[I,String],region[I,String],pop[M,Integer]
2000,Belgium,2000
2000,Denmark,2000
2000,France,2000
2000,Hungary,2000
2000,Iceland,2000
2000,Italy,2000
2000,Norway,2000
2000,Poland,2000
2000,Portugal,2000
2000,Romania,2000
2000,Slovakia,2000
2000,Spain,2000
2000,Sweden,2000
2000,Ukraine,2000
2002,Belgium,2002
2002,Denmark,2002
2002,France,2002
2002,Hungary,2002
2002,Iceland,2002
2002,Italy,2002
2002,Norway,2002
2002,Poland,2002
2002,Portugal,2002
2002,Romania,2002
2002,Slovakia,2002
2002,Spain,2002
2002,Sweden,2002
2002,Ukraine,2002
2004,Belgium,2004
2004,Denmark,2004
2004,France,2004
2004,Hungary,2004
2004,Iceland,2004
2004,Italy,2004
2004,Norway,2004
2004,Poland,2004
2004,Portugal,2004
2004,Romania,2004
2004,Slovakia,2004
2004,Spain,2004
2004,Sweden,2004
2004,Ukraine,2004
    </vtl-dataset>
    <vtl-dataset name="world">
from[I,String],to[I,String],sign[M,String]
Western Europe,Europe,+
Eastern Europe,Europe,+
Southern Europe,Europe,+
Northern Europe,Europe,+
Belgium,Western Europe,+
Denmark,Northern Europe,+
France,Western Europe,+
Hungary,Eastern Europe,+
Iceland,Northern Europe,+
Italy,Southern Europe,+
Norway,Northern Europe,+
Poland,Eastern Europe,+
Portugal,Southern Europe,+
Romania,Eastern Europe,+
Slovakia,Eastern Europe,+
Spain,Southern Europe,+
Sweden,Northern Europe,+
Ukraine,Eastern Europe,+
        </vtl-dataset>
    <vtl-data datasets="datasets" errors="errors"></vtl-data>
</div>
