import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Map;
// import java.util.Scanner;
import java.util.Set;

public class Converter {
    String codedString;
    char[] codedArray;

    public Converter(String codedString, char[] codedArray){
        this.codedArray = codedArray;
        this.codedString = codedString;
        stringConverter(codedString, codedArray);
    }

    public void stringConverter(String codedString, char[] codedArray){
        Set<String> replacedLetters = new HashSet<String> ();
        for(int j=0;j<codedArray.length;j++){
            System.out.print(codedArray[j]);
        }
        System.out.println();
        System.out.println();
        // Scanner input = new Scanner(System.in);
        // String userInput;
        // while(true){
        //     userInput = input.next();
        //     if(userInput.contains("quit")){
        //         System.exit(0);
        //     }
        //     else if(userInput.contains("restart")){
        //         System.out.println("\033[H\033[2J");
        //         System.out.flush();
        //         codedArray = codedString.toCharArray();
        //         for(int j=0;j<codedArray.length;j++){
        //             System.out.print(codedArray[j]);
        //         }
        //         replacedLetters.clear();
        //         System.out.println();
        //         frequencyCount(codedArray);
        //     }
        //     else{
        //         switchWords(userInput,codedArray);
        //         replacedLetters.add(userInput);
        //         System.out.println("\nReplaced Letters: "+replacedLetters+"\n");
        //     }
            
        // }
    }

    public String[] frequencyCount(char[] codedArray){
        int[] count = new int[codedArray.length];
        int[] countAlphabet = new int[27];
        for(int i=0; i<countAlphabet.length;i++){
            countAlphabet[i] = 0;
        }
        // System.out.println(countAlphabet);
        char[] alphabets = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' '};
        for(int i=0;i<codedArray.length;i++){
            for(int j=0;j<codedArray.length;j++){
                if(codedArray[i]==codedArray[j]){
                    count[i]++;
                }
            }
        }
        for(int i=0;i<codedArray.length;i++){
            for(int j=0;j<alphabets.length;j++){
                if(codedArray[i] == alphabets[j]){
                    countAlphabet[j]++;
                }
            }
        }
        for(int i=0;i<alphabets.length;i++){
            System.out.print(alphabets[i]+" ");
        }
        System.out.println();
        for(int i=0;i<countAlphabet.length;i++){
            System.out.print(countAlphabet[i]+" ");
        }

        String[] frequencyOutput = new String[27];
        
        System.out.println();
        System.out.println(Arrays.toString(alphabets));
        System.out.println(Arrays.toString(countAlphabet));

        for(int i=0;i<alphabets.length;i++){
            frequencyOutput[i] = alphabets[i]+":    "+countAlphabet[i]+"   "+"-".repeat(countAlphabet[i]);
            System.out.println(frequencyOutput[i]);
        }
        return frequencyOutput;
    }

    public String decodeText(ArrayList<String> letterChangeList){
        
        //Assigning code to tempArray
        char[] tempArray = codedArray.clone();

        //Converting ArrayList to String Array
        String[] changeList = new String[letterChangeList.size()];
        for (int i = 0; i < letterChangeList.size(); i++){
            changeList[i] = letterChangeList.get(i);
        }
        
        //Iterating over the whole message
        for(int i=0; i<tempArray.length; i++){
            //Operations performed on each element in the message
            for(int j=0; j<changeList.length; j++){

                //word[0] = Word to change
                //word[1] = Word to override with
                String[] word = changeList[j].split("-", 2);
                if(tempArray[i] == word[0].charAt(0)){ //checking if first input matches
                    tempArray[i] = word[1].charAt(0);
                    break;
                }
                if(tempArray[i] == word[1].charAt(0)){ //checking if second input matches
                    tempArray[i] = word[0].charAt(0);
                    break;
                } 
            }
        }
        String changedString = new String(tempArray);
        return changedString;
    }

    public static void switchWords(String userInput, char[] codedArray){
        String[] word = userInput.split("-", 2);
        for(int i=0;i<codedArray.length;i++){
            if(word[0].charAt(0) == '.'){
                break;
            }
            if(word[0].charAt(0) == codedArray[i]){
                codedArray[i] = word[1].charAt(0);
            }
        }
        for(int j=0;j<codedArray.length;j++){
            System.out.print(codedArray[j]);
        }
        System.out.println();
        // frequencyCount(codedArray);
    }
}
