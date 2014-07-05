package pl.allegro.itm.confitura

import java.util.List;

class JamJars {
    import scala.collection.JavaConversions._

    private def javaIteratorToList(ji : Shelf) : List[Jar] =
        com.google.common.collect.Lists.newArrayList(ji.iterator())

    def giveMeMyJars(shelves: List[Shelf], jarsInBox: Int): List[JarBox] = {
        shelves
            .map(javaIteratorToList) //Java 8 to Scala blue using Guava, this is pure barok
            .flatten //flatting out collections
            .filter(_.isFresh())
            .sortWith(_.flavor.toLowerCase > _.flavor.toLowerCase) //reverse name sorting
            .grouped(jarsInBox)
            .toList
            .map(new JarBox(_))
    }
}