package parsing;

import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;
import java.util.Arrays;  
//import java.util.ArrayList;

public class covidCSV2 
{
	public static void main(String[] args)   
	{  
		String csv = "D:\\WHO-COVID-19-global-data.csv";
		//String pop = "D:\\World population.csv";
	String line = "";  
	String splitBy = ",";  
	try   
	{  
	//parsing a CSV file into BufferedReader class constructor  
	BufferedReader br = new BufferedReader(new FileReader(csv));  
	while ((line = br.readLine()) != null)   //returns a Boolean value  
	{  
	String[] covid = line.split(splitBy);    // use comma as separator  
	System.out.println(Arrays.toString(covid));  
	}  
	}   
	catch (IOException e)   
	{  
	e.printStackTrace();  
	}  
	}  

}
