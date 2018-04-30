package com.zyy.YingBrain;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import bitoflife.chatterbean.AliceBot;

import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color;


public class YingBrain {
	private static AliceBotMother xmother = new AliceBotMother();
	private static AliceBot xbot;

	public static void main(String[] args) {
		YingWindow YWindow = new YingWindow();
		YWindow.Ying();
		try {
			xbot = xmother.newInstance();
			xbot.respond("welcome");
			xmother.setUp();
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			while(true) {
				String RobotSay;
				//Scanner in = new Scanner(System.in);
				//in.next();
				String personSay = reader.readLine().trim();
				RobotSay = xbot.respond(personSay);
				
				AnsiConsole.systemInstall();
				System.out.println(ansi().eraseLine().fg(Color.CYAN).a("颖").reset());
				System.out.println("AQUA \033[0;36;1m 颖 \033[0m");
				// 0;32;1m
				
				System.out.println(RobotSay);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
