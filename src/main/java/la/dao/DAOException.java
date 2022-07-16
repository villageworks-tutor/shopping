package la.dao;

/**
 * すべてのDAOクラスで発生する例外を統一的に扱うために変換される独自例外
 * @author tutor
 */
public class DAOException extends Exception {

	public DAOException(String message) {
		super(message);
	}

}
