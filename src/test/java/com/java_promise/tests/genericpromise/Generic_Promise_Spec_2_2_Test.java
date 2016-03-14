package com.java_promise.tests.genericpromise;

import com.java_promise.common.RejectCallback;
import com.java_promise.genericpromise.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;


/**
 * Created by Philip on 25/02/2016.
 */
public class Generic_Promise_Spec_2_2_Test {

    private Promise testObject;

    @Before
    public void Init() {

        testObject = new Promise<Integer>();
    }

    //  promise.then(onFulfilled, onRejected)

    //
    //  2.2.1
    //  Both onFulfilled and onRejected are optional arguments:
    //
    
    //  2.2.1.1
    @Test
    public void If_onFulfilled_is_not_a_function_it_must_be_ignored() {

        final List<Integer> promiseResults = new ArrayList<Integer>();

        testObject.then(null, new RejectCallback() {

            @Override
            public void onRejected(Exception ex) {
            }
        });

        testObject.then(new ResolveCallback<Integer>() {

            @Override
            public void onResolved(Integer result) {

                promiseResults.add(result);
            }
        });

        testObject.resolve(1);

        assertEquals(1, promiseResults.size());
    }

    //  2.2.1.2 If onRejected is not a function, it must be ignored.

    //
    //  2.2.2
    //  If onFulfilled is a function:
    //
    
    //  2.2.2.1
    @Test
    public void it_must_be_called_after_promise_is_fulfilled_with_promises_value_as_its_first_argument() {

        final List<Integer> promiseResults = new ArrayList<Integer>();

        testObject.then(new ResolveCallback<Integer>() {

            @Override
            public void onResolved(Integer result) {

                promiseResults.add(result);
            }
        });

        testObject.resolve(1);

        assertEquals(1, promiseResults.size());
        assertEquals(1, (int)promiseResults.get(0));
    }

    //  2.2.2.2 it must not be called before promise is fulfilled.
    
    //  2.2.2.3 it must not be called more than once.

    //  2.2.3
    //  If onRejected is a function,
    //  2.2.3.1 it must be called after promise is rejected, with promise’s reason as its first argument.
    //  2.2.3.2 it must not be called before promise is rejected.
    //  2.2.3.3 it must not be called more than once.

    //  2.2.4
    //  onFulfilled or onRejected must not be called until the execution context stack contains only platform code. [3.1].

    //  2.2.5
    //  onFulfilled and onRejected must be called as functions (i.e. with no this value). [3.2]

    //  2.2.6
    //  then may be called multiple times on the same promise.
    //  2.2.6.1 If/when promise is fulfilled, all respective onFulfilled callbacks must execute in the order of their originating calls to then.
    //  2.2.6.2 If/when promise is rejected, all respective onRejected callbacks must execute in the order of their originating calls to then.

    //  2.2.7
    //  then must return a promise [3.3].
    //  promise2 = promise1.then(onFulfilled, onRejected);
    //  2.2.7.1 If either onFulfilled or onRejected returns a value x, run the Promise Resolution Procedure [[resolve]](promise2, x).
    //  2.2.7.2 If either onFulfilled or onRejected throws an exception e, promise2 must be rejected with e as the reason.
    //  2.2.7.3 If onFulfilled is not a function and promise1 is fulfilled, promise2 must be fulfilled with the same value as promise1.
    //  2.2.7.4 If onRejected is not a function and promise1 is rejected, promise2 must be rejected with the same reason as promise1.

}
