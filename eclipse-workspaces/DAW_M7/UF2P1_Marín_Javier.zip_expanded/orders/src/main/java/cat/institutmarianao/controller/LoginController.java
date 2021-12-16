package cat.institutmarianao.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// Configure Spring element and add mappings
@Controller
public class LoginController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String root() throws ServletException, IOException {
		return "redirect:/check";
	}

	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public String check(HttpServletRequest request) throws ServletException, IOException {
		if (request.isUserInRole("ROLE_ADMIN")) {
			return "redirect:/admin/orders/";
		} else {
			return "redirect:/users/orders/";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/loginFailed", method = RequestMethod.GET)
	public String loginFailed(Model model) {
		model.addAttribute("error", "true");
		return "redirect:/";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) throws ServletException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		request.logout();
		return "redirect:/";
	}
}
