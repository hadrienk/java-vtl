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
    <vtl-code expression="expression" output="datasets" input="datasets">test := get("1104")</vtl-code>
    <vtl-data datasets="datasets">
        <vtl-dataset>
            Identifier1[I,String],Measure[M,String],Attribute[A,String]
            1,2,3
            4,5,6
            7,8,9
            7,8,9
        </vtl-dataset>
    </vtl-data>


</div>


Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla porttitor nec neque placerat tincidunt. Etiam volutpat pharetra libero id imperdiet. In non orci quam. Duis viverra velit est, nec sodales sapien vulputate sed. Sed ullamcorper neque lorem, vitae tempor diam laoreet sed. Integer est lacus, vulputate at libero id, eleifend vestibulum erat. Maecenas non porttitor odio. Aliquam auctor luctus massa, in congue ante tincidunt eget. Donec vel hendrerit lacus. Duis sit amet tristique purus. Nam cursus placerat mi, non tristique nunc consectetur in. Sed porta augue id sapien hendrerit vestibulum. Phasellus ut erat quis diam vestibulum facilisis a gravida leo. Etiam porttitor eget justo aliquet iaculis.








<div class="vtl-example">
    <div class="vtl-block vtl-data">
        <p>Any html</p>
    </div>
    <div class="vtl-block vtl-code">
        <div class="vtl-toolbar">
            <div class="btn-group btn-group-xs" role="group" aria-label="...">
                <button type="button" class="btn btn-default">
                    <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span> expand
                </button>
                <button type="button" class="btn btn-default">
                    <span class="glyphicon glyphicon-edit" aria-hidden="true"></span> edit
                </button>
                <button type="button" class="btn btn-default">
                    <span class="glyphicon glyphicon-play" aria-hidden="true"></span> run
                </button>
            </div>
        </div>
        <ui-codemirror2>
ds1 := get("ds1")
ds3 := [ds1] {
    fold ds1.value1, value2, 'value3' to type, value
}
        </ui-codemirror2>
    </div>
    <div class="vtl-block vtl-data">
        <uib-tabset active="activeForm">
            <uib-tab heading="ds1">
                <table class="table table-condensed">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Identifier 1</th>
                            <th>Measure 1</th>
                            <th>Attribute 1</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th>1</th>
                            <td>Mark</td>
                            <td>Otto</td>
                            <td>@mdo</td>
                        </tr>
                        <tr>
                            <th>2</th>
                            <td>Jacob</td>
                            <td>Thornton</td>
                            <td>@fat</td>
                        </tr>
                            <tr>
                            <th>3</th>
                            <td colspan="2">Larry the Bird</td>
                            <td>@twitter</td>
                        </tr>
                    </tbody>
                </table>
            </uib-tab>
            <uib-tab heading="ds2">
                <table class="table table-condensed">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Identifier 2</th>
                            <th>Measure 2</th>
                            <th>Attribute 3</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th>1</th>
                            <td>Mark</td>
                            <td>Otto</td>
                            <td>@mdo</td>
                        </tr>
                        <tr>
                            <th>2</th>
                            <td>Jacob</td>
                            <td>Thornton</td>
                            <td>@fat</td>
                        </tr>
                            <tr>
                            <th>3</th>
                            <td colspan="2">Larry the Bird</td>
                            <td>@twitter</td>
                        </tr>
                    </tbody>
                </table>
            </uib-tab>
        </uib-tabset>
    </div>
</div>



| Identifier | Measure | Attribute |
|:--------------------|:--------|:----------|
| Value               | Value   | Value     |
| Value               | value   | Value     |



