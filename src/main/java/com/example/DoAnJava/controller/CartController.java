package com.example.DoAnJava.controller;

import com.example.DoAnJava.daos.Cart;
import com.example.DoAnJava.daos.CartItem;
import com.example.DoAnJava.services.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("cart")

public class CartController {
    @Autowired
    private CartService cartService;
    @GetMapping("/cart")
    public String viewCart(Model model) {
        // Lấy giỏ hàng từ session
        Cart cart = (Cart) model.getAttribute("cart");

        // Kiểm tra nếu giỏ hàng không tồn tại, tạo mới
        if (cart == null) {
            cart = new Cart();
            model.addAttribute("cart", cart);
        }

        model.addAttribute("items", cart.getItems());
        return "cart";
    }

    @RequestMapping(value = "/cart/add", method = {RequestMethod.GET, RequestMethod.POST})
    public String addToCart(@RequestParam Long id, @RequestParam String imageList,@RequestParam String name, @RequestParam double price, Model model) {
        // Lấy giỏ hàng từ session
        Cart cart = (Cart) model.getAttribute("cart");

        // Kiểm tra nếu giỏ hàng không tồn tại, tạo mới
        if (cart == null) {
            cart = new Cart();
            model.addAttribute("cart", cart);
        }

        // Tạo sản phẩm từ thông tin nhận được
        CartItem product = new CartItem(id,imageList,name,price);

        // Thêm sản phẩm vào giỏ hàng
        cart.addItem(product);

        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String removeFromCart(HttpSession session,
                                 @PathVariable Long id) {
        var cart = cartService.getCart(session);
        cart.removeItem(id);
        return "redirect:/cart";
    }



}
