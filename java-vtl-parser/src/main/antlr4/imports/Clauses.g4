/*-
 * #%L
 * Java VTL
 * %%
 * Copyright (C) 2016 Hadrien Kohl
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
grammar Clauses;

clauseExpression      : '[' clause ']' ;

clause       : 'rename' renameParam (',' renameParam)*     #renameClause
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
renameParam : from=varID 'as' to=varID ( 'role' '=' role )? ;

role : ( 'IDENTIFIER' | 'MEASURE' | 'ATTRIBUTE' ) ;

filter      : 'filter' booleanExpression ;

keep        : 'keep' booleanExpression ( ',' booleanExpression )* ;

calc        : 'calc' ;

attrcalc    : 'attrcalc' ;

aggregate   : 'aggregate' ;

booleanExpression : 'booleanExpression' ;

varID       : 'varId';

WS          : [ \t\n\t] -> skip ;
