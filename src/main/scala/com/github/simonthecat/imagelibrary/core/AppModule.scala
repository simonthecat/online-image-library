package com.github.simonthecat.imagelibrary.core

import akka.actor.ActorSystem
import com.github.simonthecat.imagelibrary.core.storage.ImageStorage
import com.github.simonthecat.imagelibrary.http.auth.{User, UserPassAuth}
import com.typesafe.config.ConfigFactory
import spray.routing.authentication.UserPassAuthenticator

import scala.concurrent.ExecutionContext

trait AppModule extends MongoModule {

  val cfg = ConfigFactory.load()

  implicit val system = ActorSystem("image-library")

  implicit val ec: ExecutionContext = system.dispatcher

  implicit val imageStorage: ImageStorage

  implicit val authenticator: UserPassAuthenticator[User] = UserPassAuth.apply

}
