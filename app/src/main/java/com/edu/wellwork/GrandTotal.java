package com.edu.wellwork;

import java.util.ArrayList;
import java.util.Formatter;

public class GrandTotal {

    double tot = 0.00;
    Formatter format;

    public GrandTotal() {
    }

    public String countGtot(ArrayList<Orders> arr, int x){


        for (int i = 0; i <= x; i++){
            Orders order = arr.get(i);
            tot += Double.parseDouble(order.getTotal());

        }

        String total = Double.toString(tot);

        return total;
    }
}
