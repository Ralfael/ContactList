package br.edu.scl.ifsp.sdm.contactlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.scl.ifsp.sdm.contactlist.R
import br.edu.scl.ifsp.sdm.contactlist.databinding.TileContactBinding
import br.edu.scl.ifsp.sdm.contactlist.model.Contact
import br.edu.scl.ifsp.sdm.contactlist.view.OnContactClickListener

class ContactRvAdapter(private val contactList: MutableList<Contact>, private val onContactClickListener: OnContactClickListener
):
    RecyclerView.Adapter<ContactRvAdapter.ContactViewHolder>() {
    inner class ContactViewHolder(tileContactBinding: TileContactBinding): RecyclerView.ViewHolder(tileContactBinding.root){
        val nameTv: TextView = tileContactBinding.nameTv
        val emailTv: TextView = tileContactBinding.emailTv
    }

    override fun getItemCount() = contactList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TileContactBinding.inflate(LayoutInflater.from(parent.context), parent, false).run {
            ContactViewHolder(this)
        }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        contactList[position].also { contact ->
            with(holder) {
                nameTv.text = contact.name
                emailTv.text = contact.email
                itemView.setOnClickListener {
                    val adapterPosition = holder.adapterPosition
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        onContactClickListener.onContactClick(adapterPosition)
                    }
                }
                itemView.setOnLongClickListener { view ->
                    PopupMenu(view.context, view).apply {
                        inflate(R.menu.context_menu_main)
                        setOnMenuItemClickListener { item ->
                            val adapterPosition = holder.adapterPosition
                            if (adapterPosition == RecyclerView.NO_POSITION) {
                                false
                            } else {
                                when (item.itemId) {
                                    R.id.editContactMi -> {
                                        onContactClickListener.onContactEdit(adapterPosition)
                                        true
                                    }
                                    R.id.removeContactMi -> {
                                        onContactClickListener.onContactRemove(adapterPosition)
                                        true
                                    }
                                    else -> false
                                }
                            }
                        }
                    }.show()
                    true
                }
            }
        }
    }
}