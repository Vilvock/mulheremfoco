package br.com.app5m.mulheremfoco.ui.fragment.main.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.mulheremfoco.R
import br.com.app5m.mulheremfoco.controler.CompileByCepControl

import br.com.app5m.mulheremfoco.databinding.FragmentHomeBinding
import br.com.app5m.mulheremfoco.helper.Message
import br.com.app5m.mulheremfoco.helper.Preferences
import br.com.app5m.mulheremfoco.helper.RecyclerItemClickListener
import br.com.app5m.mulheremfoco.model.category.CategorySubList
import br.com.app5m.mulheremfoco.model.category.CategorySubListItem
import br.com.app5m.mulheremfoco.ui.activity.BriefiengActivity
import br.com.app5m.mulheremfoco.ui.activity.MainActivity
import br.com.app5m.mulheremfoco.ui.adapter.CategoriesFullAdapter

import br.com.app5m.mulheremfoco.ui.dialog.AtentionMessageDialog
import br.com.app5m.mulheremfoco.ui.dialog.RightMessageDialog
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import kotlinx.android.synthetic.main.fragment_home.*
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {
    private val locationUser: br.com.app5m.mulheremfoco.model.Address =
        br.com.app5m.mulheremfoco.model.Address()

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var geocoder: Geocoder? = null
    var addresses: List<android.location.Address> = emptyList()
    private var preferences: Preferences? = null
    private var isValidZip: Boolean = false
    private var postalCode: String = ""
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private lateinit var alertDialog: AlertDialog
    private lateinit var builder: AlertDialog.Builder

    private var _binding: FragmentHomeBinding? = null
    private lateinit var categoriesAdapter: RecyclerView.Adapter<*>

    private val categoryList = java.util.ArrayList<CategorySubList>()


    private val binding get() = _binding!!





    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onResume() {
        super.onResume()

        if (findNavController().currentDestination?.id == R.id.homeFragment){
//            getLastKnownLocationAndStart()
            configureInitialViews()

            return
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            val intent = Intent(it, BriefiengActivity::class.java)
            it.startActivity(intent)
        }
        createCategories()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        swiperefresh.setOnRefreshListener {
            createCategories()
            swiperefresh.isRefreshing = false

        }

    }

    fun configureInitialViews() {



//        preferences = Preferences(requireContext())

       /* if (preferences?.getLogin() == true) {
            saveAddress()
        }*/


        builder = AlertDialog.Builder(requireContext())
        alertDialog = builder.create()
        clicks()
        configureCategoriesAdapter()


    }


/*
    private fun saveAddress() {
        var user = preferences?.getUserData()
        var userAux= UserItem()
        userAux.id_user = user?.id

        if (user != null) {
            //listendereco
            AddresseControl(requireContext(), object : WSResult {
                fun saveAddressOfPref(){
                    var addressModel = preferences?.getUserLocation()

                    if (addressModel != null) {
                        AddresseControl(requireContext(),   object : WSResult {
                            override fun responseAddress(addressList: List<Address>, type: String) {
                                super.responseAddress(addressList, type)
                                preferences?.clearUserLocation()
                                saveAddress()

                            }

                            override fun error(error: String) {
                                super.error(error)
                                dialogshowAtention(error)
                            }


                        }).saveEndereco(addressModel)
                    }
                }

                override fun responseAddress(addressList: List<Address>, type: String) {
                    super.responseAddress(addressList, type)


                    if (!addressList[0].id.isNullOrEmpty() && preferences?.getUserLocation()?.address.isNullOrEmpty()){
                        preferences?.setUserLocation(addressList[0])

                        adressTextHome.text = addressList[0].address

                    }

                    //cai aqui após o cadastro
                    else if (!preferences?.getUserLocation()?.address.isNullOrEmpty()) {
                        if (preferences?.getUserLocation()?.id.isNullOrEmpty()){

                            saveAddressOfPref()
                        }
                    }
                    else if(addressList[0].rows == "0"){
                        saveAddressOfPref()

                    }

                    if( !preferences?.getUserLocation()?.address.isNullOrEmpty()){
                        if (findNavController().currentDestination?.id == R.id.homeFragment){
                            adressTextHome.text = preferences?.getUserLocation()?.address

                            return
                        }
                    }


                }


                override fun error(error: String) {
                    super.error(error)
                    dialogshowAtention(error)
                }


            }).listenderecoid(userAux)
        }
    }
*/


    @SuppressLint("ClickableViewAccessibility")
    fun clicks() {

    }
    fun goLogin(){
        Message.msg(
            requireContext(), "Fazer Login?",
            "", object : Message.Answer {
                override fun setOnClickListener() {
                    requireActivity().finishAffinity()
                    activity?.let {
                    val intent = Intent(it, MainActivity::class.java)
                        it.startActivity(intent)
                        it.finishAffinity()
                    }

                }
            })
    }
    fun getLastKnownLocationAndStart() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            configureInitialViews()

//            startActivity(Intent(requireContext(), PermissionLocalizationAct::class.java))

            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        } else {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        latitude = location.latitude
                        longitude = location.longitude
                        // use your location object
                        // get latitude , longitude and other info from this

                        geocoder = Geocoder(requireContext(), Locale.getDefault())
                        try {
                            addresses = geocoder!!.getFromLocation(
                                latitude,
                                longitude,
                                1
                            ) // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                            val addressFull = addresses.get(0)
                                .getAddressLine(0)
                            isValidZip = true

//                        Log.d("lastL", "getLastKnownLocation: $addresses")

                            // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                            val city = addresses.get(0).locality
                            val state = addresses.get(0).adminArea
                            val country = addresses.get(0).countryName
                            postalCode = addresses.get(0).postalCode.replace("-", "")

                            val knownName =
                                addresses.get(0).featureName


                            locationUser.latitude = latitude.toString()
                            locationUser.longitude = longitude.toString()
                            locationUser.country = country

//                            fullAddressText.text = addressFull

                            CompileByCepControl.compile(postalCode, object :
                                CompileByCepControl.CepResult {

                                override fun resultCep(cep: br.com.app5m.mulheremfoco.model.Address) {
//                                    Toast.makeText(requireContext(), postalCode, Toast.LENGTH_SHORT).show()
//não consigo resultado com o cep
                                    isValidZip = true

                                    if (cep.cep == null) {
                                        isValidZip = false

//                        cep_et.error = "Por favor, digite um CEP válido."
                                    }
                                    locationUser.city = cep.place
                                    locationUser.address = cep.logradouro
                                    locationUser.state = cep.state
                                    locationUser.cep = cep.cep?.replace("-", "")
                                    locationUser.neighborhood = cep.neighborhood
                                    locationUser.complement = cep.complement

//                                 Log.d("locationUser", cep.logradouro.toString())

                                        if (!preferences?.getUserLocation()?.address.isNullOrEmpty()) {

//                                            adressTextHome.text = preferences?.getUserLocation()?.address
                                        } else {
                                            preferences?.setUserLocation(locationUser)
//                                            adressTextHome.text = preferences?.getUserLocation()?.address

//                                            adressTextHome.text = cep.logradouro

                                        }


                                }


                            })

                            configureInitialViews()


                        } catch (e: IOException) {
                            isValidZip = false

//                        cep_et.error = "Não foi possível achar o CEP."

                            e.printStackTrace()
                            configureInitialViews()

                        }
                    } else {
                        configureInitialViews()
                    }

                }


        }


    }

    fun dialogshowAtention(message: String) {
        val dialog = AtentionMessageDialog(message)

        fragmentManager?.let { it1 -> dialog.show(it1, "BadMessageDialog") }
    }

    fun dialogshowRight(message: String) {
        val dialog = RightMessageDialog(message)

        fragmentManager?.let { it1 -> dialog.show(it1, "BadMessageDialog") }
    }

    fun configureCategoriesAdapter() {
        categoriesAdapter = CategoriesFullAdapter(categoryList, object : RecyclerItemClickListener {
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

        categoriesRv.apply {
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

        childCategories_1.add(CategorySubListItem(
            title = "teste", image = R.drawable.thumbnail1, duration = "00:02 min",

        ))
        childCategories_1.add(CategorySubListItem(
            title = "teste2", image = R.drawable.thumbnail2, duration = "00:10 min",

            ))
        childCategories_1.add(CategorySubListItem(
            title = "teste3", image = R.drawable.thumbnail3, duration = "00:05 min",

            ))
        childCategories_1.add(CategorySubListItem(
            title = "teste4", image = R.drawable.thumbnail4, duration = "00:16 min",

            ))
        childCategories_1.add(CategorySubListItem(
            title = "teste5", image = R.drawable.thumbnail5, duration = "00:30 min",

            ))

        childCategories_2.add(CategorySubListItem(
            title = "teste", image = R.drawable.thumbnail5, duration = "00:02 min",

            ))
        childCategories_2.add(CategorySubListItem(
            title = "teste2", image = R.drawable.thumbnail4, duration = "00:10 min",

            ))
        childCategories_2.add(CategorySubListItem(
            title = "teste3", image = R.drawable.thumbnail3, duration = "00:05 min",

            ))
        childCategories_2.add(CategorySubListItem(
            title = "teste4", image = R.drawable.thumbnail2, duration = "00:16 min",

            ))
        childCategories_2.add(CategorySubListItem(
            title = "teste5", image = R.drawable.thumbnail1, duration = "00:30 min",

            ))
        categorySubList =CategorySubList(

            nome="Abdomen", categories = childCategories_2


        )
        categoryList.add(categorySubList)

        categorySubList=CategorySubList(

            nome="Pernas", categories = childCategories_1


        )
        categoryList.add(categorySubList)

        categorySubList=CategorySubList(

            nome="Ombros", categories = childCategories_1


        )
        categoryList.add(categorySubList)

        categorySubList=CategorySubList(

            nome="Costas", categories = childCategories_2


        )
        categoryList.add(categorySubList)

    }




    override fun onDestroyView() {
        super.onDestroyView()
//        requireActivity().homeAct_appbar.visibility = View.VISIBLE
        _binding = null
    }
}