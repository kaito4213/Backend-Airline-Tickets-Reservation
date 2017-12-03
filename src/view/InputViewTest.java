package view;

import static org.junit.Assert.*;

import org.junit.*;

public class InputViewTest {

	@Test
	/**
	 * Create the test case
	 * Check the valid input date and valid input format
	 */
	public void testCheckValidDate() {
		InputView input = new InputView();
		
		// Check a date too early
		String input1 = input.checkValidDate("12/1/2017");
		assertNull(input1);
		
		// Check a date too late
		String input2 = input.checkValidDate("1/1/2018");
		assertNull(input2);
		
		// Check an invalid format
		String input3 = input.checkValidDate("12122017");
		assertNull(input3);
		
		// Check a valid date and format
		String input4 = input.checkValidDate("12/12/2017");
		assertNotNull(input4);
	}

	@Test
	/**
	 * Create the test case
	 * Check the valid return date is after departure date
	 */
	public void testRequestValidRetDate() {
		InputView input = new InputView();

		input.departureDate("12/12/2017");
		assertTrue(input.ensureRetAfterDep("2017_12_20"));

		assertFalse(input.ensureRetAfterDep("2017_12_2"));
	}

}
