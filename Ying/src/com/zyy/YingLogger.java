package com.zyy;

import org.fusesource.jansi.AnsiConsole;

import java.io.PrintStream;

public class YingLogger
{
    private static  PrintStream Y = System.out;

    private static String YPrefix = YingColor.YGRAY + "[" + YingColor.YAQUA + "颖" + YingColor.YGRAY + "]" + YingColor.YRESET;

    YingLogger() {

    }

    public static void Ying(String YMessage) {
        Y.println(YingColor.YYELLOW + "Ying" + YingColor.YAQUA + " > " + YingColor.YRESET + YMessage + YingColor.YRESET);
    }

    public static void YInfo(String YMessage)
    {
        Y.println (YPrefix + YMessage + YingColor.YRESET);
    }

    public static void YWarn(String YMessage)
    {
        Y.println (YPrefix + YingColor.YYELLOW + YMessage + YingColor.YRESET);
    }

    public static void YError(String YMessage)
    {
        Y.println (YPrefix + YingColor.YRED + YMessage + YingColor.YRESET);
    }

    public static void YDebug(String YMessage)
    {
        Y.println (YPrefix + YingColor.YWHITE + YMessage + YingColor.YRESET);
    }

}
