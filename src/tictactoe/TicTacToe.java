package tictactoe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class TicTacToe extends JPanel {
    private static final long serialVersionUID = 1L;
    public static final String TITLE = "Tic Tac Toe";
    public static final Color COLOR_BG = Color.WHITE;
    public static final Color COLOR_BG_STATUS = new Color(216, 216, 216);
    public static final Color COLOR_CROSS = new Color(239, 105, 80);
    public static final Color COLOR_NOUGHT = new Color(64, 154, 225);
    public static final Font FONT_STATUS = new Font("OCR A Extended", Font.PLAIN, 14);

    private boolean isGameStarted = false;

    public TicTacToe() {
        setLayout(null); // Use null layout for absolute positioning

        // Welcome Page with Background Image
        JPanel welcomePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/tictactoe/welcomettt.png"));
                Image backgroundImage = backgroundIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        welcomePanel.setBounds(0, 0, 600, 600); // Set the size of the welcome panel

        // "Tic Tac Toe" button
        JButton ticTacToeButton = new JButton("Tic Tac Toe");
        ticTacToeButton.setFont(new Font("Poppins", Font.PLAIN, 18));
        ticTacToeButton.setContentAreaFilled(false);
        ticTacToeButton.setBorderPainted(false);
        ticTacToeButton.setFocusPainted(false);
        ticTacToeButton.setForeground(Color.WHITE);
        ticTacToeButton.setBounds(90, 204, 200, 50); // Set position and size of the button
        ticTacToeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isGameStarted = true;
                remove(welcomePanel);
                addGameComponents();
                revalidate();
                repaint();
            }
        });

        // "Othello" button
        JButton othelloButton = new JButton("Othello");
        othelloButton.setFont(new Font("Poppins", Font.PLAIN, 18));
        othelloButton.setContentAreaFilled(false);
        othelloButton.setBorderPainted(false);
        othelloButton.setFocusPainted(false);
        othelloButton.setForeground(Color.WHITE);
        othelloButton.setBounds(88, 270, 200, 50); // Set position and size of the button
        othelloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Othello game starting soon!");
            }
        });

        welcomePanel.setLayout(null); // Use null layout for the welcome panel
        welcomePanel.add(ticTacToeButton);
        welcomePanel.add(othelloButton);
        add(welcomePanel);
        SoundEffect.WELCOME.play();
    }

    private void addGameComponents() {
        // New panel for the game page with multiplayer background
        JPanel gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/tictactoe/multiplayer.png"));
                Image backgroundImage = backgroundIcon.getImage();

                // Calculate the aspect ratio
                int imgWidth = backgroundImage.getWidth(this);
                int imgHeight = backgroundImage.getHeight(this);
                double imgAspect = (double) imgWidth / imgHeight;

                int panelWidth = getWidth();
                int panelHeight = getHeight();
                double panelAspect = (double) panelWidth / panelHeight;

                int drawWidth, drawHeight;
                if (imgAspect > panelAspect) {
                    // Image is wider than panel
                    drawWidth = panelWidth;
                    drawHeight = (int) (panelWidth / imgAspect);
                } else {
                    // Image is taller than panel
                    drawHeight = panelHeight;
                    drawWidth = (int) (panelHeight * imgAspect);
                }

                // Center the image
                int x = (panelWidth - drawWidth) / 2;
                int y = (panelHeight - drawHeight) / 2;

                g.drawImage(backgroundImage, x, y, drawWidth, drawHeight, this);
            }
        };
        gamePanel.setLayout(null); // Use null layout for absolute positioning
        gamePanel.setBounds(0, 0, 600, 600); // Set the size of the game panel

        // Button with transparent background
        JButton pinkButton = new JButton();
        pinkButton.setBounds(300, 50, 250, 250); // Set position and size of the button
        pinkButton.setContentAreaFilled(false); // Make the button transparent
        pinkButton.setBorderPainted(false); // Remove the border
        pinkButton.setFocusPainted(false);
        pinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Stop the welcome sound
                SoundEffect.WELCOME.stop();
                // Play the karoke sound
                SoundEffect.KAROKE.play();
                // Remove the current game panel
                remove(gamePanel);
                // Add the Tic Tac Toe game components
                addTicTacToeGame();
                revalidate();
                repaint();
            }
        });

        gamePanel.add(pinkButton);
        add(gamePanel);
    }

    private void addTicTacToeGame() {
        // Initialize and add the Tic Tac Toe game components
        Board board = new Board();
        board.setBounds(0, 0, 600, 600);
        add(board);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new TicTacToe());
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}