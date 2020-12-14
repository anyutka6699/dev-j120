package ru.avalon.java_pp.dev_j120;
import ru.avalon.java_pp.dev_j120.controllers.OrdersController;
import ru.avalon.java_pp.dev_j120.io.*;


public class Main {
    public static void main(String[] args) {
        OrdersController controller = new OrdersController();
        
        MainFrame f = new MainFrame(controller);
        f.setVisible(true);
    }
}
