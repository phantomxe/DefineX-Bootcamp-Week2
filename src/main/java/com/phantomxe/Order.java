package com.phantomxe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
    private Map<Seller, List<Product>> products;
    private Boolean isConfirmed = false;

    public Order() {
        products = new HashMap<>();
    }

    public void addProduct(Product product, Integer quantity) throws IllegalArgumentException { 
        if(product.getQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough stock for " + product.getName());
        }

        product.setQuantity(product.getQuantity() - quantity);
        Product newProduct = new Product(product.getSeller(), product.getName(), product.getPrice(), quantity, product.getCategory());
        if(products.get(product.getSeller()) != null) {
            products.get(product.getSeller()).add(newProduct);
        } else {
            List<Product> productList = new ArrayList<>();
            productList.add(newProduct);
            products.put(product.getSeller(), productList);
        }
    }

    public List<Product> getProducts() {
        return products.values().stream()
            .reduce(new ArrayList<>(), (a, b) -> {
                a.addAll(b);
                return a;
            });
    } 

    public List<Invoice> generateInvoices(Customer customer) throws Exception {
        if(!isConfirmed) { 
            isConfirmed = true;

            //Create multiple invoices for each seller
            List<Invoice> invoices = new ArrayList<>();
            products.forEach((sellerKey, productList) -> {
                invoices.add(new Invoice(productList, customer, sellerKey));
            });

            return invoices;
        } else {
            throw new Exception("Order already confirmed");
        }
    }
}
