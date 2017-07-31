package mymap.map.com.webserviceretrofit.firstkotlinapp

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.layout_list_artist.view.*
import mymap.map.com.webserviceretrofit.R

/**
 * Created by Belal on 5/21/2017.
 */
class ArtistList(private val context: Activity, internal var artists: List<Artist>) : ArrayAdapter<Artist>(context, R.layout.layout_list_artist, artists) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem = inflater.inflate(R.layout.layout_list_artist, null, true)

        val textViewName = listViewItem.textViewName
        val textViewGenre = listViewItem.textViewGenre

        val artist = artists[position]
        textViewName.text = artist.name
        textViewGenre.text = artist.genre

        return listViewItem
    }
}
