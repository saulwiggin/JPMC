package com.saulwiggin.jpmc.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saulwiggin.jpmc.R
import com.saulwiggin.jpmc.database.DatabaseAlbum
import com.saulwiggin.jpmc.databinding.MainFragmentBinding
import com.saulwiggin.jpmc.utils.LoadingState
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private var viewModelAdapter: AlbumsAdapter? = null
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: MainFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.main_fragment,container,false)
        binding.setLifecycleOwner (viewLifecycleOwner)
        binding.viewmodel = viewModel
        viewModelAdapter = AlbumsAdapter()

        binding.root.findViewById<RecyclerView>(R.id.rvAlbums).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()

    }

    private fun setUpObserver() {
        viewModel.albumsResults.observe(viewLifecycleOwner, Observer<List<DatabaseAlbum>> { album ->
            album?.apply {
                viewModelAdapter?.result = album
            }
        })

        viewModel.loadingState.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                LoadingState.Status.FAILED -> {
                    Toast.makeText(activity, it.msg, Toast.LENGTH_SHORT).show()
                }
                LoadingState.Status.RUNNING -> {

                }
                LoadingState.Status.SUCCESS -> {

                }
            }
        })
    }
}