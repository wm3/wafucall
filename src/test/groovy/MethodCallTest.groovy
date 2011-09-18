import spock.lang.*

import jp.w3ch.wa.Wa

class MethodCallTest extends Specification {

	interface Traveler {

		void 移動する(o)
		void 移動する(o1, o2)

	}

	def someone = Mock(Traveler)
	def あの人 = Wa.japanese(someone)

	def "call multiple times"() {
		def アマゾン川 = new Object()

		when: あの人.が アマゾン川 へ移動する()
		and:  あの人.が アマゾン川 へ移動する()

		then: 2 * someone.移動する(アマゾン川)
	}

	def "use an overloaded method"() {
		def 西海岸 = new Object(), カヤック = new Object()

		when: あの人.は 西海岸 へ移動する()
		and:  あの人.は 西海岸 へ カヤック で移動する()

		then: 1 * someone.移動する(西海岸)
		and:  1 * someone.移動する(西海岸, カヤック)
	}

	def "pass expressions as params"() {
		def 黄金の国 = new Object(), ダッシュ = new Object()
		def 海 = [の向こう: 黄金の国]

		when: あの人.が (海.の向こう) へ移動する()
		and:  あの人.が (海.の向こう) へ ダッシュ で移動する()

		then: 1 * someone.移動する(黄金の国)
		and:  1 * someone.移動する(黄金の国, ダッシュ)
	}

	def "pass expressions as params without parentheses"() {
		def スコップ = new Object(), こっち = new Object(), あっち = new Object()
		def 地球 = [の反対側: あっち]

		when: あの人.が スコップ で (地球.の反対側) へ移動する()
		and:  あの人.が (地球.の反対側) から こっち へ移動する()

		then: 1 * someone.移動する(スコップ, あっち)
		and:  1 * someone.移動する(あっち, こっち)
	}
}
