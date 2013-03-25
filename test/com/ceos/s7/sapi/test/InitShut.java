/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceos.s7.sapi.test;

import com.ceos.s7.sapi.S7_READ_PARA;
import com.ceos.s7.sapi.S7_WRITE_PARA;
import com.ceos.s7.sapi.SapiLibrary;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.NativeLongByReference;
import java.nio.ShortBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    //@Test
    public void Prueba() {
        String cp_name  = "GATEWAY";
        String vfd_name = "KARAF";
        String con_name = "H02A09";         
        System.out.println("******************************************");
        System.out.println("INICIALIZAR Y TUMBAR");        
        System.out.println("******************************************");            
        int res = 45; 
        NativeLongByReference cp_descr_ptr = new NativeLongByReference();
        
        res = SapiLibrary.INSTANCE.s7_init(cp_name, vfd_name, cp_descr_ptr);
        assertEquals("Inicializo la libreria: ",SapiLibrary.S7_OK,res);        
        
        res = SapiLibrary.INSTANCE.s7_shut(cp_descr_ptr.getValue());
        assertEquals("Tumbo la libreria: ",SapiLibrary.S7_OK,res);                       
        System.out.println("******************************************");  
        System.out.println();
    }
    
    //@Test
    public void UnaReferenciaALaConexion(){
        String cp_name  = "GATEWAY";
        String vfd_name = "KARAF";
        String con_name = "H02A09";         
        System.out.println("******************************************");
        System.out.println("REFERENCIA A LA CONEXION");        
        System.out.println("******************************************");          
        int res = 45; 
        
        NativeLongByReference cp_descr_ptr = new NativeLongByReference();
        ShortBuffer cp_cref_ptr = ShortBuffer.allocate(100);      
        
        res = SapiLibrary.INSTANCE.s7_init(cp_name, vfd_name, cp_descr_ptr);
        assertEquals("Inicializo la libreria: ",SapiLibrary.S7_OK,res);  
        
        res = SapiLibrary.INSTANCE.s7_get_cref(cp_descr_ptr.getValue(), con_name, cp_cref_ptr);
        assertEquals("Tomando referencia a la conexion: ", SapiLibrary.S7_OK, res);         
        
        res = SapiLibrary.INSTANCE.s7_shut(cp_descr_ptr.getValue());
        assertEquals("Tumbo la libreria: ",SapiLibrary.S7_OK,res);
        System.out.println("******************************************");  
        System.out.println();        
    }
   
    //@Test
    public void ListadoDeConexiones(){
        String cp_name  = "GATEWAY";
        String vfd_name = "KARAF";
        String con_name = "H02A09"; 
        byte[] conn_name = new byte[255];  
        ShortBuffer number = ShortBuffer.allocate(100);
        System.out.println("******************************************");
        System.out.println("LISTADO DE CONEXIONES");        
        System.out.println("******************************************");   
        System.out.println("CP: " + cp_name);    
        System.out.println("VFD: " + vfd_name);       
        System.out.println("******************************************");          
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
            res = SapiLibrary.INSTANCE.s7_get_conn(cp_descr_ptr.getValue(), i, number, cp_cref_ptr, conn_name);
            assertEquals("Tomando referencia a la conexion: ", SapiLibrary.S7_OK, res);
            System.out.println("Conn("+i+"): "+Native.toString(conn_name));            
        }
              
        res = SapiLibrary.INSTANCE.s7_shut(cp_descr_ptr.getValue());
        assertEquals("Tumbo la libreria: ",SapiLibrary.S7_OK,res); 
        System.out.println("******************************************"); 
        System.out.println();         
    }
    
    @Test
    public void PruebaMiniDB(){
        String str;
        System.out.println("******************************************");
        System.out.println("CONFIGURACION COMUNICACIONES S7");        
        System.out.println("******************************************");        
        str = SapiLibrary.INSTANCE.s7_mini_db_get((short) SapiLibrary.S7_MINI_DB_INIT_CNF_AMQ_CALLING);
        System.out.println("S7_MINI_DB_INIT_CNF_AMQ_CALLING: " + str);  
        str = SapiLibrary.INSTANCE.s7_mini_db_get((short) SapiLibrary.S7_MINI_DB_INIT_CNF_AMQ_CALLED);
        System.out.println("S7_MINI_DB_INIT_CNF_AMQ_CALLED: " + str); 
        str = SapiLibrary.INSTANCE.s7_mini_db_get((short) SapiLibrary.S7_MINI_DB_INIT_CNF_PDU_SIZE);
        System.out.println("S7_MINI_DB_INIT_CNF_PDU_SIZE: " + str); 
        str = SapiLibrary.INSTANCE.s7_mini_db_get((short) SapiLibrary.S7_MINI_DB_INIT_REQ_PDU_SIZE);
        System.out.println("S7_MINI_DB_INIT_REQ_PDU_SIZE: " + str);
        System.out.println("******************************************"); 
        System.out.println();         
    }
    
    public void receive(NativeLong cp_descr,int last_event_expected){
        ShortBuffer cref = ShortBuffer.allocate(10);
        ShortBuffer orderid = ShortBuffer.allocate(10);
        
        ShortBuffer var_length_ptr = ShortBuffer.allocate(2);
        Memory value_ptr = new Memory(100);
        value_ptr.clear();
        var_length_ptr.put(0,(short)4);
        
        int ret = 0;
        int res = 0;        
        int counter = 0;
        boolean paso = true;
        
        do {
            ret = SapiLibrary.INSTANCE.s7_receive(cp_descr, cref, orderid);
            switch(ret){
                case SapiLibrary.S7_NO_MSG:
                        if (paso) {
                            System.out.println("Msg: S7_NO_MSG");
                            paso = false;
                        }
                    break;

                case SapiLibrary.S7_INITIATE_CNF:
                        System.out.println("Msg: S7_INITIATE_CNF");              
                        res = SapiLibrary.INSTANCE.s7_get_initiate_cnf();
                        System.out.println("s7_get_initiate_cnf: " + SapiLibrary.INSTANCE.s7_last_detailed_err_msg());  
                       
                        break;
                case SapiLibrary.S7_WRITE_CNF:
                        System.out.println("Msg: S7_WRITE_CNF");
                        //my_get_write_cnf();
                        break;
                case SapiLibrary.S7_MULTIPLE_WRITE_CNF:
                        System.out.println("Msg: S7_MULTIPLE_WRITE_CNF");
                        //my_get_multiple_write_cnf();
                        break;
                case SapiLibrary.S7_READ_CNF:
                        System.out.println("Msg: S7_READ_CNF"); 
                        SapiLibrary.INSTANCE.s7_get_read_cnf(Pointer.NULL, var_length_ptr, value_ptr);
                        System.out.println("Longitud de la data: " + var_length_ptr.get(0));
                        System.out.println("Valor real: " + value_ptr.getByte(1));
                        //my_read_cnf(cp_descr);
                        break;
                case SapiLibrary.S7_ABORT_IND:
                        System.out.println("Msg: S7_ABORT_IND");
                        res = SapiLibrary.INSTANCE.s7_get_abort_ind();
                        System.out.println("s7_get_abort_ind: " + SapiLibrary.INSTANCE.s7_last_detailed_err_msg());
                        break;
                case SapiLibrary.S7_BSEND_CNF:
                        System.out.println("Msg: S7_BSEND_CNF");
                        SapiLibrary.INSTANCE.s7_get_bsend_cnf();
                        break; 
                case SapiLibrary.S7_BRCV_IND:
                        System.out.println("Msg: S7_BRCV_IND");
                        //my_get_abort_ind(cp_descr);
                        break;                       
                default:
                    SapiLibrary.INSTANCE.s7_discard_msg();
                    //System.out.println("Evento no esperado: "+ret+" cref: "+cref.getValue());
            }
            counter++;
            if (counter>50000){
                System.out.println("Iteracciones superadas: " + counter);
                break;
            }                
        } while (ret != last_event_expected);

        
    }
    
    //@Test
    public void TestLoopPrincipal(){
        String cp_name  = "GATEWAY";
        String vfd_name = "KARAF";
        String con_name = "H02A05"; 
        byte[] conn_name = new byte[255];  
        ShortBuffer number = ShortBuffer.allocate(100);
    
        int res = 45; 
        short index = 0;
        short index2 = 0;
        
        System.out.println("******************************************");
        System.out.println("INICIALIZACION LOOP PRINCIPAL");        
        System.out.println("******************************************"); 
        
        NativeLongByReference cp_descr_ptr = new NativeLongByReference();
        ShortBuffer cp_cref_ptr = ShortBuffer.allocate(100);      
        
        res = SapiLibrary.INSTANCE.s7_init(cp_name, vfd_name, cp_descr_ptr);
        assertEquals("Inicializo la libreria: ",SapiLibrary.S7_OK,res);  
        
        res = SapiLibrary.INSTANCE.s7_get_conn(cp_descr_ptr.getValue(), index, number, cp_cref_ptr, conn_name);
        assertEquals("Tomando referencia a la conexion: ", SapiLibrary.S7_OK, res);
        
        receive(cp_descr_ptr.getValue(),SapiLibrary.S7_NO_MSG);

        res = SapiLibrary.INSTANCE.s7_shut(cp_descr_ptr.getValue());
        assertEquals("Tumbo la libreria: ",SapiLibrary.S7_OK,res);    
        
        System.out.println("******************************************"); 
        System.out.println();          
    }   
    
    @Test
    public void TestBSEND(){
        String cp_name  = "GATEWAY";
        String vfd_name = "KARAF";
        String con_name = "H02A05"; 
        byte[] conn_name = new byte[255];  
        byte[] buffer = new byte[32];        
        ShortBuffer number = ShortBuffer.allocate(100);
        NativeLong r_id = new NativeLong();
        NativeLong buffer_len = new NativeLong();
        Memory DB = new Memory(1000);
    
        int res = 45; 
        short index = 0;
        short index2 = 0;
        r_id.setValue(1);
        buffer_len.setValue(10);
        
        DB.setString(0,cp_name);
 
        System.out.println("******************************************");
        System.out.println("INICIALIZACION BSEND");        
        System.out.println("******************************************");         
        
        NativeLongByReference cp_descr_ptr = new NativeLongByReference();
        ShortBuffer cp_cref_ptr = ShortBuffer.allocate(100);      

        res = SapiLibrary.INSTANCE.s7_init(cp_name, vfd_name, cp_descr_ptr);
        assertEquals("Inicializo la libreria: ",SapiLibrary.S7_OK,res);  

        cp_cref_ptr.put((short) 123);
        cp_cref_ptr.clear();
        res = SapiLibrary.INSTANCE.s7_get_conn(cp_descr_ptr.getValue(), index, number, cp_cref_ptr, conn_name);
        assertEquals("Tomando referencia a la conexion: ", SapiLibrary.S7_OK, res);
        
        System.out.println("Referencia a la conexion: " + cp_cref_ptr.get(0) );
        System.out.println("Nombre de la conexion: " + Native.toString(conn_name));
        System.out.println("CP: " + cp_descr_ptr.getValue() );                

        res = SapiLibrary.INSTANCE.s7_initiate_req(cp_descr_ptr.getValue(), cp_cref_ptr.get(0));
        assertEquals("Inicia la conexión: " + SapiLibrary.INSTANCE.s7_last_detailed_err_msg(), SapiLibrary.S7_OK, res);        
        receive( cp_descr_ptr.getValue(), SapiLibrary.S7_INITIATE_CNF);  

        S7_READ_PARA rparam = new S7_READ_PARA("DB1000,REAL0");
        
        try {
            DB.clear();
            DB.setByte(0, (byte) 100);
            /*
            res = SapiLibrary.INSTANCE.s7_bsend_req(cp_descr_ptr.getValue(), cp_cref_ptr.get(0), (short) 1, r_id, DB, buffer_len);
            assertEquals("s7_bsend_req: " + SapiLibrary.INSTANCE.s7_last_detailed_err_msg(), SapiLibrary.S7_OK, res); 
            
            receive(cp_descr_ptr.getValue(),SapiLibrary.S7_BSEND_CNF);
            
            */

            res = SapiLibrary.INSTANCE.s7_read_req(cp_descr_ptr.getValue(), 
                         cp_cref_ptr.get(0),(short) 1, rparam);
            
            assertEquals("s7_read_req: " + SapiLibrary.INSTANCE.s7_last_detailed_err_msg(),SapiLibrary.S7_OK,res);  
                                                         
            receive(cp_descr_ptr.getValue(),SapiLibrary.S7_READ_CNF);
   
        } catch (Exception ex) {
            Logger.getLogger(InitShut.class.getName()).log(Level.SEVERE, null, ex);
        }
        res = SapiLibrary.INSTANCE.s7_shut(cp_descr_ptr.getValue());
        assertEquals("Tumbo la libreria: ",SapiLibrary.S7_OK,res);   
        
        System.out.println("******************************************"); 
        System.out.println();         
    }     
    
    
    
}
