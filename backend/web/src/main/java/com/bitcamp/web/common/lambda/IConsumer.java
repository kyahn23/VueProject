package com.bitcamp.web.common.lambda;

@FunctionalInterface
public interface IConsumer {
    public abstract void accept(Object o);
}