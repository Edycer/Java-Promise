package com.java_promise.common;

/**
 * Created by Philip on 29/03/2016.
 */
public interface ActionablePromise<TypeT> {

    void resolve(TypeT result);

    void reject(Exception ex);
}
