日本語風にメソッド呼び出しを行うためのライブラリです。

以下のような操作にする予定です。

```groovy
// 日本語の名前でメソッドを定義
class Hungry {
	def もらう(from, food) { "$food ムシャムシャ" }
}

def apple = "リンゴ"
def someone = new Object()
def you = new Hungry()


// ターゲットに日本語風呼び出しをサポートを追加する
def あなた = Wa.japanese(you)


// メソッド呼び出し
あなた.が someone に apple をもらう() // -> "リンゴムシャムシャ"
```
