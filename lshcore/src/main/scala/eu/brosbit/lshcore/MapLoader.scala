
object LoadGame:
  val mapPath = "mainmap.txt"

  def loadMap = 
    val linies = scala.io.Source.fromFile(mapPath).getLines
      .map(line => linie.split(" "))
    val SIZE = linies.size
    lines.map(line => line.map( s =>
          s match 
          case "
      )
    )

