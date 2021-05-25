package com.sosnowskydevelop.tourroutessaratovregion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sosnowskydevelop.tourroutessaratovregion.databinding.FragmentAnnotationBinding
import com.sosnowskydevelop.tourroutessaratovregion.utilities.InjectorUtils
import com.sosnowskydevelop.tourroutessaratovregion.viewmodels.AnnotationViewModel

class AnnotationFragment : Fragment() {
    private lateinit var fragmentAnnotationBinding: FragmentAnnotationBinding

    private val annotationViewModel: AnnotationViewModel by viewModels {
        InjectorUtils.provideAnnotationViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentAnnotationBinding =
            FragmentAnnotationBinding.inflate(inflater, container, false)
        return fragmentAnnotationBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Аннотация"
    }
}