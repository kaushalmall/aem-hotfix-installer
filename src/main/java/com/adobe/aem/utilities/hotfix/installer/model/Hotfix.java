package com.adobe.aem.utilities.hotfix.installer.model;

import java.util.Calendar;

/**
 *
 * @author brobert
 */
public class Hotfix implements Comparable<Hotfix>{

    private String downloadHref;
    private String name;
    private Calendar date;
    private String description;

    public String getDownloadHref() {
        return downloadHref;
    }
    
    public void setDownloadHref(String href) {
        downloadHref = href;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Calendar getDate() {
        return date;
    }
    
    public void setDate(Calendar date) {
        this.date = date;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescripton(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(Hotfix other) {
        return date.compareTo(other.getDate());
    }
    
}
