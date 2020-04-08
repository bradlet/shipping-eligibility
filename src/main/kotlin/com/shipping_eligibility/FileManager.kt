package com.shipping_eligibility

import org.springframework.web.bind.annotation.RestController
import java.io.File

@RestController
class FileManager {

    fun readFile(fileName: String?): Array<String>{
        var list: MutableList<String> = mutableListOf()
        File(fileName).forEachLine {
            list.add(it)
        }
        return list.toTypedArray()
    }

    fun appendToFile(fileName: String?, newEntries: Array<String>) {
        var outFile = File(fileName)
        for (entry in newEntries) { outFile.appendText(entry+"\n") }
    }

    // Use before calling appendToFile to clear previous file contents.
    // For replacing a file entirely.
    fun overwriteFile(fileName: String?) { File(fileName).writeText("") }
}