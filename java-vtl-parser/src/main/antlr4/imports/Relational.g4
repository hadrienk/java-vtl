grammar Relational;

relationalExpression : unionExpression | joinExpression ;

unionExpression : 'union' '(' datasetExpression (',' datasetExpression )* ')' ;

datasetExpression : 'datasetExpr' NUM+;

joinExpression : '[' (INNER | OUTER | CROSS ) datasetExpression (',' datasetExpression )* ']' joinClause (',' joinClause)* ;
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

INNER : 'inner' ;
OUTER : 'outer' ;
CROSS : 'cross' ;

NUM : '0'..'9' ;

WS          : [ \t\n\t] -> skip ;
