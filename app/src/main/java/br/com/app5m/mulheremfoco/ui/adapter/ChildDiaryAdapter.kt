package br.com.app5m.mulheremfoco.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.mulheremfoco.R
import br.com.app5m.mulheremfoco.helper.RecyclerItemClickListener
import br.com.app5m.mulheremfoco.model.category.CategorySubListItem
import com.bumptech.glide.Glide


class ChildDiaryAdapter (private val categoryList: List<CategorySubListItem>, val clickListener: RecyclerItemClickListener, context: Context):
    RecyclerView.Adapter<ChildDiaryAdapter.CategoriesChildHolder>() {

    val context = context
    private val viewPool = RecyclerView.RecycledViewPool()
    private lateinit var categoriesAdapter: RecyclerView.Adapter<*>

    private val childcategoryList = java.util.ArrayList<CategorySubListItem>()

    private var screenWidth = 0

    class CategoriesChildHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textAndPhoto: LinearLayout = itemView.findViewById(R.id.textAndPhoto)
        val onlyPhoto: LinearLayout = itemView.findViewById(R.id.onlyPhoto)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesChildHolder {
        /*   val displayMetrics = DisplayMetrics()
           (context as HomeActivity).windowManager.getDefaultDisplay().getMetrics(displayMetrics)
           screenWidth = displayMetrics.widthPixels*/

        return CategoriesChildHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_child_diary, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoriesChildHolder, position: Int) {
        val categoryItem = categoryList[position]
/*        val itemWidth = screenWidth / 5.33
        val lp = holder.frameLayout.layoutParams
        lp.height = lp.height
        lp.width = itemWidth.toInt()*/

        val id=   categoryItem.id
        val guessStr: String = id.toString()
        val theGuess = guessStr.toInt()
        val valor = theGuess % 2
        if (valor == 0) {
            holder.textAndPhoto.visibility = View.GONE
            holder.onlyPhoto.visibility = View.VISIBLE

            //imprima é par
        } else if (valor == 1) {
            holder.textAndPhoto.visibility = View.VISIBLE
            holder.onlyPhoto.visibility = View.GONE
            // imprima é impar
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