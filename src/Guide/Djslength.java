package Guide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Djslength extends JFrame {
	public Djslength(){
		build_Djslength();
	}

	public void build_Djslength(){
		SchoolGragh G=new SchoolGragh();

		setTitle("某点到其余点最短路径的查询!!");
		setSize(400,600);
		setLayout(null);

		JLabel start = new JLabel("起始点");
		JComboBox building = new JComboBox();
		JTextArea ta = new JTextArea();
		JButton query = new JButton("查询");
		JButton back = new JButton("返回");

		for (int i = 0; i<G.V; i++){
			building.addItem(G.vex[i].name);
		}

		start.setBounds(50,10,50,30);
		building.setBounds(120,10,200,30);
		ta.setBounds(50,50,270,350);
		query.setBounds(50,420,100,30);
		back.setBounds(220,420,100,30);

		query.addActionListener(e->{
			ta.setText("");
			int a = building.getSelectedIndex();
			String text = G.AllShortestDistance(a);
			ta.setText(text);
		});
		back.addActionListener(e->{
			new Welcome();
			setVisible(false);
		});

		add(start);
		add(building);
		add(ta);
		add(query);
		add(back);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Djslength();
	}
}