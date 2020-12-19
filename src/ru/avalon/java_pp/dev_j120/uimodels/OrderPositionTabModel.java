package ru.avalon.java_pp.dev_j120.uimodels;

import ru.avalon.java_pp.dev_j120.models.OrderPosition;
import ru.avalon.java_pp.dev_j120.models.Product;
import ru.avalon.java_pp.dev_j120.uimodels.*;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class OrderPositionTabModel implements TableModel {
    private static final String[] COLUMN_HEADERS = {
            "Артикул",
            "Название",
            "Цвет",
            "Цена",
            "Количество"
    };

    private static final Class<?>[] COLUMN_TYPES = {
            Integer.class,
            String.class,
            String.class,
            Float.class,
            Integer.class
    };

    private List<TableModelListener> listeners;
    private List<OrderPosition> selectedProducts = new ArrayList<>();

    public OrderPositionTabModel() {
        this.listeners = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return selectedProducts.size();
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
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        OrderPosition op = selectedProducts.get(rowIndex);
        switch (columnIndex) {
            case 0: return op.getArt();
            case 1: return op.getName();
            case 2: return op.getColor();
            case 3: return op.getPrice();
            case 4: return op.getQuantity();
            default: return null;
        }
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

    public void addOrderPosition(Product product, int quantity) {
        selectedProducts.add(new OrderPosition(product, quantity));
        int rowNum = selectedProducts.size() - 1;
        tableModelEvent(new TableModelEvent(this, rowNum, rowNum,
                TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
    }

    private void tableModelEvent(TableModelEvent e) {
        for(TableModelListener l: listeners)
            l.tableChanged(e);
    }

    public List<OrderPosition> getOrderPositionList() {
        return selectedProducts;
    }


    public void setOrderPosition(List<OrderPosition> orderPosition) {
    }
}
