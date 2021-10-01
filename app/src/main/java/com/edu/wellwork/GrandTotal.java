package com.edu.wellwork;

import java.util.ArrayList;

public class GrandTotal {

    double tot = 0.0;

    public GrandTotal() {
    }

    public String countGtot(ArrayList<Orders> arr, int x){
        for (int i = 0; i <= x; i++){
            Orders order = arr.get(i);
            tot += Double.parseDouble(order.getTotal());
        }

        return Double.toString(tot);
    }
}
