package com.example.foodorderapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Controller
public class FoodOrderAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodOrderAppApplication.class, args);
    }

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String authenticate(@RequestParam String username, 
                             @RequestParam String password,
                             Model model) {
        if ("admin".equals(username) && "admin".equals(password)) {
            return "redirect:/menu";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }
   
    @GetMapping("/menu")
    public String menu(Model model) {
   	 List<FoodItem> foodItems = new ArrayList<>();
   	 foodItems.add(new FoodItem("Pizza", "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=300&auto=format", 10.99, "Delicious cheese pizza with tomato sauce"));
   	 foodItems.add(new FoodItem("Burger", "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=300&auto=format", 8.99, "Juicy beef burger with lettuce and cheese"));
   	 foodItems.add(new FoodItem("Pasta", "https://images.unsplash.com/photo-1555949258-eb67b1ef0ceb?w=300&auto=format", 12.99, "Creamy Alfredo pasta with mushrooms"));
   	 foodItems.add(new FoodItem("Salad", "https://images.unsplash.com/photo-1546793665-c74683f339c1?w=300&auto=format", 7.99, "Fresh garden salad with vinaigrette"));
   	 foodItems.add(new FoodItem("Sushi", "https://images.unsplash.com/photo-1611143669185-af224c5e3252?w=300&auto=format", 15.99, "Assorted sushi platter with wasabi"));
    
   	 model.addAttribute("foodItems", foodItems);
   	 return "menu";
    }
    // CSS and JS endpoints
    @GetMapping("/login-style.css")
    @ResponseBody
    public String loginCss() {
        return "body { background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab); background-size: 400% 400%; animation: gradient 15s ease infinite; height: 100vh; margin: 0; display: flex; justify-content: center; align-items: center; font-family: Arial, sans-serif; } @keyframes gradient { 0% { background-position: 0% 50%; } 50% { background-position: 100% 50%; } 100% { background-position: 0% 50%; } } .login-box { background: white; padding: 2rem; border-radius: 10px; box-shadow: 0 0 20px rgba(0,0,0,0.1); width: 300px; } .login-box h2 { text-align: center; margin-bottom: 1.5rem; } .input-group { margin-bottom: 1rem; } .input-group label { display: block; margin-bottom: 0.5rem; } .input-group input { width: 100%; padding: 0.5rem; border: 1px solid #ddd; border-radius: 4px; } button { width: 100%; padding: 0.75rem; background: #ff6b6b; color: white; border: none; border-radius: 4px; cursor: pointer; } button:hover { background: #ff5252; } .error { color: red; text-align: center; margin-bottom: 1rem; }";
    }

    @GetMapping("/menu-style.css")
    @ResponseBody
    public String menuCss() {
        return "body { font-family: Arial, sans-serif; margin: 0; padding: 0; background: #f5f5f5; } header { background: linear-gradient(to right, #ff416c, #ff4b2b); color: white; padding: 1rem; text-align: center; } .menu-container { display: grid; grid-template-columns: repeat(auto-fill, minmax(300px, 1fr)); gap: 1rem; padding: 1rem; } .food-item { background: white; border-radius: 8px; overflow: hidden; box-shadow: 0 2px 5px rgba(0,0,0,0.1); } .food-image { width: 100%; height: 200px; object-fit: cover; } .food-details { padding: 1rem; } .food-details h3 { margin-top: 0; } .price { font-weight: bold; color: #ff4b2b; margin: 0.5rem 0; } select, button { width: 100%; padding: 0.5rem; margin: 0.5rem 0; } button { background: #ff416c; color: white; border: none; border-radius: 4px; cursor: pointer; } button:hover { background: #ff4b2b; }";
    }

    @GetMapping("/script.js")
    @ResponseBody
    public String menuJs() {
        return "document.addEventListener('DOMContentLoaded', () => { document.querySelectorAll('.order-btn').forEach(btn => { btn.addEventListener('click', function() { const item = this.closest('.food-item'); const name = item.querySelector('h3').textContent; const qty = item.querySelector('select').value; alert(`Ordered ${qty} ${name}(s)!`); }); }); });";
    }

    // HTML templates as string endpoints
    @GetMapping(value = "/login", produces = "text/html")
    @ResponseBody
    public String loginHtml() {
        return "<!DOCTYPE html><html><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'><title>Login</title><link rel='stylesheet' href='/login-style.css'></head><body><div class='login-box'><h2>Food Order App</h2><form action='/login' method='post'><div th:if='${error}' class='error' th:text='${error}'></div><div class='input-group'><label for='username'>Username</label><input type='text' id='username' name='username' required></div><div class='input-group'><label for='password'>Password</label><input type='password' id='password' name='password' required></div><button type='submit'>Login</button></form></div></body></html>";
    }

    @GetMapping(value = "/menu-html", produces = "text/html")
    @ResponseBody
    public String menuHtml() {
        return "<!DOCTYPE html><html xmlns:th='http://www.thymeleaf.org'><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'><title>Menu</title><link rel='stylesheet' href='/menu-style.css'><script src='/script.js' defer></script></head><body><header><h1>Our Menu</h1></header><div class='menu-container'><div class='food-item' th:each='item : ${foodItems}'><img th:src=\"'/images/' + ${item.image}\" class='food-image'><div class='food-details'><h3 th:text='${item.name}'></h3><p th:text='${item.description}'></p><div class='price' th:text=\"'$' + ${#numbers.formatDecimal(item.price, 1, 2)}\"></div><select><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select><button class='order-btn'>Order Now</button></div></div></div></body></html>";
    }

    public static class FoodItem {
        private final String name;
        private final String image;
        private final double price;
        private final String description;

        public FoodItem(String name, String image, double price, String description) {
            this.name = name;
            this.image = image;
            this.price = price;
            this.description = description;
        }

        public String getName() { return name; }
        public String getImage() { return image; }
        public double getPrice() { return price; }
        public String getDescription() { return description; }
    }
}
