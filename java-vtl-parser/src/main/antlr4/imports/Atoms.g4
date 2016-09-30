grammar Atoms;

/* Atom */
exprAtom : variableRef;

variableRef : varID
            | constant
            ;

varID : IDENTIFIER;

constant : INTEGER_CONSTANT | FLOAT_CONSTANT | BOOLEAN_CONSTANT | STRING_CONSTANT | NULL_CONSTANT;

IDENTIFIER:LETTER(LETTER|'_'|'0'..'9')*;

INTEGER_CONSTANT  :'0'..'9'+;
BOOLEAN_CONSTANT  : 'true' | 'false' ;
STRING_CONSTANT   :'"' (~'"')* '"';
FLOAT_CONSTANT    : ('0'..'9')+ '.' ('0'..'9')* FLOATEXP?
                  | ('0'..'9')+ FLOATEXP
                  ;
NULL_CONSTANT     :'null';

fragment
FLOATEXP : ('e'|'E')(PLUS|MINUS)?('0'..'9')+;

PLUS : '+';
MINUS : '-';

fragment
LETTER : 'A'..'Z' | 'a'..'z';
