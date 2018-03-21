package com.whaletail.uklon.test.util.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.whaletail.uklon.test.R
import com.whaletail.uklon.test.model.User
import kotlinx.android.synthetic.main.user_view_layout.view.*

/**
 * @author whaletail on 21.03.18.
 */
class UserView : LinearLayout {

    init {
        View.inflate(context, R.layout.user_view_layout, this)
    }

    var user: User? = null
        set(value) {
            field = value
            tv_user_name.text = value?.name
            tv_user_email.text = value?.email
            val address = value?.address
            val addressText = "${address?.city}, ${address?.street}, ${address?.suite}"
            tv_user_address.text = addressText
            tv_user_zip_code.text = address?.zipcode
            tv_user_phone.text = value?.phone
        }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}