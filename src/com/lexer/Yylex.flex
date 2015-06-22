%%

%byaccj

%{
  private Parser yyparser;

  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }
%}

NUM = [0-9]*\.?[0-9]+[A-z]? | [A-z]

NL  = \n | \r | \r\n

%%

"+" |
"-" |
"*" |
"/" |
"^" |
"(" |
")"    { return yycharat(0); }

{NL}   { return Parser.NL; }

{NUM}  { yyparser.yylval = new ParserVal(yytext());
         return Parser.NUM; }

[ \t]+ { }

\b     { System.err.println("Error, backspace used"); }

[^]    { System.err.println("Error: unexpected char '"+yytext()+"'"); return -1; }