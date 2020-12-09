package tsi.too.lucaschfonseca.shell.ui;

import tsi.too.lucaschfonseca.shell.CommandInterpreter;
import tsi.too.lucaschfonseca.shell.model.Command;
import tsi.too.lucaschfonseca.shell.model.CommandFactory;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

    private static int shellNumber = 0;

    private JTextArea outputTextArea;
    private JTextArea inputTextArea;

    private final CommandInterpreter interpreter = CommandInterpreter.getInstance();

    public MainWindow(String c) {
        initComponent();
        setUpWindow();

        if (c == null || c.isBlank()) {
            setTitle("Shell " + ++shellNumber);
            var s = getDefaultDirectory() + ">";
            inputTextArea.append(s);
            inputTextArea.setCaretPosition(s.length());
        } else
            setTitle(c);

        inputTextArea.requestFocus();
    }

    private void setUpWindow() {
        setMinimumSize(new Dimension(800, 420));
        pack();
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                shellNumber--;
            }
        });
    }

    private String getDefaultDirectory() {
        return System.getProperty("user.home");
    }

    private void setUpOutputTextArea() {
        outputTextArea = new JTextArea();
        outputTextArea.setLineWrap(true);
        outputTextArea.setEditable(false);
        outputTextArea.setFocusable(false);
        outputTextArea.setForeground(Color.WHITE);
        outputTextArea.setBackground(Color.BLACK);

        outputTextArea.setBorder(BorderFactory.createEmptyBorder());
    }

    private void setUpInputTextArea() {
        inputTextArea = new JTextArea();
        inputTextArea.setLineWrap(true);
        inputTextArea.setBackground(Color.BLACK);
        inputTextArea.setForeground(Color.WHITE);
        inputTextArea.setCaretColor(Color.WHITE);

        inputTextArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    executeCommand();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isShiftDown())
                    keyReleased(e);
            }
        });
    }

    private void initComponent() {
        setUpOutputTextArea();
        setUpInputTextArea();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(6)
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
                                .addGap(6))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(6)
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                                .addGap(6))
        );

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        scrollPane.setViewportView(panel);


        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addComponent(inputTextArea, GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
                                                .addGap(1))
                                        .addComponent(outputTextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(1)
                                .addComponent(outputTextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(1)
                                .addComponent(inputTextArea, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                                .addContainerGap())
        );
        panel.setLayout(gl_panel);
        getContentPane().setLayout(groupLayout);
    }

    private void executeCommand() {
        var enteredCommand = inputTextArea.getText();
        appendCommandOnHistory(enteredCommand);

        var split = enteredCommand.split(">");
        inputTextArea.setText(split[0] + ">");

        if (split[1].trim().isBlank())
            return;

        try {
            var command = recoverCommandFromUserInput(split[1].replaceAll("\n", ""));
        } catch (IllegalArgumentException e) {
            appendCommandOnHistory(e.getMessage());
        }
    }

    private void appendCommandOnHistory(String message) {
        if (outputTextArea.getText().isEmpty())
            outputTextArea.append(message.replace("\n", ""));
        else
            outputTextArea.append("\n" + message.replace("\n", ""));
    }

    private Command recoverCommandFromUserInput(String userInput) throws IllegalArgumentException {
        String commandName;
        String args = "";

        if (userInput == null || !userInput.contains(" ")) {
            commandName = userInput;
        } else {
            commandName = userInput.substring(0, userInput.indexOf(" ")).trim();
            args = userInput.substring(commandName.length()).trim();
        }

        System.out.println(args);
        return CommandFactory.create(commandName.replace("\n", "").trim());
    }

    public static void open(String directory) {
        new MainWindow(directory).setVisible(true);
    }

    private void close() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
