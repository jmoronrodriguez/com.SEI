/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nominas.sei;
//import org.apache.pdfbox.pdmodel.PDPage;

import org.apache.pdfbox.pdmodel.PDPage;

/**
 *
 * @author jmoron
 */
public class PaginaNomina implements Comparable<PaginaNomina>{
    private PDPage pagina;
    private String nombre;

    public PaginaNomina(PDPage pagina, String nombre) {
        this.pagina = pagina;
        this.nombre = nombre;
    }

    @Override
    public int compareTo(PaginaNomina t) {
        return nombre.toLowerCase().compareTo(t.nombre.toLowerCase());
    }

    public String getNombre() {
        return nombre;
    }

    public PDPage getPagina() {
        return pagina;
    }
    
    
}
