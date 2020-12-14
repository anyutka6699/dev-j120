package ru.avalon.java_pp.dev_j120.io;
import ru.avalon.java_pp.dev_j120.controllers.OrdersController;
import ru.avalon.java_pp.dev_j120.models.OrderPosition;
import ru.avalon.java_pp.dev_j120.uimodels.*;
import ru.avalon.java_pp.dev_j120.io.OrdersStorage;
import ru.avalon.java_pp.dev_j120.io.ProductsStorage;
import ru.avalon.java_pp.dev_j120.models.Product;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

        public class MainFrame extends JFrame {
            private OrdersController controller;
            private OrdersTableModel ordersTableModel;
            private JTable mainTable;

            public MainFrame(OrdersController controller) {
                super("Phone company clients");

                setBounds(300, 200, 900, 600);
                setDefaultCloseOperation(EXIT_ON_CLOSE);

                this.controller = controller;
                ordersTableModel = new OrdersTableModel(controller);
                mainTable = new JTable(ordersTableModel);
                Container c = getContentPane();
                c.add(mainTable.getTableHeader(), BorderLayout.NORTH);
                c.add(new JScrollPane(mainTable), BorderLayout.CENTER);

                JMenuBar mb = new JMenuBar();
                JMenu m = new JMenu("Order operations");
                JMenuItem mi;

//                mi = new JMenuItem("Add order");
//                mi.addActionListener(this::addOrder);
//                m.add(mi);
//
//                mi = new JMenuItem("Edit order");
//                mi.addActionListener(e -> editOrder());
//                m.add(mi);
//
//                mi = new JMenuItem("Remove order");
//                mi.addActionListener(e -> deleteOrder());
//                m.add(mi);

                mb.add(m);
                setJMenuBar(mb);
            }

//            private void addOrder(ActionEvent ee) {
//                OrderDialog dlg = new OrderDialog(this);
//                dlg.setLocationRelativeTo(this);
//                dlg.setVisible(true);
//                if(dlg.isModalResult()) {
//                    String areaCode = dlg.getAreaCode(),
//                            phoneNum = dlg.getPhoneNumber(),
//                            name = dlg.getName(),
//                            addr = dlg.getAddress();
//
//                    try {
//                        ordersTableModel.addOrder(LocalDate date1, String fio, int phone, String address, int percent, Order.Status status, LocalDate date2, OrderPosition
//                        orderPosition);
//                    } catch(Exception e) {
//                        JOptionPane.showMessageDialog(this, e.getMessage(),
//                                "Error adding client", JOptionPane.ERROR_MESSAGE);
//                    }
//                }
//            }
//
//            private void editOrder() {
//                int row = mainTable.getSelectedRow();
//                if(row == -1)
//                    return;
//                ClientDialog dlg = new ClientDialog(this, controller.get(row));
//                dlg.setLocationRelativeTo(this);
//                dlg.setVisible(true);
//                if(dlg.isModalResult()) {
//                    clientsTableModel.editClient(row, dlg.getName(), dlg.getAddress());
//                }
//            }
//
//            private void deleteOrder() {
//                int row = mainTable.getSelectedRow();
//                if(row == -1)
//                    return;
//                String phNum = controller.get(row).getNumber().toString();
//                if(JOptionPane.showConfirmDialog(this,
//                        "Are you sure you want to delete a record about " + phNum + "?",
//                        "Delete confirmation",
//                        JOptionPane.YES_NO_OPTION,
//                        JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
//                    clientsTableModel.deleteClient(row);
//                }
//            }
//        }



}
