
// Name:
// Date:
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
//here any additional imports that you may need

public class Cemetery {
	public static void main(String[] args) throws FileNotFoundException {
		//File file = new File("cemetery_short.txt");
		File file = new File("cemetery.txt");
		int numEntries = countEntries(file);
		Person[] cemetery = readIntoArray(file, numEntries);
		// see what you have
		
		for (int i = 0; i < cemetery.length; i++)
			System.out.println(cemetery[i]);
		/*
		int min = locateMinAgePerson(cemetery);
		int max = locateMaxAgePerson(cemetery);
		System.out.println("\nIn the St. Mary Magdelene Old Fish Cemetery --> ");
		System.out.println("Name of youngest person: " + cemetery[min].getName());
		System.out.println("Age of youngest person: " + cemetery[min].getAge());
		System.out.println("Name of oldest person: " + cemetery[max].getName());
		System.out.println("Age of oldest person: " + cemetery[max].getAge());*/
		// you may create other testing cases here
		// comment them out when you submit your file to Grade-It

	}

	/*
	 * Counts and returns the number of entries in File f. Returns 0 if the File f
	 * is not valid. Uses a try-catch block.
	 * 
	 * @param f -- the file object
	 */
	public static int countEntries(File f) throws FileNotFoundException {
		int lines = 0;
		Scanner infile = new Scanner(f);
		while (infile.hasNextLine()) {
			lines++;
			infile.nextLine();
		}
		return lines;
	}

	/*
	 * Reads the data from file f (you may assume each line has same allignment).
	 * Fills the array with Person objects. If File f is not valid return null.
	 * 
	 * @param f -- the file object
	 * 
	 * @param num -- the number of lines in the File f
	 */
	public static Person[] readIntoArray(File f, int num) throws FileNotFoundException {
		Scanner infile = new Scanner(f);
		Person[] output = new Person[num];
		for(int i = 0; i < num; i++) {
			String input = infile.nextLine();
			output[i] = makeObjects(input);
		}
		return output;
	}

	/*
	 * A helper method that instantiates one Person object.
	 * 
	 * @param entry -- one line of the input file. This method is made public for
	 * gradeit testing purposes. This method should not be used in any other
	 * class!!!
	 */
	public static Person makeObjects(String entry) {
		int j = 0;
		while(j < entry.length() - 2) {
			if(entry.substring(j, j + 2).equals("  ")) {
				entry = entry.substring(0,j) + " " + entry.substring(j + 2);
			}
			else {
				j++;
			}
				
		}
		String[] data = entry.split(" ");
		Person person;
		if(Character.isUpperCase(data[1].charAt(data[1].length() - 1))){
			person = new Person(data[0] +" " +  data[1], data[2] + data[3] + data[4], data[5]);
		}
		else{
			person = new Person(data[0]  + " " + data[1]  + " "+ data[2], data[3] + data[4] + data[5], data[6]);
		}

		return person;
	}

	/*
	 * Finds and returns the location (the index) of the Person who is the youngest.
	 * (if the array is empty it returns -1) If there is a tie the lowest index is
	 * returned.
	 * 
	 * @param arr -- an array of Person objects.
	 */
	public static int locateMinAgePerson(Person[] arr) {
		int minimumAge = 0;
		double min = 1000;
		for(int i = 0; i < arr.length; i++) {
			if(min > arr[i].getAge()) {
				min = arr[i].getAge();
				minimumAge = i;
			}
		}
		return minimumAge;
		

	}

	/*
	 * Finds and returns the location (the index) of the Person who is the oldest.
	 * (if the array is empty it returns -1) If there is a tie the lowest index is
	 * returned.
	 * 
	 * @param arr -- an array of Person objects.
	 */
	public static int locateMaxAgePerson(Person[] arr) {
		int maxAge = 0;
		double max = 0;
		for(int i = 0; i < arr.length; i++) {
			if(max < arr[i].getAge()) {
				max = arr[i].getAge();
				maxAge = i;
			}
		}
		return maxAge;
		


	}
}

class Person {
	// constant that can be used for formatting purposes
	private static final DecimalFormat df = new DecimalFormat("0.0000");
	private String name;
	private String burialDate;
	private String age;
	/* private fields */

	/*
	 * a three-arg constructor
	 * 
	 * @param name, burialDate may have leading or trailing spaces It creates a
	 * valid Person object in which each field has the leading and trailing spaces
	 * eliminated
	 */
	public Person(String name, String burialDate, String age) {
			this.name = name;
			this.burialDate = burialDate;
			this.age = age;
	}

	public double getAge() {
		return calculateAge(age);
	}


	public String getName() {
		return name;
	}
	/*
	 * any necessary accessor methods (at least "double getAge()" and
	 * "String getName()" ) make sure your get and/or set methods use the same data
	 * type as the field
	 */

	/*
	 * handles the inconsistencies regarding age
	 * 
	 * @param a = a string containing an age from file. Ex: "12", "12w", "12d"
	 * returns the age transformed into year with 4 decimals rounding
	 */
	public double calculateAge(String a) {
		if(age.endsWith("w")) {
			return Double.parseDouble((age.substring(0,age.length() - 1))) / 52.0;
		}
		else if(age.endsWith("d")) {
			return Double.parseDouble((age.substring(0,age.length() - 1))) / 365.0;
		}
		else {
			return Double.parseDouble((age.substring(0,age.length())));
		}
	}
}

/******************************************
 * 
 * John William ALLARDYCE, 17 Mar 1844, 2.9 Frederic Alex. ALLARDYCE, 21 Apr
 * 1844, 0.17 Philip AMIS, 03 Aug 1848, 1.0 Thomas ANDERSON, 06 Jul 1845, 27.0
 * Edward ANGEL, 20 Nov 1842, 22.0 Lucy Ann COLEBACK, 23 Jul 1843, 0.2685 Thomas
 * William COLLEY, 08 Aug 1833, 0.011 Joseph COLLIER, 03 Apr 1831, 58.0
 * 
 * In the St. Mary Magdelene Old Fish Cemetery --> Name of youngest person:
 * Thomas William COLLEY Age of youngest person: 0.011 Name of oldest person:
 * Joseph COLLIER Age of oldest person: 58.0
 * 
 **************************************/