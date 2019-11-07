/*
 * Classe que defineix un jardiner o jardinera. Un jardiner o jardinera es defineix 
 * pel seu NIF, nom, torn de feina i estat, és a dir, actiu si està treballant o no actiu si està 
 * de baixa o vacances.
 */
package components;
import principal.GestorEstudisException;

/**
 *
 * @author root
 */
public class Jardiner extends Treballador{

    private Torn torn;

    /*
     TODO CONSTRUCTOR
     */
    public Jardiner(String nif, String nom) {
        super(nif,nom);
        this.torn = null;
    }

    /*
     TODO Mètodes accessors    
     */
    public Torn getTorn() {
        return torn;
    }

    public void setTorn(Torn torn) {
        this.torn = torn;
    }

    /*
     TODO
     Paràmetres: cap
     Accions:
     - Demanar a l'usuari les dades per consola per crear un nou jardiner o nova
     jardinera. Les dades a demanar són les que passem per paràmetre en el constructor.
     - També heu de tenir en compte que tant el nom pot ser una frase, per exemple, 
     Francesc Xavier.
     Retorn: El nou jardiner o nova jardinera creat/da.
     */
    public static Jardiner addJardiner() {

        String nif;
        String nom;
        Torn torn;

        System.out.println("NIF del jardiner o jardinera:");
        nif = DADES.next();
        DADES.nextLine(); //Neteja buffer
        System.out.println("Nom del jardiner o jardinera:");
        nom = DADES.nextLine();        

        return new Jardiner(nif, nom);
    }

    @Override
    public void showComponent() throws GestorEstudisException {
        super.showComponent();
        if (this.getTorn()== null){ throw new GestorEstudisException("2");}
        torn.showComponent();
    }
}
