package co.jp.systena.tigerscave.webjava.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.jp.systena.tigerscave.webjava.builder.IncomeStatementBuilder;
import co.jp.systena.tigerscave.webjava.builder.IncomeStatementCreator;
import co.jp.systena.tigerscave.webjava.document.IncomeStatement;
import co.jp.systena.tigerscave.webjava.employee.AbstractEmployee;
import co.jp.systena.tigerscave.webjava.infrastructure.dao.User;
import co.jp.systena.tigerscave.webjava.infrastructure.repository.UserRepository;
import co.jp.systena.tigerscave.webjava.system.AbstractSystemBase;

@Service
public class SimulationSystemService extends AbstractSystemBase{
	  @Autowired    // DI設定。依存関係をなくす
	  private UserRepository repository;

	public SimulationSystemService() {
		setBudget(100);
	}

	@Override
	public AbstractEmployee run(String input) {
		AbstractEmployee employee = null;
		employee = runWork(input);
		System.out.println(employee.getPosition() + "の売上："+employee.getContract().getEarnings()+" 残予算:"+ this.budget);
		return employee;
	}

	public IncomeStatement getResult() {
	    // 売上を計算する
	    IncomeStatementCreator incomeStatementCreator = new IncomeStatementCreator(builder);
	    IncomeStatement incomeStatement = incomeStatementCreator.construct();
	    return incomeStatement;
	}

	public int getBudget() {
		return this.budget;
	}

	public void resetBudget() {
		this.budget = 100;
	}

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

	  public void deleteAll() {
		  repository.deleteAll();
		  resetBudget();
		  builder = null;
		  builder = new IncomeStatementBuilder();
	  }


}
