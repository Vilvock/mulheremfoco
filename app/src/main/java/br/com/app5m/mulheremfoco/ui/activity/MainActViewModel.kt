package br.com.app5m.mulheremfoco.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.widget.FrameLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import egolabsapps.basicodemine.videolayout.VideoLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActViewModel:ViewModel() {
    @SuppressLint("StaticFieldLeak")
    lateinit var  frameLayout:FrameLayout
    @SuppressLint("StaticFieldLeak")
    lateinit var  videoLayout:VideoLayout

    var isPlaying =  MutableLiveData<Boolean>()


    suspend fun startVideoLayout(fM:FrameLayout,videoLayout:VideoLayout,context: Context) = coroutineScope {  // this: CoroutineScope
        launch(Dispatchers.IO) {
            try {
                this@MainActViewModel.frameLayout = fM
                 this@MainActViewModel.videoLayout= videoLayout


                // Call some suspend functions.
                val urlVideoBg="https://vod-progressive.akamaized.net/exp=1656576859~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F1725%2F17%2F433625737%2F1887755183.mp4~hmac=76cc76c74a54c59c71e1a5a0840ce8b11a42be17dfa1feb65f1f73b46bac0f56/vimeo-prod-skyfire-std-us/01/1725/17/433625737/1887755183.mp4"
                val pathVideBg =  "videos/intro_mulher_em_foco.mp4"

                videoLayout.setGravity(VideoLayout.VGravity.centerCrop)
                videoLayout.setIsLoop(false)
                setPath(pathVideBg)
//                setDataSource(urlVideoBg)
                videoLayout.setSound(false)
                fM.addView(videoLayout)




            }
            catch (e:Exception){
                e
            }
            finally {
                // This line might execute after Lifecycle is DESTROYED.

            }
        }
    }


    private  fun setPath(urlVideoBackgroud:String)  {
            try {
                videoLayout.setPathOrUrl( urlVideoBackgroud) // could be any video url

            }catch (exc:Exception){

            }

    }


    private  fun setDataSource(urlVideoBackgroud:String)  {
            try {
                videoLayout.setPathOrUrl(urlVideoBackgroud) // could be any video url

            }catch (exc:Exception){

            }

    }



}