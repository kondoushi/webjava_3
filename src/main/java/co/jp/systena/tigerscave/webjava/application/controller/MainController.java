package co.jp.systena.tigerscave.webjava.application.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.jp.systena.tigerscave.webjava.domain.service.SimulationSystemService;
import co.jp.systena.tigerscave.webjava.employee.AbstractEmployee;

@Controller
public class MainController {
	private String STAFF = "1";
	private String CHIEF = "2";
	private String CEO = "3";

	@Autowired
	SimulationSystemService service;

	  @Autowired
	  HttpSession session;

	@RequestMapping(value = "/main")
	public ModelAndView index(RedirectAttributes redirectAttributes,ModelAndView mav,@ModelAttribute("retry") String retry) {
		if (retry.equals("retry")) {
			service.deleteAll();
		}
		// Viewに渡すデータを設定
		mav.addObject("users", service.findAll());  // ユーザデータをサービスから取得し、設定
		mav.addObject("budget","残予算：" + service.getBudget());
		if (service.getBudget() <= 0) {
	    	ModelAndView modelAndView = new ModelAndView("redirect:/goodbye");
	    	String msg = "総費用："+ service.getResult().getExpense() + "\\n" + "総売上："+ service.getResult().getRevenue();
	    	redirectAttributes.addFlashAttribute("name",msg);
	    	ModelMap modelMap = new ModelMap();
	    	modelMap.addAttribute("service", service);
	    	redirectAttributes.addFlashAttribute("model", modelMap);
	    	return modelAndView;
		}else {
			// Viewのテンプレート名を設定
			mav.setViewName("main");
		}
		return mav;
	}

	@RequestMapping(value = "main", params = "input_type_staff", method = RequestMethod.POST)
	public ModelAndView inputStaff(ModelAndView mav) {
		if (service.getBudget() < 20) {
			service.save("予算オーバ―です！");
			return  new ModelAndView("redirect:/main");
		}
		AbstractEmployee employee = service.run(STAFF);
		mav.addObject("users", service.findAll());  // ユーザデータをサービスから取得し、設定
	    service.save(employee.getPosition() + "の売り上げは、"+employee.getContract().getEarnings() + "万円でした！");
	    mav.setViewName("main");
		return  new ModelAndView("redirect:/main");
	}

	@RequestMapping(value = "main", params = "input_type_chief", method = RequestMethod.POST)
	public ModelAndView inputCheif(ModelAndView mav) {
		if (service.getBudget() < 40) {
			service.save("予算オーバ―です！");
			return  new ModelAndView("redirect:/main");
		}
		AbstractEmployee employee = service.run(CHIEF);
		mav.addObject("users", service.findAll());  // ユーザデータをサービスから取得し、設定
	    service.save(employee.getPosition() + "の売り上げは、"+employee.getContract().getEarnings() + "万円でした！");
	    mav.setViewName("main");
		return  new ModelAndView("redirect:/main");

	}

	@RequestMapping(value = "main", params = "input_type_ceo", method = RequestMethod.POST)
	public ModelAndView inputCEO(ModelAndView mav) {
		if (service.getBudget() < 80) {
			service.save("予算オーバ―です！");
			return  new ModelAndView("redirect:/main");
		}
		AbstractEmployee employee = service.run(CEO);
		mav.addObject("users", service.findAll());  // ユーザデータをサービスから取得し、設定
	    service.save(employee.getPosition() + "の売り上げは、"+employee.getContract().getEarnings() + "万円でした！");
	    mav.setViewName("main");
		return  new ModelAndView("redirect:/main");
	}
}
