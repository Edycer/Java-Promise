package com.java_promise.tests.promise;

import com.java_promise.genericpromise.Promise;
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

        //testObject.Resolved();
    }

    //  2.1.1.1 When pending a promise may transition to the rejected state.

    //  2.1.2
    //  2.1.2.1 When fulfilled a promise must not transition to any other state.
    //  2.1.2.2 When fulfilled a promise must have a value which must not change.

    //  2.1.3
    //  2.1.3.1 When rejected a promise must not transition to any other state.
    //  2.1.3.2 When rejected a promise must have a reason, which must not change.
}
