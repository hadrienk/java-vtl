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
statement : identifier ':=' datasetExpression
          | identifier ':=' block
          ;

block : '{' statement+ '}' ;

/* Expressions */
datasetExpression : <assoc=right>datasetExpression clauseExpression #withClause
           | relationalExpression                                   #withRelational
           | getExpression                                          #withGet
           | putExpression                                          #withPut
           | exprAtom                                               #withAtom
           | checkExpression                                        #withCheck
           ;


getExpression : 'get' '(' datasetId ')';
putExpression : 'put(todo)';

datasetId : STRING_CONSTANT ;

/* Atom */
exprAtom : variableRef;

checkExpression : 'check' '(' checkParam ')';

checkParam : datasetExpression (',' checkRows)? (',' checkColumns)? ( 'errorcode' '(' errorCode ')' )? ( 'errorlevel' '=' '(' errorLevel ')' )?;

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
renameParam : from=componentRef 'as' to=identifier ( 'role' '=' role )? ;

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
    : booleanExpression op=AND booleanExpression
    | booleanExpression op=(OR|XOR) booleanExpression
    | booleanNot
    | booleanIsNull
    | booleanEquality
    | BOOLEAN_CONSTANT
    ;

booleanEquality
    : left=booleanParam op=( EQ | NE | LE | LT | GE | GT ) right=booleanParam
    ;
booleanParam
    : componentRef
    | constant
    ;

booleanNot
    : 'not' '(' booleanExpression ')';

booleanIsNull
    : 'isnull' '(' booleanParam ')'
    ;

//datasetExpression
//    : 'dsTest'
//    ;

EQ : '='  ;
NE : '<>' ;
LE : '<=' ;
LT : '<'  ;
GE : '>=' ;
GT : '>'  ;

AND : 'and' ;
OR  : 'or' ;
XOR : 'xor' ;

//WS : [ \r\t\u000C] -> skip ;

relationalExpression : unionExpression | joinExpression ;

unionExpression : 'union' '(' datasetExpression (',' datasetExpression )* ')' ;

joinExpression : '[' joinDefinition ']' '{' joinBody '}';

joinDefinition : type=( INNER | OUTER | CROSS )? datasetRef (',' datasetRef )* ( 'on' componentRef (',' componentRef )* )? ;

joinBody : joinClause (',' joinClause)* ;

// TODO: Implement role and implicit
joinClause : role? identifier '=' joinCalcExpression # joinCalcClause
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

nvlExpression : 'nvl' '(' variableRef ',' nvlRepValue ')';

nvlRepValue : constant;

// Left recursive
joinCalcExpression : leftOperand=joinCalcExpression  sign=( '*' | '/' ) rightOperand=joinCalcExpression #joinCalcProduct
                   | leftOperand=joinCalcExpression  sign=( '+' | '-' ) rightOperand=joinCalcExpression #joinCalcSummation
                   | '(' joinCalcExpression ')'                                                         #joinCalcPrecedence
                   | componentRef                                                                       #joinCalcReference
                   | constant                                                                           #joinCalcAtom
                   | booleanExpression                                                                  #joinCalcBoolean
                   | conditionalExpression                                                              #joinCalcCondition
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


INTEGER_CONSTANT  : DIGIT+;
BOOLEAN_CONSTANT  : 'true' | 'false' ;
STRING_CONSTANT   :'"' (~'"')* '"';

FLOAT_CONSTANT    : (DIGIT)+ '.' (DIGIT)* FLOATEXP?
                  | (DIGIT)+ FLOATEXP
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

