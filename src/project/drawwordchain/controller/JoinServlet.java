package project.drawwordchain.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.drawwordchain.model.Drawing;
import project.drawwordchain.model.User;

@WebServlet("/project/drawwordchain/join")
public class JoinServlet extends ActionServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");

        // セッションスコープの作成
		HttpSession session = request.getSession();

        // アプリケーションスコープの作成
		ServletContext application = this.getServletContext();

        // スコープからDrawingオブジェクトを取得
		Drawing drawing = (Drawing)application.getAttribute("drawing");
		User user = drawing.getUserByName(name);

        // 既存ユーザの確認/いなければ登録
		if(user == null) {
			user = new User();
			user.setName(name);
			drawing.addUser(user);
		}

        // 送信者をセッションスコープに登録
		session.setAttribute("user", user);

        // main.htmlに飛ばす
		response.sendRedirect("main.html");
	}

}
