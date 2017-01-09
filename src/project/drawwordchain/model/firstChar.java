package project.drawwordchain.model;

import java.util.Random;

//しりとり開始時の最初の文字を決定するクラス
public class FirstChar {

  //50音の[を]と[ん]を抜いた文字。計４４文字
  String[] charList = {"あ","い","う","え","お","か","き",
    "く","け","こ","さ","し","す","せ","そ","た","ち","つ",
    "て","と","な","に","ぬ","ね","の","は","ひ","ふ","へ",
    "ほ","ま","み","む","め","も","や","ゆ","よ","ら","り",
    "る","れ","ろ","わ"};

  public String getChar(){
    //Randomクラスの生成
    Random r = new Random();
    //乱数取得
    int i = r.nextInt(44);
    //charListの文字をランダムにreturnする
    return charList[i];
  }
}
