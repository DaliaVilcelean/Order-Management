package bll.validators;

import dao.ProduseDAO;
import model.Produs;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProdusBLL extends AbstractBLL<ProdusBLL, Produs, ProduseDAO>{

    public Produs findById(int id){
        Produs produs=new ProduseDAO().findById(id);

       if(produs==null){
            throw  new NoSuchElementException("Nu exista produs cu acest id!");
        }
        return produs;
    }

    public List<Produs> findAll(){
        try{
            List<Produs> produs= (List<Produs>) new ProduseDAO().findAll();
            return produs;
        }catch (Exception exc){
            throw new NoSuchElementException("Nu exista roduse in baza de date");
        }
    }

    public Produs update(Produs p){
        try{
            Produs p1=new ProduseDAO().update(p);
            return p1;
        }catch (Exception e){
            throw new NoSuchElementException("Nu s a gasit");
        }
    }

    @Override
    protected List<Validator<Produs>> getValidators() {
        List<Validator<Produs>> valid;
        valid=new ArrayList<Validator<Produs>>();
        return valid;
    }
}
