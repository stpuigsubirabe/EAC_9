/*
 * Interfície que implementarà qualsevol component de l'estudi
 */
package principal;

import java.util.Scanner;

/**
 *
 * @author FTA
 */
public interface Component {  
    
    public final static Scanner DADES = new Scanner(System.in);
    public void updateComponent(); 
    public void showComponent();     
    
}
