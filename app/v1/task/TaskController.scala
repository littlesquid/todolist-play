package v1.task

import javax.inject.Inject
import play.api.mvc._
import play.api.db._
import scala.collection.immutable._
import play.api.libs.json.{JsArray, Json}

class TaskController @Inject()(cc: ControllerComponents) extends AbstractController(cc){

  def getAll = Action{
    implicit  request =>
      Ok(Json.obj("task" -> Task("1","test",1)))

  }

  def addTask(id:String,subject:String,status:Int) = Action {
    implicit  request =>
      Task.addTask(id,subject,status)
      Ok(Json.obj("task" -> Task(id,subject,status)))
  }

  def getTaskById(id:String) = Action {
    implicit request =>
      Ok(s"getTaskById $id")
  }

  def updateTask(id:String,subject:String) = Action {
    implicit request =>
      Ok(s"updateTask $id , $subject")
  }

  def updateStatus(id:String,status:String) = Action {
    implicit  request =>
      Ok(s"updateStatus $id , $status")
  }

}
