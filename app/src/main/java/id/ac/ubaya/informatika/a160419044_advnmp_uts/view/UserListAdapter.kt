package id.ac.ubaya.informatika.a160419044_advnmp_uts.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.a160419044_advnmp_uts.R
import id.ac.ubaya.informatika.a160419044_advnmp_uts.databinding.UserListItemBinding
import id.ac.ubaya.informatika.a160419044_advnmp_uts.model.User
import id.ac.ubaya.informatika.a160419044_advnmp_uts.util.loadImage
import kotlinx.android.synthetic.main.user_list_item.view.*

class UserListAdapter(val userList:ArrayList<User>):RecyclerView.Adapter<UserListAdapter.UserViewHolder>(), ButtonDetailClickListener {
    class UserViewHolder(val view:UserListItemBinding):RecyclerView.ViewHolder(view.root)

    fun updateUserList(newUserList:List<User>) {
        userList.clear()
        userList.addAll(newUserList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // val v = inflater.inflate(R.layout.user_list_item, parent, false)
        val v = DataBindingUtil.inflate<UserListItemBinding>(inflater, R.layout.user_list_item, parent, false)

        return UserViewHolder(v)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        holder.view.user = userList[position]
        holder.view.listener = this

//        holder.view.txtId.text = userList[position].id
//        holder.view.txtName.text = userList[position].name
//        holder.view.imageView.loadImage(userList[position].photoUrl.toString(), holder.view.progressBar)
//
//        if(userList[position].status.equals("VACCINATED")) {
//            holder.view.txtStatusV.setText("VACCINATED")
//            holder.view.txtStatusV.setTextColor(Color.GREEN)
//        } else {
//            holder.view.txtStatusV.setText("NOT VACCINATED")
//            holder.view.txtStatusV.setTextColor(Color.RED)
//        }
//
//        holder.view.btnDetail.setOnClickListener {
//            //val action = UserListFragmentDirections.actionUserDetail()
//            var userId = userList[position].id.toString()
//            val action = UserListFragmentDirections.actionUserDetail(userId)
//            Navigation.findNavController(it).navigate(action)
//        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onButtonDetailClick(v: View) {
        val action = UserListFragmentDirections.actionUserDetail(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }
}