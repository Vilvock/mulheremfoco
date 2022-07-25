package br.com.app5m.mulheremfoco.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.mulheremfoco.R
import br.com.app5m.mulheremfoco.helper.RecyclerItemClickListener
import br.com.app5m.mulheremfoco.model.category.CategorySubListItem
import com.bumptech.glide.Glide


class ChildCategoriesAdapter (private val categoryList: List<CategorySubListItem>, val clickListener: RecyclerItemClickListener, context: Context):
    RecyclerView.Adapter<ChildCategoriesAdapter.CategoriesChildHolder>() {

    val context = context
    private val viewPool = RecyclerView.RecycledViewPool()
    private lateinit var categoriesAdapter: RecyclerView.Adapter<*>

    private val childcategoryList = java.util.ArrayList<CategorySubListItem>()

    private var screenWidth = 0

    class CategoriesChildHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.title)
        val image: ImageView = itemView.findViewById(R.id.image)
        val constraintSalvation: ConstraintLayout = itemView.findViewById(R.id.constraintSalvation)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesChildHolder {
        /*   val displayMetrics = DisplayMetrics()
           (context as HomeActivity).windowManager.getDefaultDisplay().getMetrics(displayMetrics)
           screenWidth = displayMetrics.widthPixels*/

        return CategoriesChildHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_child_categories, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoriesChildHolder, position: Int) {
        val categoryItem = categoryList[position]
/*        val itemWidth = screenWidth / 5.33
        val lp = holder.frameLayout.layoutParams
        lp.height = lp.height
        lp.width = itemWidth.toInt()*/


        Glide
            .with(context)
            .load(categoryItem.image)
            .error(R.drawable.logo_mulher_ef)
            .centerCrop()
            .into(holder.image)


        holder.name.text = categoryItem.title
        holder.constraintSalvation.setOnClickListener{ view ->
            view.findNavController().navigate(R.id.action_homeFragment_to_trainingDetailFragment)
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