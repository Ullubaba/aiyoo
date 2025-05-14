%{
#include <stdio.h>
#include <math.h>
#include <stdlib.h>

int yylex();
void yyerror(const char *s);
%}

%union {
    float fval;
}

%token <fval> NUMBER
%left '+' '-'
%left '*' '/'
%right '^'
%left UMINUS
%type <fval> expr

%%

input:
    | input line
    ;

line:
    '\n'
    | expr '\n'    { printf("Result = %f\n", $1); }
    ;

expr:
    NUMBER         { $$ = $1; }
    | expr '+' expr  { $$ = $1 + $3; }
    | expr '-' expr  { $$ = $1 - $3; }
    | expr '*' expr  { $$ = $1 * $3; }
    | expr '/' expr  {
                        if ($3 == 0) {
                            yyerror("Division by zero");
                            $$ = 0;
                        } else {
                            $$ = $1 / $3;
                        }
                     }
    | expr '^' expr  { $$ = pow($1, $3); }
    | '-' expr %prec UMINUS  { $$ = -$2; }
    | '(' expr ')'   { $$ = $2; }
    ;

%%

void yyerror(const char *s) {
    fprintf(stderr, "Error: %s\n", s);
}

int main() {
    printf("Enter an arithmetic expression (decimals, ^ for power, brackets supported):\n");
    yyparse();
    return 0;
}
/* run it

lex expr.l
yacc -d expr.y
gcc lex.yy.c y.tab.c -lm -o expr
./expr
*/
