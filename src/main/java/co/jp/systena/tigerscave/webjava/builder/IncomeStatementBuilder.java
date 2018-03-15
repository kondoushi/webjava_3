package co.jp.systena.tigerscave.webjava.builder;

import java.util.ArrayList;

import co.jp.systena.tigerscave.webjava.document.AbstractDocument;
import co.jp.systena.tigerscave.webjava.document.Contract;
import co.jp.systena.tigerscave.webjava.document.IncomeStatement;

public class IncomeStatementBuilder implements Builder {
	private ArrayList<Contract> contractArray = new ArrayList<>();

	@Override
	public void setDoc(AbstractDocument doc) {
		Contract contract  = new Contract();
		contract = (Contract) doc;
		this.contractArray.add(contract);
	}

	@Override
	public IncomeStatement create() {
		IncomeStatement incomeStatement = new IncomeStatement();

		for (int i = 0; i < contractArray.size(); i++) {
			incomeStatement.addRevenue(contractArray.get(i).getEarnings());
			incomeStatement.addExpense(contractArray.get(i).getCosts());
		}
		return incomeStatement;
	}

}
