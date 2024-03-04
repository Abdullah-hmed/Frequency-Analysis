import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class App {
    public static void main(String[] args) throws Exception {
        String code = "MLD BLF PMLD GSV TZNV SLD GL KOZB RG. TVG IVZWB ULJ HRNROZI KILYOVNH RM BLFI VCZNH. XSVVIH";
        String codedString = code.toLowerCase();
        char[] codedArray = codedString.toCharArray();
        Set<String> replacedLetters = new HashSet<String> ();
        for(int j=0;j<codedArray.length;j++){
            System.out.print(codedArray[j]);
        }
        System.out.println();
        frequencyCount(codedArray);
        System.out.println();
        Scanner input = new Scanner(System.in);
        String userInput;
        while(true){
            userInput = input.next();
            if(userInput.contains("quit")){
                System.exit(0);
            }
            else if(userInput.contains("restart")){
                System.out.println("\033[H\033[2J");
                System.out.flush();
                codedArray = codedString.toCharArray();
                for(int j=0;j<codedArray.length;j++){
                    System.out.print(codedArray[j]);
                }
                replacedLetters.clear();
                System.out.println();
                frequencyCount(codedArray);
            }
            else{
                switchWords(userInput,codedArray);
                replacedLetters.add(userInput);
                System.out.println("\nReplaced Letters: "+replacedLetters+"\n");
            }
            
        }
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
        frequencyCount(codedArray);
    }

    public static void frequencyCount(char[] codedArray){
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
    }
}
