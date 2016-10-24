grammar Relational;

relationalExpression : unionExpression ;

unionExpression : 'union' '(' datasetExpression (',' datasetExpression)+ ')' ;

datasetExpression : 'datasetExpr' NUM+;


NUM : '0'..'9' ;
