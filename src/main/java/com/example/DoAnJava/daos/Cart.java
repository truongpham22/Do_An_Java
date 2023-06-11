package com.example.DoAnJava.daos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class Cart {
    private List<CartItem> items;
    private Long userId;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addItem(CartItem product) {
        items.add(product);
    }
    public void clearCart() {
        items.clear();
    }

    public void removeItem(Long id){
        items.removeIf(item -> Objects.equals(item.getId(),id));
    }

    public List<CartItem> getItems() {
        return items;
    }
}
