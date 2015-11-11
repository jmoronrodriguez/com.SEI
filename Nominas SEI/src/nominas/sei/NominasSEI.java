/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nominas.sei;

import java.awt.Rectangle;
import java.awt.print.PageFormat;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.PDFTextStripperByArea;
import java.util.*;
/**
 *
 * @author jmoron
 */
public class NominasSEI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        ArrayList<PaginaNomina> paginasNomina=new ArrayList<PaginaNomina>();

        for (int x = 0; x < 1; x++) {//RECORREMOS EL ARREGLO CON LOS NOMBRES DE ARCHIVO
            String ruta = new String();//VARIABLE QUE DETERMINARA LA RUTA DEL ARCHIVO A LEER.
            ruta = (".\\NOMINAS.pdf"); //SE ALMACENA LA RUTA DEL ARCHIVO A LEER. 

            try {
                PDDocument pd = PDDocument.load(ruta); //CARGAR EL PDF
                List l = pd.getDocumentCatalog().getAllPages();//NUMERO LAS PAGINAS DEL ARCHIVO
                Object[] obj = l.toArray();//METO EN UN OBJETO LA LISTA DE PAGINAS PARA MANIPULARLA
                for (int i = 0; i < l.size(); i++) {
                    PDPage page = (PDPage) obj[i];//PAGE ES LA PAGINA 1 DE LA QUE CONSTA EL ARCHIVO
                    PageFormat pageFormat = pd.getPageFormat(0);//PROPIEDADES DE LA PAGINA (FORMATO)
                    Double d1 = new Double(pageFormat.getHeight());//ALTO
                    Double d2 = new Double(pageFormat.getWidth());//ANCHO
                    int width = d1.intValue();//ANCHO
                    int eigth = 1024;//ALTO

                    PDFTextStripperByArea stripper = new PDFTextStripperByArea();//COMPONENTE PARA ACCESO AL TEXTO
                    Rectangle rect = new Rectangle(0, 0, width, eigth);//DEFNIR AREA DONDE SE BUSCARA EL TEXTO
                    stripper.addRegion("area1", rect);//REGISTRAMOS LA REGION CON UN NOMBRE
                    stripper.extractRegions(page);//EXTRAE TEXTO DEL AREA

                    String contenido = new String();//CONTENIDO = A LO QUE CONTENGA EL AREA O REGION
                    contenido = (stripper.getTextForRegion("area1"));
                    String[] lines = contenido.split("[\\r\\n]+");
                    String nombre = lines[1].substring(28, lines[1].length() - 10);
                    PaginaNomina nomina = new PaginaNomina(page, nombre);
                    paginasNomina.add(nomina);
                }
                Collections.sort(paginasNomina);
                // Create a new empty document
                PDDocument document = new PDDocument();

                

                
                for (int i=0; i<paginasNomina.size(); i++){
                    System.out.println(paginasNomina.get(i).getNombre());
                    document.addPage( paginasNomina.get(i).getPagina() );
                }
                // Save the newly created document
                document.save("NominasOrdenadas.pdf");

                // finally make sure that the document is properly
                // closed.
                document.close();
                pd.close();//CERRAMOS OBJETO ACROBAT
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }//CATCH
        }//FOR

    }

}
