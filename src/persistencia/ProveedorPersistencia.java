package persistencia;

import principal.Estudi;

/**
 *
 * @author cesca
 */
public interface ProveedorPersistencia {
    public void desarEstudi(String nomFitxer, Estudi estudi);
    public void carregarEstudi(String nomFitxer); 
}
