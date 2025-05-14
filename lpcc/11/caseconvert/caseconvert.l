%{
#include "y.tab.h"
#include <string.h>
%}

%%

[A-Za-z0-9]+     { yylval.str = strdup(yytext); return WORD; }
\n               { return '\n'; }
[ \t]+           ;  // Skip spaces
.                { return *yytext; } // return other symbols as-is

%%

int yywrap() {
    return 1;
}
