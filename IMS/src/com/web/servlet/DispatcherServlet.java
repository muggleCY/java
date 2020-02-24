package com.web.servlet;


import com.web.factory.ObjectFactory;
import com.web.mvc.Action;
import com.web.mvc.MVCConfig;
import com.web.mvc.MVCConfigParser;
import com.web.mvc.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author zyb
 * @TIME 19-12-10
 **/
public class DispatcherServlet extends HttpServlet {
    //每次调用方法都需要新建一个action类对象，不符合java编程思想，使用map实现单例
    //actionMap用于保存每一个action类的唯一对象
    //key    类的包路径
    //value  类的对象
    private Map<String, Object> actionMap = new HashMap<String, Object>();

    private MVCConfig config;

    @Override
    public void init() throws ServletException {
        //加载javaBean设置
        ObjectFactory factory = new ObjectFactory();
        //获得mvc的配置对象
        config = MVCConfigParser.getConfig();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
//		String pathInfo = req.getPathInfo();
//		System.out.println(pathInfo);
        String uri = req.getRequestURI();//含项目名在内的全路径
        String path = req.getContextPath();//项目主路径
//		String packageAndAction = uri.substring(path.length() + 1, uri.length() - 3);
//		String[] strs = packageAndAction.split("/");
//		String packageNameSpace = strs[0];
//		String actionName = strs[1];
        //通过字符串的分割找到namespace和actionName
        String packageNameSpace = uri.substring(path.length() + 1, uri.lastIndexOf("/"));///QQQZone/user/login.do
        String actionName = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf(".do"));
        //获得mvc的配置对象
//		MVCConfig config = MVCConfigParser.getConfig();
        //从配置对象中获得package对象
        com.web.mvc.Package packageObj = config.getPackage(packageNameSpace);
        //从package对象中获得action对象
        Action action = packageObj.getAction(actionName);

        try {
            //通过反射找到action对应的java类
//			Class clazz = Class.forName(action.getClassName());
            Class clazz = ObjectFactory.getBean(action.getId()).getClass();
            //通过反射找到action对应的类中的方法
            Method method = clazz.getMethod(action.getMethod(), HttpServletRequest.class, HttpServletResponse.class);
            //创建java类的对象，用以调用action对应的类中的方法
            Object clazzObj = actionMap.get(action.getId());
            if (clazzObj == null) {
                clazzObj = ObjectFactory.getBean(action.getId());
                actionMap.put(action.getId(), clazzObj);
            }

            Object obj = method.invoke(clazzObj, req, resp);
            //通过方法的返回值，找到结果
            String resultName = obj.toString();
            Result result = action.getResult(resultName);
            //根据结果获得跳转方式（转发/重定向）
            String type = result.getType();
            if (type.equals("forward")) {
                //利用result中配置的路径进行转发
                req.getRequestDispatcher(result.getTargetUrl()).forward(req, resp);
            } else if (type.equals("redirect")) {
                //利用result中配置的路径进行重定向
                resp.sendRedirect(path + result.getTargetUrl());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
