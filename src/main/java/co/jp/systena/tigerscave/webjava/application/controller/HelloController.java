package co.jp.systena.tigerscave.webjava.application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;;

// Viewなし。jsonやxmlを返却するアノテーション
@RestController
public class HelloController {

  @RequestMapping(value="/")    // URLとのマッピング
  private String hello() {
      return "Welcome Tiger's cave";    //  文字列を返却
  }
}
