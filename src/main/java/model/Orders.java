package model;

import java.sql.Date;



public class Orders  {

    private int id;
    private int idClient;
    private int idProdus;
    private int cantitate;
    private String data;


    public Orders(){

    }
    public Orders(int id,int idClient,int idProdus,int cantitate,int idTransport,String data){
       this.id=id;
       this.idClient=idClient;
       this.idProdus=idProdus;
       this.cantitate=cantitate;
       this.data=data;

    }

    public Orders(int id, int idClient, int idProdus, int cantitate, String data) {
        this.id=id;
        this.idClient=idClient;
        this.idProdus=idProdus;
        this.cantitate=cantitate;
        this.data= data;
    }


    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public String getDate() {
        return data;
    }

    public int getCantitate() {
        return cantitate;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getIdProdus() {
        return idProdus;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public void setDate(String data) {
        this.data = data;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }




}
