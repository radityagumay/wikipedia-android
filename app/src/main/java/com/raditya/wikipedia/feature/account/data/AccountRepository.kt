package com.raditya.wikipedia.feature.account.data

import com.raditya.wikipedia.common.service.WikipediaService
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

interface AccountRepository {

    suspend fun execute(file: File, mediaType: MediaType)
}

class AccountRepositoryImpl @Inject constructor(
    private val service: WikipediaService
) : AccountRepository {

    override suspend fun execute(file: File, mediaType: MediaType) {
        val requestFile = RequestBody.create(mediaType, file)
        val body: MultipartBody.Part =
            MultipartBody.Part.createFormData("picture", file.name, requestFile)
        val descriptionString = "upload image"
        val description = RequestBody.create(MultipartBody.FORM, descriptionString)
        val log = service.upload(description, body).execute()
    }
}