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
statement : variableRef ':=' datasetExpression;

exprMember : datasetExpression ('#' componentID)? ;

/* Expressions */
datasetExpression : <assoc=right>datasetExpression clauseExpression #withClause
           | relationalExpression                                   #withRelational
           | getExpression                                          #withGet
           | putExpression                                          #withPut
           | exprAtom                                               #withAtom
           ;

componentID : IDENTIFIER;

getExpression : 'get' '(' datasetId ')';
putExpression : 'put(todo)';

datasetId : STRING_CONSTANT ;

WS : [ \n\r\t\u000C] -> skip ;
COMMENT : '/*' .*? '*/' -> skip;


/* Atom */
exprAtom : variableRef;

variableRef : constant
            | varID
            ;

varID : IDENTIFIER;

constant : INTEGER_CONSTANT | FLOAT_CONSTANT | BOOLEAN_CONSTANT | STRING_CONSTANT | NULL_CONSTANT;

IDENTIFIER:LETTER(LETTER|'_'|'0'..'9')* ;

INTEGER_CONSTANT  : DIGIT+;
BOOLEAN_CONSTANT  : 'true' | 'false' ;
STRING_CONSTANT   :'"' (~'"')* '"';

FLOAT_CONSTANT    : (DIGIT)+ '.' (DIGIT)* FLOATEXP?
                  | (DIGIT)+ FLOATEXP
                  ;

NULL_CONSTANT     : 'null';


PLUS : '+';
MINUS : '-';

fragment DIGIT    : '0'..'9' ;
fragment FLOATEXP : ('e'|'E')(PLUS|MINUS)?('0'..'9')+;
fragment LETTER   : 'A'..'Z' | 'a'..'z';

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
renameParam : from=varID 'as' to=varID ( 'role' '=' role )? ;

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

joinParam : varID (',' varID )* ( 'on' dimensionExpression (',' dimensionExpression )* )? ;

joinBody : joinClause (',' joinClause)* ;

joinClause : role? varID '=' joinCalcExpression # joinCalcClause
           | joinDropExpression                 # joinDropClause
           | joinKeepExpression                 # joinKeepClause
           | joinRenameExpression               # joinRenameClause
           | joinFilterExpression               # joinFilterClause
           ;
           //| joinFilter
           //| joinKeep
           //| joinRename ;
           //| joinDrop
           //| joinUnfold
           //| joinFold ;

// Left recursive
joinCalcExpression : leftOperand=joinCalcExpression  sign=( '*' | '/' ) rightOperand=joinCalcExpression #joinCalcProduct
                   | leftOperand=joinCalcExpression  sign=( '+' | '-' ) rightOperand=joinCalcExpression #joinCalcSummation
                   | '(' joinCalcExpression ')'                                                         #joinCalcPrecedence
                   | joinCalcRef                                                                        #joinCalcReference
                   | constant                                                                           #joinCalcAtom
                   ;

joinCalcRef : (aliasName=varID '.')? componentName=varID ;


// Drop clause
joinDropExpression : 'drop' joinDropRef (',' joinDropRef)* ;
joinDropRef : (aliasName=varID '.')? componentName=varID ;

// Keep clause
joinKeepExpression : 'keep' joinKeepRef (',' joinKeepRef)* ;
joinKeepRef : (aliasName=varID '.')? componentName=varID ;

// TODO: Use in keep, drop and calc.
// TODO: Make this the membership operator.
// TODO: Revise this when the final version of the specification precisely define if the rename needs ' or not.
joinComponentReference : (aliasName=varID '.')? componentName=varID ;

// Rename clause
joinRenameExpression : 'rename' joinRenameParameter (',' joinRenameParameter)* ;
joinRenameParameter  : from=joinComponentReference 'to' to=varID ;

// Filter clause
joinFilterExpression : 'filter' booleanExpression ;

//role : ( 'IDENTIFIER' | 'MEASURE' | 'ATTRIBUTE' ) ;

INNER : 'inner' ;
OUTER : 'outer' ;
CROSS : 'cross' ;

// For tests only
//varID               : 'varID' NUM+;
//variableRef         : ( 'varName' | 'constant' ) NUM+;
//datasetExpression   : 'datasetExpr' NUM*;
dimensionExpression : 'dimensionExpr' NUM*;
//constant            : NUM ;

NUM : '0'..'9' ;

//WS          : [ \t\n\t] -> skip ;

