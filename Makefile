SUBPACKAGES = station vehicles controlcenter person main exceptions input 
PACKAGES = vlille
MAIN = VLilleMain
TO_CLEAN = classes/ jars/VLilleMain.jar docs/ testclass/
CLEAN_TERMINAL = printf "\033c"
JUNIT = jars/junit.jar


all: clean compile
	java -classpath classes vlille.main.$(MAIN)

compile : $(SUBPACKAGES)
	$(CLEAN_TERMINAL)

compileTest : compile
	javac -sourcepath src -d testclass -cp testclass:$(JUNIT) $(shell find src/test -name "*.java")

$(SUBPACKAGES) : 
	javac -classpath src -d classes src/vlille/$@/*.java

test: compileTest
	java -jar $(JUNIT) --class-path testclass --scan-classpath testclass

clean:
	rm -rf $(TO_CLEAN)

doc: compile
	javadoc -classpath src -d docs -subpackages $(PACKAGES)
	$(CLEAN_TERMINAL)

jar: compile
	jar cvfm jars/$(MAIN).jar manifest.txt -C classes .

	
