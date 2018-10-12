package by.it_academy.ad02_09_2018_home.hw7

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.*
import by.it_academy.ad02_09_2018_home.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target


class PersonEditActivity : Activity() {

    private var nameEditText: EditText? = null
    private var surnameEditText: EditText? = null
    private var buttonSave: Button? = null
    private var buttonDel: Button? = null
    private var progressBar: ProgressBar? = null
    private var imageView: ImageView? = null

    private var position: Int = 0
    private var isNew: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_edit)
        initViews()
        getIncomingIntent()

        buttonSave?.setOnClickListener {
            saveData()
        }

        buttonDel?.setOnClickListener {
            removeData()
        }
    }


    private fun getIncomingIntent() {
        if (getIntent().hasExtra("position")) {
            position = getIntent().getIntExtra("position", 0)
            val person: Person? = PersonListSingleton.list[position]
            setData(person?.image, person?.name, person?.surname)
        } else if (getIntent().hasExtra("isNew")) {
            changingForCreateNew()
        }
    }

    private fun initViews() {
        buttonSave = findViewById(R.id.save)
        buttonDel = findViewById(R.id.detele)
        nameEditText = findViewById(R.id.nameEditText)
        surnameEditText = findViewById(R.id.surnameEditText)
        progressBar = findViewById(R.id.progressBar)
        imageView = findViewById(R.id.image)
    }

    private fun setData(image_url: String?, name: String?, surname: String?) {
        nameEditText?.setText(name)
        surnameEditText?.setText(surname)
        progressBar?.visibility = ProgressBar.VISIBLE
        var imageView: ImageView = findViewById(R.id.image)

        val options = RequestOptions()
                .centerCrop()
                .error(R.drawable.ic_face_white_24dp)

        Glide.with(this)
                .load(image_url)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                        progressBar?.visibility = ProgressBar.GONE
                        return false
                    }

                    override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                        progressBar?.visibility = ProgressBar.GONE
                        return false
                    }
                })
                .apply(options)
                .into(imageView)
    }

    private fun saveData() {
        if (isNew) {
            val person = Person("", nameEditText?.getText().toString(), surnameEditText?.getText().toString())
            PersonListSingleton.list.add(person)
        } else {
            PersonListSingleton.list[position].name = nameEditText?.getText().toString()
            PersonListSingleton.list[position].surname = surnameEditText?.getText().toString()
        }
        var intent = Intent(this.baseContext, Lesson7Activity::class.java)
        startActivity(intent)
    }

    private fun removeData() {
        PersonListSingleton.list.removeAt(position)
        var intent = Intent(this.baseContext, Lesson7Activity::class.java)
        startActivity(intent)
    }

    private fun changingForCreateNew() {
        isNew = getIntent().getBooleanExtra("isNew", false)
        buttonDel?.visibility = Button.GONE
        (buttonSave?.layoutParams as LinearLayout.LayoutParams).weight = 2.0F
        setData("", "", "")
    }
}
