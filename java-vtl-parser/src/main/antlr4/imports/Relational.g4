grammar Relational;

relationalExpression : unionExpression | joinExpression ;

unionExpression : 'union' '(' datasetExpression (',' datasetExpression )* ')' ;

joinExpression : '[' JOIN_TYPE? joinDefinition ']' joinBody ;
joinDefinition : datasetExpression (',' datasetExpression )* 'on' dimensionExpression (',' dimensionExpression )* ;
joinBody : '{' joinClause ( ',' joinClause )* '}' ;

joinClause : joinCalc
           | joinFilter
           | joinKeep
           | joinRename ;
           // | joinDrop
           // | joinUnfold
           // | joinFold ;

joinCalc   : 'TODO' ;
joinFilter : 'TODO' ;
joinKeep   : 'TODO' ;
joinRename : 'TODO' ;

JOIN_TYPE : INNER | OUTER | CROSS ;
INNER : 'inner' ;
OUTER : 'outer' ;
CROSS : 'cross' ;

// For tests only
datasetExpression : 'datasetExpr' NUM+;
dimensionExpression : 'dimensionExpr' NUM+;
NUM : '0'..'9' ;
WS          : [ \t\n\t] -> skip ;
