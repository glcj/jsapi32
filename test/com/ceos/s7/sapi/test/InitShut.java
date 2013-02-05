/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceos.s7.sapi.test;

import com.ceos.s7.sapi.SapiLibrary;
import com.sun.jna.Native;
import com.sun.jna.ptr.NativeLongByReference;
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
public class InitShut {
    
    public InitShut() {
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
        String cp_name  = "GATEWAY";
        String vfd_name = "Application";
        int res = 45; 
        NativeLongByReference cp_descr_ptr = new NativeLongByReference();
        
        res = SapiLibrary.INSTANCE.s7_init(cp_name, vfd_name, cp_descr_ptr);
        assertEquals("Inicializo la libreria: ",SapiLibrary.S7_OK,res);        
        
        res = SapiLibrary.INSTANCE.s7_shut(cp_descr_ptr.getValue());
        assertEquals("Tumbo la libreria: ",SapiLibrary.S7_OK,res);                       
        
    }
    
    @Test
    public void UnaReferenciaALaConexion(){

        String cp_name  = "GATEWAY";
        String vfd_name = "Application";
        String con_name ="AS01";
        int res = 45; 
        
        NativeLongByReference cp_descr_ptr = new NativeLongByReference();
        ShortBuffer cp_cref_ptr = ShortBuffer.allocate(100);      
        
        res = SapiLibrary.INSTANCE.s7_init(cp_name, vfd_name, cp_descr_ptr);
        assertEquals("Inicializo la libreria: ",SapiLibrary.S7_OK,res);  
        
        res = SapiLibrary.INSTANCE.s7_get_cref(cp_descr_ptr.getValue(), con_name, cp_cref_ptr);
        assertEquals("Tomando referencia a la conexion: ", SapiLibrary.S7_OK, res);         
        
        res = SapiLibrary.INSTANCE.s7_shut(cp_descr_ptr.getValue());
        assertEquals("Tumbo la libreria: ",SapiLibrary.S7_OK,res);                   
    }
   
    @Test
    public void ListadoDeConexiones(){
        String cp_name  = "GATEWAY";
        String vfd_name = "Application";
        String con_name = "AS01";
        byte[] conn_name = new byte[255];  
        ShortBuffer number = ShortBuffer.allocate(100);
    
        int res = 45; 
        short index = 0;
        short index2 = 0;
        
        
        NativeLongByReference cp_descr_ptr = new NativeLongByReference();
        ShortBuffer cp_cref_ptr = ShortBuffer.allocate(100);      
        
        res = SapiLibrary.INSTANCE.s7_init(cp_name, vfd_name, cp_descr_ptr);
        assertEquals("Inicializo la libreria: ",SapiLibrary.S7_OK,res);  
        
        res = SapiLibrary.INSTANCE.s7_get_conn(cp_descr_ptr.getValue(), index, number, cp_cref_ptr, conn_name);
        assertEquals("Tomando referencia a la conexion: ", SapiLibrary.S7_OK, res);
        
        index2 = number.get();
        for (short i=0; i<index2; i++){
            res = SapiLibrary.INSTANCE.s7_get_conn(cp_descr_ptr.getValue(), index, number, cp_cref_ptr, conn_name);
            assertEquals("Tomando referencia a la conexion: ", SapiLibrary.S7_OK, res);
            System.out.println("Conn("+i+"): "+Native.toString(conn_name));            
        }
              
        res = SapiLibrary.INSTANCE.s7_shut(cp_descr_ptr.getValue());
        assertEquals("Tumbo la libreria: ",SapiLibrary.S7_OK,res);            
    }
    
    
    
}
