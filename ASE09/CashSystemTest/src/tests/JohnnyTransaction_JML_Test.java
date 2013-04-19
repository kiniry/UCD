// This file was generated by jmlunit on Sun May 10 22:14:55 BST 2009.

package tests;

import cashsystem.*;

/** Automatically-generated test driver for JML and JUnit based
 * testing of JohnnyTransaction. The superclass of this class should be edited
 * to supply test data. However it's best not to edit this class
 * directly; instead use the command
 * <pre>
 *  jmlunit JohnnyTransaction.java
 * </pre>
 * to regenerate this class whenever JohnnyTransaction.java changes.
 */
public class JohnnyTransaction_JML_Test
     extends JohnnyTransaction_JML_TestData
{
    /** Initialize this class. */
    public JohnnyTransaction_JML_Test(java.lang.String name) {
        super(name);
    }

    /** Run the tests. */
    public static void main(java.lang.String[] args) {
        org.jmlspecs.jmlunit.JMLTestRunner.run(suite());
        // You can also use a JUnit test runner such as:
        // junit.textui.TestRunner.run(suite());
    }

    /** Test to see if the code for class JohnnyTransaction
     * has been compiled with runtime assertion checking (i.e., by jmlc).
     * Code that is not compiled with jmlc would not make an effective test,
     * since no assertion checking would be done. */
    public void test$IsRACCompiled() {
        junit.framework.Assert.assertTrue("code for class JohnnyTransaction"
                + " was not compiled with jmlc"
                + " so no assertions will be checked!",
            org.jmlspecs.jmlrac.runtime.JMLChecker.isRACCompiled(JohnnyTransaction.class)
            );
    }

    /** Return the test suite for this test class.  This will have
    * added to it at least test$IsRACCompiled(), and any test methods
    * written explicitly by the user in the superclass.  It will also
    * have added test suites for each testing each method and
    * constructor.
    */
    //@ ensures \result != null;
    public static junit.framework.Test suite() {
        JohnnyTransaction_JML_Test testobj
            = new JohnnyTransaction_JML_Test("JohnnyTransaction_JML_Test");
        junit.framework.TestSuite testsuite = testobj.overallTestSuite();
        // Add instances of Test found by the reflection mechanism.
        testsuite.addTestSuite(JohnnyTransaction_JML_Test.class);
        testobj.addTestSuiteForEachMethod(testsuite);
        return testsuite;
    }

    /** A JUnit test object that can run a single test method.  This
     * is defined as a nested class solely for convenience; it can't
     * be defined once and for all because it must subclass its
     * enclosing class.
     */
    protected static abstract class OneTest extends JohnnyTransaction_JML_Test {

        /** Initialize this test object. */
        public OneTest(String name) {
            super(name);
        }

        /** The result object that holds information about testing. */
        protected junit.framework.TestResult result;

        //@ also
        //@ requires result != null;
        public void run(junit.framework.TestResult result) {
            this.result = result;
            super.run(result);
        }

        /* Run a single test and decide whether the test was
         * successful, meaningless, or a failure.  This is the
         * Template Method pattern abstraction of the inner loop in a
         * JML/JUnit test. */
        public void runTest() throws java.lang.Throwable {
            try {
                // The call being tested!
                doCall();
            }
            catch (org.jmlspecs.jmlrac.runtime.JMLEntryPreconditionError e) {
                // meaningless test input
                addMeaningless();
            } catch (org.jmlspecs.jmlrac.runtime.JMLAssertionError e) {
                // test failure
                int l = org.jmlspecs.jmlrac.runtime.JMLChecker.getLevel();
                org.jmlspecs.jmlrac.runtime.JMLChecker.setLevel
                    (org.jmlspecs.jmlrac.runtime.JMLOption.NONE);
                try {
                    java.lang.String failmsg = this.failMessage(e);
                    junit.framework.AssertionFailedError err
                        = new junit.framework.AssertionFailedError(failmsg);
                    err.setStackTrace(new java.lang.StackTraceElement[]{});
                    err.initCause(e);
                    result.addFailure(this, err);
                } finally {
                    org.jmlspecs.jmlrac.runtime.JMLChecker.setLevel(l);
                }
            } catch (java.lang.Throwable e) {
                // test success
            }
        }

        /** Call the method to be tested with the appropriate arguments. */
        protected abstract void doCall() throws java.lang.Throwable;

        /** Format the error message for a test failure, based on the
         * method's arguments. */
        protected abstract java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e);

        /** Inform listeners that a meaningless test was run. */
        private void addMeaningless() {
            if (result instanceof org.jmlspecs.jmlunit.JMLTestResult) {
                ((org.jmlspecs.jmlunit.JMLTestResult)result)
                    .addMeaningless(this);
            }
        }
    }

    /** Create the tests that are to be run for testing the class
     * JohnnyTransaction.  The framework will then run them.
     * @param overallTestSuite$ The suite accumulating all of the tests
     * for this driver class.
     */
    //@ requires overallTestSuite$ != null;
    public void addTestSuiteForEachMethod
        (junit.framework.TestSuite overallTestSuite$)
    {
        try {
            this.addTestSuiteFor$TestJohnnyTransaction(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestGetTimestamp(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestGetAmount(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestGetLocation(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
    }

    /** Add tests for the JohnnyTransaction contructor
     * to the overall test suite. */
    private void addTestSuiteFor$TestJohnnyTransaction
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("JohnnyTransaction");
        try {
            org.jmlspecs.jmlunit.strategies.IntIterator
                vint$1$iter
                = this.vintIter("JohnnyTransaction", 2);
            this.check_has_data
                (vint$1$iter,
                 "this.vintIter(\"JohnnyTransaction\", 2)");
            while (!vint$1$iter.atEnd()) {
                org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                    vjava_lang_String$2$iter
                    = this.vjava_lang_StringIter("JohnnyTransaction", 1);
                this.check_has_data
                    (vjava_lang_String$2$iter,
                     "this.vjava_lang_StringIter(\"JohnnyTransaction\", 1)");
                while (!vjava_lang_String$2$iter.atEnd()) {
                    org.jmlspecs.jmlunit.strategies.LongIterator
                        vlong$3$iter
                        = this.vlongIter("JohnnyTransaction", 0);
                    this.check_has_data
                        (vlong$3$iter,
                         "this.vlongIter(\"JohnnyTransaction\", 0)");
                    while (!vlong$3$iter.atEnd()) {
                        final int value
                            = vint$1$iter.getInt();
                        final java.lang.String loc
                            = (java.lang.String) vjava_lang_String$2$iter.get();
                        final long time
                            = vlong$3$iter.getLong();
                        methodTests$.addTest
                            (new TestJohnnyTransaction(value, loc, time));
                        vlong$3$iter.advance();
                    }
                    vjava_lang_String$2$iter.advance();
                }
                vint$1$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the JohnnyTransaction contructor. */
    protected static class TestJohnnyTransaction extends OneTest {
        /** Argument value */
        private int value;
        /** Argument loc */
        private java.lang.String loc;
        /** Argument time */
        private long time;

        /** Initialize this instance. */
        public TestJohnnyTransaction(int value, java.lang.String loc, long time) {
            super("JohnnyTransaction"+ ":" + value+ "," +(loc==null? "null" :("\""+loc+"\""))+ "," +time);
            this.value = value;
            this.loc = loc;
            this.time = time;
        }

        protected void doCall() throws java.lang.Throwable {
            new JohnnyTransaction(value, loc, time);
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tContructor 'JohnnyTransaction' applied to";
            msg += "\n\tArgument value: " + this.value;
            msg += "\n\tArgument loc: " + this.loc;
            msg += "\n\tArgument time: " + this.time;
            return msg;
        }
    }

    /** Add tests for the getTimestamp method
     * to the overall test suite. */
    private void addTestSuiteFor$TestGetTimestamp
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("getTimestamp");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vcashsystem_JohnnyTransactionIter("getTimestamp", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vcashsystem_JohnnyTransactionIter(\"getTimestamp\", 0))");
            while (!receivers$iter.atEnd()) {
                final cashsystem.JohnnyTransaction receiver$
                    = (cashsystem.JohnnyTransaction) receivers$iter.get();
                methodTests$.addTest
                    (new TestGetTimestamp(receiver$));
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the getTimestamp method. */
    protected static class TestGetTimestamp extends OneTest {
        /** The receiver */
        private cashsystem.JohnnyTransaction receiver$;

        /** Initialize this instance. */
        public TestGetTimestamp(cashsystem.JohnnyTransaction receiver$) {
            super("getTimestamp");
            this.receiver$ = receiver$;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.getTimestamp();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'getTimestamp' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            return msg;
        }
    }

    /** Add tests for the getAmount method
     * to the overall test suite. */
    private void addTestSuiteFor$TestGetAmount
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("getAmount");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vcashsystem_JohnnyTransactionIter("getAmount", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vcashsystem_JohnnyTransactionIter(\"getAmount\", 0))");
            while (!receivers$iter.atEnd()) {
                final cashsystem.JohnnyTransaction receiver$
                    = (cashsystem.JohnnyTransaction) receivers$iter.get();
                methodTests$.addTest
                    (new TestGetAmount(receiver$));
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the getAmount method. */
    protected static class TestGetAmount extends OneTest {
        /** The receiver */
        private cashsystem.JohnnyTransaction receiver$;

        /** Initialize this instance. */
        public TestGetAmount(cashsystem.JohnnyTransaction receiver$) {
            super("getAmount");
            this.receiver$ = receiver$;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.getAmount();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'getAmount' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            return msg;
        }
    }

    /** Add tests for the getLocation method
     * to the overall test suite. */
    private void addTestSuiteFor$TestGetLocation
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("getLocation");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vcashsystem_JohnnyTransactionIter("getLocation", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vcashsystem_JohnnyTransactionIter(\"getLocation\", 0))");
            while (!receivers$iter.atEnd()) {
                final cashsystem.JohnnyTransaction receiver$
                    = (cashsystem.JohnnyTransaction) receivers$iter.get();
                methodTests$.addTest
                    (new TestGetLocation(receiver$));
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the getLocation method. */
    protected static class TestGetLocation extends OneTest {
        /** The receiver */
        private cashsystem.JohnnyTransaction receiver$;

        /** Initialize this instance. */
        public TestGetLocation(cashsystem.JohnnyTransaction receiver$) {
            super("getLocation");
            this.receiver$ = receiver$;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.getLocation();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'getLocation' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            return msg;
        }
    }

    /** Check that the iterator is non-null and not empty. */
    private void
    check_has_data(org.jmlspecs.jmlunit.strategies.IndefiniteIterator iter,
                   String call)
    {
        if (iter == null) {
            junit.framework.Assert.fail(call + " returned null");
        }
        if (iter.atEnd()) {
            junit.framework.Assert.fail(call + " returned an empty iterator");
        }
    }

    /** Converts a char to a printable String for display */
    public static String charToString(char c) {
        if (c == '\n') {
            return "NL";
        } else if (c == '\r') {
            return "CR";
        } else if (c == '\t') {
            return "TAB";
        } else if (Character.isISOControl(c)) {
            int i = (int)c;
            return "\\u"
                    + Character.forDigit((i/2048)%16,16)
                    + Character.forDigit((i/256)%16,16)
                    + Character.forDigit((i/16)%16,16)
                    + Character.forDigit((i)%16,16);
        }
        return Character.toString(c);
    }
}