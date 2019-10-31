/*
 * Classe que defineix un dissenyador o dissenyadora. Un dissenyador o dissenyadora
 * es defineix pel seu nif, nom i estat, és a dir, actiu si està treballant o no 
 * actiu si està de baixa o vacances.
 */
package components;

/**
 *
 * @author root
 */
public class Dissenyador extends Treballador {

    /*
     TODO CONSTRUCTOR
     */
    public Dissenyador(String nif, String nom) {
       super(nif,nom);
    }

    /*
     TODO
     Paràmetres: cap
     Accions:
     - Demanar a l'usuari les dades per consola per crear un nou dissenyador o nova
     dissenyadora. Les dades a demanar són les que passem per paràmetre en el constructor.
     - També heu de tenir en compte que tant el nom pot ser una frase, per exemple, 
     Francesc Xavier.
     Retorn: El nou dissenyador o nova dissenyadora creat/da.
     */
    public static Dissenyador addDissenyador() {

        String nif;
        String nom;

        System.out.println("NIF del dissenyador o dissenyadora:");
        nif = DADES.next();
        DADES.nextLine(); //Neteja buffer
        System.out.println("Nom del dissenyador o dissenyadora:");
        nom = DADES.nextLine();

        return new Dissenyador(nif, nom);
    }
    
}
