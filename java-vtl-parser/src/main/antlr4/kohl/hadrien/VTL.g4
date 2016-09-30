grammar VTL;

import Atoms, Clauses, Conditional;

start : statement+ EOF;

/* Assignment */
statement : variableRef ':=' datasetExpression;

exprMember : datasetExpression ('#' componentID)? ;

/* Expressions */
datasetExpression : <assoc=right>datasetExpression clauseExpression #withClause
           | getExpression                                #withGet
           | putExpression                                #withPut
           | exprAtom                                     #withAtom
           ;

componentID : IDENTIFIER;

getExpression : 'get(todo)';
putExpression : 'put(todo)';

WS : [ \r\t\u000C] -> skip ;
