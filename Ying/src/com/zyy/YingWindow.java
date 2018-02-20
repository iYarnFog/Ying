package com.zyy;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class YingWindow extends JFrame {
	
	private JFrame YWindow = new JFrame("Ying");
	
	public void Ying() {
		YWindow.setSize(400, 300);
		YWindow.setLocation(200, 200);
		YWindow.setLayout(null);
		
		JLabel YLabel1 = new JLabel("Ying_Lable1");
		YLabel1.setForeground(Color.cyan);
		YLabel1.setBounds(50, 50, 280, 30);
		
		YWindow.add(YLabel1);
		YWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		YWindow.setVisible(true);
	}

}
