package model;


public class Client  {

    private int id;
    private String nume;
    private String oras;
    private String adresa;
    private String email;
    private int varsta;
    private String telefon;

    public Client(){

    }
    public Client(int id,String nume,String oras,String adresa,String email,
                  int varsta, String telefon){
        this.id=id;
        this.nume=nume;
        this.oras=oras;
        this.adresa=adresa;
        this.email=email;
        this.varsta=varsta;
        this.telefon=telefon;
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

    public String getOras(){
        return oras;
    }
    public void setOras(String oras){
        this.oras=oras;
    }
    public String getAdresa(){
        return adresa;
    }
    public void setAdresa(String adresa){
        this.adresa=adresa;
    }
    public String getEmail(){
        return  email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public int getVarsta(){
        return varsta;
    }
    public void setVarsta(int varsta){
        this.varsta=varsta;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String toString(){
        return "Client [id="+id+", nume="+nume+", oras="+oras+", adresa="+adresa+", email="+email+", varsta="+varsta+", nr.Telefon="+telefon+"]";
    }
}
