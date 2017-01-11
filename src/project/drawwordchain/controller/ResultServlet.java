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
import project.drawwordchain.model.CheckWord;
import project.drawwordchain.model.User;

import javax.servlet.http.Cookie;

@WebServlet("/project/drawwordchain/result")
public class ResultServlet extends ActionServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // アプリケーションスコープの作成
        ServletContext application = this.getServletContext();

        // スコープからDrawingオブジェクトを取得
        Drawing drawing = (Drawing)application.getAttribute("drawing");

        // 文字判定
        List<Boolean> judgeList = new ArrayList<Boolean>();
        // 各判定を格納
        List<Statement> sList = drawing.getStatementList();

        // 最初の文字を決めるメソッド
		CheckWord cS = new CheckWord();
        for ( int i = 0; i <= sList.size(); i++ ) {
            String word = sList[i].getWord();
            String nextWord = sList[++i].getWord();
            judgeList.add(cS.checkWord(word,nextWord));
        }

        // jsonファイル書き込み用
        StringBuilder builder = new StringBuilder();
        // json処理を委譲
        ResultJson.toJson(sList, judgeList, builder);
        String json = builder.toString();

        // 以下出力用
		System.out.println(json);
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		writer.append(json);
		writer.flush();
	}

}
