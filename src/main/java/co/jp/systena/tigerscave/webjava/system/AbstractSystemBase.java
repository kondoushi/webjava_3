package co.jp.systena.tigerscave.webjava.system;

import static co.jp.systena.tigerscave.webjava.constants.ConstantPrintMsg.*;

import java.util.Scanner;

import co.jp.systena.tigerscave.webjava.builder.Builder;
import co.jp.systena.tigerscave.webjava.builder.IncomeStatementBuilder;
import co.jp.systena.tigerscave.webjava.document.Contract;
import co.jp.systena.tigerscave.webjava.employee.AbstractEmployee;
import co.jp.systena.tigerscave.webjava.employee.CEO;
import co.jp.systena.tigerscave.webjava.employee.Chief;
import co.jp.systena.tigerscave.webjava.employee.Staff;

public abstract class AbstractSystemBase {
  protected int budget = 0;
  protected Builder builder;

  protected Scanner scanner = new Scanner(System.in);

  protected AbstractEmployee staff;
  protected AbstractEmployee chief;
  protected AbstractEmployee ceo;

  public AbstractSystemBase() {
    this.builder = new IncomeStatementBuilder();
    staff = new Staff();
    chief = new Chief();
    ceo = new CEO();
  }

  /*
   * システム実行メソッド
   */
  public abstract AbstractEmployee run(String input);

  protected void setBudget(int budget) {
    this.budget = budget;
  }

  protected void workEmployee(AbstractEmployee employee) {
	  println("残予算："+ budget);
    this.budget -= employee.getCost();
    println("残予算："+ budget);
    Contract contract = employee.work();
    builder.setDoc(contract);
    printDummy();
    showEmployeeResult(employee);
  }

  protected void setBuilder(Builder builder) {
    this.builder = builder;
  }

  /**
   * 従業員の成績表示
   *
   * @param employee
   */
  private void showEmployeeResult(AbstractEmployee employee) {
      if (employee.getContract().getEarnings() == 0) {
          println(employee.getPosition() + "は、契約に失敗しました。売上は、" + employee.getContract().getEarnings() + "円です。");
          println("---------------------------------------------");
          println("");
      } else {
          println(employee.getPosition() + "の売上は、" + employee.getContract().getEarnings() + "万円です。");
          println("---------------------------");
          println("");
      }
  }

  /**
   * オブジェクトの操作
   *
   * @param mode
   * @param input
   */
  protected AbstractEmployee runWork(String input) {
    switch (input) {
      case "1":
        workEmployee(this.staff);
        return this.staff;
      case "2":
        workEmployee(this.chief);
        return this.chief;
      case "3":
        workEmployee(this.ceo);
        return this.ceo;
    }
    return null;
  }

}
