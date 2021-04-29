package com.muhammadfurqan.bangkitaclass

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammadfurqan.bangkitaclass.data.BookRepository
import com.muhammadfurqan.bangkitaclass.data.db.entity.BookEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author by furqan on 29/04/2021
 */
class BookViewModel (private var bookRepository: BookRepository) : ViewModel() {

    fun getAllBooks(): LiveData<List<BookEntity>> =
        bookRepository.getAllBooks()

    fun addBook(book : BookEntity){
        viewModelScope.launch (Dispatchers.IO){ bookRepository.addNewBook(book)}
    }

    fun deleteBook(bookId : Int){
        viewModelScope.launch (Dispatchers.IO){ bookRepository.deleteBookByID(bookId)}
    }



}