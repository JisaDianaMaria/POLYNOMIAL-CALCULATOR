package org.example.GraphicalUserInterface;
import org.example.BussinesLogic.Operations;
import org.example.DataModels.Polynomial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {

    private JTextField polynomialField1, polynomialField2, resultField;
    private JButton addButton, subButton, mulButton, divButton, derivButton, integButton, clearButton;

    public GUI() {
        super("Polynomial Calculator");

        // Set layout
        setLayout(new BorderLayout());

        // Create polynomial input fields
        JPanel polynomialPanel = new JPanel();
        polynomialPanel.setLayout(new BoxLayout(polynomialPanel, BoxLayout.Y_AXIS));
        Color c1 = new Color(152,152,156);
        polynomialPanel.setBackground(c1);
        polynomialField1 = new JTextField(20);
        polynomialField1.setFont(new Font(polynomialField1.getFont().getName(), Font.PLAIN, 17));
        polynomialField2 = new JTextField(20);
        polynomialField2.setFont(new Font(polynomialField2.getFont().getName(), Font.PLAIN, 17));
        resultField = new JTextField(20);
        resultField.setEditable(false);
        resultField.setFont(new Font(resultField.getFont().getName(), Font.PLAIN, 17));

        JLabel label1 = new JLabel("Polynomial 1:");
        label1.setFont(new Font(label1.getFont().getName(), Font.PLAIN, 20));
        polynomialPanel.add(label1);
        polynomialPanel.add(polynomialField1);
        polynomialPanel.add(Box.createVerticalStrut(10));
        JLabel label2 = new JLabel("Polynomial 2:");
        label2.setFont(new Font(label2.getFont().getName(), Font.PLAIN, 20));
        polynomialPanel.add(label2);
        polynomialPanel.add(polynomialField2);
        polynomialPanel.add(Box.createVerticalStrut(10));
        JLabel label3 = new JLabel("Result:");
        label3.setFont(new Font(label3.getFont().getName(), Font.PLAIN, 20));
        polynomialPanel.add(label3);
        polynomialPanel.add(resultField);

        // Create operation buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 2, 20, 20));
        buttonPanel.setBackground(c1);
        addButton = new JButton("Addition");
        subButton = new JButton("Subtraction");
        mulButton = new JButton("Multiplication");
        divButton = new JButton("Division");
        derivButton = new JButton("Derivative");
        integButton = new JButton("Integration");

        Color c2 = new Color(220, 220, 220);
        addButton.setBackground(c2);
        subButton.setBackground(c2);
        mulButton.setBackground(c2);
        divButton.setBackground(c2);
        derivButton.setBackground(c2);
        integButton.setBackground(c2);

        // Set custom font for operation buttons
        Font buttonFont = new Font("Arial", Font.PLAIN, 20);
        addButton.setFont(buttonFont);
        subButton.setFont(buttonFont);
        mulButton.setFont(buttonFont);
        divButton.setFont(buttonFont);
        derivButton.setFont(buttonFont);
        integButton.setFont(buttonFont);

        // Add operation buttons to buttonPanel
        buttonPanel.add(addButton);
        buttonPanel.add(subButton);
        buttonPanel.add(mulButton);
        buttonPanel.add(divButton);
        buttonPanel.add(derivButton);
        buttonPanel.add(integButton);

        // Create Clear button and add it to a new panel
        JPanel clearPanel = new JPanel(new GridLayout(1, 1, 10, 20));
        clearButton = new JButton("Clear");
        clearButton.setFont(buttonFont);
        clearButton.setBackground(c2);
        clearPanel.add(clearButton);

        // Add event listeners to buttons
        addButton.addActionListener(this);
        subButton.addActionListener(this);
        mulButton.addActionListener(this);
        divButton.addActionListener(this);
        derivButton.addActionListener(this);
        integButton.addActionListener(this);
        clearButton.addActionListener(this);

        // Add components to frame
        add(polynomialPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(clearPanel);

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            Polynomial p1 = new Polynomial(polynomialField1.getText());
            Polynomial p2 = new Polynomial(polynomialField2.getText());
            Polynomial result = Operations.add(p1, p2);
            resultField.setText(result.toString());
        } else if (e.getSource() == subButton) {
            Polynomial p1 = new Polynomial(polynomialField1.getText());
            Polynomial p2 = new Polynomial(polynomialField2.getText());
            Polynomial result = Operations.sub(p1, p2);
            resultField.setText(result.toString());
        } else if (e.getSource() == mulButton) {
            Polynomial p1 = new Polynomial(polynomialField1.getText());
            Polynomial p2 = new Polynomial(polynomialField2.getText());
            Polynomial result = Operations.mul(p1, p2);
            resultField.setText(result.toString());
        } else if (e.getSource() == divButton) {
            Polynomial p1 = new Polynomial(polynomialField1.getText());
            Polynomial p2 = new Polynomial(polynomialField2.getText());
            Polynomial[] result = Operations.div(p1, p2);
            resultField.setText("Q= " + result[0] + "     R= " + result[1]);
        } else if (e.getSource() == derivButton) {
            Polynomial p1 = new Polynomial(polynomialField1.getText());
            Polynomial result = Operations.derivative(p1);
            resultField.setText(result.toString());
        } else if (e.getSource() == integButton) {
            Polynomial p1 = new Polynomial(polynomialField1.getText());
            Polynomial result = Operations.integrate(p1);
            resultField.setText(result.toString());
        }else if (e.getSource() == clearButton) {
            polynomialField1.setText("");
            polynomialField2.setText("");
            resultField.setText("");
        }
    }

    public static void main(String[] args) {
        new GUI();
    }
}