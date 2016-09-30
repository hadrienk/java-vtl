grammar VTL;

options{
    language = Java;
}

import Atoms, Clauses;

start : statement+ EOF;

/* Assignment */
statement : variableRef ':=' datasetExpression;

exprMember : datasetExpression ('#' componentID)? ;

/* Expressions */
datasetExpression : <assoc=right>datasetExpression clause #withClause
           | getExpression                                #withGet
           | putExpression                                #withPut
           | exprAtom                                     #withAtom
           ;

componentID : IDENTIFIER;

getExpression : 'get(todo)';
putExpression : 'put(todo)';

WS : [ \r\t\u000C] -> skip ;
