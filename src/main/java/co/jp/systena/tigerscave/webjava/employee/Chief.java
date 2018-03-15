package co.jp.systena.tigerscave.webjava.employee;

public class Chief extends AbstractEmployee{
	private final static int COST_CHIEF = 40;

	public Chief() {
		super();
		setPosition("チーフ");
		setCost(COST_CHIEF);
	}
}
