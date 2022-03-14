package gui;

import dao.AbstractDAO;
import model.Client;
import model.Orders;
import model.Produs;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class View extends JFrame  {

    private static final long serialVersionUID=1L;
    private Container content;
    private JPanel panel;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;


    public JFrame orderFrame=new JFrame();
    public JFrame clientFrame=new JFrame();
   public JFrame produseFrame=new JFrame();
    private JFrame princFrame=new JFrame();
    public JTable tabelClient=new JTable();
    public JTable tabelProdus=new JTable();
    public JTable tabelOrders=new JTable();
    private JButton btnClient=new JButton("Clienti");
    private JButton btnProdus=new JButton("Produse");
    private JButton btnComenzi=new JButton("Comenzi");
    private JButton btnUpdateClient=new JButton("Update");
    private JButton btnUpdateProdus=new JButton("Update");
    private JButton btnInsertClient=new JButton("Insert");
    private JButton btnInsertProdus=new JButton("Insert");
    private JButton btnDeleteClient=new JButton("Delete");
    private JButton btnDeleteprodus=new JButton("Delete");
    private JButton btnAddOrder=new JButton("Add a new order");
    JTextField[] clientText;
    JTextField[] produsText;
    JTextField[] orderText;

    private ArrayList<Integer> rowsModified= new ArrayList<Integer>();

    public View(){

        panel=new JPanel();
        panel2=new JPanel();
        panel3=new JPanel();
        panel4=new JPanel();

        panel2.add(btnClient);
        panel2.add(btnProdus);
        panel3.add(btnComenzi);


        panel4.add(panel);
        panel4.add(panel2);
        panel4.add(panel3);
        panel4.setLayout(new BoxLayout(panel4,BoxLayout.Y_AXIS));

        addOrderTable();
        addClientTable();
        addProduseTable();
        princFrame.add(panel4);
        princFrame.setTitle("Alegeti tabelul in care doriti sa intrati:");
        princFrame.setSize(500,400);
        princFrame.setVisible(true);


    }

    public void addFrame(){
        panel.removeAll();
        JScrollPane scrollPane=new JScrollPane();
        panel.add(scrollPane);
        content.add(panel,BorderLayout.CENTER);
        this.setSize(400,500);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setName("ORDER MANAGEMENT SYSTEM");
        this.setVisible(true);
    }

    void genericTableFiller(AbstractDAO abs,JTable table,Class c){

        DefaultTableModel tableModel=new DefaultTableModel();
        tableModel.setColumnCount(c.getDeclaredFields().length);
        String[] cols=new String[c.getDeclaredFields().length];
        int i=0;
        for(Field field:c.getDeclaredFields()){
            cols[i++]=field.getName();
        }
        tableModel.setColumnIdentifiers(cols);
        int j=0;
        abs.findAll();

        while(abs.date[j]!=null){
            tableModel.addRow(abs.date[j]);
            j++;
        }
        table.setModel(tableModel);


    }

    JPanel cols(Class c){
        JPanel result=new JPanel();

        switch (c.getName()){
            case "model.Client":
                clientText=new JTextField[c.getDeclaredFields().length];
                break;
            case "model.Produs":
                produsText=new JTextField[c.getDeclaredFields().length];
                break;
            case "model.Orders":
                orderText=new JTextField[c.getDeclaredFields().length];
                break;
        }
        int i=0;

        result.setLayout(new BoxLayout(result,BoxLayout.Y_AXIS));
        for(Field field:c.getDeclaredFields()){
            JPanel p=new JPanel();
            JLabel  label=new JLabel(field.getName());
            p.add(label);

            switch (c.getName()){
                case "model.Client":
                    clientText[i]=new JTextField(10);
                    p.add(clientText[i]);
                    break;
                case "model.Produs":
                    produsText[i]=new JTextField(10);
                    p.add(produsText[i]);
                    break;
                case "model.Orders":
                    orderText[i]=new JTextField(10);
                    p.add(orderText[i]);
                    break;
            }
            i++;
            result.add(p);
        }
        return result;
    }

    String[] dateFields(Class c){
        String[] fields=new String[c.getDeclaredFields().length];
        for(int i=0;i<c.getDeclaredFields().length;i++){
            switch (c.getName()){
                case "model.Client":
                    fields[i]=clientText[i].getText();
                    break;
                case "model.Produs":
                    fields[i]=produsText[i].getText();
                    break;
                case "model.Orders":
                    fields[i]=orderText[i].getText();
                    break;
            }
        }
        return fields;
    }

    void genericFrame(JTable tabel,JButton insert,JButton delete,
                      JButton update,JFrame frame,Class c){

        JPanel panel1=new JPanel();
        JPanel panel2=new JPanel();
        JPanel panel3=new JPanel();
        JScrollPane scroll=new JScrollPane(tabel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tabel.setRowHeight(20);
        JPanel panel4=new JPanel();

        panel1.add(scroll);

        if(insert!=null)
        panel2.add(insert);
        if(delete!=null)
         panel3.add(delete);
        if(update!=null)
        panel4.add(update);

        JPanel panel5=new JPanel();
        panel5.add(panel2);
        panel5.add(panel3);
        panel5.add(panel4);
        panel5.setLayout(new BoxLayout(panel5,BoxLayout.Y_AXIS));

        JPanel panel6=new JPanel();
        panel6.add(cols(c));
        panel6.add(panel5);
        panel6.setLayout(new BoxLayout(panel6,BoxLayout.X_AXIS));

        JPanel panel7=new JPanel();
        panel7.add(panel1);
        panel7.add(panel6);
        panel7.setLayout(new BoxLayout(panel7,BoxLayout.Y_AXIS));

        frame.add(panel7);
        frame.setSize(600,700);
        frame.setVisible(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    void addOrderTable(){

        orderFrame.setTitle("Aici realizati o comanda");
        genericFrame(tabelOrders,btnAddOrder,null,null,
                orderFrame, Orders.class);

    }
    void addClientTable(){

        clientFrame.setTitle("Clienti");
        genericFrame(tabelClient,btnInsertClient,btnDeleteClient,btnUpdateClient,
                clientFrame, Client.class);

    }
    void addProduseTable(){

        produseFrame.setTitle("Produse");
        genericFrame(tabelProdus,btnInsertProdus,btnDeleteprodus,btnUpdateProdus,
                produseFrame, Produs.class);

    }


    public static void showMessage(String message,int warningMsg){
        switch(warningMsg){
            case JOptionPane.WARNING_MESSAGE:
                JOptionPane.showMessageDialog(null,message,"Mesaj",JOptionPane.WARNING_MESSAGE);
                break;
            case JOptionPane.ERROR_MESSAGE:
                JOptionPane.showMessageDialog(null,message,"Mesaj",JOptionPane.ERROR_MESSAGE);
                break;
            case JOptionPane.INFORMATION_MESSAGE:
                JOptionPane.showMessageDialog(null,message,"Mesaj",JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(null,message,"Mesaj",JOptionPane.INFORMATION_MESSAGE);
        }
    }




    public void addClientButtonListener(ActionListener mal){
        btnClient.addActionListener(mal);
    }
    public void addProductButtonListener(ActionListener mal){
        btnProdus.addActionListener(mal);
    }
    public void addComenziButtonListener(ActionListener mal){
        btnComenzi.addActionListener(mal);
    }

    public void addUpdateClientButtonListener(ActionListener mal){
        btnUpdateClient.addActionListener(mal);
    }
    public void addInsertClientButtonListener(ActionListener mal){
        btnInsertClient.addActionListener(mal);
    }
    public void addDeleteClientButtonListener(ActionListener mal){
        btnDeleteClient.addActionListener(mal);
    }
    public void addUpdateProdusButtonListener(ActionListener mal){
        btnUpdateProdus.addActionListener(mal);
    }
    public void addInsertProdusButtonListener(ActionListener mal){
        btnInsertProdus.addActionListener(mal);
    }
    public void addDeleteProdusButtonListener(ActionListener mal){
        btnDeleteprodus.addActionListener(mal);
    }
    public void addAddOrderButtonListener(ActionListener mal){
        btnAddOrder.addActionListener(mal);
    }

}
