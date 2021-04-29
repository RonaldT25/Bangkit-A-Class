package com.muhammadfurqan.bangkitaclass

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammadfurqan.bangkitaclass.data.db.entity.BookEntity
import com.muhammadfurqan.bangkitaclass.databinding.ItemBookBinding



class RecyclerViewAdapter(private val activity: Activity) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private var bookList = emptyList<BookEntity>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(bookList[position])
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    inner class MyViewHolder(private val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: BookEntity) {
            with(binding){
                bookId.text = book.uid.toString()
                bookName.text = book.bookName
                delete.setOnClickListener {
                    val intentdetail = Intent(activity,MainActivity::class.java)
                    intentdetail.putExtra(MainActivity.EXTRA_ID, book.uid)
                    activity.startActivity(intentdetail)
                }
            }

        }
    }

    fun setData(book : List<BookEntity>){
        this.bookList = book
        notifyDataSetChanged()
    }

}