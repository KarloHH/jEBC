package de.jebc.ebc.addressbook.domain;

public class BaseAddressData {

    private final AddressCategory addressCategory;
    private final String name;

    public BaseAddressData(AddressCategory addressCategory, String name) {
        this.addressCategory = addressCategory;
        this.name = name;
    }

    public AddressCategory getCategory() {
        return addressCategory;
    }

}
