package de.jebc.ebc.addressbook.gui.detail;

import java.util.Observable;
import java.util.Observer;

import de.jebc.ebc.InPin;
import de.jebc.ebc.InTrigger;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.addressbook.domain.addressdetails.Address;
import de.jebc.ebc.impl.SingleOutPin;

public class DetailsController implements Observer {

    private final IDetailsView view;
    private InTrigger savedPin = new InTrigger() {

        @Override
        public void receive() {
            // TODO notwendig?
        }
    };
    private InPin<Address> displayPin = new InPin<Address>() {

        @Override
        public void receive(Address message) {
            view.setAddress(message);
        }
    };
    private OutPin<Address> updatePin = new SingleOutPin<Address>();
    private OutPin<Address> saveNewPin = new SingleOutPin<Address>();
    private InTrigger savePin = new InTrigger() {

        @Override
        public void receive() {
            Address address = view.getAddress();
            if (address.getId() <= 0) {
                SaveNew().send(address);
            } else {
                Update().send(address);
            }
        }
    };
    private InTrigger newPin = new InTrigger() {
        
        @Override
        public void receive() {
            Address address = new Address();
            view.setAddress(address);
        }
    };

    public DetailsController(IDetailsView view) {
        this.view = view;
    }

    @Override
    public void update(Observable sender, Object newValue) {
        Changed().send((Boolean) newValue);
    }

    public OutPin<Boolean> Changed() {
        return view.Changed();
    }

    public InTrigger Saved() {
        return savedPin;
    }

    public InPin<Address> Display() {
        return displayPin;
    }

    public OutPin<Address> Update() {
        return updatePin;
    }

    public OutPin<Address> SaveNew() {
        return saveNewPin;
    }

    public InTrigger Save() {
        return savePin;
    }

    public InTrigger New() {
        return newPin;
    }

}
