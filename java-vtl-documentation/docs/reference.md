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
2,4,left value 4, left attribute 4
3,5,left value 5, left attribute 5
4,6,left value 6, left attribute 6
    </vtl-dataset>
    <vtl-dataset name="right">
id1[I,String],id2[I,String],measure[M,String],attribute[A,String]
1,1,right value 1, right attribute 1
2,2,right value 2, right attribute 2
3,3,right value 3, right attribute 3
4,4,right value 4, right attribute 4
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
folded := [colors] {
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
