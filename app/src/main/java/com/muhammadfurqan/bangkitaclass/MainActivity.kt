package com.muhammadfurqan.bangkitaclass

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhammadfurqan.bangkitaclass.data.ViewModelFactory
import com.muhammadfurqan.bangkitaclass.data.db.entity.BookEntity
import com.muhammadfurqan.bangkitaclass.databinding.ActivityMainBinding

/**
 * Challenge
 *
 * Steps :
 * 1. Fork my repository : https://github.com/fueerqan/Bangkit-A-Class
 * 2. Do the challenge
 * 3. Push the code
 * 4. Submit the form
 *
 * Tasks :
 * 1. Use the layout
 * 2. When user input in edit text and click on add book, you should insert new book to your database
 * 3. Show list of books in recycler view
 * 4. Each items of books in recycler view must have function delete
 *
 * Submit : https://forms.gle/H2wXsc4h4tTwXKD96
 * github repository URL
 *
 * Rewards :
 * 1. 20.000 of GoPay/OVO
 * 2. No limit people
 * 3. Limit Time, 30 April 2021, 23:59
 *
 */
class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    companion object {
        const val EXTRA_ID ="extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        val factory = ViewModelFactory.getInstance(this)
        val bookViewModel = ViewModelProvider(this, factory)[BookViewModel::class.java]
        val adapter = RecyclerViewAdapter(this)
        val recylerView = activityMainBinding.rvBookList
        recylerView.adapter = adapter
        recylerView.layoutManager = LinearLayoutManager(this)
        bookViewModel.getAllBooks().observe(this, Observer { book -> adapter.setData(book) })
        val id = intent.getIntExtra(EXTRA_ID,-1)
        if (id!=null){
            bookViewModel.deleteBook(id)
        }
        activityMainBinding.addBook.setOnClickListener{
            val bookName = activityMainBinding.etBookName.text.toString()
            if (bookName.isNotEmpty()){
                val book = BookEntity(0,bookName)
                bookViewModel.addBook(book)
            }
        }
    }





}