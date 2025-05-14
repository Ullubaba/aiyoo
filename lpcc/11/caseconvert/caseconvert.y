%{
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

int yylex();
void yyerror(const char *s);
%}

%union {
    char* str;
}

%token <str> WORD
%type <str> converted

%%

input:
    | input line
    ;

line:
    '\n'
    | converted '\n' { printf("🔁 Converted: %s\n", $1); free($1); }
    ;

converted:
    WORD {
        // Convert case
        char *original = $1;
        char *result = malloc(strlen(original) + 1);
        for (int i = 0; original[i]; i++) {
            if (islower(original[i]))
                result[i] = toupper(original[i]);
            else if (isupper(original[i]))
                result[i] = tolower(original[i]);
            else
                result[i] = original[i]; // preserve digits/symbols
        }
        result[strlen(original)] = '\0';
        free(original);
        $$ = result;
    }
    ;

%%

void yyerror(const char *s) {
    printf("❌ Error: %s\n", s);
}

int main() {
    printf("🔤 Enter input (one word per line):\n");
    return yyparse();
}

/* run:
lex caseconvert.l
yacc -d caseconvert.y
gcc lex.yy.c y.tab.c -o caseconvert -lfl
./caseconvert
*/
