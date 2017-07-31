/*
 * Copyright (c) 2016 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package mymap.map.com.webserviceretrofit.test2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.ShareActionProvider
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import mymap.map.com.webserviceretrofit.R

class DetailActivityKotlin: Activity() {

  private val IMAGE_URL_BASE = "http://covers.openlibrary.org/b/id/"
  internal var mImageURL = ""
  internal var mShareActionProvider: ShareActionProvider? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_detail)

    if (null != actionBar) {
      actionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    val imageView = img_cover

    val coverId = this.intent.extras.getString("coverID")

    val len = coverId?.length ?: 0

    if (len > 0) {
      mImageURL = IMAGE_URL_BASE + coverId + "-L.jpg"

      Picasso.with(this).load(mImageURL).placeholder(R.drawable.img_books_loading).into(imageView)
    }
  }

  private fun setShareIntent() {

    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"
    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Book Recommendation!")
    shareIntent.putExtra(Intent.EXTRA_TEXT, mImageURL)

    mShareActionProvider?.setShareIntent(shareIntent)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {

    menuInflater.inflate(R.menu.main, menu)

    val shareItem = menu.findItem(R.id.menu_item_share)

    if (shareItem != null) {
      mShareActionProvider = shareItem!!.actionProvider as ShareActionProvider
    }

    setShareIntent()

    return true
  }
}