package br.edu.scl.ifsp.sdm.contactlist.view

sealed interface OnContactClickListener {
    fun onContactClick(position: Int)
    fun onContactEdit(position: Int)
    fun onContactRemove(position: Int)
}