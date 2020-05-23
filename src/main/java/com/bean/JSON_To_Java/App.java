package com.bean.JSON_To_Java;

import java.io.FileNotFoundException;
import java.net.URL;

import com.bean.Entity.Country;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws FileNotFoundException {

		try {

			System.out.println("Loading...");
			URL src = null;

			String countryName = "india";

			src = new URL("https://api.covid19api.com/total/country/" + countryName);

			/* 1. create object mapper */
			ObjectMapper objectMapper = new ObjectMapper();
			SimpleModule module = new SimpleModule();

			module.addDeserializer(Country.class, new CountryDeserializer());
			objectMapper.registerModule(module);

			/* 2. read JSON file.... convert to JAVA POJO */
			Country[] countryData = objectMapper.readValue(src, Country[].class);

			int confirmCasesTillNow = -1;

			int confirmDeaths = -1;

			int confirmRecovery = -1;

			int confirmActiveCases = -1;

			int count = 0;

			/* 3. print data */
			for (Country country : countryData) {

				int readConfirmValue = Integer.parseInt(country.getConfirmed());
				int readDeathValue = Integer.parseInt(country.getDeaths());
				int readRecoveryValue = Integer.parseInt(country.getRecovered());
				int readActiveCasesValue = Integer.parseInt(country.getActive());

				if (readConfirmValue > confirmCasesTillNow) {
					confirmCasesTillNow = readConfirmValue;
				}

				count++;

				/* Deaths */
				if (readDeathValue > confirmDeaths) {
					confirmDeaths = readDeathValue;
				}

				/* Recovery */
				if (readRecoveryValue > confirmRecovery) {
					confirmRecovery = readRecoveryValue;
				}

				/* Total active cases */
				if (readActiveCasesValue > confirmActiveCases) {
					confirmActiveCases = readActiveCasesValue;
				}

			}

			System.out.println("Total conrifm cases = " + confirmCasesTillNow);
			System.out.println("Total deaths cases = " + confirmDeaths);
			System.out.println("Total recovery cases = " + confirmRecovery);
			System.out.println("Total active cases  = " + confirmActiveCases);
			System.out.println("This data is based on the analysis of " + count + " days");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}
}
