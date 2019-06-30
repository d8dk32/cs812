// Generated from T.g4 by ANTLR 4.7.1
package tc.compiler.parse;

  import tc.compiler.Location;
  import tc.compiler.tree.*;
  import static tc.compiler.parse.TreeBuilder.*;
  import java.util.List;
  import java.util.ArrayList;
  import tc.compiler.tree.type.*;
  import java.lang.Integer;
  import java.lang.String;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INTEGER_LITERAL=1, LPAREN=2, RPAREN=3, LBRACK=4, RBRACK=5, LSQBRACK=6, 
		RSQBRACK=7, SEMICOLON=8, PLUS=9, MINUS=10, COMMA=11, EQUALS=12, NOT=13, 
		MULTIPLY=14, DIVIDE=15, LESSTHAN=16, GREATERTHAN=17, DOT=18, INT=19, MAIN=20, 
		OUT=21, IF=22, ELSE=23, WHILE=24, BREAK=25, CONTINUE=26, NEW=27, CLASS=28, 
		EXTENDS=29, IDENTIFIER=30, Discard=31, DiscardComment=32;
	public static final int
		RULE_compilationUnit = 0, RULE_mainFunctionDeclaration = 1, RULE_mainFunctionBody = 2, 
		RULE_mainBlock = 3, RULE_mainBlockStatements = 4, RULE_mainBlockStatement = 5, 
		RULE_classDeclarations = 6, RULE_classDeclaration = 7, RULE_classBody = 8, 
		RULE_classBodyDeclarations = 9, RULE_classBodyDeclaration = 10, RULE_fieldDeclaration = 11, 
		RULE_block = 12, RULE_blockStatements = 13, RULE_blockStatement = 14, 
		RULE_statement = 15, RULE_breakStatement = 16, RULE_continueStatement = 17, 
		RULE_ifElseStatement = 18, RULE_whileStatement = 19, RULE_outputStatement = 20, 
		RULE_declarationStatement = 21, RULE_declarations = 22, RULE_declaration = 23, 
		RULE_expressionStatement = 24, RULE_emptyStatement = 25, RULE_expression = 26, 
		RULE_assignmentExpression = 27, RULE_assignment = 28, RULE_leftHandSide = 29, 
		RULE_equalityExpression = 30, RULE_relationalExpression = 31, RULE_additiveExpression = 32, 
		RULE_multiplicativeExpression = 33, RULE_unaryExpression = 34, RULE_castExpression = 35, 
		RULE_primary = 36, RULE_primaryNoNewArray = 37, RULE_parenExpression = 38, 
		RULE_literal = 39, RULE_identifier = 40, RULE_arrayCreationExpression = 41, 
		RULE_arrayType = 42, RULE_dimensionExpression = 43, RULE_dimensions = 44, 
		RULE_dimension = 45, RULE_fieldAccess = 46, RULE_arrayAccess = 47;
	public static final String[] ruleNames = {
		"compilationUnit", "mainFunctionDeclaration", "mainFunctionBody", "mainBlock", 
		"mainBlockStatements", "mainBlockStatement", "classDeclarations", "classDeclaration", 
		"classBody", "classBodyDeclarations", "classBodyDeclaration", "fieldDeclaration", 
		"block", "blockStatements", "blockStatement", "statement", "breakStatement", 
		"continueStatement", "ifElseStatement", "whileStatement", "outputStatement", 
		"declarationStatement", "declarations", "declaration", "expressionStatement", 
		"emptyStatement", "expression", "assignmentExpression", "assignment", 
		"leftHandSide", "equalityExpression", "relationalExpression", "additiveExpression", 
		"multiplicativeExpression", "unaryExpression", "castExpression", "primary", 
		"primaryNoNewArray", "parenExpression", "literal", "identifier", "arrayCreationExpression", 
		"arrayType", "dimensionExpression", "dimensions", "dimension", "fieldAccess", 
		"arrayAccess"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, "'int'", "'main'", "'out'", 
		"'if'", "'else'", "'while'", "'break'", "'continue'", "'new'", "'class'", 
		"'extends'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "INTEGER_LITERAL", "LPAREN", "RPAREN", "LBRACK", "RBRACK", "LSQBRACK", 
		"RSQBRACK", "SEMICOLON", "PLUS", "MINUS", "COMMA", "EQUALS", "NOT", "MULTIPLY", 
		"DIVIDE", "LESSTHAN", "GREATERTHAN", "DOT", "INT", "MAIN", "OUT", "IF", 
		"ELSE", "WHILE", "BREAK", "CONTINUE", "NEW", "CLASS", "EXTENDS", "IDENTIFIER", 
		"Discard", "DiscardComment"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "T.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public TParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class CompilationUnitContext extends ParserRuleContext {
		public MainFunctionDeclarationContext mfd;
		public TerminalNode EOF() { return getToken(TParser.EOF, 0); }
		public MainFunctionDeclarationContext mainFunctionDeclaration() {
			return getRuleContext(MainFunctionDeclarationContext.class,0);
		}
		public CompilationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compilationUnit; }
	}

	public final CompilationUnitContext compilationUnit() throws RecognitionException {
		CompilationUnitContext _localctx = new CompilationUnitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_compilationUnit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			((CompilationUnitContext)_localctx).mfd = mainFunctionDeclaration();
			setState(97);
			match(EOF);
			 semanticValue = buildCompilationUnit(loc(_localctx.start), ((CompilationUnitContext)_localctx).mfd.lval,
			        new ArrayList<ClassDeclaration>()); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MainFunctionDeclarationContext extends ParserRuleContext {
		public List<Statement> lval;
		public MainFunctionBodyContext mfb;
		public TerminalNode INT() { return getToken(TParser.INT, 0); }
		public TerminalNode MAIN() { return getToken(TParser.MAIN, 0); }
		public TerminalNode LPAREN() { return getToken(TParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(TParser.RPAREN, 0); }
		public MainFunctionBodyContext mainFunctionBody() {
			return getRuleContext(MainFunctionBodyContext.class,0);
		}
		public MainFunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainFunctionDeclaration; }
	}

	public final MainFunctionDeclarationContext mainFunctionDeclaration() throws RecognitionException {
		MainFunctionDeclarationContext _localctx = new MainFunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_mainFunctionDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(INT);
			setState(101);
			match(MAIN);
			setState(102);
			match(LPAREN);
			setState(103);
			match(RPAREN);
			setState(104);
			((MainFunctionDeclarationContext)_localctx).mfb = mainFunctionBody();
			 ((MainFunctionDeclarationContext)_localctx).lval =  ((MainFunctionDeclarationContext)_localctx).mfb.lval; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MainFunctionBodyContext extends ParserRuleContext {
		public List<Statement> lval;
		public MainBlockContext mb;
		public MainBlockContext mainBlock() {
			return getRuleContext(MainBlockContext.class,0);
		}
		public MainFunctionBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainFunctionBody; }
	}

	public final MainFunctionBodyContext mainFunctionBody() throws RecognitionException {
		MainFunctionBodyContext _localctx = new MainFunctionBodyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_mainFunctionBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			((MainFunctionBodyContext)_localctx).mb = mainBlock();
			 ((MainFunctionBodyContext)_localctx).lval =  ((MainFunctionBodyContext)_localctx).mb.lval; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MainBlockContext extends ParserRuleContext {
		public List<Statement> lval;
		public MainBlockStatementsContext mbs;
		public TerminalNode LBRACK() { return getToken(TParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(TParser.RBRACK, 0); }
		public MainBlockStatementsContext mainBlockStatements() {
			return getRuleContext(MainBlockStatementsContext.class,0);
		}
		public MainBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainBlock; }
	}

	public final MainBlockContext mainBlock() throws RecognitionException {
		MainBlockContext _localctx = new MainBlockContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_mainBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(LBRACK);
			setState(111);
			((MainBlockContext)_localctx).mbs = mainBlockStatements(0);
			setState(112);
			match(RBRACK);
			 ((MainBlockContext)_localctx).lval =  ((MainBlockContext)_localctx).mbs.lval; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MainBlockStatementsContext extends ParserRuleContext {
		public List<Statement> lval;
		public MainBlockStatementsContext mbss;
		public MainBlockStatementContext mbs;
		public MainBlockStatementContext mainBlockStatement() {
			return getRuleContext(MainBlockStatementContext.class,0);
		}
		public MainBlockStatementsContext mainBlockStatements() {
			return getRuleContext(MainBlockStatementsContext.class,0);
		}
		public MainBlockStatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainBlockStatements; }
	}

	public final MainBlockStatementsContext mainBlockStatements() throws RecognitionException {
		return mainBlockStatements(0);
	}

	private MainBlockStatementsContext mainBlockStatements(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		MainBlockStatementsContext _localctx = new MainBlockStatementsContext(_ctx, _parentState);
		MainBlockStatementsContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_mainBlockStatements, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(116);
			((MainBlockStatementsContext)_localctx).mbs = mainBlockStatement();
			 ((MainBlockStatementsContext)_localctx).lval =  new ArrayList<Statement>(); _localctx.lval.add(((MainBlockStatementsContext)_localctx).mbs.lval); 
			}
			_ctx.stop = _input.LT(-1);
			setState(125);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new MainBlockStatementsContext(_parentctx, _parentState);
					_localctx.mbss = _prevctx;
					_localctx.mbss = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_mainBlockStatements);
					setState(119);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(120);
					((MainBlockStatementsContext)_localctx).mbs = mainBlockStatement();
					 ((MainBlockStatementsContext)_localctx).mbss.lval.add(((MainBlockStatementsContext)_localctx).mbs.lval); ((MainBlockStatementsContext)_localctx).lval =  ((MainBlockStatementsContext)_localctx).mbss.lval; 
					}
					} 
				}
				setState(127);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class MainBlockStatementContext extends ParserRuleContext {
		public Statement lval;
		public BlockStatementContext bs;
		public BlockStatementContext blockStatement() {
			return getRuleContext(BlockStatementContext.class,0);
		}
		public MainBlockStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainBlockStatement; }
	}

	public final MainBlockStatementContext mainBlockStatement() throws RecognitionException {
		MainBlockStatementContext _localctx = new MainBlockStatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_mainBlockStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			((MainBlockStatementContext)_localctx).bs = blockStatement();
			 ((MainBlockStatementContext)_localctx).lval =  ((MainBlockStatementContext)_localctx).bs.lval; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclarationsContext extends ParserRuleContext {
		public ArrayList<ClassDeclarations> lval;
		public ClassDeclarationsContext cds;
		public ClassDeclarationContext cd1;
		public ClassDeclarationContext cd;
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public ClassDeclarationsContext classDeclarations() {
			return getRuleContext(ClassDeclarationsContext.class,0);
		}
		public ClassDeclarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclarations; }
	}

	public final ClassDeclarationsContext classDeclarations() throws RecognitionException {
		return classDeclarations(0);
	}

	private ClassDeclarationsContext classDeclarations(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ClassDeclarationsContext _localctx = new ClassDeclarationsContext(_ctx, _parentState);
		ClassDeclarationsContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_classDeclarations, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(132);
			((ClassDeclarationsContext)_localctx).cd1 = classDeclaration();
			 ((ClassDeclarationsContext)_localctx).lval =  new ArrayList<ClassDeclaration>(); _localctx.lval.add(((ClassDeclarationsContext)_localctx).cd1.lval); 
			}
			_ctx.stop = _input.LT(-1);
			setState(141);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ClassDeclarationsContext(_parentctx, _parentState);
					_localctx.cds = _prevctx;
					_localctx.cds = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_classDeclarations);
					setState(135);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(136);
					((ClassDeclarationsContext)_localctx).cd = classDeclaration();
					 ((ClassDeclarationsContext)_localctx).cds.lval.add(((ClassDeclarationsContext)_localctx).cd.lval); ((ClassDeclarationsContext)_localctx).lval =  ((ClassDeclarationsContext)_localctx).cds.lval; 
					}
					} 
				}
				setState(143);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ClassDeclarationContext extends ParserRuleContext {
		public ClassDeclaration lval;
		public IdentifierContext i;
		public ClassBodyContext cb;
		public IdentifierContext s;
		public TerminalNode CLASS() { return getToken(TParser.CLASS, 0); }
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public TerminalNode EXTENDS() { return getToken(TParser.EXTENDS, 0); }
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_classDeclaration);
		try {
			setState(156);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(144);
				match(CLASS);
				setState(145);
				((ClassDeclarationContext)_localctx).i = identifier();
				setState(146);
				((ClassDeclarationContext)_localctx).cb = classBody();
				 ((ClassDeclarationContext)_localctx).lval =  buildClassDeclaration(loc(_localctx.start), ((ClassDeclarationContext)_localctx).i.lval, null, ((ClassDeclarationContext)_localctx).cb.lval); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(149);
				match(CLASS);
				setState(150);
				((ClassDeclarationContext)_localctx).i = identifier();
				setState(151);
				match(EXTENDS);
				setState(152);
				((ClassDeclarationContext)_localctx).s = identifier();
				setState(153);
				((ClassDeclarationContext)_localctx).cb = classBody();
				 ((ClassDeclarationContext)_localctx).lval =  buildClassDeclaration(loc(_localctx.start), ((ClassDeclarationContext)_localctx).i.lval, ((ClassDeclarationContext)_localctx).s.lval, ((ClassDeclarationContext)_localctx).cb.lval); 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassBodyContext extends ParserRuleContext {
		public ClassBody lval;
		public ClassBodyDeclarationsContext cbd;
		public TerminalNode LBRACK() { return getToken(TParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(TParser.RBRACK, 0); }
		public ClassBodyDeclarationsContext classBodyDeclarations() {
			return getRuleContext(ClassBodyDeclarationsContext.class,0);
		}
		public ClassBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBody; }
	}

	public final ClassBodyContext classBody() throws RecognitionException {
		ClassBodyContext _localctx = new ClassBodyContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_classBody);
		try {
			setState(166);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(158);
				match(LBRACK);
				setState(159);
				((ClassBodyContext)_localctx).cbd = classBodyDeclarations(0);
				setState(160);
				match(RBRACK);
				 ((ClassBodyContext)_localctx).lval =  buildClassBody(loc(_localctx.start), ((ClassBodyContext)_localctx).cbd.lval); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(163);
				match(LBRACK);
				setState(164);
				match(RBRACK);
				 ((ClassBodyContext)_localctx).lval =  buildClassBody(loc(_localctx.start), new ArrayList<ClassBodyDeclaration>()); 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassBodyDeclarationsContext extends ParserRuleContext {
		public ArrayList<ClassBodyDeclaration> lval;
		public ClassBodyDeclarationsContext cbds;
		public ClassBodyDeclarationContext cbd1;
		public ClassBodyDeclarationContext cbd;
		public ClassBodyDeclarationContext classBodyDeclaration() {
			return getRuleContext(ClassBodyDeclarationContext.class,0);
		}
		public ClassBodyDeclarationsContext classBodyDeclarations() {
			return getRuleContext(ClassBodyDeclarationsContext.class,0);
		}
		public ClassBodyDeclarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBodyDeclarations; }
	}

	public final ClassBodyDeclarationsContext classBodyDeclarations() throws RecognitionException {
		return classBodyDeclarations(0);
	}

	private ClassBodyDeclarationsContext classBodyDeclarations(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ClassBodyDeclarationsContext _localctx = new ClassBodyDeclarationsContext(_ctx, _parentState);
		ClassBodyDeclarationsContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_classBodyDeclarations, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(169);
			((ClassBodyDeclarationsContext)_localctx).cbd1 = classBodyDeclaration();
			 ((ClassBodyDeclarationsContext)_localctx).lval =  new ArrayList<ClassBodyDeclaration>(); 
			      if (((ClassBodyDeclarationsContext)_localctx).cbd1.lval !=null) { _localctx.lval.add(((ClassBodyDeclarationsContext)_localctx).cbd1.lval); } 
			}
			_ctx.stop = _input.LT(-1);
			setState(178);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ClassBodyDeclarationsContext(_parentctx, _parentState);
					_localctx.cbds = _prevctx;
					_localctx.cbds = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_classBodyDeclarations);
					setState(172);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(173);
					((ClassBodyDeclarationsContext)_localctx).cbd = classBodyDeclaration();
					 ((ClassBodyDeclarationsContext)_localctx).cbds.lval.add(((ClassBodyDeclarationsContext)_localctx).cbd.lval); ((ClassBodyDeclarationsContext)_localctx).lval =  ((ClassBodyDeclarationsContext)_localctx).cbds.lval; 
					}
					} 
				}
				setState(180);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ClassBodyDeclarationContext extends ParserRuleContext {
		public ClassBodyDeclaration lval;
		public FieldDeclarationContext fd;
		public FieldDeclarationContext fieldDeclaration() {
			return getRuleContext(FieldDeclarationContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(TParser.SEMICOLON, 0); }
		public ClassBodyDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBodyDeclaration; }
	}

	public final ClassBodyDeclarationContext classBodyDeclaration() throws RecognitionException {
		ClassBodyDeclarationContext _localctx = new ClassBodyDeclarationContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_classBodyDeclaration);
		try {
			setState(186);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(181);
				((ClassBodyDeclarationContext)_localctx).fd = fieldDeclaration();
				 ((ClassBodyDeclarationContext)_localctx).lval =  ((ClassBodyDeclarationContext)_localctx).fd.lval; 
				}
				break;
			case SEMICOLON:
				enterOuterAlt(_localctx, 2);
				{
				setState(184);
				match(SEMICOLON);
				 ((ClassBodyDeclarationContext)_localctx).lval =  null; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldDeclarationContext extends ParserRuleContext {
		public FieldDeclaration lval;
		public ArrayTypeContext at;
		public DeclarationsContext d1;
		public DeclarationsContext d;
		public TerminalNode SEMICOLON() { return getToken(TParser.SEMICOLON, 0); }
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public DeclarationsContext declarations() {
			return getRuleContext(DeclarationsContext.class,0);
		}
		public TerminalNode INT() { return getToken(TParser.INT, 0); }
		public FieldDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldDeclaration; }
	}

	public final FieldDeclarationContext fieldDeclaration() throws RecognitionException {
		FieldDeclarationContext _localctx = new FieldDeclarationContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_fieldDeclaration);
		try {
			setState(198);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(188);
				((FieldDeclarationContext)_localctx).at = arrayType();
				setState(189);
				((FieldDeclarationContext)_localctx).d1 = declarations(0);
				setState(190);
				match(SEMICOLON);
				 ((FieldDeclarationContext)_localctx).lval =  buildFieldDeclaration(loc(_localctx.start), ((FieldDeclarationContext)_localctx).at.lval, ((FieldDeclarationContext)_localctx).at.dval, ((FieldDeclarationContext)_localctx).d1.lval, ((FieldDeclarationContext)_localctx).d1.ivals);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(193);
				match(INT);
				setState(194);
				((FieldDeclarationContext)_localctx).d = declarations(0);
				setState(195);
				match(SEMICOLON);
				 ((FieldDeclarationContext)_localctx).lval =  buildFieldDeclaration(loc(_localctx.start), "int", 0, ((FieldDeclarationContext)_localctx).d.lval, ((FieldDeclarationContext)_localctx).d.ivals); 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public Block lval;
		public BlockStatementsContext bs;
		public TerminalNode LBRACK() { return getToken(TParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(TParser.RBRACK, 0); }
		public BlockStatementsContext blockStatements() {
			return getRuleContext(BlockStatementsContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_block);
		try {
			setState(208);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(200);
				match(LBRACK);
				setState(201);
				((BlockContext)_localctx).bs = blockStatements(0);
				setState(202);
				match(RBRACK);
				 ((BlockContext)_localctx).lval =  buildBlock(loc(_localctx.start), ((BlockContext)_localctx).bs.lval); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(205);
				match(LBRACK);
				setState(206);
				match(RBRACK);
				 ((BlockContext)_localctx).lval =  buildBlock(loc(_localctx.start), new ArrayList<Statement>()); 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockStatementsContext extends ParserRuleContext {
		public List<Statement> lval;
		public BlockStatementsContext bss;
		public BlockStatementContext bs;
		public BlockStatementContext blockStatement() {
			return getRuleContext(BlockStatementContext.class,0);
		}
		public BlockStatementsContext blockStatements() {
			return getRuleContext(BlockStatementsContext.class,0);
		}
		public BlockStatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockStatements; }
	}

	public final BlockStatementsContext blockStatements() throws RecognitionException {
		return blockStatements(0);
	}

	private BlockStatementsContext blockStatements(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BlockStatementsContext _localctx = new BlockStatementsContext(_ctx, _parentState);
		BlockStatementsContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_blockStatements, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(211);
			((BlockStatementsContext)_localctx).bs = blockStatement();
			 ((BlockStatementsContext)_localctx).lval =  new ArrayList<Statement>(); _localctx.lval.add(((BlockStatementsContext)_localctx).bs.lval); 
			}
			_ctx.stop = _input.LT(-1);
			setState(220);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new BlockStatementsContext(_parentctx, _parentState);
					_localctx.bss = _prevctx;
					_localctx.bss = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_blockStatements);
					setState(214);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(215);
					((BlockStatementsContext)_localctx).bs = blockStatement();
					 ((BlockStatementsContext)_localctx).bss.lval.add(((BlockStatementsContext)_localctx).bs.lval); ((BlockStatementsContext)_localctx).lval =  ((BlockStatementsContext)_localctx).bss.lval; 
					}
					} 
				}
				setState(222);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BlockStatementContext extends ParserRuleContext {
		public Statement lval;
		public StatementContext s;
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public BlockStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockStatement; }
	}

	public final BlockStatementContext blockStatement() throws RecognitionException {
		BlockStatementContext _localctx = new BlockStatementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_blockStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			((BlockStatementContext)_localctx).s = statement();
			 ((BlockStatementContext)_localctx).lval =  ((BlockStatementContext)_localctx).s.lval; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public Statement lval;
		public BlockContext b;
		public EmptyStatementContext e;
		public ExpressionStatementContext es;
		public IfElseStatementContext its;
		public WhileStatementContext ws;
		public OutputStatementContext os;
		public DeclarationStatementContext ds;
		public BreakStatementContext br;
		public ContinueStatementContext c;
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public EmptyStatementContext emptyStatement() {
			return getRuleContext(EmptyStatementContext.class,0);
		}
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public IfElseStatementContext ifElseStatement() {
			return getRuleContext(IfElseStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public OutputStatementContext outputStatement() {
			return getRuleContext(OutputStatementContext.class,0);
		}
		public DeclarationStatementContext declarationStatement() {
			return getRuleContext(DeclarationStatementContext.class,0);
		}
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public ContinueStatementContext continueStatement() {
			return getRuleContext(ContinueStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_statement);
		try {
			setState(253);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACK:
				enterOuterAlt(_localctx, 1);
				{
				setState(226);
				((StatementContext)_localctx).b = block();
				 ((StatementContext)_localctx).lval =  ((StatementContext)_localctx).b.lval; 
				}
				break;
			case SEMICOLON:
				enterOuterAlt(_localctx, 2);
				{
				setState(229);
				((StatementContext)_localctx).e = emptyStatement();
				 ((StatementContext)_localctx).lval =  ((StatementContext)_localctx).e.lval; 
				}
				break;
			case INTEGER_LITERAL:
			case LPAREN:
			case MINUS:
			case NOT:
			case NEW:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 3);
				{
				setState(232);
				((StatementContext)_localctx).es = expressionStatement();
				 ((StatementContext)_localctx).lval =  ((StatementContext)_localctx).es.lval; 
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 4);
				{
				setState(235);
				((StatementContext)_localctx).its = ifElseStatement();
				 ((StatementContext)_localctx).lval =  ((StatementContext)_localctx).its.lval; 
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 5);
				{
				setState(238);
				((StatementContext)_localctx).ws = whileStatement();
				 ((StatementContext)_localctx).lval =  ((StatementContext)_localctx).ws.lval; 
				}
				break;
			case OUT:
				enterOuterAlt(_localctx, 6);
				{
				setState(241);
				((StatementContext)_localctx).os = outputStatement();
				 ((StatementContext)_localctx).lval =  ((StatementContext)_localctx).os.lval; 
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 7);
				{
				setState(244);
				((StatementContext)_localctx).ds = declarationStatement();
				 ((StatementContext)_localctx).lval =  ((StatementContext)_localctx).ds.lval; 
				}
				break;
			case BREAK:
				enterOuterAlt(_localctx, 8);
				{
				setState(247);
				((StatementContext)_localctx).br = breakStatement();
				 ((StatementContext)_localctx).lval =  ((StatementContext)_localctx).br.lval; 
				}
				break;
			case CONTINUE:
				enterOuterAlt(_localctx, 9);
				{
				setState(250);
				((StatementContext)_localctx).c = continueStatement();
				 ((StatementContext)_localctx).lval =  ((StatementContext)_localctx).c.lval; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BreakStatementContext extends ParserRuleContext {
		public BreakStatement lval;
		public TerminalNode BREAK() { return getToken(TParser.BREAK, 0); }
		public TerminalNode SEMICOLON() { return getToken(TParser.SEMICOLON, 0); }
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			match(BREAK);
			setState(256);
			match(SEMICOLON);
			 ((BreakStatementContext)_localctx).lval =  buildBreakStatement(loc(_localctx.start)); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContinueStatementContext extends ParserRuleContext {
		public ContinueStatement lval;
		public TerminalNode CONTINUE() { return getToken(TParser.CONTINUE, 0); }
		public TerminalNode SEMICOLON() { return getToken(TParser.SEMICOLON, 0); }
		public ContinueStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStatement; }
	}

	public final ContinueStatementContext continueStatement() throws RecognitionException {
		ContinueStatementContext _localctx = new ContinueStatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_continueStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			match(CONTINUE);
			setState(260);
			match(SEMICOLON);
			 ((ContinueStatementContext)_localctx).lval =  buildContinueStatement(loc(_localctx.start)); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfElseStatementContext extends ParserRuleContext {
		public IfElseStatement lval;
		public ParenExpressionContext p;
		public StatementContext s1;
		public StatementContext s2;
		public TerminalNode IF() { return getToken(TParser.IF, 0); }
		public TerminalNode ELSE() { return getToken(TParser.ELSE, 0); }
		public ParenExpressionContext parenExpression() {
			return getRuleContext(ParenExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public IfElseStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifElseStatement; }
	}

	public final IfElseStatementContext ifElseStatement() throws RecognitionException {
		IfElseStatementContext _localctx = new IfElseStatementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_ifElseStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			match(IF);
			setState(264);
			((IfElseStatementContext)_localctx).p = parenExpression();
			setState(265);
			((IfElseStatementContext)_localctx).s1 = statement();
			setState(266);
			match(ELSE);
			setState(267);
			((IfElseStatementContext)_localctx).s2 = statement();
			 ((IfElseStatementContext)_localctx).lval =  buildIfElseStatement(loc(_localctx.start), ((IfElseStatementContext)_localctx).p.lval, ((IfElseStatementContext)_localctx).s1.lval, ((IfElseStatementContext)_localctx).s2.lval); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileStatementContext extends ParserRuleContext {
		public WhileStatement lval;
		public ParenExpressionContext p;
		public StatementContext s;
		public TerminalNode WHILE() { return getToken(TParser.WHILE, 0); }
		public ParenExpressionContext parenExpression() {
			return getRuleContext(ParenExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			match(WHILE);
			setState(271);
			((WhileStatementContext)_localctx).p = parenExpression();
			setState(272);
			((WhileStatementContext)_localctx).s = statement();
			 ((WhileStatementContext)_localctx).lval =  buildWhileStatement(loc(_localctx.start), ((WhileStatementContext)_localctx).p.lval, ((WhileStatementContext)_localctx).s.lval); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OutputStatementContext extends ParserRuleContext {
		public OutputStatement lval;
		public ExpressionContext e;
		public TerminalNode OUT() { return getToken(TParser.OUT, 0); }
		public TerminalNode SEMICOLON() { return getToken(TParser.SEMICOLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public OutputStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outputStatement; }
	}

	public final OutputStatementContext outputStatement() throws RecognitionException {
		OutputStatementContext _localctx = new OutputStatementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_outputStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			match(OUT);
			setState(276);
			((OutputStatementContext)_localctx).e = expression();
			setState(277);
			match(SEMICOLON);
			 ((OutputStatementContext)_localctx).lval =  buildOutputStatement(loc(_localctx.start), ((OutputStatementContext)_localctx).e.lval); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationStatementContext extends ParserRuleContext {
		public DeclarationStatement lval;
		public ArrayTypeContext at;
		public DeclarationsContext d1;
		public DeclarationsContext d;
		public TerminalNode SEMICOLON() { return getToken(TParser.SEMICOLON, 0); }
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public DeclarationsContext declarations() {
			return getRuleContext(DeclarationsContext.class,0);
		}
		public TerminalNode INT() { return getToken(TParser.INT, 0); }
		public DeclarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarationStatement; }
	}

	public final DeclarationStatementContext declarationStatement() throws RecognitionException {
		DeclarationStatementContext _localctx = new DeclarationStatementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_declarationStatement);
		try {
			setState(290);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(280);
				((DeclarationStatementContext)_localctx).at = arrayType();
				setState(281);
				((DeclarationStatementContext)_localctx).d1 = declarations(0);
				setState(282);
				match(SEMICOLON);
				 ((DeclarationStatementContext)_localctx).lval =  buildDeclarationStatement(loc(_localctx.start), ((DeclarationStatementContext)_localctx).at.lval, ((DeclarationStatementContext)_localctx).at.dval, ((DeclarationStatementContext)_localctx).d1.lval, ((DeclarationStatementContext)_localctx).d1.ivals);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(285);
				match(INT);
				setState(286);
				((DeclarationStatementContext)_localctx).d = declarations(0);
				setState(287);
				match(SEMICOLON);
				 ((DeclarationStatementContext)_localctx).lval =  buildDeclarationStatement(loc(_localctx.start), "int", 0, ((DeclarationStatementContext)_localctx).d.lval, ((DeclarationStatementContext)_localctx).d.ivals); 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationsContext extends ParserRuleContext {
		public List<Identifier> lval;
		public List<Integer> ivals;
		public DeclarationsContext ds;
		public DeclarationContext d;
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(TParser.COMMA, 0); }
		public DeclarationsContext declarations() {
			return getRuleContext(DeclarationsContext.class,0);
		}
		public DeclarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarations; }
	}

	public final DeclarationsContext declarations() throws RecognitionException {
		return declarations(0);
	}

	private DeclarationsContext declarations(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		DeclarationsContext _localctx = new DeclarationsContext(_ctx, _parentState);
		DeclarationsContext _prevctx = _localctx;
		int _startState = 44;
		enterRecursionRule(_localctx, 44, RULE_declarations, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(293);
			((DeclarationsContext)_localctx).d = declaration();
			 ((DeclarationsContext)_localctx).lval = new ArrayList<Identifier>(); _localctx.lval.add(((DeclarationsContext)_localctx).d.lval); ((DeclarationsContext)_localctx).ivals = new ArrayList<Integer>(); _localctx.ivals.add(new Integer(((DeclarationsContext)_localctx).d.dval)); 
			}
			_ctx.stop = _input.LT(-1);
			setState(303);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new DeclarationsContext(_parentctx, _parentState);
					_localctx.ds = _prevctx;
					_localctx.ds = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_declarations);
					setState(296);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(297);
					match(COMMA);
					setState(298);
					((DeclarationsContext)_localctx).d = declaration();
					 ((DeclarationsContext)_localctx).ds.lval.add(((DeclarationsContext)_localctx).d.lval); ((DeclarationsContext)_localctx).lval = ((DeclarationsContext)_localctx).ds.lval; ((DeclarationsContext)_localctx).ds.ivals.add(new Integer(((DeclarationsContext)_localctx).d.dval)); ((DeclarationsContext)_localctx).ivals = ((DeclarationsContext)_localctx).ds.ivals; 
					}
					} 
				}
				setState(305);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public Identifier lval;
		public int dval;
		public IdentifierContext i;
		public DimensionsContext ds;
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public DimensionsContext dimensions() {
			return getRuleContext(DimensionsContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_declaration);
		try {
			setState(313);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(306);
				((DeclarationContext)_localctx).i = identifier();
				setState(307);
				((DeclarationContext)_localctx).ds = dimensions(0);
				 ((DeclarationContext)_localctx).lval =  ((DeclarationContext)_localctx).i.lval; ((DeclarationContext)_localctx).dval =  ((DeclarationContext)_localctx).ds.lval; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(310);
				((DeclarationContext)_localctx).i = identifier();
				 ((DeclarationContext)_localctx).lval =  ((DeclarationContext)_localctx).i.lval; ((DeclarationContext)_localctx).dval =  0; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionStatementContext extends ParserRuleContext {
		public ExpressionStatement lval;
		public ExpressionContext e;
		public TerminalNode SEMICOLON() { return getToken(TParser.SEMICOLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStatement; }
	}

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_expressionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315);
			((ExpressionStatementContext)_localctx).e = expression();
			setState(316);
			match(SEMICOLON);
			 ((ExpressionStatementContext)_localctx).lval =  buildExpressionStatement(loc(_localctx.start), ((ExpressionStatementContext)_localctx).e.lval); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EmptyStatementContext extends ParserRuleContext {
		public EmptyStatement lval;
		public TerminalNode SEMICOLON() { return getToken(TParser.SEMICOLON, 0); }
		public EmptyStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_emptyStatement; }
	}

	public final EmptyStatementContext emptyStatement() throws RecognitionException {
		EmptyStatementContext _localctx = new EmptyStatementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_emptyStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(319);
			match(SEMICOLON);
			 ((EmptyStatementContext)_localctx).lval =  buildEmptyStatement(loc(_localctx.start)); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public Expression lval;
		public AssignmentExpressionContext ae;
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(322);
			((ExpressionContext)_localctx).ae = assignmentExpression();
			 ((ExpressionContext)_localctx).lval =  ((ExpressionContext)_localctx).ae.lval; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentExpressionContext extends ParserRuleContext {
		public Expression lval;
		public AssignmentContext as;
		public EqualityExpressionContext ee;
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public EqualityExpressionContext equalityExpression() {
			return getRuleContext(EqualityExpressionContext.class,0);
		}
		public AssignmentExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentExpression; }
	}

	public final AssignmentExpressionContext assignmentExpression() throws RecognitionException {
		AssignmentExpressionContext _localctx = new AssignmentExpressionContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_assignmentExpression);
		try {
			setState(331);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(325);
				((AssignmentExpressionContext)_localctx).as = assignment();
				 ((AssignmentExpressionContext)_localctx).lval =  ((AssignmentExpressionContext)_localctx).as.lval; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(328);
				((AssignmentExpressionContext)_localctx).ee = equalityExpression(0);
				 ((AssignmentExpressionContext)_localctx).lval =  ((AssignmentExpressionContext)_localctx).ee.lval; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public Assignment lval;
		public LeftHandSideContext lhs;
		public AssignmentExpressionContext ae;
		public TerminalNode EQUALS() { return getToken(TParser.EQUALS, 0); }
		public LeftHandSideContext leftHandSide() {
			return getRuleContext(LeftHandSideContext.class,0);
		}
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333);
			((AssignmentContext)_localctx).lhs = leftHandSide();
			setState(334);
			match(EQUALS);
			setState(335);
			((AssignmentContext)_localctx).ae = assignmentExpression();
			 ((AssignmentContext)_localctx).lval =  buildAssignment(loc(_localctx.start), ((AssignmentContext)_localctx).lhs.lval, ((AssignmentContext)_localctx).ae.lval); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LeftHandSideContext extends ParserRuleContext {
		public LeftSide lval;
		public IdentifierContext i;
		public FieldAccessContext fa;
		public ArrayAccessContext aa;
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FieldAccessContext fieldAccess() {
			return getRuleContext(FieldAccessContext.class,0);
		}
		public ArrayAccessContext arrayAccess() {
			return getRuleContext(ArrayAccessContext.class,0);
		}
		public LeftHandSideContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftHandSide; }
	}

	public final LeftHandSideContext leftHandSide() throws RecognitionException {
		LeftHandSideContext _localctx = new LeftHandSideContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_leftHandSide);
		try {
			setState(347);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(338);
				((LeftHandSideContext)_localctx).i = identifier();
				 ((LeftHandSideContext)_localctx).i.lval.setLeftSide(true); ((LeftHandSideContext)_localctx).lval =  ((LeftHandSideContext)_localctx).i.lval; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(341);
				((LeftHandSideContext)_localctx).fa = fieldAccess(0);
				 ((LeftHandSideContext)_localctx).fa.lval.setLeftSide(true); ((LeftHandSideContext)_localctx).lval =  ((LeftHandSideContext)_localctx).fa.lval; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(344);
				((LeftHandSideContext)_localctx).aa = arrayAccess(0);
				 ((LeftHandSideContext)_localctx).aa.lval.setLeftSide(true); ((LeftHandSideContext)_localctx).lval =  ((LeftHandSideContext)_localctx).aa.lval; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EqualityExpressionContext extends ParserRuleContext {
		public Expression lval;
		public EqualityExpressionContext ee;
		public RelationalExpressionContext re;
		public RelationalExpressionContext r;
		public RelationalExpressionContext relationalExpression() {
			return getRuleContext(RelationalExpressionContext.class,0);
		}
		public List<TerminalNode> EQUALS() { return getTokens(TParser.EQUALS); }
		public TerminalNode EQUALS(int i) {
			return getToken(TParser.EQUALS, i);
		}
		public EqualityExpressionContext equalityExpression() {
			return getRuleContext(EqualityExpressionContext.class,0);
		}
		public EqualityExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalityExpression; }
	}

	public final EqualityExpressionContext equalityExpression() throws RecognitionException {
		return equalityExpression(0);
	}

	private EqualityExpressionContext equalityExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		EqualityExpressionContext _localctx = new EqualityExpressionContext(_ctx, _parentState);
		EqualityExpressionContext _prevctx = _localctx;
		int _startState = 60;
		enterRecursionRule(_localctx, 60, RULE_equalityExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(350);
			((EqualityExpressionContext)_localctx).re = relationalExpression(0);
			 ((EqualityExpressionContext)_localctx).lval =  ((EqualityExpressionContext)_localctx).re.lval; 
			}
			_ctx.stop = _input.LT(-1);
			setState(361);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new EqualityExpressionContext(_parentctx, _parentState);
					_localctx.ee = _prevctx;
					_localctx.ee = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_equalityExpression);
					setState(353);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(354);
					match(EQUALS);
					setState(355);
					match(EQUALS);
					setState(356);
					((EqualityExpressionContext)_localctx).r = relationalExpression(0);
					 ((EqualityExpressionContext)_localctx).lval =  buildBinaryOperator(loc(_localctx.start), Binop.EQUALITY, ((EqualityExpressionContext)_localctx).ee.lval, ((EqualityExpressionContext)_localctx).r.lval); 
					}
					} 
				}
				setState(363);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class RelationalExpressionContext extends ParserRuleContext {
		public Expression lval;
		public RelationalExpressionContext re1;
		public RelationalExpressionContext re2;
		public AdditiveExpressionContext ae;
		public AdditiveExpressionContext ae1;
		public AdditiveExpressionContext ae2;
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public TerminalNode LESSTHAN() { return getToken(TParser.LESSTHAN, 0); }
		public RelationalExpressionContext relationalExpression() {
			return getRuleContext(RelationalExpressionContext.class,0);
		}
		public TerminalNode GREATERTHAN() { return getToken(TParser.GREATERTHAN, 0); }
		public RelationalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalExpression; }
	}

	public final RelationalExpressionContext relationalExpression() throws RecognitionException {
		return relationalExpression(0);
	}

	private RelationalExpressionContext relationalExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		RelationalExpressionContext _localctx = new RelationalExpressionContext(_ctx, _parentState);
		RelationalExpressionContext _prevctx = _localctx;
		int _startState = 62;
		enterRecursionRule(_localctx, 62, RULE_relationalExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(365);
			((RelationalExpressionContext)_localctx).ae = additiveExpression(0);
			 ((RelationalExpressionContext)_localctx).lval =  ((RelationalExpressionContext)_localctx).ae.lval; 
			}
			_ctx.stop = _input.LT(-1);
			setState(380);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(378);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new RelationalExpressionContext(_parentctx, _parentState);
						_localctx.re1 = _prevctx;
						_localctx.re1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_relationalExpression);
						setState(368);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(369);
						match(LESSTHAN);
						setState(370);
						((RelationalExpressionContext)_localctx).ae1 = additiveExpression(0);
						 ((RelationalExpressionContext)_localctx).lval =  buildBinaryOperator(loc(_localctx.start), Binop.LESSTHAN, ((RelationalExpressionContext)_localctx).re1.lval, ((RelationalExpressionContext)_localctx).ae1.lval); 
						}
						break;
					case 2:
						{
						_localctx = new RelationalExpressionContext(_parentctx, _parentState);
						_localctx.re2 = _prevctx;
						_localctx.re2 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_relationalExpression);
						setState(373);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(374);
						match(GREATERTHAN);
						setState(375);
						((RelationalExpressionContext)_localctx).ae2 = additiveExpression(0);
						 ((RelationalExpressionContext)_localctx).lval =  buildBinaryOperator(loc(_localctx.start), Binop.GREATERTHAN, ((RelationalExpressionContext)_localctx).re2.lval, ((RelationalExpressionContext)_localctx).ae2.lval); 
						}
						break;
					}
					} 
				}
				setState(382);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AdditiveExpressionContext extends ParserRuleContext {
		public Expression lval;
		public AdditiveExpressionContext ae;
		public AdditiveExpressionContext ae1;
		public MultiplicativeExpressionContext me;
		public MultiplicativeExpressionContext me1;
		public MultiplicativeExpressionContext multiplicativeExpression() {
			return getRuleContext(MultiplicativeExpressionContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(TParser.PLUS, 0); }
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(TParser.MINUS, 0); }
		public AdditiveExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpression; }
	}

	public final AdditiveExpressionContext additiveExpression() throws RecognitionException {
		return additiveExpression(0);
	}

	private AdditiveExpressionContext additiveExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AdditiveExpressionContext _localctx = new AdditiveExpressionContext(_ctx, _parentState);
		AdditiveExpressionContext _prevctx = _localctx;
		int _startState = 64;
		enterRecursionRule(_localctx, 64, RULE_additiveExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(384);
			((AdditiveExpressionContext)_localctx).me = multiplicativeExpression(0);
			 ((AdditiveExpressionContext)_localctx).lval =  ((AdditiveExpressionContext)_localctx).me.lval; 
			}
			_ctx.stop = _input.LT(-1);
			setState(399);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(397);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
					case 1:
						{
						_localctx = new AdditiveExpressionContext(_parentctx, _parentState);
						_localctx.ae = _prevctx;
						_localctx.ae = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_additiveExpression);
						setState(387);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(388);
						match(PLUS);
						setState(389);
						((AdditiveExpressionContext)_localctx).me = multiplicativeExpression(0);
						 ((AdditiveExpressionContext)_localctx).lval =  buildBinaryOperator(loc(_localctx.start), Binop.ADD, ((AdditiveExpressionContext)_localctx).ae.lval, ((AdditiveExpressionContext)_localctx).me.lval); 
						}
						break;
					case 2:
						{
						_localctx = new AdditiveExpressionContext(_parentctx, _parentState);
						_localctx.ae1 = _prevctx;
						_localctx.ae1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_additiveExpression);
						setState(392);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(393);
						match(MINUS);
						setState(394);
						((AdditiveExpressionContext)_localctx).me1 = multiplicativeExpression(0);
						 ((AdditiveExpressionContext)_localctx).lval =  buildBinaryOperator(loc(_localctx.start), Binop.SUBTRACT, ((AdditiveExpressionContext)_localctx).ae1.lval, ((AdditiveExpressionContext)_localctx).me1.lval); 
						}
						break;
					}
					} 
				}
				setState(401);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class MultiplicativeExpressionContext extends ParserRuleContext {
		public Expression lval;
		public MultiplicativeExpressionContext me;
		public MultiplicativeExpressionContext me1;
		public UnaryExpressionContext ue;
		public UnaryExpressionContext ue1;
		public UnaryExpressionContext ue2;
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public TerminalNode MULTIPLY() { return getToken(TParser.MULTIPLY, 0); }
		public MultiplicativeExpressionContext multiplicativeExpression() {
			return getRuleContext(MultiplicativeExpressionContext.class,0);
		}
		public TerminalNode DIVIDE() { return getToken(TParser.DIVIDE, 0); }
		public MultiplicativeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpression; }
	}

	public final MultiplicativeExpressionContext multiplicativeExpression() throws RecognitionException {
		return multiplicativeExpression(0);
	}

	private MultiplicativeExpressionContext multiplicativeExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		MultiplicativeExpressionContext _localctx = new MultiplicativeExpressionContext(_ctx, _parentState);
		MultiplicativeExpressionContext _prevctx = _localctx;
		int _startState = 66;
		enterRecursionRule(_localctx, 66, RULE_multiplicativeExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(403);
			((MultiplicativeExpressionContext)_localctx).ue = unaryExpression();
			 ((MultiplicativeExpressionContext)_localctx).lval =  ((MultiplicativeExpressionContext)_localctx).ue.lval; 
			}
			_ctx.stop = _input.LT(-1);
			setState(418);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(416);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
					case 1:
						{
						_localctx = new MultiplicativeExpressionContext(_parentctx, _parentState);
						_localctx.me = _prevctx;
						_localctx.me = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_multiplicativeExpression);
						setState(406);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(407);
						match(MULTIPLY);
						setState(408);
						((MultiplicativeExpressionContext)_localctx).ue1 = unaryExpression();
						 ((MultiplicativeExpressionContext)_localctx).lval =  buildBinaryOperator(loc(_localctx.start), Binop.MULTIPLY, ((MultiplicativeExpressionContext)_localctx).me.lval, ((MultiplicativeExpressionContext)_localctx).ue1.lval); 
						}
						break;
					case 2:
						{
						_localctx = new MultiplicativeExpressionContext(_parentctx, _parentState);
						_localctx.me1 = _prevctx;
						_localctx.me1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_multiplicativeExpression);
						setState(411);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(412);
						match(DIVIDE);
						setState(413);
						((MultiplicativeExpressionContext)_localctx).ue2 = unaryExpression();
						 ((MultiplicativeExpressionContext)_localctx).lval =  buildBinaryOperator(loc(_localctx.start), Binop.DIVIDE, ((MultiplicativeExpressionContext)_localctx).me1.lval, ((MultiplicativeExpressionContext)_localctx).ue2.lval); 
						}
						break;
					}
					} 
				}
				setState(420);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class UnaryExpressionContext extends ParserRuleContext {
		public Expression lval;
		public UnaryExpressionContext ue;
		public UnaryExpressionContext ne;
		public CastExpressionContext ce;
		public TerminalNode MINUS() { return getToken(TParser.MINUS, 0); }
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public TerminalNode NOT() { return getToken(TParser.NOT, 0); }
		public CastExpressionContext castExpression() {
			return getRuleContext(CastExpressionContext.class,0);
		}
		public UnaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpression; }
	}

	public final UnaryExpressionContext unaryExpression() throws RecognitionException {
		UnaryExpressionContext _localctx = new UnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_unaryExpression);
		try {
			setState(432);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MINUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(421);
				match(MINUS);
				setState(422);
				((UnaryExpressionContext)_localctx).ue = unaryExpression();
				 ((UnaryExpressionContext)_localctx).lval =  buildUnaryOperator(loc(_localctx.start), Unop.NEGATE, ((UnaryExpressionContext)_localctx).ue.lval); 
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(425);
				match(NOT);
				setState(426);
				((UnaryExpressionContext)_localctx).ne = unaryExpression();
				 ((UnaryExpressionContext)_localctx).lval =  buildUnaryOperator(loc(_localctx.start), Unop.NOT, ((UnaryExpressionContext)_localctx).ne.lval); 
				}
				break;
			case INTEGER_LITERAL:
			case LPAREN:
			case NEW:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 3);
				{
				setState(429);
				((UnaryExpressionContext)_localctx).ce = castExpression();
				 ((UnaryExpressionContext)_localctx).lval =  ((UnaryExpressionContext)_localctx).ce.lval; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CastExpressionContext extends ParserRuleContext {
		public Expression lval;
		public ParenExpressionContext pe;
		public CastExpressionContext ce;
		public ArrayTypeContext a;
		public PrimaryContext p;
		public ParenExpressionContext parenExpression() {
			return getRuleContext(ParenExpressionContext.class,0);
		}
		public CastExpressionContext castExpression() {
			return getRuleContext(CastExpressionContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(TParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(TParser.RPAREN, 0); }
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public CastExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_castExpression; }
	}

	public final CastExpressionContext castExpression() throws RecognitionException {
		CastExpressionContext _localctx = new CastExpressionContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_castExpression);
		try {
			setState(447);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(434);
				((CastExpressionContext)_localctx).pe = parenExpression();
				setState(435);
				((CastExpressionContext)_localctx).ce = castExpression();
				 ((CastExpressionContext)_localctx).lval =  buildCast(loc(_localctx.start), null, ((CastExpressionContext)_localctx).pe.lval, ((CastExpressionContext)_localctx).ce.lval);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(438);
				match(LPAREN);
				setState(439);
				((CastExpressionContext)_localctx).a = arrayType();
				setState(440);
				match(RPAREN);
				setState(441);
				((CastExpressionContext)_localctx).ce = castExpression();
				 ((CastExpressionContext)_localctx).lval =  buildCast(loc(_localctx.start), new ArrayType(((CastExpressionContext)_localctx).a.lval, ((CastExpressionContext)_localctx).a.dval), null, ((CastExpressionContext)_localctx).ce.lval);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(444);
				((CastExpressionContext)_localctx).p = primary();
				 ((CastExpressionContext)_localctx).lval =  ((CastExpressionContext)_localctx).p.lval; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryContext extends ParserRuleContext {
		public Expression lval;
		public ArrayCreationExpressionContext ac;
		public IdentifierContext i;
		public PrimaryNoNewArrayContext pnna;
		public ArrayCreationExpressionContext arrayCreationExpression() {
			return getRuleContext(ArrayCreationExpressionContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public PrimaryNoNewArrayContext primaryNoNewArray() {
			return getRuleContext(PrimaryNoNewArrayContext.class,0);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_primary);
		try {
			setState(458);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(449);
				((PrimaryContext)_localctx).ac = arrayCreationExpression();
				 ((PrimaryContext)_localctx).lval =  ((PrimaryContext)_localctx).ac.lval; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(452);
				((PrimaryContext)_localctx).i = identifier();
				 ((PrimaryContext)_localctx).lval =  ((PrimaryContext)_localctx).i.lval; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(455);
				((PrimaryContext)_localctx).pnna = primaryNoNewArray();
				 ((PrimaryContext)_localctx).lval =  ((PrimaryContext)_localctx).pnna.lval; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryNoNewArrayContext extends ParserRuleContext {
		public Expression lval;
		public ParenExpressionContext pe;
		public FieldAccessContext fa;
		public ArrayAccessContext aa;
		public LiteralContext l;
		public ParenExpressionContext parenExpression() {
			return getRuleContext(ParenExpressionContext.class,0);
		}
		public FieldAccessContext fieldAccess() {
			return getRuleContext(FieldAccessContext.class,0);
		}
		public ArrayAccessContext arrayAccess() {
			return getRuleContext(ArrayAccessContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public PrimaryNoNewArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryNoNewArray; }
	}

	public final PrimaryNoNewArrayContext primaryNoNewArray() throws RecognitionException {
		PrimaryNoNewArrayContext _localctx = new PrimaryNoNewArrayContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_primaryNoNewArray);
		try {
			setState(472);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(460);
				((PrimaryNoNewArrayContext)_localctx).pe = parenExpression();
				 ((PrimaryNoNewArrayContext)_localctx).lval =  ((PrimaryNoNewArrayContext)_localctx).pe.lval; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(463);
				((PrimaryNoNewArrayContext)_localctx).fa = fieldAccess(0);
				 ((PrimaryNoNewArrayContext)_localctx).lval =  ((PrimaryNoNewArrayContext)_localctx).fa.lval; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(466);
				((PrimaryNoNewArrayContext)_localctx).aa = arrayAccess(0);
				 ((PrimaryNoNewArrayContext)_localctx).lval =  ((PrimaryNoNewArrayContext)_localctx).aa.lval; 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(469);
				((PrimaryNoNewArrayContext)_localctx).l = literal();
				 ((PrimaryNoNewArrayContext)_localctx).lval =  ((PrimaryNoNewArrayContext)_localctx).l.lval; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParenExpressionContext extends ParserRuleContext {
		public Expression lval;
		public ExpressionContext e;
		public TerminalNode LPAREN() { return getToken(TParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(TParser.RPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParenExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parenExpression; }
	}

	public final ParenExpressionContext parenExpression() throws RecognitionException {
		ParenExpressionContext _localctx = new ParenExpressionContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_parenExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(474);
			match(LPAREN);
			setState(475);
			((ParenExpressionContext)_localctx).e = expression();
			setState(476);
			match(RPAREN);
			 ((ParenExpressionContext)_localctx).lval =  ((ParenExpressionContext)_localctx).e.lval; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public IntegerLiteral lval;
		public Token INTEGER_LITERAL;
		public TerminalNode INTEGER_LITERAL() { return getToken(TParser.INTEGER_LITERAL, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_literal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(479);
			((LiteralContext)_localctx).INTEGER_LITERAL = match(INTEGER_LITERAL);
			 ((LiteralContext)_localctx).lval =  buildIntegerLiteral(loc(_localctx.start), (((LiteralContext)_localctx).INTEGER_LITERAL!=null?((LiteralContext)_localctx).INTEGER_LITERAL.getText():null)); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierContext extends ParserRuleContext {
		public Identifier lval;
		public Token IDENTIFIER;
		public TerminalNode IDENTIFIER() { return getToken(TParser.IDENTIFIER, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(482);
			((IdentifierContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			 ((IdentifierContext)_localctx).lval =  buildIdentifier(loc(_localctx.start), (((IdentifierContext)_localctx).IDENTIFIER!=null?((IdentifierContext)_localctx).IDENTIFIER.getText():null)); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayCreationExpressionContext extends ParserRuleContext {
		public ArrayCreationExpression lval;
		public DimensionExpressionContext de;
		public DimensionsContext d;
		public TerminalNode NEW() { return getToken(TParser.NEW, 0); }
		public TerminalNode INT() { return getToken(TParser.INT, 0); }
		public DimensionExpressionContext dimensionExpression() {
			return getRuleContext(DimensionExpressionContext.class,0);
		}
		public DimensionsContext dimensions() {
			return getRuleContext(DimensionsContext.class,0);
		}
		public ArrayCreationExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayCreationExpression; }
	}

	public final ArrayCreationExpressionContext arrayCreationExpression() throws RecognitionException {
		ArrayCreationExpressionContext _localctx = new ArrayCreationExpressionContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_arrayCreationExpression);
		try {
			setState(496);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(485);
				match(NEW);
				setState(486);
				match(INT);
				setState(487);
				((ArrayCreationExpressionContext)_localctx).de = dimensionExpression();
				setState(488);
				((ArrayCreationExpressionContext)_localctx).d = dimensions(0);
				 ((ArrayCreationExpressionContext)_localctx).lval =  buildArrayCreationExpression(loc(_localctx.start), IntegerType.getInstance(), ((ArrayCreationExpressionContext)_localctx).de.lval, new Integer(((ArrayCreationExpressionContext)_localctx).d.lval + 1)); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(491);
				match(NEW);
				setState(492);
				match(INT);
				setState(493);
				((ArrayCreationExpressionContext)_localctx).de = dimensionExpression();
				 ((ArrayCreationExpressionContext)_localctx).lval =  buildArrayCreationExpression(loc(_localctx.start), IntegerType.getInstance(), ((ArrayCreationExpressionContext)_localctx).de.lval, new Integer(1)); 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayTypeContext extends ParserRuleContext {
		public String lval;
		public int dval;
		public DimensionsContext ds;
		public TerminalNode INT() { return getToken(TParser.INT, 0); }
		public DimensionsContext dimensions() {
			return getRuleContext(DimensionsContext.class,0);
		}
		public ArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayType; }
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_arrayType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(498);
			match(INT);
			setState(499);
			((ArrayTypeContext)_localctx).ds = dimensions(0);
			 ((ArrayTypeContext)_localctx).lval =  "int"; ((ArrayTypeContext)_localctx).dval =  ((ArrayTypeContext)_localctx).ds.lval; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DimensionExpressionContext extends ParserRuleContext {
		public Expression lval;
		public ExpressionContext e;
		public TerminalNode LSQBRACK() { return getToken(TParser.LSQBRACK, 0); }
		public TerminalNode RSQBRACK() { return getToken(TParser.RSQBRACK, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DimensionExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dimensionExpression; }
	}

	public final DimensionExpressionContext dimensionExpression() throws RecognitionException {
		DimensionExpressionContext _localctx = new DimensionExpressionContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_dimensionExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(502);
			match(LSQBRACK);
			setState(503);
			((DimensionExpressionContext)_localctx).e = expression();
			setState(504);
			match(RSQBRACK);
			 ((DimensionExpressionContext)_localctx).lval =  ((DimensionExpressionContext)_localctx).e.lval; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DimensionsContext extends ParserRuleContext {
		public int lval;
		public DimensionsContext ds;
		public DimensionContext d;
		public DimensionContext dimension() {
			return getRuleContext(DimensionContext.class,0);
		}
		public DimensionsContext dimensions() {
			return getRuleContext(DimensionsContext.class,0);
		}
		public DimensionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dimensions; }
	}

	public final DimensionsContext dimensions() throws RecognitionException {
		return dimensions(0);
	}

	private DimensionsContext dimensions(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		DimensionsContext _localctx = new DimensionsContext(_ctx, _parentState);
		DimensionsContext _prevctx = _localctx;
		int _startState = 88;
		enterRecursionRule(_localctx, 88, RULE_dimensions, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(508);
			((DimensionsContext)_localctx).d = dimension();
			 ((DimensionsContext)_localctx).lval =  ((DimensionsContext)_localctx).d.lval; 
			}
			_ctx.stop = _input.LT(-1);
			setState(517);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new DimensionsContext(_parentctx, _parentState);
					_localctx.ds = _prevctx;
					_localctx.ds = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_dimensions);
					setState(511);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(512);
					dimension();
					 ((DimensionsContext)_localctx).lval =  ((DimensionsContext)_localctx).ds.lval+1; 
					}
					} 
				}
				setState(519);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class DimensionContext extends ParserRuleContext {
		public int lval;
		public TerminalNode LSQBRACK() { return getToken(TParser.LSQBRACK, 0); }
		public TerminalNode RSQBRACK() { return getToken(TParser.RSQBRACK, 0); }
		public DimensionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dimension; }
	}

	public final DimensionContext dimension() throws RecognitionException {
		DimensionContext _localctx = new DimensionContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_dimension);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(520);
			match(LSQBRACK);
			setState(521);
			match(RSQBRACK);
			 ((DimensionContext)_localctx).lval =  1; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldAccessContext extends ParserRuleContext {
		public FieldAccess lval;
		public FieldAccessContext fa;
		public IdentifierContext id;
		public IdentifierContext i;
		public ArrayAccessContext aa;
		public TerminalNode DOT() { return getToken(TParser.DOT, 0); }
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public ArrayAccessContext arrayAccess() {
			return getRuleContext(ArrayAccessContext.class,0);
		}
		public FieldAccessContext fieldAccess() {
			return getRuleContext(FieldAccessContext.class,0);
		}
		public FieldAccessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldAccess; }
	}

	public final FieldAccessContext fieldAccess() throws RecognitionException {
		return fieldAccess(0);
	}

	private FieldAccessContext fieldAccess(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		FieldAccessContext _localctx = new FieldAccessContext(_ctx, _parentState);
		FieldAccessContext _prevctx = _localctx;
		int _startState = 92;
		enterRecursionRule(_localctx, 92, RULE_fieldAccess, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(535);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				{
				setState(525);
				((FieldAccessContext)_localctx).id = identifier();
				setState(526);
				match(DOT);
				setState(527);
				((FieldAccessContext)_localctx).i = identifier();
				 ((FieldAccessContext)_localctx).lval =  buildFieldAccess(loc(_localctx.start), ((FieldAccessContext)_localctx).id.lval, ((FieldAccessContext)_localctx).i.lval); 
				}
				break;
			case 2:
				{
				setState(530);
				((FieldAccessContext)_localctx).aa = arrayAccess(0);
				setState(531);
				match(DOT);
				setState(532);
				((FieldAccessContext)_localctx).i = identifier();
				 ((FieldAccessContext)_localctx).lval =  buildFieldAccess(loc(_localctx.start), ((FieldAccessContext)_localctx).aa.lval, ((FieldAccessContext)_localctx).i.lval); 
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(544);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new FieldAccessContext(_parentctx, _parentState);
					_localctx.fa = _prevctx;
					_localctx.fa = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_fieldAccess);
					setState(537);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(538);
					match(DOT);
					setState(539);
					((FieldAccessContext)_localctx).i = identifier();
					 ((FieldAccessContext)_localctx).lval =  buildFieldAccess(loc(_localctx.start), ((FieldAccessContext)_localctx).fa.lval, ((FieldAccessContext)_localctx).i.lval); 
					}
					} 
				}
				setState(546);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ArrayAccessContext extends ParserRuleContext {
		public ArrayAccess lval;
		public ArrayAccessContext aa;
		public IdentifierContext i;
		public DimensionExpressionContext de1;
		public DimensionExpressionContext de2;
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public DimensionExpressionContext dimensionExpression() {
			return getRuleContext(DimensionExpressionContext.class,0);
		}
		public ArrayAccessContext arrayAccess() {
			return getRuleContext(ArrayAccessContext.class,0);
		}
		public ArrayAccessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayAccess; }
	}

	public final ArrayAccessContext arrayAccess() throws RecognitionException {
		return arrayAccess(0);
	}

	private ArrayAccessContext arrayAccess(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ArrayAccessContext _localctx = new ArrayAccessContext(_ctx, _parentState);
		ArrayAccessContext _prevctx = _localctx;
		int _startState = 94;
		enterRecursionRule(_localctx, 94, RULE_arrayAccess, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(548);
			((ArrayAccessContext)_localctx).i = identifier();
			setState(549);
			((ArrayAccessContext)_localctx).de1 = dimensionExpression();
			 ((ArrayAccessContext)_localctx).lval =  buildArrayAccess(loc(_localctx.start), ((ArrayAccessContext)_localctx).i.lval, ((ArrayAccessContext)_localctx).de1.lval); 
			}
			_ctx.stop = _input.LT(-1);
			setState(558);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ArrayAccessContext(_parentctx, _parentState);
					_localctx.aa = _prevctx;
					_localctx.aa = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_arrayAccess);
					setState(552);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(553);
					((ArrayAccessContext)_localctx).de2 = dimensionExpression();
					 ((ArrayAccessContext)_localctx).lval =  buildArrayAccess(loc(_localctx.start), ((ArrayAccessContext)_localctx).aa.lval, ((ArrayAccessContext)_localctx).de2.lval); 
					}
					} 
				}
				setState(560);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 4:
			return mainBlockStatements_sempred((MainBlockStatementsContext)_localctx, predIndex);
		case 6:
			return classDeclarations_sempred((ClassDeclarationsContext)_localctx, predIndex);
		case 9:
			return classBodyDeclarations_sempred((ClassBodyDeclarationsContext)_localctx, predIndex);
		case 13:
			return blockStatements_sempred((BlockStatementsContext)_localctx, predIndex);
		case 22:
			return declarations_sempred((DeclarationsContext)_localctx, predIndex);
		case 30:
			return equalityExpression_sempred((EqualityExpressionContext)_localctx, predIndex);
		case 31:
			return relationalExpression_sempred((RelationalExpressionContext)_localctx, predIndex);
		case 32:
			return additiveExpression_sempred((AdditiveExpressionContext)_localctx, predIndex);
		case 33:
			return multiplicativeExpression_sempred((MultiplicativeExpressionContext)_localctx, predIndex);
		case 44:
			return dimensions_sempred((DimensionsContext)_localctx, predIndex);
		case 46:
			return fieldAccess_sempred((FieldAccessContext)_localctx, predIndex);
		case 47:
			return arrayAccess_sempred((ArrayAccessContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean mainBlockStatements_sempred(MainBlockStatementsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean classDeclarations_sempred(ClassDeclarationsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean classBodyDeclarations_sempred(ClassBodyDeclarationsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean blockStatements_sempred(BlockStatementsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean declarations_sempred(DeclarationsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean equalityExpression_sempred(EqualityExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean relationalExpression_sempred(RelationalExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 3);
		case 7:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean additiveExpression_sempred(AdditiveExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 8:
			return precpred(_ctx, 3);
		case 9:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean multiplicativeExpression_sempred(MultiplicativeExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 10:
			return precpred(_ctx, 3);
		case 11:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean dimensions_sempred(DimensionsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 12:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean fieldAccess_sempred(FieldAccessContext _localctx, int predIndex) {
		switch (predIndex) {
		case 13:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean arrayAccess_sempred(ArrayAccessContext _localctx, int predIndex) {
		switch (predIndex) {
		case 14:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\"\u0234\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\3\2\3\2\3\2\3\2\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\7\6~\n\6\f\6\16\6\u0081\13\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\7\b\u008e\n\b\f\b\16\b\u0091\13\b\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u009f\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\5\n\u00a9\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00b3\n"+
		"\13\f\13\16\13\u00b6\13\13\3\f\3\f\3\f\3\f\3\f\5\f\u00bd\n\f\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00c9\n\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\5\16\u00d3\n\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\7\17\u00dd\n\17\f\17\16\17\u00e0\13\17\3\20\3\20\3\20\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0100\n\21\3\22"+
		"\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u0125\n\27\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\7\30\u0130\n\30\f\30\16\30\u0133\13\30\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\5\31\u013c\n\31\3\32\3\32\3\32\3\32\3\33\3\33"+
		"\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u014e\n\35\3\36"+
		"\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\5\37"+
		"\u015e\n\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \7 \u016a\n \f \16 \u016d\13"+
		" \3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\7!\u017d\n!\f!\16!\u0180\13"+
		"!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\7\"\u0190\n"+
		"\"\f\"\16\"\u0193\13\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\7#\u01a3"+
		"\n#\f#\16#\u01a6\13#\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\5$\u01b3\n$\3%\3"+
		"%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\5%\u01c2\n%\3&\3&\3&\3&\3&\3&\3&\3"+
		"&\3&\5&\u01cd\n&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\5\'\u01db"+
		"\n\'\3(\3(\3(\3(\3(\3)\3)\3)\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3"+
		"+\5+\u01f3\n+\3,\3,\3,\3,\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3.\3.\7.\u0206"+
		"\n.\f.\16.\u0209\13.\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3"+
		"\60\3\60\3\60\3\60\5\60\u021a\n\60\3\60\3\60\3\60\3\60\3\60\7\60\u0221"+
		"\n\60\f\60\16\60\u0224\13\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3"+
		"\61\7\61\u022f\n\61\f\61\16\61\u0232\13\61\3\61\2\16\n\16\24\34.>@BDZ"+
		"^`\62\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>"+
		"@BDFHJLNPRTVXZ\\^`\2\2\2\u022f\2b\3\2\2\2\4f\3\2\2\2\6m\3\2\2\2\bp\3\2"+
		"\2\2\nu\3\2\2\2\f\u0082\3\2\2\2\16\u0085\3\2\2\2\20\u009e\3\2\2\2\22\u00a8"+
		"\3\2\2\2\24\u00aa\3\2\2\2\26\u00bc\3\2\2\2\30\u00c8\3\2\2\2\32\u00d2\3"+
		"\2\2\2\34\u00d4\3\2\2\2\36\u00e1\3\2\2\2 \u00ff\3\2\2\2\"\u0101\3\2\2"+
		"\2$\u0105\3\2\2\2&\u0109\3\2\2\2(\u0110\3\2\2\2*\u0115\3\2\2\2,\u0124"+
		"\3\2\2\2.\u0126\3\2\2\2\60\u013b\3\2\2\2\62\u013d\3\2\2\2\64\u0141\3\2"+
		"\2\2\66\u0144\3\2\2\28\u014d\3\2\2\2:\u014f\3\2\2\2<\u015d\3\2\2\2>\u015f"+
		"\3\2\2\2@\u016e\3\2\2\2B\u0181\3\2\2\2D\u0194\3\2\2\2F\u01b2\3\2\2\2H"+
		"\u01c1\3\2\2\2J\u01cc\3\2\2\2L\u01da\3\2\2\2N\u01dc\3\2\2\2P\u01e1\3\2"+
		"\2\2R\u01e4\3\2\2\2T\u01f2\3\2\2\2V\u01f4\3\2\2\2X\u01f8\3\2\2\2Z\u01fd"+
		"\3\2\2\2\\\u020a\3\2\2\2^\u0219\3\2\2\2`\u0225\3\2\2\2bc\5\4\3\2cd\7\2"+
		"\2\3de\b\2\1\2e\3\3\2\2\2fg\7\25\2\2gh\7\26\2\2hi\7\4\2\2ij\7\5\2\2jk"+
		"\5\6\4\2kl\b\3\1\2l\5\3\2\2\2mn\5\b\5\2no\b\4\1\2o\7\3\2\2\2pq\7\6\2\2"+
		"qr\5\n\6\2rs\7\7\2\2st\b\5\1\2t\t\3\2\2\2uv\b\6\1\2vw\5\f\7\2wx\b\6\1"+
		"\2x\177\3\2\2\2yz\f\4\2\2z{\5\f\7\2{|\b\6\1\2|~\3\2\2\2}y\3\2\2\2~\u0081"+
		"\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\13\3\2\2\2\u0081\177\3\2"+
		"\2\2\u0082\u0083\5\36\20\2\u0083\u0084\b\7\1\2\u0084\r\3\2\2\2\u0085\u0086"+
		"\b\b\1\2\u0086\u0087\5\20\t\2\u0087\u0088\b\b\1\2\u0088\u008f\3\2\2\2"+
		"\u0089\u008a\f\4\2\2\u008a\u008b\5\20\t\2\u008b\u008c\b\b\1\2\u008c\u008e"+
		"\3\2\2\2\u008d\u0089\3\2\2\2\u008e\u0091\3\2\2\2\u008f\u008d\3\2\2\2\u008f"+
		"\u0090\3\2\2\2\u0090\17\3\2\2\2\u0091\u008f\3\2\2\2\u0092\u0093\7\36\2"+
		"\2\u0093\u0094\5R*\2\u0094\u0095\5\22\n\2\u0095\u0096\b\t\1\2\u0096\u009f"+
		"\3\2\2\2\u0097\u0098\7\36\2\2\u0098\u0099\5R*\2\u0099\u009a\7\37\2\2\u009a"+
		"\u009b\5R*\2\u009b\u009c\5\22\n\2\u009c\u009d\b\t\1\2\u009d\u009f\3\2"+
		"\2\2\u009e\u0092\3\2\2\2\u009e\u0097\3\2\2\2\u009f\21\3\2\2\2\u00a0\u00a1"+
		"\7\6\2\2\u00a1\u00a2\5\24\13\2\u00a2\u00a3\7\7\2\2\u00a3\u00a4\b\n\1\2"+
		"\u00a4\u00a9\3\2\2\2\u00a5\u00a6\7\6\2\2\u00a6\u00a7\7\7\2\2\u00a7\u00a9"+
		"\b\n\1\2\u00a8\u00a0\3\2\2\2\u00a8\u00a5\3\2\2\2\u00a9\23\3\2\2\2\u00aa"+
		"\u00ab\b\13\1\2\u00ab\u00ac\5\26\f\2\u00ac\u00ad\b\13\1\2\u00ad\u00b4"+
		"\3\2\2\2\u00ae\u00af\f\4\2\2\u00af\u00b0\5\26\f\2\u00b0\u00b1\b\13\1\2"+
		"\u00b1\u00b3\3\2\2\2\u00b2\u00ae\3\2\2\2\u00b3\u00b6\3\2\2\2\u00b4\u00b2"+
		"\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\25\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b7"+
		"\u00b8\5\30\r\2\u00b8\u00b9\b\f\1\2\u00b9\u00bd\3\2\2\2\u00ba\u00bb\7"+
		"\n\2\2\u00bb\u00bd\b\f\1\2\u00bc\u00b7\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd"+
		"\27\3\2\2\2\u00be\u00bf\5V,\2\u00bf\u00c0\5.\30\2\u00c0\u00c1\7\n\2\2"+
		"\u00c1\u00c2\b\r\1\2\u00c2\u00c9\3\2\2\2\u00c3\u00c4\7\25\2\2\u00c4\u00c5"+
		"\5.\30\2\u00c5\u00c6\7\n\2\2\u00c6\u00c7\b\r\1\2\u00c7\u00c9\3\2\2\2\u00c8"+
		"\u00be\3\2\2\2\u00c8\u00c3\3\2\2\2\u00c9\31\3\2\2\2\u00ca\u00cb\7\6\2"+
		"\2\u00cb\u00cc\5\34\17\2\u00cc\u00cd\7\7\2\2\u00cd\u00ce\b\16\1\2\u00ce"+
		"\u00d3\3\2\2\2\u00cf\u00d0\7\6\2\2\u00d0\u00d1\7\7\2\2\u00d1\u00d3\b\16"+
		"\1\2\u00d2\u00ca\3\2\2\2\u00d2\u00cf\3\2\2\2\u00d3\33\3\2\2\2\u00d4\u00d5"+
		"\b\17\1\2\u00d5\u00d6\5\36\20\2\u00d6\u00d7\b\17\1\2\u00d7\u00de\3\2\2"+
		"\2\u00d8\u00d9\f\4\2\2\u00d9\u00da\5\36\20\2\u00da\u00db\b\17\1\2\u00db"+
		"\u00dd\3\2\2\2\u00dc\u00d8\3\2\2\2\u00dd\u00e0\3\2\2\2\u00de\u00dc\3\2"+
		"\2\2\u00de\u00df\3\2\2\2\u00df\35\3\2\2\2\u00e0\u00de\3\2\2\2\u00e1\u00e2"+
		"\5 \21\2\u00e2\u00e3\b\20\1\2\u00e3\37\3\2\2\2\u00e4\u00e5\5\32\16\2\u00e5"+
		"\u00e6\b\21\1\2\u00e6\u0100\3\2\2\2\u00e7\u00e8\5\64\33\2\u00e8\u00e9"+
		"\b\21\1\2\u00e9\u0100\3\2\2\2\u00ea\u00eb\5\62\32\2\u00eb\u00ec\b\21\1"+
		"\2\u00ec\u0100\3\2\2\2\u00ed\u00ee\5&\24\2\u00ee\u00ef\b\21\1\2\u00ef"+
		"\u0100\3\2\2\2\u00f0\u00f1\5(\25\2\u00f1\u00f2\b\21\1\2\u00f2\u0100\3"+
		"\2\2\2\u00f3\u00f4\5*\26\2\u00f4\u00f5\b\21\1\2\u00f5\u0100\3\2\2\2\u00f6"+
		"\u00f7\5,\27\2\u00f7\u00f8\b\21\1\2\u00f8\u0100\3\2\2\2\u00f9\u00fa\5"+
		"\"\22\2\u00fa\u00fb\b\21\1\2\u00fb\u0100\3\2\2\2\u00fc\u00fd\5$\23\2\u00fd"+
		"\u00fe\b\21\1\2\u00fe\u0100\3\2\2\2\u00ff\u00e4\3\2\2\2\u00ff\u00e7\3"+
		"\2\2\2\u00ff\u00ea\3\2\2\2\u00ff\u00ed\3\2\2\2\u00ff\u00f0\3\2\2\2\u00ff"+
		"\u00f3\3\2\2\2\u00ff\u00f6\3\2\2\2\u00ff\u00f9\3\2\2\2\u00ff\u00fc\3\2"+
		"\2\2\u0100!\3\2\2\2\u0101\u0102\7\33\2\2\u0102\u0103\7\n\2\2\u0103\u0104"+
		"\b\22\1\2\u0104#\3\2\2\2\u0105\u0106\7\34\2\2\u0106\u0107\7\n\2\2\u0107"+
		"\u0108\b\23\1\2\u0108%\3\2\2\2\u0109\u010a\7\30\2\2\u010a\u010b\5N(\2"+
		"\u010b\u010c\5 \21\2\u010c\u010d\7\31\2\2\u010d\u010e\5 \21\2\u010e\u010f"+
		"\b\24\1\2\u010f\'\3\2\2\2\u0110\u0111\7\32\2\2\u0111\u0112\5N(\2\u0112"+
		"\u0113\5 \21\2\u0113\u0114\b\25\1\2\u0114)\3\2\2\2\u0115\u0116\7\27\2"+
		"\2\u0116\u0117\5\66\34\2\u0117\u0118\7\n\2\2\u0118\u0119\b\26\1\2\u0119"+
		"+\3\2\2\2\u011a\u011b\5V,\2\u011b\u011c\5.\30\2\u011c\u011d\7\n\2\2\u011d"+
		"\u011e\b\27\1\2\u011e\u0125\3\2\2\2\u011f\u0120\7\25\2\2\u0120\u0121\5"+
		".\30\2\u0121\u0122\7\n\2\2\u0122\u0123\b\27\1\2\u0123\u0125\3\2\2\2\u0124"+
		"\u011a\3\2\2\2\u0124\u011f\3\2\2\2\u0125-\3\2\2\2\u0126\u0127\b\30\1\2"+
		"\u0127\u0128\5\60\31\2\u0128\u0129\b\30\1\2\u0129\u0131\3\2\2\2\u012a"+
		"\u012b\f\4\2\2\u012b\u012c\7\r\2\2\u012c\u012d\5\60\31\2\u012d\u012e\b"+
		"\30\1\2\u012e\u0130\3\2\2\2\u012f\u012a\3\2\2\2\u0130\u0133\3\2\2\2\u0131"+
		"\u012f\3\2\2\2\u0131\u0132\3\2\2\2\u0132/\3\2\2\2\u0133\u0131\3\2\2\2"+
		"\u0134\u0135\5R*\2\u0135\u0136\5Z.\2\u0136\u0137\b\31\1\2\u0137\u013c"+
		"\3\2\2\2\u0138\u0139\5R*\2\u0139\u013a\b\31\1\2\u013a\u013c\3\2\2\2\u013b"+
		"\u0134\3\2\2\2\u013b\u0138\3\2\2\2\u013c\61\3\2\2\2\u013d\u013e\5\66\34"+
		"\2\u013e\u013f\7\n\2\2\u013f\u0140\b\32\1\2\u0140\63\3\2\2\2\u0141\u0142"+
		"\7\n\2\2\u0142\u0143\b\33\1\2\u0143\65\3\2\2\2\u0144\u0145\58\35\2\u0145"+
		"\u0146\b\34\1\2\u0146\67\3\2\2\2\u0147\u0148\5:\36\2\u0148\u0149\b\35"+
		"\1\2\u0149\u014e\3\2\2\2\u014a\u014b\5> \2\u014b\u014c\b\35\1\2\u014c"+
		"\u014e\3\2\2\2\u014d\u0147\3\2\2\2\u014d\u014a\3\2\2\2\u014e9\3\2\2\2"+
		"\u014f\u0150\5<\37\2\u0150\u0151\7\16\2\2\u0151\u0152\58\35\2\u0152\u0153"+
		"\b\36\1\2\u0153;\3\2\2\2\u0154\u0155\5R*\2\u0155\u0156\b\37\1\2\u0156"+
		"\u015e\3\2\2\2\u0157\u0158\5^\60\2\u0158\u0159\b\37\1\2\u0159\u015e\3"+
		"\2\2\2\u015a\u015b\5`\61\2\u015b\u015c\b\37\1\2\u015c\u015e\3\2\2\2\u015d"+
		"\u0154\3\2\2\2\u015d\u0157\3\2\2\2\u015d\u015a\3\2\2\2\u015e=\3\2\2\2"+
		"\u015f\u0160\b \1\2\u0160\u0161\5@!\2\u0161\u0162\b \1\2\u0162\u016b\3"+
		"\2\2\2\u0163\u0164\f\4\2\2\u0164\u0165\7\16\2\2\u0165\u0166\7\16\2\2\u0166"+
		"\u0167\5@!\2\u0167\u0168\b \1\2\u0168\u016a\3\2\2\2\u0169\u0163\3\2\2"+
		"\2\u016a\u016d\3\2\2\2\u016b\u0169\3\2\2\2\u016b\u016c\3\2\2\2\u016c?"+
		"\3\2\2\2\u016d\u016b\3\2\2\2\u016e\u016f\b!\1\2\u016f\u0170\5B\"\2\u0170"+
		"\u0171\b!\1\2\u0171\u017e\3\2\2\2\u0172\u0173\f\5\2\2\u0173\u0174\7\22"+
		"\2\2\u0174\u0175\5B\"\2\u0175\u0176\b!\1\2\u0176\u017d\3\2\2\2\u0177\u0178"+
		"\f\4\2\2\u0178\u0179\7\23\2\2\u0179\u017a\5B\"\2\u017a\u017b\b!\1\2\u017b"+
		"\u017d\3\2\2\2\u017c\u0172\3\2\2\2\u017c\u0177\3\2\2\2\u017d\u0180\3\2"+
		"\2\2\u017e\u017c\3\2\2\2\u017e\u017f\3\2\2\2\u017fA\3\2\2\2\u0180\u017e"+
		"\3\2\2\2\u0181\u0182\b\"\1\2\u0182\u0183\5D#\2\u0183\u0184\b\"\1\2\u0184"+
		"\u0191\3\2\2\2\u0185\u0186\f\5\2\2\u0186\u0187\7\13\2\2\u0187\u0188\5"+
		"D#\2\u0188\u0189\b\"\1\2\u0189\u0190\3\2\2\2\u018a\u018b\f\4\2\2\u018b"+
		"\u018c\7\f\2\2\u018c\u018d\5D#\2\u018d\u018e\b\"\1\2\u018e\u0190\3\2\2"+
		"\2\u018f\u0185\3\2\2\2\u018f\u018a\3\2\2\2\u0190\u0193\3\2\2\2\u0191\u018f"+
		"\3\2\2\2\u0191\u0192\3\2\2\2\u0192C\3\2\2\2\u0193\u0191\3\2\2\2\u0194"+
		"\u0195\b#\1\2\u0195\u0196\5F$\2\u0196\u0197\b#\1\2\u0197\u01a4\3\2\2\2"+
		"\u0198\u0199\f\5\2\2\u0199\u019a\7\20\2\2\u019a\u019b\5F$\2\u019b\u019c"+
		"\b#\1\2\u019c\u01a3\3\2\2\2\u019d\u019e\f\4\2\2\u019e\u019f\7\21\2\2\u019f"+
		"\u01a0\5F$\2\u01a0\u01a1\b#\1\2\u01a1\u01a3\3\2\2\2\u01a2\u0198\3\2\2"+
		"\2\u01a2\u019d\3\2\2\2\u01a3\u01a6\3\2\2\2\u01a4\u01a2\3\2\2\2\u01a4\u01a5"+
		"\3\2\2\2\u01a5E\3\2\2\2\u01a6\u01a4\3\2\2\2\u01a7\u01a8\7\f\2\2\u01a8"+
		"\u01a9\5F$\2\u01a9\u01aa\b$\1\2\u01aa\u01b3\3\2\2\2\u01ab\u01ac\7\17\2"+
		"\2\u01ac\u01ad\5F$\2\u01ad\u01ae\b$\1\2\u01ae\u01b3\3\2\2\2\u01af\u01b0"+
		"\5H%\2\u01b0\u01b1\b$\1\2\u01b1\u01b3\3\2\2\2\u01b2\u01a7\3\2\2\2\u01b2"+
		"\u01ab\3\2\2\2\u01b2\u01af\3\2\2\2\u01b3G\3\2\2\2\u01b4\u01b5\5N(\2\u01b5"+
		"\u01b6\5H%\2\u01b6\u01b7\b%\1\2\u01b7\u01c2\3\2\2\2\u01b8\u01b9\7\4\2"+
		"\2\u01b9\u01ba\5V,\2\u01ba\u01bb\7\5\2\2\u01bb\u01bc\5H%\2\u01bc\u01bd"+
		"\b%\1\2\u01bd\u01c2\3\2\2\2\u01be\u01bf\5J&\2\u01bf\u01c0\b%\1\2\u01c0"+
		"\u01c2\3\2\2\2\u01c1\u01b4\3\2\2\2\u01c1\u01b8\3\2\2\2\u01c1\u01be\3\2"+
		"\2\2\u01c2I\3\2\2\2\u01c3\u01c4\5T+\2\u01c4\u01c5\b&\1\2\u01c5\u01cd\3"+
		"\2\2\2\u01c6\u01c7\5R*\2\u01c7\u01c8\b&\1\2\u01c8\u01cd\3\2\2\2\u01c9"+
		"\u01ca\5L\'\2\u01ca\u01cb\b&\1\2\u01cb\u01cd\3\2\2\2\u01cc\u01c3\3\2\2"+
		"\2\u01cc\u01c6\3\2\2\2\u01cc\u01c9\3\2\2\2\u01cdK\3\2\2\2\u01ce\u01cf"+
		"\5N(\2\u01cf\u01d0\b\'\1\2\u01d0\u01db\3\2\2\2\u01d1\u01d2\5^\60\2\u01d2"+
		"\u01d3\b\'\1\2\u01d3\u01db\3\2\2\2\u01d4\u01d5\5`\61\2\u01d5\u01d6\b\'"+
		"\1\2\u01d6\u01db\3\2\2\2\u01d7\u01d8\5P)\2\u01d8\u01d9\b\'\1\2\u01d9\u01db"+
		"\3\2\2\2\u01da\u01ce\3\2\2\2\u01da\u01d1\3\2\2\2\u01da\u01d4\3\2\2\2\u01da"+
		"\u01d7\3\2\2\2\u01dbM\3\2\2\2\u01dc\u01dd\7\4\2\2\u01dd\u01de\5\66\34"+
		"\2\u01de\u01df\7\5\2\2\u01df\u01e0\b(\1\2\u01e0O\3\2\2\2\u01e1\u01e2\7"+
		"\3\2\2\u01e2\u01e3\b)\1\2\u01e3Q\3\2\2\2\u01e4\u01e5\7 \2\2\u01e5\u01e6"+
		"\b*\1\2\u01e6S\3\2\2\2\u01e7\u01e8\7\35\2\2\u01e8\u01e9\7\25\2\2\u01e9"+
		"\u01ea\5X-\2\u01ea\u01eb\5Z.\2\u01eb\u01ec\b+\1\2\u01ec\u01f3\3\2\2\2"+
		"\u01ed\u01ee\7\35\2\2\u01ee\u01ef\7\25\2\2\u01ef\u01f0\5X-\2\u01f0\u01f1"+
		"\b+\1\2\u01f1\u01f3\3\2\2\2\u01f2\u01e7\3\2\2\2\u01f2\u01ed\3\2\2\2\u01f3"+
		"U\3\2\2\2\u01f4\u01f5\7\25\2\2\u01f5\u01f6\5Z.\2\u01f6\u01f7\b,\1\2\u01f7"+
		"W\3\2\2\2\u01f8\u01f9\7\b\2\2\u01f9\u01fa\5\66\34\2\u01fa\u01fb\7\t\2"+
		"\2\u01fb\u01fc\b-\1\2\u01fcY\3\2\2\2\u01fd\u01fe\b.\1\2\u01fe\u01ff\5"+
		"\\/\2\u01ff\u0200\b.\1\2\u0200\u0207\3\2\2\2\u0201\u0202\f\4\2\2\u0202"+
		"\u0203\5\\/\2\u0203\u0204\b.\1\2\u0204\u0206\3\2\2\2\u0205\u0201\3\2\2"+
		"\2\u0206\u0209\3\2\2\2\u0207\u0205\3\2\2\2\u0207\u0208\3\2\2\2\u0208["+
		"\3\2\2\2\u0209\u0207\3\2\2\2\u020a\u020b\7\b\2\2\u020b\u020c\7\t\2\2\u020c"+
		"\u020d\b/\1\2\u020d]\3\2\2\2\u020e\u020f\b\60\1\2\u020f\u0210\5R*\2\u0210"+
		"\u0211\7\24\2\2\u0211\u0212\5R*\2\u0212\u0213\b\60\1\2\u0213\u021a\3\2"+
		"\2\2\u0214\u0215\5`\61\2\u0215\u0216\7\24\2\2\u0216\u0217\5R*\2\u0217"+
		"\u0218\b\60\1\2\u0218\u021a\3\2\2\2\u0219\u020e\3\2\2\2\u0219\u0214\3"+
		"\2\2\2\u021a\u0222\3\2\2\2\u021b\u021c\f\4\2\2\u021c\u021d\7\24\2\2\u021d"+
		"\u021e\5R*\2\u021e\u021f\b\60\1\2\u021f\u0221\3\2\2\2\u0220\u021b\3\2"+
		"\2\2\u0221\u0224\3\2\2\2\u0222\u0220\3\2\2\2\u0222\u0223\3\2\2\2\u0223"+
		"_\3\2\2\2\u0224\u0222\3\2\2\2\u0225\u0226\b\61\1\2\u0226\u0227\5R*\2\u0227"+
		"\u0228\5X-\2\u0228\u0229\b\61\1\2\u0229\u0230\3\2\2\2\u022a\u022b\f\3"+
		"\2\2\u022b\u022c\5X-\2\u022c\u022d\b\61\1\2\u022d\u022f\3\2\2\2\u022e"+
		"\u022a\3\2\2\2\u022f\u0232\3\2\2\2\u0230\u022e\3\2\2\2\u0230\u0231\3\2"+
		"\2\2\u0231a\3\2\2\2\u0232\u0230\3\2\2\2!\177\u008f\u009e\u00a8\u00b4\u00bc"+
		"\u00c8\u00d2\u00de\u00ff\u0124\u0131\u013b\u014d\u015d\u016b\u017c\u017e"+
		"\u018f\u0191\u01a2\u01a4\u01b2\u01c1\u01cc\u01da\u01f2\u0207\u0219\u0222"+
		"\u0230";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}