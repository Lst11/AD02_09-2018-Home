package by.it_academy.ad02_09_2018_home.hw7

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import by.it_academy.ad02_09_2018_home.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target


class PersonEditActivity : FragmentActivity() {

    private var nameEditText: EditText? = null
    private var surnameEditText: EditText? = null
    private var buttonSave: Button? = null
    private var buttonDel: Button? = null
    private var progressBar: ProgressBar? = null
    private var imageView: ImageView? = null

    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_edit)
        initViews()
        getIncomingIntent()

        buttonSave?.setOnClickListener {
            ExecutorPortraitOrientation().saveData(position, this.baseContext)
        }

        buttonDel?.setOnClickListener {
            ExecutorPortraitOrientation().removeData(position, this.baseContext)
        }
    }


    private fun getIncomingIntent() {
        if (getIntent().hasExtra("position")) {
            position = getIntent().getIntExtra("position", 0)
            val person: Person? = PersonListSingleton.list[position]
            setData(person?.image, person?.name, person?.surname)
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
        buttonSave?.visibility = Button.VISIBLE
        buttonDel?.visibility = Button.VISIBLE
        nameEditText?.visibility = EditText.VISIBLE
        surnameEditText?.visibility = EditText.VISIBLE
        imageView?.visibility = ImageView.VISIBLE
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


    inner class ExecutorPortraitOrientation : EditPesonDate {

        override fun saveData(position: Int?, context: Context?) {
            if (position != null) {
                PersonListSingleton.list[position!!].name = nameEditText?.text.toString()
                PersonListSingleton.list[position!!].surname = surnameEditText?.text.toString()
            }
            var intent = Intent(context, Lesson7Activity::class.java)
            startActivity(intent)
        }

        override fun removeData(position: Int?, context: Context?) {
            position?.let { PersonListSingleton.list.removeAt(it) }
            var intent = Intent(context, Lesson7Activity::class.java)
            startActivity(intent)
        }
    }
}
