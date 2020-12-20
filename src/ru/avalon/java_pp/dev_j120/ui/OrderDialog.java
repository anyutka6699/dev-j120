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
        fio = new JTextField(30);
        p.add(fio);
        topPanel.add(p);

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("Телефон"));
        phone = new JTextField(30);
        p.add(phone);
        topPanel.add(p);

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("Адрес"));
        address = new JTextField(30);
        p.add(address);
        topPanel.add(p);


        p = new JPanel();
        JButton btn = new JButton("Создать заказ");
        btn.addActionListener(e -> {
            modalResult = true;
            dispose();
        });
        p.add(btn);

        btn = new JButton("Отмена");
        btn.addActionListener(e -> {
            dispose();
        });
        p.add(btn);

        topPanel.add(p);
        getContentPane().add(topPanel, BorderLayout.NORTH);
        setResizable(false);
        pack();

    JPanel addProducts = new JPanel();
    listProducts = new JList(productController.getProducts());
        this.listProducts.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.listProducts.setLayoutOrientation(JList.VERTICAL);
        this.listProducts.setVisibleRowCount(-1);
    JScrollPane listScroller = new JScrollPane(this.listProducts);
        listScroller.setPreferredSize(new Dimension(600, 100));
        addProducts.add(listScroller);

    btn = new JButton("Добавить новый заказ");
        btn.addActionListener(this::addProductToOrder);
        topPanel.add(addProducts);
        JPanel panelWithAmount = new JPanel();
        panelWithAmount.add(new JLabel("Количество товара"));
        this.amountTextField = new JTextField(30);
        panelWithAmount.add(amountTextField);
        amountTextField.setText("1");
        panelWithAmount.add(btn);
        topPanel.add(panelWithAmount);

        Box panel = new Box(BoxLayout.Y_AXIS);
        selectedProductsInOrder.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        panel.add(new JScrollPane(selectedProductsInOrder));
        panel.setPreferredSize(new Dimension(100, 100));
        topPanel.add(panel);
        topPanel.add(p);
    getContentPane().add(topPanel, BorderLayout.CENTER);

    setResizable(false);
    pack();
}

    public OrderDialog(MainFrame owner, ProductController productController, Order order) {
        this(owner, productController);
        fio.setText(order.getFio());
        phone.setText(String.valueOf(order.getPhone()));
        address.setText(order.getAddress());
        orderPositionTabModel.setOrderPosition(order.getOrderPosition());
    }

    public String getFio() {
        return fio.getText();
    }


    public Integer getPhone() {
        if (phone== null){
            return 0;
        }
        if (phone.getText().isEmpty()){
            return 0;
        }
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

