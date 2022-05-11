package fr.eni.exception;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends RuntimeException{

    private List<String> errors;

    public BusinessException() {
        super();
        errors = new ArrayList<>();
    }

    public void addError(String msg) {
        errors.add(msg);
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean isError() {
        return errors.size() > 0;
    }
}
