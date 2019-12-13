package bsu.rfe.java.group7.lab2.Fyodorov.varA;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
@SuppressWarnings("serial") // чтобы не выводила еррор

public class Formula extends JFrame {

    private static final int WIDTH = 450;
    private static final int HEIGHT = 420;

    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;

    private ButtonGroup radioButtons = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox(); //создаем бокс для компановки элементов
    private int formulaId = 1;
    private double sum = 0, result;
    private boolean flag = false, flag2 = false;

    public Double calculate1(Double x, Double y, Double z) {
        if (z <= 0){
            JOptionPane.showMessageDialog(Formula.this,
                    "Невозможно вычислить натуральный логарифм", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
            return 0.0;
        }
        if (z > 0 && z < 1){
            JOptionPane.showMessageDialog(Formula.this,
                    "Отрицательное цисло под корнем чётной степени", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
            return 0.0;
        }
        return Math.pow(Math.log(z) + Math.sin(Math.PI*z*z),0.25)/Math.pow(y*y + Math.pow(Math.E,Math.cos(x)) + Math.sin(y),Math.sin(x));
    }
    public Double calculate2(Double x, Double y, Double z) {

        if (z < 0 || x < 0){
            JOptionPane.showMessageDialog(Formula.this,
                    "Отрицательное цисло под корнем чётной степени", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
            return 0.0;
        }
        if (y == 0 || x == -1){
            JOptionPane.showMessageDialog(Formula.this,
                    "Деление на 0!", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
            return 0.0;
        }

        return (1 + Math.sqrt(z*x))/Math.pow(1 + x*x*x,y);
    }


    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Formula.this.formulaId = formulaId;
            } });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }

    public Formula() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);
        hboxFormulaType.add(Box.createHorizontalGlue()); addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2); radioButtons.setSelected(
                radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue()); hboxFormulaType.setBorder(
                BorderFactory.createLineBorder(Color.BLACK));

        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());

        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());

        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX); hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX); hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForY); hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY); hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForZ); hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ); hboxVariables.add(Box.createHorizontalGlue());

        JLabel labelForResult = new JLabel("Результат:");

        textFieldResult = new JTextField("0", 10);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());

        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Создать область для кнопок
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) { try {
                Double x = Double.parseDouble(textFieldX.getText());
                Double y = Double.parseDouble(textFieldY.getText());
                Double z = Double.parseDouble(textFieldZ.getText());
                if (formulaId==1)
                    result = calculate1(x, y,z);
                else
                    result = calculate2(x, y,z);
                flag2 = true;
                textFieldResult.setText(toString().format("%-10.3f%n", (result)));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(Formula.this,
                        "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
            } }
        });
        JButton MC = new JButton("MC");
        MC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                sum = 0;
                textFieldResult.setText("0");
            } });
        JButton M = new JButton("M+");
        M.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) { try {
                Double x = Double.parseDouble(textFieldX.getText());
                Double y = Double.parseDouble(textFieldY.getText());
                Double z = Double.parseDouble(textFieldZ.getText());
                if (flag && flag2) { sum += result; }
                else { sum = 0; result = 0;}
                textFieldResult.setText(toString().format("%-10.3f%n", (result + sum)));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(Formula.this,
                        "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
            } }
        });

        MC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                flag = false;
                flag2 = false;
            }
        });

        M.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                flag = true;
            }
        });


        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldZ.setText("0");
                textFieldY.setText("0");
                textFieldResult.setText("0");
            } });

        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(20));
        hboxButtons.add(MC);
        hboxButtons.add(Box.createHorizontalStrut(5));
        hboxButtons.add(M);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }
}