package com.zyy.YingServer;

import javax.swing.*;

public class YingWindow {
    private JPanel Ying;
    private JLabel YingLabel1;

    public static void main(String[] args) {
        JFrame YFrame = new JFrame("YingWindow");
        YFrame.setContentPane(new YingWindow().Ying);
        YFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        YFrame.pack();
        YFrame.setVisible(true);
    }
}
