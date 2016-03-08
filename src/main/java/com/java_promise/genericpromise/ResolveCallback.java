package com.java_promise.genericpromise;

/**
 * Created by Philip on 8/03/2016.
 */
public interface ResolveCallback<TypeT> {

    //
    //  The function that is called when a promise is resolved.
    //
    void onResolved(TypeT result);
}
