package com.zyy.YingCommand;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义子命令信息
 * @author Ying
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface YingCommandBase {
    String YingName();
    String YingCommand();
    String YingDescription();
}
