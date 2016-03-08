package com.java_promise.tests.promise;

import com.java_promise.promise.Promise;
import com.java_promise.promise.RejectCallback;
import com.java_promise.promise.ResolveCallback;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Philip on 25/02/2016.
 */
public class Promise_Spec_2_2_Test {

    private Promise testObject;

    private int calls = 0;

    @Before
    public void Init() {

        testObject = null;

        calls = 0;
    }

    //  promise.then(onFulfilled, onRejected)

    //  2.2.1
    //  Both onFulfilled and onRejected are optional arguments:
    //  2.2.1.1 If onFulfilled is not a function, it must be ignored.
    //  2.2.1.2 If onRejected is not a function, it must be ignored.

    //  2.2.2
    //  If onFulfilled is a function:
    //  2.2.2.1 it must be called after promise is fulfilled, with promise’s value as its first argument.
    @Test
    public void onResolve_must_be_called_after_promise_is_fulfilled() {

        testObject = new Promise(new ResolveCallback() {
            @Override
            public void onResolved() {
                calls++;
            }
        }, null);

        testObject.resolve();

        assertEquals(1, calls);
    }

    //  2.2.2.2 it must not be called before promise is fulfilled.
    @Test
    public void onResolve_must_not_be_called_before_promise_is_fulfilled() {

        testObject = new Promise(new ResolveCallback() {
            @Override
            public void onResolved() {
                calls++;
            }
        }, null);

        assertEquals(0, calls);

        testObject.resolve();
    }

    //  2.2.2.3 it must not be called more than once.
    @Test
    public void onResolve_must_not_be_called_more_than_once() {

        testObject = new Promise(new ResolveCallback() {
            @Override
            public void onResolved() {
                calls++;
            }
        }, null);

        testObject.resolve();

        testObject.resolve();

        assertEquals(1, calls);
    }

    //  2.2.3
    //  If onRejected is a function,
    //  2.2.3.1 it must be called after promise is rejected, with promise’s reason as its first argument.
    @Test
    public void onRejected_must_be_called_after_promise_is_rejected_with_reason() {

        final Exception expectedException = new Exception("foo");

        testObject = new Promise(null, new RejectCallback() {
            @Override
            public void onRejected(Exception ex) {
                assertEquals(expectedException, ex);
                calls++;
            }
        });

        testObject.reject(expectedException);

        assertEquals(1, calls);
    }

    //  2.2.3.2 it must not be called before promise is rejected.
    @Test
    public void onRejected_must_not_be_called_before_promise_is_rejected() {

        testObject = new Promise(null, new RejectCallback() {
            @Override
            public void onRejected(Exception ex) {
                calls++;
            }
        });

        assertEquals(0, calls);
    }

    //  2.2.3.3 it must not be called more than once.
    @Test
    public void onRejected_must_not_be_called_more_than_once() {

        final Exception expectedException = new Exception("foo");

        testObject = new Promise(null, new RejectCallback() {
            @Override
            public void onRejected(Exception ex) {
                assertEquals(expectedException, ex);
                calls++;
            }
        });

        testObject.reject(expectedException);
        testObject.reject(expectedException);

        assertEquals(1, calls);
    }

    //  2.2.4
    //  onFulfilled or onRejected must not be called until the execution context stack contains only platform code. [3.1].

    //  2.2.5
    //  onFulfilled and onRejected must be called as functions (i.e. with no this value). [3.2]
    //  -- not applicable for Java

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
