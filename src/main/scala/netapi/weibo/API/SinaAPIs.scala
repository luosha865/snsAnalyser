package netapi.weibo.API

import scala.xml.XML

/**
 * Created by tianhaowei on 14-11-10.
 */
object SinaAPIs {


  val _appkey = (XML.load("appkey.xml") \\ "APPKEY").text.toString

  val userinfo = "https://api.weibo.com/2/users/show.json?source=" + _appkey +"&uid="

  val public_timeline = "https://api.weibo.com/2/statuses/public_timeline.json?source=" + _appkey

  val topstatus = "https://api.weibo.com/2/users/get_top_status.json?source=" + _appkey +"&uid="
}
