package co.jp.systena.tigerscave.webjava.builder;
import co.jp.systena.tigerscave.webjava.document.AbstractDocument;

public interface Builder {

	void setDoc(AbstractDocument doc);

	AbstractDocument create();
}
