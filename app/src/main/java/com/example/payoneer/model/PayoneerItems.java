package com.example.payoneer.model;

import java.net.URL;
import java.util.Map;

public class PayoneerItems {
    private String label;
    private String method;
    private Map<String, URL> links;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public PayoneerItems(String label, String method, Map<String, URL> links) {
        this.label = label;
        this.method = method;
        this.links = links;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Map<String, URL> getLinks() {
        return links;
    }

    public void setLinks(Map<String, URL> links) {
        this.links = links;
    }
}
