package de.okrumnow.ebc.proof;

import de.okrumnow.ebc.OutPin;
import de.okrumnow.ebc.impl.SingleOutPin;
import de.okrumnow.ebc.parts.Cache;
import de.okrumnow.ebc.parts.ReadonlyCache;

public class EchoTool {

    OutPin<String> outpin = new SingleOutPin<String>();

    /**
     * @param args
     */
    public static void main(String[] args) {
        EchoTool client = new EchoTool();
        ConsoleEchoService<String> echo = new ConsoleEchoService<String>();
        UpcaseService upcase = new UpcaseServiceImpl();
        Cache<String, String> cache = new ReadonlyCache<String, String>();
        client.outpin.connect(cache.GetValue());
        cache.RequestValue().connect(upcase.Request());
        upcase.Result().connect(cache.ReceiveValue());
        cache.DeliverValue().connect(echo.Echo());
        client.Run();
    }

    private void Run() {
        outpin.transmit("Hallo Welt");
    }

}
