package com.phantomxe;

import java.time.LocalDate;

public class Customer {
    private String name;
    private String email;
    private LocalDate registirationDate;

    public Customer(String name, String email, LocalDate registirationDate) {
        this.name = name;
        this.email = email;
        this.registirationDate = registirationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer [name=" + name + ", email=" + email + ", registirationDate=" + registirationDate + "]";
    }

    public LocalDate getRegistirationDate() {
        return registirationDate;
    }

    public void setRegistirationDate(LocalDate registirationDate) {
        this.registirationDate = registirationDate;
    }
}
