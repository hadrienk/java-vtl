grammar Clauses;

clause      : '[' ( rename | filter | keep | calc | attrcalc | aggregate ) ']' ;

// [ rename component as string,
//          component as string role = IDENTIFIER,
//          component as string role = MEASURE,
//          component as string role = ATTRIBUTE
// ]
rename      : 'rename' renameParam (',' renameParam )* ;
renameParam : from=varID 'as' to=varID role?
            ;

role : 'role' '=' ( 'IDENTIFIER' | 'MEASURE' | 'ATTRIBUTE' ) ;

filter      : 'filter' booleanExpression ;

keep        : 'keep' booleanExpression ( ',' booleanExpression )* ;

calc        : 'calc' ;

attrcalc    : 'attrcalc' ;

aggregate   : 'aggregate' ;


varID       : 'varId';

booleanExpression : 'booleanExpression' ;

WS          : [ \t\n\t] -> skip ;
