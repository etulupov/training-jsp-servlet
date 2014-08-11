package com.noveogroup.tulupov.guestbook.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class AbstractFactory<T, P> {
    private Class<? extends T> typeClass;
    private Class<P> parameterClass;
    private Map<P, T> map = new HashMap<>();

    protected AbstractFactory(Class<? extends T> typeClass, Class<P> parameterClass) {
        this.typeClass = typeClass;
        this.parameterClass = parameterClass;
    }

    public T get(P parameter) {
        T object = map.get(parameter);

        if(object == null) {
            synchronized (typeClass) {
                object = map.get(parameter);
                if (object == null) {
                    try {
                        object = typeClass.getConstructor(parameterClass).newInstance(parameter);
                    } catch (Exception e) {
                        log.error("Cannot instantiate class", e);
                    }
                    map.put(parameter, object);
                }
            }
        }

        return object;
    }
}
