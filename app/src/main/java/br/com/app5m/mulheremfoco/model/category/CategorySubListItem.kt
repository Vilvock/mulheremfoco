package br.com.app5m.mulheremfoco.model.category

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class CategorySubListItem(
    var id: Int?=null,
    var title: String?=null,
    var image: Int? =null,
    var videoUrl: String?=null,
    var duration: String?=null,
    var objective: String?=null,
    var description: String?=null,




    ): Parcelable {

}