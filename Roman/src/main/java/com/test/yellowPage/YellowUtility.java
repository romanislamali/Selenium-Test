package com.test.yellowPage;


import java.io.*;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

public class YellowUtility {
	ChromeDriver cDriver = new ChromeDriver();
	Output output = new Output();
	List<String> nameList = new ArrayList<>();
	List<String> telephoneList = new ArrayList<>();
	List<String> emailList = new ArrayList<>();
	List<String> addressList = new ArrayList<>();
	ArrayList<String> linkList = new ArrayList<>();
	public static int totalCompany;

	// this method opened primary browser for starting work
	public void setUp(String endPoint) {
		// this is for maximizing window
		cDriver.manage().window().maximize();

		// primary link of website
		cDriver.get(endPoint);

	}

	// this method is work for all activity
	public void doActibity() throws IOException {

		// checking nextPage navigation and if have next page it return false, if haven't next page it return true.
		boolean nextPage = cDriver.findElements(By.xpath("//a[@class='next ajax-page']")).isEmpty();

		// if nextPage available it's return false and while loop is work
		while (!nextPage) {

			// this totalCompany contain the count of totalCompany
			 totalCompany = cDriver.findElements(By.xpath("//a[@class='business-name']")).size();
			System.out.println("Total Company: "+ totalCompany);

			// this loop work base on count of company and each company link is store in link variable
			for(int i = 1; i <= totalCompany; i++) {
				String link = cDriver.findElement(By.xpath("(//a[@class='business-name'])["+i+"]")).getAttribute("href");
				linkList.add(link);
			}


			// this loop work base on links array size
			for(int i = 0; i < totalCompany; i++) {

				// Open new window tab for working each company details 
				cDriver.switchTo().newWindow(WindowType.TAB);
				
				// handle the window
				Set<String> windowHandle = cDriver.getWindowHandles();
				
				// store all window in winList array
				List<String> winList = new ArrayList<String>(windowHandle);
				cDriver.get(linkList.get(i));

				// Check if company name element is present
				if (!cDriver.findElements(By.xpath("//h1[@class='dockable business-name']")).isEmpty()) {
					String name = cDriver.findElement(By.xpath("//h1[@class='dockable business-name']")).getText();
					if (name.isEmpty()) {
						nameList.add(" ");
						System.out.println("Company Name: ");
					} else {
						nameList.add(name);
						System.out.println("Company Name: " + name);
					}
				} else {
					nameList.add(" ");
					System.out.println("Company Name: ");
				}

				// Check if company telephone number is present
				if (!cDriver.findElements(By.xpath("//a[contains(@href,'tel:')]")).isEmpty()) {
					String telephone = cDriver.findElement(By.xpath("//a[contains(@href,'tel:')]")).getText();
					if (telephone.isEmpty()) {
						telephoneList.add(" ");
						System.out.println("Telephone No: ");
					} else {
						telephoneList.add(telephone);
						System.out.println("Telephone No: " + telephone);
					}
				} else {
					telephoneList.add(" ");
					System.out.println("Telephone No: ");
				}

				// Check if company email is present
				if (!cDriver.findElements(By.xpath("//a[@class='email-business' and starts-with(@href, 'mailto:')]")).isEmpty()) {
					String email = cDriver.findElement(By.xpath("//a[@class='email-business' and starts-with(@href, 'mailto:')]")).getAttribute("href");
					if (email != null && email.startsWith("mailto:")) {
						email = email.substring(7); // Removes 'mailto:' prefix to get the email
						emailList.add(email);
						System.out.println("Email: " + email);
					} else {
						emailList.add(" ");
						System.out.println("Email: ");
					}
				} else {
					emailList.add(" ");
					System.out.println("Email: ");
				}

				// Check if company address is present
				if (!cDriver.findElements(By.xpath("//span[@class='address']")).isEmpty()) {
					String address = cDriver.findElement(By.xpath("//span[@class='address']")).getText();
					if (address.isEmpty()) {
						addressList.add(" ");
						System.out.println("Address: ");
					} else {
						addressList.add(address);
						System.out.println("Address: " + address);
					}
				} else {
					addressList.add(" ");
					System.out.println("Address: ");
				}

				// close each window after completing each task
				cDriver.close();

				// go back to main window, index number zero(0) in winList array
				cDriver.switchTo().window(winList.get(0));

				System.out.println("************************************************");
			}

			Map<String, List<String>> dataMap = new HashMap<>();

			if (nameList != null) {
				dataMap.put("nameList", nameList);
			}
			if (telephoneList != null) {
				dataMap.put("telephoneList", telephoneList);
			}
			if (emailList != null) {
				dataMap.put("emailList", emailList);
			}
			if (addressList != null) {
				dataMap.put("addressList", addressList);
			}

			output.writeInExcel(dataMap);


			// initialized result empty(true) in nextPage variable when next page are not available, for this reason the while loop is stop
			nextPage = cDriver.findElements(By.xpath("//a[@class='next ajax-page']")).isEmpty();
			try {
				// click nextPage navigation button after completing once page
				cDriver.findElement(By.xpath("//a[@class='next ajax-page']")).click();
				
				// rest program for 5 second
				Thread.sleep(5000);

			} catch (Exception e) {
				System.out.println("Something is wrong......!!");
			}

		}

	}

}
