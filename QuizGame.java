package com.example;

import java.io.*;
import java.util.*;

public class QuizGame {

    private List<Question> questions;
    private int currentIndex;
    private int score;
    private String playerName;

    public QuizGame() {
        questions = new ArrayList<>();
        loadQuestions();
        currentIndex = 0;
        score = 0;
    }

    private void loadQuestions() {
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream("questions.txt");
            if (in == null) {
                throw new IOException("questions.txt file not found!");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    questions.add(new Question(parts[0].trim(), parts[1].trim().toLowerCase()));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public boolean hasNextQuestion() {
        return currentIndex < questions.size();
    }

    public String getNextQuestion() {
        if (hasNextQuestion()) {
            return questions.get(currentIndex).getQuestion();
        } else {
            return null;
        }
    }

    public boolean checkAnswer(String answer) {
        Question q = questions.get(currentIndex);
        boolean correct = q.getAnswer().equalsIgnoreCase(answer.trim());
        if (correct) score++;
        currentIndex++;
        return correct;
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public void saveScore() {
        if (playerName == null || playerName.isEmpty()) playerName = "Player";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt", true))) {
            writer.write(playerName + " - " + score + "/" + getTotalQuestions());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Question {
        private String question;
        private String answer;

        public Question(String question, String answer) {
            this.question = question;
            this.answer = answer;
        }

        public String getQuestion() {
            return question;
        }

        public String getAnswer() {
            return answer;
        }
    }
}
