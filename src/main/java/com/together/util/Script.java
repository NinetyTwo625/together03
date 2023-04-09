package com.together.util;

public class Script {

    /* 유효성 검사 실패 */
    public static String back(String msg) {
        String alertMsg = msg.substring(msg.lastIndexOf("=") + 1, msg.length() - 1);

        StringBuffer sb = new StringBuffer();
        sb.append("<script>");
        sb.append("alert('" + alertMsg + "');");
        sb.append("history.back();");
        sb.append("</script>");

        return sb.toString();
    }
}