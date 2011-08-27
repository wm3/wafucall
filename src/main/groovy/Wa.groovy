package jp.w3ch.wa

class Wa {
	static japanese(target) {
		def rejectedMethods = Object.metaClass.methods.collect {it.name}
		def methods = target.metaClass.methods.findAll {
			! rejectedMethods.contains(it.name)
		}

		def proxy = new Proxy(target: target)
		methods.each {method ->
			proxy.metaClass."は${method.name}" = {->
				proxy._call(method)
			}
			proxy.metaClass."を${method.name}" = {->
				proxy._call(method)
			}
			proxy.metaClass."getを${method.name}" = {->
				proxy._call(method)
			}
		}

		proxy
	}
}

class Proxy {
	def target
	def arguments = []

	def が(arg) { arguments << arg; this }
	def に(arg) { arguments << arg; this }
	def getを() { this }

	def _call(method) {
		method.invoke(target, *arguments)
	}

	@Override
	def propertyMissing(String name) {
		def method = metaClass.methods.find{ it.name == name }
		if ( ! method) method = metaClass.methods.find { it.name ==~ /を$name/ }

		method ? method.invoke() : super.propertyMissing(name)
	}
}
