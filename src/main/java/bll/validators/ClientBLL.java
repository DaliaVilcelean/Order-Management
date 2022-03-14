package bll.validators;

import dao.ClientDAO;
import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL extends AbstractBLL<ClientBLL,Client,ClientDAO> {
    private ArrayList<Validator<Client>> valid;

    public ClientBLL(){
        valid=new ArrayList<Validator<Client>>();
        valid.add(new EmailValidator<>());
        valid.add(new PhoneValidator<>());

    }

    @Override
    protected List<Validator<Client>> getValidators() {
        List<Validator<Client>> valid;
        valid=new ArrayList<Validator<Client>>();
        valid.add(new EmailValidator<>());
        valid.add(new PhoneValidator<>());
        return valid;
    }

    public Client findClientById(int id){
        Client c=new ClientDAO().findById(id);

        if(c==null){
            throw new NoSuchElementException("Nu exist client cu acest id!");
        }
        return c;
    }
    public Client update(Client c){
        try{
            Client c1=new ClientDAO().update(c);
            return c1;
        }catch (Exception e){
            throw new NoSuchElementException("Nu exista");
        }
    }


    public List<Client> findAll(){
        List<Client> clienti=new ArrayList<Client>();
        try{
            clienti= (List<Client>) new ClientDAO().findAll();
        }catch (Exception exc){
            throw new NoSuchElementException("Nu exista clienti in baza de date");
        }
        return clienti;
    }

}
