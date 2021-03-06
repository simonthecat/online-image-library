package com.github.simonthecat.imagelibrary.core.storage

sealed trait ImageStoreResult

case class ImageStoreSuccess(imageId: String) extends ImageStoreResult

case class ImageStoreError(reason: String) extends ImageStoreResult
