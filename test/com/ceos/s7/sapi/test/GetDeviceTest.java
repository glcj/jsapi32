package com.ceos.s7.sapi.test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.ceos.s7.sapi.SapiLibrary;
import com.sun.jna.Native;
import com.sun.jna.ptr.ShortByReference;
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
public class GetDeviceTest {
    
    public GetDeviceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        //System.out.println("1. Setupclass");
    }
    
    @AfterClass
    public static void tearDownClass() {
        //System.out.println("2. tearDownClass");        
    }
    
    @Before
    public void setUp() {
        //System.out.println("3. Setup");        
    }
    
    @After
    public void tearDown() {
        //System.out.println("4. Teardown");          
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void Prueba() {
             
        ShortBuffer number = ShortBuffer.allocate(100); 
        number.put(0,(short) 0);   

        short index = 0;
        short index2 = 0;        
        
        int res = 45;
        byte[] device_name = new byte[255];        
        
        String s = new String();  
        
        res = SapiLibrary.INSTANCE.s7_get_device(index, number, device_name);
        index2 = number.get(0);  
    
    
        //System.out.println("Respuesta: " + res);        
        System.out.println("Numero de dispositivos: " + index2);
        System.out.println("Dispositivo: " + Native.toString(device_name));
        //System.out.println();
        

        
        //System.out.println();        
        
        
        //System.out.println("Index    Device");
        //System.out.println("==============="); 
            number.rewind();
        for (short i = 0; i < index2; i++){
            res = SapiLibrary.INSTANCE.s7_get_device(i, number, device_name); 
            System.out.println("Device("+i+"): " +  Native.toString(device_name)); 
            //System.out.println(number.get(0));
            //System.out.println(number.get(1));    
            number.rewind();
        }
        
        
    }
}
