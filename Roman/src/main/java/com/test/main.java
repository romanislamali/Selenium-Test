package com.test;

import com.test.fiverr.FiverrUtility;
import com.test.yellowPage.YellowUtility;

import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		String endPoint = "https://www.yellowpages.com/search?search_terms=Hotels&geo_location_terms=United+New+Mexico%2C+Albuquerque%2C+NM";

//		FiverrUtility utility = new FiverrUtility();
		YellowUtility utility = new YellowUtility();
		utility.setUp(endPoint);
		utility.doActibity();
		System.out.println("Data collection successfull");

	}

}
