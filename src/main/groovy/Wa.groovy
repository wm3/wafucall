package jp.w3ch.wa

class Wa {
	static japanese(target) {
		def rejectedMethods = Object.metaClass.methods.collect {it.name}
		def methods = target.metaClass.methods.findAll {
			! rejectedMethods.contains(it.name)
		}

		def proxy = new Proxy(target: target)
		methods.each {method ->
			proxy.postpositions.each {pp ->
				def name = method.name
				proxy.metaClass."$pp$name" = {->
					proxy._call(name)
				}
				proxy.metaClass."get$pp$name" = {->
					proxy._call(name)
				}
			}
		}

		proxy
	}
}

class Proxy {

	def postpositions = 'は が の を に と から で へ や か まで だけ など も しか'.split(/\s/)

	def target
	def arguments = []

	def _pushArg(arg) { arguments << arg; this }

	def _call(String method) {
		target."$method"(*arguments)
		arguments.clear()
	}

	def findPostpositionMethod(String method) {
		if (method in postpositions) {
			this.&_pushArg
		} else {
			null
		}
	}

	@Override
	def methodMissing(String name, args) {
		def method = findPostpositionMethod(name)
		if (method) return method(*args)

		throw new MissingMethodException(name, this.getClass(), args)
	}

	@Override
	def propertyMissing(String name) {
		def method = metaClass.methods.find{ it.name == name }
		if (method) return method.invoke()

		throw new MissingPropertyException(name)
	}
}
