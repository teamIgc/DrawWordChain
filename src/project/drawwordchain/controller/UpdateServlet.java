// package project.drawwordchain.controller;
//
// import java.io.IOException;
// import java.io.PrintWriter;
// import java.util.Iterator;
//
// import javax.servlet.ServletContext;
// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;
//
// // 以下要注意
// import project.drawwordchain.model.Chat;
// import project.drawwordchain.model.Statement;
// import project.drawwordchain.model.User;
// // 以上
//
// /*
//  * ほぼいじってない
//  */
// @WebServlet("/project/drawwordchain/update")
// public class UpdateServlet extends ActionServlet {
//
// 	private static final long serialVersionUID = 1L;
//
// 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//         // 自分が登録した名前を取り出す．
// 		HttpSession session = request.getSession();
// 		User user = (User)session.getAttribute("user");
//
// 		if(user != null) {
// 			ServletContext application = this.getServletContext();
// 			Drawing drawing = (Drawing)application.getAttribute("drawing");
//
// 			StringBuilder builder = new StringBuilder();
// 			builder.append("{");
// 			builder.append("\"user\":");
// 			UserHelper.toJson(user, builder);
// 			builder.append(",");
// 			builder.append("\"statementList\":[");
//
// 			Iterator<Statement> statementIterator = chat.getStatementList().iterator();
// 			if(statementIterator.hasNext()) {
// 				Statement statement = statementIterator.next();
// 				StatementHelper.toJson(statement, builder);
// 			}
// 			while(statementIterator.hasNext()) {
// 				builder.append(",");
// 				Statement statement = statementIterator.next();
// 				StatementHelper.toJson(statement, builder);
// 			}
// 			builder.append("]");
// 			builder.append("}");
// 			String json = builder.toString();
//
// 			response.setContentType("application/json");
// 			PrintWriter writer = response.getWriter();
// 			writer.append(json);
// 			writer.flush();
//
//             //jsonファイルの作成(以下sample)
//             StringBuilder builder = new StringBuilder();
//             builder.append('{');
//             builder.append("\"answer\":").append("\"").append(answer).append("\"");
//             builder.append('}');
//             String json = builder.toString();
//             System.out.println(json);
//             response.setContentType("application/json");
//             PrintWriter writer = response.getWriter();
//             writer.append(json);
//             writer.flush();
// 		}
// 	}
//
// }
