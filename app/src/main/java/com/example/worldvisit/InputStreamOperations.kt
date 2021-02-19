package com.example.worldvisit

import java.io.IOException
import java.io.InputStream

object InputStreamOperations {
    /**
     * @param in : buffer with the php result
     * @param bufSize : size of the buffer
     * @return : the string corresponding to the buffer
     */
    /**
     * @param in : buffer with the php result
     * @return : the string corresponding to the buffer
     */
    @JvmOverloads
    fun InputStreamToString(`in`: InputStream, bufSize: Int = 1024): String {
        val out = StringBuilder()
        val buffer = ByteArray(bufSize)
        try {
            var ctr: Int
            while (`in`.read(buffer).also { ctr = it } != -1) {
                out.append(String(buffer, 0, ctr))
            }
        } catch (e: IOException) {
            throw RuntimeException("Cannot convert stream to string", e)
        }
        // On retourne la chaine contenant les donnees de l'InputStream
        return out.toString()
    }
}