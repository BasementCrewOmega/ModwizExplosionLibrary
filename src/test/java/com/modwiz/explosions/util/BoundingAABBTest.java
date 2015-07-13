/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modwiz.explosions.util;

import static org.junit.Assert.*;

/**
 *
 * @author starbuck
 */
public class BoundingAABBTest {
   
    /**
     * Test of update method, of class BoundingAABB.
     */
    @org.junit.Test
    public void testUpdate() {
        System.out.println("update");
        double x = 0.0;
        double y = 0.0;
        BoundingAABB instance = null;
        instance.update(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getX method, of class BoundingAABB.
     */
    @org.junit.Test
    public void testGetX() {
        System.out.println("getX");
        BoundingAABB instance = null;
        double expResult = 0.0;
        double result = instance.getX();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getY method, of class BoundingAABB.
     */
    @org.junit.Test
    public void testGetY() {
        System.out.println("getY");
        BoundingAABB instance = null;
        double expResult = 0.0;
        double result = instance.getY();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWidth method, of class BoundingAABB.
     */
    @org.junit.Test
    public void testGetWidth() {
        System.out.println("getWidth");
        BoundingAABB instance = null;
        double expResult = 0.0;
        double result = instance.getWidth();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHeight method, of class BoundingAABB.
     */
    @org.junit.Test
    public void testGetHeight() {
        System.out.println("getHeight");
        BoundingAABB instance = null;
        double expResult = 0.0;
        double result = instance.getHeight();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkCollision method, of class BoundingAABB.
     */
    @org.junit.Test
    public void testCheckCollision() {
        System.out.println("checkCollision");
        BoundingAABB colliding = null;
        BoundingAABB instance = null;
        boolean expResult = false;
        boolean result = instance.checkCollision(colliding);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
