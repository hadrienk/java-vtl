/*-
 * #%L
 * Java VTL
 * %%
 * Copyright (C) 2016 Hadrien Kohl
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
grammar VTL;
start : assignment+ EOF;

/* Assignment */
assignment : identifier ASSIGNMENT datasetExpression
           | identifier ASSIGNMENT block
           ;

block : '{' assignment+ '}' ;

/* Expressions */
datasetExpression : <assoc=right>datasetExpression clauseExpression #withClause
           | hierarchyExpression                                    #withHierarchy
           | relationalExpression                                   #withRelational
           | function                                               #withFunction
           | exprAtom                                               #withAtom
           ;

hierarchyExpression : 'hierarchy' '(' datasetRef ',' componentRef ',' hierarchyReference ',' BOOLEAN_CONSTANT ( ',' ('sum' | 'prod') )? ')' ;
hierarchyReference : datasetRef ;

function : getFunction               #withGet
         | putFunction               #withPut
         | checkFunction             #withCheck
         | aggregationFunction       #withAggregation
         ;

getFunction : 'get' '(' datasetId ')';
putFunction : 'put(todo)';

aggregationFunction
       : 'sum' '(' (datasetRef|componentRef) ')' aggregationParms       #aggregateSum   //TODO: This causes an ambiguity warning for an aggregation function with implicit component e.g. sum(ds) ...
       | 'avg' '(' (datasetRef|componentRef) ')' aggregationParms       #aggregateAvg
       ;

aggregationParms: aggregationClause=(GROUP_BY|ALONG) componentRef (',' componentRef)*;

ALONG : 'along' ;
GROUP_BY : 'group by' ;

datasetId : STRING_CONSTANT ;

/* Atom */
exprAtom : variableRef;

checkFunction : 'check' '(' checkParam ')';

checkParam : datasetExpression (',' checkRows)? (',' checkColumns)? (',' 'errorcode' '(' errorCode ')' )? (',' 'errorlevel' '=' '(' errorLevel ')' )?;

checkRows : ( 'not_valid' | 'valid' | 'all' ) ;
checkColumns : ( 'measures' | 'condition' ) ;
errorCode : STRING_CONSTANT ;
errorLevel : INTEGER_CONSTANT ;

datasetRef: variableRef ;

componentRef : ( datasetRef '.')? variableRef ;
variableRef : identifier;

identifier : IDENTIFIER ;

constant : INTEGER_CONSTANT | FLOAT_CONSTANT | BOOLEAN_CONSTANT | STRING_CONSTANT | NULL_CONSTANT;


clauseExpression      : '[' clause ']' ;

clause       : 'rename' renameParam (',' renameParam)*     #renameClause
             | filter     #filterClause
             | keep       #keepClause
             | calc       #calcClause
             | attrcalc   #attrcalcClause
             | aggregate  #aggregateClause
             ;

// [ rename component as string,
//          component as string role = IDENTIFIER,
//          component as string role = MEASURE,
//          component as string role = ATTRIBUTE
// ]
renameParam : from=componentRef 'as' to=identifier ( 'role' role )? ;

role : ( 'IDENTIFIER' | 'MEASURE' | 'ATTRIBUTE' ) ;

filter      : 'filter' booleanExpression ;

keep        : 'keep' booleanExpression ( ',' booleanExpression )* ;

calc        : 'calc' ;

attrcalc    : 'attrcalc' ;

aggregate   : 'aggregate' ;

//varID       : 'varId';

//WS          : [ \t\n\t] -> skip ;

booleanExpression                                                                                       //Evaluation order of the operators
    : '(' booleanExpression ')'                                                 # BooleanPrecedence     // I
    | ISNULL_FUNC '(' booleanParam ')'                                          # BooleanIsNullFunction // II  All functional operators
    | booleanParam op=(ISNULL|ISNOTNULL)                                        # BooleanPostfix        // ??
    | left=booleanParam op=( LE | LT | GE | GT ) right=booleanParam             # BooleanEquality       // VII
    | op=NOT booleanExpression                                                  # BooleanNot            // IV
    | left=booleanParam op=( EQ | NE ) right=booleanParam                       # BooleanEquality       // IX
    | booleanExpression op=AND booleanExpression                                # BooleanAlgebra        // X
    | booleanExpression op=(OR|XOR) booleanExpression                           # BooleanAlgebra        // XI
    | BOOLEAN_CONSTANT                                                          # BooleanConstant
    ;

booleanParam
    : componentRef
    | constant
    ;

ASSIGNMENT : ':=' ;
EQ : '='  ;
NE : '<>' ;
LE : '<=' ;
LT : '<'  ;
GE : '>=' ;
GT : '>'  ;

AND : 'and' ;
OR  : 'or' ;
XOR : 'xor' ;
NOT : 'not' ;
ISNULL : 'is null' ;
ISNOTNULL : 'is not null' ;

ISNULL_FUNC : 'isnull' ;

//WS : [ \r\t\u000C] -> skip ;

relationalExpression : unionExpression | joinExpression ;

unionExpression : 'union' '(' datasetExpression (',' datasetExpression )* ')' ;

joinExpression : '[' joinDefinition ']' '{' joinBody '}';

joinDefinition : type=( INNER | OUTER | CROSS )? datasetRef (',' datasetRef )* ( 'on' componentRef (',' componentRef )* )? ;

joinBody : joinClause (',' joinClause)* ;

// TODO: Implement role and implicit
joinClause : role? identifier ASSIGNMENT joinCalcExpression # joinCalcClause
           | joinDropExpression                 # joinDropClause
           | joinKeepExpression                 # joinKeepClause
           | joinRenameExpression               # joinRenameClause
           | joinFilterExpression               # joinFilterClause
           | joinFoldExpression                 # joinFoldClause
           | joinUnfoldExpression               # joinUnfoldClause
           ;
// TODO: The spec writes examples with parentheses, but it seems unecessary to me.
// TODO: The spec is unclear regarding types of the elements, we support only strings ATM.
joinFoldExpression      : 'fold' componentRef (',' componentRef)* 'to' dimension=identifier ',' measure=identifier ;
joinUnfoldExpression    : 'unfold' dimension=componentRef ',' measure=componentRef 'to' STRING_CONSTANT (',' STRING_CONSTANT)* ;

conditionalExpression
    : nvlExpression
    ;

nvlExpression : 'nvl' '(' componentRef ',' nvlRepValue=constant ')';

dateFunction
    : dateFromStringFunction
    ;

dateFromStringFunction : 'date_from_string' '(' componentRef ',' format=STRING_CONSTANT ')';


// Left recursive
joinCalcExpression : leftOperand=joinCalcExpression  sign=( '*' | '/' ) rightOperand=joinCalcExpression #joinCalcProduct
                   | leftOperand=joinCalcExpression  sign=( '+' | '-' ) rightOperand=joinCalcExpression #joinCalcSummation
                   | '(' joinCalcExpression ')'                                                         #joinCalcPrecedence
                   | conditionalExpression                                                              #joinCalcCondition
                   | dateFunction                                                                       #joinCalcDate
                   | componentRef                                                                       #joinCalcReference
                   | constant                                                                           #joinCalcAtom
                   | booleanExpression                                                                  #joinCalcBoolean
                   ;


// Drop clause
joinDropExpression : 'drop' componentRef (',' componentRef)* ;

// Keep clause
joinKeepExpression : 'keep' componentRef (',' componentRef)* ;

// TODO: Use in keep, drop and calc.
// TODO: Make this the membership operator.
// TODO: Revise this when the final version of the specification precisely define if the rename needs ' or not.

// Rename clause
joinRenameExpression : 'rename' joinRenameParameter (',' joinRenameParameter)* ;
joinRenameParameter  : from=componentRef 'to' to=identifier ;

// Filter clause
joinFilterExpression : 'filter' booleanExpression ;

//role : ( 'IDENTIFIER' | 'MEASURE' | 'ATTRIBUTE' ) ;

INNER : 'inner' ;
OUTER : 'outer' ;
CROSS : 'cross' ;


INTEGER_CONSTANT  : (PLUS|MINUS)?DIGIT+;
BOOLEAN_CONSTANT  : 'true' | 'false' ;
STRING_CONSTANT   :'"' (ESCAPED_QUOTE|~'"')* '"';
fragment ESCAPED_QUOTE : '""';

FLOAT_CONSTANT    : (PLUS|MINUS)?(DIGIT)+ '.' (DIGIT)* FLOATEXP?
                  | (PLUS|MINUS)?(DIGIT)+ FLOATEXP
                  ;

NULL_CONSTANT     : 'null';

IDENTIFIER : REG_IDENTIFIER | ESCAPED_IDENTIFIER ;
//regular identifiers start with a (lowercase or uppercase) English alphabet letter, followed by zero or more letters, decimal digits, or underscores
REG_IDENTIFIER: LETTER(LETTER|'_'|DIGIT)* ; //TODO: Case insensitive??
//VTL 1.1 allows us to escape the limitations imposed on regular identifiers by enclosing them in single quotes (apostrophes).
fragment ESCAPED_IDENTIFIER:  QUOTE (~['\r\n] | '\'\'')+ QUOTE;
fragment QUOTE : '\'';

PLUS : '+';
MINUS : '-';

fragment DIGIT    : '0'..'9' ;
fragment FLOATEXP : ('e'|'E')(PLUS|MINUS)?('0'..'9')+;
fragment LETTER   : 'A'..'Z' | 'a'..'z';

WS : [ \n\r\t\u000C] -> skip ;
COMMENT : '/*' .*? '*/' -> skip;

