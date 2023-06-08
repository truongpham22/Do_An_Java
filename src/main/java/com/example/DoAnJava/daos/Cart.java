package com.example.DoAnJava.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addItem(CartItem product) {
        items.add(product);
    }

    public void removeItem(Long id){
        items.removeIf(item -> Objects.equals(item.getId(),id));
    }

    public List<CartItem> getItems() {
        return items;
    }
}
