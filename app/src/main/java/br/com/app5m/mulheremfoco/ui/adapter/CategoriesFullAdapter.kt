package br.com.app5m.mulheremfoco.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.mulheremfoco.R
import br.com.app5m.mulheremfoco.helper.DividerItemDecorationLastExcluded
import br.com.app5m.mulheremfoco.helper.RecyclerItemClickListener
import br.com.app5m.mulheremfoco.model.category.CategorySubList
import br.com.app5m.mulheremfoco.model.category.CategorySubListItem


class CategoriesFullAdapter (private val categoryList: List<CategorySubList>, val clickListener: RecyclerItemClickListener, context: Context):
    RecyclerView.Adapter<CategoriesFullAdapter.CategoriesHoriHolder>() {

    val context = context
    private val viewPool = RecyclerView.RecycledViewPool()
    private lateinit var categoriesAdapter: RecyclerView.Adapter<*>

    private val listCategorySubListItem = java.util.ArrayList<CategorySubListItem>()

    private var screenWidth = 0

    class CategoriesHoriHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.nome)
        val categoriesChieldRv: RecyclerView = itemView.findViewById(R.id.categoriesChieldRv)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHoriHolder {
     /*   val displayMetrics = DisplayMetrics()
        (context as HomeActivity).windowManager.getDefaultDisplay().getMetrics(displayMetrics)
        screenWidth = displayMetrics.widthPixels*/

        return CategoriesHoriHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_categories_full_adapter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoriesHoriHolder, position: Int) {
        val categoryItem = categoryList[position]
/*        val itemWidth = screenWidth / 5.33
        val lp = holder.frameLayout.layoutParams
        lp.height = lp.height
        lp.width = itemWidth.toInt()*/

        holder.name.text = categoryItem.nome



//        holder.itemView.setOnClickListener { clickListener.onClickListenerCategoriesAdapter(categoryItem) }
//        createCategories(categoryItem)
        configureCategoriesAdapter(holder,position,categoryItem)
    }

    fun configureCategoriesAdapter(
        holder: CategoriesHoriHolder,
        position: Int,
        categoryItem: CategorySubList
    ) {

        listCategorySubListItem.clear()
        categoryItem.categories?.let { listCategorySubListItem.addAll(it) }


        categoriesAdapter = ChildCategoriesAdapter(listCategorySubListItem, object : RecyclerItemClickListener {

            override fun onClickListenerCategoriesAdapter(categorySubList: CategorySubListItem) {
                super.onClickListenerCategoriesAdapter(categorySubList)



                   /* holder.itemView.setOnClickListener{ view ->
                         view.findNavController().navigate(R.id.action_homeFragment_to_trainingDetailFragment)
                     }*/

                /*   var bundle = bundleOf(
           "categorySubListArgs" to categorySubList
       )
       findNavController().navigate(
           R.id.action_homeFragment_to_restaurantDetailFragment,
           bundle
       )
*/
            }

        }, context)


        val vmProduct = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

       holder.categoriesChieldRv.apply {
            setHasFixedSize(true)
            setItemViewCacheSize(512)
//            categoriesAdapter.setHasStableIds(true)





           var decor = holder.categoriesChieldRv.itemDecorationCount
           if (decor >= 1) {
               holder.categoriesChieldRv.removeItemDecorationAt(0)
           }


           this.addItemDecoration(
               @SuppressLint("UseCompatLoadingForDrawables")
               object : DividerItemDecorationLastExcluded( resources.getDrawable(
                   R.drawable.decor_layout_no_bg_hori,
                   null
               )){

               }
           )



           layoutManager = vmProduct
            adapter = categoriesAdapter


        }

    }

    fun createCategories( categoryItem : CategorySubList) {


        categoryItem.categories?.let {
            listCategorySubListItem.clear()
            listCategorySubListItem.addAll(it)


        }
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