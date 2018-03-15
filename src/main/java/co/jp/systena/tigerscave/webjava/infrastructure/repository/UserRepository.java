package co.jp.systena.tigerscave.webjava.infrastructure.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.jp.systena.tigerscave.webjava.infrastructure.dao.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
// findall, save, delete, findOneなどのAPIがある
  List<User> findAll(); // List形式のfindAllを定義
   void deleteAll();
}
