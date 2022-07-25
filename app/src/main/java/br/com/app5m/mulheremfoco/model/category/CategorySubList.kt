package br.com.app5m.mulheremfoco.model.category

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class CategorySubList(
    var id: Int?=null,
    var nome: String?=null,
    var rows: Int?=null,
    var status: Int?=null,
    var token: String?=null,
    var categories : List<CategorySubListItem>?=null



    ): Parcelable {

}