package bll.validators;

import gui.View;
import model.Client;


import javax.swing.*;
import java.util.logging.Level;
import java.util.regex.Pattern;

public class PhoneValidator<T> implements Validator<T> {

    private static final String PHONE_PATTERN="\\d{10}";


    @Override
    public boolean validate(T t) {
        try{

            Pattern pattern=Pattern.compile(PHONE_PATTERN,Pattern.CASE_INSENSITIVE);
            if(t.getClass().getSimpleName().equals(Client.class.getSimpleName())){
                if(!pattern.matcher(((Client) t).getTelefon()).matches()){
                    throw new IllegalArgumentException("Telefon invalid");
                }

            }else{
                throw new IllegalArgumentException("Clasa "+t.getClass().getName()+" nu are nevoie de un telefom");
            }

        }catch(IllegalArgumentException e){
            LOGGER.log(Level.WARNING,this.getClass().getName()+e.getMessage());
            View.showMessage("Telefon invalid ", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
}
