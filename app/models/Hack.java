package models;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Hack {

    public String id;
    public String date;
    public String body;
    public String name;
    public String url;
     
    public Hack(String id, String date, String body, String name, String url) {
    	this.id = id;
        this.date = date;
        this.body = body;
        this.name = name;
        this.url = url;
    }
    
    public String toString() {
        return body;
    }
}
