%{
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

void yyerror(const char *s);
int yylex();

typedef struct {
    char *name;
    double value;
} variable;

variable vars[100];
int var_count = 0;

double get_var_value(char *name) {
    for (int i = 0; i < var_count; ++i) {
        if (strcmp(vars[i].name, name) == 0) return vars[i].value;
    }
    printf("⚠️ Undefined variable: %s\n", name);
    return 0;
}

void set_var_value(char *name, double val) {
    for (int i = 0; i < var_count; ++i) {
        if (strcmp(vars[i].name, name) == 0) {
            vars[i].value = val;
            return;
        }
    }
    vars[var_count].name = strdup(name);
    vars[var_count].value = val;
    var_count++;
}

%}

%union {
    double fval;
    char* str;
}

%token <fval> NUMBER
%token <str> ID STRING

%token SQRT SIN COS POW LOG STRLEN

%left '+' '-'
%left '*' '/'
%right UMINUS

%type <fval> expr func

%%

input:
    | input line
    ;

line:
    '\n'
    | ID '=' expr '\n' {
        set_var_value($1, $3);
        printf("✅ %s = %lf\n", $1, $3);
        free($1);
    }
    ;

expr:
    NUMBER               { $$ = $1; }
    | ID                 { $$ = get_var_value($1); free($1); }
    | expr '+' expr      { $$ = $1 + $3; }
    | expr '-' expr      { $$ = $1 - $3; }
    | expr '*' expr      { $$ = $1 * $3; }
    | expr '/' expr      { $$ = $1 / $3; }
    | '-' expr %prec UMINUS { $$ = -$2; }
    | '(' expr ')'       { $$ = $2; }
    | func               { $$ = $1; }
    ;

func:
    SQRT '(' expr ')'        { $$ = sqrt($3); }
    | SIN '(' expr ')'       { $$ = sin($3 * M_PI / 180); }
    | COS '(' expr ')'       { $$ = cos($3 * M_PI / 180); }
    | LOG '(' expr ')'       { $$ = log($3); }
    | POW '(' expr ',' expr ')' { $$ = pow($3, $5); }
    | STRLEN '(' STRING ')'  { $$ = strlen($3); free($3); }
    ;

%%

void yyerror(const char *s) {
    printf("❌ Error: %s\n", s);
}

int main() {
    printf("🧮 Enter expressions (e.g. x = sqrt(9), y = strlen(\"abc\")):\n");
    return yyparse();
}

/* run:
lex func_eval.l
yacc -d func_eval.y
gcc y.tab.c lex.yy.c -lm -o func_eval
./func_eval
*/
