package com.java_promise.genericpromise;

import com.java_promise.common.RejectCallback;
import com.java_promise.common.State;
import com.java_promise.common.ActionablePromise;
import com.java_promise.common.Actionable;

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
     * Called when the promise is resolved or rejected.
     */
    private List<Actionable<TypeT>> actionables = new ArrayList<>();

    public Promise() {

        State = State.Pending;
    }

    /**
     * Registers a new actionable.
     * @param resolveCallback
     * @param rejectCallback
     * @param promise
     */
    private void registerActionables(
            ResolveCallback<TypeT> resolveCallback,
            RejectCallback rejectCallback,
            ActionablePromise promise) {

        actionables.add(new Actionable(resolveCallback, rejectCallback, promise));
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

        if (actionables.size() > 0) {

            for (Actionable<TypeT> actionable : actionables) {

                try {

                    if (actionable.ResolveCallback != null) {

                        actionable.ResolveCallback.onResolved(result);
                    }

                    actionable.ActionablePromise.resolve(result);

                } catch (Exception ex) {

                    actionable.ActionablePromise.reject(ex);
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

        if (actionables.size() > 0) {

            for (Actionable<TypeT> actionable : actionables) {

                if (actionable.RejectCallback!= null) {

                    try {

                        actionable.RejectCallback.onRejected(ex);

                    } catch (Exception exception) {

                        // todo: I need to use the equivalent of a inner exception.
                        actionable.ActionablePromise.reject(exception);
                    }
                }

                if (actionable.ActionablePromise != null) {

                    actionable.ActionablePromise.reject(ex);
                }
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

        registerActionables(resolveCallback, rejectCallback, newPromise);

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

        registerActionables(null, rejectCallback, null);
    }
}
