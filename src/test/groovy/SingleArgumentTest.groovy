import spock.lang.*

import jp.w3ch.wa.Wa

class SingleArgumentTest extends Specification {
	static interface Hangry {
		def 食べる(食べ物)
		def 我慢する()
	}

	def apple = new Object()
	def you = Mock(Hangry.class)

	def "zero argument call"() {
		def あなた = Wa.japanese(you)

		when: あなた.は我慢する()

		then:
		1 * you.我慢する()
		0 * you._
	}

	def "most simple call"() {
		def あなた = Wa.japanese(you)

		when: あなた.が(apple).を食べる()

		then:
		1 * you.食べる(apple)
		0 * you._
	}

	def "call with few parenthesis"() {
		def あなた = Wa.japanese(you)

		when: あなた.が apple を食べる()

		then:
		1 * you.食べる(apple)
		0 * you._
	}

	@Ignore
	def "call with no parenthesis"() {
		def あなた = Wa.japanese(you)

		when: あなた.が apple を食べる

		then:
		1 * you.食べる(apple)
		0 * you._
	}
}
