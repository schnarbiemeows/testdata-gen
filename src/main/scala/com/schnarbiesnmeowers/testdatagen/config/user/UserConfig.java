package com.schnarbiesnmeowers.testdatagen.config.user;

public class UserConfig {
    private int numrecords;
    private int numfiles;
    private String filename;
    private String format;
    private boolean header;
    private boolean footer;
    private ListItem[] fields;

    public UserConfig() {}

    public UserConfig(int numrecords, int numfiles, String filename, String format,
                      boolean header, boolean footer, ListItem[] fields) {
        this.numrecords = numrecords;
        this.numfiles = numfiles;
        this.filename = filename;
        this.format = format;
        this.header = header;
        this.footer = footer;
        this.fields = fields;
    }

    public int getNumrecords() {
        return numrecords;
    }

    public void setNumrecords(int numrecords) {
        this.numrecords = numrecords;
    }

    public int getNumfiles() {
        return numfiles;
    }

    public void setNumfiles(int numfiles) {
        this.numfiles = numfiles;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean isHeader() {
        return header;
    }

    public void setHeader(boolean header) {
        this.header = header;
    }

    public boolean isFooter() {
        return footer;
    }

    public void setFooter(boolean footer) {
        this.footer = footer;
    }

    public ListItem[] getFields() {
        return fields;
    }

    public void setFields(ListItem[] fields) {
        this.fields = fields;
    }
}
