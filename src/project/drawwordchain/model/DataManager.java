package project.drawwordchain.model;

import java.util.ArrayList;
import java.util.List;

import project.drawwordchain.model.Drawing;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
	//Statementを保持するリスト
	static List<Statement> statementList = new ArrayList<Statement>();


	//Statement(名前、イラスト名、画像)オブジェクトを生成後フィールドで保持
	public void setStatement(String user,String word,String imgData){
		Statement statement = new Statement(user,word,imgData);
		DataManager.statementList.add(statement);
	}

	//statementListをかえすよおおおお
    public static List<Statement>  getStatementList(){
        return statementList;
    }

		public void ClearList(){
			System.out.println("なんでええ"+statementList.size());
			 statementList.clear();
		}

// //テスト用
//     public static void main(String args[]){
//     	DataManager data = new DataManager ();
//     	data.setStatement("111","222","333");
//     	data.setStatement("444","555","666");
//     	data.setStatement("777","888","999");
//
//     	List<Statement> sList = DataManager.getStatementList();
//     	System.out.println(sList.size());
//         for ( int i = 0; i < sList.size(); i++ ) {
//         	System.out.println(sList.get(i).getUser());
//         	System.out.println(sList.get(i).getWord());
//         	System.out.println(sList.get(i).getImageData());
//         }
//     }
}
