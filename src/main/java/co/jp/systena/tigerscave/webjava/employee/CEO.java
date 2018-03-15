package co.jp.systena.tigerscave.webjava.employee;

public class CEO extends AbstractEmployee{
	private final static int COST_CEO = 80;

	public CEO() {
		super();
		setPosition("CEO");
		setCost(COST_CEO);
	}
}
