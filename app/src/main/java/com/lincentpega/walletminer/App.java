package com.lincentpega.walletminer;

import com.lincentpega.walletminer.Constants;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App {
    public static void main(String[] args) {
    	try {
    	Document doc = Jsoup.connect("https://lbc.cryptoguru.org/dio/1")
    			.userAgent(Constants.userAgent)
    			.get();
    	
    	Element keysBlock = doc.getElementsByClass("keys").first();
    	Elements privateKeys = keysBlock.select("span > span");
    	Elements selectedAddresses = keysBlock.select("span > span + a, a + a");
    	
    	Map<String, String[]> keyToAddresses = new HashMap<>();; 
    	
    	for (int i = 0; i < privateKeys.size(); ++i) {
    		for (int j = 0; j < selectedAddresses.size(); j += 2) {
    			String addresses[] = {selectedAddresses.get(j).text(), selectedAddresses.get(j + 1).text()};
    			System.out.println(addresses[0] + " " + addresses[1]);
    			keyToAddresses.put(privateKeys.get(i).text(), addresses);
    		}
    	}
    	
    	System.out.println(keyToAddresses);
    	
    	
//    	System.out.println(privateKeys.size());
//    	System.out.println(addresses.text());
    			
    	
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    }
}
