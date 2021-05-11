package com.revature.project0.utilities;

import com.revature.project0.persistance.DAO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class InputValidatorTest
{
    private DAO mockDAO;
    private InputValidator iV;

    @Before
    public void setUp()
    {
        mockDAO = mock(DAO.class);
    }

    @After
    public void tearDown()
    {
        mockDAO = null;
    }

    @Test
    public void test_validateBoundaryNullInput()
    {
        Assert.assertEquals(-1, InputValidator.validate(null, 1, 3));
    }

    @Test
    public void test_validateBoundaryBadBoundaryTooHigh()
    {
        Assert.assertEquals(-1, InputValidator.validate("100", 0, 20));
    }

    @Test
    public void test_validateBoundaryBadBoundaryTooLow()
    {
        Assert.assertEquals(-1, InputValidator.validate("5", 10, 20));
    }

    @Test
    public void test_validateBoundaryBadNumber()
    {
        Assert.assertEquals(-1, InputValidator.validate("hello", 0, 20));
    }

    @Test
    public void test_validateBoundaryValidInputs()
    {
        Assert.assertEquals(6, InputValidator.validate("6", 1, 7));
    }

    @Test
    public void test_validateStringWithIdentifierNullString() throws SQLException
    {
        Assert.assertNull(InputValidator.validate(null, "/test"));
    }

    @Test
    public void test_validateStringWithIdentifierNullIdentifier() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("test", null));
    }

    @Test
    public void test_validateStringWithIdentifierInvalidIdentifier() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("test", "/test"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierInvalidLengthUsername() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("usr", "/username"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierInvalidCharacter() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("/username", "/username"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierExistingUsername() throws SQLException
    {
        when(mockDAO.tryNewUsername(anyString())).thenReturn(true);
        Assert.assertNull(InputValidator.validate("username", "/username"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierInvalidLengthPassword() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("pass", "/password"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierValidLengthPassword() throws SQLException
    {
        Assert.assertEquals("password",InputValidator.validate("password", "/password"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierInvalidLengthName() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("n", "/name"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierInvalidName() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("name9", "/name"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierValidName() throws SQLException
    {
        Assert.assertEquals("name",InputValidator.validate("name", "/name"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierInvalidLengthSSN() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("ssn", "/ssn"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierInvalidSSN() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("ssn545852", "/ssn"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierValidSsnAlreadyTaken() throws SQLException
    {
        when(mockDAO.tryNewSSN(anyString())).thenReturn(true);
        //mockDAO.tryNewSSN(anyString());

        Assert.assertNull(InputValidator.validate("458745874", "/ssn"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierValidAvailableSsn() throws SQLException
    {
        Assert.assertEquals("458745874",InputValidator.validate("458745874", "/ssn"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierInvalidEmail() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("asd.aad.dg", "/email"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierValidEmail() throws SQLException
    {
        Assert.assertEquals("dsfgg@sdgff.kjh",InputValidator.validate("dsfgg@sdgff.kjh", "/email"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierIncorrectLengthPhone() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("54644", "/phone"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierIncorrectPhone() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("54644sdffs", "/phone"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierCorrectLengthPhone() throws SQLException
    {
        Assert.assertEquals("5464123534",InputValidator.validate("5464123534", "/phone"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierIncorrectLengthUnit() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("35442", "/unit"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierIncorrectUnit() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("3l2", "/unit"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierCorrectLengthUnit() throws SQLException
    {
        Assert.assertEquals("352",InputValidator.validate("352", "/unit"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierIncorrectLengthStreet() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("3l2", "/street"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierIncorrectStreet() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("3l2dfg dfg #", "/street"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierCorrectStreet() throws SQLException
    {
        Assert.assertEquals("3l2dfg dfg 65 sda",InputValidator.validate("3l2dfg dfg 65 sda", "/street"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierIncorrectLengthState() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("3l2dfg dfg asdsa asdad", "/state"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierIncorrectLengthCity() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("3l2dfg dfg asdsa asdad", "/city"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierIncorrectState() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("3l2dfg dfg #", "/state"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierCorrectCityState() throws SQLException
    {
        Assert.assertEquals("adddsad adad",InputValidator.validate("adddsad adad", "/city"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierIncorrectLengthZip() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("215", "/zip"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierIncorrectZip() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("215re", "/zip"));
    }
    @Test
    public void test_validateStringWithIdentifierValidIdentifierCorrectZip() throws SQLException
    {
        Assert.assertEquals("21554",InputValidator.validate("21554", "/zip"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierIncorrectAccountNumber() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("215%re", "/account number"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierIncorrectFormatAccountNumber() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("215re", "/account number"));
    }

    @Test
    public void test_validateStringWithIdentifierValidIdentifierAccountNumberNotFound() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("100", "/account number"));
    }

    @Test
    public void test_test_validateStringWithIdentifierValidIdentifierInvalidDouble() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("54f56", "/deposit"));
    }

    @Test
    public void test_test_validateStringWithIdentifierValidIdentifierNegativeDouble() throws SQLException
    {
        Assert.assertNull(InputValidator.validate("-5456", "/deposit"));
    }

    @Test
    public void test_test_validateStringWithIdentifierValidIdentifierValidDeposit() throws SQLException
    {
        Assert.assertEquals("5456", InputValidator.validate("5456", "/deposit"));
    }




}
