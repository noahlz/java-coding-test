Your assignment is to implement two classes, FileLister and CaesarCipher.  You
have been provided with a stub definition of each in the "src/main" directory
and a JUnit test class for each in the "src/test" directory.  Although the code
compiles, most of the tests are failing.

Read the comments in the stub source files and the code in the test classes to
determine the correct behavior for the classes, then edit the stub sources to
ensure that all of the tests pass.

You may add tests to the test classes if you wish, but please do not remove or 
modify the existing tests.

Project files have been prepared for use with IntelliJ IDEA or Eclipse which 
will allow you to edit code and run the tests with the IDE.  You may also run
the tests using "run-tests.bat" which uses Ant to compile your code and run
the tests, producing a HTML test results report.

Your primary objective is to write code that passes the tests.

Your secondary objective is to write code that is clear and easy to understand.
You may use any coding style that you are comfortable with.

Although the two classes are independent, we recommend that you start with
FileLister, then proceed to CaesarCipher.

If you have any questions about or trouble using the tools or getting the tests
to run, please ask for help.

*** A QUICK OVERVIEW OF JUNIT ***

If you are not familiar with JUnit, it is a framework for developing automated
tests.  A Test Case is represented by a class, and each method whose name 
begins with "test" serves as an individual test which may pass or fail.  The
methods setUp() and tearDown() are executed before and after each individual
test, respectively.

Within each test method, calls to assert- methods ensure that the code is 
behaving as intended.  If any one assertion fails, that individual test fails
and JUnit proceeds to the next individual test method.
