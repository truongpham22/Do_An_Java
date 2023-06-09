package com.example.DoAnJava.services;

import com.example.DoAnJava.daos.Cart;
import com.example.DoAnJava.daos.CartItem;
import jakarta.servlet.http.HttpSession;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartService {
    private static final String CART_SESSION_KEY = "cart";
    public Cart getCart(@NotNull HttpSession session) {
        return Optional.ofNullable((Cart)
                        session.getAttribute(CART_SESSION_KEY))
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    session.setAttribute(CART_SESSION_KEY, cart);
                    return cart;
                });
    }
    public void updateCart(@NotNull HttpSession session, Cart cart) {
        session.setAttribute(CART_SESSION_KEY, cart);
    }
    public void removeCart(@NotNull HttpSession session) {
        session.removeAttribute(CART_SESSION_KEY);
    }

    public BigDecimal getSumPrice(@jakarta.validation.constraints.NotNull HttpSession session) {
        return getCart(session).getItems().stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
