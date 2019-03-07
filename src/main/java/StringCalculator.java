import java.util.ArrayList;
import java.util.List;

public class StringCalculator {


    public int add(String s) throws Exception {
        if(s.length() == 0) {
            return 0;
        }


        String[] tokens;
        String delimiter =  ",|\n|%";
        String expressionBody = s;


        // if the string starts with a /, the delimiter is whatever the symbol is at index 2 of string

        if(s.charAt(0) == '/'){
            String charTwo = String.valueOf(s.charAt(2));
            String newDelimiter;
            if(charTwo.equals("[")){
                newDelimiter = s.substring(3, s.indexOf("]"));
            } else {
                newDelimiter = charTwo;
            }


            expressionBody = s.substring(s.indexOf("\n"));
            expressionBody = expressionBody.replace(newDelimiter, ",");
        }



        tokens = expressionBody.trim().split(delimiter);

        int result = 0;
        ArrayList<String> negatives = new ArrayList<>();

        for(String token : tokens){
            int numToken = Integer.parseInt(token);

            if(numToken < 0 ){
                negatives.add(token);

            }
            if(numToken >= 1000){
                continue;
            }
            result += numToken;
        }

        if (negatives.size() > 0){
            throw new Exception("Negatives Not Allowed: " + String.join(", ", negatives));
        }

        return result;
    }
}
