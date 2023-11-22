package com.example.todolist

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    lateinit var item:EditText
    lateinit var button: Button
    lateinit var listview: ListView
      var itemslist= ArrayList<String>()
     var helper=helper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        item=findViewById(R.id.editText)
        button=findViewById(R.id.button)
        listview=findViewById(R.id.list)
        itemslist=helper.readdata(this)
        var arrayAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,itemslist)
        listview.adapter=arrayAdapter

        button.setOnClickListener {
            var itemName:String=item.text.toString()
            itemslist.add(itemName)
            item.setText("")
            helper.writedata(itemslist,applicationContext)
            arrayAdapter.notifyDataSetChanged()
        }

        listview.setOnItemClickListener {adapterView,view,position,l->
          var alert=AlertDialog.Builder(this)
            alert.setTitle("Delete")
            alert.setMessage("Done or Delete ?")
            alert.setCancelable(false)
            alert.setNegativeButton("Delete",DialogInterface.OnClickListener{ dialogInterface,i->
                dialogInterface.cancel()
                
            })
            alert.setPositiveButton("Done",DialogInterface.OnClickListener{ dialogInterface,i->
                itemslist.removeAt(position)
                arrayAdapter.notifyDataSetChanged()
                helper.writedata(itemslist,applicationContext)
            })
            alert.create().show()
        }
    }

}