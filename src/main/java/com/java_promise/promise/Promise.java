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

    public Promise() {

        State = State.Pending;
    }

    public void Resolve() {

        if (State != State.Pending) {
            return;
        }

        State = State.Resolved;
    }

    public void Reject(Exception ex) {

        if (State != State.Pending) {
            return;
        }

        State = State.Rejected;

        Reason = ex;
    }
}
