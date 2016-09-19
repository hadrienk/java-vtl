parser grammar VTL;

options{
    language = Java;
    tokenVocab = VTLLexer;
}

start
  :
  statement* EOF
  ;

/* Assignment */

statement
  : variableRef ':=' expr;

/* Conditional */

expr :
  exprOr
  | IF exprOr THEN exprOr (ELSEIF exprOr THEN exprOr)* (ELSE exprOr)*
  ;

exprOr
  : exprAnd ( OR exprAnd | XOR exprAnd )*
  ;

/* Logical AND */

exprAnd
  :
  exprEq (AND exprEq)*
  ;

/* Equality, inequality */

exprEq
  :
  exprExists ( ( '=' | '<>' | '<='| '>=' ) exprExists )*
  ;

/* Matching */

exprExists
  :
  exprComp (
    ('exists_in' exprComp) |
    ('exists_in_all' exprComp) |
    ('not_exists_in' exprComp) |
    ('not_exists_in_all' exprComp )
  )*
  ;

/* Comparison, range */

exprComp
  :
  exprAdd (
    IN setExpr |
    NOT IN setExpr |
    ( '>' | '<' ) exprAdd  |
    BETWEEN exprAdd AND exprAdd |
    'not' 'between' exprAdd 'and' exprAdd )*
  ;

/* Addition, subtraction */

exprAdd
  :
  exprMultiply (
    '++' exprMultiply |
    '--' exprMultiply |
    '+'  exprMultiply |
    '-'  exprMultiply
  )*
  ;

/* Multiplication, division */

exprMultiply
  :
  exprFactor
  (
    (
        ('**' exprFactor)
      |
        ('//' exprFactor)
      |
        ('*' exprFactor)
      |
        ('/' exprFactor)
      |
        ('%' exprFactor)
    )
  )*
  ;

/* Unary plus, unary minus, not */

exprFactor
  :
  (exprMember)
  | ('+' exprMember)
  | ('-' exprMember)
  | 'not' exprMember
  ;

/* Membership and clauses */

exprMember
  :
  exprAtom ('[' datasetClause ']')?('#' componentID)?
  ;

/* Functions */

exprAtom
  : 'round' '(' expr ',' INTEGER_CONSTANT ')'
  | 'min' '(' expr ')'
  | 'max' '(' expr ')'
  | 'abs' '(' expr ')'
  | 'exp' '(' expr ')'
  | 'ln' '(' expr ')'
  | 'log' '(' expr ',' logBase ')'
  | 'trunc' '(' expr ',' INTEGER_CONSTANT ')'
  | 'power' '(' expr ',' powerExponent ')'
  | 'nroot' '(' expr ',' INTEGER_CONSTANT ')'
  | 'length' '(' expr ')'
  | 'trim' '(' expr ')'
  | 'upper' '(' expr ')'
  | 'lower' '(' expr ')'
  | 'substr' '(' expr ',' scalarExpr (',' scalarExpr)? ')'
  | 'indexof' '(' expr ',' STRING_CONSTANT ')'
  | 'missing' '(' expr ')'
  | 'match_characters' '(' expr ',' STRING_CONSTANT (',' 'all')? ')'
  | 'match_values' '(' expr ',' setExpr (',' 'all')? ')'
  | 'charlength' '(' expr ')'
  | 'type' '(' expr ')' '=' STRING_CONSTANT
  | 'intersect' '(' expr ',' expr ')'
  | 'union' '(' expr ',' expr ')'
  | 'symdiff' '(' expr ',' expr ')'
  | 'setdiff' '(' expr ',' expr ')'
  | 'isnull' '(' expr ')'
  | 'nvl' '(' expr ',' constant ')'
  | 'mod' '(' expr ',' expr ')'
  | validationExpr
  | getExpr
  | variableRef
  | putExpr
  | evalExpr
  | mergeExpr
  | hierarchyExpr
  ;

/* Parentheses */

variableRef
  : '(' exprOr ')' | varID | constant;

/* get */

getExpr
  :
  'get' '(' persistentDatasetID (',' persistentDatasetID)* (',' keepClause)? (',' filterClause)? (',' aggregategetClause)? ')'
  ;

persistentDatasetID
  :
  STRING_CONSTANT
  ;

putExpr
  :
  'put' '(' expr ',' persistentDatasetID ')'
  ;

evalExpr
  :
  'eval' '(' STRING_CONSTANT (',' variableRef)* ',' persistentDatasetID ')'
  ;

validationExpr
  :
  'check' '(' exprOr (',' 'imbalance' '(' exprOr ')')? (',' 'erlevel' '(' exprOr ')')? (',' 'errorcode' '(' constant ')')? (',' 'threshold' '(' constant ')')? (',' 'all')? ')'
  ;

mergeExpr
  :
  'merge' '(' expr 'as'? STRING_CONSTANT (',' expr 'as'? STRING_CONSTANT)+ ',' 'on' '(' expr ')' ',' 'return' '(' (expr 'as'? STRING_CONSTANT) (',' expr 'as'? STRING_CONSTANT)+ ')' ')'
  ;

hierarchyExpr
  :
  'hierarchy' '(' expr ',' IDENTIFIER ','
  (
    STRING_CONSTANT
    | (mappingExpr (',' mappingExpr)* 'as' STRING_CONSTANT)
  )
  ',' BOOLEAN_CONSTANT (',' aggrParam)? ')'
  ;

mappingExpr
  :
  '(' constant ',' INTEGER_CONSTANT ','
  (
    '+'
    | '-'
  )
  ')' 'to' constant
  ;

aggrParam
  :
  'sum'
  | 'prod'
  ;

aggregategetClause
  :
  'aggregate' '(' aggrFunction '(' scalarExpr ')' (',' aggrFunction '(' scalarExpr ')')* ')'
  ;

aggregateClause
  :
  aggrFunctionClause (',' aggrFunctionClause)*
  ;

aggrFunctionClause
  :
  aggrFunction '(' scalarExpr ')'
  | percentileFunction
  ;

datasetIDGroup
  :
  varID (',' varID)*
  ;

caseElseClause
  :
  (
    (',' ELSE exprAdd)
  )
  ;

caseCaseClause
  :
  (
    (exprOr ',' exprOr (',' exprOr ',' exprOr)*)
  )
  ;

getFiltersClause
  :
  (
    getFilterClause (',' getFilterClause)*
  )
  ;

getFilterClause
  :
  (
    ('filter'? bScalarExpr)
  )
  ;

datasetClause
  :
  ('rename' renameClause)
  | aggrFilterClause
  | (calcClause)
  | (attrCalcClause)
  | (keepClause)
  | (dropClause)
  | (compareClause)
  ;

aggrFilterClause
  :
  |
  (
    filterClause (',' keepClause ',' 'aggregate' aggregateClause)?
  )
  |
  (
    (
      keepClause
      | dropClause
    )
    ',' 'aggregate' aggregateClause
  )
  ;

filterClause
  :
  (
    ('filter'? bScalarExpr)
  )
  ;

ascdescClause
  : 'asc'
  | 'desc'
  ;

renameClause
  :
  (
    varID 'as' varID ('role' varRole)? (',' varID 'as' varID ('role' varRole)?)*
  )
  ;

aggrFunction
  :
  'sum'
  | 'avg'
  | 'min'
  | 'max'
  | 'std'
  | 'count'
  | 'count_distinct'
  | 'median'
  ;

percentileFunction
  :
  ('percentile' '(' scalarExpr ',' constant ')')
  ;

calcClause
  :
  (
    ('calc') calcClauseItem (',' calcClauseItem)*
  )
  ;

attrCalcClause
  :
  'attrcalc' scalarExpr 'as' STRING_CONSTANT ('viral')? (',' IDENTIFIER 'as' STRING_CONSTANT ('viral')?)*
  ;

calcClauseItem
  :
  (
    calcExpr 'as' STRING_CONSTANT ('role' varRole ('viral')?)?
  )
  ;

calcExpr
  :
  (aggrFunction '(' scalarExpr ')')
  | scalarExpr
  ;

dropClause
  :
  'drop' '(' dropClauseItem (',' dropClauseItem)* ')'
  ;

dropClauseItem
  :
  (varID)
  ;

keepClause
  :
  'keep' '(' keepClauseItem (',' keepClauseItem)* ')'
  ;

keepClauseItem
  :
  (varID)
  ;

compareClause
  : compOpScalarClause constant
  ;

inBetweenClause
  :
  (
    ('in' setExpr)
  )
  |
  (
    ('between' constant 'and' constant)
  )
  |
  (
    ('not' 'in' setExpr)
  )
  |
  (
    ('not' 'between' constant 'and' constant)
  )
  ;

dimClause
  :
  | compareClause
  | inBetweenClause
  ;

varRole
  :
  ('IDENTIFIER')
  | ('MEASURE')
  | ('ATTRIBUTE')
  ;

bScalarExpr
  :
  (sExprOr)
  (
    (
      ('or' sExprOr)
    )
    |
    (
      ('xor' sExprOr)
    )
  )*
  ;

sExprOr
  :
  (sExprAnd)
  (
    ('and' sExprAnd)
  )*
  ;

sExprAnd
  :
  (
    ('not' sExprPredicate)
  )
  | sExprPredicate
  ;

sExprPredicate
  :
  (scalarExpr)
  (
    (
      (compOpScalar scalarExpr)
    )
    |
    (
      ('in' setExpr)
    )
    |
    (
      ('between' scalarExpr 'and' scalarExpr)
    )
    |
    (
      ('not' 'in' setExpr)
    )
    |
    (
      ('not' 'between' scalarExpr 'and' scalarExpr)
    )
  )?
  ;

scalarExpr
  :
  (sExprAdd)
  (
    (
      ('+' sExprAdd)
    )
    |
    (
      ('-' sExprAdd)
    )
  )*
  ;

sExprAdd
  :
  (sExprFactor)
  (
    (
      ('/' sExprFactor)
    )
    |
    (
      ('*' sExprFactor)
    )
    |
    (
      ('%' sExprFactor)
    )
  )*
  ;

sExprFactor
  :
  (
    (sExprAtom)
    |
    (
      ('+' sExprAtom)
    )
    |
    (
      ('-' sExprAtom)
    )
  )
  ;

sExprAtom
  :
  varID
  | constant
  | '(' bScalarExpr ')'
  | ('length' '(' scalarExpr ')')
  | ('concat' '(' scalarExpr (',' scalarExpr)+ ')')
  | ('trim' '(' scalarExpr ')')
  | ('upper' '(' scalarExpr ')')
  | ('lower' '(' scalarExpr ')')
  | ('substr' '(' b1=scalarExpr ',' b2=scalarExpr ',' b3=scalarExpr ')')
  | ('round' '(' scalarExpr ',' INTEGER_CONSTANT ')')
  | ('trunc' '(' scalarExpr ')')
  | ('ln' '(' scalarExpr ')')
  | ('exp' '(' scalarExpr ')')
  | ('mod' '(' scalarExpr ',' INTEGER_CONSTANT ')')
  | ('abs' '(' scalarExpr ')')
  | ('power' '(' scalarExpr ',' powerExponent ')')
  | ('SYSTIMESTAMP')
  ;

componentID
  :
  IDENTIFIER
  ;

compOpScalarClause
  : '='
  | '<'
  | '>'
  | '<='
  | '>='
  | '<>'
  ;

logBase
  :
  INTEGER_CONSTANT
  ;

powerExponent
  :
  (
    (exponent)
    |
    (
      ('+' exponent)
    )
    |
    (
      ('-' exponent)
    )
  )
  ;

exponent
  : INTEGER_CONSTANT | FLOAT_CONSTANT
  ;

setExpr
  : '(' constant (',' constant)* ')'
  | 'union'     '(' setExpr (',' setExpr)+ ')'
  | 'symdiff'   '(' setExpr  ',' setExpr   ')'
  | 'intersect' '(' setExpr  ',' setExpr   ')'
  ;

varID
  :
  IDENTIFIER
  ;

compOp
  : ('=')
  | ('<')
  | ('>')
  | ('<=')
  | ('>=')
  | ('<>')
  ;

compOpScalar
: ('=')
  | ('<')
  | ('>')
  | ('<=')
  | ('>=')
  | ('<>')
  ;

constant
  : INTEGER_CONSTANT
  | FLOAT_CONSTANT
  | BOOLEAN_CONSTANT
  | STRING_CONSTANT
  | NULL_CONSTANT
  ;

isCompl
  :
  ('Y')
  | ('N')
  ;

lhperc
  :
  (
    PLUS
    | MINUS
  )?
  FLOAT_CONSTANT
  | ('INF')
  |
  (
    PLUS
    | MINUS
  )?
  INTEGER_CONSTANT
  ;


