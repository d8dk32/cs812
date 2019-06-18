#
# check if parser needs to be rebuilt
#
# also contains target for generating class files for parser in this directory
# this is used with the gui.sh, tokens.sh and tree.sh scripts that run the
#   parser in debug mode.
#

TParser.java: T.g4
	bash buildParser.sh

debug: T.g4
	bash buildParserForDebugging.sh
	javac -cp ../../build/packages:../../build/lib/antlr.jar *.java
	touch debug

clean:
	/bin/rm -f TParser.java T.tokens TLexer.java TLexer.tokens TListener.java TBaseListener.java T.interp TLexer.interp *.class debug

