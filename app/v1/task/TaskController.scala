package v1.task

import javax.inject.Inject
import net.liftweb.json._
import play.api.mvc._

class TaskController @Inject()(cc: ControllerComponents) extends AbstractController(cc){

  def getAll = Action{
    implicit  request =>
      //Task.getTask()
      Ok("")

  }

  def addTask() = Action {
    implicit  request =>{
      implicit val fmt = net.liftweb.json.DefaultFormats
      val res = net.liftweb.json.parse(request.body.asJson.get.toString()).extract[Task]
      println(res)
      Task.addTask(res.id, res.subject, res.status)
      Ok(compactRender(Extraction.decompose(res)))
    }

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
