package com.shipping_eligibility

import java.io.File

class FileManager {

    fun readFile(fileName: String?): Array<String>{
        var list: MutableList<String> = mutableListOf()
        File(fileName).forEachLine {
            list.add(it)
        }
        return list.toTypedArray()
    }

}