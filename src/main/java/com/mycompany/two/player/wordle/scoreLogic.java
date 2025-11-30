/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.two.player.wordle;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author Joshua
 */
public class scoreLogic {
    private String path = "C:/Users/Joshua/Documents/NetBeansProjects/TWO-PLAYER-WORDLE/src/main/java/com/mycompany/two/player/wordle/gameScore.txt";
    
    // Variables to hold data
    public int bestStreakScore = 0;   // The highest accumulated score (High Score)
    public int longestStreakCount = 0;// The highest number of games won in a row
    public int currentStreakScore = 0;// The current running score total
    public int currentStreakCount = 0;// The current running game count
               
         
            
    public scoreLogic() {
        loadStats();
    }
    
  
    // Load data from text file
    private void loadStats() {
        try {
            File file = new File(path);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                if (scanner.hasNextLine()) bestStreakScore = Integer.parseInt(scanner.nextLine().trim());
                if (scanner.hasNextLine()) longestStreakCount = Integer.parseInt(scanner.nextLine().trim());
                if (scanner.hasNextLine()) currentStreakScore = Integer.parseInt(scanner.nextLine().trim());
                if (scanner.hasNextLine()) currentStreakCount = Integer.parseInt(scanner.nextLine().trim());
                scanner.close();
            } else {
                saveStats(); // Create file if it doesn't exist
            }
        } catch (Exception e) {
            System.out.println("Error loading stats: " + e.getMessage());
        }
    }

    // Save data to text file
    private void saveStats() {
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(bestStreakScore + "\n");
            writer.write(longestStreakCount + "\n");
            writer.write(currentStreakScore + "\n");
            writer.write(currentStreakCount + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving stats: " + e.getMessage());
        }
    }

    // Logic to update stats after a game
    public void updateGameResult(int roundScore, boolean isWin) {
        if (isWin) {
            // 1. Add round score to the CURRENT accumulation
            currentStreakScore += roundScore;
            currentStreakCount++;

            // 2. Check if this breaks the all-time records
            if (currentStreakScore > bestStreakScore) {
                bestStreakScore = currentStreakScore;
            }
            if (currentStreakCount > longestStreakCount) {
                longestStreakCount = currentStreakCount;
            }
        } else {
            // 3. If lost, the accumulation breaks. Reset currents to 0.
            currentStreakScore = 0;
            currentStreakCount = 0;
        }
        // Save to file immediately
        saveStats();
    }
}