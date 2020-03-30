package com.android.themoviedb.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.android.themoviedb.R
import com.android.themoviedb.base.BaseActivity
import com.android.themoviedb.base.BaseRecyclerView
import com.android.themoviedb.model.MovieDetailAdapter
import com.android.themoviedb.ui.favorite.adapter.FavoriteAdapter
import com.android.themoviedb.ui.favorite.adapter.FavoriteViewHolder
import com.android.themoviedb.viewmodel.DaoViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.layout_bottom_sheet_confirmation.view.*
import kotlinx.android.synthetic.main.layout_toolbar_default.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteActivity : BaseActivity(), FavoriteViewHolder.SetItemListener {

    private val viewModelDao by viewModel<DaoViewModel>()

    private val resultList = mutableListOf<MovieDetailAdapter>()
    private val adapterFavorite by lazy { FavoriteAdapter(resultList, this) }

    override fun getLayoutResId(): Int = R.layout.activity_favorite

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        setupToolbar()
        setupRecyclerView()
        setContentVisibility(true)
    }

    override fun initEvent() {
        super.initEvent()
        setupOnClickToolbar()
    }

    private fun setupToolbar() {
        ivToolbarBack.visibility = View.VISIBLE
        tvToolbarInfoTitle.text = getString(R.string.label_favorite)
    }

    private fun setupRecyclerView() {
        with(rvFavorite) {
            initRecyclerView(adapterFavorite, BaseRecyclerView.LayoutManager.VERTICAL)
        }
    }

    private fun setupOnClickToolbar() {
        ivToolbarBack.setOnClickListener { finish() }
    }

    private fun addData(data: List<MovieDetailAdapter>) {
        resultList.clear()
        resultList.addAll(data)
        adapterFavorite.notifyDataSetChanged()
    }

    private fun setContentVisibility(visible: Boolean) {
        when (visible) {
            true -> {
                sectionEmptyState.visibility = View.GONE
                nestedHome.visibility = View.VISIBLE
            }
            else -> {
                nestedHome.visibility = View.GONE
                sectionEmptyState.visibility = View.VISIBLE
            }
        }
    }

    override fun onClickRemove(items: MovieDetailAdapter) {
        val dialog = BottomSheetDialog(this)
        val dialogView: View = layoutInflater.inflate(
            R.layout.layout_bottom_sheet_confirmation,
            null
        )

        with(dialogView) {
            this.tvBodyMessageConfirmDialog.text = getString(R.string.label_confirmation_remove)
            this.ivCloseConfirmDialog.setOnClickListener { dialog.dismiss() }
            this.btnCancelConfirmDialog.setOnClickListener { dialog.dismiss() }
            this.btnOkConfirmDialog.setOnClickListener {
                items.id?.let { viewModelDao.deleteItem(it) }
                dialog.dismiss()
            }
            dialog.apply {
                setContentView(dialogView)
                show()
            }
        }
    }

    override fun observeData() {
        super.observeData()
        viewModelDao.getAllData().observe(this, Observer {
            when (it.isNotEmpty()) {
                true -> addData(it)
                else -> onDataNotFound()
            }
        })
    }

    override fun onDataNotFound() {
        setContentVisibility(false)
    }
}
