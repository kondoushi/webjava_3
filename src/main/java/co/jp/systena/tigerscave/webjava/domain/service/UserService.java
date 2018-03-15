package co.jp.systena.tigerscave.webjava.domain.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.jp.systena.tigerscave.webjava.infrastructure.dao.User;
import co.jp.systena.tigerscave.webjava.infrastructure.repository.UserRepository;

@Service    // サービス層クラス（ビジネスロジック）
public class UserService {
  @Autowired    // DI設定。依存関係をなくす
  private UserRepository repository;

  // 新規登録
  public void save(String name) {
    User user = new User();
    user.setName(name);
    repository.save(user);
  }

  // 全権取得
  public List<User> findAll() {
    return repository.findAll();
  }
}
