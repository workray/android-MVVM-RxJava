package com.mobdev.android_mvvm.networkplatform.entities.encodable;

/**
 * Created by mobdev125 on 2/19/18.
 */

public abstract class Encodable<T extends Encodable.DomainConvertibleType> {
    public abstract class Identifiable {
        private String uid;
        public abstract String getUid();
    }

    public abstract class DomainConvertibleType<T extends Identifiable> {
        public DomainConvertibleType(T domain) {

        }
    }

    private T encoder;
    public abstract T getEncoder();
}
