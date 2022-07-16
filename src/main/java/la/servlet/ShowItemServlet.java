package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.CategoryBean;
import la.bean.ItemBean;
import la.dao.DAOException;
import la.dao.ItemDAO;

/**
 * Servlet implementation class ShowItemServlet
 */
@WebServlet("/ShowItemServlet")
public class ShowItemServlet extends HttpServlet {
	
	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;
	
	/** actionキー文字列定数 */
	private static final String ACTION_TOP = "top";
	private static final String ACTION_LIST = "list";
	
	private static final String KEY_CATEGORIES = "categories";
	private static final String KEY_CODE = "code";

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		try {
			// categoryテーブルからすべての商品カテゴリーを取得
			ItemDAO dao = new ItemDAO();
			List<CategoryBean> list = dao.findAllCategory();
			// 取得した商品カテゴリーリストをアプリケーションスコープに登録
			getServletContext().setAttribute(KEY_CATEGORIES, list);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの文字コードを設定
		request.setCharacterEncoding("utf-8");
		// リクエストパラメータのactionキーを取得
		String action = request.getParameter("action");
		// 取得したactionキーによる処理の分岐
		String nextPage = ""; // 不デフォルトの遷移先URL
		if (action == null || action.isEmpty() || action.equals(ACTION_TOP)) {
			// actionキーが未送信、未入力または「top」である場合：強制的にトップページに遷移
			nextPage = ACTION_TOP + ".jsp";
		} else if (action.equals(ACTION_LIST)) {
			// 商品カテゴリーが選択された場合：商品一覧を表示
			String code = request.getParameter(KEY_CODE);
			int categoryCode = Integer.parseInt(code);
			// 選択された商品カテゴリーコードに対応した商品を取得
			try {
				ItemDAO dao = new ItemDAO();
				List<ItemBean> list = dao.findByCategory(categoryCode);
				// リクエストスコープに登録
				request.setAttribute("items", list);
				nextPage = ACTION_LIST + ".jsp";
			} catch (DAOException e) {
				e.printStackTrace();
				throw new ServletException(e.getMessage());
			}
		}
		this.gotoPage(request, response, nextPage);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * 指定されたURLに遷移する。
	 * @param request HttpSevletRequest
	 * @param response HttpSevletResponse
	 * @param string 遷移先URL
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
