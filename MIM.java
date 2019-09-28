/*
Implement a method that takes in a list of strings. Its behavior should be the following for each string:
        a) Reverse the string if its length is a multiple of 4.
        b) Truncate the string to 5 characters if its length is a multiple of 5.
        c) Convert the string to all uppercase if it contains at least 3 uppercase characters in the first 5 characters.
        d) If the string ends with a hyphen, remove it, and append the next string in the list to the current one.
        e) Print the string out.
Additionally, give a final report of the total characters in the input, total characters in the output, and median length of all strings.  Give this module a name that you think is most descriptive of what it does, while still concise.
You may write this in any of the more common languages--Java, variations of C, Python, Perl, Ruby, etc.  
 */

package mim;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Varun
 */
public class MIM {

    private Integer i;
    private Integer tci;
    private StringBuilder outputHolder;
    
    public MIM(){
        i = 0;
        tci = 0;
        outputHolder = new StringBuilder();
    }
    
    
    public void stringManupulation(List<String> list){
       
       if(list.size() == 0)return; //empty list input
       
       while(i<list.size()){
           String s = list.get(i).trim(); //taking out white spaces
           if(s.length() != 0){ //checking string actually has a letters in it.
              
           tci += s.length();
           if(s.substring(s.length()-1,s.length()).equals("-")){ //checking hyphen before sending it to recursion method
               s = checkHyphen(s,list);
           }else{
               i++; //incrementin i pointer if there is no hyphen found at all
           }
           
           if(s.length()>=5){ //checking length of string after removal of hyphen and appending new string 
               s = checkUpperCase(s); //now we check for uppercase condition
           }
           
           if(s.length() % 4 == 0){
               s = new StringBuilder(s).reverse().toString(); //reversing the string if the length is multiple  of 4
           }
           /*
           Here I have not used if else for checking multiple  by 4 and 5 because there are lengths that are multiple of 4 and 5 
           Example 20 => 4 * 5 and 5 * 4 , 40 => 4 * 10 and 5 * 8 so on...
           */
           if(s.length() % 5 == 0){
               s = s.substring(0,5); //truncating string to first 5 postions if it multiply by 5
           }
           
           System.out.println("After string manupulation result string is => " + s);
           outputHolder.append(s); //keeping in StringBuilder to calculate the length of the output length of all Strings in list.
       }else{
               System.out.println("Empty string detected"); //displayed when there is empty or whitespace
               i++; //increment i if string empty 
           }
            
      }
        System.out.println("Total Char in Input String => " + tci);
        System.out.println("Total Char in Output String => " + outputHolder.length());
        System.out.print("Median of the all String is => ");
        System.out.println(tci / list.size());
    }
    /*
    checkHyphen method checks for hyphen and appends the next input string
    But if that string also contains the hyphen then we have to remove it so 
    we do recursion over the string until we find a word without hyphen as the 
    last char in the word.
    */
    private String checkHyphen(String s, List<String> list) {
        if(s.substring(s.length()-1,s.length()).equals("-")){ //checking for hyphen in last char of word
            s = s.substring(0,s.length()-1); //if present remove it
            i++; //perform pointer inc to point to next string in list
            if(i<list.size() && list.get(i).substring(list.get(i).length()-1,list.get(i).length()).equals("-")){ //checking ArrayIndexOutOfBounds and if the next String in list contains the hyphen at last postion. 
                s += list.get(i); //append the new next string 
                tci += list.get(i).length(); //add the new next string length to the tci 
                s = checkHyphen(s, list); //recursively check the condition for hyphen
            }else{
                return s;//return string if string does not contain hyphen in last postion anymore
            }
        }
        return s;//returns the string once after the check for backtracking in stack 
    }
   /*
    CheckUpperCase method is used to check for the uppercase letters if the 
    string length is 5 or greater. 
    It returns the String converted to uppercase if condition satisfies or
    returns original string.
    */
    private String checkUpperCase(String s) {
        int count = 0;
        for(int j=0; j<5; j++){//looping upto 5 letters
            if(Character.isUpperCase(s.charAt(j))){//checking for uppercase
                count++;
                if(count >=3){ //checking for atleast 3 letters having uppercase
                    return s.toUpperCase(); //return converted string
                }
            }
        }
        return s; //return original string 
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MIM mObj = new MIM();
        mObj.stringManupulation(Arrays.asList("a-","b-","c-","d-","APPLE","MANgo", "", " ", "ABCDEFGH", "abcdefgh", "a", "bc","PEACH-", "GOD"));
    }
}

/**
 * Time complexity of the solution is O(M) * O(N) where M is the length of the list and N is the constant 5 for uppercase letters
 * For recursion comes under M as we are checking and moving towards next word in the list.
 * 
 * Space complexity of the solution is O(N) as we use StringBuilder and the recursion uses the internal memory stack.
 * 
 * NOTE:ALL THE LOGIC IS FOLLOWED AS INSTRUCTED IN THE MAIL. SOME ASSUMPTIONS ARE MADE AS THE REQUIREMENTS FOR THIS PROGRAM RISE A LOT OF QUESTIONS 
 *      REGARDING THE LOGIC IMPLEMENTATION 
 * 
 * THANK YOU - VARUN
 */
