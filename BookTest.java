package assign06;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class contains tests for the Book class.
 *
 * @author Prof. Martin and Khang Hoang Nguyen
 * @version Oct 19 2023
 */
public class BookTest {

	private static Book smallBook;

	@BeforeAll
	public static void setup() {
		smallBook = new Book("small_book.txt");
	}

	@Test
	public void testGetWordCount() {
		assertEquals(32, smallBook.getWordCount());
	}

	@Test
	public void testGetCharacterCount() {
		assertEquals(137, smallBook.getCharacterCount());
	}

	@Test
	public void testGetAverageWordLength() {
		assertEquals(4.28125, smallBook.getAverageWordLength(), 0.00001);
	}

	@Test
	public void testGetShortestWord() {
		assertEquals("a", smallBook.getShortestWord());
	}

	@Test
	public void testGetLongestWord() {
		assertEquals("checking", smallBook.getLongestWord());
	}

	@Test
	public void testGetTargetWordCount() {
		assertEquals(2, smallBook.getTargetWordCount("do"));
	}

	@Test
	public void testGetWordLexicographicallyBefore() {
		assertEquals("Use", smallBook.getWordLexicographicallyBefore("a"));
	}

	@Test
	public void testGetWordLexicographicallyAfter() {
		assertEquals("the", smallBook.getWordLexicographicallyAfter("testing"));
	}

	@Test
	public void testSaveReversedBook() {
		String expected = "files. book large using testing thorough do also to sure be However, class. Book the of checking initial simple, some do to it Use book. small very a of example an This";
		String filename = "reverse_test.txt";
		smallBook.saveReversedBook(filename);
		try {// update to close w/ resources
			Scanner fileInput = new Scanner(new File(filename));
			String actual = fileInput.nextLine();
			assertEquals(expected, actual);
			fileInput.close();
		} catch (FileNotFoundException e) {
			fail("File not written or written using incorrect path.");
		}
	}

	@Test
	public void getTargetWordCount() {
		assertEquals(0, smallBook.getTargetWordCount(" "));
	}

	@Test
	public void getWordLexicographicallyBefore() {
		assertNotEquals("This", smallBook.getWordLexicographicallyBefore("This"));

	}

	@Test
	public void getWordLexicographicallyAfter() {
		assertNotEquals("This", smallBook.getWordLexicographicallyAfter("This"));

	}

	private static Book secondBook;

	@BeforeAll
	public static void setupAnotherBook() {
		secondBook = new Book("second_book.txt");
	}

	@Test
	public void testGetWordCountA() {
		assertEquals(35, secondBook.getWordCount());
	}

	@Test
	public void testGetCharacterCountA() {
		assertEquals(178, secondBook.getCharacterCount());
	}

	@Test
	public void testGetAverageWordLengthA() {
		assertEquals(5.08571, secondBook.getAverageWordLength(), 0.00001);
	}

	@Test
	public void testGetShortestWordA() {
		assertEquals("a", secondBook.getShortestWord());
	}

	@Test
	public void testGetLongestWordA() {
		assertEquals("intricate", secondBook.getLongestWord());
	}

	@Test
	public void testGetTargetWordCountA() {
		assertEquals(3, secondBook.getTargetWordCount("of"));
	}

	@Test
	public void testGetWordLexicographicallyBeforeA() {
		assertEquals("The", secondBook.getWordLexicographicallyBefore("a"));
	}

	@Test
	public void testGetWordLexicographicallyAfterA() {
		assertEquals("the", secondBook.getWordLexicographicallyAfter("testing"));
	}

	@Test
	public void testSaveReversedBookA() {
		String expected = "bakery. nearby a from wafting bread, baked freshly of scent The joy. of symphony a air, the through echoing laughter of sound The ground. the on patterns intricate casting leaves, through filters sunlight way The";
		String filename = "second_book_reversed.txt";
		secondBook.saveReversedBook(filename);
		try {// update to close w/ resources
			Scanner fileInput = new Scanner(new File(filename));
			String actual = fileInput.nextLine();
			assertEquals(expected, actual);
			fileInput.close();
		} catch (FileNotFoundException e) {
			fail("File not written or written using incorrect path.");
		}
	}

	private static Book edgeCaseBook;

	@BeforeAll
	public static void setupedgecaseBook() {
		edgeCaseBook = new Book("edge_case.txt");
	}

	@Test
	public void testGetWordCountB() {
		assertEquals(1, edgeCaseBook.getWordCount());
	}

	@Test
	public void testGetCharacterCountB() {
		assertEquals(1, edgeCaseBook.getCharacterCount());
	}

	@Test
	public void testGetAverageWordLengthB() {
		assertEquals(1.0, edgeCaseBook.getAverageWordLength(), 0.00001);
	}

	@Test
	public void testGetShortestWordB() {
		assertNotEquals(null, edgeCaseBook.getShortestWord());
	}

	@Test
	public void testGetLongestWordB() {
		assertNotEquals(null, edgeCaseBook.getLongestWord());

	}

	@Test
	public void testGetTargetWordCountB() {
		assertEquals(0, edgeCaseBook.getTargetWordCount(" "));
	}

	@Test
	public void testGetWordLexicographicallyBeforeB() {
		assertEquals(" ", edgeCaseBook.getWordLexicographicallyBefore(" "));
	}

	@Test
	public void testGetWordLexicographicallyAfterB() {
		assertEquals(".", edgeCaseBook.getWordLexicographicallyAfter(" "));
	}

	@Test
	public void testSaveReversedBookB() {
		String expected = ".";
		String filename = "edge_case.txt";
		edgeCaseBook.saveReversedBook(filename);
		try {// update to close w/ resources
			Scanner fileInput = new Scanner(new File(filename));
			if (fileInput.hasNext()) {
				String actual = fileInput.nextLine();
				assertEquals(expected, actual);
				fileInput.close();
			}
		} catch (FileNotFoundException e) {
			fail("File not written or written using incorrect path.");
		}
	}

}
