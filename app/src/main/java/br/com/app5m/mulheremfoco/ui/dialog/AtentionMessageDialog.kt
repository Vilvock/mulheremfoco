package br.com.app5m.mulheremfoco.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

import br.com.app5m.mulheremfoco.R
import br.com.app5m.mulheremfoco.helper.DialogClickListener
import kotlinx.android.synthetic.main.dialog_atention_message.*


class AtentionMessageDialog : DialogFragment {
    var message: String
    var clickListener: DialogClickListener? = null
    var bundle: Bundle? = null

    constructor(message: String, clickListener: DialogClickListener) : super() {
        this.message = message
        this.clickListener = clickListener
    }
    constructor(message: String) : super() {
        this.message = message

    }

    constructor(message: String,bundle: Bundle) : super() {
        this.message = message
        this.bundle = bundle

    }

   

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogNoBackground)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_atention_message, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        coseBt.setOnClickListener {
            clickListener?.onClickListener()


            dialog?.dismiss()

        }


    }

    override fun onDestroy() {
        super.onDestroy()

    }
}