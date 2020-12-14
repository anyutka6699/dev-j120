package ru.avalon.java_pp.dev_j120.uimodels;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import ru.avalon.java_pp.dev_j120.controllers.OrdersController;
import ru.avalon.java_pp.dev_j120.models.Order;
import ru.avalon.java_pp.dev_j120.models.OrderPosition;
import ru.avalon.java_pp.dev_j120.models.Product;

public class OrdersTableModel implements TableModel {
    private static final String[] COLUMN_HEADERS = {
            "DATA",
            "FIO",
            "PHONE",
            "ADDRESS",
            "DISCOUNT",
            "STATUS",
            "DATA2",
            "POSITION"
    };


    private static final Class<?>[] COLUMN_TYPES = {
            Date.class,
            String.class,
            String.class,
            Integer.class,
            String.class,
            Integer.class,
            Date.class,
            Integer.class
    };


    private OrdersController controller;
    private List<TableModelListener> listeners;

    public OrdersTableModel(OrdersController controller) {
        this.controller = controller;
        this.listeners = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return controller.getOrdersCount();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_HEADERS.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return COLUMN_HEADERS[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return COLUMN_TYPES[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Order o = controller.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return o.getDate1();
            case 1:
                return o.getFio();
            case 2:
                return o.getPhone();
            case 3:
                return o.getAddress();
            case 4:
                return o.getPercent();
            case 5:
                return o.getStatus();
            case 6:
                return o.getDate2();
            case 7:
                return o.getOrderPosition();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }

    private void fireTableModelEvent(TableModelEvent e) {
        for (TableModelListener l : listeners)
            l.tableChanged(e);
    }

    public void addOrders(LocalDate date1, String fio, int phone, String address, int percent, Order.Status status, LocalDate date2, OrderPosition orderPosition) {
        {
            controller.add(date1, fio, phone, address, percent, status, date2, orderPosition);
            int rowNum = controller.getOrdersCount() - 1;
            fireTableModelEvent(new TableModelEvent(this, rowNum, rowNum,
                    TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
        }

    }

    public void editOrders(int index, LocalDate date1, String fio, int phone, String address, int percent, LocalDate date2, OrderPosition orderPosition) {
        Order o = controller.get(index);
        o.setDate2(date2);
        o.setFio(fio);
        o.setPhone(phone);
        o.setAddress(address);
        o.setPercent(percent);
        o.setDate2(date2);
        o.setOrderPosition(orderPosition);

        fireTableModelEvent(new TableModelEvent(this, index, index,
                TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE));
    }

    public void deleteOrders(int index) {
        controller.remove(index);
        fireTableModelEvent(new TableModelEvent(this, index, index,
                TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE));
    }

}
