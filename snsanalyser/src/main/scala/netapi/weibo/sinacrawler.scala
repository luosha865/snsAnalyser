package netapi.weibo

import scala.xml._
import play.api.libs.json._
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by tianhaowei on 14-11-9.
 */

object sinacrawler {
  def main(args: Array[String]): Unit = {
    val keyFile = XML.load("appconf.xml")
    val appkey = (keyFile \\ "APPKEY").text.toString

    println(appkey)
  }
}