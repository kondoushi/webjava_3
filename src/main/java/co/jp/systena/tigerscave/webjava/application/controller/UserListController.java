package co.jp.systena.tigerscave.webjava.application.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import co.jp.systena.tigerscave.webjava.application.model.UserForm;
import co.jp.systena.tigerscave.webjava.domain.service.UserService;

@Controller  // Viewあり。Viewを返却するアノテーション
public class UserListController {

  @Autowired                            // UserServiceの注入（ここではinterfaceを指定）
  private UserService service;          // 使用時にインスタンスが生成される
  @Autowired
  HttpSession session;                  // セッション管理

  @RequestMapping(value="/userlist", method = RequestMethod.GET)          // URLとのマッピング
  public ModelAndView index(ModelAndView mav) {
	  System.out.println("get");
    // Viewに渡すデータを設定
    // セッション情報から保存したデータを取得してメッセージを生成
    UserForm userForm = (UserForm) session.getAttribute("form");
    session.removeAttribute("form");
    if (userForm != null) {
      mav.addObject("message", userForm.getName()+"を保存しました");
    }
    mav.addObject("userForm", new UserForm());  // 新規クラスを設定
    mav.addObject("users", service.findAll());  // ユーザデータをサービスから取得し、設定

    BindingResult bindingResult = (BindingResult) session.getAttribute("result");
    if (bindingResult != null) {
      mav.addObject("bindingResult", bindingResult);
    }
    // Viewのテンプレート名を設定
    mav.setViewName("userlist");
    return mav;
  }

  @RequestMapping(value="/userlist", method = RequestMethod.POST)  // URLとのマッピング
  private ModelAndView adduser(ModelAndView mav, @Valid UserForm userForm, BindingResult bindingResult, HttpServletRequest request) {
	  System.out.println("post");
    if (bindingResult.getAllErrors().size() > 0) {
      // エラーがある場合はそのまま戻す
      mav.addObject("userForm",userForm);  // 新規クラスを設定
      mav.addObject("users", service.findAll());  // ユーザデータをサービスから取得し、設定
      // Viewのテンプレート名を設定
      mav.setViewName("userlist");
      return mav;

    }
    service.save(userForm.getName());    // Viewで設定されたUserFormから名前を取得して登録
    // データをセッションへ保存
    session.setAttribute("form", userForm);
    return new ModelAndView("redirect:/userlist");        // リダイレクト
  }
}
