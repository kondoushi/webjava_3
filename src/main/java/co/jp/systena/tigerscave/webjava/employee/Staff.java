package co.jp.systena.tigerscave.webjava.employee;

public class Staff extends AbstractEmployee{
	private static final int COST_STAFF = 20;

	public Staff() {
		super();
		setPosition("スタッフ");
		setCost(COST_STAFF);
	}

}
