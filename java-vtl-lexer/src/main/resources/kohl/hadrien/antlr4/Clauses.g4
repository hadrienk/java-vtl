grammar clauses;

clause      : ( '[' ( rename | filter | keep | calc | attrcalc | aggregate ) ']' )+ ;

// [ rename component as string,
//          component as string role = IDENTIFIER,
//          component as string role = MEASURE,
//          component as string role = ATTRIBUTE
// ]

rename      : 'rename' renameParam ;
renameParam : renameParam ( ',' renameParam )+
            | component 'as' string role?
            ;

filter      : 'filter' booleanExpression ;

keep        : 'keep' booleanExpression ( ',' booleanExpression )* ;

calc        : 'calc' ;

attrcalc    : 'attrcalc' ;

aggregate   : 'aggregate' ;

role : 'role' '=' ( 'IDENTIFIER' | 'MEASURE' | 'ATTRIBUTE' ) ;


booleanExpression : 'booleanExpression' ;
string      : 'string' ;
component   : 'componentName' ;

WS          : [ \t\n\t] -> skip ;
