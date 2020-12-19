package ru.avalon.java_pp.dev_j120.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date date1;
    private String fio;
    private int phone;
    private String address;
    private int percent;
    private Status status;
    private Date date2;
    private List<OrderPosition>  orderPosition;


    public Order(Date date1, String fio, Integer phone, String address, Status status, Date date2, List<OrderPosition> orderPosition) {
        this.date1 = date1;
        this.fio = fio;
        this.phone = phone;
        this.address = address;
        this.percent = percent;
        this.status = status;
        this.date2 = date2;
        this.orderPosition = orderPosition;
    }

    public enum Status {
        PREPARING("готовится"),
        SHIPPED("отгружен"),
        CANCELLED("отменен");

        private String Status;

        Status(String Status) {
            this.Status = Status;
        }

        public String toString() {
            return Status;
        }

        public static Status getStatus(String s) {
            switch (s) {
                case "готовится":
                    return PREPARING;
                case "отгружен":
                    return SHIPPED;
                case "отменён":
                    return CANCELLED;
                default:
                    return null;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(date1.toString());
        sb.append(";");
        sb.append(fio);
        sb.append(";");
        sb.append(phone);
        sb.append(";");
        sb.append(address);
        sb.append(";");
        sb.append(status);
        sb.append(";");
        sb.append(date2.toString());
        sb.append(";");
        sb.append(orderPosition);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (o instanceof Order) {
            Order obj = (Order) o;
            if (obj.date1 == this.date1
                    && obj.fio.equals(this.fio)
                    && obj.phone == (this.phone)
                    && obj.address == (this.address)
                    && obj.status == (this.status)
                    && obj.date2 == (this.date2)
            ) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

        public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public String getStatus() {
        return status.toString();
    }


    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

//    public ru.avalon.java_pp.dev_j120.models.OrderPosition
//    getOrderPosition() {
//        return OrderPosition;
//    }


    public List<OrderPosition> getOrderPosition() {
        return orderPosition;
    }

    public void setOrderPosition(List<OrderPosition> orderPosition) {
        this.orderPosition = orderPosition;
    }


}
