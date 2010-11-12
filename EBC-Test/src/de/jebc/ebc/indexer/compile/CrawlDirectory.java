package de.jebc.ebc.indexer.compile;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

import de.jebc.ebc.InPin;
import de.jebc.ebc.OutPin;
import de.jebc.ebc.impl.SingleOutPin;

public class CrawlDirectory {

    private OutPin<Enumeration<String>> out = new SingleOutPin<Enumeration<String>>();
    private InPin<String> in = new InPin<String>() {

        @Override
        public void receive(String rootDirectory) {
            crawl(rootDirectory);
        }
    };

    public OutPin<Enumeration<String>> Out() {
        return out;
    }

    public InPin<String> In() {
        return in;
    }

    protected void crawl(String rootDirectory) {
        ArrayList<String> list = new ArrayList<String>();
        File dir = new File(rootDirectory);
        list.addAll(parseDirectory(dir));
//        Out().transmit(list.);
    }

    private Collection<? extends String> parseDirectory(File dir) {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> list = getListOfTextfiles(dir);
        result.addAll(list);
        File[] subdirs = getSubdirs(dir);
        for (File file : subdirs) {
            result.addAll(getFilesFromSubdir(file));
        }
        return result;
    }

    private Collection<? extends String> getFilesFromSubdir(File file) {
		return null;
    }

    private ArrayList<String> getListOfTextfiles(File dir) {
        String[] files = getTextFiles(dir);
        ArrayList<String> result = new ArrayList<String>();
        if (files != null) {
            for (String string : files) {
                result.add(string);
            }
        }
        return result;
    }

    private File[] getSubdirs(File dir) {
        return dir.listFiles(new FileFilter() {
            
            @Override
            public boolean accept(File arg0) {
                return arg0.isDirectory();
            }
        });
    }

    private String[] getTextFiles(File dir) {
        return dir.list(new FilenameFilter() {

            @Override
            public boolean accept(File arg0, String arg1) {
                return arg1.endsWith(".txt");
            }
        });
    }

}
