import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class GUI extends JFrame {
    static String code; //Monoalphabetic encoded text.
    static String[] chart; //contains the charts
    Converter converter;
    JPanel mainPanel = new JPanel();
    JLabel codeLabel = new JLabel();
    JPanel frequencyChart = new JPanel();
    JLabel chartLabel = new JLabel();
    JPanel alphabetsPanel = new JPanel();
    JLabel modifiedCodeLabel = new JLabel();
    JPanel modifiedCodePanel = new JPanel();
    JLabel[] frequencyJLabels;
    ArrayList<String> letterChangeList = new ArrayList<String>();
    JTextField[] alphabetsField = new JTextField[27];
    JButton changeButton = new JButton("Encode");
    JButton clearButton = new JButton("Clear");
    public void createGUI(String title, int width, int height) {
        assignConverterValues();
        modifiedCodeLabel.setText(code);
        frequencyJLabels = new JLabel[chart.length];


        setSize(width, height);
        setBackground(Color.WHITE);
        setLayout(new BorderLayout(20, 15));
        setExtendedState(JFrame.MAXIMIZED_BOTH);


        mainPanel.setBackground(Color.GRAY);
        frequencyChart.setBackground(Color.LIGHT_GRAY);

        add(mainPanel, BorderLayout.NORTH);
        add(frequencyChart, BorderLayout.WEST);
        frequencyChart.setLayout(new BoxLayout(frequencyChart, BoxLayout.Y_AXIS));
        add(alphabetsPanel, BorderLayout.CENTER);
        add(modifiedCodePanel, BorderLayout.SOUTH);

        
        mainPanel.add(codeLabel);
        codeLabel.setText(code);
        codeLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
        modifiedCodePanel.add(modifiedCodeLabel);
        modifiedCodeLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
        assignAlphabetLabels(alphabetsPanel);
        assignChart(frequencyChart);
        alphabetsPanel.add(changeButton);
        alphabetsPanel.add(clearButton);
        setButtons();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setButtons(){
        changeButton.addActionListener((ActionEvent e) -> {
            getChangeLetters();
            String newCode = converter.decodeText(letterChangeList);
            modifiedCodeLabel.setText(newCode);
            letterChangeList.clear();
            
            // for (JTextField jTextField : alphabetsField) {
            //     if(jTextField != null){
            //         jTextField.setText("");
            //     }
            // }
        });
        clearButton.addActionListener((ActionEvent e) -> {
            for (JTextField jTextField : alphabetsField) {
                if(jTextField != null){
                    jTextField.setText("");
                }
            }
        });
    }

    public void getChangeLetters(){
        char[] alphabets = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' '};
        for(int i=0; i<alphabetsField.length; i++){
            if(alphabetsField[i] != null && !alphabetsField[i].getText().isEmpty()){
                System.out.println(alphabets[i]+"-"+alphabetsField[i].getText());
                letterChangeList.add(alphabets[i]+"-"+alphabetsField[i].getText());
            }
        }
        System.out.println(Arrays.toString(letterChangeList.toArray()));
    }

    public void assignConverterValues(){
        code = "MLD BLF PMLD GSV TZNV SLD GL KOZB RG. TVG IVZWB ULJ HRNROZI KILYOVNH RM BLFI VCZNH. XSVVIH";
        String codedString = code.toLowerCase();
        char[] codedArray = codedString.toCharArray();
        converter = new Converter(codedString, codedArray);
        chart = converter.frequencyCount(codedArray);
    }

    public void assignAlphabetLabels(JPanel alphabetsPanel) {
        char[] alphabets = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' '};
        JLabel[] alphabetsLabel = new JLabel[27];
        for(int i=0;i<26;i++){
            alphabetsLabel[i] = new JLabel(alphabets[i]+"");
            alphabetsField[i] = new JTextField("",2);
            alphabetsLabel[i].setFont(new Font("Times New Roman", Font.BOLD, 30));
        }
        for(int i=0;i<26;i++){
            alphabetsPanel.add(alphabetsLabel[i]);
            alphabetsPanel.add(alphabetsField[i]);
        }
    }

    
    public void assignChart(JPanel freqPanel){
        for(int i=0;i<chart.length;i++){
            frequencyJLabels[i] = new JLabel();
            frequencyJLabels[i].setFont(new Font("Times New Roman", Font.BOLD, 20));
            freqPanel.add(frequencyJLabels[i]);
        }
        for(int i=0;i<chart.length;i++){
            frequencyJLabels[i].setText(chart[i]);
        }
    }
}
