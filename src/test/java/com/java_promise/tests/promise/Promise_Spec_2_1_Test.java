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

        testObject.Resolved();

        assertEquals(State.Resolved, testObject.State);
    }

    //  2.1.1.1
    @Test
    public void When_pending_a_promise_mat_transation_to_the_fulfilled_state() {

        testObject.Reject(new Exception());

        assertEquals(State.Rejected, testObject.State);
    }

    //  2.1.2
    //  2.1.2.1 When fulfilled a promise must not transition to any other state.
    //  2.1.2.2 When fulfilled a promise must have a value which must not change.

    //  2.1.3
    //  2.1.3.1 When rejected a promise must not transition to any other state.
    //  2.1.3.2 When rejected a promise must have a reason, which must not change.
}
