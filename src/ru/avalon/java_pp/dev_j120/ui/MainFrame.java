package ru.avalon.java_pp.dev_j120.ui;

import ru.avalon.java_pp.dev_j120.controllers.OrdersController;
import ru.avalon.java_pp.dev_j120.controllers.ProductController;
import ru.avalon.java_pp.dev_j120.models.Order;
import ru.avalon.java_pp.dev_j120.models.OrderPosition;
import ru.avalon.java_pp.dev_j120.uimodels.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Container;
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
        setBounds(200, 200, 1000, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();
        this.ordersTableModel = new OrdersTableModel(oc);
        mainTable = new JTable(this.ordersTableModel);
        mainTable.setGridColor(Color.BLUE);
        mainTable.setBackground(Color.PINK);
        c.add(mainTable.getTableHeader(), BorderLayout.CENTER);
        c.add(new JScrollPane(mainTable), BorderLayout.CENTER);

        JMenuBar mb = new JMenuBar();
        JMenu m = new JMenu("Учет заказов");
        JMenuItem mi;

        mi = new JMenuItem("Добавить новый заказ");

        mi.addActionListener(this::addNewOrder);
        m.add(mi);

        mi = new JMenuItem("Изменить заказ");
        mi.addActionListener(this::editOrders);
        m.add(mi);

        mi = new JMenuItem("Удалить заказ");
        mi.addActionListener(e -> cancelOrder());
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
                    throw new RuntimeException(exception);
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
                ordersTableModel.addOrders(new Date(), fio, phone, address,percent, Order.Status.PREPARING, new Date(), orderPositionList);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(),
                        "Ошибка добавления заказа", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editOrders(ActionEvent a) {
        int row = mainTable.getSelectedRow();
        if(row == -1)
            return;
        Order order = oc.getOrder(row);
        if (!Order.Status.PREPARING.toString().equals(order.getStatus())) {
            JOptionPane.showMessageDialog(this, "Заказ нельзя изменить",
                    "Ошибка добавления заказа", JOptionPane.ERROR_MESSAGE);
            return;
        }
        OrderDialog dlg = new OrderDialog(this,pc, order);
        dlg.setLocationRelativeTo(this);
        dlg.setVisible(true);
        if(dlg.isModalResult()) {
            ordersTableModel.editOrders(row, dlg.getDate1(), dlg.getFio(), dlg.getPhone(),
                    dlg.getAddress(), dlg.getDate2(), dlg.getOrderPositionList());
        }
        ordersTableModel.renewalOrders(row);

    }

    private void cancelOrder() {
        int row = mainTable.getSelectedRow();
        if(row == -1)
            return;
       oc.remove(row);
        ordersTableModel.removeOrders(row);
    }

}

