import spock.lang.*

import jp.w3ch.wa.Wa

class BasicUsageTest extends Specification {
	static interface Ninja {
		def 隠れる()
		def 投げる(道具)
		def 忘れる(道具, 場所)
	}

	def shuriken = new Object()
	def home = new Object()
	def ninja = Mock(Ninja)

	def 忍者 = Wa.japanese(ninja)

	def "call a zero argument method"() {

		when: 忍者.は隠れる()

		then: 1 * ninja.隠れる()
	}

	def "call a single argument method"() {

		when: 忍者.は(shuriken).を投げる()

		then: 1 * ninja.投げる(shuriken)
	}

	def "call a single argument method with few symbols"() {

		when: 忍者.は shuriken を投げる()

		then: 1 * ninja.投げる(shuriken)
	}

	@Ignore
	def "call a single argument method with no symbols"() {

		when: 忍者.は shuriken を投げる

		then: 1 * ninja.投げる(shuriken)
	}

	def "call a two argument method"() {

		when: 忍者.も(shuriken).を(home)に忘れる()

		then: 1 * ninja.忘れる(shuriken, home)
	}

	def "call a two argument method with few symbols"() {

		when: 忍者.も shuriken を home に忘れる()

		then: 1 * ninja.忘れる(shuriken, home)
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
