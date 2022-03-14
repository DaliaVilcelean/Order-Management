package bll.validators;

import dao.OrdersDAO;
import model.Orders;

import java.util.ArrayList;
import java.util.List;

public class OrdersBLL extends AbstractBLL<OrdersBLL, Orders, OrdersDAO> {

    public Validator<Orders> validator;

    public OrdersBLL(){
        validator=new DateValidator<Orders>();

    }

    @Override
    protected List<Validator<Orders>> getValidators() {
        List<Validator<Orders>> valid;
        valid=new ArrayList<Validator<Orders>>();
        valid.add(new DateValidator<Orders>());
        return valid;
    }


}
