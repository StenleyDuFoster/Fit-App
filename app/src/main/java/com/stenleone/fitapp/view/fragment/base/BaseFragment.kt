package com.stenleone.fitapp.view.fragment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.stenleone.fitapp.model.view_model.base.BaseViewModel
import kotlinx.android.synthetic.main.app_bar.*

abstract class BaseFragment(val layView: Int) : Fragment() {

    protected lateinit var navController: NavController
    protected lateinit var viewModel: BaseViewModel

    abstract fun initViewModel()
    abstract fun initAfterViewCreated()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layView, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        initViewModel()
        initAfterViewCreated()
        super.onViewCreated(view, savedInstanceState)
    }
}