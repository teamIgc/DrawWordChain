package project.drawwordchain.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.drawwordchain.model.Drawing;
import project.drawwordchain.model.FirstChar;
import project.drawwordchain.model.User;

import javax.servlet.http.Cookie;

@WebServlet("/project/drawwordchain/start")
public class StartServlet extends ActionServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 最初の文字を決めるメソッド
		FirstChar firstChar = new FirstChar();

        // アプリケーションスコープの作成
        ServletContext context = this.getServletContext();

        // jsonファイルの作成
        StringBuilder builder = new StringBuilder();

        // スコープからDrawingオブジェクトを取得
        Drawing drawing = (Drawing)context.getAttribute("drawing");


        // Json作成
        builder.append('{');

        // userListのjson作成
        builder.append("\"playerList\":[");

        // Userクラスに入っているuserをJsonに書き込む
        List<User> userList = drawing.getUserList();

        for (User user : userList) {
            builder.append('\"').append(user.getName()).append('\"');

            // 最後のみ”,”を省く
            User lastUser = userList.get(userList.size() - 1);
            if(user.equals(lastUser)) {

            } else {
                builder.append(',');
            }
        }

        builder.append(']');
        builder.append('}');
        String json = builder.toString();
        System.out.println(json);
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.append(json);
        writer.flush();

		Cookie cookie[] = request.getCookies();
		for (int i = 0 ; i < cookie.length ; i++){
        	System.out.println(cookie[i].getName() + " = " + cookie[i].getValue());
      	}
	}

}
