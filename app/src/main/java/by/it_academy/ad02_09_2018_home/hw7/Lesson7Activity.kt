package by.it_academy.ad02_09_2018_home.hw7

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SearchView
import by.it_academy.ad02_09_2018_home.R

class Lesson7Activity : Activity() {

    private var recyclerView: RecyclerView? = null
    private var adapter: RecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson7)
        initRecyclerView()

        val searchView = findViewById<SearchView>(R.id.search_view)
        val id = searchView.context.resources.getIdentifier("android:id/search_src_text", null, null)
        val editText = searchView.findViewById(id) as EditText
        editText.setTextColor(Color.BLACK)

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        val buttonAdd: FloatingActionButton? = findViewById<FloatingActionButton>(R.id.addFAB)
        buttonAdd?.setOnClickListener {
            var intent: Intent = Intent(this, PersonEditActivity::class.java)
            intent.putExtra("isNew", true)
            startActivity(intent)
        }
    }

    private fun filter(text: String) {
        val filteredList: ArrayList<Person> = ArrayList<Person>()

        PersonListSingleton.list.forEach {
            if (it.name.contains(text, true) || it.surname.contains(text, true)) {
                filteredList.add(it)
            }
        }
        adapter?.filterList(filteredList)
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.receclerView)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.setHasFixedSize(true)
        adapter = RecyclerViewAdapter(this.baseContext)
        recyclerView?.adapter = adapter
    }

}
