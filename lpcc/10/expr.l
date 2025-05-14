%{
#include "y.tab.h"
#include <stdlib.h>
%}

%%

[0-9]+(\.[0-9]+)?    { yylval.fval = atof(yytext); return NUMBER; }
[\t ]+               ; // Skip whitespace
\n                   { return '\n'; }

"+"                  { return '+'; }
"-"                  { return '-'; }
"*"                  { return '*'; }
"/"                  { return '/'; }
"^"                  { return '^'; }
"("                  { return '('; }
")"                  { return ')'; }

.                    { printf("Invalid character: %s\n", yytext); }

%%
int yywrap() {
    return 1;
}
