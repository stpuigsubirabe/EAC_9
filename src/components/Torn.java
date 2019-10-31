/*
 * Classe que defineix un torn. Un torn es defineix pel seu codi, nom, hora d'inici
 * i hora de finalització del torn.
 */
package components;

import principal.Component;

/**
 *
 * @author root
 */
public class Torn implements Component{

    private String codi;
    private String nom;
    private String horaInici;
    private String horaAcabament;

    /*
     TODO CONSTRUCTOR
     Paràmetres: valors per tots els atributs de la classe.
     Accions:
     - Assignar als atributs els valors passats com a paràmetres.
     */
    public Torn(String codi, String nom, String horaInici, String horaAcabament) {
        this.codi = codi;
        this.nom = nom;
        this.horaInici = horaInici;
        this.horaAcabament = horaAcabament;
    }

    /*
     TODO Mètodes accessors    
     */
    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getHoraInici() {
        return horaInici;
    }

    public void setHoraInici(String horaInici) {
        this.horaInici = horaInici;
    }

    public String getHoraAcabament() {
        return horaAcabament;
    }

    public void setHoraAcabament(String horaAcabament) {
        this.horaAcabament = horaAcabament;
    }

    /*
     TODO
     Paràmetres: cap
     Accions:
     - Demanar a l'usuari les dades per consola per crear un nou torn. Les dades 
     a demanar són les que passem per paràmetre en el constructor.
     - Per assignar les hores d'inici i finalització (acabament) del torn, heu d'utilitzar el mètode
     escaient d'aquesta classe.
     Retorn: El nou torn creat.
     */
    public static Torn addTorn() {

        String codi;
        String nom;
        String horaInici;
        String horaAcabament;

        System.out.println("Codi del torn:");
        codi = DADES.next();
        System.out.println("Nom del torn:");
        nom = DADES.next();
        System.out.println("Hora d'inici del torn:");
        horaInici = horesTorn();
        System.out.println("Hora de finalització del torn:");
        horaAcabament = horesTorn();

        return new Torn(codi, nom, horaInici, horaAcabament);
    }

    /*
     TODO
     Paràmetres: cap
     Accions:
     - Demanar a l'usuari que introdueixi les noves dades de l'objecte actual
     i modificar els atributs corresponents d'aquest objecte (Penseu com heu de
     modificar les hores d'inici i de finalització del torn).
     - Li heu de mostrar a l'usuari els valors dels atributs abans de modificar-los.
     Retorn: cap
     */
    public void updateComponent() {
        System.out.println("\nCodi del torn: " + codi);
        System.out.println("\nEntra el nou codi:");
        codi = DADES.next();
        System.out.println("\nNom del torn: " + nom);
        System.out.println("\nEntra el nou nom:");
        nom = DADES.next();
        System.out.println("Hora d'inici del torn: "+horaInici);
        System.out.println("\nEntra la nova hora d'inci del torn:");
        horaInici = horesTorn();
        System.out.println("Hora d'acabament del torn: "+horaAcabament);
        System.out.println("\nEntra la nova hora d'acabament del torn:");
        horaAcabament = horesTorn();
        
    }

    public void showComponent() {
        System.out.println("\nLes dades del torn amb codi " + codi + " són:");
        System.out.println("\nNom: " + nom);
        System.out.println("\nHora d'inici: "+horaInici);
        System.out.println("\nHora de finalització: "+horaAcabament);
    }

    /*
     TODO
     Paràmetres: cap
     Accions:
     - Demanar a l'usuari les dades per consola per crear les hores d'inici o finalització
     d'un torn.
     - Tant l'hora d'inici com la de finalització, han de tenir el format "hh:mm", on
     hh és l'hora en format 24 hores i mm els minuts. Perquè es respecti el format,
     primer demanarem l'hora a l'usuari i després els minuts. El mateix mètode és
     el que haurà de crear el format correcte amb les dades introduïdes per l'usuari.
     - Heu de controlar que les hores i minuts introduïts siguin correctes. Si no 
     és així, se li tornaran a demanar a l'usuari fins que introdueixi unes dades correctes.
     Retorn: Les hores d'inici o finalització del torn.
     */
    public static String horesTorn() {

        int hora;
        int minuts;
        String stHora;
        String stMinuts;

        do {
            System.out.println("Hora:");
            hora = DADES.nextInt();
        } while (hora < 0 || hora > 23);

        do {
            System.out.println("Minuts:");
            minuts = DADES.nextInt();
        } while (hora < 0 || hora > 60);

        if (hora >= 0 && hora <= 9) {
            stHora = "0"+hora;
        } else {
            stHora = String.valueOf(hora);
        }

        if (minuts >= 0 && minuts <= 9) {
            stMinuts = "0"+minuts;
        } else {
            stMinuts = String.valueOf(minuts);
        }

        return stHora + ":" + stMinuts;
    }
}
