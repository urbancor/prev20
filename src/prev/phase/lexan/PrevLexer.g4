lexer grammar PrevLexer;

@header {
	package prev.phase.lexan;
	import prev.common.report.*;
}

@members {
    @Override	
	public LexAn.PrevToken nextToken() {
		return (LexAn.PrevToken) super.nextToken();
	}
}

COMMENT : ('#')+ ~('\n')* -> skip ;
WS : ('\n' | '\r' | ' ')+ -> skip ;
TAB : '\t' {setCharPositionInLine((getCharPositionInLine()/8 +1)*8);} -> skip;
NONE : 'none' ;
TRUE : 'true' ;
FALSE : 'false' ;
INTCONST : ('0'..'9')+ ;
CHARACTERCONST : '\'' (' '..'&' | '('..'~' | '\\\'') '\'' ; 

STRINGCONST: '"' (' ' | '!' | '#'..'~' | '\\"')* '"';

// STRING : '"' ~('"')* ('\\"')* ~('"')*'"';
POINTER : 'nil';
LEFTBRAC : '(' ;
RIGHTBRAC : ')' ;
LEFTCURL : '{' ;
RIGHTCURL : '}' ;
LEFTSQR : '[' ;
RIGHTSQR : ']' ;
DOT : '.' ;
COMMA : ',' ;
COL : ':' ;
SEMCOL : ';' ;
AND : '&' ;
OR : '|' ;
EXCL : '!' ;
EQL : '==' ;
NEQ : '!=' ;
LT : '<' ;
GT : '>' ;
LTE : '<=' ;
GTE : '>=' ;
STAR : '*' ;
SLASH : '/' ;
MOD : '%' ;
PLUS : '+' ;
MINUS: '-' ;
CARET : '^' ;
ASIGN: '=';
BOOLEAN : 'boolean' ;
CHAR : 'char' ;
DEL : 'del' ;
DO : 'do' ;
ELSE : 'else' ;
FUNCTION : 'fun' ;
IF : 'if' ;
INTEGER : 'integer' ;
NEW : 'new' ;
THEN : 'then' ;
TYPE : 'typ' ;
VARIABLE : 'var' ;
VOID : 'void' ;
WHERE : 'where' ;
WHILE : 'while' ;
IDENTIFIER : (('A'..'Z') | ('a'..'z') | ('_') )(('A'..'Z') | ('a'..'z') | ('_') | ('0'..'9'))* ;

ERROR : . { if(true) throw new Report.Error(new Location(getLine(),getCharPositionInLine(), getLine(),getCharPositionInLine()+getText().length()), "Napaka v "+ getLine() +". vrstici, "+ getCharPositionInLine() + ". znak.");};