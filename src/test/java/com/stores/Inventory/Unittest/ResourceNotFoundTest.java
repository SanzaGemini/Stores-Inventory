package com.stores.Inventory.Unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.stores.Inventory.exception.ResourceNotFoundExeption;

public class ResourceNotFoundTest {

    @Test
    public void testMessage(){
        ResourceNotFoundExeption rNotFoundTest = new ResourceNotFoundExeption("This Was Not FOUND");

        assertEquals("This Was Not FOUND", rNotFoundTest.getMessage());
    }

}
