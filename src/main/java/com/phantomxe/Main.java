package com.phantomxe;

import java.time.LocalDate;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Seller mediamarkt = new Seller(Sector.TECHNOLOGY, "MediaMarkt");
        Seller zara = new Seller(Sector.TEXTILE, "Zara");
        Seller koctas = new Seller(Sector.RETAIL, "Koçtaş");
        Seller watsons = new Seller(Sector.PERSONAL_CARE, "Watsons");

        Set<Seller> sellers = Set.of(mediamarkt, zara, koctas, watsons);

        OrderService orderService = new OrderService(
            List.of( //Registered customers
                new Customer("Jane", "test2@test.com", LocalDate.of(2004, 5, 17)),
                new Customer("Cem", "test3@test.com", LocalDate.of(1997, 9, 1)),
                new Customer("Ercan", "test4@test.com", LocalDate.of(1999, 4, 2)),
                new Customer("Ertem", "test5@test.com", LocalDate.of(2002, 6, 19))
            ), 
            List.of(
                new Product(mediamarkt, "Laptop", 35000.0, 10, ProductCategory.ELECTRONIC),
                new Product(mediamarkt, "Mouse", 250.0, 100, ProductCategory.ELECTRONIC),
                new Product(mediamarkt, "Keyboard", 500.0, 50, ProductCategory.ELECTRONIC),
                new Product(zara, "Tshirt", 3000.0, 20, ProductCategory.CLOTHING),
                new Product(koctas, "Cordless Drill", 11650.0, 30, ProductCategory.TOOLS),
                new Product(koctas, "Screwdriver", 150.0, 100, ProductCategory.TOOLS),
                new Product(koctas, "Screw", 1.0, 1000, ProductCategory.TOOLS),
                new Product(watsons, "Shampoo", 50.0, 100, ProductCategory.PERSONAL_CARE)
            )
        );
        
        //Create new customers
        System.out.println("Adding new customers to system...");
        orderService.addCustomer(new SpecialCustomer("John", "test@test.com", LocalDate.of(1990, 6, 1)));


        //List all customers
        System.out.println("All customers in system:");
        orderService.getCustomers().stream()
            .forEach(customer -> {
                System.out.println(customer);
        });

        //List all customers that name includes "C"
        System.out.println("Customers that name includes 'C':");
        orderService.getCustomers().stream()
            .filter(customer -> customer.getName().toLowerCase().contains("c"))
            .forEach(customer -> {
                System.out.println(customer);
        });


        // Order 1
        try {
            Product laptop = orderService.getProductByName("Laptop");
            Product mouse = orderService.getProductByName("Mouse");
            Product keyboard = orderService.getProductByName("Keyboard");

            Order order = new Order();
            order.addProduct(laptop, 1);
            order.addProduct(mouse, 1);
            order.addProduct(keyboard, 1);

            Customer customer = orderService.getCustomer("test4@test.com");
            List<Invoice> invoices = order.generateInvoices(customer);
            orderService.addInvoices(invoices); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 

        // Order 2
        try { 
            Product tshirt = orderService.getProductByName("Tshirt");
            Product cordlessDrill = orderService.getProductByName("Cordless Drill");

            Order order = new Order();
            order.addProduct(tshirt, 3);
            order.addProduct(cordlessDrill, 1);

            Customer customer = orderService.getCustomer("test2@test.com");
            List<Invoice> invoices = order.generateInvoices(customer);
            orderService.addInvoices(invoices); 
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Order 3
        try {
            Product screwdriver = orderService.getProductByName("Screwdriver");
            Product screw = orderService.getProductByName("Screw"); 

            Order order = new Order();
            order.addProduct(screwdriver, 2);
            order.addProduct(screw, 100);

            Customer customer = orderService.getCustomer("test@test.com");
            List<Invoice> invoices = order.generateInvoices(customer);
            orderService.addInvoices(invoices); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Order 4
        try { 
            Product shampoo = orderService.getProductByName("Shampoo"); 
            Product tshirt = orderService.getProductByName("Tshirt");

            Order order = new Order();
            order.addProduct(shampoo, 2);
            order.addProduct(tshirt, 5);

            Customer customer = orderService.getCustomer("test3@test.com");
            List<Invoice> invoices = order.generateInvoices(customer);
            orderService.addInvoices(invoices); 
 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

         // Order 5
         try { 
            Product screw = orderService.getProductByName("Screw"); 

            Order order = new Order();
            order.addProduct(screw, 150); 

            Customer customer = orderService.getCustomer("test5@test.com");
            List<Invoice> invoices = order.generateInvoices(customer);
            orderService.addInvoices(invoices); 

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //Print all invoices that customer's registration date at june
        System.out.println("Invoices that customer's registration date at june:");
        orderService.getInvoices().stream()
            .filter(invoice -> invoice.getCustomer().getRegistirationDate().getMonthValue() == 6)
            .forEach(invoice -> {
                System.out.println(invoice);
        });
        

        //Print all invoices
        System.out.println("All invoices in system:");
        orderService.getInvoices().stream()
            .forEach(invoice -> {
                System.out.println(invoice);
        });


        //Print all invoices that exceeds 1500 TL 
        System.out.println("Invoices that exceeds 1500 TL:");
        orderService.getInvoices().stream()
            .filter(invoice -> invoice.getTotalPrice() > 1500)
            .forEach(invoice -> {
                System.out.println(invoice);
        });

        //Print average of invoices that exceeds 1500 TL 
        OptionalDouble average = orderService.getInvoices().stream()
            .filter(invoice -> invoice.getTotalPrice() > 1500)
            .mapToDouble(invoice -> invoice.getTotalPrice())
            .average();
        if(average.isPresent()) {
            System.out.println("Average of invoices that exceeds 1500 TL: " + String.format("%.2f TL", average.getAsDouble()));
        } 

        //Print all customer names that have less than total 500TL invoices
        System.out.println("Customers that have less than total 500 TL invoices:");
        orderService.getCustomers().stream()
            .filter(customer -> {
                return orderService.getInvoices().stream()
                    .filter(invoice -> invoice.getCustomer().getEmail().equals(customer.getEmail()))
                    .mapToDouble(invoice -> invoice.getTotalPrice())
                    .anyMatch(total -> total < 500.0);
            })
            .forEach(customer -> {
                System.out.println(customer.getName());
        });


        //Print sectors that total of sales less than 750TL at june
        System.out.println("Sectors that total of sales less than 750 TL at june:");
        sellers.stream().forEach(seller -> {
            long invoiceCount = orderService.getInvoices().stream()
                .filter(invoice -> invoice.getDate().getMonthValue() == 6)
                .count();
            
            if(invoiceCount > 0) {   
                double totalAmount = orderService.getInvoices().stream()
                    .filter(invoice -> invoice.getDate().getMonthValue() == 6 && invoice.getSeller().equals(seller))  
                    .mapToDouble(invoice -> invoice.getTotalPrice())
                    .sum();
                
                double output = totalAmount / invoiceCount;
                
                System.out.println("Seller: " + seller + " Total: " + String.format("%.2f TL", output));
            } else {
                System.out.println("Seller: " + seller + " Total: 0 TL");
            }
        });
            
    }
}