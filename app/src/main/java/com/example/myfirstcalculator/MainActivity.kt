package com.example.myfirstcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    var lastnumeric : Boolean = false
    var lastdot : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun onDigit(view: View) {
        textviewinput.append((view as Button).text)
        lastnumeric= true

    }

    fun onclear(view: View)
    {
        textviewinput.setText(" ")
        lastnumeric=false
        lastdot=false
    }
    fun ondecimalpoint(view: View )
    {
        if (lastnumeric && !lastdot)
        {
            textviewinput.append(".")
            lastnumeric=false
            lastdot=true
        }
    }
     fun isoperator(view : View )
     {
         if(lastnumeric && !isoperatoradded(textviewinput.text.toString())){
             textviewinput.append((view as Button).text)
             lastnumeric=false
             lastdot=false

         }
     }
     private fun  isoperatoradded(value : String ): Boolean {
            return if (value.startsWith("-"))
            {
                    false
            }else {
                value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")

            }


     }
     private  fun removezeroafterdot(result : String) : String {
           var value = result
         if(result.contains("0"))
         {
             value=result.substring(0,result.length-2)
         }
        return value
     }

    fun oneqal(view : View )
    {
        if(lastnumeric){
            var tvvalue =textviewinput.text.toString()
            var prefix=""
           // tvvalue=tvvalue.substring(1)


            try {
                if(tvvalue.startsWith("-"))
                {
                    prefix="-"
                    tvvalue=tvvalue.substring(1)

                }
                if(tvvalue.contains("-"))

                {
                    val splitvalue =tvvalue.split("-")
                    var one =splitvalue[0]
                    var two=splitvalue[1]
                    if(!prefix.isEmpty())
                    {
                        one = prefix+one

                    }
                    textviewinput.text=removezeroafterdot((one.toDouble()-two.toDouble()).toString())
                }
                else if(tvvalue.contains("/"))
                    {
                        val splitvalue =tvvalue.split("/")
                        var one =splitvalue[0]
                        var two=splitvalue[1]
                        if(!prefix.isEmpty())
                        {
                            one = prefix+one

                        }
                        textviewinput.text=removezeroafterdot((one.toDouble()/two.toDouble()).toString())

                    }else if(tvvalue.contains("+"))
                {
                    val splitvalue =tvvalue.split("+")
                    var one =splitvalue[0]
                    var two=splitvalue[1]
                    if(!prefix.isEmpty())
                    {
                        one = prefix+one

                    }
                    textviewinput.text=removezeroafterdot((one.toDouble()+two.toDouble()).toString())

                } else if(tvvalue.contains("*"))
                {
                    val splitvalue =tvvalue.split("*")
                    var one =splitvalue[0]
                    var two=splitvalue[1]
                    if(!prefix.isEmpty())
                    {
                        one = prefix+one

                    }
                    textviewinput.text=removezeroafterdot((one.toDouble()*two.toDouble()).toString())

                }

            }catch(e:ArithmeticException)
            {
                e.printStackTrace()
            }
        }
    }


}