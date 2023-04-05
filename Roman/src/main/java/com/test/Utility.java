package com.test;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Utility {

	// reference of ChromeDriver and ChromeOption
	ChromeDriver cDriver;
	ChromeOptions cOptions;

	// this method is work for all activity
	public void doActibity() throws InterruptedException {

		// checking nextPage navigation and if have next page it return false, if haven't next page it return true.
		boolean nextPage = cDriver.findElements(By.xpath("//a[@class='next ajax-page']")).isEmpty();

		// if nextPage available it's return false and while loop is work
		while (nextPage == false) {

			// this totalCompany contain the count of totalCompany
			int totalCompany = cDriver.findElements(By.xpath("//a[@class='business-name']")).size();
			System.out.println("Total Company: "+ totalCompany);

			// This links array contain all company links
			ArrayList<String> links = new ArrayList<String>();

			// this loop work base on count of company and each company link is store in link variable
			for(int i = 1; i <= totalCompany; i++) {
				String link = cDriver.findElement(By.xpath("(//a[@class='business-name'])["+i+"]")).getAttribute("href");
				System.out.println(link);
				
				// all of links are store in the links array declear in top (ArrayList<String> links = new ArrayList<String>();)
				links.add(link);
			}



			// this loop work base on links array size
			for(int i = 0; i < links.size(); i++) {

				// Open new window tab for working each company details 
				cDriver.switchTo().newWindow(WindowType.TAB);
				
				// handle the window
				Set<String> windowHandle = cDriver.getWindowHandles();
				
				// store all window in winList array
				List<String> winList = new ArrayList<String>(windowHandle);
				cDriver.get(links.get(i));

				// collecting each company name
				String name = cDriver.findElement(By.xpath("//h1[@class='dockable business-name']")).getText();
				System.out.println("Company Name: "+name);

				// collecting each company telephone number
				String telephone = cDriver.findElement(By.xpath("//a[contains(@href,'tel:')]")).getText();
				System.out.println("Telephone No: "+telephone);

				// close each window after completing each task
				cDriver.close();
				
				// go back to main window thats index number zero(0) are available in winList array
				cDriver.switchTo().window(winList.get(0));

			}


			// initialized result empty(true) in nextPage variable when next page are not available, for this reason the while loop is stop
			nextPage = cDriver.findElements(By.xpath("//a[@class='next ajax-page']")).isEmpty();
			try {
				// click nextPage navigation button after completing once page
				cDriver.findElement(By.xpath("//a[@class='next ajax-page']")).click();
				
				// rest program for 5 second
				Thread.sleep(5000);

			} catch (Exception e) {
				// TODO: handle exception
			}

		}

	}
	
	// this method opened primary browser for starting work
	public void setUp() {
		cOptions = new ChromeOptions();
		cDriver = new ChromeDriver();

		// this is for maximizing window
		cDriver.manage().window().maximize();
		
		// primary link of web site
		cDriver.get("https://www.yellowpages.com/search?search_terms=gun+shop&geo_location_terms=Fresno%2C+CA");

	} 
}
