package persistencia;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import principal.Estudi;
import principal.GestorEstudisException;
import org.w3c.dom.Element;
import principal.Component;
import components.Dissenyador;
import components.Jardiner;
import components.Torn;
import components.Treballador;
import java.util.Map;
import principal.Projecte;
import org.w3c.dom.Node;

/**
 *
 * @author cesca
 */
public class GestorXML {
    private Document doc;
    private Estudi estudi;

    public Estudi getEstudi() {
        return estudi;
    }

    public void setEstudi(Estudi estudi) {
        this.estudi = estudi;
    }

    public void desarEstudi(String nomFitxer, Estudi estudi) throws GestorEstudisException {
        construeixModel(estudi);
        desarModel(nomFitxer);
    }

    public void carregarEstudi(String nomFitxer) throws GestorEstudisException {
        carregarFitxer(nomFitxer);
        fitxerEstudi();
    }

    /*Paràmetres: Estudi a partir de la qual volem construir el model
     *
     *Acció: 
     * - Llegir els atributs de l'objecte estudi passat per paràmetre 
     *   per construir un model (document XML) sobre el Document doc (atribut de GestorXML).
     *
     * - L'arrel del document XML és "estudi". Aquesta arrel, l'heu d'afegir 
     *   a doc. Un cop fet això, heu de recórrer l'ArrayList components d'estudi
     *   i per a cada component, afegir un fill a doc. Cada fill 
     *   tindrà com atributs els atributs de l'objecte (nif, nom, …).
     *   Si l'atribut a afegir és l'atribut booleà actiu o finalitzat, quan aquests siguin verdaders
     *   el valor de l'atribut del document XML serà 1, en cas contrari 0.
     *
     * - Si es tracta d'un jardiner, a més, heu d'afegir un fill addicional amb 
     *   el seu torn.
     *
     * - Si es tracte d'un projecte, a més, heu d'afegir fills addicionals amb 
     *   els dissenyador i jardiners assignats.
     *
     *Retorn: cap
     */
    public void construeixModel(Estudi estudi) throws GestorEstudisException {
        
        //Recullim els components de l'estudi.
         List <Component> components = estudi.getComponents();
        //Declarem un iterador per poder llegir els components
         Iterator<Component> iteradorComponents = components.iterator();
        
        
        //Element arrel l'estudi
        Element arrel = doc.createElement("estudi");
        arrel.setAttribute("adreca", estudi.getAdreca());
        arrel.setAttribute("codi", Integer.toString(estudi.getCodi()));
        arrel.setAttribute("nom", estudi.getNom());
        doc.appendChild(arrel);
        
        //Llegim els elements de l'estudi per plasmar-los a doc  
        while(iteradorComponents.hasNext()){
            Component component = iteradorComponents.next();
            if (component instanceof Dissenyador){
                dissenyadorToXML(component,arrel);
            }else if(component instanceof Jardiner){
                jardinerToXML(component,arrel);
            }else if(component instanceof Torn){
                tornToXML(component,arrel);
            }else if(component instanceof Projecte){
                projecteToXML(component,arrel);
            }
        }                 
    }

    public void desarModel(String nomFitxer) throws GestorEstudisException {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(doc);
            File f = new File(nomFitxer + ".xml");
            StreamResult result = new StreamResult(f);
            transformer.transform(source, result);
        } catch (Exception ex) {
            throw new GestorEstudisException("GestorXML.desar");
        }
    }

    public void carregarFitxer(String nomFitxer) throws GestorEstudisException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            File f = new File(nomFitxer + ".xml");
            doc = builder.parse(f);
        } catch (Exception ex) {
            throw new GestorEstudisException("GestorXML.carrega");
        }
    }
    
    /*Paràmetres: cap
     *
     *Acció: 
     * - Llegir el fitxer del vostre sistema i carregar-lo sobre l'atribut doc, 
     *   per assignar valors als atributs d'estudi i la resta d'objectes que formen 
     *   els components d'estudi.
     *    
     * - Primer heu de crear l'objecte estudi a partir de l'arrel del document XML
     *   per després recórrer el doc i per cada fill, afegir un objecte a l'atribut 
     *   components d'estudi mitjançant el mètode escaient de la classe Estudi.
    
     * - Si l'element del document XML que s'ha llegit és un jardiner, recordeu 
     *   que a més d'afegir-lo a components, també haureu d'assignarli el torn 
     *   corresponent.
     *
     * - Si l'element del document XML que s'ha llegit és un projecte, recordeu 
     *   que a més d'afegir-lo a components, també haureu d'assignar-li
     *   el dissenyador i jardiners corresponents.
     *
     * - Penseu que els estats actius dels treballadors i finalitzats del projecte,
     *   s'han d'afegir un cop creat l'objecte pertinent.
     *
     *Retorn: cap
     */
    private void fitxerEstudi() throws GestorEstudisException {
        
    }
    /*
    Retorna 1 si es actiu 0 si es no actiu
    Retorna 1 si es projecte esta finalitzat 0 si no ho esta
    */
    public int booleanToInt(boolean value) {
        // Convert true to 1 and false to 0.
        return value ? 1 : 0;
    }
    /*
    Construeix model per als dissenyadors
    */
    public void dissenyadorToXML(Component comp, Node node ){
        Element dissenyador = doc.createElement("dissenyador");
        int actiu = booleanToInt (((Dissenyador)comp).getActiu());
        dissenyador.setAttribute("actiu", Integer.toString(actiu));
        dissenyador.setAttribute("nif",((Dissenyador) comp).getNif());
        dissenyador.setAttribute("nom", ((Dissenyador) comp).getNom());
        dissenyador.appendChild(node);
    }
    /*
    Construeix model per als jardiners
    */
    public void jardinerToXML(Component comp, Node node ){
        Element jardiner = doc.createElement("jardiner");
        int actiu = booleanToInt (((Jardiner)comp).getActiu());
        jardiner.setAttribute("actiu",  Integer.toString(actiu));
        jardiner.setAttribute("nif", ((Jardiner) comp).getNif());
        jardiner.setAttribute("nom", ((Jardiner) comp).getNom());
        jardiner.appendChild(node);
        if (((Jardiner)comp).getTorn()!= null){
            Component torn = ((Jardiner)comp).getTorn();
            tornToXML(torn,jardiner);
        }
    }
    /*
    Construeix model per als torns
    */
    public void tornToXML(Component comp, Node node ){
        Element torn = doc.createElement("torn");
        torn.setAttribute("codi", ((Torn) comp).getCodi());
        torn.setAttribute("horaAcabament", ((Torn) comp).getHoraAcabament());
        torn.setAttribute("horaInici", ((Torn) comp).getHoraInici());
        torn.setAttribute("nom", ((Torn) comp).getNom());
        torn.appendChild(node);
    }
    /*
    Construeix model per als projectes
    */
    public void projecteToXML(Component comp, Node node ){
        Element projecte = doc.createElement("projecte");
        int codi = ((Projecte) comp).getCodi();
        projecte.setAttribute("codi", Integer.toString(codi));
        projecte.setAttribute("nifClient", ((Projecte) comp).getNifClient());
        int finalitzat = booleanToInt (((Projecte)comp).isFinalitzat());
        projecte.setAttribute("finalitzat", Integer.toString(finalitzat));
        double pressupost = ((Projecte)comp).getPressupost();
        projecte.setAttribute("pressupost", Double.toString(pressupost));
        projecte.appendChild(node);
        
        // Recullim el hashMap del projecte i creem un iterador
        Map <String,Treballador> treballadors = ((Projecte)comp).getTreballadors();
        Iterator iteradorTreballadors = treballadors.keySet().iterator();
        
        if (treballadors.isEmpty() == false){
            
            while (iteradorTreballadors.hasNext()){
                String key = (String) iteradorTreballadors.next();
                Component treballador = treballadors.get(key);
                if (key.equals("dissenyador")){
                    dissenyadorToXML(treballador,projecte);
                }else{
                    jardinerToXML(treballador,projecte);
                }
            }
        }
    }
    
}
