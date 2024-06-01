package com.allenDigital.allenDigital.exceptions;

public class EntityNotFound extends Exception{

    private final String entitytName;

    public EntityNotFound(String entitytName) {
        this.entitytName = entitytName;
    }

}
