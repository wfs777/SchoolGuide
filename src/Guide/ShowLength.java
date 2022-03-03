package Guide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ShowLength extends JFrame{
	public ShowLength(){
		build_ShowLength();
	}

	public void build_ShowLength(){
		SchoolGragh G=new SchoolGragh();

		setTitle("查询两点间的距离");
		setLayout(null);
		setSize(400,300);

		JLabel start = new JLabel("出发地");
		JLabel end = new JLabel("目的地");
		JLabel shortest_path_label = new JLabel("最短路径");
		JLabel path_label = new JLabel("路径");
		JTextField shortest_path_tf = new JTextField();
		JTextArea path_ta = new JTextArea();
		JComboBox start_point = new JComboBox();
		JComboBox end_point = new JComboBox();
		JButton query = new JButton("查询");
		JButton back = new JButton("返回");

		for (int i = 0; i<G.V; i++){
			start_point.addItem(G.vex[i].name);
			end_point.addItem(G.vex[i].name);
		}

		start.setBounds(10,10,50,30);
		start_point.setBounds(70,10,120,30);
		end.setBounds(200,10,50,30);
		end_point.setBounds(260,10,120,30);
		shortest_path_label.setBounds(10,50,50,30);
		shortest_path_tf.setBounds(70,50,200,30);
		path_label.setBounds(10,90,50,30);
		path_ta.setBounds(70,90,300,90);
		query.setBounds(150,190,100,30);
		back.setBounds(150,230,100,30);

		shortest_path_tf.setEditable(false);
		path_ta.setEditable(false);
		path_ta.setLineWrap(true);
		query.addActionListener(e->{
			int v0 = start_point.getSelectedIndex();
			int v1 = end_point.getSelectedIndex();
			String text1 = G.ShortestDistance(v0,v1);
			shortest_path_tf.setText(text1);
			String text2 = G.ShowPath(v0,v1);
			path_ta.setText(text2);
		});
		back.addActionListener(e->{
			setVisible(false);
			new Welcome();
		});

		add(start);
		add(end);
		add(start_point);
		add(end_point);
		add(shortest_path_label);
		add(shortest_path_tf);
		add(path_label);
		add(path_ta);
		add(query);
		add(back);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ShowLength();
	}
}