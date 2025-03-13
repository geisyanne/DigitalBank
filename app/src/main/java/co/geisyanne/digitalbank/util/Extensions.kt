package co.geisyanne.digitalbank.util

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import co.geisyanne.digitalbank.R

fun Fragment.initToolbar(toolbar: Toolbar, homesAsUpEnabled: Boolean = true) {
    (activity as AppCompatActivity).setSupportActionBar(toolbar)
    (activity as AppCompatActivity).title = ""
    (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(homesAsUpEnabled)
    (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
    toolbar.setNavigationOnClickListener{ activity?.onBackPressed() }


}