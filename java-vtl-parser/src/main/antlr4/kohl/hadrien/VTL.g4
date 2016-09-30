grammar VTL;

options{
    language = Java;
}

import Atoms, Clauses;

start : statement+ EOF;

/* Assignment */
statement : variableRef ':=' exprMember;

exprMember : expression ('#' componentID)? ;

/* Expressions */
expression : <assoc=right>expression clause
           | getExpression
           | putExpression
           | exprAtom
           ;

componentID : IDENTIFIER;

getExpression : 'get(todo)';
putExpression : 'put(todo)';

WS : [ \r\t\u000C] -> skip ;
