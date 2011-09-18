import spock.lang.*

import jp.w3ch.wa.Wa

class MethodCallTest extends Specification {

	interface DoSomething {

		void 何かする(o)
		void 何かする(o1, o2)

		interface Overloaded {
			void 何かする()
			void 何かする(o)
		}
	}

	def "call multiple times"() {
		def subject = Mock(DoSomething)
		def 対象 = Wa.japanese(subject)
		def o = new Object()

		when: 対象.が o を何かする()
		and:  対象.が o を何かする()

		then: 2 * subject.何かする(o)
	}

	def "use an overloaded method"() {
		def subject = Mock(DoSomething.Overloaded)
		def 対象 = Wa.japanese(subject)
		def o = new Object()

		when: 対象.は何かする()
		and:  対象.が o を何かする()

		then: 1 * subject.何かする()
		and:  1 * subject.何かする(o)
	}

	def "pass expressions as params"() {
		def subject = Mock(DoSomething)
		def 対象 = Wa.japanese(subject)

		when: 対象.が (1 + 2) を何かする()
		and:  対象.が (1 + 2) で (3 + 4) を何かする()

		then: 1 * subject.何かする(3)
		and:  1 * subject.何かする(3, 7)
	}

	def "pass expressions as params without parentheses"() {
		def subject = Mock(DoSomething)
		def 対象 = Wa.japanese(subject)

		when: 対象.が (1 + 2) で 4 を何かする()
		and:  対象.が 1 で (3 + 4) を何かする()

		then: 1 * subject.何かする(3, 4)
		and:  1 * subject.何かする(1, 7)
	}
}
