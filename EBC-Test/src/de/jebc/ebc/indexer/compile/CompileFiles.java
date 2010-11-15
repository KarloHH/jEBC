package de.jebc.ebc.indexer.compile;

import java.util.Enumeration;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.impl.Board;
import de.jebc.ebc.impl.Splitter;
import de.jebc.ebc.impl.SingleOutPin;
import de.jebc.ebc.indexer.IndexerData;

public class CompileFiles extends Board {

    private InPin<IndexerData> in;
    private OutPin<String> indexFilename;
    private OutPin<Enumeration<String>> textFilenames = new SingleOutPin<Enumeration<String>>();
    private Splitter<IndexerData,String,String> split;

    public InPin<IndexerData> in() {
        return in;
    }

    public OutPin<String> indexFilename() {
        return indexFilename;
    }

    public OutPin<Enumeration<String>> textFilenames() {
        return textFilenames;
    }

    public CompileFiles() {
        split = createSplitter();
        CrawlDirectory crawler = new CrawlDirectory();

        in = extend(split.in());
        indexFilename = extend(split.out2());
        connect(split.out1(), with(crawler.in()));
    }

    private Splitter<IndexerData, String, String> createSplitter() {
        return new Splitter<IndexerData, String, String>() {

            @Override
            protected String getMessage1(IndexerData message) {
                return message.sourceDir;
            }

            @Override
            protected String getMessage2(IndexerData message) {
                return message.indexFilename;
            }
        };
    }

}
