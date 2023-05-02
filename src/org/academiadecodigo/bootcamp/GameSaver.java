package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class GameSaver {

    // PROPERTIES RELATED TO I/O
    private String path = "basededados.txt";
    private InputStream inputStream = getClass().getResourceAsStream("/basededados.txt");

    private InputStreamReader reader = new InputStreamReader(inputStream);
    private BufferedReader bufferedReader = new BufferedReader(reader);

    // PROPERTIES RELATED TO HIGH SCORE STORING
    private Map<String, Integer> highscores;
    private Text fourDisplayNames[] = new Text[4];
    private Text fourDisplayScores[] = new Text[4];
    private static Text currentScore = new Text(1110, 250, Integer.toString(Cannon.getPoints()));

    public GameSaver() throws IOException {
        this.highscores = new HashMap<>();
        getFromSaveFile();
        displayOnScreen();
    }

    private String scoresToString(Map<String, Integer> highscores) {
        StringBuilder stringedScores = new StringBuilder();
        for (Map.Entry<String, Integer> set : highscores.entrySet()) {
            stringedScores.append("").append(set.getKey()).append(":").append(set.getValue()).append("|");
        }
            stringedScores.deleteCharAt(stringedScores.length() - 1);
        return stringedScores.toString();
    }

    private void stringToScores(String stringedScores, Map<String, Integer> highscores) {
        highscores.clear();
        for (int i = 0; i < stringedScores.split("\\|").length; i++) {
            highscores.put(stringedScores.split("\\|")[i].split(":")[0], Integer.parseInt(stringedScores.split("\\|")[i].split(":")[1]));
        }
    }

    public void storeToSaveFile(Map<String, Integer> highscores) {
        try {
            FileWriter writer = new FileWriter(inputStream.toString());
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(scoresToString(highscores));
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void getFromSaveFile() {

        String readText = "";
        try {
            readText = bufferedReader.readLine();
            if (readText == null) {
                stringToScores("NO_NAME:0|NO_NAME:0|NO_NAME:0|NO_NAME:0",highscores);
            } else {
                stringToScores(readText, highscores);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayOnScreen() {

        String defaultNameSlots[] = {"NO_NAME", "NO_NAME", "NO_NAME", "NO_NAME"};
        int defaultScoreSlots[] = {0, 0, 0, 0};
        int yHandler = 152;
        Queue<Map.Entry<String, Integer>> queue = new PriorityQueue<>((a, b) -> {
            return b.getValue() - a.getValue();
        });

        queue.addAll(highscores.entrySet());

        for (int i = 0; i < defaultNameSlots.length; i++) {
            if (queue.peek() != null) {
                defaultNameSlots[i] = queue.peek().getKey();
                defaultScoreSlots[i] = queue.poll().getValue();
            }
        }

        for (int i = 0; i < fourDisplayScores.length; i++) {
            fourDisplayNames[i] = new Text(1110, yHandler, defaultNameSlots[i]);
            fourDisplayNames[i].setColor(Color.WHITE);
            fourDisplayNames[i].draw();
            fourDisplayScores[i] = new Text(1110, yHandler + 28, Integer.toString(defaultScoreSlots[i]));
            fourDisplayScores[i].setColor(Color.WHITE);
            fourDisplayScores[i].draw();
            yHandler += 83;
        }
        storeToSaveFile(highscores);
    }

    public void updateHighscore(Integer currentScore) {
        String playerName = JOptionPane.showInputDialog(null, "Enter your name:");
        addHighscores(playerName, currentScore);
        for (int i = 0; i < fourDisplayScores.length; i++) {
            fourDisplayNames[i].delete();
            fourDisplayScores[i].delete();
        }
        storeToSaveFile(highscores);
        displayOnScreen();
    }

    private void addHighscores(String player, Integer score) {
        this.highscores.put(player, score);
    }

    public static void displayCurrentScore() {
        currentScore.delete();
        currentScore = new Text(1138, 488, Integer.toString(Cannon.getPoints()));
        currentScore.setColor(Color.WHITE);
        currentScore.draw();
    }
}
