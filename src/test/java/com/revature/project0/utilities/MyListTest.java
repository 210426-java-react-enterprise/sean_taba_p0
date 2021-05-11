package com.revature.project0.utilities;

import exceptions.IllegalInputException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MyListTest
{
    private MyList<String> sut;

    @Before
    public void setUpTest()
    {
        sut = new MyList<>();
    }

    @After
    public void tearDownTest()
    {
        sut = null;
    }

    @Test
    public void test_addWithNonNullValue() throws IllegalInputException
    {
        int beforeSize = 1;

        sut.add(Mockito.anyString());

        int afterSize = sut.size();

        Assert.assertEquals(beforeSize, afterSize);
    }

    @Test(expected = Exception.class)
    public void test_addWithNullValue() throws IllegalInputException
    {
        sut.add(null);
    }

    @Test(expected = Exception.class)
    public void test_addWithIndexWithNullValue() throws IllegalInputException
    {
        sut.add(2, null);
    }

    @Test
    public void test_addWithIndexWithNotNullValue() throws IllegalInputException
    {
        sut.add(2, Mockito.anyString());

        Assert.assertEquals(0, sut.size());
    }

    @Test
    public void test_addWithIndexWithValidValue() throws IllegalInputException
    {
        sut.add("test");
        sut.add("test1");
        sut.add("test2");
        sut.add(1,"hello");

        Assert.assertEquals("hello", sut.at(1));
    }

    @Test
    public void test_grow() throws IllegalInputException
    {
        for (int i = 0; i < 1024; i++)
        {
            sut.add(Mockito.anyString());
        }

        Assert.assertEquals(1024, sut.size());
    }

    @Test
    public void test_isEmptyWithEmpty()
    {
        Assert.assertTrue(sut.isEmpty());
    }

    @Test
    public void test_isEmptyWithNotEmpty() throws IllegalInputException
    {
        sut.add(Mockito.anyString());
        Assert.assertFalse(sut.isEmpty());
    }

    @Test
    public void test_containsWithValue() throws IllegalInputException
    {
        sut.add("test");

        Assert.assertTrue(sut.contains("test"));
    }

    @Test
    public void test_containsWithoutValue() throws IllegalInputException
    {
        Assert.assertFalse(sut.contains("duck"));
    }

    @Test
    public void test_containsWithWrongValue() throws IllegalInputException
    {
        sut.add("Duck");
        Assert.assertFalse(sut.contains("duck"));
    }

    @Test
    public void test_toArrayWithValues() throws IllegalInputException
    {
        for (int i = 0; i < 10; i++)
        {
            sut.add(String.valueOf(i));
        }
        String[] strArray = {"0","1","2","3","4","5","6","7","8","9"};

        Assert.assertArrayEquals(strArray, sut.toArray());
    }

    @Test
    public void test_clear() throws IllegalInputException
    {
        for (int i = 0; i < 10; i++)
        {
            sut.add(String.valueOf(i));
        }

        sut.clear();

        Assert.assertEquals(0,sut.size());
    }

    @Test
    public void test_getWithNegativeIndex()
    {
        Assert.assertNull(sut.get(-2));
    }

    @Test
    public void test_getWithOutOfBoundsIndex()
    {
        Assert.assertNull(sut.get(100));
    }

    @Test
    public void test_getWithCorrectValue() throws IllegalInputException
    {
        sut.add("one");
        sut.add("two");

        Assert.assertEquals("two", sut.get(1));
    }

    @Test(expected = Exception.class)
    public void test_setWithNegativeIndex()
    {
        sut.set(-2,Mockito.anyString());
    }

    @Test
    public void test_setWithOutOfBoundsIndex()
    {
        Assert.assertNull(sut.set(5, Mockito.anyString()));
    }

    @Test
    public void test_setWithValidIndex() throws IllegalInputException
    {
        sut.add("test1");
        sut.add("test2");

        sut.set(1, "hello");

        Assert.assertEquals("hello", sut.at(1));
    }

    @Test
    public void test_removeWithNullValue()
    {
        Assert.assertFalse(sut.remove(null));
    }

    @Test
    public void test_removeWithNonExistingValue()
    {
        Assert.assertFalse(sut.remove(Mockito.anyString()));
    }

    @Test
    public void test_removeWithExistingValueForTrue() throws IllegalInputException
    {
        for (int i = 0; i < 10; i++)
        {
            sut.add(String.valueOf(i));
        }

        Assert.assertTrue(sut.remove("4"));
    }

    @Test
    public void test_removeWithExistingValueForSize() throws IllegalInputException
    {
        for (int i = 0; i < 10; i++)
        {
            sut.add(String.valueOf(i));
        }
        sut.remove("3");
        Assert.assertEquals(9, sut.size());
    }

    @Test
    public void test_removeWithExistingValueForCorrectness() throws IllegalInputException
    {
        for (int i = 0; i < 10; i++)
        {
            sut.add(String.valueOf(i));
        }
        sut.remove("3");
        String[] array = {"0","1","2","4","5","6","7","8","9"};

        Assert.assertArrayEquals(array, sut.toArray());
    }

    @Test
    public void test_removeByIndexWithExistingValueForTrue() throws IllegalInputException
    {
        sut.add("test");

        Assert.assertEquals("test",sut.remove(0));
    }

    @Test
    public void test_removeByIndexWithExistingValueForSize() throws IllegalInputException
    {
        for (int i = 0; i < 5; i++)
        {
            sut.add(String.valueOf(i));
        }
        Assert.assertEquals(5, sut.size());
    }

    @Test
    public void test_removeByIndexWithExistingValueForCorrectness() throws IllegalInputException
    {
        for (int i = 0; i < 10; i++)
        {
            sut.add(String.valueOf(i));
        }
        sut.remove(5);
        String[] array = {"0","1","2","3","4","6","7","8","9"};

        Assert.assertArrayEquals(array, sut.toArray());
    }

    @Test
    public void test_indexOfWithNull() throws IllegalInputException
    {
        sut.add("test");
        sut.add("test1");
        sut.add("test2");

        Assert.assertEquals(-1, sut.indexOf(null));
    }

    @Test
    public void test_indexOfWithExistingValue() throws IllegalInputException
    {
        sut.add("test");
        sut.add("test1");
        sut.add("test2");

        Assert.assertEquals(1, sut.indexOf("test1"));
    }

    @Test
    public void test_atWithNegativeIndex() throws IllegalInputException
    {
        sut.add("test");
        sut.add("test1");
        sut.add("test2");

        Assert.assertNull(sut.at(-2));
    }

    @Test
    public void test_atWithValidIndex() throws IllegalInputException
    {
        sut.add("test");
        sut.add("test1");
        sut.add("test2");

        Assert.assertEquals("test2",sut.at(2));
    }

    @Test
    public void test_toString() throws IllegalInputException
    {
        sut.add("test");

        Assert.assertEquals("MyList{array=[test]}", sut.toString());
    }



}
