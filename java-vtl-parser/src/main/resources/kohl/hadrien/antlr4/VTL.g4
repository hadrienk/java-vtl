grammar VTL;

options{
    language = Java;
}

import Conditional;

start : statement+ EOF;

/* Assignment */
statement : variableRef ':=' expression;

/* Atom */
exprAtom : variableRef;


expression : getExpression
           | putExpression
           | exprAtom
           ;

getExpression : 'get(todo)';
putExpression : 'put(todo)';

variableRef : varID
            | constant
            ;

constant : INTEGER_CONSTANT | FLOAT_CONSTANT | BOOLEAN_CONSTANT | STRING_CONSTANT | NULL_CONSTANT;

varID : IDENTIFIER;

IDENTIFIER:LETTER(LETTER|'_'|'0'..'9')*;


INTEGER_CONSTANT:'0'..'9'+;
FLOAT_CONSTANT : ('0'..'9')+ '.' ('0'..'9')* FLOATEXP?
               | ('0'..'9')+ FLOATEXP
               ;
STRING_CONSTANT :'"' (~'"')* '"';
NULL_CONSTANT:'null';

fragment
FLOATEXP : ('e'|'E')(PLUS|MINUS)?('0'..'9')+;

PLUS : '+';
MINUS : '-';

fragment
LETTER : 'A'..'Z' | 'a'..'z';

WS : [ \r\t\u000C] -> skip ;
