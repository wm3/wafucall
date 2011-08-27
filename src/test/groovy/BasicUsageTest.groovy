import spock.lang.*

import jp.w3ch.wa.Wa

class BasicUsageTest extends Specification {
	static interface Hangry {
		def 食べる(食べ物)
		def もらう(人, 食べ物)
		def 我慢する()
	}

	def apple = new Object()
	def someone = new Object()
	def you = Mock(Hangry.class)

	def あなた = Wa.japanese(you)

	def "call a zero argument method"() {

		when: あなた.は我慢する()

		then: 1 * you.我慢する()
	}

	def "call a single argument method"() {

		when: あなた.が(apple).を食べる()

		then: 1 * you.食べる(apple)
	}

	def "call a single argument method with few symbols"() {

		when: あなた.が apple を食べる()

		then: 1 * you.食べる(apple)
	}

	@Ignore
	def "call a single argument method with no symbols"() {

		when: あなた.が apple を食べる

		then: 1 * you.食べる(apple)
	}

	def "call a two argument method"() {

		when: あなた.が(someone).に(apple).をもらう()

		then: 1 * you.もらう(someone, apple)
	}

	def "call a two argument method with few symbols"() {

		when: あなた.が someone に apple をもらう()

		then: 1 * you.もらう(someone, apple)
	}

	@Ignore
	def "call repeatedly"() {

		when:
		t.が各(x).を登録する()
		t.が(xlength).個の(x).を登録する()

		then:
		x.each { t.登録する(it) }
	}

	@Ignore
	def "call a procedure object "() {
		x.run(t)
		 t.が(x).する()
	}
}
