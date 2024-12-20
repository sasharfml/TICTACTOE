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

        JButton othelloButton = new JButton("Connect 4");
        othelloButton.setFont(new Font("Poppins", Font.PLAIN, 18));
        othelloButton.setContentAreaFilled(false);
        othelloButton.setBorderPainted(false);
        othelloButton.setFocusPainted(false);
        othelloButton.setForeground(Color.WHITE);
        othelloButton.setBounds(88, 270, 200, 50);
        othelloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(welcomePanel);
                addConnect4Panel();
                revalidate();
                repaint();
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

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Poppins", Font.PLAIN, 12));
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setForeground(Color.BLACK); // Set text color to black for visibility
        backButton.setBounds(10, 10, 50, 20); // Position in the top-left corner
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(gamePanel);
                addWelcomePanel();
                revalidate();
                repaint();
            }
        });
        gamePanel.add(backButton);

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
                SoundEffect.WELCOME.stop();
                SoundEffect.KAROKE.play();
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
        ticTacToePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        ticTacToePanel.setLayout(null);
        ticTacToePanel.setBounds(0, 0, 600, 600);

        Board board = new Board();
        board.setBounds(0, 0, 600, 600);
        ticTacToePanel.add(board);

        // Tambahkan tombol "Back" dan "Reset" dengan action listener
        board.addControlButtons(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        remove(ticTacToePanel);
                        addGameComponents();
                        revalidate();
                        repaint();
                    }
                },
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        board.initGame();
                        board.repaint();
                    }
                }
        );

        add(ticTacToePanel);
        ticTacToePanel.setVisible(true); // Ensure the panel is visible
        revalidate();
        repaint();
    }

    private void addNewGamePanel() {
        JPanel newGamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/tictactoe/interface.png"));
                Image backgroundImage = backgroundIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Draw image to fill the panel
            }
        };
        newGamePanel.setLayout(null);
        newGamePanel.setBounds(0, 0, 600, 600);

        // Add a new Tic Tac Toe board to the new game panel
        NewGameBoard newBoard = new NewGameBoard();
        int xOffset = (newGamePanel.getWidth() - Connect4Board.CANVAS_WIDTH) / 2;
        int yOffset = (newGamePanel.getHeight() - Connect4Board.CANVAS_HEIGHT) / 2 + 20;
        newBoard.setBounds(xOffset, yOffset, Connect4Board.CANVAS_WIDTH, Connect4Board.CANVAS_HEIGHT);

        newBoard.setBounds(0, 0, 600, 600); // Adjust height to leave space for the button
        newGamePanel.add(newBoard);

        // Back button to return to the game panel
        newBoard.addControlButtons(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        remove(ticTacToePanel);
                        addGameComponents();
                        revalidate();
                        repaint();
                    }
                },
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        newBoard.initGame();
                        newBoard.repaint();
                    }
                }
        );


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

        JButton othelloButton = new JButton("Connect 4");
        othelloButton.setFont(new Font("Poppins", Font.PLAIN, 18));
        othelloButton.setContentAreaFilled(false);
        othelloButton.setBorderPainted(false);
        othelloButton.setFocusPainted(false);
        othelloButton.setForeground(Color.WHITE);
        othelloButton.setBounds(88, 270, 200, 50);
        othelloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(welcomePanel);
                addConnect4Panel();
                revalidate();
                repaint();
            }
        });

        welcomePanel.add(ticTacToeButton);
        welcomePanel.add(othelloButton);

        add(welcomePanel);
        revalidate();
        repaint();
    }

    private void addConnect4Panel() {
        JPanel connect4Panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/tictactoe/connectfourr.png"));
                Image backgroundImage = backgroundIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        connect4Panel.setLayout(null);
        connect4Panel.setBounds(0, 0, 600, 600);

        Connect4Board connect4Board = new Connect4Board();
        int xOffset = (connect4Panel.getWidth() - Connect4Board.CANVAS_WIDTH) / 2;
        int yOffset = (connect4Panel.getHeight() - Connect4Board.CANVAS_HEIGHT) / 2 + 20;
        connect4Board.setBounds(xOffset, yOffset, Connect4Board.CANVAS_WIDTH, Connect4Board.CANVAS_HEIGHT);
        connect4Panel.add(connect4Board);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Poppins", Font.PLAIN, 14));
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setBounds(58, 550, 100, 30);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(connect4Panel);
                addWelcomePanel();
                revalidate();
                repaint();
            }
        });
        connect4Panel.add(backButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Poppins", Font.PLAIN, 14));
        resetButton.setContentAreaFilled(false);
        resetButton.setBorderPainted(false);
        resetButton.setFocusPainted(false);
        resetButton.setBounds(146, 550, 100, 30);
        resetButton.setForeground(Color.WHITE);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect4Board.resetGame();
            }
        });
        connect4Panel.add(resetButton);

        add(connect4Panel);
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