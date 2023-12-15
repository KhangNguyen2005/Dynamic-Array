package assign06;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class represents a book (read from file), specifically all words in a
 * book. The instance methods provide various book stats, such as number of
 * words.
 * 
 * Note that a word is considered to be any sequence of symbols separated by
 * whitespace. For example, a book file containing "Hello, world." has two
 * words: "Hello," and "world."
 *
 * @author Prof. Martin and Khang Hoang Nguyen
 * @version Oct 19 2023
 */
public class Book {

	// DO NOT ADD OR REMOVE ANY INSTANCE VARIABLES
	private DynamicArray words;
	private int characterCount;
	private String shortestWord;
	private String longestWord;

	/**
	 * Creates a Book object, given a filename for the book file to read. If no file
	 * with the given name exists, creates an empty book.
	 * 
	 * @param filename - the name of the file containing the book
	 * 
	 *                 DO NOT MODIFY THIS METHOD
	 */
	public Book(String filename) {
		words = new DynamicArray();

		try (Scanner fileInput = new Scanner(new File(filename))) {
			while (fileInput.hasNext())
				words.append(fileInput.next());
		} catch (FileNotFoundException e) {
			System.out.println("deodc");
		}

		characterCount = -1;
		shortestWord = null;
		longestWord = null;
	}

	public int getWordCount() {
		/**
		 * Returns the number of words in the text.
		 */
		int wordCount = 0;
		for (int i = 0; i < words.size(); i++) {
			wordCount++;
		}
		return wordCount;

	}

	/**
	 * Gets the number of characters in this book.
	 * 
	 * @return the number of characters in this book
	 */
	public int getCharacterCount() {

		if (characterCount == -1) {
			characterCount = 0;
			for (int index = 0; index < words.size(); index++)
				characterCount += words.getElement(index).length();
		}

		return characterCount;
	}

	public double getAverageWordLength() {
		/**
		 * Returns the average word length in the text. The formula is the total number
		 * of character divide by the total amount of words in the book.
		 */
		if (words.size() == 0) {
			return 0;
		} else {
			getCharacterCount();
			double result = (double) this.characterCount / this.words.size();
			return result;
		}
	}

	public String getShortestWord() {
		/**
		 * Returns the shortest word in the text. In the event of a tie for the shortest
		 * word, use the shortest word that occurs first in the book. If there are no
		 * words in the book, return null.
		 */

		String shortestWord = words.getElement(0);

		for (int index = 0; index < words.size(); index++) {
			if (shortestWord == null) {
				shortestWord = words.getElement(index);
			} else if (words.getElement(index).length() < shortestWord.length()) {
				shortestWord = words.getElement(index);
			}

		}
		if (words.getElement(0).length() == 0) {

			return null;
		}
		this.shortestWord = shortestWord;
		return this.shortestWord;
	}

	public String getLongestWord() {
		/**
		 * Returns the longest word in the text. In the event of a tie for the longest
		 * word, use the longest word that occurs first in the book. If there are no
		 * words in the book, return null.
		 */

		String longestWord = words.getElement(0);
		for (int index = 0; index < words.size(); index++) {
			if (words.getElement(index).length() > longestWord.length()) {
				longestWord = words.getElement(index);
			}
			if (words.getElement(index).length() == 0) {
				return null;
			}
		}
		this.longestWord = longestWord;
		return this.longestWord;
	}

	public int getTargetWordCount(String targetWord) {
		/**
		 * Returns the number of times the given target word appears in the text. Count
		 * and return the number of times that targetWord appears in the book
		 */

		int wordAppear = 0;
		for (int i = 0; i < words.size(); i++) {
			if (words.getElement(i).equals(targetWord)) {
				wordAppear++;
			}
		}
		return wordAppear;
	}

	/**
	 * Determines the word that would come immediately before the given target word
	 * if all the distinct words in this book were arranged in lexicographic order.
	 * If there is no such word, returns the target word.
	 * 
	 * Note: This method does not actually put the words into lexicographic order.
	 * 
	 * @param targetWord - the given target word
	 * @return the word that comes immediately before the target word,
	 *         lexicographically
	 */
	public String getWordLexicographicallyBefore(String targetWord) {
		DynamicArray wordsBeforeTarget = new DynamicArray();
		for (int i = 0; i < words.size(); i++) {
			if (targetWord.compareTo(words.getElement(i)) > 0) {
				wordsBeforeTarget.append(words.getElement(i));
			}
		}
		if (wordsBeforeTarget.size() == 0) {
			return targetWord;
		}
		String latest = wordsBeforeTarget.getElement(0);
		for (int i = 1; i < wordsBeforeTarget.size(); i++) {
			if (wordsBeforeTarget.getElement(i).compareTo(latest) > 0) {
				latest = wordsBeforeTarget.getElement(i);
			}
		}
		return latest;
	}

	public String getWordLexicographicallyAfter(String targetWord) {
		/**
		 * Determines the word that would come immediately after the given target word
		 * if all the distinct words in this book were arranged in lexicographic order.
		 * If there is no such word, returns the target word.
		 * 
		 * Note: This method does not actually put the words into lexicographic order.
		 * 
		 * @param targetWord - the given target word
		 * @return the word that comes immediately after the target word,
		 *         lexicographically
		 */
		DynamicArray wordsAfterTarget = new DynamicArray();
		for (int i = 0; i < words.size(); i++) {
			if (targetWord.compareTo(words.getElement(i)) < 0) {
				wordsAfterTarget.append(words.getElement(i));
			}
		}
		if (wordsAfterTarget.size() == 0) {
			return targetWord;
		}
		String latest = wordsAfterTarget.getElement(0);
		for (int i = 1; i < wordsAfterTarget.size(); i++) {
			if (wordsAfterTarget.getElement(i).compareTo(latest) < 0) {
				latest = wordsAfterTarget.getElement(i);
			}
		}
		return latest;

	}

	/**
	 * Writes the words of this book to file, in reverse order.
	 * 
	 * @param filename - the name of the file to write
	 */
	public void saveReversedBook(String filename) {

		try (FileWriter fileOutput = new FileWriter(filename)) {

			for (int i = words.size() - 1; i >= 0; i--) {
				String reverseBook = words.getElement(i);
				fileOutput.write(reverseBook);
				if (i > 0) {
					fileOutput.write(" ");
				}
			}

		} catch (IOException e) {
		}
	}
}