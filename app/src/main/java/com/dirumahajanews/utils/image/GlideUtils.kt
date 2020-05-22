package com.dirumahajanews.utils.image

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide

import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

import java.io.File

object GlideUtils {

    fun setImage(context: Context, storageReference: StorageReference, imageView: ImageView) {
        if (context is Activity)
            if (!context.isDestroyed)
                Glide.with(context)
                    .load(storageReference)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(imageView)
    }

    fun setImage(context: Context, path: String, imageView: ImageView) {
        if (context is Activity)
            if (!context.isDestroyed)
                Glide.with(context)
                    .load(path)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(imageView)
    }

    fun setFoto(context: Context, uri: File, imageView: ImageView) {
        Glide.with(context)
            .load(uri.absoluteFile)
            .centerCrop()
            .into(imageView)
    }

    fun setFotoWithUrl(context: Context, uri: Uri, imageView: ImageView) {
        Glide.with(context)
            .load(uri)
            .centerCrop()
            .into(imageView)
    }

    fun setFotoWithUrl(context: Context, uri: String, imageView: ImageView) {
        Glide.with(context)
            .load(uri)
            .centerCrop()
            .into(imageView)
    }

    fun setFotoRoundedWithUrl(context: Context, uri: String, imageView: ImageView) {
        Glide.with(context)
            .load(uri)
            .centerCrop()
            .transform(RoundedCorners(20))
            .into(imageView)
    }

    fun setFotoCircle(context: Context, uri: String, imageView: ImageView) {
        Glide.with(context)
            .load(uri)
            .apply(RequestOptions.circleCropTransform())
            .into(imageView)
    }

    fun setFotoFromFirebase(
        context: Context,
        storageReference: StorageReference,
        imageView: ImageView
    ) {
        Glide.with(context)
            .load(storageReference)
            .centerCrop()
            .into(imageView)
    }

    fun setFotoFromFirebase(context: Context, path: String, imageView: ImageView) {
        Glide.with(context)
            .load(FirebaseStorage.getInstance().reference.child(path))
            .centerCrop()
            .into(imageView)
    }
}
