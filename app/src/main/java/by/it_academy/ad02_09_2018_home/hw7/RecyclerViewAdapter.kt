package by.it_academy.ad02_09_2018_home.hw7

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import by.it_academy.ad02_09_2018_home.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerViewAdapter.Holder>() {
    private var context: Context? = null
    private var listData: List<Person> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    constructor(context: Context?) : this() {
        this.listData = PersonListSingleton.list
        this.context = context
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): Holder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_person, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = listData.size

    fun filterList(filteredList: ArrayList<Person>) {
        listData = filteredList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val person: Person? = listData[position]
        holder.progressBar.visibility = ProgressBar.VISIBLE

        val options = RequestOptions()
                .circleCrop()
                .error(R.drawable.ic_face_white_24dp)

        if (context != null) {
            Glide.with(context!!)
                    .load(person?.image)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                            holder.progressBar.visibility = ProgressBar.GONE
                            return false
                        }

                        override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                            holder.progressBar.visibility = ProgressBar.GONE
                            return false
                        }
                    })
                    .apply(options)
                    .into(holder.imageView)
        }
        holder.nameTextView.setText(person?.name ?: "")
        holder.surnameTextView.setText(person?.surname ?: "")
        holder.parentLayout.setOnClickListener {
            onClick(position)
        }
    }

    private fun onClick(position: Int) {
        var number: Int = 0
        PersonListSingleton.list.forEachIndexed { index, person ->
            if (person.equals(this.listData[position])) {
                number = index
            }
        }

        val intent: Intent = Intent(context, PersonEditActivity::class.java)
        intent.putExtra("position", number)
        Log.e("AAA", "put extra $number")
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context?.startActivity(intent)
    }

    inner class Holder : RecyclerView.ViewHolder {
        var parentLayout: RelativeLayout
        var imageView: ImageView
        var nameTextView: TextView
        var surnameTextView: TextView
        var progressBar: ProgressBar

        constructor(view: View) : super(view) {
            progressBar = view.findViewById(R.id.progressBar)
            parentLayout = view.findViewById(R.id.parentLayout)
            imageView = view.findViewById(R.id.image)
            nameTextView = view.findViewById(R.id.name)
            surnameTextView = view.findViewById(R.id.surname)
        }
    }
}