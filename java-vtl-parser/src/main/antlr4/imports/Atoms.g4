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
grammar Atoms;

/* Atom */
exprAtom : variableRef;

variableRef : varID
            | constant
            ;

varID : IDENTIFIER;

constant : INTEGER_CONSTANT | FLOAT_CONSTANT | BOOLEAN_CONSTANT | STRING_CONSTANT | NULL_CONSTANT;

IDENTIFIER:LETTER(LETTER|'_'|'0'..'9')*;

INTEGER_CONSTANT  :'0'..'9'+;
BOOLEAN_CONSTANT  : 'true' | 'false' ;
STRING_CONSTANT   :'"' (~'"')* '"';
FLOAT_CONSTANT    : ('0'..'9')+ '.' ('0'..'9')* FLOATEXP?
                  | ('0'..'9')+ FLOATEXP
                  ;
NULL_CONSTANT     :'null';

fragment
FLOATEXP : ('e'|'E')(PLUS|MINUS)?('0'..'9')+;

PLUS : '+';
MINUS : '-';

fragment
LETTER : 'A'..'Z' | 'a'..'z';
