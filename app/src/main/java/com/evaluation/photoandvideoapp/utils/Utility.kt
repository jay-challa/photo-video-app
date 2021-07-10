package com.evaluation.photoandvideoapp.utils

import android.content.Context
import java.io.IOException
import java.io.InputStream

fun getJsonFromAssets(context : Context, fileName : String?) : String? {
	val fileInString: String = context.assets.open(fileName!!).bufferedReader().use { it.readText() }
	return fileInString
}