grammar Relational;

relationalExpression : unionExpression | joinExpression ;

unionExpression : 'union' '(' datasetExpression (',' datasetExpression )* ')' ;

joinExpression : '[' JOIN_TYPE* joinDefinition ']' joinBody ;
joinDefinition : varID (',' varID )* ( 'on' dimensionExpression (',' dimensionExpression )* )* ;
joinBody : '{' joinClause ( ',' joinClause )* '}' ;

joinClause : joinCalc
           | joinFilter
           | joinKeep
           | joinRename ;
           // | joinDrop
           // | joinUnfold
           // | joinFold ;

joinCalc   : role* variableRef '=' aritmeticExpression ;

aritmeticExpression : aritmeticExpression  ( '*' | '/' ) aritmeticExpression
                     | aritmeticExpression  ( '+' | '-' ) aritmeticExpression
                     | variableRef ;

joinFilter : 'TODO' ;
joinKeep   : 'TODO' ;
joinRename : 'TODO' ;

role : ( 'IDENTIFIER' | 'MEASURE' | 'ATTRIBUTE' ) ;


JOIN_TYPE : INNER | OUTER | CROSS ;
INNER : 'inner' ;
OUTER : 'outer' ;
CROSS : 'cross' ;

// For tests only
varID               : 'varID' NUM+;
variableRef         : ( 'varName' | 'constant' ) NUM+;
datasetExpression   : 'datasetExpr' NUM*;
dimensionExpression : 'dimensionExpr' NUM*;
NUM : '0'..'9' ;

WS          : [ \t\n\t] -> skip ;
