package util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by martijn on 21-6-2016.
 */
public class ValidationHelperTest {

    ValidationHelper validationHelper;

    @Before
    public void setUp() {
        validationHelper = new ValidationHelper();
    }

    @Test
    public void isIntegerTest() {
        boolean stringTest = validationHelper.isInteger("test");
        boolean doubleTest = validationHelper.isInteger("1.0");
        boolean longTest = validationHelper.isInteger("1L");
        boolean intTest = validationHelper.isInteger("1");

        assertFalse("Should be an integer",stringTest);
        assertFalse("Should be an integer",doubleTest);
        assertFalse("Should be an integer",longTest);

        assertTrue("Should be an integer",intTest);
    }

}
