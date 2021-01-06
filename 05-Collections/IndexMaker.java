// Name:      
// Date:
// This program takes a text file, creates an index (by line numbers)
// for all the words in the file and writes the index
// into the output file.  The program prompts the user for the file names.

import java.util.*;
import java.io.*;

public class IndexMaker {
	public static void main(String[] args) throws IOException {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("\nEnter input file name: ");
		String inFileName = keyboard.nextLine().trim();
		Scanner inputFile = new Scanner(new File(inFileName));
		String outFileName = "fishIndex.txt";
		PrintWriter outputFile = new PrintWriter(new FileWriter(outFileName));
		indexDocument(inputFile, outputFile);
		inputFile.close();
		outputFile.close();
		System.out.println("Done.");
	}

	public static void indexDocument(Scanner inputFile, PrintWriter outputFile) {
		DocumentIndex index = new DocumentIndex();
		String line = null;
		int lineNum = 0;
		while (inputFile.hasNextLine()) {
			lineNum++;
			index.addAllWords(inputFile.nextLine(), lineNum);
		}
		for (IndexEntry entry : index)
			outputFile.println(entry);
	}
}

class DocumentIndex extends ArrayList<IndexEntry> {
	// constructors

	/**
	 * extracts all the words from str, skipping punctuation and whitespace and for
	 * each word calls addWord(). In this situation, a good way to extract while
	 * also skipping punctuation is to use String's split method, e.g.,
	 * str.split("[., \"!?]")
	 */
	public void addAllWords(String str, int lineNum) {
		String[] words = str.split(("[., \"!?]"));
		for (int i = 0; i < words.length; i++) {
			if(!words[i].equals("")) {
				addWord(words[i],lineNum);
			}
		}
	}

	/**
	 * calls foundOrInserted, which returns a position. At that position, updates
	 * that IndexEntry's list of line numbers with lineNum.
	 */
	public void addWord(String word, int lineNum) {
		this.get(foundOrInserted(word)).add(lineNum);

	}

	/**
	 * traverses this DocumentIndex and compares word to the words in the IndexEntry
	 * objects in this list, looking for the correct position of word. If an
	 * IndexEntry with word is not already in that position, the method creates and
	 * inserts a new IndexEntry at that position. The method returns the position of
	 * either the found or the inserted IndexEntry.
	 */
	private int foundOrInserted(String word) {
		for (int i = 0; i < this.size(); i++) {
			String s = this.get(i).getWord();
			if (s.equals(word.toUpperCase())) {
				return i;
			} else {
				if (s.compareTo(word.toUpperCase()) > 0) {
					this.add(i, new IndexEntry(word));
					return i;
				}
			}
		}
		this.add(new IndexEntry(word));
		return this.size() - 1;
	}
}

class IndexEntry implements Comparable<IndexEntry> {
	ArrayList<Integer> list;
	String word;

	public IndexEntry(String word1) {
		word = word1.toUpperCase();
		list = new ArrayList<Integer>();
		// TODO Auto-generated constructor stub
	}

	// fields

	// constructors

	/**
	 * appends num to numsList, but only if it is not already in that list.
	 */
	public void add(int num) {
		if (!(list.contains(num))) {
			list.add(num);
		}
	}

	/** this is a standard accessor method */
	public String getWord() {
		return word;
	}

	/** returns a string representation of this Index Entry. */
	public String toString() {
		String out = word + " ";
		for(int i = 0; i < list.size() - 1; i++) {
			out = out + list.get(i) + ", ";
		}
		return out + list.get(list.size() - 1);
	}

	public int compareTo(IndexEntry o) {
		// TODO Auto-generated method stub
		return this.word.compareTo(o.getWord());
	}
}
