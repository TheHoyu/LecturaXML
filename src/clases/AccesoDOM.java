
package clases;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;//for Document
import org.w3c.dom.Document;
import java.util.*;
import java.io.*;//clase File

public class AccesoDOM {
    Document doc;
    
    public int abrirXMLaDOM(File f){
        try{
            System.out.println("Abriendo archivo XML file y generando DOM");
            //creamos documentbuilder al que apunta la variable
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            //tenemos el método parse que genera DOM en memoria
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc=builder.parse(f);
            //Ahora doc apunta al arbol yodemos recorrerlo;
            System.out.println("Dom creado con exito");
            return 0; //si funciona.           
            
        }catch(Exception e){
            System.out.println(e);
            return-1;//Si el metodo aborta en algun punto 
        }
    }
    
    
    public void recorreDOMyMuestra(){
        String [] datos = new String[3]; // usado para la info de cada libro
        Node nodo = null;
        Node root = doc.getFirstChild();
        NodeList nodelist = root.getChildNodes();
        //recorremos el arbol y el 1º nivel de nodos hijos del raiz 
        for (int i = 0; i<nodelist.getLength(); i++) {
            nodo=nodelist.item(i);
            if(nodo.getNodeType()==Node.ELEMENT_NODE){
                Node ntemp=null;
                int contador = 1;
                //Sacamos el valor del atributo publicado
                datos[0]= nodo.getAttributes().item(0).getNodeName();
                //sacamos los valores de los hijos de nodo, titulo y autor 
                NodeList n12=nodo.getChildNodes();//obtenemos la lista de hijos (2)
                for (int j = 0; j <n12.getLength(); j++) {
                    ntemp=n12.item(j);
                    if(ntemp.getNodeType()==Node.ELEMENT_NODE){
                        if(ntemp.getNodeType()==Node.ELEMENT_NODE){
//                            //para conseguir el texto de titulo y autor,se 
//                            puede hacer con getnodevalue(), tambien con get
//                                    textcontent() si es ELEMET
                             datos[contador]=ntemp.getTextContent();              
                            // tambien 
                            //datos[contador]=ntemp.getChildNodes().item(0).getNodeValue();
                            
                            contador++;
                        }
                    }
                }
                System.out.println(datos[0]+"--"+datos[2]+"--"+datos[1]);

            }
            
        }
    }
    
    public int insertarLibroEnDOm(String titulo,String autor,String fecha){
        try{
            System.out.println("----Añadir libro al arbol dom:"+titulo+";"+autor+";"+fecha);
            //crea los nodos => los añade al padre desde las hojas a la raiz
            //create titulo con el texto medio.
            Node ntitulo=doc.createElement("Titulo");//crea etiquetas;
            //<Titulo>...</Titulo>...
            Node ntitulo_text=doc.createTextNode(titulo);//crea el nodo terxto para el titulo
            ntitulo.appendChild(ntitulo_text); // añade el titulo a las etiquetas
            Node nautor =doc.createElement("Autor");
            Node nautor_text=doc.createTextNode(autor);
            nautor.appendChild(nautor_text);
            
            //Crea libro , con atributos y nodos titulo y autor final.
            Node nLibro=doc.createElement("Libro");
            
            ((Element)nLibro).setAttribute("publicado",fecha);
            nLibro.appendChild(ntitulo);
            nLibro.appendChild(nautor);
            //append libro to the root 
            
            
            nLibro.appendChild(doc.createTextNode("\n"));//insertar saltos de linea
            Node raiz = doc.getFirstChild();//tb doc.getChilNodes().item(0)
            raiz.appendChild(nLibro);
            System.out.println("Libro insertado en DOM: ");
            return 0;
           
        }catch(Exception e){
            System.out.println(e);
            return -1;
        }
    }
    
    public int deleteNode(String tit){
        System.out.println("Buscando el libro "+tit+"para borrarlo");
        
        try{
            //Node root)=doc.getFirstChil();
            Node raiz= doc.getDocumentElement();
            NodeList nl1=doc.getElementsByTagName("Titulo");
            Node n1;
            
            for (int i = 0;i<nl1.getLength(); i++) {
                n1=nl1.item(i);
                
                if(n1.getNodeType()==Node.ELEMENT_NODE){//redundante por getelemetsbt tagname, no lo es si usamos getchild notes
                    if(n1.getChildNodes().item(0).getNodeValue().equals(tit)){
                        System.out.println("Borrando el nodo <libro> con titulo"+tit);
                        //n1,getParentNode().removeChild(n1);//borra <titulo> tit</titulo> , pero deja libro y autor
                        n1.getParentNode().getParentNode().removeChild(n1.getParentNode());
                        
                    }
                    
                }
            }
            
            System.out.println("Nodo borrado");
            
            //guardar el arbol DOM en un nuevo arcghivio para mantener nuestro archivo orignal 
            // guardar DOMcomo Archivo("libros borrados .xml);
            return 0;
        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
            return -1;
        }
        
    }
  
    void guardarDOmcomoArchivo(String nuevoArchivo){
        
    }
   } 
    

    

    


    


