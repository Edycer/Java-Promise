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

        if (onFulfilled != null) {
            resolveCallback = onFulfilled;
        }
    }

    /**
     * Sets the promise to resolved and actions registered thenables.
     */
    public void Resolve() {

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
    public void Reject(Exception ex) {

        if (State != State.Pending) {
            return;
        }

        State = State.Rejected;

        Reason = ex;
    }
}
