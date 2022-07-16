package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.CategoryBean;

/**
 * itemテーブルまたはcategoryテーブルにアクセスするDAO
 * @author tutor
 *
 */
public class ItemDAO {

	/**
	 * クラス定数
	 */
	// データベース接続情報文字列定数群
	private static final String JDBC_DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/sample";
	private static final String DB_USER = "student";
	private static final String DB_PASSWORD = "himitu";
	
	// SQL文字列定数群
	private static final String SQL_FIND_ALL_CATGORY = "SELECT * FROM category ORDER BY code";
	
	/**
	 * クラスフィールド：データベース接続オブジェクト
	 */
	private Connection conn;
	
	/**
	 * コンストラクタ
	 * @throws DAOException
	 */
	public ItemDAO() throws DAOException {
		try {
			Class.forName(JDBC_DRIVER);
			this.conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DAOException("データベースへの接続に失敗しました。");
		}
	}

	/**
	 * すべての商品カテゴリーを取得する。
	 * @return List<CategoryBean> 処品カテゴリーリスト
	 * @throws DAOException
	 */
	public List<CategoryBean> findAllCategory() throws DAOException {
		try (PreparedStatement pstmt = this.conn.prepareStatement(SQL_FIND_ALL_CATGORY);
			 ResultSet rs = pstmt.executeQuery();) {
			List<CategoryBean> list = new ArrayList<>();
			while (rs.next()) {
				CategoryBean bean = new CategoryBean();
				bean.setCode(rs.getInt("code"));
				bean.setName(rs.getString("name"));
				list.add(bean);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました");
		}
	}

}
