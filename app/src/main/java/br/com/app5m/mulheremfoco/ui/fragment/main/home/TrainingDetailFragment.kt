package br.com.app5m.mulheremfoco.ui.fragment.main.home

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.mulheremfoco.R
import br.com.app5m.mulheremfoco.helper.RecyclerItemClickListener
import br.com.app5m.mulheremfoco.model.category.CategorySubList
import br.com.app5m.mulheremfoco.model.category.CategorySubListItem
import br.com.app5m.mulheremfoco.ui.adapter.CategoriesFullAdapter
import br.com.app5m.mulheremfoco.ui.adapter.ChildCategoriesAdapter
import br.com.app5m.mulheremfoco.ui.adapter.VideosAdapter
import com.halilibo.bvpkotlin.BetterVideoPlayer
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_training_detail.*


class TrainingDetailFragment : Fragment() {
 lateinit  var betterVideoPlayer: BetterVideoPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_training_detail, container, false)
    }
    private lateinit var categoriesAdapter: RecyclerView.Adapter<*>

    private val categoryList = java.util.ArrayList<CategorySubListItem>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        createCategories()
        configureCategoriesAdapter()


         betterVideoPlayer = better_video2


        betterVideoPlayer.setSource(
            Uri.parse( "https://github.com/Capitaoneo3/GymApp1/blob/7aa3095ccd7c8775f52e73960ad42e88b7f002f9/app/src/main/res/raw/woman_on_gym.mp4?raw=true")
        )

        try {
            betterVideoPlayer.removeCaptions()
            betterVideoPlayer.setAutoPlay(true)
            midiaButton.setImageResource(R.drawable.ic_baseline_pause_24)

            betterVideoPlayer.start()

        }catch (e:Exception){
            e
        }




        midiaButton.setOnClickListener {
            try {
                if ( betterVideoPlayer.isPlaying()){
                    pause()
                    midiaButton.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                    betterVideoPlayer.pause()

                }else{
                    play()
                    midiaButton.setImageResource(R.drawable.ic_baseline_pause_24)


                    betterVideoPlayer.start()


                }
            }catch (e:Exception){
                e

            }

        }


   /*     jzvdStd.

        {
            midiaButton.setImageResource(R.drawable.ic_baseline_pause_24)

        }*/


    }

    fun configureCategoriesAdapter() {
        categoriesAdapter = VideosAdapter(categoryList, object : RecyclerItemClickListener {
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

        videosRv.apply {
            setHasFixedSize(true)
            setItemViewCacheSize(512)
            categoriesAdapter.setHasStableIds(true)


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
//        buscaEstab("")

        var categories = ArrayList<CategorySubList>()
        var childCategories_1 = ArrayList<CategorySubListItem>()

        childCategories_1.add(
            CategorySubListItem(
            title = "teste", image = R.drawable.thumbnail1, duration = "00:02 min",

            )
        )
        childCategories_1.add(
            CategorySubListItem(
            title = "teste2", image = R.drawable.thumbnail2, duration = "00:10 min",

            )
        )
        childCategories_1.add(
            CategorySubListItem(
            title = "teste3", image = R.drawable.thumbnail3, duration = "00:05 min",

            )
        )
        childCategories_1.add(
            CategorySubListItem(
            title = "teste4", image = R.drawable.thumbnail4, duration = "00:16 min",

            )
        )
        childCategories_1.add(
            CategorySubListItem(
            title = "teste5", image = R.drawable.thumbnail5, duration = "00:30 min",

            )
        )


        categoryList.addAll(childCategories_1)
    }


    override fun onDestroy() {
        super.onDestroy()
        betterVideoPlayer.release()

    }

    override fun onPause() {
        super.onPause()
        pause()
    }

    override fun onResume() {
        super.onResume()
      play()
    }

  private  fun play(){
    if  (::betterVideoPlayer.isInitialized){
        betterVideoPlayer.start()
        betterVideoPlayer.hideControls()


    }
    }

  private  fun pause(){

      if  (::betterVideoPlayer.isInitialized) {
          betterVideoPlayer.pause()
          betterVideoPlayer.hideControls()

      }


    }
}