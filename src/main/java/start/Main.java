package start;

import gui.Controller;
import gui.View;

import java.sql.SQLException;
import java.util.logging.Logger;

public class Main {

    protected static final Logger LOGGER=Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws SQLException{
        View view=new View();
     //   view.addFrame();
        Controller controller;
        controller=new Controller(view);

    }

}
