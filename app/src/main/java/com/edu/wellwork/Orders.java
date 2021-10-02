package com.edu.wellwork;

public class Orders{

    public String name;
    public String qty;
    public String unitPrice;
    public String total;

    public Orders() {
    }

    public Orders(String name, String qty, String unitPrice, String total) {
        this.name = name;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.total = total;
    }

    public Orders(String itemName, String qty) {
        this.name = itemName;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public String getQty() {
        return qty;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public String getTotal() {
        return total;
    }
}
