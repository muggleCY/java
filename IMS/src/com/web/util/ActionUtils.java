package com.web.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author zyb
 * @TIME 19-12-17
 **/
public class ActionUtils {
    public static PrintWriter getWriter(HttpServletResponse response){
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = null;
        try {
             out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }
    public static  void write(PrintWriter out,Object obj){
        out.write(obj.toString());
        out.flush();
        out.close();
    }
    public static void writeJson(HttpServletResponse response,String str){
        response.setContentType("application/json;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            out.write(str);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
