package DB.graph

/**
 * Created by tianhaowei on 14-11-11.
 */

import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.graphdb.Node
import org.neo4j.graphdb.Relationship
import org.neo4j.graphdb.RelationshipType
import org.neo4j.graphdb.Transaction
import org.neo4j.graphdb.factory.GraphDatabaseFactory
import org.neo4j.graphdb.factory.GraphDatabaseSettings
import org.neo4j.graphdb.index.ReadableIndex

object RelTypes extends RelationshipType{
  def name:String = {
    "relationTo"
  }
}

class neo4jWrap(DB_PATH : String) {
  //val DB_PATH = "snsgraph.db"
  val USERNAME_KEY = "uid"

  val graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH )
  val nodeIndex = graphDb.index().forNodes( "nodes" );
  registerShutdownHook( graphDb )

  def createNode(uid:String): Node = {
    val node : Node = graphDb.createNode()
    node.setProperty(USERNAME_KEY , uid )
    nodeIndex.add( node, USERNAME_KEY, uid )
    node
  }

  def getDB(): GraphDatabaseService={
    graphDb
  }



  def addLink(id1:String , id2:String ): Unit ={
    val tx = graphDb.beginTx()
    val node1 : Node = graphDb.createNode()
    node1.setProperty("uid",id1)
    val node2 : Node= graphDb.createNode()
    node2.setProperty("uid",id2)
    val relation = node1.createRelationshipTo(node2, RelTypes)
    relation.setProperty("type", "To")
    tx.success()
    tx.failure()
  }

  private def registerShutdownHook(graphDb : GraphDatabaseService) {
    Runtime.getRuntime.addShutdownHook(new Thread {
      override def run {
        graphDb.shutdown()
      }
    })
  }

}

object neo4jWrap {



}
