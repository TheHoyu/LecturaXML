/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package libros_ad;

import clases.AccesoDOM;
import java.io.File;

/**
 *
 * @author Hoyu
 */
public class Libros_AD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AccesoDOM a = new AccesoDOM();
        File f = new File("Libros.xml");
        a.abrirXMLaDOM(f);
        a.recorreDOMyMuestra();
        a.insertarLibroEnDOm("Rambo4", "Stallone", "01/01/2001");
    }
    
}
