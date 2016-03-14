package com.java_promise.common;

/**
 * Created by rory on 8/03/16.
 */
public interface RejectCallback {
    /**
     * Invoked when the promise is rejected.
     * @param ex Reason for rejection.
     */
    void onRejected(Exception ex);
}
