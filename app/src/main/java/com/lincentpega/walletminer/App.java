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
    	Document doc = Jsoup.connect("https://lbc.cryptoguru.org/dio/3")
    			.userAgent(Constants.userAgent)
    			.get();
    	
    	Element keysBlock = doc.getElementsByClass("keys").first();
    	Elements privateKeys = keysBlock.select("span > span");
    	Elements selectedAddresses = keysBlock.select("span > span + a, a + a");
    	
    	Map<String, String[]> keyToAddresses = new HashMap<>(); 
    	
    	int j = 0;
    	for (int i = 0; i < privateKeys.size(); ++i) {
    		String addresses[] = {selectedAddresses.get(j).text(), selectedAddresses.get(j + 1).text()};
    		keyToAddresses.put(privateKeys.get(i).text(), addresses);
    		j += 2;
    	}
    		
    	
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    }
}
