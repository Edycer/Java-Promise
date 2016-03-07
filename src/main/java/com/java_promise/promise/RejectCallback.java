package com.java_promise.promise;

/**
 * Created by rory on 8/03/16.
 */
public interface RejectCallback {
    /**
     * Invoked when the promise is rejected.
     * @param ex Reason for rejection.
     */
    void onReject(Exception ex);
}
