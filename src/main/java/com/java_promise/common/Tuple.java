package com.java_promise.common;

/**
 * Created by Philip on 29/03/2016.
 */
public class Tuple<TypeA, TypeB> {

    public final TypeA A;

    public final TypeB B;

    public Tuple(TypeA a, TypeB b) {

        this.A = a;
        this.B = b;
    }
}
