%{
#include "y.tab.h"
#include <string.h>
#include <math.h>
#include <stdlib.h>

%}

%%
[ \t]+                ;
[0-9]+(\.[0-9]+)?     { yylval.fval = atof(yytext); return NUMBER; }
[a-zA-Z_][a-zA-Z0-9_]* {
    if (strcmp(yytext, "sqrt") == 0) return SQRT;
    if (strcmp(yytext, "sin") == 0) return SIN;
    if (strcmp(yytext, "cos") == 0) return COS;
    if (strcmp(yytext, "pow") == 0) return POW;
    if (strcmp(yytext, "log") == 0) return LOG;
    if (strcmp(yytext, "strlen") == 0) return STRLEN;
    yylval.str = strdup(yytext);
    return ID;
}
\"[^\"]*\"            {
    yylval.str = strdup(yytext + 1); // skip starting "
    yylval.str[strlen(yylval.str) - 1] = '\0'; // remove trailing "
    return STRING;
}
"="                  return '=';
","                  return ',';
"("                  return '(';
")"                  return ')';
"\n"                 return '\n';
.                    return yytext[0];

%%

int yywrap() {
    return 1;
}
