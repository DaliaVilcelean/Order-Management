package bll.validators;

import model.Client;
import model.Orders;
import model.Produs;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FileOrder {

    private static PrintWriter writer;

    public static void createOrderFile(Orders newObj) {

        try{
            writer=new PrintWriter("Comanda nr. "+newObj.getId()+".txt","UTF-8");
            writer.println("------------------------------------------------------");
            writer.println("---------COMANDA NR. "+newObj.getId()+"--------------");
            writer.println();
            ClientBLL clientBLL= new ClientBLL();
            ProdusBLL produsBLL=new ProdusBLL();
            Client client=clientBLL.findClientById(newObj.getIdClient());
            Produs produs=produsBLL.findById(newObj.getIdProdus());


            writer.println("Nume Client: "+client.getNume());
            writer.println("Adresa Client: "+client.getAdresa());
            writer.println("E-Mail Client: "+client.getEmail());
            writer.println("Telefon Client: "+client.getTelefon());
            writer.println();
            writer.println("Nume Produs: "+produs.getNume());
            writer.println("Pretul pe bucata: "+produs.getPret1buc());
            writer.println("Cantitatea Cumparata: "+newObj.getCantitate());
            writer.println();
            writer.println("PRETUL TOTAL: "+newObj.getCantitate()*produs.getPret1buc());
            writer.println("DATA: "+newObj.getDate());

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
