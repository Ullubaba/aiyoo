%{
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int yylex();
void yyerror(const char *s);
%}

%union {
    char* str;
}

%token <str> VARIABLE
%token INVALID
%type <str> var

%%

input:
    | input line
    ;

line:
    '\n'
    | var '\n'      { printf("✅ Valid variable name: %s\n", $1); free($1); }
    | error '\n'    { yyerrok; } // Discard rest of line on error
    ;

var:
    VARIABLE { $$ = $1; }
    ;

%%

void yyerror(const char *s) {
    printf("❌ Invalid variable name\n");
}

int main() {
    printf("🔍 Enter variable names (one per line):\n");
    return yyparse();
}
/* run:

lex varname.l
yacc -d varname.y
gcc lex.yy.c y.tab.c -o varname -lfl
./varname
*/
