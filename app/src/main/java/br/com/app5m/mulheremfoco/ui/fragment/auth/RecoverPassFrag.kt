package br.com.app5m.mulheremfoco.ui.fragment.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import br.com.app5m.mulheremfoco.databinding.FragmentRecoverPassBinding

import br.com.app5m.mulheremfoco.helper.ValidationHelper

import br.com.app5m.mulheremfoco.ui.dialog.AtentionMessageDialog
import br.com.app5m.mulheremfoco.ui.dialog.RightMessageDialog


class RecoverPassFrag : Fragment()


{
    private var _binding: FragmentRecoverPassBinding? = null
    private val binding get() = _binding!!

    private lateinit var builder: AlertDialog.Builder

    private lateinit var alertDialog: AlertDialog

//    private lateinit var preferences: Preferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecoverPassBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        preferences = Preferences(requireContext())

        builder = AlertDialog.Builder(requireContext())
        alertDialog = builder.create()

        clicks()


    }
    private fun abrirTermos() {
 /*       val intent = Intent(view?.context, TermsAct::class.java)
        view?.getContext()?.startActivity(intent)*/
    }
    fun clicks() {

     /*   termsTv.setOnClickListener {

            abrirTermos()
        }*/


        binding.sendBt.setOnClickListener {
            dialogshowRight("As instruções para alteração de senha foram enviadas para o seu e-mail.")
            /*         if (!validation()) return@setOnClickListener

                     val user = UserItem()
                     user.email = binding.email.text.toString()


                     UserControl(requireContext(),   object : WSResult {
                         override fun responseUser(user: User, type: String) {
                             super.responseUser(user, type)


                             val preferences = Preferences(
                                 requireContext()
                             )
                             if (user[0].status =="01"){
                                 user[0].msg?.let { it1 -> dialogshowRight(it1) }

                             }else{
                                 user[0].msg?.let { it1 -> dialogshowAtention(it1) }

                             }
                         }


                         override fun error(error: String) {
                             super.error(error)
                             dialogshowAtention(error)
                         }


                     }).recuperarSenha(user)*/

        }

    }
    private fun validation(): Boolean {
        if (!ValidationHelper.email(binding.email, requireContext())) return false
        return true
    }
    fun dialogshowAtention(message: String) {
        val dialog = AtentionMessageDialog(message)

        fragmentManager?.let { it1 -> dialog.show(it1, "BadMessageDialog") }
    }

    fun dialogshowRight(message: String) {
        val dialog = RightMessageDialog(message)

        fragmentManager?.let { it1 -> dialog.show(it1, "BadMessageDialog") }
    }

    //to avoid memory leaks
    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}