package gui;

import bll.validators.*;
import dao.AbstractDAO;
import dao.ClientDAO;

import dao.OrdersDAO;
import dao.ProduseDAO;
import model.Client;
import model.Orders;
import model.Produs;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class Controller {

    protected static final Logger LOGGER=Logger.getLogger(Controller.class.getName());
    private View view;



    public Controller(View view){
        this.view=view;
        this.view.addClientButtonListener(new ClientListener());
        this.view.addProductButtonListener(new ProdusListener());
        this.view.addComenziButtonListener(new OrderListener());

        this.view.addDeleteClientButtonListener(new DeleteClientListener());
        this.view.addInsertClientButtonListener(new InsertClientListener());
        this.view.addDeleteProdusButtonListener(new DeleteProdusListener());
        this.view.addInsertProdusButtonListener(new InsertProdusListener());
        this.view.addUpdateClientButtonListener(new UpdateClientListener());
        this.view.addUpdateProdusButtonListener(new UpdateProdusListener());
        this.view.addAddOrderButtonListener(new AddOrderListener());


       // this.view.addFrame();
    }


    class AddOrderListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String[] orderFields=view.dateFields(Orders.class);
            OrdersBLL orders=new OrdersBLL();
            ProdusBLL produs=new ProdusBLL();

            try{

                Orders ord=new Orders(Integer.parseInt(orderFields[0]),Integer.parseInt(orderFields[1]),
                        Integer.parseInt(orderFields[2]),Integer.parseInt(orderFields[3]),
                     orderFields[4]  );
                Produs p=produs.findById(Integer.parseInt(orderFields[2]));
                if(p.getCantitate()>=ord.getCantitate()){
                    orders.insert(ord);
                    p.setCantitate(p.getCantitate()-ord.getCantitate());
                    produs.update(p);

                    FileOrder.createOrderFile(ord);
                }else{
                    JOptionPane.showMessageDialog(null,"Nu Exista Destule\n" +
                            "Produse pe Stoc");
                    throw new Exception();
                }
                view.genericTableFiller(new OrdersDAO(),view.tabelOrders,Orders.class);
                view.tabelOrders.repaint();
                view.tabelOrders.revalidate();
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }
    }


    class ClientListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            view.clientFrame.setVisible(true);
            view.genericTableFiller(new ClientDAO(),view.tabelClient, Client.class);

        }
    }

    class ProdusListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            view.produseFrame.setVisible(true);
            view.genericTableFiller(new ProduseDAO(), view.tabelProdus, Produs.class);

        }
    }


    class OrderListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

          view.orderFrame.setVisible(true);
          view.genericTableFiller(new OrdersDAO(),view.tabelOrders,Orders.class);

        }
    }
    class UpdateClientListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {


            String[] fields=view.dateFields(Client.class);
            ClientBLL clientBLL=new ClientBLL();
            try{
                Client client=new Client(Integer.parseInt(fields[0]),fields[1],fields[2],
                        fields[3],fields[4],Integer.parseInt(fields[5]),
                        fields[6]);
                clientBLL.update(client);
                view.genericTableFiller(new ClientDAO(),view.tabelClient,Client.class);
                view.tabelClient.repaint();
                view.tabelClient.revalidate();

            }catch(Exception exception){

                JOptionPane.showMessageDialog(null,"EROARE \n La " +
                        "update client");
            }

        }
    }
    class UpdateProdusListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String[] fields=view.dateFields(Produs.class);
            ProdusBLL produsBLL=new ProdusBLL();
            try{
                Produs produs=new Produs(Integer.parseInt(fields[0]),fields[1],
                        Integer.parseInt(fields[2]),Integer.parseInt(fields[3]));
                produsBLL.update(produs);
                view.genericTableFiller(new ProduseDAO(),view.tabelProdus,Produs.class);
                view.tabelProdus.repaint();
                view.tabelProdus.revalidate();

            }catch(Exception exception){

                JOptionPane.showMessageDialog(null,"EROARE \n La " +
                        "update produs");
            }

        }
    }
    class InsertClientListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String[] fields=view.dateFields(Client.class);
            ClientBLL clientBLL=new ClientBLL();
            try{
                Client client=new Client(Integer.parseInt(fields[0]),fields[1],fields[2],
                        fields[3],fields[4],Integer.parseInt(fields[5]),
                        fields[6]);
                clientBLL.insert(client);
                view.genericTableFiller(new ClientDAO(),view.tabelClient,Client.class);
                view.tabelClient.repaint();
                view.tabelClient.revalidate();

            }catch(Exception exception){

                JOptionPane.showMessageDialog(null,"EROARE \n La " +
                        "inserare de client");
            }

        }
    }
    class InsertProdusListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String[] fields=view.dateFields(Produs.class);
            ProdusBLL produsBLL=new ProdusBLL();
            try{
                Produs produs=new Produs(Integer.parseInt(fields[0]),fields[1],
                        Integer.parseInt(fields[2]),Integer.parseInt(fields[3]));
                produsBLL.insert(produs);
                view.genericTableFiller(new ProduseDAO(),view.tabelProdus,Produs.class);
                view.tabelProdus.repaint();
                view.tabelProdus.revalidate();

            }catch(Exception exception){

                JOptionPane.showMessageDialog(null,"EROARE \n La " +
                        "inserare de produs");
            }

        }
    }
    class DeleteClientListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String[] fields=view.dateFields(Client.class);
            ClientBLL clientBLL=new ClientBLL();
            try{
                int id=clientBLL.delete(Integer.parseInt(fields[0]));
                view.genericTableFiller(new ClientDAO(),view.tabelClient,Client.class);
                view.tabelClient.repaint();
                view.tabelClient.revalidate();

            }catch(Exception exception){

                JOptionPane.showMessageDialog(null,"EROARE \n La " +
                        "stergere client");
            }

        }
    }
class DeleteProdusListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {

        String[] fields=view.dateFields(Produs.class);
        ProdusBLL produsBLL=new ProdusBLL();
        try{
            int id=produsBLL.delete(Integer.parseInt(fields[0]));
            view.genericTableFiller(new ProduseDAO(),view.tabelProdus,Produs.class);
            view.tabelProdus.repaint();
            view.tabelProdus.revalidate();

        }catch(Exception exception){

            JOptionPane.showMessageDialog(null,"EROARE \n La " +
                    "stergere produs");
        }

    }
}


}
