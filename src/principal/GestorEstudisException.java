package principal;

/**
 *
 * @author cesca
 */
public class GestorEstudisException extends Exception {
    
    private String codiCausa = "desconegut";
    private String missatge;

    public GestorEstudisException(String codiCausa) {
        this.codiCausa = codiCausa;
        switch (codiCausa) {
            case "1":
                missatge = "L'opció introduïda no és numèrica";
                break;
            case "2":
                missatge = "No te cap torn assignat";
                break;
            case "3":
                missatge = "El projecte ja té assignat un dissenyador o dissenyadora";
                break;
            case "4":
                missatge = "Aquest jardiner ja està assignat al projecte";
                break;
            case "5":
                missatge = "No existeix aquest torn";
                break;
            case "6":
                missatge = "No existeix aquest jardiner o jardinera";
                break;
            case "7":
                missatge = "No existeix aquest treballador o treballadora";
                break;
            case "8":
                missatge = "No existeix aquest projecte";
                break;
            case "9":
                missatge = "Ja no hi caben més components";
                break;
            case "GestorXML.model":
                missatge = "No s'ha pogut crear el model XML per desar l'estudi";
                break;
            case "GestorXML.desar":
                missatge = "No s'ha pogut desar l'estudi a causa d'error d'entrada/sortida";
                break;
            case "GestorXML.carrega":
                missatge = "No s'ha pogut carregar l'estudi a causa d'error d'entrada/sortida";
                break;
            default:
                missatge = "Error desconegut";
                break;
        }
    }

    @Override
    public String getMessage() {
        return "Error " + codiCausa + "/n" +  missatge;
    }
}
