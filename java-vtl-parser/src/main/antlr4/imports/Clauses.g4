grammar Clauses;

clauseExpression      : '[' clause ']' ;

clause       : rename     #renameClause
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
rename      : 'rename' renameParam (',' renameParam )* ;
renameParam : from=varID 'as' to=varID ( 'role' '=' role )?
            ;

role : ( 'IDENTIFIER' | 'MEASURE' | 'ATTRIBUTE' ) ;

filter      : 'filter' booleanExpression ;

keep        : 'keep' booleanExpression ( ',' booleanExpression )* ;

calc        : 'calc' ;

attrcalc    : 'attrcalc' ;

aggregate   : 'aggregate' ;

booleanExpression : 'booleanExpression' ;

varID       : 'varId';

WS          : [ \t\n\t] -> skip ;
