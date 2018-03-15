package co.jp.systena.tigerscave.webjava.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.jp.systena.tigerscave.webjava.domain.service.SimulationSystemService;


@Controller  // Viewあり。Viewを返却するアノテーション
public class GoodbyController {
  @RequestMapping("/goodbye")  // URLとのマッピング
  public ModelAndView index(@ModelAttribute("model") ModelMap  modelMap,ModelAndView mav) {
	  SimulationSystemService service = (SimulationSystemService) modelMap.get("service");
    // Viewに渡すデータを設定
	  mav.addObject("revenue", "総売上："+service.getResult().getRevenue());
	  mav.addObject("expense", "総費用："+service.getResult().getExpense());
	  mav.addObject("profit", "総利益："+service.getResult().getProfit());
	  mav.addObject("users", service.findAll());  // ユーザデータをサービスから取得し、設定
    // Viewのテンプレート名を設定
    mav.setViewName("goodby");
    return mav;
  }

	@RequestMapping(value = "goodbye", params = "input_retry", method = RequestMethod.POST)
	public ModelAndView inputStaff(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("retry", "retry");
		return  new ModelAndView("redirect:/main");
	}

}
