package co.jp.systena.tigerscave.webjava.customer;
import co.jp.systena.tigerscave.webjava.document.Contract;

public abstract class AbstractCustomer {
  //契約金
  protected int amount = 0;

  AbstractCustomer(){
    work();
  }

  /**
   * 契約金を決定する
   */
  protected abstract void work();


	public void sign(Contract contract) {
	  contract.setEarnings(this.amount);
	}

}
