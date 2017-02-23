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



<div class="vtl-example" data-vtl-example>
    <div class="vtl-block">
Given the following dataset Lorem ipsum dolor sit amet, consectetur adipiscing elit.
    </div>
    <vtl-code expression="expression" output="datasets" input="inputs"></vtl-code>
    <vtl-data datasets="inputs.concat(datasets)"></vtl-data>
    <vtl-dataset name="test" add-to="inputs">
Identifier1[I,String],Measure[M,String],Attribute[A,String]
1,2,3
4,5,6
7,8,9
7,8,9
    </vtl-dataset>
</div>



| Identifier | Measure | Attribute |
|:--------------------|:--------|:----------|
| Value               | Value   | Value     |
| Value               | value   | Value     |



