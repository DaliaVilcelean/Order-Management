package dao;

import model.Client;

import java.util.NoSuchElementException;

public class ClientDAO extends AbstractDAO<Client>{

    public int insert(Client c){
        return super.insert(c);
    }


}
