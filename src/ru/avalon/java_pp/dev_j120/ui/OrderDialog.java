package ru.avalon.java_pp.dev_j120.ui;
import ru.avalon.java_pp.dev_j120.models.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import ru.avalon.java_pp.dev_j120.config.Config;
import ru.avalon.java_pp.dev_j120.io.ProductsStorage;
import ru.avalon.java_pp.dev_j120.models.OrderPosition;
import ru.avalon.java_pp.dev_j120.models.Product;
import ru.avalon.java_pp.dev_j120.controllers.*;
import ru.avalon.java_pp.dev_j120.uimodels.OrderPositionTabModel;

public class OrderDialog extends JDialog {
    private JTextField date1;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    private JTextField fio;
    private JTextField phone;
    private JTextField address;
    private JTextField percent;
    private JComboBox  status;
    private JTextField date2;
    private JTextField orderPosition;
    private JTextField amountTextField;
    private JList listProducts;
    private boolean modalResult;
    private final ProductController productController;
    private final OrderPositionTabModel orderPositionTabModel = new OrderPositionTabModel();
    private JTable selectedProductsInOrder =  new JTable(orderPositionTabModel);


    public OrderDialog(MainFrame owner, ProductController productController) {
        super(owner, "Заказ", true);
        this.productController = productController;

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JPanel p;

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("ФИО"));
        fio = new JTextField(40);
        p.add(fio);
        topPanel.add(p);

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("Телефон"));
        phone = new JTextField(11);
        p.add(phone);
        topPanel.add(p);

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("Адрес"));
        address = new JTextField(60);
        p.add(address);
        topPanel.add(p);

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("Статус заказа "));
        String[] statusString = {Order.Status.PREPARING.toString(), Order.Status.SHIPPED.toString(), Order.Status.CANCELLED.toString()};
        this.status = new JComboBox(statusString);
        status.setSelectedIndex(2);
        status.setVisible(true);
        p.add(status);
        topPanel.add(p);

        p = new JPanel();
        JButton btn = new JButton("OK");
        btn.addActionListener(e -> {
            modalResult = true;
            dispose();
        });
        p.add(btn);

        btn = new JButton("Cancel");
        btn.addActionListener(e -> {
            dispose();
        });
        p.add(btn);

//        topPanel.add(p);
//
//        getContentPane().add(topPanel, BorderLayout.CENTER);
//
//        setResizable(false);
//        pack();
    JPanel addProductsPanel = new JPanel();
    listProducts = new JList(productController.getProducts());
        this.listProducts.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.listProducts.setLayoutOrientation(JList.VERTICAL);
        this.listProducts.setVisibleRowCount(-1);
    JScrollPane listScroller = new JScrollPane(this.listProducts);
        listScroller.setPreferredSize(new Dimension(350, 100));
        addProductsPanel.add(listScroller);

    btn = new JButton("Добавить в заказ");
        btn.addActionListener(this::addProductToOrder);

    JPanel panelWithAmountProducts = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelWithAmountProducts.add(new JLabel("Количество товара"));
        this.amountTextField = new JTextField(10);
        panelWithAmountProducts.add(amountTextField);
        amountTextField.setText("1");
        addProductsPanel.add(panelWithAmountProducts);
        addProductsPanel.add(btn);
        topPanel.add(addProductsPanel);

    JPanel panel = new JPanel();
        panel.add(selectedProductsInOrder.getTableHeader(), BorderLayout.SOUTH);
        panel.add(new JScrollPane(selectedProductsInOrder), BorderLayout.CENTER);

        topPanel.add(panel);
        topPanel.add(p);
    getContentPane().add(topPanel, BorderLayout.CENTER);

    setResizable(false);
    pack();
}

    public String getFio() {
        return fio.getText();
    }


    public Integer getPhone() {
        return Integer.valueOf(phone.getText());
    }

    public String getAddress() {
        return address.getText();
    }

    public List<OrderPosition> getOrderPositionList() {
        return orderPositionTabModel.getOrderPositionList();
    }

    public boolean isModalResult() {
        return modalResult;
    }

    public void addProductToOrder(ActionEvent ee) {
        String selectedItem = (String)this.listProducts.getSelectedValue();
        long vendorCode = Long.valueOf(selectedItem.split(";")[0]);
        Product p = productController.searchProduct(vendorCode);
        int requestedQuantity = Integer.valueOf(amountTextField.getText());
        if (requestedQuantity > p.getQuantity() || requestedQuantity < 1) {

        } else {
            orderPositionTabModel.addOrderPosition(p, requestedQuantity);
        }
    }

    public Date getDate1() {
        try {
            if (date1==null){
                return new Date();
            }
            return formatter.parse(date1.getText());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Date getDate2() {
        try {
            if (date2 == null) {
                return new Date();
            }
            return formatter.parse(date2.getText());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getPercent() {
        if (percent== null){
            return 0;
        }
        if (percent.getText().isEmpty()){
            return 0;
        }
        return Integer.valueOf(percent.getText());
    }
}

