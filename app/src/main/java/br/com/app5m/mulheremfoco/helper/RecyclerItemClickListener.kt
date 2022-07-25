package br.com.app5m.mulheremfoco.helper

import br.com.app5m.mulheremfoco.model.category.CategorySubList
import br.com.app5m.mulheremfoco.model.category.CategorySubListItem


interface RecyclerItemClickListener {

    fun onClickListenerCategoriesAdapter(categorySubList: CategorySubListItem){
        //optional body
    }

}