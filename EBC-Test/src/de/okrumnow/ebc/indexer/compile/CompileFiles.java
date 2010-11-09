package de.okrumnow.ebc.indexer.compile;

import java.util.Enumeration;

import de.okrumnow.ebc.InPin;
import de.okrumnow.ebc.OutPin;
import de.okrumnow.ebc.Splitter;
import de.okrumnow.ebc.impl.AbstractBoard;
import de.okrumnow.ebc.impl.AbstractSplitter;
import de.okrumnow.ebc.impl.ExtensionInPin;
import de.okrumnow.ebc.impl.ExtensionOutPin;
import de.okrumnow.ebc.impl.SingleOutPin;
import de.okrumnow.ebc.indexer.IndexerData;

public class CompileFiles extends AbstractBoard {

    private InPin<IndexerData> in;
    private OutPin<String> indexFilename;
    private OutPin<Enumeration<String>> textFilenames = new SingleOutPin<Enumeration<String>>();
    private Splitter<IndexerData, String, String> split;

    public InPin<IndexerData> In() {
        return in;
    }

    public OutPin<String> IndexFilename() {
        return indexFilename;
    }

    public OutPin<Enumeration<String>> TextFilenames() {
        return textFilenames;
    }

    public CompileFiles() {
        split = new AbstractSplitter<IndexerData, String, String>() {

            @Override
            protected String getPart1(IndexerData message) {
                return message.SourceDir;
            }

            @Override
            protected String getPart2(IndexerData message) {
                return message.IndexFilename;
            }
        };
        in = new ExtensionInPin<IndexerData>(split.In());
        indexFilename = new ExtensionOutPin<String>(split.Out2());
        CrawlDirectory crawler = new CrawlDirectory();
        connect(split.Out1(), with(crawler.In()));
    }

    private <T> void connect(OutPin<T> outPin, InPin<T> inPin) {
        outPin.connect(inPin);
    }

}
