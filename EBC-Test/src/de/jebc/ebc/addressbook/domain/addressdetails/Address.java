package de.jebc.ebc.addressbook.domain.addressdetails;

import de.jebc.ebc.addressbook.domain.AddressCategory;

public class Address {

    private final int id;
    private final AddressCategory category;
    private final String name;
    private final String givenName;
    private final String zipCode;
    private final String city;
    private final String street;
    private final String country;

    public Address(int id, AddressCategory category, String name,
            String givenName, String zipCode, String city, String street,
            String country) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.givenName = givenName;
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.country = country;
    }

    public Address() {
        this(0, new AddressCategory(""), "", "", "", "", "", "");
    }

    public int getId() {
        return id;
    }

    public AddressCategory getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getCountry() {
        return country;
    }

}
