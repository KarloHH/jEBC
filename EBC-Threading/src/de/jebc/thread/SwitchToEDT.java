package de.jebc.thread;

import javax.swing.SwingUtilities;

import de.jebc.ebc.impl.ProcessImpl;

public class SwitchToEDT<T> extends ProcessImpl<T, T> {

    @Override
    protected void process(final T message) {
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    Result().send(message);
                }
            });
        } else {
            Result().send(message);
        }
    }

}
