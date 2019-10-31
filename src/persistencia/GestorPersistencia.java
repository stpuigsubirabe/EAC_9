package persistencia;

import principal.Estudi;
import principal.GestorEstudisException;

/**
 *
 * @author cesca
 */
public class GestorPersistencia {
     private GestorXML gestor;

    public GestorXML getGestor() {
        return gestor;
    }

    public void setGestor(GestorXML pGestor) {
        gestor = pGestor;
    }

    public void desarEstudi(String tipusPersistencia, String nomFitxer, Estudi estudi) throws GestorEstudisException{
        if (tipusPersistencia.equals("XML")) {
            gestor = new GestorXML();
            gestor.desarEstudi(nomFitxer, estudi);
        }
    }

    public void carregarEstudi(String tipusPersistencia, String nomFitxer) throws GestorEstudisException{
       
        if (tipusPersistencia.equals("XML")) {
            gestor = new GestorXML();
            gestor.carregarEstudi(nomFitxer);
        }
    }
}
