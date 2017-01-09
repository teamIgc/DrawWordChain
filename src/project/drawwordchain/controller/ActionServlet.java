package project.drawwordchain.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import project.drawwordchain.model.Drawing;

/*
 * 初期動作用Servlet
 */
public class ActionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    /*
     * 初期化用メソッド
     * 起動時にDrawingをインスタンス化してアプリケーションスコープに保存
     */
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();

		Drawing drawing = (Drawing)application.getAttribute("drawing");
		if(drawing == null) {
			drawing = new Drawing();
			application.setAttribute("drawing", drawing);
		}
	}

}
