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
start : statement+ EOF;

/* Assignment */
statement : variableID ':=' datasetExpression;

/* Expressions */
datasetExpression : <assoc=right>datasetExpression clauseExpression #withClause
           | relationalExpression                                   #withRelational
           | getExpression                                          #withGet
           | putExpression                                          #withPut
           | exprAtom                                               #withAtom
           ;


getExpression : 'get' '(' datasetId ')';
putExpression : 'put(todo)';

datasetId : STRING_CONSTANT ;

/* Atom */
exprAtom : variableRef;

datasetRef: IDENTIFIER;

componentRef : datasetRef '.' componentID
             | componentID ;
componentID : IDENTIFIER;

variableRef : datasetRef
            | componentRef
            ;
variableID: IDENTIFIER;


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
renameParam : from=componentRef 'as' to=componentRef ( 'role' '=' role )? ;

role : ( 'IDENTIFIER' | 'MEASURE' | 'ATTRIBUTE' ) ;

filter      : 'filter' booleanExpression ;

keep        : 'keep' booleanExpression ( ',' booleanExpression )* ;

calc        : 'calc' ;

attrcalc    : 'attrcalc' ;

aggregate   : 'aggregate' ;

//booleanExpression : 'booleanExpression' ;

//varID       : 'varId';

//WS          : [ \t\n\t] -> skip ;

booleanExpression
    : booleanExpression AND booleanExpression
    | booleanExpression ( OR booleanExpression | XOR booleanExpression )
    | booleanEquallity
    | BOOLEAN_CONSTANT
    ;
booleanEquallity
    : booleanEquallity ( ( EQ | NE | LE | GE ) booleanEquallity )
    | datasetExpression
    // typed constant?
    ;

//datasetExpression
//    : 'dsTest'
//    ;

EQ : '='  ;
NE : '<>' ;
LE : '<=' ;
GE : '>=' ;

AND : 'and' ;
OR  : 'or' ;
XOR : 'xor' ;

//WS : [ \r\t\u000C] -> skip ;

relationalExpression : unionExpression | joinExpression ;

unionExpression : 'union' '(' datasetExpression (',' datasetExpression )* ')' ;

joinExpression : '[' joinDefinition ']' '{' joinBody '}';

joinDefinition : INNER? joinParam  #joinDefinitionInner
               | OUTER  joinParam  #joinDefinitionOuter
               | CROSS  joinParam  #joinDefinitionCross ;

joinParam : datasetRef (',' datasetRef )* ( 'on' dimensionExpression (',' dimensionExpression )* )? ;

dimensionExpression : IDENTIFIER; //unimplemented

joinBody : joinClause (',' joinClause)* ;

joinClause : role? variableID '=' joinCalcExpression # joinCalcClause
           | joinDropExpression                 # joinDropClause
           | joinKeepExpression                 # joinKeepClause
           | joinRenameExpression               # joinRenameClause
           | joinFilterExpression               # joinFilterClause
           | joinFoldExpression                 # joinFoldClause
           | joinUnfoldExpression               # joinUnfoldClause
           ;

joinFoldExpression      : 'fold' elements=foldUnfoldElements 'to' dimension=componentRef ',' measure=componentRef ;
joinUnfoldExpression    : 'unfold' dimension=componentRef ',' measure=componentRef 'to' elements=foldUnfoldElements ;
// TODO: The spec writes examples with parentheses, but it seems unecessary to me.
// TODO: The spec is unclear regarding types of the elements, we support strings only for now.
// TODO: Reuse component references
foldUnfoldElements      : STRING_CONSTANT (',' STRING_CONSTANT)* ;

// Left recursive
joinCalcExpression : leftOperand=joinCalcExpression  sign=( '*' | '/' ) rightOperand=joinCalcExpression #joinCalcProduct
                   | leftOperand=joinCalcExpression  sign=( '+' | '-' ) rightOperand=joinCalcExpression #joinCalcSummation
                   | '(' joinCalcExpression ')'                                                         #joinCalcPrecedence
                   | componentRef                                                                        #joinCalcReference
                   | constant                                                                           #joinCalcAtom
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
joinRenameParameter  : from=componentRef 'to' to=componentID ;

// Filter clause
joinFilterExpression : 'filter' booleanExpression ;

//role : ( 'IDENTIFIER' | 'MEASURE' | 'ATTRIBUTE' ) ;

INNER : 'inner' ;
OUTER : 'outer' ;
CROSS : 'cross' ;


INTEGER_CONSTANT  : DIGIT+;
BOOLEAN_CONSTANT  : 'true' | 'false' ;
STRING_CONSTANT   :'"' (~'"')* '"';

FLOAT_CONSTANT    : (DIGIT)+ '.' (DIGIT)* FLOATEXP?
                  | (DIGIT)+ FLOATEXP
                  ;

NULL_CONSTANT     : 'null';

IDENTIFIER : REG_IDENTIFIER | ESCAPED_IDENTIFIER ;
//regular identifiers start with a (lowercase or uppercase) English alphabet letter, followed by zero or more letters, decimal digits, or underscores
fragment REG_IDENTIFIER: LETTER(LETTER|'_'|DIGIT)* ; //TODO: Case insensitive??
//VTL 1.1 allows us to escape the limitations imposed on regular identifiers by enclosing them in single quotes (apostrophes).
fragment ESCAPED_IDENTIFIER:  QUOTE (~'\'' | '\'\'')+ QUOTE;
fragment QUOTE : '\'';

PLUS : '+';
MINUS : '-';

fragment DIGIT    : '0'..'9' ;
fragment FLOATEXP : ('e'|'E')(PLUS|MINUS)?('0'..'9')+;
fragment LETTER   : 'A'..'Z' | 'a'..'z';

WS : [ \n\r\t\u000C] -> skip ;
COMMENT : '/*' .*? '*/' -> skip;

