package com.java_promise.promise;

import com.java_promise.common.State;

/**
 * Created by Philip on 23/02/2016.
 */
public class Promise {

    public State State;

    public Promise() {

        State = State.Pending;
    }

    public void Resolved() {


    }

    public void Reject(Exception ex) {

    }
}
