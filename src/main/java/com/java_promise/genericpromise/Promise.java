package com.java_promise.genericpromise;

import com.java_promise.common.RejectCallback;
import com.java_promise.common.State;
import com.java_promise.common.ActionablePromise;
import com.java_promise.common.Tuple;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by Philip on 2/03/2016.
 */
public class Promise<TypeT>
        implements ActionablePromise<TypeT> {

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
    private List<Tuple<ResolveCallback<TypeT>, ActionablePromise<TypeT>>> resolveCallbacks = new ArrayList<>();

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

            for (Tuple<ResolveCallback<TypeT>, ActionablePromise<TypeT>> callback : resolveCallbacks) {

                try {

                    callback.A.onResolved(result);
                    callback.B.resolve(result);

                } catch (Exception ex) {

                    callback.B.reject(ex);
                }
            }
        }
    }

    /**
     * Sets the promise to rejected and actions registered catchers.
     * @param ex
     */
    public void reject(Exception ex) {

        if (State != State.Pending) {

            return;
        }

        State = State.Rejected;

        Reason = ex;

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
    public Promise<TypeT> then(ResolveCallback<TypeT> resolveCallback, RejectCallback rejectCallback) {

        Promise<TypeT> newPromise = new Promise<>();

        if (resolveCallback != null) {

            resolveCallbacks.add(new Tuple<ResolveCallback<TypeT>, ActionablePromise<TypeT>>(resolveCallback, newPromise));
        }

        if (rejectCallback != null) {

            rejectCallbacks.add(rejectCallback);
        }

        return newPromise;
    }

    /**
     * Registers a thenable with the promise.
     * @param resolveCallback
     */
    public Promise<TypeT> then(ResolveCallback<TypeT> resolveCallback) {

        return then(resolveCallback, null);
    }

    /**
     * Registers a catcher with the promise.
     * @param rejectCallback
     */
    public void handle(RejectCallback rejectCallback) {

        then(null, rejectCallback);
    }
}
