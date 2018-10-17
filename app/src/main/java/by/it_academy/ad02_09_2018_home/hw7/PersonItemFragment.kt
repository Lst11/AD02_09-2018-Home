package by.it_academy.ad02_09_2018_home.hw7

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import by.it_academy.ad02_09_2018_home.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

class PersonItemFragment : Fragment() {

    var position: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_person_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonSave = view!!.findViewById(R.id.save) as TextView
        buttonSave?.setOnClickListener {
            ExecutorLandOrientation().saveData(position, this.context)
        }

        val buttonDel = view!!.findViewById(R.id.detele) as TextView
        buttonDel?.setOnClickListener {
            ExecutorLandOrientation().removeData(position, this.context)
        }
    }

    fun setData(position: Int) {
        this.position = position
        val name = view!!.findViewById(R.id.nameEditText) as TextView
        name.visibility = TextView.VISIBLE
        name.text = PersonListSingleton.list[position].name
        val surname = view!!.findViewById(R.id.surnameEditText) as TextView
        surname.visibility = TextView.VISIBLE
        surname.text = PersonListSingleton.list[position].surname
        val buttonSave = view!!.findViewById(R.id.save) as Button
        buttonSave.visibility = Button.VISIBLE
        val buttonDel = view!!.findViewById(R.id.detele) as Button
        buttonDel.visibility = Button.VISIBLE
        val progressBar = view!!.findViewById(R.id.progressBar) as ProgressBar
        progressBar?.visibility = ProgressBar.VISIBLE

        var imageView: ImageView = view!!.findViewById(R.id.image) as ImageView
        imageView.visibility = ImageView.VISIBLE


        val options = RequestOptions()
                .centerCrop()
                .error(R.drawable.ic_face_white_24dp)

        Glide.with(this)
                .load(PersonListSingleton.list[position].image)
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

    inner class ExecutorLandOrientation : EditPesonDate {

        override fun saveData(position: Int?, context: Context?) {
            val name = view!!.findViewById(R.id.nameEditText) as TextView
            val surname = view!!.findViewById(R.id.surnameEditText) as TextView
            PersonListSingleton.list[position!!].name = name?.getText().toString()
            PersonListSingleton.list[position!!].surname = surname?.getText().toString()
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
