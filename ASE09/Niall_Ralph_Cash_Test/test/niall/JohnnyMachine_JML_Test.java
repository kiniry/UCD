// This file was generated by jmlunit on Thu May 07 20:07:37 BST 2009.

package niall;

/** Automatically-generated test driver for JML and JUnit based
 * testing of JohnnyMachine. The superclass of this class should be edited
 * to supply test data. However it's best not to edit this class
 * directly; instead use the command
 * <pre>
 *  jmlunit JohnnyMachine.java
 * </pre>
 * to regenerate this class whenever JohnnyMachine.java changes.
 */
public class JohnnyMachine_JML_Test
     extends JohnnyMachine_JML_TestData
{
    /** Initialize this class. */
    public JohnnyMachine_JML_Test(java.lang.String name) {
        super(name);
    }

    /** Run the tests. */
    public static void main(java.lang.String[] args) {
        org.jmlspecs.jmlunit.JMLTestRunner.run(suite());
        // You can also use a JUnit test runner such as:
        // junit.textui.TestRunner.run(suite());
    }

    /** Test to see if the code for class JohnnyMachine
     * has been compiled with runtime assertion checking (i.e., by jmlc).
     * Code that is not compiled with jmlc would not make an effective test,
     * since no assertion checking would be done. */
    public void test$IsRACCompiled() {
    	//This test does not work with the current JML compiler
        /*junit.framework.Assert.assertTrue("code for class JohnnyMachine"
                + " was not compiled with jmlc"
                + " so no assertions will be checked!",
            org.jmlspecs.jmlrac.runtime.JMLChecker.isRACCompiled(JohnnyMachine.class)
            );*/
    }

    /** Return the test suite for this test class.  This will have
    * added to it at least test$IsRACCompiled(), and any test methods
    * written explicitly by the user in the superclass.  It will also
    * have added test suites for each testing each method and
    * constructor.
    */
    //@ ensures \result != null;
    public static junit.framework.Test suite() {
        JohnnyMachine_JML_Test testobj
            = new JohnnyMachine_JML_Test("JohnnyMachine_JML_Test");
        junit.framework.TestSuite testsuite = testobj.overallTestSuite();
        // Add instances of Test found by the reflection mechanism.
        testsuite.addTestSuite(JohnnyMachine_JML_Test.class);
        testobj.addTestSuiteForEachMethod(testsuite);
        return testsuite;
    }

    /** A JUnit test object that can run a single test method.  This
     * is defined as a nested class solely for convenience; it can't
     * be defined once and for all because it must subclass its
     * enclosing class.
     */
    protected static abstract class OneTest extends JohnnyMachine_JML_Test {

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
     * JohnnyMachine.  The framework will then run them.
     * @param overallTestSuite$ The suite accumulating all of the tests
     * for this driver class.
     */
    //@ requires overallTestSuite$ != null;
    public void addTestSuiteForEachMethod
        (junit.framework.TestSuite overallTestSuite$)
    {
        try {
            this.addTestSuiteFor$TestGetBalance(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestGetJohnnyCardBalance(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestLockJohnnyCard(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestTransferFunds(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestValidatePin(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestInitCardSession(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
    }

    /** Add tests for the getBalance method
     * to the overall test suite. */
    private void addTestSuiteFor$TestGetBalance
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("getBalance");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vniall_JohnnyMachineIter("getBalance", 1));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vniall_JohnnyMachineIter(\"getBalance\", 1))");
            while (!receivers$iter.atEnd()) {
                org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                    vniall_JohnnyCard$1$iter
                    = this.vniall_JohnnyCardIter("getBalance", 0);
                this.check_has_data
                    (vniall_JohnnyCard$1$iter,
                     "this.vniall_JohnnyCardIter(\"getBalance\", 0)");
                while (!vniall_JohnnyCard$1$iter.atEnd()) {
                    final niall.JohnnyMachine receiver$
                        = (niall.JohnnyMachine) receivers$iter.get();
                    final niall.JohnnyCard johnnyCard
                        = (niall.JohnnyCard) vniall_JohnnyCard$1$iter.get();
                    methodTests$.addTest
                        (new TestGetBalance(receiver$, johnnyCard));
                    vniall_JohnnyCard$1$iter.advance();
                }
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the getBalance method. */
    protected static class TestGetBalance extends OneTest {
        /** The receiver */
        private niall.JohnnyMachine receiver$;
        /** Argument johnnyCard */
        private niall.JohnnyCard johnnyCard;

        /** Initialize this instance. */
        public TestGetBalance(niall.JohnnyMachine receiver$, niall.JohnnyCard johnnyCard) {
            super("getBalance"+ ":" + (johnnyCard==null? "null" :"{niall.JohnnyCard}"));
            this.receiver$ = receiver$;
            this.johnnyCard = johnnyCard;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.getBalance(johnnyCard);
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'getBalance' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            msg += "\n\tArgument johnnyCard: " + this.johnnyCard;
            return msg;
        }
    }

    /** Add tests for the getJohnnyCardBalance method
     * to the overall test suite. */
    private void addTestSuiteFor$TestGetJohnnyCardBalance
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("getJohnnyCardBalance");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vniall_JohnnyMachineIter("getJohnnyCardBalance", 1));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vniall_JohnnyMachineIter(\"getJohnnyCardBalance\", 1))");
            while (!receivers$iter.atEnd()) {
                org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                    vniall_JohnnyCard$1$iter
                    = this.vniall_JohnnyCardIter("getJohnnyCardBalance", 0);
                this.check_has_data
                    (vniall_JohnnyCard$1$iter,
                     "this.vniall_JohnnyCardIter(\"getJohnnyCardBalance\", 0)");
                while (!vniall_JohnnyCard$1$iter.atEnd()) {
                    final niall.JohnnyMachine receiver$
                        = (niall.JohnnyMachine) receivers$iter.get();
                    final niall.JohnnyCard johnnyCard
                        = (niall.JohnnyCard) vniall_JohnnyCard$1$iter.get();
                    methodTests$.addTest
                        (new TestGetJohnnyCardBalance(receiver$, johnnyCard));
                    vniall_JohnnyCard$1$iter.advance();
                }
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the getJohnnyCardBalance method. */
    protected static class TestGetJohnnyCardBalance extends OneTest {
        /** The receiver */
        private niall.JohnnyMachine receiver$;
        /** Argument johnnyCard */
        private niall.JohnnyCard johnnyCard;

        /** Initialize this instance. */
        public TestGetJohnnyCardBalance(niall.JohnnyMachine receiver$, niall.JohnnyCard johnnyCard) {
            super("getJohnnyCardBalance"+ ":" + (johnnyCard==null? "null" :"{niall.JohnnyCard}"));
            this.receiver$ = receiver$;
            this.johnnyCard = johnnyCard;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.getJohnnyCardBalance(johnnyCard);
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'getJohnnyCardBalance' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            msg += "\n\tArgument johnnyCard: " + this.johnnyCard;
            return msg;
        }
    }

    /** Add tests for the lockJohnnyCard method
     * to the overall test suite. */
    private void addTestSuiteFor$TestLockJohnnyCard
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("lockJohnnyCard");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vniall_JohnnyMachineIter("lockJohnnyCard", 1));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vniall_JohnnyMachineIter(\"lockJohnnyCard\", 1))");
            while (!receivers$iter.atEnd()) {
                org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                    vniall_JohnnyCard$1$iter
                    = this.vniall_JohnnyCardIter("lockJohnnyCard", 0);
                this.check_has_data
                    (vniall_JohnnyCard$1$iter,
                     "this.vniall_JohnnyCardIter(\"lockJohnnyCard\", 0)");
                while (!vniall_JohnnyCard$1$iter.atEnd()) {
                    final niall.JohnnyMachine receiver$
                        = (niall.JohnnyMachine) receivers$iter.get();
                    final niall.JohnnyCard johnnyCard
                        = (niall.JohnnyCard) vniall_JohnnyCard$1$iter.get();
                    methodTests$.addTest
                        (new TestLockJohnnyCard(receiver$, johnnyCard));
                    vniall_JohnnyCard$1$iter.advance();
                }
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the lockJohnnyCard method. */
    protected static class TestLockJohnnyCard extends OneTest {
        /** The receiver */
        private niall.JohnnyMachine receiver$;
        /** Argument johnnyCard */
        private niall.JohnnyCard johnnyCard;

        /** Initialize this instance. */
        public TestLockJohnnyCard(niall.JohnnyMachine receiver$, niall.JohnnyCard johnnyCard) {
            super("lockJohnnyCard"+ ":" + (johnnyCard==null? "null" :"{niall.JohnnyCard}"));
            this.receiver$ = receiver$;
            this.johnnyCard = johnnyCard;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.lockJohnnyCard(johnnyCard);
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'lockJohnnyCard' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            msg += "\n\tArgument johnnyCard: " + this.johnnyCard;
            return msg;
        }
    }

    /** Add tests for the transferFunds method
     * to the overall test suite. */
    private void addTestSuiteFor$TestTransferFunds
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("transferFunds");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vniall_JohnnyMachineIter("transferFunds", 2));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vniall_JohnnyMachineIter(\"transferFunds\", 2))");
            while (!receivers$iter.atEnd()) {
                org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                    vniall_JohnnyCard$1$iter
                    = this.vniall_JohnnyCardIter("transferFunds", 1);
                this.check_has_data
                    (vniall_JohnnyCard$1$iter,
                     "this.vniall_JohnnyCardIter(\"transferFunds\", 1)");
                while (!vniall_JohnnyCard$1$iter.atEnd()) {
                    org.jmlspecs.jmlunit.strategies.IntIterator
                        vint$2$iter
                        = this.vintIter("transferFunds", 0);
                    this.check_has_data
                        (vint$2$iter,
                         "this.vintIter(\"transferFunds\", 0)");
                    while (!vint$2$iter.atEnd()) {
                        final niall.JohnnyMachine receiver$
                            = (niall.JohnnyMachine) receivers$iter.get();
                        final niall.JohnnyCard johnnyCard
                            = (niall.JohnnyCard) vniall_JohnnyCard$1$iter.get();
                        final int amount
                            = vint$2$iter.getInt();
                        methodTests$.addTest
                            (new TestTransferFunds(receiver$, johnnyCard, amount));
                        vint$2$iter.advance();
                    }
                    vniall_JohnnyCard$1$iter.advance();
                }
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the transferFunds method. */
    protected static class TestTransferFunds extends OneTest {
        /** The receiver */
        private niall.JohnnyMachine receiver$;
        /** Argument johnnyCard */
        private niall.JohnnyCard johnnyCard;
        /** Argument amount */
        private int amount;

        /** Initialize this instance. */
        public TestTransferFunds(niall.JohnnyMachine receiver$, niall.JohnnyCard johnnyCard, int amount) {
            super("transferFunds"+ ":" + (johnnyCard==null? "null" :"{niall.JohnnyCard}")+ "," +amount);
            this.receiver$ = receiver$;
            this.johnnyCard = johnnyCard;
            this.amount = amount;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.transferFunds(johnnyCard, amount);
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'transferFunds' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            msg += "\n\tArgument johnnyCard: " + this.johnnyCard;
            msg += "\n\tArgument amount: " + this.amount;
            return msg;
        }
    }

    /** Add tests for the validatePin method
     * to the overall test suite. */
    private void addTestSuiteFor$TestValidatePin
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("validatePin");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vniall_JohnnyMachineIter("validatePin", 2));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vniall_JohnnyMachineIter(\"validatePin\", 2))");
            while (!receivers$iter.atEnd()) {
                org.jmlspecs.jmlunit.strategies.IntIterator
                    vint$1$iter
                    = this.vintIter("validatePin", 1);
                this.check_has_data
                    (vint$1$iter,
                     "this.vintIter(\"validatePin\", 1)");
                while (!vint$1$iter.atEnd()) {
                    org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                        vniall_JohnnyCard$2$iter
                        = this.vniall_JohnnyCardIter("validatePin", 0);
                    this.check_has_data
                        (vniall_JohnnyCard$2$iter,
                         "this.vniall_JohnnyCardIter(\"validatePin\", 0)");
                    while (!vniall_JohnnyCard$2$iter.atEnd()) {
                        final niall.JohnnyMachine receiver$
                            = (niall.JohnnyMachine) receivers$iter.get();
                        final int thePIN
                            = vint$1$iter.getInt();
                        final niall.JohnnyCard card
                            = (niall.JohnnyCard) vniall_JohnnyCard$2$iter.get();
                        methodTests$.addTest
                            (new TestValidatePin(receiver$, thePIN, card));
                        vniall_JohnnyCard$2$iter.advance();
                    }
                    vint$1$iter.advance();
                }
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the validatePin method. */
    protected static class TestValidatePin extends OneTest {
        /** The receiver */
        private niall.JohnnyMachine receiver$;
        /** Argument thePIN */
        private int thePIN;
        /** Argument card */
        private niall.JohnnyCard card;

        /** Initialize this instance. */
        public TestValidatePin(niall.JohnnyMachine receiver$, int thePIN, niall.JohnnyCard card) {
            super("validatePin"+ ":" + thePIN+ "," +(card==null? "null" :"{niall.JohnnyCard}"));
            this.receiver$ = receiver$;
            this.thePIN = thePIN;
            this.card = card;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.validatePin(thePIN, card);
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'validatePin' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            msg += "\n\tArgument thePIN: " + this.thePIN;
            msg += "\n\tArgument card: " + this.card;
            return msg;
        }
    }

    /** Add tests for the initCardSession method
     * to the overall test suite. */
    private void addTestSuiteFor$TestInitCardSession
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("initCardSession");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vniall_JohnnyMachineIter("initCardSession", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vniall_JohnnyMachineIter(\"initCardSession\", 0))");
            while (!receivers$iter.atEnd()) {
                final niall.JohnnyMachine receiver$
                    = (niall.JohnnyMachine) receivers$iter.get();
                methodTests$.addTest
                    (new TestInitCardSession(receiver$));
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the initCardSession method. */
    protected static class TestInitCardSession extends OneTest {
        /** The receiver */
        private niall.JohnnyMachine receiver$;

        /** Initialize this instance. */
        public TestInitCardSession(niall.JohnnyMachine receiver$) {
            super("initCardSession");
            this.receiver$ = receiver$;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.initCardSession();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'initCardSession' applied to";
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
