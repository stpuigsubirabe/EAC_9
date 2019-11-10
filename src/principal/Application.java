package principal;

import components.Dissenyador;
import components.Jardiner;
import components.Torn;
import java.util.Scanner;
import persistencia.GestorPersistencia;
import java.util.InputMismatchException;
import java.util.Iterator;

/**
 *
 * @author root
 */
public class Application {

    private final static Scanner DADES = new Scanner(System.in);

    static private Estudi[] estudis = new Estudi[4];
    static private int posicioEstudis = 0;
    static private Estudi estudiActual = null;
    static private String FITXER = "estudi";
    static private GestorPersistencia gp = new GestorPersistencia();

    public static void main(String[] args) {
            try{
                menuPrincipal();
            }catch(GestorEstudisException e){System.out.println(e.getMessage());}
    }

    private static void menuPrincipal() throws GestorEstudisException {
        int opcio = 0;

        do {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Gestió d'estudis");
            System.out.println("\n2. Gestió de projectes");
            System.out.println("\n3. Gestió de dissenyadors o dissenyadores");
            System.out.println("\n4. Gestió de jardiners o jardineres");
            System.out.println("\n5. Gestió de torns");
            try{
                opcio = DADES.nextInt();
            }catch(Exception e){
                if (e instanceof InputMismatchException){throw new GestorEstudisException("1"); }
            }
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    menuEstudis();
                    break;
                case 2:
                    if (estudiActual != null) {
                        menuProjectes();
                    } else {
                        System.out.println("\nPrimer s'ha de seleccionar l'estudi al menú Gestió d'estudis");
                    }
                    break;
                case 3:
                    if (estudiActual != null) {
                        menuComponents(1);
                    } else {
                        System.out.println("\nPrimer s'ha de seleccionar l'estudi al menú Gestió d'estudis");
                    }
                    break;
                case 4:
                    if (estudiActual != null) {
                        menuComponents(2);
                    } else {
                        System.out.println("\nPrimer s'ha de seleccionar l'estudi al menú Gestió d'estudis");
                    }
                    break;
                case 5:
                    if (estudiActual != null) {
                        menuComponents(3);
                    } else {
                        System.out.println("\nPrimer s'ha de seleccionar l'estudi al menú Gestió d'estudis");
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static void menuEstudis() throws GestorEstudisException {
        int opcio = 0;

        do {
            int indexSel = -1;
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Seleccionar estudi");
            System.out.println("\n3. Modificar");
            System.out.println("\n4. Llista d'estudis");
            System.out.println("\n5. Carregar estudi");
            System.out.println("\n6. Desar estudi");
            try{
            opcio = DADES.nextInt();
            }catch(InputMismatchException e){throw new GestorEstudisException("1");}
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    try{ 
                    estudis[posicioEstudis] = Estudi.addEstudi();
                    }catch(ArrayIndexOutOfBoundsException e){ throw new GestorEstudisException("9");} 
                    posicioEstudis++;
                    break;
                case 2:
                    indexSel = selectEstudi();
                    if (indexSel >= 0) {
                        estudiActual = estudis[indexSel];
                    } else {
                        System.out.println("\nNo existeix aquest estudi");
                    }
                    break;
                case 3:
                    indexSel = selectEstudi();
                    if (indexSel >= 0) {
                        estudis[indexSel].updateComponent();
                    } else {
                        System.out.println("\nNo existeix aquest estudi");
                    }
                    break;
                case 4:
                    for (int i = 0; i < posicioEstudis; i++) {
                        estudis[i].showComponent();
                    }
                    break;
                case 5: //Carregar estudi
                    posicioEstudis = 0;
                    estudis = new Estudi[1]; //Només podem carregar un estudi
                    gp.carregarEstudi("XML", FITXER);
                    estudis[posicioEstudis] = gp.getGestor().getEstudi();
                    posicioEstudis++;
                    break;
                case 6: //Desar estudi
                    int pos = selectEstudi();
                    if (pos >= 0) {
                        gp.desarEstudi("XML", FITXER, estudis[pos]);
                    } else {
                        System.out.println("\nNo existeix aquest estudi");
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    /*
     TODO Heu de desenvolupar el menuDissenyadors amb les opcions que podeu veure.
     Nota: penseu que quan arribem aquí, l'atribut estudiActual no és null
       
     Opció 0. Sortir -->         Surt del menú i retorna al menú principal
     Opció 1. Alta -->           Crea un dissenyador en l'estudi actual. Penseu que Estudi sap crear dissenyadors o dissenyadores        
     Opció 2. Modificar ->     Permet modificar un dissenyador o dissenyadora que està donat d'alta a l'estudi actual
     (per comprovar l'existència del dissenyador o dissenyadora tenim un mètode en la classe Estudi que ens ajuda)
     Opció 3. Llista de dissenyadors i dissenyadores -> Imprimeix les dades dels dissenyadors i dissenyadores de l'estudi actual
        
     A més, heu de fer una estructura iterativa per tornar a mostrar el menú sempre que no es premi l'opció 0 de sortida
     Recomanacions:
     - estructura de control switch-case per bifurcar les opcions
     - si no s'ha introduït cap opció de les de la llista, s'ha de mostrar el missatge
     "S'ha de seleccionar una opció correcta del menú."
     - definiu una variable opcio de tipus enter
     Nota important: 
     no controlem que l'usuari introdueixi una opció numèrica, ja que això ho farem amb la
     tècnica de les excepcions que veurem més endavant
     */
    public static void menuComponents(int tipus)throws GestorEstudisException {
        int opcio = 0;

        do {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Modificar");
            if (tipus == 2) {
                System.out.println("\n3. Assignar torn");
                System.out.println("\n4. Llista de jardiners i jardineres");
            } else {
                System.out.println("\n3. Llista");
            }
            try{
            opcio = DADES.nextInt();
            }catch(InputMismatchException e){throw new GestorEstudisException("1");}
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    switch (tipus) {
                        case 1:
                            estudiActual.addDissenyador();
                            break;
                        case 2:
                            estudiActual.addJardiner();
                            break;
                        case 3:
                            estudiActual.addTorn();
                            break;
                    }
                    break;
                case 2:
                    int indexSel = estudiActual.selectComponent(tipus, null);
                    if (indexSel >= 0) {
                        estudiActual.getComponents().get(indexSel).updateComponent();
                    } else {
                        System.out.println("\nNo existeix aquest component");
                    }
                    break;
                case 3:
                    if (tipus == 2) {
                        try{
                        estudiActual.addTornJardiner();
                        }catch(GestorEstudisException e){System.out.println(e.getMessage());}
                    } else {
                        Iterator<Component> iteradorComponents = estudiActual.getComponents().iterator();
                        while (iteradorComponents.hasNext()){
                            Component comp = iteradorComponents.next();
                            if (comp instanceof Dissenyador && tipus == 1){
                                try{
                                    ((Dissenyador)comp).showComponent(); 
                                }catch(GestorEstudisException e){System.out.println(e.getMessage());}
                                
                            }else if (comp instanceof Torn && tipus == 3){
                                //try{
                                    ((Torn)comp).showComponent();
                                //}catch(GestorEstudisException e){System.out.println(e.getMessage());}
                            
                            }
                        } 
                        
                    /* AQUEST CODI DESAPAREIX AMB LES NOVES IMPLEMENTACIONS
        ---------------------------------------------------------------------------------------
                            
                        for (int i = 0; i < estudiActual.getPosicioComponents(); i++) {
                            if (estudiActual.getComponents()[i] instanceof Dissenyador && tipus == 1) {
                                try{
                                estudiActual.getComponents()[i].showComponent();
                                }catch(GestorEstudisException e){System.out.println(e.getMessage());}
                            } else if (estudiActual.getComponents()[i] instanceof Torn && tipus == 3) {
                                try{
                                estudiActual.getComponents()[i].showComponent();
                                }catch(GestorEstudisException e){System.out.println(e.getMessage());}
                            }
                        }
                    */    
                    }
                       
                    break;
                case 4:
                    Iterator<Component> iteradorComponents = estudiActual.getComponents().iterator();
                    while (iteradorComponents.hasNext()){
                        Component comp = iteradorComponents.next();
                            if (comp instanceof Jardiner ){
                                try{
                                    ((Jardiner) comp).showComponent();
                                }catch(GestorEstudisException e){System.out.println(e.getMessage());}
                            }
                    }
                    /*
                    AQUEST CODI DESAPAREIX AMB LA NOVA IMPLEMENTACIO
--------------------------------------------------------------------------------                    
                    for (int i = 0; i < estudiActual.getPosicioComponents(); i++) {
                        if (estudiActual.getComponents()[i] instanceof Jardiner) {
                            try{
                                estudiActual.getComponents()[i].showComponent();
                            }catch(GestorEstudisException e){ System.out.println(e.getMessage());}
                        }
                    }
                    */
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    /*
     TODO Heu de desenvolupar el menuProjectes amb les opcions que podeu veure.
     Nota: penseu que quan arribem aquí, l'atribut estudiActual no és null
     
     Opció 0. Sortir -->         Surt del menú i retorna al menú principal
     Opció 1. Alta -->           Crea un projecte en l'estudi actual. Penseu que Estudi sap crear projectes      
     Opció 2. Modificar ->     Permet modificar un projecte que existeix a l'estudi actual
     (per comprovar l'existència del projecte tenim un mètode en la classe Estudi que ens ajuda)
     Opció 3. Assignar dissenyador o dissenyadora ->   Assigna un dissenyador o dissenyadora a un projecte, però penseu que Estudi sap com fer-ho.
     Opció 4. Assignar jardiner o jardinera ->   Assigna un jardiner o jardinera a un projecte, però penseu que Estudi sap com fer-ho.
     Opció 5. Llista de projectes-> Imprimeix les dades dels projectes de l'estudi actual.
        
     A més, heu de fer una estructura iterativa per tornar a mostrar el menú sempre que no es premi l'opció 0 de sortida
     Recomanacions:
     - estructura de control switch-case per bifurcar les opcions
     - si no s'ha introduït cap opció de les de la llista, s'ha de mostrar el missatge
     "S'ha de seleccionar una opció correcta del menú."
     - definiu una variable opcio de tipus enter
     Nota important: 
     no controlem que l'usuari introdueixi una opció numèrica, ja que això ho farem amb la
     tècnica de les excepcions que veurem més endavant
     */
    public static void menuProjectes() throws GestorEstudisException  {
        int opcio = 0;

        do {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Modificar");
            System.out.println("\n3. Assignar dissenyador o dissenyadora");
            System.out.println("\n4. Assignar jardiner o jardinera");
            System.out.println("\n5. Llista de projectes");
            opcio = DADES.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    estudiActual.addProjecte();
                    break;
                case 2:
                    int indexSel = estudiActual.selectComponent(4, null);
                    if (indexSel >= 0) {
                        estudiActual.getComponents().get(indexSel).updateComponent();
                    } else {
                        System.out.println("\nNo existeix aquest projecte");
                    }
                    break;
                case 3:
                    estudiActual.addTreballadorProjecte(1);                    
                    break;
                case 4:
                    estudiActual.addTreballadorProjecte(2);
                    break;
                case 5:
                    Iterator<Component> iteradorComponents = estudiActual.getComponents().iterator();
                    while (iteradorComponents.hasNext()){
                            if (iteradorComponents.next() instanceof Projecte){
                                try{
                                    for (int i=0;i<(estudiActual.getComponents().size());i++){
                                        (estudiActual.getComponents().get(i)).showComponent();
                                    }
                                }catch(GestorEstudisException e){System.out.println(e.getMessage());}
                                
                            }
                    }        
                    /* aquest codi desapareix amb la nova implementacio
--------------------------------------------------------------------------------                    
                    
                    for (int i = 0; i < estudiActual.getPosicioComponents(); i++) {
                        if (estudiActual.getComponents()[i] instanceof Projecte) {
                            estudiActual.getComponents()[i].showComponent();
                        }
                    }
                    */    
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static Integer selectEstudi() {

        System.out.println("\nCodi de l'estudi?:");
        int codi = DADES.nextInt();

        for (int i = 0; i < posicioEstudis; i++) {
            if (estudis[i].getCodi() == codi) {
                return i;
            }
        }
        return -1;
    }

}
