package com.java_promise.genericpromise;

import com.java_promise.common.State;

/**
 * Created by Philip on 2/03/2016.
 */
public class Promise<TypeT> {

    //
    //  The current state of the promise.
    //
    public State State;

    //
    //  The result of the promise.
    //
    public TypeT Result;

    public Promise() {

        State = State.Pending;
    }

    //
    //  Sets the promise to resolved and actions registered thenables.
    //
    public void Resolve(TypeT result) {

        if (State != State.Pending) {

            return;
        }

        State = State.Resolved;

        Result = result;
    }

    //
    //  Sets the promise to rejected and actions registered catchers.
    //
    public void Reject(Exception e) {

        if (State != State.Pending) {

            return;
        }

        State = State.Rejected;
    }
}
