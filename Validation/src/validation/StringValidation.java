package validation;
/**
 *
 * @author Craig
 */
public class StringValidation {
    public boolean CaseCheck(String user, String search){
        String Case = user.toUpperCase();
        return Case == null ? search == null : Case.equals(search);
    }
}
