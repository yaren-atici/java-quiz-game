package com.example;

import javax.swing.*;
import java.awt.*;

public class QuizGUI extends JFrame {

    private QuizGame game;
    private JLabel questionLabel, scoreLabel;
    private JTextField answerField;
    private JButton submitButton, newGameButton;

    public QuizGUI() {
        game = new QuizGame();
        setTitle("Java Quiz Game");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        questionLabel = new JLabel("Welcome to Java Quiz!", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);

        JPanel inputPanel = new JPanel();
        answerField = new JTextField(10);
        submitButton = new JButton("Submit");
        newGameButton = new JButton("New Game");

        inputPanel.add(new JLabel("Your Answer:"));
        inputPanel.add(answerField);
        inputPanel.add(submitButton);
        inputPanel.add(newGameButton);

        add(questionLabel, BorderLayout.CENTER);
        add(scoreLabel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(e -> checkAnswer());
        newGameButton.addActionListener(e -> startNewGame());

        startNewGame();

        setVisible(true);
    }

    private void startNewGame() {
        String name = JOptionPane.showInputDialog(this, "Enter your name:");
        if (name == null || name.isEmpty()) name = "Player";
        game = new QuizGame();
        game.setPlayerName(name);
        scoreLabel.setText("Score: 0");
        showNextQuestion();
    }

    private void showNextQuestion() {
        if (game.hasNextQuestion()) {
            questionLabel.setText(game.getNextQuestion());
            answerField.setText("");
        } else {
            endGame();
        }
    }

    private void checkAnswer() {
        String userAnswer = answerField.getText().trim();
        if (userAnswer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an answer!");
            return;
        }

        boolean correct = game.checkAnswer(userAnswer);
        if (correct) {
            JOptionPane.showMessageDialog(this, "✅ Correct!");
        } else {
            JOptionPane.showMessageDialog(this, "❌ Wrong!");
        }

        scoreLabel.setText("Score: " + game.getScore());
        showNextQuestion();
    }

    private void endGame() {
        JOptionPane.showMessageDialog(this, "Game Over! Final Score: " +
                game.getScore() + "/" + game.getTotalQuestions());
        game.saveScore();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizGUI());
    }
}
