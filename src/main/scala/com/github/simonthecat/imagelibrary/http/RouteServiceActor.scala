package com.github.simonthecat.imagelibrary.http

import akka.actor.{Actor, ActorLogging, ActorRefFactory, Props}
import com.github.simonthecat.imagelibrary.core.storage.ImageStorage
import com.github.simonthecat.imagelibrary.http.auth.User
import com.github.simonthecat.imagelibrary.http.route.api.ApiRoutesService
import com.github.simonthecat.imagelibrary.http.route.StaticRoutesService
import spray.routing._
import spray.routing.authentication.UserPassAuthenticator

class RouteServiceActor(val imageStorage: ImageStorage, val authenticator: UserPassAuthenticator[User]) extends Actor
with ActorLogging with HttpService with StaticRoutesService with ApiRoutesService {

  implicit val system = context.system

  override implicit def actorRefFactory: ActorRefFactory = context

  override def receive: Receive = runRoute(myRoute)

  val myRoute = staticRoutes ~ apiRoutes

}

object RouteServiceActor {

  def props(implicit imageStorage: ImageStorage, authenticator: UserPassAuthenticator[User]): Props =
    Props(classOf[RouteServiceActor], imageStorage, authenticator)

}