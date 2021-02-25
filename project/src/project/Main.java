package project;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import project.commons.Constants;
import project.commons.Utils;
import project.models.Car;
import project.models.Data;
import project.models.Intersection;
import project.models.Street;
import project.models.StreetSchedule;

public class Main {
	private final static String USER_DIR = "user.dir";
	private final static String INPUT_DATA_PATH = "/in";
	private final static String OUTPUT_DATA_PATH = "/out/";

	private static File inputFile = null;
	private static Data data = null;
	
	public static void main(String[] args) {
		selectInputFile();

		System.out.println("\n> SelectedFile: ".concat(inputFile.getName()));
		
		data = Utils.readFile(inputFile.getPath());
		
		arrangeIntersectionSchedulesEasy();
		//arrangeIntersectionSchedules();
		
		Utils.writeFile(data, System.getProperty(USER_DIR).concat(OUTPUT_DATA_PATH).concat(inputFile.getName()));
	}
	
	// Method that asks the user for the file to read and selects it saving it in "inputFile" variable.
	private static void selectInputFile() {
		File[] files = new File(System.getProperty(USER_DIR).concat(INPUT_DATA_PATH)).listFiles();

		if (files == null || files.length <= 0) {
			System.out.println("> No files have been found in folder 'in'.");
			return;
		}

		Arrays.sort(files);

		String text = "> Select the file you want to read (type number):\n";

		for (int i = 0; i < files.length; i++) {
			text = text.concat(Constants.BREAK_LINE).concat("> ").concat(Integer.toString(i + 1).concat(Constants.DOT)
					.concat(Constants.ONE_SPACE).concat(files[i].getName()));
		}

		text = text.concat("\n\n> Enter number (1 - ".concat(Integer.toString(files.length)).concat("): "));
		
		System.out.println(text);

		Scanner input = new Scanner(System.in);

		try {
			String userInput = input.nextLine();

			inputFile = files[Integer.parseInt(userInput) - 1];
			input.close();
		} catch (Exception e) {
			System.out.println("\n> Please select a valid number (1 - ".concat(Integer.toString(files.length)).concat(").\n"));

			selectInputFile();
		}
	}
	
	// Method that arranges the intersection schedules (easy).
	private static void arrangeIntersectionSchedulesEasy() {
		System.out.println("> Preparing intersections (easy)...");
		for (Intersection intersection : data.getIntersections()) {
			for (Street street : intersection.getInStreets()) {
				intersection.getSchedules().add(new StreetSchedule(street.getName(), 1));
			}
		}
	}
	
	// Method that arranges the intersection schedules.
	private static void arrangeIntersectionSchedules() {
		System.out.println("> Preparing intersections...");
		
		for (Car car : data.getCars()) {
			int arrivalTime = 0;
			int streetIndex = 0;
					
			for (int intersectionId : car.getIntersectionIds()) {
				data.getIntersections().get(intersectionId).getArrivals().add(new StreetSchedule(car.getStreets().get(streetIndex).getName(), arrivalTime));
				
				arrivalTime = arrivalTime + car.getStreets().get(streetIndex).getCrossingDuration();
				streetIndex++;
			}
		}
		
		Collections.sort(data.getIntersections(), new Comparator<Intersection>() {
			@Override
			public int compare(Intersection i1, Intersection i2) {
				Integer i1Size = i1.getArrivals().size();
				Integer i2Size = i2.getArrivals().size();
				
				return i1Size.compareTo(i2Size);
			}
		});	
	
		for (Intersection intersection : data.getIntersections()) {
			for (int i = 0; i < intersection.getArrivals().size(); i++) {
				boolean equal = false;
				for (int j = 0; j < intersection.getArrivals().size(); j++) {
					if (i != j && intersection.getArrivals().get(i).getName().equals(intersection.getArrivals().get(j).getName())) {
						equal = true;
						break;
					}
					
				}
				
				if (!equal) {					
					intersection.getSchedules().add(new StreetSchedule(intersection.getArrivals().get(i).getName(), 1));
				}
			}
		}
	}
}
