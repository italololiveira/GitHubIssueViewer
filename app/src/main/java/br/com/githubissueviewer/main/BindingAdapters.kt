package br.com.githubissueviewer.main

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.bumptech.glide.Glide
import com.bumptech.glide.module.AppGlideModule

@BindingAdapter("app:withGlide")
fun setWithGlide(view: ImageView?, url: String?) {
    view?.let { Glide.with(view.context).load(url).into(it) }
}