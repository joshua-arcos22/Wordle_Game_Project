/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.two.player.wordle;
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
/**
 *
 * @author Joshua
 */
public class MainGame {
    public static void main(String[] args) throws FileNotFoundException{
        File fopen = new File("C:/Users/Joshua/Documents/NetBeansProjects/TWO-PLAYER-WORDLE/src/main/java/com/mycompany/two/player/wordle/words.txt");
        String user_Input_Word  = "";
        Scanner userInput = new Scanner(System.in);
        Scanner cpuScanner = new Scanner(fopen);
        String cpuWord = "";
        int u_Attempts = 4;
        int pos_Correct_Counter = 0;
        int pos_Present_Counter = 0;
        int pos_NotPresent_Counter = 0;
        int []cpuWord_letterflag = {0,0,0,0};
        int []userInput_letterflag = {0,0,0,0};
        
        //random number generator for the word 
        Random word_Generator = new Random();
        int max = 100;
        int word_Generator_Index = word_Generator.nextInt(max);
        
        while(cpuScanner.hasNextLine()){
            for (int i = 0; i < word_Generator_Index-1; i++) {
                cpuScanner.nextLine();
            }
            cpuWord = cpuScanner.nextLine();
            break;
        }
        
        
        System.out.println("(d) The word is: " + cpuWord + " Enter a 4 Letter word and guess the word: ");
        user_Input_Word = userInput.nextLine();
        
            if(user_Input_Word.length() == 4){
                
                
                for (int i = 0; i < 4; i++) {
                    if(user_Input_Word.charAt(i) == cpuWord.charAt(i)){
                        pos_Correct_Counter++;
                        cpuWord_letterflag[i] = 1;
                        userInput_letterflag[i] = 1;
                    }
                }
                
                for (int i = 0; i < 4; i++) {
                    if(cpuWord_letterflag[i] == 1){
                        continue;
                    }
                    
                    for (int j = 0; j < 4; j++) {
                        if(user_Input_Word.charAt(i) == cpuWord.charAt(j)){
                            pos_Present_Counter++;
                            cpuWord_letterflag[i] = 1;
                            break;
                        }
                    }
                    
                }
                pos_NotPresent_Counter = 4 - pos_Correct_Counter - pos_Present_Counter;
               
                if(pos_Correct_Counter == 4){
                    System.out.println("You got it all correct");
                } else {
                    System.out.println("Number of Correct Letters: " + pos_Correct_Counter);
                    System.out.println("Number of Present Correct Letters: " + pos_Present_Counter);
                    System.out.println("Number of Inccorect Letters: " + pos_NotPresent_Counter);
                    System.out.println("Please Try again");
                    u_Attempts--;
                }



            } else {
                System.out.println("Not a 4 Letter word, Please input a 4 letter word");
            }
        }
    }
    
    
    

