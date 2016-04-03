import java.io.{File, PrintWriter}

def curriedSum(x: Int)(y: Int) = x + y

def onePlus = curriedSum(1) _

onePlus(3)

def twice(op: Double => Double, x: Double) = op(op(x))

def withPrintWriter(file: File) (op: PrintWriter => Unit) = {
  val p = new PrintWriter(file)
  try {
    op(p)
  }finally {
    p.close()
  }
}

withPrintWriter(new File("/tmp/Date.txt")){
  writer => writer.println(new java.util.Date())
}

var assertionEnabled = false
def myAssert(predicate : ()=>Boolean) = {
  if(assertionEnabled && !predicate())
    throw new AssertionError()
}

def myAssert2(predicate : => Boolean) = {
  if(assertionEnabled && !predicate)
    throw new AssertionError()
}

myAssert(() => 5 > 3)
myAssert2( 5 > 3)

//call by name is lazy
def boolAssert(predicate : Boolean) =
  if(assertionEnabled && !predicate)
     throw new AssertionError()

val x = 9
myAssert2(x/0==0)
boolAssert(x/0 == 0)