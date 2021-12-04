package id.ac.ubaya.informatika.a160419044_advnmp_uts.view

import android.view.View
import android.widget.CompoundButton
import id.ac.ubaya.informatika.a160419044_advnmp_uts.model.Todo

interface TodoChekedChangeListener {
    fun onTodoCheckedChange(cb:CompoundButton, isChecked:Boolean, obj:Todo)
}

interface TodoEditClickListener {
    fun onTodoEditClick(v:View)
}

interface RadioClickListener {
    fun onRadioClick(v:View, obj:Todo)
}

interface TodoSaveChangesListener {
    fun onTodoSaveChanges(v:View, obj:Todo)
}