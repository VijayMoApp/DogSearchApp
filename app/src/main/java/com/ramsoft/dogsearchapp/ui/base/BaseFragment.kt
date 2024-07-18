package com.ramsoft.dogsearchapp.ui.base

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB:ViewBinding>:Fragment() {

    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding!!

    private lateinit var mProgressDialog: ProgressDialog

    abstract fun inflatesBinding( inflater: LayoutInflater,
                                  container: ViewGroup?) : VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = inflatesBinding(inflater,container)
        mProgressDialog = ProgressDialog(activity)
        mProgressDialog.setMessage("Loading")
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun showProgressDialog(){
        if (!mProgressDialog.isShowing){
            mProgressDialog.show()
            mProgressDialog.setCancelable(false)
            mProgressDialog.setCanceledOnTouchOutside(false)

        }
    }

    fun dismissProgressDialog(){
        if (mProgressDialog.isShowing){
            mProgressDialog.dismiss()
        }
    }
}