grammar VTL;

options{
    language = Java;
}

import Conditional, Clauses, Atoms;

start : statement+ EOF;

/* Assignment */
statement : variableRef ':=' expression;

/* Expressions */
expression : getExpression
           | putExpression
           | exprMember
           ;

/* Membership */
exprMember : exprAtom (clause)?('#' componentID)? ;
componentID : IDENTIFIER;

getExpression : 'get(todo)';
putExpression : 'put(todo)';

WS : [ \r\t\u000C] -> skip ;
