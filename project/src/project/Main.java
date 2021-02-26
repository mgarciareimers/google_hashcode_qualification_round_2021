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
		
		arrangeIntersectionSchedules();
		
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
	private static void arrangeIntersectionSchedules() {
		System.out.println("> Preparing intersections (easy)...");
		for (Intersection intersection : data.getIntersections()) {
			for (Street street : intersection.getInStreets()) {
				intersection.getSchedules().add(new StreetSchedule(street.getName(), 1));
			}
		}
	}
}
