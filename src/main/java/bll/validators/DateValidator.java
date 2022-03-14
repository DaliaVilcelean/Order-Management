package bll.validators;

import gui.View;
import model.Orders;

import javax.swing.*;
import java.util.logging.Level;
import java.util.regex.Pattern;

public class DateValidator<T> implements Validator<T> {

    private static final String DATE_PATTERN="^\\d{4}-\\d{2}-\\d{2}$";

    @Override
    public boolean validate(T t) {

        try{
            Pattern pattern=Pattern.compile(DATE_PATTERN);
            if(t.getClass().getSimpleName().equals(Orders.class.getSimpleName())){
                if(!pattern.matcher(((Orders) t).getDate().toString()).matches()){
                    throw new IllegalArgumentException("Data nevalida");
                }
            }else{
                throw new IllegalArgumentException("Clasa "+t.getClass().getName()+" nu are nevoie de o data");
            }
        }catch(IllegalArgumentException e){
            LOGGER.log(Level.WARNING,this.getClass().getName()+e.getMessage());
            View.showMessage("Data invalida ", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;

    }


    public static boolean validateString(String validate){
        Pattern pattern=Pattern.compile(DATE_PATTERN);
        try{
            if(!pattern.matcher(validate).matches()){
                throw new IllegalArgumentException("DATA invalida");
            }
        }catch(IllegalArgumentException e){
            LOGGER.log(Level.WARNING,"Date validator "+e.getMessage());
            View.showMessage("Data invalida ", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }




}
