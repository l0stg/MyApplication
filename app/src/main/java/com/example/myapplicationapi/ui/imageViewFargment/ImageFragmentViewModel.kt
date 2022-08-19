package com.example.myapplicationapi.ui.imageViewFargment

import android.content.ContentResolver
import android.provider.MediaStore.Images.Media.insertImage
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModel


class ImageFragmentViewModel: ViewModel() {
    // Загрузка изображения во внутрению память
    fun downloadImageFromPath(context: ContentResolver, image: ImageView) {
        val imageBitmap = image.drawable.toBitmap()
        insertImage(
            context,
            imageBitmap,
            "name",
            null)
    }

}