package parsing;

import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import module5.CityMarker;
import de.fhpotsdam.unfolding.providers.*;
//import de.fhpotsdam.unfolding.providers.Google.*;

import java.util.List;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


import de.fhpotsdam.unfolding.marker.Marker;

public class covidMap extends PApplet
{
	UnfoldingMap map;
	Map<String, Float> cases;
	List<Feature> countries;
	List<Marker> countryMarkers;
	
	public void setup()
	{
		size(1800,900,OPENGL);
		map = new UnfoldingMap(this, 50, 50, 1600, 800, new Microsoft.RoadProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		
		// Load country polygons and adds them as markers
		countries = GeoJSONReader.loadData(this, "countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		map.addMarkers(countryMarkers);
		
		// Load number of cases per country
		cases = loadCases();
		//System.out.println(CasesPerCountry);
		
		// Country markers are shaded according to number of cases (only once)
	    shadeCountries();
		
	}
	
	public void draw()
	{
		background(0);
		map.draw();
		addKey();
	}

	private void addKey() {
		// TODO Auto-generated method stub
		fill(255, 250, 240);
		
		int xbase = 25;
		int ybase = 50;
		
		rect(xbase, ybase, 150, 250);
		
		fill(0);
		textAlign(LEFT, CENTER);
		textSize(15);
		text("Covid-19 Key", xbase+25, ybase+25);
		
		fill(150, 30, 30);
		int tri_xbase = xbase + 35;
		int tri_ybase = ybase + 50;
		/*triangle(tri_xbase, tri_ybase-CityMarker.TRI_SIZE, tri_xbase-CityMarker.TRI_SIZE, 
				tri_ybase+CityMarker.TRI_SIZE, tri_xbase+CityMarker.TRI_SIZE, 
				tri_ybase+CityMarker.TRI_SIZE);*/

		fill(0, 0, 0);
		textAlign(LEFT, CENTER);
		text("Cases", tri_xbase + 15, tri_ybase);
		
		text("< 100", xbase+50, ybase+70);
		text("< 1k", xbase+50, ybase+90);
		text("< 10k", xbase+50, ybase+110);
		text("< 100k", xbase+50, ybase+130);
        text("< 200k", xbase+50, ybase+150);
        text("> 200k", xbase+50, ybase+170);

		
		fill(0,50,0);
		rect(xbase+30, ybase+65, 15,15);
		fill(200,200,0);
		rect(xbase+30, ybase+85, 15,15);
		fill(0,200,200);
		rect(xbase+30, ybase+105, 15,15);
		fill(0,00,200);
		rect(xbase+30, ybase+125, 15,15);
		fill(0,150,0);
		rect(xbase+30, ybase+145, 15,15);
		fill(200,00,0);
		rect(xbase+30, ybase+165, 15,15);
		
	}

	private void shadeCountries()
	{
		for (Marker marker : countryMarkers)
		{
			String c = marker.getStringProperty("name");
			//System.out.println(c);
		
			if(cases.containsKey(c))
			{
				if( cases.get(c)<=100) marker.setColor(color(0,50,0));
				if( cases.get(c)>100 && cases.get(c) <=1000)  marker.setColor(color(200,200,0));
				if( cases.get(c)>1000 && cases.get(c) <=10000)  marker.setColor(color(0,200,200));
				if( cases.get(c)>10000 && cases.get(c) <=100000)  marker.setColor(color(0,0,200));
				if( cases.get(c)>100000 && cases.get(c) <=200000)  marker.setColor(color(0,150,0));
				if( cases.get(c)>200000) marker.setColor(color(200,0,0));
					
			}
			
			else marker.setColor(color(200,200,0));
			
		}
	}
	
	
		//Helper method to load number of cases per country data from file
		
		private Map<String, Float> loadCases()
		{
			Map<String, Float> cases = new HashMap<String, Float>();
			
			String csv = "D:\\WHO-COVID-19-global-data.csv";
			String line = "";
			String splitBy = ",";
			
			ArrayList<ArrayList<String>> covid = new ArrayList<ArrayList<String>>();
			
			try   
			{  
			BufferedReader br = new BufferedReader(new FileReader(csv));  
			while ((line = br.readLine()) != null)   //returns a Boolean value  
			 {
				if(line.startsWith("09-07-2020"))  //to get data for this day only
				{ covid.add(new ArrayList<String>(Arrays.asList(line.split(splitBy))));
				}	
			 } br.close();
			}  catch (IOException e) { e.printStackTrace(); }
			for (int i = 0; i < covid.size(); i++) System.out.println(covid.get(i));
			
			for(ArrayList<String> row : covid) 
			{
				cases.put(row.get(2), Float.parseFloat(row.get(5)));
			}
				
			return cases;
			
		}
	
	
	

	
}
