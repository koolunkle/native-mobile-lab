package com.example.fileprovider

//fun main() {
//    val cacheDir = context.cacheDir
//    val fileToReadFrom = File(cacheDir, "my-file.txt")
//
//    val size = fileToReadFrom.length().toInt()
//    val bytes = ByteArray(size)
//    val tmpBuff = ByteArray(size)
//
//    val fis = FileInputStream(fileToReadFrom)
//
//    try {
//        var read = fis.read(bytes, 0, size)
//        if (read < size) {
//            var remain = size - read
//            while (remain > 0) {
//                read = fis.read(tmpBuff, 0, remain)
//                System.arraycopy(tmpBuff, 0, bytes, size - remain, read)
//                remain -= read
//            }
//        }
//    } catch (e: IOException) {
//        throw e
//    } finally {
//        fis.close()
//    }
//}