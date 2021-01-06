// Name: 
// Date: 

public class Sentence_Driver {
	public static void main(String[] args) {
		System.out.println("PALINDROME TESTER");

		Sentence s = new Sentence("abcdxcba");
		System.out.println(s.getSentence());
		System.out.println(s.getNumWords());
		System.out.println(s.isPalindrome());
		System.out.println();

		s = new Sentence("A Santa lived as a devil at NASA.");
		System.out.println(s.getSentence());
		System.out.println(s.getNumWords());
		System.out.println(s.isPalindrome());
		System.out.println();

		s = new Sentence("Flo, gin is a sin! I golf.");
		System.out.println(s.getSentence());
		System.out.println(s.getNumWords());
		System.out.println(s.isPalindrome());
		System.out.println();

		s = new Sentence("Eva, can I stab bats in a cave?");
		System.out.println(s.getSentence());
		System.out.println(s.getNumWords());
		System.out.println(s.isPalindrome());
		System.out.println();

		s = new Sentence("Madam, I'm Adam.");
		System.out.println(s.getSentence());
		System.out.println(s.getNumWords());
		System.out.println(s.isPalindrome());
		System.out.println();

		// Lots more test cases. Test every line of code. Test
		// the extremes, test the boundaries. How many test cases do you need?

	}
}

class Sentence {
	private String mySentence;
	private int myNumWords;

	// Precondition: str is not empty.
	// Words in str separated by exactly one blank.
	public Sentence(String str) {
		mySentence = str;
		String[] arr = mySentence.split(" ");
		myNumWords = arr.length;
	}

	public int getNumWords() {
		
		return myNumWords;
	}

	public String getSentence() {
		return mySentence;
	}

	// Returns true if mySentence is a palindrome, false otherwise.
	public boolean isPalindrome() {
		mySentence = lowerCase(mySentence);
		mySentence = removeBlanks(mySentence);
		mySentence = removePunctuation(mySentence);
		return(isPalindrome(mySentence, 0, mySentence.length() - 1));
	}

	// Precondition: s has no blanks, no punctuation, and is in lower case.
	// Returns true if s is a palindrome, false otherwise.
	public static boolean isPalindrome(String s, int start, int end) {
		if(start == end) {
			return true;
		}
		else if(start + 1 == end && s.charAt(start) == s.charAt(end)) {
			return true;
		}
		else if(s.charAt(start) == s.charAt(end)) {
			return isPalindrome(s, start + 1, end - 1);
		}
		else {
			return false;
		}
	}

	// Returns copy of String s with all blanks removed.
	// Postcondition: Returned string contains just one word.
	public static String removeBlanks(String s) {
		int i =0;
		while(i < s.length()) {
			if(s.charAt(i) == ' ') {
				s = s.substring(0,i) + s.substring(i + 1);
			}
			else {
				i++;
			}
		}
		return s;
	}

	// Returns copy of String s with all letters in lowercase.
	// Postcondition: Number of words in returned string equals
	// number of words in s.
	public static String lowerCase(String s) {
		return s.toLowerCase();
	}

	// Returns copy of String s with all punctuation removed.
	// Postcondition: Number of words in returned string equals
	// number of words in s.
	public static String removePunctuation(String s) {
		String punct = ".,'?!:;\"(){}[]<>";
		int i = 0;
		while(i < s.length()) {;
			boolean bool = true;
			for(int j = 0; j < punct.length(); j++) {
				if(s.charAt(i) == punct.charAt(j)) {
					if(i == 0) {
						s = s.substring(1);
					}
					else {
						s = s.substring(0,i) + s.substring(i+1);
					}
					bool = false;
					break;

				}
			}
			if(bool) {
				i++;
			}
		}
		return s;
	}
}

/*****************************************
 * 
 * PALINDROME TESTER "Hello there!" she said. 4 false
 * 
 * A Santa lived as a devil at NASA. 8 true
 * 
 * Flo, gin is a sin! I golf. 7 true
 * 
 * Eva, can I stab bats in a cave? 8 true
 * 
 * Madam, I'm Adam. 3 true
 */