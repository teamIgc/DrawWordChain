package project.drawwordchain.model;

public class CheckSentence {
  //濁音、半濁音、促音、拗音のリスト。計３０文字
  char[] sonantList = {'が','ぎ','ぐ','げ','ご','ざ','じ',
    'ず','ぜ','ぞ','だ','ぢ','づ','で','ど','ば','び','ぶ','べ',
    'ぼ','ぱ','ぴ','ぷ','ぺ','ぽ','ゃ','ゅ','ょ','ゎ','っ'};
    //濁音、半濁音、促音、拗音の清音のリスト。計３０文字
  char[] sonantChangeList = {'か','き','く','け','こ',
    'さ','し','す','せ','そ','た','ち','つ','て','と','は',
    'ひ','ふ','へ','ほ','は','ひ','ふ','へ','ほ','や','ゆ',
    'よ','わ','つ'};

  //しりとりの一つ目の単語と二つ目の単語がしりとりとしての正否を判定するメソッド
  //引数(一つ目の単語,二つ目の単語)
  //戻り値は合っていればtrue,間違っていればfalse
  public boolean checkSentence(String sentence,String nextSentence){
    //最後の文字取得
    char endChar = sentence.charAt(sentence.length()-1);
	  //長音が最後に来た場合は長音の前の文字を取得
	  if(endChar == 'ー'){
		  endChar = sentence.charAt(sentence.length()-2);
	  }
    //最初の文字提出
	  char firstChar = nextSentence.charAt(0);
    //濁音、半濁音、促音、拗音が来た場合清音に変換する
	  for(int i=0;i<30;i++){
		  if(sonantList[i]==endChar){
			  endChar = sonantChangeList[i];
		  }
		  if(sonantList[i]==firstChar){
			  firstChar = sonantChangeList[i];
		  }
	  }
    //文字の一致判定
	  if (endChar==firstChar){
		  return true;
	  }
	  else return false;

  }

}
