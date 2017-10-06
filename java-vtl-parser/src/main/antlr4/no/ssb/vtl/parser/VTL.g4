/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
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
 * =========================LICENSE_END==================================
 */
grammar VTL;
start : statement+ EOF;

statement : assignment | expression ;

nativeCall         : functionName=NATIVE_FUNCTIONS LPAR functionParameters? RPAR ;
functionCall       : functionName=REG_IDENTIFIER LPAR functionParameters? RPAR ;

functionParameters : namedExpression ( COMMA namedExpression)*
                   | expression ( COMMA expression )*
                   | expression ( COMMA expression )* COMMA namedExpression ( COMMA namedExpression)* ;


namedExpression     : name=REG_IDENTIFIER COLON expression ;

// Expressions
expression : LPAR expression RPAR
           | nativeCall
           | functionCall
           // | unaryOperators
           | <assoc=right> expression operatorConcat expression
           | datasetExpression
           | expression clauseExpression
           | variable
           | litteral ;

variable : ( ESCAPED_IDENTIFIER | REG_IDENTIFIER ) ;

operatorConcat : op=CONCAT ;

// Literal.
litteral : nullLiteral
         | booleanLiteral
         | dateLiteral
         | integerLiteral
         | floatLiteral
         | stringLiteral ;

nullLiteral     : NULL_CONSTANT ;
booleanLiteral  : BOOLEAN_CONSTANT ;
dateLiteral     : 'TODO:date' ;
integerLiteral  : INTEGER_CONSTANT ;
floatLiteral    : FLOAT_CONSTANT ;
stringLiteral   : STRING_CONSTANT ;

assignment : variable ASSIGNMENT expression ;

/* TODO: deprecate, use expression instead */
datasetExpression : <assoc=right>datasetExpression clauseExpression     #withClause
                  | hierarchyExpression                                 #withHierarchy
                  | relationalExpression                                #withRelational
                  | function                                            #withFunction
                  | exprAtom                                            #withAtom
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
        //TODO: This causes an ambiguity warning for an aggregation function with implicit component e.g. sum(ds) ...
       : 'sum' '(' (datasetRef|componentRef) ')' aggregationParms       #aggregateSum
       | 'avg' '(' (datasetRef|componentRef) ')' aggregationParms       #aggregateAvg
       ;

aggregationParms: aggregationClause=(GROUP_BY|ALONG) componentRef (',' componentRef)*;

ALONG : 'along' ;
GROUP_BY : 'group by' ;

datasetId : STRING_CONSTANT ;

/* Atom */
exprAtom : datasetRef;

checkFunction : 'check' '(' checkParam ')';

checkParam : datasetExpression (',' checkRows)? (',' checkColumns)? (',' 'errorcode' '(' errorCode ')' )? (',' 'errorlevel' '=' '(' errorLevel ')' )?;

checkRows : ( 'not_valid' | 'valid' | 'all' ) ;
checkColumns : ( 'measures' | 'condition' ) ;
errorCode : STRING_CONSTANT ;
errorLevel : INTEGER_CONSTANT ;

datasetRef: variable ;

componentRef : ( datasetRef '.')? variable ;

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
renameParam : from=componentRef 'as' to=variable ( ROLE role=componentRole )? ;

filter      : 'filter' booleanExpression ;

keep        : 'keep' booleanExpression ( ',' booleanExpression )* ;

calc        : 'calc' ;

attrcalc    : 'attrcalc' ;

aggregate   : 'aggregate' ;

booleanExpression                                                                                       //Evaluation order of the operators
    : '(' booleanExpression ')'                                                 # BooleanPrecedence     // I
    | FUNC_ISNULL '(' booleanParam ')'                                          # BooleanIsNullFunction // II  All functional operators
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

/* Operators */

CONCAT : '||' ;
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

/* Core functions */

NATIVE_FUNCTIONS : (NUMERIC_FUNCTIONS | STRING_FUNCTIONS ) ;

FUNC_ISNULL : 'isnull' ;

// Numeric
fragment NUMERIC_FUNCTIONS : ( FUNC_ROUND | FUNC_CEIL | FUNC_FLOOR | FUNC_ABS |
                      FUNC_TRUNC | FUNC_EXP  | FUNC_LN    | FUNC_LOG |
                      FUNC_POWER | FUNC_SQRT | FUNC_NROOT | FUNC_MOD |
                      FUNC_LISTSUM
                    ) ;

FUNC_ROUND  : 'round' ;
FUNC_CEIL   : 'ceil' ;
FUNC_FLOOR  : 'floor' ;
FUNC_ABS    : 'abs' ;
FUNC_TRUNC  : 'trunc' ;
FUNC_EXP    : 'exp' ;
FUNC_LN     : 'ln' ;
FUNC_LOG    : 'log' ;
FUNC_POWER  : 'power' ;
FUNC_SQRT   : 'sqrt' ;
FUNC_NROOT  : 'nroot' ;
FUNC_MOD    : 'mod' ;
FUNC_LISTSUM: 'listsum' ;

// String
fragment STRING_FUNCTIONS : ( FUNC_LENGTH | FUNC_TRIM  | FUNC_LTRIM  | FUNC_RTRIM |
                     FUNC_UPPER  | FUNC_LOWER | FUNC_SUBSTR | FUNC_INSTR
                     // | FUNC_D_F_S
                   | FUNC_REPLACE
                   ) ;

FUNC_LENGTH  : 'length' ;
FUNC_TRIM    : 'trim' ;
FUNC_LTRIM   : 'ltrim' ;
FUNC_RTRIM   : 'rtrim' ;
FUNC_UPPER   : 'upper' ;
FUNC_LOWER   : 'lower' ;
FUNC_SUBSTR  : 'substr' ;
FUNC_INSTR   : 'instr' ;
// TODO: Fix conflict FUNC_D_F_S   : 'date_from_string' ;
FUNC_REPLACE : 'replace' ;

//WS : [ \r\t\u000C] -> skip ;

relationalExpression : unionExpression | joinExpression ;

unionExpression : 'union' '(' datasetExpression (',' datasetExpression )* ')' ;

joinExpression : '[' joinDefinition ']' '{' joinBody '}';

joinDefinition : type=( INNER | OUTER | CROSS )? datasetRef (',' datasetRef )* ( 'on' componentRef (',' componentRef )* )? ;

joinBody : joinClause (',' joinClause)* ;

joinClause : implicit=IMPLICIT? role=componentRole? variable ASSIGNMENT joinCalcExpression # joinCalcClause
           | joinDropExpression                 # joinDropClause
           | joinKeepExpression                 # joinKeepClause
           | joinRenameExpression               # joinRenameClause
           | joinFilterExpression               # joinFilterClause
           | joinFoldExpression                 # joinFoldClause
           | joinUnfoldExpression               # joinUnfoldClause
           ;
// TODO: The spec writes examples with parentheses, but it seems unecessary to me.
// TODO: The spec is unclear regarding types of the elements, we support only strings ATM.
joinFoldExpression      : 'fold' componentRef (',' componentRef)* 'to' dimension=variable ',' measure=variable ;
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
joinRenameParameter  : from=componentRef 'to' to=variable ;

// Filter clause
joinFilterExpression : 'filter' booleanExpression ;

componentRole : role=(IDENTIFIER | MEASURE | ATTRIBUTE);

INNER : 'inner' ;
OUTER : 'outer' ;
CROSS : 'cross' ;

ROLE : 'role' ;

// TODO: Rename to INTEGER_LITTERAL
INTEGER_CONSTANT  : (PLUS|MINUS)?DIGIT+;
// TODO: Rename to BOOLEAN_LITTERAL
BOOLEAN_CONSTANT  : 'true' | 'false' ;

// TODO: Rename to STRING_LITTERAL
STRING_CONSTANT   :'"' (ESCAPED_QUOTE|~'"')* '"';
fragment ESCAPED_QUOTE : '""';

// TODO: Rename to FLOAT_LITTERAL
FLOAT_CONSTANT    : (PLUS|MINUS)?(DIGIT)+ '.' (DIGIT)* FLOATEXP?
                  | (PLUS|MINUS)?(DIGIT)+ FLOATEXP
                  ;

// TODO: Rename to NULL
NULL_CONSTANT     : 'null';

IMPLICIT : 'implicit' ;

IDENTIFIER : 'identifier' | 'IDENTIFIER' ;
MEASURE : 'measure' | 'MEASURE' ;
ATTRIBUTE : 'attribute' | 'ATTRIBUTE'  ;

//regular identifiers start with a (lowercase or uppercase) English alphabet letter, followed by zero or more letters, decimal digits, or underscores
REG_IDENTIFIER : LETTER(LETTER|'_'|DIGIT)* ;
//VTL 1.1 allows us to escape the limitations imposed on regular identifiers by enclosing them in single quotes (apostrophes).
ESCAPED_IDENTIFIER:  QUOTE (~['\r\n] | '\'\'')+ QUOTE;

fragment QUOTE : '\'';

PLUS : '+';
MINUS : '-';

LPAR : '(' ;
RPAR : ')' ;
COMMA : ',' ;
COLON : ':' ;

fragment DIGIT    : '0'..'9' ;
fragment FLOATEXP : ('e'|'E')(PLUS|MINUS)?('0'..'9')+;
fragment LETTER   : 'A'..'Z' | 'a'..'z';

WS : [ \n\r\t\u000C] -> skip ;
COMMENT : '/*' .*? '*/' -> skip;
LINE_COMMENT : LineComment -> skip;

fragment LineComment
   : '//' ~ [\r\n]*
   ;

