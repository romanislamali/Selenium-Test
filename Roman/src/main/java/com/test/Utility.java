package com.test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Utility {

	ChromeDriver cDriver;
	ChromeOptions cOptions;
	
	public void setUp() {
		
		System.out.println("setUp work!!");
		
		cOptions = new ChromeOptions();
		cDriver = new ChromeDriver();
		
		cDriver.manage().window().maximize();
		cDriver.get("https://www.lw.com/en/people#sort=%40peoplerankbytitle%20ascending%3B%40peoplelastname%20ascending");
		
	} 
	
}
