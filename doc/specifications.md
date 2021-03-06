<style>h1,h2,.name,#date {text-align: center;}h1 {padding-top: 150px;}h2 {padding-top:150px; }h3 {}th {font-weight: normal !important;}#date {padding-top: 50px;padding-bottom: 30px;}#whitePage {width: 100%;height: 100%;}</style>

# DrawWordChain 仕様書

## B17チーム
<p class="name">MVC 担当 14-1-037-0120 松浦 知明</p>
<p class="name"> VM 担当 14-1-037-0115 辻野 翔平</p>
<p class="name"> VC 担当 14-1-037-0126 馬屋原 隆弘</p>
<p class="name">  V 担当 14-1-037-0133 後安 謙吾</p>

<div id="date">2016年1月22日(日) 作成</div>

### イントロダクション
作成者：松浦(MVC担当)
#### 1 アプリケーションの目的
　現在Web上で存在する絵しりとりでHTML5のCanvas，バックグラウンドの処理をJavaで行っているものは存在しない．そこでゲームを通したコミュニケーションツールとして作成する．想像力をアウトプットできるようになるためのトレーニングツールとしての利用も可能．<br>
　本アプリケーションの目的は，以下のとおりである．

- しりとりを通じて友情を深めること
- 絵しりとりの楽しみを知ってもらうこと
- 画力向上すること
- センスを身に着けてもらうこと
- 想像力を発達させること

<br>
#### 2 アプリケーションの範囲
　本アプリケーションは「DrawWordChain」と呼び，絵しりとりを提供する．絵しりとりとはリアルタイムで参加者が絵を書き，その絵が何であるかを次の人が予測行い，前の人の絵の名称に続けて絵を書いていくゲームのことである．また，絵しりとりには以下のような機能を搭載できると考える．
　
- 参加者のお絵かき枠の表示
- 参加者のイラスト名の表示
- 参加者の回答スペースの表示
- 参加者判別の機能
- 参加者のメッセージ送信/受信
- 正誤チェックを表示

<br>
#### 3 アプリケーションの概要
　本アプリケーションは，絵しりとりサービスを提供するものとする．本アプリケーションのユーザは，次に上げる機能を使用することができる．
　
- 参加者のお絵かき枠から絵を描き，回答スペースから送信する．
- 参加者のイラスト名を閲覧する．
- 参加者判別をする．

<br>
#### 4 アプリケーションの条件
　本アプリケーションに課された条件は，以下のとおりである．

- HTMLのcanvasを用いるため，以下のブラウザでの対応となる，<br>IE11/Edge14/Firefox49以上/Chrome49以上/Opera41以上で行うこと<br>動作検証はChrome54で行うこと
- プログラミング言語としてJavaScriptとJavaを使用すること
- サーブレットコンテナとしてJettyを使用すること
- 絵を描く番の人しか絵が描けないようになっていること
- 次の絵しりとりプレイヤーが絵を書き終わったら一つ前のプレイヤーの絵としりとりとして成り立っているか判別できること<br>
(e.g. りんご→ごりら のごの部分が一致しているかの判定)
- 参加者が楽しめるゲームになっているものになること

<br>
### アプリケーションのユースケース図とユースケース記述
作成者：松浦(MVC担当)
<img src="https://cdn.discordapp.com/attachments/254681505236910080/260670965829074945/usecase.png">

<br>

<style>#space1 {width: 100%;height: 200px;}</style>
<div id="space1"></div>

<!--<table><tr><td>ユースケース名</td><td>絵しりとりを開始する</td></tr><tr><td>アクター</td><td>ユーザ</td></tr></table>
-->

作成者:馬屋原(VC担当)

|ユースケース名|絵しりとりに参加する|
|:-|:-|
|アクター|ユーザ|
|事前条件|<ol><li>Webブラウザが起動されている．</li></ol>|
|基本系列|<ol><li>アクターが，トップページのURLにアクセスする．</li><li>システムが，トップページを表示する．</li><li>アクターが，「ネーム」フィールドにハンドルネームを入力する．</li><li>アクターが，「参加」ボタンを押す</li><li>システムが，メインページを表示する．|
|事後条件|<ol><li>メインページが表示されている．</ol></li>|
|説明|<ol><li>トップページ<ul><li>本アプリケーションのタイトルを表示する．</li><li>ユーザのハンドルネームを入力する「ネーム」フォームを表示する．</li><li>絵しりとりを開始するための「参加」ボタンを表示する．</li></ul><li>メインページ</li><ul><li>本アプリケーションのタイトルを表示する．</li><li>お絵かきを開始するための「開始」ボタンを表示する．</li><li>参加ユーザを表示する．</li><li>絵を描くための「キャンバス」を表示する．</li><li>過去の絵を表示するための「フォーム」の表示する．</li><li>絵のタイトルを入力する「イラスト名」フォームを表示する.</li><li>絵のタイトルを送信するための「送信」ボタンを表示する．</li></ul></ol>|

|ユースケース名|絵しりとりを開始する|
|:-|:-|
|アクター|ユーザ|
|事前条件|<ol><li>メインページが表示されている．</li></ol>|
|基本系列|<ol><li>アクターが，「開始」ボタンを押す．</li></ol>|
|事後条件|<ol><li>メインページが表示されている．</li></ol>|
|説明|<ol><li>「参加」ボタンを押した順に絵を書いていく．</li></ol>|

|ユースケース名|絵を送信する|
|:-|:-|
|アクター |ユーザ |
|事前条件|<ol><li>メインページが表示されている．</li></ol>|
|基本系列|<ol><li>アクターが，絵を描く．</li><li>アクターが，イラストの名前を入力する．</li><li>アクターが，「送信」ボタンを押す．</li><li>次の書き手に移る．(描く人が残ってる場合)</li><li>システムがリザルトページを表示する．(描く人が最後の場合)</li></ol>|
|事後条件|<ol><li>メインページが表示されている．(描く人が残ってる場合)</li><li>リザルトページが表示されている．(描く人が最後の場合)</li></ol>|
|説明|<ol><li>「送信」ボタンを押された時，アクター名と書いたイラストとイラスト名を組みで保存する．</li><li>それぞれ１回づつ絵を描き送信する．</li><li>リザルトページ<br><ul><li>リザルトページに遷移する時，アクター名と書いたイラストとイラスト名を表示する．</li><li>イラストが絵しりとりになっているか表示する．</li><li>「トップページに戻る」ボタンを表示する．</li></ul></li></ol>|


|ユースケース名|トップページに戻る|
|:-|:-|
|アクター |ユーザ |
|事前条件|<ol><li>リザルトページが表示されている．</li></ol>|
|基本系列|<ol><li>システムが，全てのアクター名とイラストとイラスト名の組を表示する．</li><li>正誤チェックの結果を表示する．</li><li>「トップページに戻る」ボタンを押す．</li><li>トップページを表示する．</ol>|
|事後条件|<ol><li>トップページが表示されている．</li></ol>|
|説明|<ol><li>正誤チェック<br><ul><li>前者のイラスト名の最後の文字と後者のイラスト名の頭文字が一致しているかを判定する．</li></ul></ol>|

<div id=space2></div>
<style>#space2 {margin-top: 6em;}</style>
<br>
### ページ遷移図と試作ページ
作成者:後安(V担当)
<img src="https://cdn.discordapp.com/attachments/254681505236910080/272550495011471361/pageTransition.png">

index.html
<img src="https://i.gyazo.com/d7fd22c69e448ebb831626b5e1737a7f.png">

main.html
<img src="https://gyazo.com/2e1d4481ccf3f082d4553ef0c57cd7c7.png">

<div id=space3></div>
<style>#space3 {margin-top: 300px;}</style>
result.html
<img src="https://gyazo.com/fd2a8c474a089a8b09aa24868a1d4652.png">

<div id=space4></div>
<style>#space4 {margin-top: 400px;}</style>
### 概念クラス図
作成者:辻野(VM担当)
<img src="https://cdn.discordapp.com/attachments/254681505236910080/272833439991070720/conceptClassImage.png">

<div id=space5></div>
<style>#space5 {margin-top: 550px;}</style>
### 詳細クラス図
作成者:辻野(VM担当)
<img src="https://cdn.discordapp.com/attachments/254681505236910080/272834083611213834/syousaikurasuzu.png">

<div id=space6></div>
<style>#space6 {margin-top: 550px;}</style>
### シーケンス図
作成者:後安(V担当),馬屋原(VC担当),松浦(MVC担当）

絵しりとりに参加する
<img src="https://cdn.discordapp.com/attachments/254681505236910080/271457958515179520/join.png">

<div id=space7></div>
<style>#space7 {margin-top: 200px;}</style>
絵しりとりを開始する
<img src="https://cdn.discordapp.com/attachments/254681505236910080/272823501746864128/Sequence.png">

<div id=space8></div>
<style>#space8 {margin-top: 500px;}</style>
絵を送信する
<img src="https://cdn.discordapp.com/attachments/254681505236910080/272829964196511745/Sequence.png">

<div id=space9></div>
<style>#space9 {margin-top: 400px;}</style>
トップページに戻る
<img src="https://cdn.discordapp.com/attachments/254681505236910080/271462322902532099/result.png">
