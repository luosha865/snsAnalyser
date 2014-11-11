package netapi.weibo.Crawler

/**
 * Created by tianhaowei on 14-11-10.
 */
import scala.collection.mutable.ArrayBuffer

object SinaUser{

  def getfollowUrl(uid:String) : Array[String] = {
    val urllst  = new ArrayBuffer[String]
    for (i<-1 to 5){
      urllst.append("http://weibo.com/" + uid + "/follow?page="+i.toString)
    }
    urllst.toArray
  }

  def getfollowingUrl(uid:String) : Array[String] = {
    val urllst  = new ArrayBuffer[String]
    for (i<-1 to 5){
      urllst.append("http://weibo.com/" + uid + "/follow?relate=fans&page="+i.toString)
    }
    urllst.toArray
  }

  def getweibopostUrl(uid:String) : String = {
    return "http://weibo.com/" + uid + "/follow"
  }

}



