package br.com.app5m.mulheremfoco.controler.webservice

import br.com.app5m.mulheremfoco.model.Address
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface WebService {

    //cep
    @GET("{CEP}/json/")
    fun getAddressByCEP(@Path("CEP") CEP: String?): Call<Address>


}