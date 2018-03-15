package co.jp.systena.tigerscave.webjava.employee;

import co.jp.systena.tigerscave.webjava.customer.AbstractCustomer;
import co.jp.systena.tigerscave.webjava.customer.GoodCustomer;
import co.jp.systena.tigerscave.webjava.customer.NormalCustomer;
import co.jp.systena.tigerscave.webjava.customer.RoyalCustomer;
import co.jp.systena.tigerscave.webjava.document.Contract;

public abstract class AbstractEmployee {
  protected String position;
  protected Contract contract;

  // 費用
  protected int cost = 0;

  // コンストラクタ
  AbstractEmployee() {
   // this.contract = new Contract();
  }
  // setter
  protected void setCost(int cost) {
    this.cost = cost;
  }

  // getter
  public int getCost() {
    return this.cost;
  }

  // 契約書を返却する
  public Contract work() {

    AbstractCustomer customer = null;
    switch (position) {
    case "スタッフ":
    	customer = new NormalCustomer();
    	break;
	case "チーフ":
		customer = new GoodCustomer();
		break;
	case "CEO":
		customer = new RoyalCustomer();
		break;
	}
    Contract contract = new Contract();
    customer.sign(contract);
    contract.setCosts(this.cost);
    this.contract = contract;
    return contract;
  }

  public Contract getContract() {
	  return this.contract;
  }

  public String getPosition() {
    return this.position;
  }

  protected void setPosition(String position) {
    this.position = position;
  }

}
