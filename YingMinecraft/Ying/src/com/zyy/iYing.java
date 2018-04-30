package com.zyy;

import com.zyy.YingCommand.YingCommandBase;
import com.zyy.YingCommand.YingCommandManager;
import org.bukkit.command.CommandExecutor;

import java.util.Date;
import java.util.Random;

import static java.lang.Thread.sleep;

public class iYing {

    public static Date YT = new Date();

    public static void main(String[] Y) throws Exception {
        System.out.println("颖" + YT);

        System.out.println(new Date());
        sleep(6);
        System.out.println(new Date());

        System.out.println("颖" + YT);

        System.out.println(new Random().nextInt());

         YingCommandManager.getClasses("com.zyy.YingCommand").forEach((YY) -> {
             if(YY.isAnnotationPresent(YingCommandBase.class)){
                 System.out.println( YY.getAnnotation(YingCommandBase.class).YingName() );
                 try {
                     CommandExecutor YCommandExecutor = ((CommandExecutor) YY.newInstance());
                     //com.zyy.YingCommand.YingHelpCommand.onCommand();
                     YCommandExecutor.onCommand(null, null, "Ying " + new Date(), null);
                     //YY.getMethod("onCommand").invoke(null, null, "Ying", null);
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }

         });
    }
}
