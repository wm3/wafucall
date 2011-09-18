日本語風にメソッド呼び出しを行うためのライブラリです。

以下のような操作にする予定です。

```groovy
// 日本語の名前でメソッドを定義
class Ninja {
	def 忘れる(tool, place) { "不覚! $tool を $place に忘れたでござる" }
}


def ninja = Mock(Ninja)

// ターゲットに日本語風呼び出しをサポートを追加する
def 忍者 = Wa.japanese(ninja)


// メソッド呼び出し
assert 忍者.も "shuriken" を "home" に忘れる() == "不覚! shuriken を home に忘れたでござる"
```
