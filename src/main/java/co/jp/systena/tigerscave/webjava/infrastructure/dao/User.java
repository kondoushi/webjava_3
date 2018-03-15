package co.jp.systena.tigerscave.webjava.infrastructure.dao;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity                 // JAPのエンティティ宣言
@Table(name = "users")  // テーブル名を明示的に指名
public class User {
  @Id               // PrimaryKey
  @GeneratedValue   // 自動採番
  private Integer id;

  // 名前
  private String name;

  // getter
  public Integer getId() {
    return this.id;
  }
  public String getName() {
    return this.name;
  }
  // setter
  public void setName(String name) {
    this.name = name;
  }
}
