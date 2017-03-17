package com.helpshift.exception;

/**
 * Created by gitanjali on 17/03/17.
 */
public class DuplicateDataException extends Exception
{
    @Override
    public String getMessage()
    {
        return "Duplicate data";
    }
}
