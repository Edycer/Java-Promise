package com.java_promise.tests.genericpromise;

import com.java_promise.genericpromise.Promise;
import com.java_promise.common.State;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Philip on 25/02/2016.
 */
public class Generic_Promise_Spec_2_1_Test {

    private Promise<Integer> testObject;

    @Before
    public void Init() {

        testObject = new Promise<>();
    }

    //
    //  2.1.1
    //

    //  2.1.1.1
    @Test
    public void When_pending_a_promise_may_transition_to_the_fulfilled_state() {

        testObject.resolve(1);

        assertEquals(State.Resolved, testObject.State);
    }

    //  2.1.1.2
    @Test
    public void When_pending_a_promise_may_transition_to_the_rejected_state() {

        testObject.reject(new Exception());

        assertEquals(State.Rejected, testObject.State);
    }

    //
    //  2.1.2
    //

    //  2.1.2.1
    @Test
    public void When_fulfilled_a_promise_must_not_transition_to_any_other_state() {

        testObject.resolve(1);

        testObject.reject(new Exception());

        assertEquals(State.Resolved, testObject.State);
    }

    //  2.1.2.2
    @Test
    public void When_fulfilled_a_promise_must_have_a_value_which_must_not_change() {

        testObject.resolve(1);

        testObject.resolve(5);

        assertEquals(1, testObject.Result.intValue());
    }

    //
    //  2.1.3
    //

    //  2.1.3.1
    @Test
    public void When_rejected_a_promise_must_not_transition_to_any_other_state() {

        testObject.reject(new Exception());

        testObject.resolve(1);

        assertEquals(State.Rejected, testObject.State);
    }

    //  2.1.3.2
    @Test
    public void When_rejected_a_promise_must_have_a_reason_which_must_not_change() {

        Exception testException = new Exception();

        testObject.reject(testException);

        testObject.resolve(1);

        assertEquals(testException, testObject.Reason);
    }
}
