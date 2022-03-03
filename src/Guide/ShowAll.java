package Guide;

import javax.swing.*;
import java.awt.*;

public class ShowAll extends JFrame {
    private Image img;
    private JButton back;
    public ShowAll(){
        build_ShowAll();
    }
    public void build_ShowAll(){
        setTitle("学校全景");
        setSize(800,600);
        this.setLocation(50, 50);


        JButton back = new JButton("返回");
        back.addActionListener(e -> {
            new Welcome();
            setVisible(false);
        });
        back.setBounds(100,500,100,30);

        JLabel Img=new JLabel(new ImageIcon("images/School.drawio.png"));
        Img.setBounds(50, 30, 500, 400);

        add(back);
        add(Img);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ShowAll();
    }
}




