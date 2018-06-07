package v1.task

import java.sql.Statement

import play.api.db.Databases
import play.api.libs.functional.syntax._
import play.api.libs.json._

import scala.collection.immutable.Map


//Model
case class Task(id:String,subject:String,status:Int)



//task model
object Task{
  implicit val taskWrites: Writes[Task] = (
      (JsPath \ "id").write[String] and
      (JsPath \ "subject").write[String] and
      (JsPath \ "status").write[Int]
    )(unlift(Task.unapply))




  def getTask(): Seq[JsValue] = {
    val stmt = connect()
    val rs = stmt.executeQuery("select * from Task")
    val userToProperties = {u:Task => (u.id, u.subject, u.status)}
    val result = Seq()
    while (rs.next) {
      result + rs.getString("subject")
    }
    val subjects = result.map(subject => Json.obj("subject" -> subject))
    subjects
  }

  def addTask(id:String,subject:String,status:Int): Unit ={
    val stmt = connect()
    stmt.executeUpdate("INSERT INTO Task " + "VALUES (" + id + ", '" + subject + "', '" + status + "')")
    stmt.close()
  }
  def getTaskById(id:String){}
  def updateTask(id:String,subject:String){}
  def updateStatus(id:String,status:Int){}

  def connect():Statement = {
    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://172.17.0.1/todolist"
    val name = "todolist"
    val config = Map(
    "username" -> "todolist",
    "password" -> "123456"
    )
    val db = Databases(driver,url,name,config)
    val conn = db.getConnection()
    val stmt = conn.createStatement()
    stmt
  }
}

