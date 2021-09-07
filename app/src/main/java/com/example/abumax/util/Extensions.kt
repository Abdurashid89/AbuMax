package com.example.abumax.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Vibrator
import android.text.Editable
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.recyclerview.widget.RecyclerView
import com.example.abumax.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputLayout

typealias SingleBlock <T> = (T) -> Unit


fun BottomNavigationView.anim(){
   this.animate()
    NavOptions.Builder()
        .setEnterAnim(R.anim.slide_in_right)
        .setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.slide_in_left)
        .setPopExitAnim(R.anim.slide_out_right)
        .build()
}

@SuppressLint("MissingPermission")
fun vibrate(context: Context) {
    val vibrate = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    vibrate.vibrate(100)
}

fun View.shakeAnim(context: Context, id: Int) {
    this.startAnimation(AnimationUtils.loadAnimation(context, id))
}

fun AppCompatEditText.txt() = this.text.toString().trim()

fun View.visible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.invisible() {
    this.isEnabled = false
    this.visibility = View.INVISIBLE
}

fun View.visible() {
    this.isEnabled = true
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.isEnabled = false
    this.visibility = View.GONE
}

fun RecyclerView.ViewHolder.bindItem(block: View.() -> Unit) = block(itemView)

fun View.fadeIn(duration: Long? = 800L) {
    if (visibility == View.VISIBLE) {
        alpha = 1.0F
        return
    }

    alpha = 0.0F
    visibility = View.VISIBLE
    animate().alpha(1.0F).setDuration(duration!!)
        .setListener(null)
}

fun View.fadeOut(duration: Long? = 800L, isCanceled: () -> Boolean = { false }) {
    if (visibility == View.GONE) {
        return
    }
    alpha = 1.0F
    animate().alpha(0.0F).setDuration(duration!!)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                val canceled = isCanceled.invoke()
                if (canceled.not()) {
                    visibility = View.GONE
                    alpha = 1.0F
                }
            }
        })
}

//fun Fragment.checkPermission(permission: String, granted: () -> Unit) {
//    val mContext = context ?: return
//    val options = Permissions.Options()
//    options.setCreateNewTask(true)
//    Permissions.check(
//        mContext,
//        arrayOf(permission),
//        null,
//        options,
//        object : PermissionHandler() {
//            override fun onGranted() {
//                granted()
//            }
//        })
//}

fun Fragment.addTextWatcher(et: EditText, tvLabel: TextInputLayout, resId: Int) {
    et.addTextChangedListener(object : TextWatcherWrapper() {
        override fun afterTextChanged(s: Editable?) {
            if (s?.isNotEmpty() == true) {
                tvLabel.error = null
                s.toString()
            } else {
                tvLabel.error = getString(resId)
                tvLabel.errorIconDrawable = null
            }
        }
    })
}

fun AppCompatActivity.addTextWatcher(et: EditText, tvLabel: TextInputLayout, resId: Int) {
    et.addTextChangedListener(object : TextWatcherWrapper() {
        override fun afterTextChanged(s: Editable?) {
            if (s?.isNotEmpty() == true) {
                tvLabel.error = null
                s.toString()
            } else {
                //R.string.is_not_empty
                tvLabel.error = getString(resId)
                tvLabel.errorIconDrawable = null
            }
        }
    })
}

@SuppressLint("MissingPermission")
fun Fragment.isNotEmpty(
    et: EditText, v: TextInputLayout? = null, resId: Int, anim: Int
): Boolean {
    return if (et.text.toString().isEmpty()) {
        if (v == null) et.setError(et.context.getString(resId), null)
        else {
            (requireActivity().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(
                100
            )
            et.startAnimation(AnimationUtils.loadAnimation(requireContext(), anim))
            v.error = et.context.getString(resId)
            v.errorIconDrawable = null
        }
        et.requestFocus()
        false
    } else {
        et.error = null
        true
    }
}

@SuppressLint("MissingPermission")
fun AppCompatActivity.isNotEmpty(
    et: EditText, v: TextInputLayout? = null, resId: Int, anim: Int
): Boolean {
    return if (et.text.toString().isEmpty()) {
        if (v == null) et.setError(et.context.getString(resId), null)
        else {
            (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(
                100
            )
            //R.anim.shake
            et.startAnimation(AnimationUtils.loadAnimation(this, anim))
            v.error = et.context.getString(resId)
            v.errorIconDrawable = null
        }
        et.requestFocus()
        false
    } else {
        et.error = null
        true
    }
}

fun Fragment.showToastMessage(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.showToastMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

val AppCompatActivity.TAG get() = this::class.java.simpleName


val Activity.TAG
    get() = this::class.java.simpleName


val Fragment.TAG
    get() = this::class.java.simpleName
