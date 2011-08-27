import spock.lang.*

import jp.w3ch.wa.Wa

class MethodCallTest extends Specification {

	interface DoSomething {

		void 何かする(o)

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
}
