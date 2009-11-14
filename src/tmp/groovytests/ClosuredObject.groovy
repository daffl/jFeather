package tmp.groovytests;

class ClosuredObject
{
	String val;
	
	def get(def closure)
	{
		def fetchClosure =
		{
					
		}
		if(val == null)
			val = closure()
		return this.get()
	}
	
	def get()
	{
		return val;
	}
	
	def fn(def v1, def v2)
	{
		println v1 + ":" + v2;
	}
	
	void setVal(String arg)
	{
		println setval
		println "setting val to " + arg
	}
	
	public static void main(String[] args)
	{
		def t = new ClosuredObject()
		/*
		def arg = "bla456"
		println t.get {
			def result = "";
			for(def i in 1..10)
				result += i
			return result
		}
		*/
		t.setVal "Setting"
	}
}