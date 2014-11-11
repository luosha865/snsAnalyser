package netapi.weibo.Crawler

import scala.xml.XML
import java.util.Base64
import java.net.URLEncoder
import java.net.URL
import java.io.BufferedReader
import java.io.InputStreamReader
import play.api.libs.json._

/**
 * Created by tianhaowei on 14-11-11.
 */
object LoginModel {

  val _user = (XML.load("appkey.xml") \\ "ROOT" \\ "SinaUser").text.toString
  val _pwd = (XML.load("appkey.xml") \\ "ROOT" \\ "SinaPWD").text.toString

  val LoginURL = "https://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.4.15)";
  var PreLogin = "http://login.sina.com.cn/sso/prelogin.php?entry=account&callback=sinaSSOController.preloginCallBack&su=USERNAME&rsakt=mod&client=ssologin.js(v1.4.15)";

  val base64 = Base64.getMimeEncoder().encodeToString(URLEncoder.encode(_user).getBytes());
  PreLogin = PreLogin.replaceAll("USERNAME", base64);
  val sysdate = System.currentTimeMillis.toString;

  def getPreLogin: Map[String,String] = {
    val url: URL = new URL(PreLogin)
    val is = url.openStream
    val br = new BufferedReader(new InputStreamReader(is))
    val sb = new StringBuffer
    var line: String = ""
    while ((({
      line = br.readLine; line
    })) != null) {
      sb.append(line)
    }
    var resultJSON: String = sb.toString
    resultJSON = resultJSON.substring(resultJSON.indexOf('{'), resultJSON.length - 1)
    val objson = Json.parse(resultJSON)
    val servertime: String = (objson \ "servertime").toString()
    val nonce: String = (objson \ "nonce").toString()
    val pubkey: String = (objson \"pubkey").toString()
    val rsakv: String = (objson  \"rsakv").toString()
    var PreLoginMap = Map[String,String]()
    PreLoginMap += ("servertime" -> servertime)
    PreLoginMap += ("nonce"  -> nonce)
    PreLoginMap += ("pubkey" -> pubkey)
    PreLoginMap += ("rsakv" -> rsakv)
    println(pubkey)
    PreLoginMap
  }

  def getCookie() : String ={
    (XML.load("appkey.xml") \\ "ROOT" \\ "Cookie").text.toString
  }

}
