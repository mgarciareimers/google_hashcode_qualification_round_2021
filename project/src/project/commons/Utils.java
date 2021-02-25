package project.commons;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.models.Car;
import project.models.Data;
import project.models.Intersection;
import project.models.Street;
import project.models.StreetSchedule;

public class Utils {		
	// Method that reads the file.
	public static Data readFile(String filePath) {
		
		try {
			String [] fileData; // Array of strings that contains the different values.
			
			// Open the file and read it.
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			
			// Read the next line;
			System.out.println("> Reading data...");
			fileData = bufferedReader.readLine().split(Constants.ONE_SPACE);
			
			Data data = new Data(fileData[0], fileData[1], fileData[2], fileData[3], fileData[4]);
			
			// Define streets.			
			for (int i = 0; i < data.getNumberStreets(); i++) {
				fileData = bufferedReader.readLine().split(Constants.ONE_SPACE);
				data.getStreets().put(fileData[2], new Street(fileData[0], fileData[1], fileData[2], fileData[3]));
			}
			
			// Define cars.			
			for (int i = 0; i < data.getNumberCars(); i++) {
				fileData = bufferedReader.readLine().split(Constants.ONE_SPACE);
				
				int numberStreets = Integer.parseInt(fileData[0]);
				
				// Gets streets of car.
				List<Street> carStreets = new ArrayList<>();
				List<Integer> intersectionIds = new ArrayList<>();
					
				for (int j = 1; j <= numberStreets; j++) {
					carStreets.add(data.getStreets().get(fileData[j]));
					intersectionIds.add(data.getStreets().get(fileData[j]).getEndIntersection());
				} 
				
				data.getCars().add(new Car(numberStreets, carStreets, intersectionIds));
			}
			
			Collections.sort(data.getCars(), new Comparator<Car>() {
				@Override
				public int compare(Car c1, Car c2) {
					Integer c1MinDuration = c1.getMinDuration();
					Integer c2MinDuration = c2.getMinDuration();
					
					return c1MinDuration.compareTo(c2MinDuration);
				}
			});
			
			// Define intersections.
			for (int i = 0; i < data.getNumberIntersections(); i++) {
				data.getIntersections().add(new Intersection(i));
			}
			
			List<Street> streets = new ArrayList<Street>(data.getStreets().values());
			
			for (Street street : streets) {
				data.getIntersections().get(street.getBeginningIntersection()).getOutStreets().add(street);
				data.getIntersections().get(street.getEndIntersection()).getInStreets().add(street);
			}
			
			// Close the file.
			bufferedReader.close();
			
			return data;
		} catch (IOException e) {
			System.out.println("> Error while reading the input file: " + e);
			return null;
		}
	}
	
	// Method that writes the output file, creating it previously in case it does not exist.
	public static void writeFile(Data data, String filePath) {
		FileWriter file = null;
		PrintWriter printWriter = null;
		
		System.out.println("> Writing output file...");
		
		try {
			// Create the printer.
			file = new FileWriter(filePath);
			printWriter = new PrintWriter(file);
			
			// Create the output.
			int numberIntersections = 0;
			
			String output = Constants.EMPTY_STRING;
			
			for (Intersection intersection : data.getIntersections()) {
				if (intersection.getSchedules().size() > 0) {
					numberIntersections++;
					output = output.concat(Constants.BREAK_LINE).concat(Integer.toString(intersection.getId()));
					output = output.concat(Constants.BREAK_LINE).concat(Integer.toString(intersection.getInStreets().size()));
					
					for (StreetSchedule schedule : intersection.getSchedules()) {
						output = output.concat(Constants.BREAK_LINE).concat(schedule.getName()).concat(Constants.ONE_SPACE).concat(Integer.toString(schedule.getDuration()));
					}
				}
			}
			
			// Print the output in the file.
			printWriter.print(Integer.toString(numberIntersections).concat(output));
		} catch (IOException e) {
			System.out.println("> Error while writing the output file: " + e);
		} finally {
			try {
				printWriter.close();
				file.close();
				System.out.println("> Output file has been written!");
			} catch (IOException e) {
				System.out.println("> Error while closing the output file: " + e);
			}
		}
	}
}
