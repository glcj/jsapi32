/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceos.s7.sapi.test;

import com.ceos.s7.sapi.SapiLibrary;
import com.sun.jna.Native;
import java.nio.ShortBuffer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cgarcia
 */
public class GetVFDTest {
    
    public GetVFDTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void Prueba() {
        
        byte[] device_name = new byte[255]; 
        byte[] vfd_name = new byte[255];         
        
        ShortBuffer number = ShortBuffer.allocate(100); 
        ShortBuffer number2 = ShortBuffer.allocate(100);        
        String access_point = "GATEWAY";

        
        int res = 45;
        short index = 0;
        short index2 = 0;
        
        res = SapiLibrary.INSTANCE.s7_get_device(index, number, device_name); 
        assertEquals("Tomo el Dispositivo",SapiLibrary.S7_OK,res);
        System.out.println("Dispositivo : " + Native.toString(device_name));        
        
        index = 0;
        number.rewind();        
        res = SapiLibrary.INSTANCE.s7_get_vfd(access_point, index, number, vfd_name);
        assertEquals("Tomo el VFD",SapiLibrary.S7_OK,res); 
        
        index2 = number.get();
        number.rewind();
        for (short i = 0; i < index2; i++){
        res = SapiLibrary.INSTANCE.s7_get_vfd(access_point, i, number, vfd_name);
            System.out.println("VFD("+i+"): " +  Native.toString(vfd_name));   
            number.rewind();
        }
        
        
    }        
           
}
