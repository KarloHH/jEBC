package de.okrumnow.ebc.proof;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import de.okrumnow.ebc.InPin;
import de.okrumnow.ebc.impl.SingleOutPin;

public class SimpleEchoWindow extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtInput;
    private JLabel lblAnswer;

    private UpcaseBoard board = new UpcaseBoard();
    private SingleOutPin<String> outPin;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SimpleEchoWindow frame = new SimpleEchoWindow();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public SimpleEchoWindow() {
        setup();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JLabel lblTexteFrsEcho = new JLabel("Texte fürs Echo");
        contentPane.add(lblTexteFrsEcho, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 121, 70, 114, 0, 0 };
        gbl_panel.rowHeights = new int[] { 19, 0, 0, 0, 0 };
        gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE };
        panel.setLayout(gbl_panel);

        JLabel lblEingabe = new JLabel("Eingabe");
        GridBagConstraints gbc_lblEingabe = new GridBagConstraints();
        gbc_lblEingabe.anchor = GridBagConstraints.WEST;
        gbc_lblEingabe.insets = new Insets(0, 0, 5, 5);
        gbc_lblEingabe.gridx = 0;
        gbc_lblEingabe.gridy = 1;
        panel.add(lblEingabe, gbc_lblEingabe);

        txtInput = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.insets = new Insets(0, 0, 5, 5);
        gbc_textField.gridwidth = 2;
        gbc_textField.anchor = GridBagConstraints.NORTHWEST;
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 1;
        panel.add(txtInput, gbc_textField);
        txtInput.setColumns(10);

        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                outPin.transmit(txtInput.getText());
            }
        });
        GridBagConstraints gbc_btnOk = new GridBagConstraints();
        gbc_btnOk.insets = new Insets(0, 0, 5, 0);
        gbc_btnOk.gridx = 3;
        gbc_btnOk.gridy = 1;
        panel.add(btnOk, gbc_btnOk);

        JLabel lblErgebnis = new JLabel("Ergebnis");
        GridBagConstraints gbc_lblErgebnis = new GridBagConstraints();
        gbc_lblErgebnis.anchor = GridBagConstraints.WEST;
        gbc_lblErgebnis.insets = new Insets(0, 0, 0, 5);
        gbc_lblErgebnis.gridx = 0;
        gbc_lblErgebnis.gridy = 3;
        panel.add(lblErgebnis, gbc_lblErgebnis);

        lblAnswer = new JLabel("?");
        lblAnswer.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
        GridBagConstraints gbc_lblAnswer = new GridBagConstraints();
        gbc_lblAnswer.gridwidth = 2;
        gbc_lblAnswer.insets = new Insets(0, 0, 0, 5);
        gbc_lblAnswer.gridx = 1;
        gbc_lblAnswer.gridy = 3;
        panel.add(lblAnswer, gbc_lblAnswer);
    }

    private void setup() {
        // connect the board
        InPin<String> inPin = new InPin<String>() {

            @Override
            public void receive(String message) {
                lblAnswer.setText(message);
            }
        };
        outPin = new SingleOutPin<String>();
        outPin.connect(board.Request());
        board.Response().connect(inPin);
    }
}
