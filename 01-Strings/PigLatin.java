
// Arnav Jain:   
// 9/25: 
import java.util.*;
import java.lang.*;
import java.io.*;

public class PigLatin {
	public static void main(String[] args) {
		//part_1_using_pig();
		part_2_using_piglatenizeFile();

		/* extension only */
		String pigLatin = pig("What!?");
		System.out.print(pigLatin + "\t\t" + pigReverse(pigLatin)); //Yahwta!?
		pigLatin = pig("{(Hello!)}");
		System.out.print("\n" + pigLatin + "\t\t" + pigReverse(pigLatin));
		//{(Yaholle!)}
		pigLatin = pig("\"McDonald???\"");
		System.out.println("\n" + pigLatin + " " +
		pigReverse(pigLatin));//"YaDcmdlano???"*/
	}

	public static void part_1_using_pig() {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("\nWhat word? ");
			String s = sc.next();
			if (s.equals("-1")) {
				System.out.println("Goodbye!");
				System.exit(0);
			}
			String p = pig(s);
			System.out.println(p);
		}
	}

	public static final String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
	public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	public static final String vowels = "AEIOUaeiou";
	public static final String vowelsy = "AEIOUYaeiouy";

	public static String pig(String s) {
		if (s.length() == 0)
			return "";

		// remove and store the beginning punctuation

		String begin = "";
		boolean check = true;
		int index = 0;
		boolean done = false;
		while (check) {
			done = false;
			for (int i = 0; i < punct.length(); i++) {
				if (s.charAt(index) == punct.charAt(i)) {
					begin = begin + s.charAt(index);
					done = true;
					index++;
					break;
				} else if (i == 26 && !done) {
					check = false;
				}
			}
		}

		// remove and store the ending punctuation
		String end = "";
		check = true;
		index = s.length() - 1;
		done = false;
		while (check) {
			done = false;
			for (int i = 0; i < punct.length(); i++) {
				if (s.charAt(index) == punct.charAt(i)) {
					end = s.charAt(index) + end;
					done = true;
					index--;
					break;
				} else if (i == 26 && !done) {
					check = false;
				}
			}
		}
		s = s.substring(begin.length(), s.length() - end.length());
		boolean capital = Character.isUpperCase(s.charAt(0));
		if(capital) {
			s = s.substring(0,1).toLowerCase() + s.substring(1);
		}
		// START HERE with the basic case:
		// find the index of the first vowel
		// y is a vowel if it is not the first letter
		// qu
		index = 0;
		for (int i = 0; i < vowels.length(); i++) {
			if (s.charAt(index) == vowels.charAt(i)) {
				s = s + "way";
				return begin + s + end;
			}
		}

		// if no vowel has been found
		boolean test = false;
		for (int i = 1; i < vowelsy.length(); i++) {
			String v = vowelsy.substring(i, i + 1);
			test = test || s.contains(v);
		}
		if (!test) {
			return ("**** NO VOWEL ****");
		}

		int firstindex = 0;
		for (int i = 1; i < s.length(); i++) {
			if(contains1(s.substring(i, i + 1), vowelsy)) {
				firstindex = i;
				break;
			}
		}
		if (firstindex <= s.length() - 1 && s.substring(firstindex - 1, firstindex + 1).toLowerCase().equals("qu")
				&& contains1(s.substring(firstindex + 1, firstindex + 2), vowelsy)) {
			s =  s.substring(firstindex + 1) + s.substring(0, firstindex + 1) + "ay";
			if (capital) {
				return begin + s.substring(0, 1).toUpperCase() + s.substring(1) + end;
			}
			else {
				return begin + s + end;
			}
		}
		else {
			s = s.substring(firstindex) + s.substring(0,firstindex) + "ay";
			if (capital) {
				return begin + s.substring(0, 1).toUpperCase() + s.substring(1) + end;
			}
			else {
				return begin + s + end;
			}
		}

		// is the first letter capitalized?


		// return the piglatinized word


	}

	public static boolean contains1(String str, String contains) {
		for (int x = 0; x < contains.length(); x++) {
			if (str.equals(contains.substring(x, x + 1))) {
				return true;
			}
		}
		return false;
	}

	public static void part_2_using_piglatenizeFile() {
		Scanner sc = new Scanner(System.in);
		System.out.print("input filename including .txt: ");
		String fileNameIn = sc.next();
		System.out.print("output filename including .txt: ");
		String fileNameOut = sc.next();
		piglatenizeFile(fileNameIn, fileNameOut);
		System.out.println("Piglatin done!");
	}

	/******************************
	 * piglatinizes each word in each line of the input file precondition: both
	 * fileNames include .txt postcondition: output a piglatinized .txt file
	 ******************************/
	public static void piglatenizeFile(String fileNameIn, String fileNameOut) {
		Scanner infile = null;
		try {
			infile = new Scanner(new File(fileNameIn));
		} catch (IOException e) {
			System.out.println("oops");
			System.exit(0);
		}
		
		PrintWriter outfile = null;
		try {
			outfile = new PrintWriter(new FileWriter(fileNameOut));
		} catch (IOException e) {
			System.out.println("File not created");
			System.exit(0);
		}
		while(infile.hasNextLine()) {
			String line = infile.nextLine();
			String[] words = line.split("\\s+");
			for(int j = 0; j < words.length; j++) {
				outfile.write(pig(words[j]) + " ");
			}
			outfile.write("\n");
		}

		// process each word in each line

		outfile.close();
		infile.close();
	}

	/**
	 * EXTENSION: Output each PigLatin word in reverse, preserving before-and-after
	 * punctuation.
	 */
	public static String pigReverse(String s) {
		String begin = "";
		boolean check = true;
		int index = 0;
		boolean done = false;
		while (check) {
			done = false;
			for (int i = 0; i < punct.length(); i++) {
				if (s.charAt(index) == punct.charAt(i)) {
					begin = begin + s.charAt(index);
					done = true;
					index++;
					break;
				} else if (i == 26 && !done) {
					check = false;
				}
			}
		}

		// remove and store the ending punctuation
		String end = "";
		check = true;
		index = s.length() - 1;
		done = false;
		while (check) {
			done = false;
			for (int i = 0; i < punct.length(); i++) {
				if (s.charAt(index) == punct.charAt(i)) {
					end = s.charAt(index) + end;
					done = true;
					index--;
					break;
				} else if (i == 26 && !done) {
					check = false;
				}
			}
		}
		s = s.substring(begin.length(), s.length() - end.length());
		boolean capital = Character.isUpperCase(s.charAt(0));
		if(capital) {
			s = s.substring(0,1).toLowerCase() + s.substring(1);
		}
		String out = "";
		for(int a = s.length() - 1; a >= 0; a--) {
			out = out + s.substring(a, a + 1);
		}
		if(capital) {
			out = out.substring(0,1).toUpperCase() + out.substring(1);
		}
		return begin + out + end;

	}
}