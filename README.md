# SystemProjectII 絵しりとり-DrawWordChain-
情報システムプロジェクトIIのB-17班による絵しりとり用プログラム．

# 作業予定内容
- 名前登録→待機用(今何人がこのセッションにいるかを表示)→開始でそのセッションにいる人とプレイ
- main.html移行時，名前を表示できない．
 - 更新用Servletが必要．それか待機用htmlを増やすか．どちらにせよ更新用servlet必須？
- Drawing内のUserListから取ると誰かが落ちたときに削除されない
 - セッションが切れたときにDrawing内のListも削除しなければならないor別にPlayerListを保存

- 空白ネームの規制(JS)
- `ん`で終わる単語の禁止

- ~~文字判定~~
- ~~しりとり開始時の文字決定~~
- ~~CanvasのJsonデータ化~~
- ~~JsonデータのCanvas化~~

# 流れ
~~ActionServlet.java
->
名前入力して開始
->
JoinServlet.java
->
main.htmlに移行~~
->
開始ボタン
->
StartServlet.java
->
これ以降UpdateServletで制限時間などを管理?
->
絵を書き終わるor制限時間が過ぎる
->
SendServlet.java
->
最後の人が終わると終了
->
EndServlet.java
->
result.html
->
トップページに戻るボタンを押す
->
上へ
