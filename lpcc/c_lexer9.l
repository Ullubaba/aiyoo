%{
#include <stdio.h>
#include <string.h>
%}

/* Definitions */
digit       [0-9]
letter      [a-zA-Z]
id          {letter}({letter}|{digit})*
number      {digit}+
operator    [\+\-\*/%=]
semicolon   [;]
comma       [,]
brace_open  [{]
brace_close [}]
parenthesis_open  [(]
parenthesis_close [)]
quote       [\"]
string      \"([^\"\\]|\\.)*\"

%%

"int"                       { printf("Keyword: %s\n", yytext); }
"char"                      { printf("Keyword: %s\n", yytext); }
"float"                     { printf("Keyword: %s\n", yytext); }
"printf"                    { printf("Function: %s\n", yytext); }

{number}                    { printf("Number: %s\n", yytext); }
{id}                        { printf("Identifier: %s\n", yytext); }
{operator}                  { printf("Operator: %s\n", yytext); }
{semicolon}                 { printf("Semicolon: %s\n", yytext); }
{comma}                     { printf("Comma: %s\n", yytext); }
{brace_open}                { printf("Left Brace: %s\n", yytext); }
{brace_close}               { printf("Right Brace: %s\n", yytext); }
{parenthesis_open}          { printf("Left Parenthesis: %s\n", yytext); }
{parenthesis_close}         { printf("Right Parenthesis: %s\n", yytext); }
{quote}                     { printf("Quote: %s\n", yytext); }
{string}                    { printf("String Literal: %s\n", yytext); }

[ \t\n]+                    { /* Skip whitespace */ }

.                           { printf("Unknown character: %s\n", yytext); }

%%

int main(int argc, char **argv)
{
    printf("Enter the code:\n");
    yylex();
    return 0;
}

int yywrap() {
    return 1;
}

/*
To compile and run:

lex c_lexer.l
gcc lex.yy.c -o c_lexer
./c_lexer
*/