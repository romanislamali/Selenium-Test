package com.test.fiverr;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.*;

public class FiverrUtility {

    ChromeDriver cDriver = new ChromeDriver();
    StoreClient storeClient = new StoreClient();
    List<String> nameList = new ArrayList<>();
    List<String> countryList = new ArrayList<>();
    List<String> ratingList = new ArrayList<>();
    List<String> deliveryDateList = new ArrayList<>();
    public static int totalClient;

    // this method opened primary browser for starting work
    public void setUp(String endPoint) {
        // this is for maximizing window
        cDriver.manage().window().maximize();

        // primary link of website
        cDriver.get(endPoint);

    }

    // this method is work for all activity
    public void doActibity() throws IOException {

            // this totalClient contain the count of totalClient
            totalClient = cDriver.findElements(By.xpath("//p[@class='d1hltpk _1554sdp1ey _1554sdp1cg _1554sdp8 _1554sdp2']")).size();
            System.out.println("Total Client: "+ totalClient);

            // this loop work base on links array size
            for(int i = 0; i < totalClient; i++) {

                // Check if company name element is present
                if (!cDriver.findElements(By.xpath("//p[@class='d1hltpk _1554sdp1ey _1554sdp1cg _1554sdp8 _1554sdp2']")).isEmpty()) {
                    String clientName = cDriver.findElement(By.xpath("//p[@class='d1hltpk _1554sdp1ey _1554sdp1cg _1554sdp8 _1554sdp2']")).getText();
                    if (clientName.isEmpty()) {
                        nameList.add(" ");
                        System.out.println("Client Name: ");
                    } else {
                        nameList.add(clientName);
                        System.out.println("Client Name: " + clientName);
                    }
                } else {
                    nameList.add(" ");
                    System.out.println("Client Name: ");
                }

                // Check if Country is present
                if (!cDriver.findElements(By.xpath("//p[@class='d1hltpk _1554sdp1eo _1554sdp1c6 _1554sdp6 _1554sdp2 _1554sdpr2']")).isEmpty()) {
                    String countryName = cDriver.findElement(By.xpath("//p[@class='d1hltpk _1554sdp1eo _1554sdp1c6 _1554sdp6 _1554sdp2 _1554sdpr2']")).getText();
                    if (countryName.isEmpty()) {
                        countryList.add(" ");
                        System.out.println("Country : ");
                    } else {
                        countryList.add(countryName);
                        System.out.println("Country : " + countryName);
                    }
                } else {
                    countryList.add(" ");
                    System.out.println("Country : ");
                }

                // Check if Rating is present
                if (!cDriver.findElements(By.xpath("//strong[@class='rating-score _1554sdp1ey']")).isEmpty()) {
                    String rating = cDriver.findElement(By.xpath("//strong[@class='rating-score _1554sdp1ey']")).getText();
                    if (rating.isEmpty()) {
                        ratingList.add(" ");
                        System.out.println("Rating : ");
                    } else {
                        ratingList.add(rating);
                        System.out.println("Rating : " + rating);
                    }
                } else {
                    ratingList.add(" ");
                    System.out.println("Rating : ");
                }

                // Check if Delivery Date is present
                if (!cDriver.findElements(By.xpath("//time")).isEmpty()) {
                    String deliveryDate = cDriver.findElement(By.xpath("//time")).getText();
                    if (deliveryDate.isEmpty()) {
                        deliveryDateList.add(" ");
                        System.out.println("Delivery Date: ");
                    } else {
                        deliveryDateList.add(deliveryDate);
                        System.out.println("Delivery Date: " + deliveryDate);
                    }
                } else {
                    deliveryDateList.add(" ");
                    System.out.println("Delivery Date: ");
                }
                System.out.println("************************************************");
            }

            Map<String, List<String>> dataMap = new HashMap<>();

            if (nameList != null) {
                dataMap.put("nameList", nameList);
            }
            if (countryList != null) {
                dataMap.put("countryList", countryList);
            }
            if (ratingList != null) {
                dataMap.put("ratingList", ratingList);
            }
            if (deliveryDateList != null) {
                dataMap.put("deliveryDateList", deliveryDateList);
            }

                storeClient.writeInExcel(dataMap);

    }

}

