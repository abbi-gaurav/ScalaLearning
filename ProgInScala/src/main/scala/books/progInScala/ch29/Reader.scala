package books.progInScala.ch29

import java.io.{IOException, BufferedReader, FileReader}

/**
 * Created by gabbi on 18/10/14.
 */
class Reader(fileName:String) {
  private val in = new BufferedReader(new FileReader(fileName))

  @throws(classOf[IOException])
  def read = in.read()
}
