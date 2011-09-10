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
	    private List<Hack> entries;
	    
		public void setEntries(List<Hack> entries) {
			this.entries = entries;
		}

		public List<Hack> getEntries() {
			return entries;
		}
	}
	
    public static void fetch() {
    	try {
            URL url = new URL("http://api.ihackernews.com/page?format=jsonp");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8" ));
            String line, json = "";
			while ((line = reader.readLine()) != null) {
            	json = json+line;
            }
			
			Feed objs = new Gson().fromJson(json, Feed.class);
			Cache.set("feed", objs);
			/*
			for(Hack entrie : objs.getEntries()) {
				new Hack(entrie.id, entrie.date, entrie.body, entrie.name, entrie.url);
	        }
	        */
        } catch (MalformedURLException e) {} catch (IOException e) {}
    }
}