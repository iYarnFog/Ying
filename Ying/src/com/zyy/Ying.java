package com.zyy;

import com.zyy.YingBrain.YingBrain;

import org.fusesource.jansi.AnsiConsole;

import static org.fusesource.jansi.Ansi.ansi;

public class Ying {

    public static void main(String[] args) {
        AnsiConsole.systemInstall();

        YingBrain.Ying();

        //YingWindow YWindow = new YingWindow();
        //YWindow.Ying();
    }
}
