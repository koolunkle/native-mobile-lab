package com.example.sharedviewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sharedviewmodel.databinding.FragmentSplitOneBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable

class SplitFragmentOne : Fragment() {

    private val param1: String? by lazy { arguments?.getString(ARG_PARAM1) }
    private val param2: String? by lazy { arguments?.getString(ARG_PARAM2) }

    private var _binding: FragmentSplitOneBinding? = null
    private val binding get() = _binding!!

    // private var disposable : Disposable? = null
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplitOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setValues()
        prepareViewModel()
    }

    private fun setValues() {
        binding.fragmentSplitOneTextView.text = getString(R.string.total, 0)
    }

    private fun prepareViewModel() {
        val totalsViewModel = ViewModelProvider(requireActivity())[TotalsViewModel::class.java]

        // updateText(totalsViewModel.total)

        /*totalsViewModel.total.observe(viewLifecycleOwner) {
            updateText(it)
        }*/

        /*lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                totalsViewModel.total.collect {
                    updateText(it)
                }
            }
        }*/

        /*disposable = totalsViewModel.total.subscribe {
            updateText(it)
        }*/
        compositeDisposable.add(
            totalsViewModel.total.subscribe {
                updateText(it)
            }
        )

        binding.fragmentSplitOneButton.setOnClickListener {
            // updateText(totalsViewModel.increaseTotal())
            totalsViewModel.increaseTotal()
        }
    }

    private fun updateText(total: Int) {
        binding.fragmentSplitOneTextView.text = getString(R.string.total, total)
    }

    override fun onDestroyView() {
        compositeDisposable.clear()
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) = SplitFragmentOne().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }
}