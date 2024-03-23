package com.phantomxe;

public class Seller {
    private Sector sector;
    private String name; 

    public Seller(Sector sector, String name) {
        this.sector = sector;
        this.name = name; 
    }

    public Sector getSector() {
        return sector;
    }
    public void setSector(Sector sector) {
        this.sector = sector;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " Sector: " + sector;
    }
}
