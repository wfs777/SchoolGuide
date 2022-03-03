package Guide;

import javax.swing.*;

public class ShowIntroduction extends JFrame {
    SchoolGragh G=new SchoolGragh();

    public ShowIntroduction(){
        buid_ShowShortest();
    }

    public void buid_ShowShortest(){
        setTitle("学校景点简介");
        setSize(600,300);
        setLayout(null);

        JLabel name = new JLabel("景点名称：");
        JLabel number = new JLabel("景点编号：");

        JComboBox<String> nameCo = new JComboBox<String>();
        JComboBox<Integer> numberCo = new JComboBox<Integer>();
        for (int i = 0; i<G.V; i++){
            nameCo.addItem(G.vex[i].name);
            numberCo.addItem(i);
        }
        nameCo.addActionListener(e -> {
            int a = nameCo.getSelectedIndex();
            numberCo.setSelectedIndex(a);
        });
        numberCo.addActionListener(e -> {
            int a = numberCo.getSelectedIndex();
            nameCo.setSelectedIndex(a);
        });


        JButton getIntroduction = new JButton("信息");
        JButton back = new JButton("返回");
        JTextArea text =new JTextArea("");
        text.setLineWrap(true);
        getIntroduction.addActionListener(e -> {
            int a = numberCo.getSelectedIndex();
            text.setText("您选择的景点是："+G.vex[a].name+"\n"+"简介是："+G.vex[a].introduction);
        });
        back.addActionListener(e->{
            new Welcome();
            setVisible(false);
        });

        name.setBounds(100,30,80,30);
        nameCo.setBounds(180,30,120,30);
        number.setBounds(320,30,80,30);
        numberCo.setBounds(400, 30, 80, 30);
        getIntroduction.setBounds(100, 90, 80, 30);
        text.setBounds(200, 90,300,80);
        back.setBounds(300, 200, 100, 30);


        add(name);
        add(number);
        add(nameCo);
        add(numberCo);
        add(getIntroduction);
        add(text);
        add(back);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public static void main(String[] args) {
        new ShowIntroduction();
    }
}
