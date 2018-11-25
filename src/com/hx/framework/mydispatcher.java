package com.hx.framework;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.jws.soap.InitParam;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




/**
 * Servlet implementation class mydispatcher
 */
@WebServlet(initParams= {@WebInitParam(name="xmlconfig",value="myservlet.xml")},value="/myservlet.xml")



public class mydispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String viewprefix;
	private String  viewSufix;
     
	public void init() throws ServletException {
	      
		   //init 的时候 ，读取myservlet.xml文件的位置
		String config=this.getInitParameter("xmlconfig");
		//System.out.println(config);
		system_servlet ss=new system_servlet();
		ss.reading(config);
		
		
	  viewprefix=this.getInitParameter("viewprefix");
	  viewSufix=this.getInitParameter("viewsufix");
		
		   
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
		   String config=getInitParameter("xmlconfig");
		   system_servlet ss=new system_servlet();
		   ss.reading(config);
		   System.out.println(system_servlet.map.keySet());
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mydispatcher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	    //充当总控制器的servlet,其目的是从URL中解析出用户要请求的controller以及action
		//下面在做url解析
		String uri=request.getRequestURI();
		String query_string=request.getQueryString();
		
		String action=uri.substring(uri.lastIndexOf("/")+1);
		String _controller=uri.substring(0,uri.lastIndexOf("/"));
		String controller=_controller.substring(_controller.lastIndexOf("/")+1);
		
		String _contextpath=request.getContextPath().substring(1,	request.getContextPath().length());
		System.out.println(	request.getContextPath().substring(1,	request.getContextPath().length()));
		controller=controller.equalsIgnoreCase(_contextpath)?"index":controller;
		
		
			//deptController c=new deptController();
			Class cl;
			try {
				cl = Class.forName(system_servlet.map.get(controller).getClazz());
				Object obj = cl.newInstance();
				Method m=cl.getMethod(action,HttpServletRequest.class,HttpServletResponse.class);
				Object o = m.invoke(obj,request,response);
			   String 	result=(String)o;
				//result中包含着我们要跳转去的地方
				if (result.equals("")) {
					//按命名约定转发去默认视图
					request.getRequestDispatcher( viewprefix+controller+"/"+action+".jsp").forward(request, response);
					
					
				}else if(result.equals("ajax:")){
	                 //什么地方也不跳,来自异步的ajax请求				
					
					
					
				}else if(result.startsWith("same:")) {
					action=result.substring(result.indexOf(":")+1);
					
					request.getRequestDispatcher("/"+controller+"/"+action).forward(request, response);
					
					
				}else if(result.startsWith("other:")) {
					//把另一控制器名称方法名称解析出来
					String _url=result.substring(result.indexOf(":")+1);
					String[] _u=_url.split("/");
					request.getRequestDispatcher("/"+_u[0]+"/"+_u[1]).forward(request, response);
					
					
				} else if(result.startsWith("jsp:")) {
					
					String  _url=result.substring(result.indexOf("jsp_dispather:")+1);
					request.getRequestDispatcher(viewprefix+_url).forward(request, response);
					
					
				}
				
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void  readServlet() {
		//根据myservlet.xml 中的设置，将其中的servlet 全部读出到一个类中
	}

}
