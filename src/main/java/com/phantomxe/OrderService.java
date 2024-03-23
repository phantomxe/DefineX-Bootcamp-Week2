package com.phantomxe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderService {
    private List<Customer> customers;
    private List<Invoice> invoices;
    private List<Product> products;

    public OrderService(List<Customer> customers, List<Product> products) {
        this.customers = new ArrayList<>(customers);
        this.products = new ArrayList<>(products);
        invoices = new ArrayList<>();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
        System.out.println("Invoice added successfully: " + invoice.getInvoiceNumber());
    }

    public void addInvoices(List<Invoice> invoices) {
        this.invoices.addAll(invoices);
    }

    public Integer getCustomerCount() {
        return customers.size();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    } 

    public Customer getCustomer(String email) throws Exception {
        Optional<Customer> customer = customers.stream().filter(c -> c.getEmail().equals(email)).findFirst();
        if(customer.isPresent()) {
            return customer.get();
        } else {
            throw new Exception("Customer not found");
        }
    }

    public Product getProductByName(String name) throws Exception{
        Optional<Product> item = products.stream().filter(product -> product.getName().equals(name)).findFirst();
        if(item.isPresent()) {
            return item.get();
        } else {
            throw new Exception("Product not found");
        }
    }
}
