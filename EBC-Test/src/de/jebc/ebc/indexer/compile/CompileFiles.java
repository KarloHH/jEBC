package de.jebc.ebc.indexer.compile;

import java.util.Enumeration;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.Splitter;
import de.jebc.ebc.impl.AbstractBoard;
import de.jebc.ebc.impl.AbstractSplitter;
import de.jebc.ebc.impl.SingleOutPin;
import de.jebc.ebc.indexer.IndexerData;

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
        split = createSplitter();
        CrawlDirectory crawler = new CrawlDirectory();

        in = extend(split.In());
        indexFilename = extend(split.Out2());
        connect(split.Out1(), with(crawler.In()));
    }

    private AbstractSplitter<IndexerData, String, String> createSplitter() {
        return new AbstractSplitter<IndexerData, String, String>() {

            @Override
            protected String getPart1(IndexerData message) {
                return message.SourceDir;
            }

            @Override
            protected String getPart2(IndexerData message) {
                return message.IndexFilename;
            }
        };
    }

}
