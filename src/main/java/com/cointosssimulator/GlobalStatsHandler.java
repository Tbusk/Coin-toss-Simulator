package com.cointosssimulator;

import java.io.*;
import java.util.Scanner;

public class GlobalStatsHandler { // A class that handles the global variable reading, writing, and minor updating tasks for the statistics page.

    // Initialization of variables responsible for the reading and writing of the files in the methods below
    protected FileInputStream fileInputStream;
    protected PrintWriter printWriter;
    protected FileOutputStream fileOutputStream;
    protected Scanner scanner;
    protected String lineText = "";

    // Initialization of the variables with static Integer values on the stats page.
    protected static Integer coinsTossed, gamesWon, gamesLost, headsScore, tailsScore;
    protected static Integer updatedCoinsTossed = 0,
            updatedGamesWon = 0,
            updatedGamesLost = 0,
            updatedHeadScore = 0,
            updatedTailsScore = 0;

    public void setGlobalGameStats() { // A one-stop method to update global game stats here and on the statistics controller class.
        getGlobalCoinsTossed();
        getGlobalGamesWon();
        getGlobalGamesLost();
        getGlobalHeadsLandedOn();
        getGlobalTailsLandedOn();

        StatisticsController.coinsTossedGlobalValue = coinsTossed;
        StatisticsController.gamesWonGlobalValue = gamesWon;
        StatisticsController.gamesLostGlobalValue = gamesLost;
        StatisticsController.headsLandedOnGlobalValue = headsScore;
        StatisticsController.tailsLandedOnGlobalValue = tailsScore;
    }

    public void getGlobalCoinsTossed() { // Reads coins tossed from stored global values in GlobalGameStats.txt.
        try {
            String stringToIntFiller = "";

            File file = new File("src/main/resources/com/cointosssimulator/GlobalGameStats.txt");
            if(!file.exists()) { // If file does not exist, it will be created.
                file.createNewFile();
            }

            fileInputStream = new FileInputStream(file);
            scanner = new Scanner(fileInputStream);

            if (scanner.hasNextLine()) {

                // Getting line text
                lineText = scanner.nextLine();

                for (int i = 14; i < lineText.length(); ++i) {
                    stringToIntFiller += (lineText.charAt(i));
                }
            } else { // If the line is blank, the value will be set to 0
                stringToIntFiller += ("0");
            }

            coinsTossed = Integer.parseInt(stringToIntFiller);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getGlobalGamesWon() { // Reads games won from stored global values in GlobalGameStats.txt.
        try {
            String stringToIntFiller = "";
            File file = new File("src/main/resources/com/cointosssimulator/GlobalGameStats.txt");
            if(!file.exists()) { // If file does not exist, it will be created.
                file.createNewFile();
            }

            fileInputStream = new FileInputStream(file);
            scanner = new Scanner(fileInputStream);

            if (scanner.hasNextLine()) {

                // Skipping first line
                scanner.nextLine();

                // Getting line text
                lineText = scanner.nextLine();
                for (int i = 11; i < lineText.length(); ++i) {
                    stringToIntFiller += (lineText.charAt(i));
                }
            } else { // If the line is blank, the value will be set to 0
                stringToIntFiller += ("0");
            }

            gamesWon = Integer.parseInt(stringToIntFiller);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getGlobalGamesLost() { // Reads games lost from stored global values in GlobalGameStats.txt.
        try {
            String stringToIntFiller = "";
            File file = new File("src/main/resources/com/cointosssimulator/GlobalGameStats.txt");
            if(!file.exists()) { // If file does not exist, it will be created.
                file.createNewFile();
            }

            fileInputStream = new FileInputStream(file);
            scanner = new Scanner(fileInputStream);

            if (scanner.hasNextLine()) {

                // Skipping first 2 lines
                scanner.nextLine();
                scanner.nextLine();

                // Getting line text
                lineText = scanner.nextLine();

                for(int i = 12; i < lineText.length(); ++i) {
                    stringToIntFiller += (lineText.charAt(i));
                }
            } else { // If the line is blank, the value will be set to 0
                stringToIntFiller += ("0");
            }


            gamesLost = Integer.parseInt(stringToIntFiller);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getGlobalHeadsLandedOn() { // Reads times heads is landed on from stored global values in GlobalGameStats.txt.
        try {
            String stringToIntFiller = "";
            File file = new File("src/main/resources/com/cointosssimulator/GlobalGameStats.txt");
            if(!file.exists()) { // If file does not exist, it will be created.
                file.createNewFile();
            }

            fileInputStream = new FileInputStream(file);
            scanner = new Scanner(fileInputStream);

            if (scanner.hasNextLine()) {

                // Skipping first 3 lines
                scanner.nextLine();
                scanner.nextLine();
                scanner.nextLine();

                // Getting line text
                lineText = scanner.nextLine();

                for (int i = 17; i < lineText.length(); ++i) {
                    stringToIntFiller += (lineText.charAt(i));
                }
            } else { // If the line is blank, the value will be set to 0
                stringToIntFiller += ("0");
            }

            headsScore = Integer.parseInt(stringToIntFiller);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getGlobalTailsLandedOn() { // Reads times tail is landed on from stored global values in GlobalGameStats.txt.
        try {
            String stringToIntFiller = "";
            File file = new File("src/main/resources/com/cointosssimulator/GlobalGameStats.txt");
            if(!file.exists()) { // If file does not exist, it will be created.
                file.createNewFile();
            }

            fileInputStream = new FileInputStream(file);
            scanner = new Scanner(fileInputStream);

            if (scanner.hasNextLine()) {

                // Skipping first 4 lines
                scanner.nextLine();
                scanner.nextLine();
                scanner.nextLine();
                scanner.nextLine();

                // Getting line text
                lineText = scanner.nextLine();

                for (int i = 17; i < lineText.length(); ++i) {
                    stringToIntFiller += (lineText.charAt(i));
                }
            } else { // If the line is blank, the value will be set to 0
                stringToIntFiller += ("0");
            }

            tailsScore = Integer.parseInt(stringToIntFiller);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setGlobalGameValues() { // Writes up-to-date global game values to file. If the file does not exist, it is created.

        // Updates local values
        getGlobalCoinsTossed();
        getGlobalGamesWon();
        getGlobalGamesLost();
        getGlobalHeadsLandedOn();
        getGlobalTailsLandedOn();

        try { // Remakes GlobalGameStats.txt with updated values as well as updates values.
            File file = new File("src/main/resources/com/cointosssimulator/GlobalGameStats.txt");
            if(!file.exists()) { // If file does not exist, it will be created.
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            printWriter = new PrintWriter(fileOutputStream);

            updatedCoinsTossed += CoinTossController.coinSidesSelected.size() + coinsTossed;
            updatedGamesWon += StatisticsController.gamesWonValue + gamesWon;
            updatedGamesLost += StatisticsController.gamesLostValue + gamesLost;
            updatedHeadScore += StatisticsController.headsLandedOnValue + headsScore;
            updatedTailsScore += StatisticsController.tailsLandedOnValue + tailsScore;

            printWriter.append("Coins-Tossed: " + updatedCoinsTossed  + "\n");
            printWriter.append("Games-Won: " + updatedGamesWon  + "\n");
            printWriter.append("Games-Lost: " + updatedGamesLost  + "\n");
            printWriter.append("Heads-Landed-On: " + updatedHeadScore  + "\n");
            printWriter.append("Tails-Landed-On: " + updatedTailsScore  + "\n");
            printWriter.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
