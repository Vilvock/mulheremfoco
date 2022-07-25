package br.com.app5m.mulheremfoco.ui.fragment.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.mulheremfoco.R
import br.com.app5m.mulheremfoco.databinding.AdapterFullDiaryBinding
import br.com.app5m.mulheremfoco.helper.RecyclerItemClickListener
import br.com.app5m.mulheremfoco.model.category.CategorySubList
import br.com.app5m.mulheremfoco.model.category.CategorySubListItem
import br.com.app5m.mulheremfoco.ui.adapter.CategoriesFullAdapter
import br.com.app5m.mulheremfoco.ui.adapter.DiaryFullAdapter
import kotlinx.android.synthetic.main.fragment_diary.*


class DiaryFragment : Fragment() {

    private val categoryList = java.util.ArrayList<CategorySubList>()
    private lateinit var categoriesAdapter: RecyclerView.Adapter<*>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createCategories()
        configureCategoriesAdapter()
    }
    fun configureCategoriesAdapter() {
        categoriesAdapter = DiaryFullAdapter(categoryList, object : RecyclerItemClickListener {
            /*      override fun onClickListenerCategoriesAdapter(categorySubList: CategorySubList) {
                      super.onClickListenerCategoriesAdapter(categorySubList)

                   *//*   var bundle = bundleOf(
                    "categorySubListArgs" to categorySubList
                )
                findNavController().navigate(
                    R.id.action_homeFragment_to_restaurantDetailFragment,
                    bundle
                )
*//*
            }*/

        }, requireContext())


        val vmProduct = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        diaryRv.apply {
            setHasFixedSize(true)
            setItemViewCacheSize(512)
//            categoriesAdapter.setHasStableIds(true)


            val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            itemDecoration.setDrawable(
                resources.getDrawable(
                    R.drawable.decor_layout_no_bg_vert,
                    null
                )
            )
            var decor = this.itemDecorationCount
            if (decor>=1){
                this.removeItemDecorationAt(0)
            }

            /*this.addItemDecoration(
                @SuppressLint("UseCompatLoadingForDrawables")
                object : CustomDividerItemDecorator( resources.getDrawable(
                    R.drawable.decor_layout_bg_vert3,
                    null
                )){

                }
            )*/




            layoutManager = vmProduct
            adapter = categoriesAdapter


        }

    }

    fun createCategories() {

        var categorySubList = CategorySubList()
        categoryList.clear()

        var childCategories_1 = ArrayList<CategorySubListItem>()
        var childCategories_2 = ArrayList<CategorySubListItem>()
        childCategories_1.clear()
        childCategories_2.clear()

        childCategories_1.add(
            CategorySubListItem(
            id = 1
            )
        )


        childCategories_2.add(
            CategorySubListItem(
                id = 2

            )
        )

        categorySubList = CategorySubList(

            nome="Abdomen", categories = childCategories_2


        )
        categoryList.add(categorySubList)

        categorySubList= CategorySubList(

            nome="Pernas", categories = childCategories_1


        )
        categoryList.add(categorySubList)

        categorySubList= CategorySubList(

            nome="Ombros", categories = childCategories_1


        )
        categoryList.add(categorySubList)

        categorySubList= CategorySubList(

            nome="Costas", categories = childCategories_2


        )
        categoryList.add(categorySubList)

    }

}