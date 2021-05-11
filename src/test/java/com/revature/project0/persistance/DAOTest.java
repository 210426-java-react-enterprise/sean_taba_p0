package com.revature.project0.persistance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import static org.mockito.Mockito.*;

public class DAOTest
{
    private Connection mockConnection;
    private DAO sut;

    @Before
    public void setUp()
    {
        mockConnection = mock(Connection.class);
        sut = new DAO(mockConnection);
    }

    @After
    public void tearDown()
    {
        sut =null;
        mockConnection = null;
    }

    @Test
    public void test_tryNewUsernameSqlException()
    {

    }
}
