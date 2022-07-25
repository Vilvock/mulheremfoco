package br.com.app5m.mulheremfoco.model.video

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class VideoSubList(
    var id: Int?=null,
    var rows: Int?=null,
    var status: Int?=null,
    var token: String?=null,

    var title: String?=null,
    var subTitle: String?=null,
    var image: String?=null,
    var videoUrl : String?=null,
    var duration : String?=null,
    var objective : String?=null,
    var description : String?=null,

    ): Parcelable {

}