package de.jebc.thread;

import javax.swing.SwingUtilities;

import de.jebc.ebc.impl.ProcessImpl;

public class SwitchToBackground<T> extends ProcessImpl<T, T> {

    @Override
    protected void process(final T message) {
        if (SwingUtilities.isEventDispatchThread()) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    Result().send(message);
                }
            }).start();
        } else {
            Result().send(message);
        }
    }
}
