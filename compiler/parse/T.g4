//
// an ANTLR parser specification for T
//

grammar T;

@header {
  import tc.compiler.Location;
  import tc.compiler.tree.*;
  import static tc.compiler.parse.TreeBuilder.*;
  import java.util.List;
  import java.util.ArrayList;
  import tc.compiler.tree.type.*;
  import java.lang.Integer;
  import java.lang.String;
}

@parser::members {
  // grab location info (filename/line/column) from token
  // in order to stick into AST nodes for later error reporting
  public Location loc(final Token token)
  {
    return new Location(getSourceName(), token.getLine(),
      token.getCharPositionInLine());
  }

  // root of AST is stored here
  // set by the action for the start symbol
  private CompilationUnit semanticValue;
  public CompilationUnit getSemanticValue()
  {
    return semanticValue;
  }
}

// grammar proper

compilationUnit
  : mfd=mainFunctionDeclaration EOF
    { semanticValue = buildCompilationUnit(loc($start), $mfd.lval,
        new ArrayList<ClassDeclaration>()); }
  | mfd=mainFunctionDeclaration cd=classDeclarations EOF
    { semanticValue = buildCompilationUnit(loc($start), $mfd.lval,
        $cd.lval); }
  | cd=classDeclarations mfd=mainFunctionDeclaration EOF
    { semanticValue = buildCompilationUnit(loc($start), $mfd.lval,
        $cd.lval); }
  | cd=classDeclarations mfd=mainFunctionDeclaration cd1=classDeclarations EOF
    { $cd.lval.addAll($cd1.lval); semanticValue = buildCompilationUnit(loc($start), $mfd.lval,
        $cd.lval); }
  ;

mainFunctionDeclaration
  returns [ List<Statement> lval ]
  : INT MAIN LPAREN RPAREN mfb=mainFunctionBody
    { $lval = $mfb.lval; }
  ;

mainFunctionBody
  returns [ List<Statement> lval ]
  : mb=mainBlock
    { $lval = $mb.lval; }
  ;

mainBlock
  returns [ List<Statement> lval ]
  : LBRACK mbs=mainBlockStatements RBRACK
    { $lval = $mbs.lval; }
  ;

mainBlockStatements
  returns [ List<Statement> lval ]
  : mbss=mainBlockStatements mbs=mainBlockStatement
    { $mbss.lval.add($mbs.lval); $lval = $mbss.lval; }
  | mbs=mainBlockStatement
    { $lval = new ArrayList<Statement>(); $lval.add($mbs.lval); }
  ;

mainBlockStatement
  returns [ Statement lval ]
  : bs=blockStatement
    { $lval = $bs.lval; }
  ;

classDeclarations
  returns [ ArrayList<ClassDeclaration> lval ]
  : cds=classDeclarations cd=classDeclaration
    { $cds.lval.add($cd.lval); $lval = $cds.lval; }
  | cd1=classDeclaration
    { $lval = new ArrayList<ClassDeclaration>(); $lval.add($cd1.lval); }
  ;

classDeclaration
  returns [ ClassDeclaration lval ]
  : CLASS i=identifier cb=classBody
    { $lval = buildClassDeclaration(loc($start), $i.lval, null, $cb.lval); }
  | CLASS i=identifier EXTENDS s=identifier cb=classBody
    { $lval = buildClassDeclaration(loc($start), $i.lval, $s.lval, $cb.lval); }
  ;

classBody
  returns [  ArrayList<ClassBodyDeclaration> lval ]
  : LBRACK cbd=classBodyDeclarations RBRACK
    { $lval = $cbd.lval; }
  | LBRACK RBRACK
    { $lval = new ArrayList<ClassBodyDeclaration>(); }
  ;

classBodyDeclarations
  returns [ ArrayList<ClassBodyDeclaration> lval ]
  : cbds=classBodyDeclarations cbd=classBodyDeclaration
    { $cbds.lval.add($cbd.lval); $lval = $cbds.lval; }
  | cbd1=classBodyDeclaration
    { $lval = new ArrayList<ClassBodyDeclaration>(); 
      if ($cbd1.lval !=null) { $lval.add($cbd1.lval); } }
  ;

classBodyDeclaration
  returns [ ClassBodyDeclaration lval ]
  : fd=fieldDeclaration
    { $lval = $fd.lval; }
  | md=methodDeclaration
    { $lval = $md.lval; }
  | SEMICOLON
    { $lval = null; }
  ;

fieldDeclaration
  returns [ FieldDeclaration lval ]
  : at=arrayType d1=declarations SEMICOLON
    { $lval = buildFieldDeclaration(loc($start), $at.lval, $at.dval, $d1.lval, $d1.ivals);}
  | INT d=declarations SEMICOLON
    { $lval = buildFieldDeclaration(loc($start), "int", 0, $d.lval, $d.ivals); }
  | i=identifier d=declarations SEMICOLON
    { $lval = buildFieldDeclaration(loc($start), $i.lval.getName(), 0, $d.lval, $d.ivals); }
  ;

methodDeclaration
  returns [ MethodDeclaration lval ]
  : at=arrayType md=methodDeclarator b=block
    { $lval = buildMethodDeclaration(loc($start), $at.lval, $at.dval + $md.dims, $md.name, $md.params, $b.lval); }
  | INT md=methodDeclarator b=block
    { $lval = buildMethodDeclaration(loc($start), "int", $md.dims, $md.name, $md.params, $b.lval); }
  | i=identifier md=methodDeclarator b=block
    { $lval = buildMethodDeclaration(loc($start), $i.lval.getName(), $md.dims, $md.name, $md.params, $b.lval); }
  ;

methodDeclarator
  returns [ String name, ArrayList<NameTypeDepth> params, int dims ]
  : i=identifier fp=formalParameters
    { $name = $i.lval.getName(); $params = $fp.lval; $dims = 0;}
  | i=identifier fp=formalParameters d=dimensions 
    { $name = $i.lval.getName(); $params = $fp.lval; $dims = $d.lval; }
  ;

constructorDeclaration
  returns [ ConstructorDeclaration lval ]
  : i=identifier fp=formalParameters cb=constructorBody
    { $lval = buildConstructorDeclaration(loc($start),$i.lval.getName(), $fp.lval, $cd.lval); }
  ;

constructorBody
  returns [ List<Statement> lval ]
  : LBRACK ci=constructorInvocation bs=blockStatements RBRACK
    { $lval = new ArrayList<Statement>(); $lval.add($ci.lval); $lval.addAll($bs.lval); }
  | LBRACK ci=constructorInvocation RBRACK
    { $lval = new ArrayList<Statement>(); $lval.add($ci.lval); }
  | b=block
    { $lval = $b.lval.getStatementList(); }
  ;

constructorInvocation
  returns [ ConstructorInvocation lval ]
  : THIS a=arguments SEMICOLON
    { $lval = buildConstructorInvocation(loc($start), false, $a.lval); }
  | SUPER a=arguments SEMICOLON
    { $lval = buildConstructorInvocation(loc($start), true, $a.lval); }
  ;

formalParameters
  returns [ ArrayList<NameTypeDepth> lval ]
  : LPAREN pl=parameterList RPAREN
    { $lval = $pl.lval; }
  | LPAREN RPAREN
    { $lval = new ArrayList<NameTypeDepth>(); }
  ;

parameterList
  returns [ ArrayList<NameTypeDepth> lval ]
  : pl=parameterList COMMA p=parameter
    { $pl.lval.add($p.lval); $lval = $pl.lval; }
  | p=parameter
    { $lval = new ArrayList<NameTypeDepth>(); $lval.add($p.lval); }
  ;

parameter
  returns [ NameTypeDepth lval ]
  : t=type id=identifier d=dimensions
    { $lval = new NameTypeDepth($id.lval.getName(), $t.lval, $t.dval+$d.lval); }
  | t=type id=identifier
    { $lval = new NameTypeDepth($id.lval.getName(), $t.lval, $t.dval); }
  ;

block
  returns [ Block lval ]
  : LBRACK bs=blockStatements RBRACK
    { $lval = buildBlock(loc($start), $bs.lval); }
  | LBRACK RBRACK
    { $lval = buildBlock(loc($start), new ArrayList<Statement>()); }
  ;

blockStatements
  returns [ List<Statement> lval ]
  : bss=blockStatements bs=blockStatement
    { $bss.lval.add($bs.lval); $lval = $bss.lval; }
  | bs=blockStatement
    { $lval = new ArrayList<Statement>(); $lval.add($bs.lval); }
  ;

blockStatement
  returns [ Statement lval ]
  : s=statement
    { $lval = $s.lval; }
  ;

statement
  returns [ Statement lval ]
  : b=block
    { $lval = $b.lval; }
  | e=emptyStatement
    { $lval = $e.lval; }
  | es=expressionStatement
    { $lval = $es.lval; }
  | its=ifElseStatement
    { $lval = $its.lval; }
  | ws=whileStatement
    { $lval = $ws.lval; }
  | os=outputStatement
    { $lval = $os.lval; }
  | ds=declarationStatement
    { $lval = $ds.lval; }
  | br=breakStatement
    { $lval = $br.lval; }
  | c=continueStatement
    { $lval = $c.lval; }
  ;

breakStatement
  returns [ BreakStatement lval ]
  : BREAK SEMICOLON
    { $lval = buildBreakStatement(loc($start)); }
  ;

continueStatement
  returns [ ContinueStatement lval ]
  : CONTINUE SEMICOLON
    { $lval = buildContinueStatement(loc($start)); }
  ;

ifElseStatement
  returns [ IfElseStatement lval ]
  : IF p=parenExpression s1=statement ELSE s2=statement
    { $lval = buildIfElseStatement(loc($start), $p.lval, $s1.lval, $s2.lval); }
  ;

whileStatement
  returns [ WhileStatement lval ]
  : WHILE p=parenExpression s=statement
    { $lval = buildWhileStatement(loc($start), $p.lval, $s.lval); }
  ;


outputStatement
  returns [ OutputStatement lval ]
  : OUT e=expression SEMICOLON
    { $lval = buildOutputStatement(loc($start), $e.lval); }
  ;

declarationStatement
  returns [ DeclarationStatement lval ]
  : at=arrayType d1=declarations SEMICOLON
    { $lval = buildDeclarationStatement(loc($start), $at.lval, $at.dval, $d1.lval, $d1.ivals);}
  | INT d=declarations SEMICOLON
    { $lval = buildDeclarationStatement(loc($start), "int", 0, $d.lval, $d.ivals); }
  | i=identifier d=declarations SEMICOLON
    { $lval = buildDeclarationStatement(loc($start), $i.lval.getName(), 0, $d.lval, $d.ivals); }
  ;

declarations
  returns [ List<Identifier> lval, List<Integer> ivals ]
  : ds=declarations COMMA d=declaration
    { $ds.lval.add($d.lval); $lval=$ds.lval; $ds.ivals.add(new Integer($d.dval)); $ivals=$ds.ivals; }
  | d=declaration
    { $lval=new ArrayList<Identifier>(); $lval.add($d.lval); $ivals=new ArrayList<Integer>(); $ivals.add(new Integer($d.dval)); }
  ;

declaration 
  returns [ Identifier lval, int dval ]
  : i=identifier ds=dimensions
    { $lval = $i.lval; $dval = $ds.lval; }
  | i=identifier 
    { $lval = $i.lval; $dval = 0; }
  ;

expressionStatement
  returns [ ExpressionStatement lval ]
  : e=expression SEMICOLON
    { $lval = buildExpressionStatement(loc($start), $e.lval); }
  ;

emptyStatement
  returns [ EmptyStatement lval ]
  : SEMICOLON
    { $lval = buildEmptyStatement(loc($start)); }
  ;

expression
  returns [ Expression lval ]
  : ae=assignmentExpression
    { $lval = $ae.lval; }
  ;

assignmentExpression
  returns [ Expression lval ]
  : as=assignment
    { $lval = $as.lval; }
  | ee=equalityExpression
    { $lval = $ee.lval; }
  ;

assignment
  returns [ Assignment lval ]
  : lhs=leftHandSide EQUALS ae=assignmentExpression
    { $lval = buildAssignment(loc($start), $lhs.lval, $ae.lval); }
  ;

leftHandSide
  returns [ LeftSide lval ]
  : i=identifier
    { $i.lval.setLeftSide(true); $lval = $i.lval; }
  | fa=fieldAccess
    { $fa.lval.setLeftSide(true); $lval = $fa.lval; }
  | aa=arrayAccess
    { $aa.lval.setLeftSide(true); $lval = $aa.lval; }
  ;

equalityExpression
  returns [ Expression lval ]
  : ee=equalityExpression EQUALS EQUALS r=relationalExpression
    { $lval = buildBinaryOperator(loc($start), Binop.EQUALITY, $ee.lval, $r.lval); }
  | re=relationalExpression
    { $lval = $re.lval; }
  ;

relationalExpression
  returns [ Expression lval ]
  : re1=relationalExpression LESSTHAN ae1=additiveExpression
    { $lval = buildBinaryOperator(loc($start), Binop.LESSTHAN, $re1.lval, $ae1.lval); }
  | re2=relationalExpression GREATERTHAN ae2=additiveExpression
    { $lval = buildBinaryOperator(loc($start), Binop.GREATERTHAN, $re2.lval, $ae2.lval); }
  | ae=additiveExpression
    { $lval = $ae.lval; }
  ;

additiveExpression
  returns [ Expression lval ]
  : ae=additiveExpression PLUS me=multiplicativeExpression
    { $lval = buildBinaryOperator(loc($start), Binop.ADD, $ae.lval, $me.lval); }
  | ae1=additiveExpression MINUS me1=multiplicativeExpression
    { $lval = buildBinaryOperator(loc($start), Binop.SUBTRACT, $ae1.lval, $me1.lval); }
  | me=multiplicativeExpression
    { $lval = $me.lval; }
  ;

multiplicativeExpression
  returns [ Expression lval ]
  : me=multiplicativeExpression MULTIPLY ue1=unaryExpression
    { $lval = buildBinaryOperator(loc($start), Binop.MULTIPLY, $me.lval, $ue1.lval); }
  | me1=multiplicativeExpression DIVIDE ue2=unaryExpression
    { $lval = buildBinaryOperator(loc($start), Binop.DIVIDE, $me1.lval, $ue2.lval); }
  | ue=unaryExpression
    { $lval = $ue.lval; }
  ;

unaryExpression
  returns [ Expression lval ]
  : MINUS ue=unaryExpression
    { $lval = buildUnaryOperator(loc($start), Unop.NEGATE, $ue.lval); }
  | NOT ne=unaryExpression
    { $lval = buildUnaryOperator(loc($start), Unop.NOT, $ne.lval); }
  | ce=castExpression
    { $lval = $ce.lval; }
  ;

castExpression
  returns [ Expression lval ]
  : pe=parenExpression ce=castExpression
    { $lval = buildCast(loc($start), null, $pe.lval, $ce.lval);}
  | LPAREN a=arrayType RPAREN  ce=castExpression
    { $lval = buildCast(loc($start), new ArrayType($a.lval, $a.dval), null, $ce.lval);}
  | p=primary
    { $lval = $p.lval; }
  ;

primary
  returns [ Expression lval ]
  : ac=arrayCreationExpression
    { $lval = $ac.lval; }
  | i=identifier
    { $lval = $i.lval; }
  | pnna=primaryNoNewArray
    { $lval = $pnna.lval; }
  ;

primaryNoNewArray
  returns [ Expression lval ]
  : pe=parenExpression
    { $lval = $pe.lval; }
  | fa=fieldAccess
    { $lval = $fa.lval; }
  | aa=arrayAccess
    { $lval = $aa.lval; }
  | cice=classInstanceCreationExpression
    { $lval = $cice.lval; }
  | l=literal
    { $lval = $l.lval; }
  ;

parenExpression
  returns [ Expression lval ]
  : LPAREN e=expression RPAREN
    { $lval = $e.lval; }
  ;

literal
  returns [ IntegerLiteral lval ]
  : INTEGER_LITERAL
    { $lval = buildIntegerLiteral(loc($start), $INTEGER_LITERAL.text); }
  ;

identifier
  returns [ Identifier lval ]
  : IDENTIFIER
    { $lval = buildIdentifier(loc($start), $IDENTIFIER.text); }
  ;

arrayCreationExpression
  returns [ ArrayCreationExpression lval ]
  : NEW INT de=dimensionExpression d=dimensions
    { $lval = buildArrayCreationExpression(loc($start), IntegerType.getInstance(), $de.lval, new Integer($d.lval + 1)); }
  | NEW INT de=dimensionExpression
    { $lval = buildArrayCreationExpression(loc($start), IntegerType.getInstance(), $de.lval, new Integer(1)); }
  | NEW i=identifier de=dimensionExpression d=dimensions
    { $lval = buildArrayCreationExpression(loc($start), ClassType.getInstance($i.lval.getName()), $de.lval, new Integer($d.lval + 1)); }
  | NEW i=identifier de=dimensionExpression
    { $lval = buildArrayCreationExpression(loc($start), ClassType.getInstance($i.lval.getName()), $de.lval, new Integer(1)); }
  ;

arrayType
  returns [ String lval, int dval ]
  : INT ds=dimensions
    { $lval = "int"; $dval = $ds.lval; }
  | i=identifier ds=dimensions
    { $lval = $i.lval.getName(); $dval = $ds.lval; }
  ;

dimensionExpression
  returns [ Expression lval ]
  : LSQBRACK e=expression RSQBRACK
    { $lval = $e.lval; }
  ;

dimensions
  returns [ int lval ]
  : ds=dimensions dimension
    { $lval = $ds.lval+1; }
  | d=dimension
    { $lval = $d.lval; }
  ;

dimension
  returns [ int lval ]
  : LSQBRACK RSQBRACK
    { $lval = 1; }
  ;

classInstanceCreationExpression
  returns [ ClassInstanceCreationExpression lval ]
  : NEW i=identifier arg=arguments
    { $lval = buildClassInstanceCreationExpression(loc($start), $i.lval.getName(), $arg.lval); }
  ;

arguments
  returns [ ArrayList<Expression> lval ]
  : LPAREN al=argumentList RPAREN
    { $lval = $al.lval; }
  | LPAREN RPAREN
    { $lval = new ArrayList<Expression>(); }
  ;

argumentList
  returns [ ArrayList<Expression> lval ]
  : al=argumentList COMMA e=expression
    { $al.lval.add($e.lval); $lval = $al.lval; }
  | e=expression
    { $lval = new ArrayList<Expression>(); $lval.add($e.lval); }
  ;

fieldAccess
  returns [FieldAccess lval]
  : id=identifier DOT i=identifier
    { $lval = buildFieldAccess(loc($start), $id.lval, $i.lval); }
  | fa=fieldAccess DOT i=identifier
    { $lval = buildFieldAccess(loc($start), $fa.lval, $i.lval); }
  | aa=arrayAccess DOT i=identifier 
    { $lval = buildFieldAccess(loc($start), $aa.lval, $i.lval); }
  ;

arrayAccess
  returns [ ArrayAccess lval ]
  : i=identifier de1=dimensionExpression
    { $lval = buildArrayAccess(loc($start), $i.lval, $de1.lval); }
  | aa=arrayAccess de2=dimensionExpression
    { $lval = buildArrayAccess(loc($start), $aa.lval, $de2.lval); }
  ;

type
  returns [ String lval, int dval ]
  : at=arrayType
    { $lval = $at.lval; $dval = $at.dval; }
  | INT
    { $lval = "int"; $dval = 0;}
  | IDENTIFIER
    { $lval = $IDENTIFIER.text; $dval = 0; }
  ;

// fragments to support the lexer rules

fragment Digit : [0-9];

fragment Letter : [a-zA-Z_];

fragment SpaceToken : ' ' | '\t' | '\f' | '\r' | '\n';

// T specifies that either CR (\r), LF (\n) or CRLF (\r\n) are line
// terminators. The following pattern will treat CRLF as the CR terminates
// the line, and the LF will just be a SpaceToken. The net result is that
// a comment terminated by a CRLF will be fully discarded.
fragment EndOfLineComment : '//' ( ~[\n\r] )* ('\n' | '\r' | EOF);

// lexer rules
//   keywords must appear before IDENTIFIER

INTEGER_LITERAL : Digit+;

LPAREN : [(];
RPAREN : [)];
LBRACK : [{];
RBRACK : [}];
LSQBRACK : [[];
RSQBRACK : [\]];
SEMICOLON : [;];
PLUS : [+];
MINUS : [-];
COMMA : [,];
EQUALS : [=];
NOT : [!];
MULTIPLY : [*];
DIVIDE : [/];
LESSTHAN : [<];
GREATERTHAN : [>];
DOT : [.];

// keywords start here
INT : 'int';
MAIN : 'main';
OUT : 'out';
IF : 'if';
ELSE : 'else';
WHILE : 'while';
BREAK : 'break';
CONTINUE : 'continue';
NEW : 'new';
CLASS : 'class';
EXTENDS : 'extends';
THIS : 'this';
SUPER : 'super';

IDENTIFIER : Letter (Letter | Digit)*;

// skip whitespace and comments

Discard : SpaceToken+ -> skip;
DiscardComment: EndOfLineComment -> skip;
