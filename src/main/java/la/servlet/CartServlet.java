package la.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.CartBean;
import la.bean.ItemBean;
import la.dao.DAOException;
import la.dao.ItemDAO;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	
	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションを取得
		HttpSession session = request.getSession();
		// セッションに登録されているカートを取得
		CartBean cart = (CartBean) session.getAttribute("cart");
		
		if (cart == null) {
			// カートがない場合はカートをインスタンス化
			cart = new CartBean();
			// セッションスコープに登録
			session.setAttribute("cart", cart);
		}
		
		// リクエストパラメータの文字コードを設定
		request.setCharacterEncoding("utf-8");
		// リクエストパラメータのactionキーを取得
		String action = request.getParameter("action");
		String nextPage = "cart.jsp";
		if (action == null || action.isEmpty() || action.equals("show")) {
			
		} else if (action.equals("add")) {
			try {
				// リクエストパラメータを取得
				String codeString = request.getParameter("code");
				String quantityString = request.getParameter("quantity");
				int code = Integer.parseInt(codeString);
				int quantity = Integer.parseInt(quantityString);
				// 種痘した商品番号に対応する商品を取得
				ItemDAO dao = new ItemDAO();
				ItemBean bean = dao.findByPrimaryKey(code);
				// 取得した商品をカートに追加
				cart.addCart(bean, quantity);
			} catch (DAOException e) {
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
