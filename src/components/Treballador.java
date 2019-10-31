package components;

import principal.Component;

/**
 *
 * @author root
 */
public abstract class Treballador implements Component {
    
    private String nif;
    private String nom;
    private boolean actiu;
    
    public Treballador(String nif, String nom) {
        this.nif = nif;
        this.nom = nom;
        actiu = true;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean getActiu() {
        return actiu;
    }

    public void setActiu(boolean actiu) {
        this.actiu = actiu;
    }
    
    public void updateComponent() {
        System.out.println("\nNIF del treballador o treballadora: " + nif);
        System.out.println("\nEntra el nou nif:");
        nif = DADES.next();
        DADES.nextLine(); //Neteja buffer
        System.out.println("\nNom del treballador o treballadora: " + nom);
        System.out.println("\nEntra el nou nom:");
        nom = DADES.nextLine();
        
        if(actiu){
            System.out.println("\nEl treballador o treballadora està en actiu");
        }else{
            System.out.println("\nEl treballador o treballadora no està en actiu");
        }
        
        System.out.println("\nEntra el nou estat (1 si està en actiu o 0 en cas contrari):");
        
        int estat=DADES.nextInt();
        
        if(estat==1){
            actiu=true;
        }else{
            actiu=false;
        }
    }
    
    public void showComponent() {
        System.out.println("\nLes dades del treballador o treballadora amb nif " + nif + " són:");
        System.out.println("\nNom: " + nom);
        System.out.println("\nL'estat és: ");
        
        if(actiu){
            System.out.print("Actiu");
        }else{
            System.out.print("No actiu");
        }
    }
}
