package assign06;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains tests for the BetterDynamicArray class.
 *
 * @author Prof. Martin and Khang Hoang Nguyen
 * @version Assignment 6
 */
public class BetterDynamicArrayTest {

	private static BetterDynamicArray threeStrings;


	@BeforeEach
	public void setup() {
		threeStrings = new BetterDynamicArray();
		threeStrings.append("9");
		threeStrings.append("0");
		threeStrings.append("cats");
	}

	@Test
	public void testConstructor() {
		BetterDynamicArray array = new BetterDynamicArray();
		assertEquals(0, array.size(), "Constructor did not yield a 0-sized dynamic array");
		assertEquals("[] backing array length: 10", array.toString(),
				"Constructor did not yield the correct dynamic array (via toString)");
	}

	@Test
	public void testConstructorCreatesDistinctArrays() {
		BetterDynamicArray array = new BetterDynamicArray();
		BetterDynamicArray otherArray = new BetterDynamicArray();
		otherArray.append("test");
		assertEquals(0, array.size(),
				"Appending an element to one DynamicArray object changed the size of a different DynamicArray object");
	}

	@Test
	public void testAppendSimple() {
		String expected = "[9, 0, cats] backing array length: 10";
		assertEquals(expected, threeStrings.toString(), "Failed appending 3 elements to empty dynamic array");
		assertEquals(3, threeStrings.size(), "Incorrect size after appending 3 elements to dynamic array");
	}

	@Test
	public void testAppendLarger() {
		String[] largeArray = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
		BetterDynamicArray array = new BetterDynamicArray();
		for (String elem : largeArray)
			array.append(elem);
		String expected = "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10] backing array length: 20";
		assertEquals(expected, array.toString(), "Failed appending 11 elements to dynamic array");
		assertEquals(11, array.size(), "Incorrect size after appending 11 elements to dynamic array");
	}

	@Test
	public void testInsertFront() {
		threeStrings.insert(0, "front");
		String expected = "[front, 9, 0, cats] backing array length: 10";
		assertEquals(expected, threeStrings.toString(), "Failed inserting an element to the front");
		assertEquals(4, threeStrings.size(), "Incorrect size after inserting element to the front");
	}

	@Test
	public void testInsertMiddle() {
		threeStrings.insert(1, "middle");
		String expected = "[9, middle, 0, cats] backing array length: 10";
		assertEquals(expected, threeStrings.toString(), "Failed inserting an element to the middle");
		assertEquals(4, threeStrings.size(), "Incorrect size after inserting element to the middle");
	}

	@Test
	public void testInsertEnd() {
		threeStrings.insert(3, "end");
		String expected = "[9, 0, cats, end] backing array length: 10";
		assertEquals(expected, threeStrings.toString(), "Failed inserting an element to the end");
		assertEquals(4, threeStrings.size(), "Incorrect size after inserting element to the end");
	}

	@Test
	public void testInsertInvalidLowIndex() {

		assertThrows(IndexOutOfBoundsException.class, () -> {
			threeStrings.insert(-1, "low");
		}, "Failed to throw exception when inserting with too-low index");
	}

	@Test
	public void testInsertInvalidHighIndex() {
		assertThrows(IndexOutOfBoundsException.class, () -> {
			threeStrings.insert(4, "high");
		}, "Failed to throw exception when inserting with too-high index");
	}

	@Test
	public void testGetElement() {
		assertEquals("9", threeStrings.getElement(0), "Failed getting element from front");
		assertEquals("0", threeStrings.getElement(1), "Failed getting element from middle");
		assertEquals("cats", threeStrings.getElement(2), "Failed getting element from end");
		assertEquals(3, threeStrings.size(), "Calling getElement changed the size of dynamic array");
	}

	@Test
	public void testGetElementInvalidLow() {
		assertThrows(IndexOutOfBoundsException.class, () -> {
			threeStrings.getElement(-1);
		}, "Failed to throw exception when getting element with too-low index");
	}

	@Test
	public void testGetElementInvalidHigh() {
		assertThrows(IndexOutOfBoundsException.class, () -> {
			threeStrings.getElement(3);
		}, "Failed to throw exception when getting element with too-high index");
	}

	@Test
	public void testDoublingIsFaster() {
		double DynamicArrayTime = SpeedTest.appendToBetterDynamicArray(10000);
		double regularDynamicArrayTime = SpeedTest.appendToDynamicArray(10000);
		assertTrue(DynamicArrayTime < regularDynamicArrayTime,
				"The time to add 10k items to a doubling dynamic array should be faster, "
						+ "but it is not with the current implementation");
	}

	@Test
	public void testSetElement() {

		threeStrings.setElement(0, "cats");
		assertEquals("cats", threeStrings.getElement(0), "Failed getting element from front");
		String expected = "[cats, 0, cats] backing array length: 10";
		assertEquals(expected, threeStrings.toString(), "Failed inserting an element to the end");
		assertEquals(3, threeStrings.size(), "Incorrect size after inserting element to the end");
	}

	@Test
	public void testDeleteFront() {
		threeStrings.delete(0);
		String expected = "[0, cats] backing array length: 10";
		assertEquals(expected, threeStrings.toString(), "Failed inserting an element to the end");
		assertEquals(2, threeStrings.size(), "Incorrect size after inserting element to the end");
	}

	@Test
	public void testDeleteMiddle() {
		threeStrings.delete(1);
		String expected = "[9, cats] backing array length: 10";
		assertEquals(expected, threeStrings.toString(), "Failed inserting an element to the end");
		assertEquals(2, threeStrings.size(), "Incorrect size after inserting element to the end");
	}

	@Test
	public void testDeleteEnd() {
		threeStrings.delete(2);
		String expected = "[9, 0] backing array length: 10";
		assertEquals(expected, threeStrings.toString(), "Failed inserting an element to the end");
		assertEquals(2, threeStrings.size(), "Incorrect size after inserting element to the end");
	}

	@Test
	public void testAppendDoubleLength() {
		// Appending >= 10 elements tests the double-length growth of a dynamic array.
		String[] largeArray = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
				"", "", "", "", "" };
		BetterDynamicArray array = new BetterDynamicArray();
		for (String elem : largeArray)
			array.append(elem);
		String expected = "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, , , , , ] backing array length: 40";
		assertEquals(expected, array.toString(), "Failed appending 21 elements to dynamic array");
		assertEquals(21, array.size(), "Incorrect size after appending 21 elements to dynamic array");
	}

	@Test
	public void testInsertFrontDoubleLength() {
		for (int i = 0; i <= 10; i++) {
			threeStrings.insert(0, "front");
		}
		String expected = "[front, front, front, front, front, front, front, front, front, front, front, 9, 0, cats] backing array length: 20";
		assertEquals(expected, threeStrings.toString(), "Failed inserting an element to the front");
		assertEquals(14, threeStrings.size(), "Incorrect size after inserting element to the front");
	}

	@Test
	public void testInsertMiddleDoubleLength() {
		for (int i = 0; i <= 10; i++) {
			threeStrings.insert(1, "middle");
		}
		String expected = "[9, middle, middle, middle, middle, middle, middle, middle, middle, middle, middle, middle, 0, cats] backing array length: 20";
		assertEquals(expected, threeStrings.toString(), "Failed inserting an element to the front");
		assertEquals(14, threeStrings.size(), "Incorrect size after inserting element to the front");
	}

	@Test
	public void testInsertEndDoubleLength() {
		for (int i = 0; i <= 10; i++) {
			threeStrings.insert(2, "end");
		}
		String expected = "[9, 0, end, end, end, end, end, end, end, end, end, end, end, cats] backing array length: 20";
		assertEquals(expected, threeStrings.toString(), "Failed inserting an element to the front");
		assertEquals(14, threeStrings.size(), "Incorrect size after inserting element to the front");
	}

	@Test
	public void testGetElementDoubleLength() {
		for (int i = 0; i <= 20; i++) {
			threeStrings.insert(2, "");
		}

		assertEquals("", threeStrings.getElement(10), "Failed getting element from front");
		assertEquals("cats", threeStrings.getElement(23), "Failed getting element from front");
	}

	@Test
	public void testSetElementDoubleLength() {
		for (int i = 0; i <= 20; i++) {
			threeStrings.insert(2, "end");
		}
		threeStrings.setElement(20, "cats");
		threeStrings.setElement(10, "cats");
		assertEquals("cats", threeStrings.getElement(10), "Failed getting element from front");
		assertEquals("cats", threeStrings.getElement(20), "Failed getting element from front");

	}

	@Test
	public void testDeleteDoubleLength() {
		for (int i = 0; i <= 20; i++) {
			threeStrings.insert(0, "word");
		}
		for (int i = 23; i >= 0; i--) {
			threeStrings.delete(i);
		}
		String expected = "[] backing array length: 40";
		assertEquals(expected, threeStrings.toString(), "Failed inserting an element to the end");
		assertEquals(0, threeStrings.size(), "Incorrect size after inserting element to the end");

	}
}
