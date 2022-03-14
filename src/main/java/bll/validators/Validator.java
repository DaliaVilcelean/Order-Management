package bll.validators;

import java.util.logging.Logger;

public interface Validator<T>{

    static final Logger LOGGER=Logger.getLogger(Validator.class.getName());

    public boolean validate(T t);

}
