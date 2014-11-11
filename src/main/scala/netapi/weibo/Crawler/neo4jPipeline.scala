package netapi.weibo.Crawler

/**
 * Created by tianhaowei on 14-11-11.
 */
import us.codecraft.webmagic.pipeline.Pipeline
import us.codecraft.webmagic.{Task,ResultItems}
import DB.graph.neo4jWrap

class neo4jPipeline extends Pipeline{

  def process(resultItems : ResultItems,  task :Task) ={
    val entryArray = resultItems.getAll().entrySet().toArray().asInstanceOf[Array[java.util.Map.Entry[String, Object]]]
    val cnt = entryArray.length
    for(i <- 0 to cnt-1){
      val entry : java.util.Map.Entry[String, Object] = entryArray.apply(i)
      println(entry.getKey() + "\n" + entry.getValue())
    }
    //entryset
  }
}
