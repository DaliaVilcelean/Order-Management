package model;



public class Produs {
    protected int id;
    private String nume;
    private int cantitate;
    private int pret1buc;

    public Produs(){

    }
    public Produs(int id,String nume,int cantitate,int pret1buc){

        this.id=id;
        this.nume=nume;
        this.cantitate=cantitate;
        this.pret1buc=pret1buc;

    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getNume(){
        return nume;
    }
    public void setNume(String nume){
        this.nume=nume;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public int getCantitate() {
        return cantitate;
    }


    public int getPret1buc() {
        return pret1buc;
    }


    public void setPret1buc(int pret1buc) {
        this.pret1buc = pret1buc;
    }


    @Override
    public String toString() {
        return "Produs{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", cantitate=" + cantitate +
                ", pret1buc=" + pret1buc +
                '}';
    }
}
