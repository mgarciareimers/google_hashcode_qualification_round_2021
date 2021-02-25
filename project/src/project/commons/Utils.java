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
import project.models.Street;

public class Utils {		
	// Method that reads the file.
	public static Data readFile(String filePath) {
		
		try {
			String [] fileData; // Array of strings that contains the different values.
			
			// Open the file and read it.
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			
			// Read the next line;
			System.out.println("> Reading first line...");
			fileData = bufferedReader.readLine().split(Constants.ONE_SPACE);
			
			Data data = new Data(fileData[0], fileData[1], fileData[2], fileData[3], fileData[4]);
			
			// Define streets.
			Map<String, Street> mapStreets = new HashMap<>();
						
			for (int i = 0; i < data.getNumberStreets(); i++) {
				fileData = bufferedReader.readLine().split(Constants.ONE_SPACE);
				data.getStreets().add(new Street(fileData[0], fileData[1], fileData[2], fileData[3]));
				mapStreets.put(data.getStreets().get(data.getStreets().size() - 1).getName(), data.getStreets().get(data.getStreets().size() - 1));
			}
			
			// Define cars.			
			for (int i = 0; i < data.getNumberCars(); i++) {
				fileData = bufferedReader.readLine().split(Constants.ONE_SPACE);
				
				int numberStreets = Integer.parseInt(fileData[0]);
				
				// Gets streets of car.
				List<Street> carStreets = new ArrayList<>();
					
				for (int j = 1; j <= numberStreets; j++) {
					carStreets.add(mapStreets.get(fileData[j]));
				} 
				
				
				data.getCars().add(new Car(numberStreets, carStreets));
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
	public static void writeFile(String filePath) {
		FileWriter file = null;
		PrintWriter printWriter = null;
		
		try {
			// Create the printer.
			file = new FileWriter(filePath);
			printWriter = new PrintWriter(file);
			
			// Create the output.
			String output = Integer.toString(0);
			
			for (int i = 1; i < 10; i++) {
				output = output.concat(Constants.BREAK_LINE).concat(Integer.toString(i));
			}
			
			// Print the output in the file.
			printWriter.print(output);
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
