package com.noveogroup.tulupov.guestbook.util;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;


/**
 * Abstract factory method.
 *
 * @param <T> type class.
 * @param <P> parameter class.
 */
@Slf4j
public abstract class AbstractFactory<T, P> {
    private Class<? extends T> typeClass;
    private Class<P> parameterClass;
    private Map<P, T> map = new HashMap<>();

    protected AbstractFactory(final Class<? extends T> typeClass, final Class<P> parameterClass) {
        this.typeClass = typeClass;
        this.parameterClass = parameterClass;
    }

    public T get(final P parameter) {
        T object = map.get(parameter);

        if (object == null) {
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
