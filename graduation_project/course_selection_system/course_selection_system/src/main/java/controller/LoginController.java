package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Admin;
import model.Student;
import model.Teacher;
import model.User;
import service.LoginService;
import util.Tool;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	
	@RequestMapping("/checkStudent")
	public @ResponseBody String checkUser(HttpServletRequest request) throws ServletException, IOException {
			request.getSession().invalidate();
			String userId = request.getParameter("account");
			String password = request.getParameter("password");
			Student student = loginService.findUserByUserid(userId);
			if(student == null){
				return "null";
			}else if(!password.equals(student.getPassword())){
				return "error";
			}else {
				request.getSession().setAttribute("student", student);
				return "success";
			}
			
	}
	
	@RequestMapping("/checkTeacher")
	public @ResponseBody String checkTeacher(HttpServletRequest request) throws ServletException, IOException{
		request.getSession().invalidate();
		String tno = request.getParameter("account");
		String password = request.getParameter("password");
		int usertype = Integer.parseInt(request.getParameter("usertype"));
		
		Teacher teacher = loginService.findTeacherByTno(tno);
//		Tool.print(teacher.toString());
		
		if(teacher == null){
			return "null";
		}else if(!password.equals(teacher.getPassword())){
			return "error";
		}else{
//			print(teacher.toString());
			request.getSession().setAttribute("teacher", teacher);
			return "success";
		}
	}
	
	@RequestMapping("/checkAdmin")
	public @ResponseBody String checkAdmin(HttpServletRequest request) throws ServletException,IOException{
		request.getSession().invalidate();
		String aid = request.getParameter("account");
		String password = request.getParameter("password");
		
		Admin admin = loginService.findAdminByAid(aid);
		if(admin == null){
			return "null";
			
		}else if(!password.equals(admin.getPassword())){
			return "error";
		}else {
//			print(admin.toString());
			request.getSession().setAttribute("admin", admin);
			return "success";
		}
	}
	
	
	private void print(Object msg){
		System.out.println("=========================================================");
		System.out.println(msg);
		System.out.println("=========================================================");
	}
	
	
	
	
}
