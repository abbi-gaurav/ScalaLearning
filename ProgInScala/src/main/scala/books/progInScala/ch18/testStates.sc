import books.progInScala.ch18.{CircuitSimulation, Time}

val time = new Time
time.hour
time.hour = 13
time.hour

object MySimulation extends CircuitSimulation{
  override def InverterDelay: Int = 1

  override def AndGateDelay: Int = 3

  override def OrGateDelay: Int = 5
}

import MySimulation._
val input1, input2, sum, carry = new Wire
probe("sum",sum)
probe("carry", carry)
halfAdder(input1,input2, sum,carry)
input1 setSignal true
run()
input2 setSignal true
run()
