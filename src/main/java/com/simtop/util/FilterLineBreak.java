package com.simtop.util;

/**
 * 换行符的处理
 */
public class FilterLineBreak {

    public static String filterLineBreak(String column){
        if(column!=null&&column.indexOf("\n")!=-1){
            if(column.indexOf("\r\n")!=-1){
                column = column.replace("\r\n","<br/>");
            }else{
                column = column.replace("\n","<br/>");
            }
        }
        return column;
    }
}
