package com.example.ec.domain;

public enum Region {
    Central_Coast("Central Coast"),
    Southern_California("Southern California"),
    Northern_California("Northern California"),
    Varies("Varies");

    private String label;

    private Region(String label) {
        this.label = label;
    }

    public static Region findByLabel(String label) {
        for (Region r : Region.values()) {
            if (r.label.equals(label)) {
                return r;
            }
        }
        return null;
    }

    public String getLabel() {
        return label;
    }
}
