package com.java_promise.genericpromise;

import com.java_promise.common.RejectCallback;
import com.java_promise.common.State;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by Philip on 2/03/2016.
 */
public class Promise<TypeT> {

    /**
     * The current state of the promise.
     */
    public State State;

    /**
     * The result of the promise.
     */
    public TypeT Result;

    /**
     * The reason for a failure.
     */
    public Exception Reason;

    /**
     * Called when the promise is resolved.
     */
    private List<ResolveCallback<TypeT>> resolveCallbacks = new ArrayList<>();

    /**
     * Called when the promise is rejected.
     */
    private List<RejectCallback> rejectCallbacks = new ArrayList<>();

    public Promise() {

        State = State.Pending;
    }

    /**
     * Sets the promise to resolved and actions registered thenables.
     * @param result
     */
    public void resolve(TypeT result) {

        if (State != State.Pending) {

            return;
        }

        State = State.Resolved;

        Result = result;

        if (resolveCallbacks.size() > 0) {

            for (ResolveCallback<TypeT> callback : resolveCallbacks) {

                callback.onResolved(Result);
            }
        }
    }

    /**
     * Sets the promise to rejected and actions registered catchers.
     * @param exception
     */
    public void reject(Exception exception) {

        if (State != State.Pending) {

            return;
        }

        State = State.Rejected;

        Reason = exception;

        if (rejectCallbacks.size() > 0) {

            for (RejectCallback callback : rejectCallbacks) {

                callback.onRejected(Reason);
            }
        }
    }

    /**
     * Registers a thenable and catcher.
     * @param resolveCallback
     * @param rejectCallback
     */
    public void then(ResolveCallback<TypeT> resolveCallback, RejectCallback rejectCallback) {

        if (resolveCallback != null) {

            resolveCallbacks.add(resolveCallback);
        }

        if (rejectCallback != null) {

            rejectCallbacks.add(rejectCallback);
        }
    }

    /**
     * Registers a thenable with the promise.
     * @param resolveCallback
     */
    public void then(ResolveCallback<TypeT> resolveCallback) {

        if (resolveCallback != null) {

            resolveCallbacks.add(resolveCallback);
        }
    }

    /**
     * Registers a catcher with the promise.
     * @param rejectCallback
     */
    public void handle(RejectCallback rejectCallback) {

        if (rejectCallback != null) {

            rejectCallbacks.add(rejectCallback);
        }
    }
}
