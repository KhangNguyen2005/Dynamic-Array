package assign06;

/**
 * This class represents a better dynamic array of strings, doubling the length
 * of the backing array when more space is needed and never shrinking.
 * 
 * @author Prof. Martin and Khang Hoang Nguyen
 * @version Oct 19 2023
 */
public class BetterDynamicArray {

	private String[] elements;
	private int elementCount;

	/**
	 * Creates a dynamic array with space for ten elements, but zero spaces
	 * occupied.
	 * 
	 * DO NOT MODIFY THIS METHOD
	 */
	public BetterDynamicArray() {
		elements = new String[10];
		elementCount = 0;
	}

	/**
	 * Appends the given string to end of this dynamic array.
	 * 
	 * @param str - the string to append
	 */
	public void append(String str) {
		this.insert(elementCount, str);
	}

	/**
	 * Inserts a given string into this dynamic array at a given index. Throws an
	 * exception if the given index is out of bounds.
	 * 
	 * @param index - the index at which to insert
	 * @param str   - the string to insert
	 */
	public void insert(int index, String str) {

		if (index < 0 || index > elementCount) {
			throw new IndexOutOfBoundsException(
					"Index " + index + " is invalid for adding to this array with length " + elements.length);

		}
		if (elementCount == elements.length)
			doubleBackingArray();

		for (int i = elementCount - 1; i >= index; i--) {
			elements[i + 1] = elements[i];
		}
		elements[index] = str;
		elementCount++;

	}

	/**
	 * Creates a new array with twice the length as the backing array. Copies all
	 * elements from the backing array to the new array. Sets the backing array
	 * reference to the new array.
	 */
	private void doubleBackingArray() {
		String[] largerArray = new String[elements.length * 2];
		for (int i = 0; i < elements.length; i++)
			largerArray[i] = elements[i];
		elements = largerArray;
	}

	/**
	 * Gets the string stored in this dynamic array at the given index. Throws an
	 * exception if the given index is out of bounds.
	 * 
	 * @param index - the index of the element to get
	 * @return the element at the given index
	 */
	public String getElement(int index) {
		if (index < 0 || index >= elementCount) {
			throw new IndexOutOfBoundsException(
					"Index " + index + " is invalid for adding to this array with length " + elementCount);
		}
		String stored = elements[index];
		return stored;

	}

	/**
	 * Returns the number of elements in this dynamic array.
	 * 
	 * @return the number of elements
	 */
	public int size() {
		// Fill in.
		return elementCount;
	}

	/**
	 * Sets (i.e., changes) the string stored in this dynamic array at the given
	 * index to the given string. Throws an exception if the given index is out of
	 * bounds.
	 * 
	 * @param index - the index of the element to set
	 * @param str   - the new string value for setting the element
	 */
	public void setElement(int index, String str) {
		if (index >= 0 && index < elementCount) {
			this.elements[index] = str;
		} else {
			throw new IndexOutOfBoundsException(
					"Index " + index + " is invalid for adding to this array with length " + elementCount);
		}
	}

	/**
	 * Deletes the string at the given index from this dynamic array. Throws an
	 * exception if the given index is out of bounds.
	 * 
	 * @param index - the index of the element to delete
	 */
	public void delete(int index) {

		if (index < 0 || index >= elementCount) {
			throw new IndexOutOfBoundsException(
					"Index " + index + " is invalid for adding to this array with length " + elementCount);
		}

		for (int i = index; i <= elementCount; i++) {
			elements[i] = elements[i + 1];
		}
		elementCount--;

	}

	/**
	 * Generates a textual representation of this dynamic array.
	 * 
	 * @return the textual representation
	 * 
	 *         DO NOT MODIFY THIS METHOD
	 */
	public String toString() {
		String result = "[";
		if (size() > 0)
			result += getElement(0);

		for (int i = 1; i < size(); i++)
			result += ", " + getElement(i);

		return result + "] backing array length: " + elements.length;
	}
}