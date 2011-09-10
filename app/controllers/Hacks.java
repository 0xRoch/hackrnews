package controllers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import play.cache.Cache;
import play.mvc.Controller;

import models.Hack;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Hacks extends Controller {
	public class Feed {
	    private List<Hack> items;
	    
		public void setEntries(List<Hack> items) {
			this.items = items;
		}

		public List<Hack> getEntries() {
			return items;
		}
	}
		
    public static void fetch() {
    	try {
    		List<Hack> items = Cache.get("items", List.class);
    		if (items == null) {
    			URL url = new URL("http://api.ihackernews.com/page?format=json");
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8" ));
                String line, json = "";
    			while ((line = reader.readLine()) != null) {
                	json = json+line;
                }
    			Feed objs = new Gson().fromJson(json, Feed.class);
    			items = objs.getEntries();
    			Cache.set("items", items, "5mn");
    		}
			render(items);
			
        } catch (MalformedURLException e) {} catch (IOException e) {}
    }
}