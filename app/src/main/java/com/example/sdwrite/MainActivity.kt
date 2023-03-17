package com.example.sdwrite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.io.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName: EditText = findViewById(R.id.etName)
        val etCgpa: EditText = findViewById(R.id.etCgpa)
        val btSave: Button = findViewById(R.id.btSave)
        val btLoad: Button = findViewById(R.id.btLoad)

        btSave.setOnClickListener {
            val name = etName.text.toString()
            val cgpa = etCgpa.text.toString()

            val file = File(getExternalFilesDir(null),"student.txt")
            val fos = FileOutputStream(file,false) //true if you want the input to be appended
            fos.write("$name,$cgpa".toByteArray())
            fos.close()
            etName.setText("")
            etCgpa.setText("")
        }

        btLoad.setOnClickListener {
            val file = File(getExternalFilesDir(null),"student.txt")
            val fis = FileInputStream(file)
            val isr = InputStreamReader(fis)
            val br = BufferedReader(isr) // of more than one line use while loop till its null
            val line: String = br.readLine()
            var part = line.split(",")
            etName.setText(part[0])
            etCgpa.setText(part[1])
            fis.close()
        }
    }
}