package project.drawwordchain.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.drawwordchain.model.User;


@WebServlet("/project/drawwordchain/start")
public class StartServlet extends ActionServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// セッションスコープの作成
		HttpSession session = request.getSession();
		User player = (User)session.getAttribute("user");


		// 名前未入力(Userセッションなし == 直接main.htmlを開いた状態)の場合
		if(player == null) {
			// 送信者をセッションスコープに登録
			player = new User();
			player.setName("");
			session.setAttribute("user", player);
		} else {

			//Jsonの作成
			StringBuilder builder = new StringBuilder();
			//{"playerName" : "playerだよ"}
			builder.append('{');
			builder.append('\"').append("playerName").append('\"');
			builder.append(':');
			builder.append('\"').append(player.getName()).append('\"');
	        builder.append('}');

	        String json = builder.toString();
	        System.out.println(json);
	        response.setContentType("application/json");
	        PrintWriter writer = response.getWriter();
	        writer.append(json);
	        writer.flush();
		}
	}
}
