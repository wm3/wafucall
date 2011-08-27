import spock.lang.*

import jp.w3ch.wa.Wa

class SingleArgumentTest extends Specification {
	static interface Hangry {
		def 食べる(食べ物)
		def もらう(人, 食べ物)
		def 我慢する()
	}

	def apple = new Object()
	def someone = new Object()
	def you = Mock(Hangry.class)

	def "zero argument method call"() {
		def あなた = Wa.japanese(you)

		when: あなた.は我慢する()

		then:
		1 * you.我慢する()
		0 * you._
	}

	def "single argument method call"() {
		def あなた = Wa.japanese(you)

		when: あなた.が(apple).を食べる()

		then:
		1 * you.食べる(apple)
		0 * you._
	}

	def "single argument method call with few symbols"() {
		def あなた = Wa.japanese(you)

		when: あなた.が apple を食べる()

		then:
		1 * you.食べる(apple)
		0 * you._
	}

	@Ignore
	def "single argument method call with no symbols"() {
		def あなた = Wa.japanese(you)

		when: あなた.が apple を食べる

		then:
		1 * you.食べる(apple)
		0 * you._
	}

	def "two argument method call"() {
		def あなた = Wa.japanese(you)

		when: あなた.が(someone).に(apple).をもらう()

		then:
		1 * you.もらう(someone, apple)
		0 * you._
	}

	def "two argument method call with few symbols"() {
		def あなた = Wa.japanese(you)

		when: あなた.が someone に apple をもらう()

		then:
		1 * you.もらう(someone, apple)
		0 * you._
	}
}
