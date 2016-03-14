package com.java_promise.genericpromise;

import com.java_promise.common.RejectCallback;
import com.java_promise.common.State;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

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

    //
    //  The reason for a failure.
    //
    public Exception Reason;

    //
    //  Called when the promise is resolved.
    //
    private List<ResolveCallback<TypeT>> resolveCallbacks = new ArrayList<ResolveCallback<TypeT>>();

    //
    //  Called when the promise is rejected.
    //
    private List<RejectCallback> rejectCallbacks = new ArrayList<RejectCallback>();

    public Promise() {

        State = State.Pending;
    }

    //
    //  Sets the promise to resolved and actions registered thenables.
    //
    public void resolve(TypeT result) {

        if (State != State.Pending) {

            return;
        }

        State = State.Resolved;

        Result = result;

        if (resolveCallbacks.size() > 0) {

            for (ResolveCallback callback : resolveCallbacks) {

                callback.onResolved(Result);
            }
        }
    }

    //
    //  Sets the promise to rejected and actions registered catchers.
    //
    public void reject(Exception e) {

        if (State != State.Pending) {

            return;
        }

        State = State.Rejected;

        Reason = e;
    }

    //
    //  Registers a thenable and catcher.
    //
    public void then(ResolveCallback<TypeT> resolveCallback, RejectCallback rejectCallback) {

        if (resolveCallback != null) {

            resolveCallbacks.add(resolveCallback);
        }

        if (rejectCallback != null) {

            rejectCallbacks.add(rejectCallback);
        }
    }

    //
    //  Registers a thenable with the promise.
    //
    public void then(ResolveCallback<TypeT> resolveCallback) {

        if (resolveCallback != null) {

            resolveCallbacks.add(resolveCallback);
        }
    }

}
