package de.jebc.ebc.addressbook.data.baseadresses;

public class BaseAddressData {

    private final int id;
    private final String category;
    private final String givenname;
    private final String name;

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getGivenName() {
        return givenname;
    }

    public String getName() {
        return name;
    }

    public BaseAddressData(int id, String category, String givenname,
            String name) {
        this.id = id;
        this.category = category;
        this.givenname = givenname;
        this.name = name;
    }

}
