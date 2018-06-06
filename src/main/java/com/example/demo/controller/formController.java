package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.example.demo.entity.User;

@Controller
public class formController {

	/**
	 * 接收前台数据（语言种类），设置浏览器语言
	 * 
	 * @param request
	 * @param lang
	 * @return
	 */
	@RequestMapping(value = "/change")
	public String changeSessionLocalLanguage(HttpServletRequest request, String lang) {
		System.out.println("前台选择：" + lang + "\n------------------\n");
		if ("中文".equals(lang)) {
			request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
					new Locale("zh", "CN"));

		} else if ("英文".equals(lang)) {
			request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
					new Locale("en", "US"));
		}

		HttpSession session = request.getSession();// 获取session
		// 遍历enumeration中的键值对
		String[] names = session.getValueNames();
		System.out.println("Session中的元素：");
		for (int i = 0; i < names.length; i++) {
			System.out.println(session.getValue(names[i]));
		}
		System.out.println("-----------------------------\n");
		return "redirect:index";
	}

	/**
	 * 获取session中的数据，将其中的user对象存入List集合，再将集合发送给前台
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String sel(Model model, HttpServletRequest request) {
		List<User> list = new ArrayList<>();

		// 获取session
		HttpSession session = request.getSession();
		// 遍历enumeration中的键值对
		String[] names = session.getValueNames();
		System.out.println("Session中的元素：");
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i] + "：" + session.getValue(names[i]));
			if (session.getValue(names[i]) != null
					&& !SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME.equals(names[i])) {
				list.add((User) session.getValue(names[i]));
			}
		}
		System.out.println("-------------------------\n");

		// 将user对象存入list
		System.out.println("List中的元素：");
		for (User u : list) {
			System.out.println(u);
		}
		System.out.println("-------------------------------\n");

		// 发送数据
		model.addAttribute("list", list);
		return "indexpage";
	}

	@RequestMapping(value = "/add")
	public String add() {
		return "form";
	}

	/**
	 * 接收前台表单数据，存入session
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save")
	public String save(User user, HttpServletRequest request) {
		User u = user;
		u.setId(String.valueOf((int) (Math.random() * 9000) + 1000));
		System.out.println("请求保存数据：\n" + u + "\n-------------------------------\n");

		String id = u.getId();
		request.getSession().setAttribute(id, u);
		return "redirect:index";
	}

	/**
	 * 获取要修改的用户信息，发送给前台
	 * 
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updata")
	public String update(String id, Model model, HttpServletRequest request) {
		User user = null;
		// 获取session
		HttpSession session = request.getSession();
		// 遍历enumeration中的键值对
		String[] names = session.getValueNames();
		System.out.println("Session中的元素:");
		for (int i = 0; i < names.length; i++) {
			if (names[i].equals(id)) {
				user = (User) session.getValue(names[i]);
			}
			System.out.println(names[i] + "：" + session.getValue(names[i]));
		}
		System.out.println("------------------\n");
		model.addAttribute("user", user);
		return "updata";
	}

	/**
	 * 接收修改后的用户信息，存入session
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save1")
	public String save1(User user, HttpServletRequest request) {
		User u = user;
		System.out.println("修改信息：\n" + u + "\n-------------------------------\n");

		String id = u.getId();
		request.getSession().setAttribute(id, u);
		return "redirect:index";
	}

}
