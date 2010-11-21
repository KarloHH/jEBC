package de.jebc.ebc.addressbook.domain.baseadresses;

import de.jebc.ebc.addressbook.domain.AddressCategory;

public class BaseAddressData {

    private final int id;
    private final AddressCategory category;
    private final String name;

    public int getId() {
        return id;
    }

    public AddressCategory getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public BaseAddressData(int id, AddressCategory category, String name) {
        this.id = id;
        this.category = category;
        this.name = name;
    }

}
