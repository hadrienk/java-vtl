grammar VTL;

options{
    language = Java;
}

import Conditional, Clauses, Atoms;

start : statement+ EOF;

/* Assignment */
statement : variableRef ':=' expression;

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
