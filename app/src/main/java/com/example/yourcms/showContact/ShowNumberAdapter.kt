package com.example.yourcms.showContact

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.yourcms.R
import com.example.yourcms.model.ContactData
import de.hdodenhof.circleimageview.CircleImageView
import kotlin.random.Random

class ShowNumberAdapter:RecyclerView.Adapter<ShowNumberAdapter.ViewHolder>() {
    private var contactList = ArrayList<ContactData>()
   // emptyList<ContactData>()
    var deleteContact:DeleteContact?=null
    private lateinit var context: Context
    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {

        val name :TextView = view.findViewById(R.id.contact_name)
        val number :TextView = view.findViewById(R.id.contact_number)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item,parent,false)
        return ViewHolder(view)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = contactList[position]



        holder.name.text = contactList.get(position).name.toString()
        holder.number.text = contactList.get(position).number.toString()

           holder.itemView.findViewById<CircleImageView>(R.id.delete_item).setOnClickListener {
               deleteContact?.deleteContact(contactList[position])

           }

        //holder.itemView.findViewById<CardView>(R.id.contact_item).setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(),null))


        /*   holder.itemView.findViewById<CircleImageView>(R.id.persen).setOnClickListener {
               deleteContact?.deleteContact(contactList[position])
           }*/

        holder.itemView.findViewById<CardView>(R.id.contact_item).setOnClickListener {
            val action = ShowNumberFragmentDirections.actionShowNumberFragmentToUpdateDetailFragment(currentItem)
                holder.itemView.findNavController().navigate(action)
        }

    }

    fun randomColor(): Int{
        val list = ArrayList<Int>()
        list.add(R.color.purple_200)
        list.add(R.color.purple_500)
        list.add(R.color.purple_700)
        list.add(R.color.teal_200)
        list.add(R.color.shimmer_placeholder)
        list.add(R.color.teal_700)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]


    }

    fun filterList(search: String){
        contactList.clear()
        for(item in contactList){
            if(item.number?.lowercase()?.contains(search.lowercase()) == true || item.name?.lowercase()?.contains(search.lowercase()) == true){
                contactList.add(item)
            }
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    fun setData(contactData: ArrayList<ContactData>){
            this.contactList = contactData
        notifyDataSetChanged()
    }

}