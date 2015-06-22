%{
  import java.io.*;
%}

%token NL
%token <sval> NUM

%type <sval> exp

%left '-' '+'
%left '*' '/'
%left NEG
%right '^'

%%

input:
       | input line
       ;

line:    NL      { }
       | exp NL  { postfix = $1; }
       ;

exp:     NUM                { $$ = $1 + " "; }
       | exp '+' exp        { $$ = $1 + $3 + "+ "; }
       | exp '-' exp        { $$ = $1 + $3 + "- "; }
       | exp '*' exp        { $$ = $1 + $3 + "* "; }
       | exp '/' exp        { $$ = $1 + $3 + "/ "; }
       | '-' exp  %prec NEG { $$ = "-" + $2; }
       | exp '^' exp        { $$ = $1 + $3 + "^ "; }
       | '(' exp ')'        { $$ = $2; }
       ;

%%
  public String postfix;
  private Yylex lexer;


  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }


  public void yyerror (String error) {
    System.err.println ("Error: " + error);
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);
  }