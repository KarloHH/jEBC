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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((category == null) ? 0 : category.hashCode());
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseAddressData other = (BaseAddressData) obj;
        if (category == null) {
            if (other.category != null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return getName();
    }

}
