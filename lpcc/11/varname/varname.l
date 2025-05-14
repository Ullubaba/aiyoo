%{
#include "y.tab.h"
#include <string.h>
%}

%%

[a-zA-Z_][a-zA-Z0-9_]*    { yylval.str = strdup(yytext); return VARIABLE; }
[ \t]+                   ;   // Ignore whitespace
\n                       { return '\n'; }
.                        { return INVALID; }

%%

int yywrap() {
    return 1;
}
