package com.example.android_practice.data.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.android_practice.domain.entity.ProfileEntity
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object ProfileSerializer : Serializer<ProfileEntity> {
    override val defaultValue: ProfileEntity
        get() = ProfileEntity.getDefaultInstance()

    override suspend fun readFrom(input: InputStream) : ProfileEntity {
        try {
            return  ProfileEntity.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Не могу прочитать профиль", e)
        }
    }

    override suspend fun writeTo(t: ProfileEntity, output: OutputStream) {
        t.writeTo(output)
    }
}
