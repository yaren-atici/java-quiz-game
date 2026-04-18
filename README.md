# Java Quiz Game

A desktop Java quiz game with a Swing GUI that loads questions from a file and tracks player scores.

## Features

- Player enters their name before starting
- Questions loaded from `questions.txt` file
- Correct / Wrong feedback after each answer
- Final score displayed at the end
- Score saved to `scores.txt`
- New Game button to restart

## Tech Stack

- Java
- Swing — GUI components
- File I/O — reading questions and writing scores

## How to run

1. Clone the repository
```bash
   git clone https://github.com/yaren-atici/java-quiz-game.git
```
2. Open in IntelliJ IDEA or any Java IDE
3. Run `Main.java`

## Project Structure

| File | Description |
|------|-------------|
| `Main.java` | Entry point |
| `QuizGame.java` | Game logic, loads questions, tracks score |
| `QuizGUI.java` | Swing GUI and user interaction |
| `questions.txt` | Questions and answers (format: `question;answer`) |
| `scores.txt` | Stores player name and final score |
