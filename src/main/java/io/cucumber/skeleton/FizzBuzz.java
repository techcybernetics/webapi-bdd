package io.cucumber.skeleton;

public class FizzBuzz {

    public String game(int int1) {
        String result=null;

         if ((int1 % 5 == 0) && (int1 % 3 == 0) ){
            result="FizzBuzz";
            return result;
        }
         else if (int1 % 3 == 0){
                result="Buzz";
                return result;
            }
            else if (int1 % 5 == 0){
              result="Fizz";
              return result;
            }



        return result;
    }
}
