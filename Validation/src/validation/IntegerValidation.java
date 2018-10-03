package validation;
/**
 *
 * @author Craig
 */
public class IntegerValidation {
    public boolean CheckInt(String input){
        try{
        int Check;
            Check = Integer.parseInt(input);
           }catch (NumberFormatException ex) {
               return false;
           }
        return true;
    }
}
