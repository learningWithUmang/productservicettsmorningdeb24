package dev.naman.productservicettsmorningdeb24;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleTest {
    /*
    What is a test case at the end of the day?
    A test case is nothing but a method
    You can multiple assertions inside a test
     */

    @Test
    void testAddAndMulFunctionality(){
        //Arranging the input params
        //add(a,b) => a + b
        int a = 1, b = 2;
        //Act =>  call the method
        int responseForAdd = a + b; //response from the add method we are testing
        int responseForMul = a * b;
        int expectedOutputForAdd = 3;
        int expectedOutputForMul = 2;
        //Assert => confirming that expected output is equal to actual output

        //assert responseForAdd == expectedOutput;
        assertEquals(expectedOutputForAdd, responseForAdd); //expected, actual
        assertEquals(expectedOutputForMul, responseForMul);
        /*
        For assert, we generally use assertion library
         */
    }
}
