package ru.avalon.java_pp.dev_j120.models;

import java.time.LocalDate;


public class Order {
    private LocalDate date1;
    private String fio;
    private int phone;
    private String address;
    private int percent;
    private String status;
    private LocalDate date2;
    private OrderPosition OrderPosition;

    public Order(LocalDate date1, String fio, int phone, String address, int percent, String status, LocalDate date2, OrderPosition orderPosition) {
        this.date1 = date1;
        this.fio = fio;
        this.phone = phone;
        this.address = address;
        this.percent = percent;
        this.status = status;
        this.date2 = date2;
        OrderPosition = orderPosition;
    }

    public enum status {
        preparing("готовится"),
        shipped("отгружен"),
        canceled("отменен");

        private String status;

        status(String status) {
            this.status = status;
        }
    }

    public LocalDate getDate1() {
        return date1;
    }

    public void setDate1(LocalDate date1) {
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
        return status;
    }

    public void setStatus(String status) { this.status = status;
    }

    public LocalDate getDate2() {
        return date2;
    }

    public void setDate2(LocalDate date2) {
        this.date2 = date2;
    }

    public ru.avalon.java_pp.dev_j120.models.OrderPosition getOrderPosition() {
        return OrderPosition;
    }

    public void setOrderPosition(ru.avalon.java_pp.dev_j120.models.OrderPosition orderPosition) {
        OrderPosition = orderPosition;
    }
}
