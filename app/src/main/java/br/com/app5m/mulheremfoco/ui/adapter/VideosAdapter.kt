package br.com.app5m.mulheremfoco.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.mulheremfoco.R
import br.com.app5m.mulheremfoco.helper.RecyclerItemClickListener
import br.com.app5m.mulheremfoco.model.category.CategorySubListItem


class VideosAdapter (private val categoryList: List<CategorySubListItem>, val clickListener: RecyclerItemClickListener, context: Context):
    RecyclerView.Adapter<VideosAdapter.CategoriesHoriHolder>() {

    val context = context
    private val viewPool = RecyclerView.RecycledViewPool()
    private lateinit var categoriesAdapter: RecyclerView.Adapter<*>

    private val childcategoryList = java.util.ArrayList<CategorySubListItem>()

    private var screenWidth = 0

    class CategoriesHoriHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val cardView: CardView = itemView.findViewById(R.id.vamoDale)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHoriHolder {
        /*   val displayMetrics = DisplayMetrics()
           (context as HomeActivity).windowManager.getDefaultDisplay().getMetrics(displayMetrics)
           screenWidth = displayMetrics.widthPixels*/

        return CategoriesHoriHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_video, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoriesHoriHolder, position: Int) {
        val categoryItem = categoryList[position]
/*        val itemWidth = screenWidth / 5.33
        val lp = holder.frameLayout.layoutParams
        lp.height = lp.height
        lp.width = itemWidth.toInt()*/


      /*  Glide
            .with(context)
            .load(categoryItem.image)
            .error(R.drawable.logo_mulher_ef)
            .centerCrop()
            .into(holder.image)


        holder.name.text = categoryItem.title*/
        holder.cardView.setOnClickListener{ view ->
            view.findNavController().navigate(R.id.action_trainingDetailFragment_self)
        }
        holder.itemView.setOnClickListener { clickListener.onClickListenerCategoriesAdapter(categoryItem) }

    }




    override fun getItemId(position: Int): Long {
        val id = categoryList.get(position).id
        if (id != null) {
            return id.toLong()
        }else return 0
    }
    override fun getItemCount(): Int {
        return categoryList.size
    }

}