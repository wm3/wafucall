import spock.lang.*

import jp.w3ch.wa.Wa

class SingleArgumentTest extends Specification {
	static interface Target {
		def 〜する()
		def 〜する(o)
		def 〜する(o1, o2)
	}

	def a = new Object()
	def b = new Object()
	def target = Mock(Target.class)

	def x = Wa.japanese(target)

	def "example 1-1"() {
		when: x.は〜する()
		then: 1 * target.〜する()
	}

	def "example 1-2"() {
		when: x.も〜する()
		then: 1 * target.〜する()
	}



	def "example 2-1"() {
		when: x.が a を〜する()
		then: 1 * target.〜する(a)
	}

	def "example 2-2"() {
		when: x.は a で〜する()
		then: 1 * target.〜する(a)
	}



	def "example 3-1"() {
		when: x.は a と b を〜する()
		then: 1 * target.〜する(a, b)
	}

}
