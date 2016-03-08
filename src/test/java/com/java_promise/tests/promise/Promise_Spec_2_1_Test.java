package com.java_promise.tests.promise;

import com.java_promise.common.State;
import com.java_promise.promise.Promise;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Philip on 25/02/2016.
 */
public class Promise_Spec_2_1_Test {

    private Promise testObject;

    @Before
    public void Init() {

        testObject = new Promise();
    }

    //  2.1.1

    //  2.1.1.1
    @Test
    public void When_pending_a_promise_may_transition_to_the_fulfilled_state() {

        testObject.reject(new Exception());

        assertEquals(State.Rejected, testObject.State);
    }

    //  2.1.2.1
    @Test
    public void When_fulfilled_a_promise_must_not_transition_to_any_other_state() {

        testObject.resolve();

        testObject.reject(new Exception());

        assertEquals(State.Resolved, testObject.State);
    }

    //  2.1.2.2
    //  When fulfilled a promise must have a value which must not change.
    //  Not relevant for non-generic promises

    //  2.1.3.1
    @Test
    public void When_rejected_a_promise_must_not_transition_to_any_other_state() {

        testObject.reject(new Exception());

        testObject.resolve();

        assertEquals(State.Rejected, testObject.State);
    }

    //  2.1.3.2
    @Test
    public void When_rejected_a_promise_must_have_a_reason_which_must_not_change() {

        Exception testException = new Exception();

        testObject.reject(testException);

        testObject.resolve();

        assertEquals(testException, testObject.Reason);
    }
}
