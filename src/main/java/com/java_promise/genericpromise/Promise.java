package com.java_promise.genericpromise;

import com.java_promise.common.State;

/**
 * Created by Philip on 2/03/2016.
 */
public class Promise<TypeT> {

    public State State;

    public Promise() {

        State = State.Pending;
    }

    //
    // Sets the promise to resolved and actions registered thenables.
    //
    public void Resolve(TypeT result) {

        State = State.Resolved;
    }


    public void Reject(Exception e) {
    }
}
