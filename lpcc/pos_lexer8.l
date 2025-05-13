%{
#include <stdio.h>
%}

%%
"the"|"a"|"an"                            { printf("%s -> Article\n", yytext); }
"from"|"to"|"in"|"on"|"at"                { printf("%s -> Preposition\n", yytext); }
"and"|"but"|"or"                          { printf("%s -> Conjunction\n", yytext); }
"he"|"she"|"it"|"they"|"we"|"I"|"you"     { printf("%s -> Pronoun\n", yytext); }
"all"|"not"|"always"|"never"              { printf("%s -> Adverb\n", yytext); }
"same"|"strong"|"weak"                    { printf("%s -> Adjective\n", yytext); }
"run"|"arrives"|"dread"|"fight"|"come"|"go" { printf("%s -> Verb\n", yytext); }
"destiny"|"time"|"man"|"power"|"stone"|"universe" { printf("%s -> Noun\n", yytext); }
[.,?!]                                    { /* Skip punctuation */ }
[ \t\n]+                                  { /* Skip whitespace */ }
[A-Za-z]+                                 { printf("%s -> Unknown\n", yytext); }
.                                         { /* Skip any other character */ }
%%

int main(int argc, char **argv) {
    printf("Enter the input:\n");
    yylex();
    return 0;
}

int yywrap() {
    return 1;
}

/*
To compile and run:

lex pos_lexer.l
gcc lex.yy.c -o pos_lexer
./pos_lexer
*/