package Guide;
//Create by 2000303224 CJLU
//test
import javax.swing.*;
import java.awt.event.*;

public class Welcome extends JFrame{
    public Welcome(){
        build_Welcome();
    }

    public void build_Welcome(){
        setTitle("欢迎来到中国计量大学！！");
        setSize(400,300);
        setLayout(null);

        JButton query_information = new JButton("查询学校景点信息");
        JButton distribution = new JButton("景点分布");
        JButton shortest_path = new JButton("某一景点到其他景点的最短距离");
        JButton query_shortest_path = new JButton("两景点间最短距离");
        JButton exit = new JButton("退出");

        query_information.setBounds(100,30,200,30);
        distribution.setBounds(100,70,200,30);
        shortest_path.setBounds(100,110,200,30);
        query_shortest_path.setBounds(100,150,200,30);
        exit.setBounds(100,190,200,30);

        query_information.addActionListener(e->{
            new ShowIntroduction();
            setVisible(false);
        });
        distribution.addActionListener(e->{
            new ShowAll();
            setVisible(false);
        });
        shortest_path.addActionListener(e->{
            new Djslength();
            setVisible(false);
        });
        query_shortest_path.addActionListener(e->{
            new ShowLength();
            setVisible(false);
        });
        exit.addActionListener(e->{
            System.exit(0);
        });

        add(query_information);
        add(distribution);
        add(shortest_path);
        add(query_shortest_path);
        add(exit);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Welcome();
    }
}
