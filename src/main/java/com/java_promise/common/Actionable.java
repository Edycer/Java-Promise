package com.java_promise.common;

import com.java_promise.genericpromise.ResolveCallback;

/**
 * Created by Philip on 29/03/2016.
 */
public class Actionable<TypeT> {

    public final ResolveCallback<TypeT> ResolveCallback;

    public final RejectCallback RejectCallback;

    public final ActionablePromise<TypeT> ActionablePromise;

    public Actionable(
            ResolveCallback<TypeT> resolveCallback,
            RejectCallback rejectCallback,
            ActionablePromise actionablePromise) {

        this.ResolveCallback = resolveCallback;
        this.RejectCallback = rejectCallback;
        this.ActionablePromise = actionablePromise;
    }
}
