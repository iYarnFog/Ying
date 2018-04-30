/*
 * @author     ucchy
 * @license    LGPLv3
 * @copyright  Copyright ucchy 2017
 */
package com.zyy.YingNMS.YingTellraw;

/**
 * ホバーアクションタイプ
 * @author ucchy
 */
public enum HoverActionType {

    SHOW_TEXT("show_text"),

    SHOW_ITEM("show_item"),

    SHOW_ACHIEVEMENT("show_achievement"),

    SHOW_ENTITY("show_entity");

    private String text;

    /**
     * コンストラクタ
     * @param text
     */
    private HoverActionType(String text) {
        this.text = text;
    }

    /**
     * 文字列表現を返す
     * @see Enum#toString()
     */
    public String toString() {
        return text;
    }

}
