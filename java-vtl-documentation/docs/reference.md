# Syntax

## Variables

VTL support two type of variable names, regular names and escaped names.
Regular variable names in must start with a letter followed by letters, numbers
and the underscore (`_`) characters. Escaped variable names must be enclosed by single
quotes (`'`) and can contain any characters except new lines. Single quotes can be escaped
by doubling them.

<div vtl-example>
    <vtl-code>
variableName := value
aVariableName1 := value
'1Variable' := value
'variable''with''quotes' := value
    </vtl-code>
</div>

## Comments

Line comments are prefixed with two slashes ('//'). Block comments are
surrounded by `/*` and `*/` and can contain new lines.

<div vtl-example>
    <vtl-code>
// Single line comment
/*
 * A block
 * comment
 */
    </vtl-code>
</div>

## Data types

### Integers

<div vtl-example>
    <vtl-code>
variable := 1
variable := -1
variable := 0
    </vtl-code>
</div>

### Floats

<div vtl-example>
    <vtl-code>
variable := -0.1e-10
variable := -0.1e10
variable := -0.1e+10
variable := 0.1e-10
variable := 0.1e10
variable := 0.1e+10
variable := +0.1e-10
variable := +0.1e10
variable := +0.1e+10
variable := 0.01
variable := -0.001
variable := +0.0001
    </vtl-code>
</div>

### Strings

<div vtl-example>
    <vtl-code>
variable := "STRING"
variable := "STR""ING"
    </vtl-code>
</div>

### Booleans

<div vtl-example>
     <vtl-code>
variable := true
variable := false
     </vtl-code>
 </div>


### Dates and time

<div vtl-example>
     <vtl-code>
variable := 2000-01-01T00:00:00.000Z
variable := 2000-01-01T00:00:00.000+00:15
     </vtl-code>
 </div>

# Expression and operators

## Precedence

One can adjust the precedence of expressions in VTL using the parenthesis (`(` and `)`)

<div vtl-example>
     <vtl-code>
variable := ( expression )
     </vtl-code>
 </div>

## Arithmetic operators

The multiplication, division, addition and subtraction operators can be
used with Float and Integer types.

<div vtl-example>
     <vtl-code>
variable := 1 + 2 / 3 * 4
     </vtl-code>
 </div>

If Floats and Integers are mixed in the same arithmetic expression the
resulting number will be Float

<div vtl-example>
     <vtl-code>
floatVariable := 1 + 2.0
floatVariable := 1 * 1.0
     </vtl-code>
</div>

## Concatenation operator

The concatenation operator `||` concatenate two strings.

<div vtl-example>
    <vtl-code>
contac := [data] {
    result := left || " " || right
}
</vtl-code>
    <vtl-dataset name="data">
left[I,String],right[I,String]
Hello,World
null,World
Hello,null
    </vtl-dataset>
    <vtl-data datasets="datasets" errors="errors"></vtl-data>
</div>

## String functions

<div vtl-example>
    <vtl-code>
strings := [data] {
	withTrailingSpc := "   " || value || "   ",
    rightTrim := "[" || rtrim(withTrailingSpc) || "]",
    leftTrim := "[" || ltrim(withTrailingSpc) || "]",
    'trim' := "[" || trim(withTrailingSpc) || "]",
    noTrim := "[" || withTrailingSpc || "]"
}
</vtl-code>
    <vtl-dataset name="data">
id[I,Long],value[M,String]
1L,Hello World!
    </vtl-dataset>
    <vtl-data datasets="datasets" errors="errors"></vtl-data>
</div>

### Trim, ltrim and rtrim functions

The `trim`, `ltrim` and `rtrim` take a _String_ as input and return a
_String_.

The `ltrim` and `rtrim` functions remove leading (to the left and
trailing (to the right) white characters from the given _String_,
respectively.

The `trim` functions remove both the leading and trailing white
characters.

<pre>
    String   trim(String value)
    String  rtrim(String value)
    String  ltrim(String value)
</pre>

### Upper and lower function

<pre>
    String  upper(String value)
    String  lower(String value)
</pre>

The `upper` function transforms all of the characters of the given
_String_ to upper case. The `lower` functions transforms all the
characters of the given _String_ to lower case.

## Mathematical functions

In addition to the arithmetic operators, basic mathematical operations
are available as functions.

<div vtl-example>
    <vtl-code>
math := [data] {
    posInteger := data.value,
    negInteger := data.value * -1,
    posFloat := data.value / 10,
    negFloat := negInteger / 10,
    absFn   := abs(negFloat),
    logFn   := log(posInteger, posInteger),
    ceilFn  := ceil(posFloat * 5),
    floorFn := floor(posFloat * 5),
    expFn   := exp(posFloat),
    lnFn    := ln(posInteger),
    logFn   := log(posInteger, posInteger),
    powerFn := power(posInteger, posInteger),
    nrootFn := nroot(posInteger, posInteger),
    roundFn := round(posFloat, 2)
}
</vtl-code>
    <vtl-dataset name="data">
value[I,Long]
1L
3L
4L
5L
6L
7L
8L
9L
10L
    </vtl-dataset>
    <vtl-data datasets="datasets" errors="errors"></vtl-data>
</div>

### Abs function

<pre>
    Float   abs(Float value)
    Integer abs(Integer value)
</pre>

The `abs` function takes an _Integer_ or a
_Float_ value and returns its absolute value.

### Ceil and Floor functions

<pre>
    Integer floor(Float value)
    Integer ceil(Float value)
</pre>

The `ceil` and `floor` functions take as input a _Float_ value and
return an _Integer_ value.

`ceil` will return the smallest _Integer_ value that is greater than the
given value.

`floor` will return the largest _Integer_ value that is smaller than the
given value.

### Trunc and Round functions

<pre>
    Float trunc(Float value, Integer decimals)
    Float floor(Float value, Integer decimals)
</pre>


The `trunc` and 'round' functions take as input a _Float_ value and an
_Integer_ that represent a number of decimals. Both `trunc` and `round`
return a _Float_.

`trunc` will wil truncate to the decimals whereas `round` will round up
to the decimals.

### Ln function (Napierian logarithm)

<pre>
    Float ln(Float value)
</pre>

### Exp function

<pre>
    Float exp(Integer value)
    Float exp(Float value)
</pre>

### Power function

<pre>
    Float power(Float value, Float exponent)
</pre>

### Sqrt function

<pre>
    Float sqrt(Float value)
</pre>

### Nroot function

<pre>
    Float nroot(Float value, Float n)
</pre>

### Datasets

### Record

### Product



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
join := [outer left, right] {
    nvl_result := nvl(right.value, "was null")
}
    </vtl-code>
    <vtl-dataset name="left">
id1[I,String],id2[I,String],value[M,String],attribute[A,String]
1,3,left value 3, left attribute 3
1,4,left value 4, left attribute 4
1,5,left value 5, left attribute 5
1,6,left value 6, left attribute 6
    </vtl-dataset>
    <vtl-dataset name="right">
id1[I,String],id2[I,String],value[M,String],attribute[A,String]
1,1,right value 1, right attribute 1
1,2,right value 2, right attribute 2
1,3,right value 3, right attribute 3
1,4,right value 4, right attribute 4
    </vtl-dataset>
    <vtl-data datasets="datasets" errors="errors"></vtl-data>
</div>

### if-then-else

<pre>
    if ds_cond_1 then ds_1 { elseif ds_cond_2 then ds_2 }* else ds_3
</pre>

The `if-then-else` operator returns the constant of the first evaluated true condition. 

`ds_cond_1` is the first _Boolean_ condition, `ds_cond_2` is an optional _Boolean_ condition. 

`ds_1` is a constant returned if `ds_cond_1` evaluated true. `ds_2` is a constant returned if `ds_cond_2` evaluated true.
`ds_3` is a constant returned if `ds_cond_2` not evaluated true.

`ds_1`, `ds_2` and `ds_3` must have the same type.

<div vtl-example>
    <vtl-code>
result := [data] {
    ifThenElseString := if M1 < 2 then "<2" elseif M1 < 3 then "<3" elseif M1 < 4 then "<4" else ">=4",
    ifThenElseInteger := if M1 < 2 then 0 else 1,
    ifThenElseFunctions := if M1 is not null then M1 * 2 else nvl(M1, 0) 
}
    </vtl-code>
    <vtl-dataset name="data">
ID[I,String],M1[M,Long]
1 , 1
2 , 2
3 , 3
4 , 4
5 , null
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
postfixIsNull := [data] {
    filter value is null
}
postfixIsNotNull := [data] {
    filter value is not null
}

functionalIsNull := [data] {
	filter isnull(value) 
}

functionalIsNotNull := [data] {
	filter not(isnull(value))
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

The hierarchy operator aggregates all measures of a dataset mapping one
identifier with a hierarchy.

For instance, consider the following hierarchy:

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
year[I,String],region[I,String],pop[M,Long]
2000,Belgium,2000L
2000,Denmark,2000L
2000,France,2000L
2000,Hungary,2000L
2000,Iceland,2000L
2000,Italy,2000L
2000,Norway,2000L
2000,Poland,2000L
2000,Portugal,2000L
2000,Romania,2000L
2000,Slovakia,2000L
2000,Spain,2000L
2000,Sweden,2000L
2000,Ukraine,2000L
2002,Belgium,2002L
2002,Denmark,2002L
2002,France,2002L
2002,Hungary,2002L
2002,Iceland,2002L
2002,Italy,2002L
2002,Norway,2002L
2002,Poland,2002L
2002,Portugal,2002L
2002,Romania,2002L
2002,Slovakia,2002L
2002,Spain,2002L
2002,Sweden,2002L
2002,Ukraine,2002L
2004,Belgium,2004L
2004,Denmark,2004L
2004,France,2004L
2004,Hungary,2004L
2004,Iceland,2004L
2004,Italy,2004L
2004,Norway,2004L
2004,Poland,2004L
2004,Portugal,2004L
2004,Romania,2004L
2004,Slovakia,2004L
2004,Spain,2004L
2004,Sweden,2004L
2004,Ukraine,2004L
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

## String operators

### substr
<pre>
    String substr(Integer startPosition, Integer length)
</pre>

The `substr` operator takes as input `startPosition` which is the index of the character
in the string from which the substring is performed and `length` which is the number of
the characters in the string to be taken starting from `startPosition`. 
The operator returns a _String_ value.

<div vtl-example>
    <vtl-code>
result := [data] {
    sub := substr(M1, 5, 6)
}
    </vtl-code>
    <vtl-dataset name="data">
ID[I,String],M1[M,String]
1 , hello world
2 , hello
3 , null
    </vtl-dataset>
    <vtl-data datasets="datasets" errors="errors"></vtl-data>
</div>

### date_from_string

The operator date_from_string converts a string into a date.
<div vtl-example>
    <vtl-code>
join := [left] {
    b := date_from_string(M1, "YYYY")
}
    </vtl-code>
    <vtl-dataset name="left">
ID[I,String],M1[M,String]
1 , 2016
2 , 2017
3 , null
    </vtl-dataset>
    <vtl-data datasets="datasets" errors="errors"></vtl-data>
</div>


## Operators outside the specification

### integer_from_string
    Note 
        This operator is not part of the VTL 1.1 specification.
    
<pre>
    Integer integer_from_string(String value)
</pre>

The `integer_from_string` operator takes as input a _String_ value and returns an _Integer_ value.
<div vtl-example>
    <vtl-code>
join := [data] {
    integerFromString := integer_from_string(M1)
}
    </vtl-code>
    <vtl-dataset name="data">
ID[I,String],M1[M,String]
1 , 10
2 , 101
3 , null
    </vtl-dataset>
    <vtl-data datasets="datasets" errors="errors"></vtl-data>
</div>

### float_from_string
    Note 
        This operator is not part of the VTL 1.1 specification.
    
<pre>
    Float float_from_string(String value)
</pre>

The `float_from_string` operator takes as input a _String_ value and returns an _Float_ value. The input
value format is described in detail in section 3.10.2 of the Java Language Specification.
Additionally a comma (`,`) is allowed as a decimal point.
<div vtl-example>
    <vtl-code>
join := [data] {
    floatFromString := float_from_string(M1)
}
    </vtl-code>
    <vtl-dataset name="data">
ID[I,String],M1[M,String]
1  , -0.1e-10
2  , -0.1e10
3  , -0.1e+10
4  , 0.1e-10
5  , 0.1e10
6  , 0.1e+10
7  , +0.1e-10
8  , +0.1e10
9  , +0.1e+10
10 , 0.01
11 , -0.001
12 , +0.0001
13 , null
    </vtl-dataset>
    <vtl-data datasets="datasets" errors="errors"></vtl-data>
</div>

### string_from_number
    Note 
        This operator is not part of the VTL 1.1 specification.
    
<pre>
    String string_from_number(Number value)
</pre>

The `string_from_number` operator takes as input a _Number_ value and returns an _String_ value.
The exact result of this operator is described in the documentation of `java.lang.Double.toString()`
and `java.lang.Long.toString()` methods available at [Oracle Java Help Center](https://docs.oracle.com/en/java).
<div vtl-example>
    <vtl-code>
join := [data] {
    stringFromInteger := string_from_number(M1),
    stringFromFloat := string_from_number(M2)
}
    </vtl-code>
    <vtl-dataset name="data">
ID[I,String],M1[M,Long],M2[M,Double]
1 , 10, 10.01
2 , 0, -0.001
3 , null, null
    </vtl-dataset>
    <vtl-data datasets="datasets" errors="errors"></vtl-data>
</div>
