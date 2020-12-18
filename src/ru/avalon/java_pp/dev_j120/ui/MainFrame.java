package ru.avalon.java_pp.dev_j120.ui;

import ru.avalon.java_pp.dev_j120.controllers.OrdersController;
import ru.avalon.java_pp.dev_j120.controllers.ProductController;
import ru.avalon.java_pp.dev_j120.models.Order;
import ru.avalon.java_pp.dev_j120.models.OrderPosition;
import ru.avalon.java_pp.dev_j120.uimodels.*;
import ru.avalon.java_pp.dev_j120.io.OrdersStorage;
import ru.avalon.java_pp.dev_j120.io.ProductsStorage;
import ru.avalon.java_pp.dev_j120.models.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MainFrame extends JFrame {
    private ProductController pc = new ProductController();
    private OrdersController oc = new OrdersController();
    private OrdersTableModel ordersTableModel;
    private JTable mainTable;

    public MainFrame(OrdersController controller) throws IOException, ClassNotFoundException {
        super("Учет заказов");

        setBounds(300, 200, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();
        this.ordersTableModel = new OrdersTableModel(oc);
        mainTable = new JTable(this.ordersTableModel);
        c.add(mainTable.getTableHeader(), BorderLayout.NORTH);
        c.add(new JScrollPane(mainTable), BorderLayout.CENTER);

        JMenuBar mb = new JMenuBar();
        JMenu m = new JMenu("Order operations");
        JMenuItem mi;

        mi = new JMenuItem("Добавить новый заказ");

        mi.addActionListener(this::addNewOrder);
        m.add(mi);

        mi = new JMenuItem("Изменить заказ");

        m.add(mi);

        mi = new JMenuItem("Удалить заказ");

        m.add(mi);


        mb.add(m);
        setJMenuBar(mb);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    pc.save();
                    oc.save();
                } catch (IOException exception) {
                    throw new RuntimeException("Не удалось сохранить файл");
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    private void addNewOrder(ActionEvent ee) {
        OrderDialog ano = new OrderDialog(this,pc);
        ano.setLocationRelativeTo(this);
        ano.setVisible(true);
        if (ano.isModalResult()) {
            Date date1 = ano.getDate1();
            String fio = ano.getFio();
            Integer phone = ano.getPhone();
            String address = ano.getAddress();
            Integer percent = ano.getPercent();
            Date date2 = ano.getDate2();
            List<OrderPosition> orderPositionList = ano.getOrderPositionList();
            try {
                ordersTableModel.addOrders(new Date(), fio, phone, address,percent, Order.Status.PREPARING, date2, orderPositionList);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(),
                        "Error adding Order", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

