package com.java_promise.promise;

import com.java_promise.common.State;

/**
 * Created by Philip on 23/02/2016.
 */
public class Promise {

    /**
     * The current state of the promise.
     */
    public State State;

    /**
     * The reason for a failure.
     */
    public Exception Reason;

    private ResolveCallback resolveCallback;

    private RejectCallback rejectCallback;

    public Promise() {

        State = State.Pending;
    }

    /**
     *
     * @param onFulfilled
     * @param onRejected
     */
    public Promise(ResolveCallback onFulfilled, RejectCallback onRejected) {

        this();

        resolveCallback = onFulfilled;
        rejectCallback = onRejected;
    }

    /**
     * Sets the promise to resolved and actions registered thenables.
     */
    public void resolve() {

        if (State != State.Pending) {
            return;
        }

        if (resolveCallback != null) {
            resolveCallback.onResolved();
        }

        State = State.Resolved;
    }

    /**
     * Sets the promise to rejected and actions registered catchers.
     * @param ex Exception to be passed to catchers.
     */
    public void reject(Exception ex) {

        if (State != State.Pending) {
            return;
        }

        if (rejectCallback != null) {
            rejectCallback.onRejected(ex);
        }

        State = State.Rejected;

        Reason = ex;
    }
}
