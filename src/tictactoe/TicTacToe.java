package tictactoe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe extends JPanel {
    private static final long serialVersionUID = 1L;
    public static final String TITLE = "Tic Tac Toe";
    public static final Color COLOR_BG = Color.WHITE;
    public static final Color COLOR_BG_STATUS = new Color(216, 216, 216);
    public static final Color COLOR_CROSS = new Color(239, 105, 80);
    public static final Color COLOR_NOUGHT = new Color(64, 154, 225);
    public static final Font FONT_STATUS = new Font("OCR A Extended", Font.PLAIN, 14);

    private boolean isGameStarted = false;
    private JPanel ticTacToePanel; // Declare ticTacToePanel

    public TicTacToe() {
        setLayout(null);

        JPanel welcomePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/tictactoe/welcomettt.png"));
                Image backgroundImage = backgroundIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        welcomePanel.setBounds(0, 0, 600, 600);

        JButton ticTacToeButton = new JButton("Tic Tac Toe");
        ticTacToeButton.setFont(new Font("Poppins", Font.PLAIN, 18));
        ticTacToeButton.setContentAreaFilled(false);
        ticTacToeButton.setBorderPainted(false);
        ticTacToeButton.setFocusPainted(false);
        ticTacToeButton.setForeground(Color.WHITE);
        ticTacToeButton.setBounds(90, 204, 200, 50);
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

        JButton othelloButton = new JButton("Othello");
        othelloButton.setFont(new Font("Poppins", Font.PLAIN, 18));
        othelloButton.setContentAreaFilled(false);
        othelloButton.setBorderPainted(false);
        othelloButton.setFocusPainted(false);
        othelloButton.setForeground(Color.WHITE);
        othelloButton.setBounds(88, 270, 200, 50);
        othelloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Othello game starting soon!");
            }
        });

        welcomePanel.setLayout(null);
        welcomePanel.add(ticTacToeButton);
        welcomePanel.add(othelloButton);
        add(welcomePanel);
        SoundEffect.WELCOME.play();
    }

    private void addGameComponents() {
        JPanel gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/tictactoe/multiplayer.png"));
                Image backgroundImage = backgroundIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        gamePanel.setLayout(null);
        gamePanel.setBounds(0, 0, 600, 600);

        JButton pinkButton = new JButton();
        pinkButton.setBounds(300, 50, 250, 250);
        pinkButton.setContentAreaFilled(false);
        pinkButton.setBorderPainted(false);
        pinkButton.setFocusPainted(false);
        pinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundEffect.WELCOME.stop();
                SoundEffect.KAROKE.play();
                remove(gamePanel);
                addTicTacToeGame();
                revalidate();
                repaint();
            }
        });
        gamePanel.add(pinkButton);

        JButton newGameButton = new JButton(" ");
        newGameButton.setFont(new Font("Poppins", Font.PLAIN, 18));
        newGameButton.setBounds(50, 300, 250, 250);
        newGameButton.setContentAreaFilled(false);
        newGameButton.setBorderPainted(false);
        newGameButton.setFocusPainted(false);
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(gamePanel);
                addNewGamePanel();
                revalidate();
                repaint();
            }
        });
        gamePanel.add(newGameButton);

        add(gamePanel);
    }

    private void addTicTacToeGame() {
        ticTacToePanel = new JPanel(); // Initialize ticTacToePanel
        ticTacToePanel.setLayout(null);
        ticTacToePanel.setBounds(0, 0, 600, 600);

        Board board = new Board();
        board.setBounds(0, 0, 600, 600);
        ticTacToePanel.add(board);

        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Poppins", Font.PLAIN, 14));
        resetButton.setForeground(Color.BLACK);
        resetButton.setBackground(Color.WHITE);
        resetButton.setOpaque(true);
        resetButton.setBounds(10, 530, 100, 30);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.initGame();
                board.repaint();
            }
        });

        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Poppins", Font.PLAIN, 14));
        quitButton.setForeground(Color.BLACK);
        quitButton.setBackground(Color.WHITE);
        quitButton.setOpaque(true);
        int buttonWidth = 100;
        int buttonHeight = 30;
        int panelWidth = 600;
        int xPosition = (panelWidth - buttonWidth) / 2;
        int yPosition = 530;
        quitButton.setBounds(xPosition, yPosition, buttonWidth, buttonHeight);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(ticTacToePanel);
                addWelcomePanel();
                revalidate();
                repaint();
            }
        });

        ticTacToePanel.add(resetButton);
        ticTacToePanel.add(quitButton);

        add(ticTacToePanel);
        revalidate();
        repaint();
    }

    private void addNewGamePanel() {
        JPanel newGamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        newGamePanel.setLayout(null);
        newGamePanel.setBounds(0, 0, 600, 600);

        // Add a new Tic Tac Toe board to the new game panel
        NewGameBoard newBoard = new NewGameBoard();
        newBoard.setBounds(0, 0, 600, 600);
        newGamePanel.add(newBoard);

        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Poppins", Font.PLAIN, 14));
        resetButton.setForeground(Color.BLACK);
        resetButton.setBackground(Color.WHITE);
        resetButton.setOpaque(true);
        resetButton.setBounds(10, 530, 100, 30);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newBoard.initGame();
                newBoard.repaint();
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Poppins", Font.PLAIN, 14));
        backButton.setBounds(120, 530, 100, 30);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(newGamePanel);
                addGameComponents();
                revalidate();
                repaint();
            }
        });

        newGamePanel.add(resetButton);
        newGamePanel.add(backButton);

        add(newGamePanel);
        revalidate();
        repaint();
    }
    private void addWelcomePanel() {
        JPanel welcomePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/tictactoe/welcomettt.png"));
                Image backgroundImage = backgroundIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        welcomePanel.setBounds(0, 0, 600, 600);
        welcomePanel.setLayout(null);

        JButton ticTacToeButton = new JButton("Tic Tac Toe");
        ticTacToeButton.setFont(new Font("Poppins", Font.PLAIN, 18));
        ticTacToeButton.setContentAreaFilled(false);
        ticTacToeButton.setBorderPainted(false);
        ticTacToeButton.setFocusPainted(false);
        ticTacToeButton.setForeground(Color.WHITE);
        ticTacToeButton.setBounds(90, 204, 200, 50);
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

        JButton othelloButton = new JButton("Othello");
        othelloButton.setFont(new Font("Poppins", Font.PLAIN, 18));
        othelloButton.setContentAreaFilled(false);
        othelloButton.setBorderPainted(false);
        othelloButton.setFocusPainted(false);
        othelloButton.setForeground(Color.WHITE);
        othelloButton.setBounds(88, 270, 200, 50);
        othelloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Othello game starting soon!");
            }
        });

        welcomePanel.add(ticTacToeButton);
        welcomePanel.add(othelloButton);

        add(welcomePanel);
        revalidate();
        repaint();
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new TicTacToe());
        frame.setSize(600, 625);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}