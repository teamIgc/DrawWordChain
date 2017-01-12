package project.drawwordchain.model;

import java.util.List;
import java.util.Iterator;



public class ResultJson{
  public static void toJson(List<Statement> statementList,List<Boolean> judgeList,StringBuilder builder){
//     {
//   "statement": [
//     {"name":"matsuura","data":"絵のデータ","word":"ワード"},
//     {"name":"tsujino","data":"絵のデータ","word":"ワード"}
//   ],
//   "judge":[
//     true,
//     false
//     ]
// }

    builder.append("{");
    builder.append("\"statement\":[");

    for(Statement statement : statementList){
      builder.append("{");
      builder.append("\"name\":\"").append(statement.getUser()).append("\",");
      builder.append("\"data\":\"").append(statement.getImageData()).append("\"");
      builder.append("}");
      //最後のみ,を省く
      Statement lastStatement = statementList.get(statementList.size()-1);
      if(statement.equals(lastStatement)){

      }else{
        builder.append(",");
      }
    }

    builder.append("]");
    builder.append(",");


    builder.append("\"judge\":[");

    for(Iterator<Boolean> iterator = judgeList.iterator(); iterator.hasNext();){
      Boolean bool = iterator.next();
      builder.append(bool);
      if(iterator.hasNext()){
        builder.append(",");
      }
    }

    builder.append("]");
    builder.append("}");
  }
}
