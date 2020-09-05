package parsing;

import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class covidAL 
{
	public static void main(String args[])
	{

		
		ArrayList<ArrayList<String>> data = covidData();
		//Date_reported	 Country_code	 Country	 WHO_region	 New_cases	 Cumulative_cases	 New_deaths	 Cumulative_deaths

		for (int i = 0; i < data.size(); i++) System.out.println(data.get(i));
	
	 }
	
	
	public static ArrayList<ArrayList<String>> covidData()
	{
		
		String csv = "D:\\WHO-COVID-19-global-data.csv";
		String line = "";
		String splitBy = ",";
		
		ArrayList<ArrayList<String>> covid = new ArrayList<ArrayList<String>>();
		
		try   
		{  
		//parsing a CSV file into BufferedReader class constructor  
		
		BufferedReader br = new BufferedReader(new FileReader(csv));  
		while ((line = br.readLine()) != null)   //returns a Boolean value  
		 {
			if(line.startsWith("09-07-2020"))  //to get data for this day only
			{ covid.add(new ArrayList<String>(Arrays.asList(line.split(splitBy))));
			}	
		 } br.close();
		}  catch (IOException e) { e.printStackTrace(); }
		
		
		//printing each row of AL
		//for (int i = 0; i < covid.size(); i++) System.out.println(covid.get(i));
		
		return covid;
	}
	
	//try again by creating your own csv files 
	public static ArrayList<ArrayList<String>> popData()  // works awesome
	{
		String population = "D:\\World population.csv";
		String line = "";
		String splitBy = "[,\"]+";
		
		
		//ArrayList<ArrayList<String>> covid = covidData();
		ArrayList<ArrayList<String>> pop = new ArrayList<ArrayList<String>>();
		
		//System.out.println(covid.get(k).get(2));
		try
		{
			//parsing a CSV file into BufferedReader class constructor  
			
			BufferedReader br = new BufferedReader(new FileReader(population));  
			while ((line = br.readLine()) != null )   //returns a Boolean value  
			{
				pop.add(new ArrayList<String>(Arrays.asList(line.split(splitBy))));
			}
			br.close();
		}   catch (IOException e) { e.printStackTrace(); }
		
		//printing each row of AL
		for (int i = 0; i < pop.size(); i++) System.out.println(pop.get(i));
		
		System.out.println(pop.get(5).get(1));
		System.out.println(pop.get(5).get(65));

		return pop;
	}
	
	public static ArrayList<ArrayList<String>> finalList()  // doesn't work coz World Bank doesn't provide data sorted. 
	                                                        
	{
		ArrayList<ArrayList<String>> covid = covidData();
		ArrayList<ArrayList<String>> pop = popData();
		
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		data.add(new ArrayList<String>( Arrays.asList( "Country", "number of cases", "population") ));
		//System.out.println(data);
		//Collections.sort(data);
		int i=0, j=5;
		while( i<covid.size() && j<pop.size() )
		{
			String c1 = covid.get(i).get(2);
			String c2 = pop.get(j).get(1);
			
			//System.out.println(i +" " +j);
			//System.out.println(c1 +" " +c2);
			
			if( c1.equalsIgnoreCase(c2) && c1!="Eritrea")
			{
				String cases = covid.get(i).get(5);
				String popu = pop.get(j).get(65);
				
				data.add(new ArrayList<String>( Arrays.asList(c1,cases,popu) ) );
				i++;
				j++;
			}
			else j++;
			
			//System.out.println(i +" " +j);
			//i++; j++;
		}
		//for (int k = 0; k < data.size(); k++) System.out.println(data.get(k));
		
		return data;
	}
}

