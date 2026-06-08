package com.example.dualpanelayouts

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dualpanelayouts.databinding.FragmentListBinding

class ListFragment : Fragment(), View.OnClickListener {

    private val param1: String? by lazy { arguments?.getString(ARG_PARAM1) }
    private val param2: String? by lazy { arguments?.getString(ARG_PARAM2) }

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var starSignListener: StarSignListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is StarSignListener) {
            starSignListener = context
        } else {
            throw RuntimeException("Must implement StarSignListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setValues()
    }

    override fun onClick(view: View?) {
        view?.let { starSign ->
            starSignListener.onSelected(starSign.id)
        }
    }

    private fun setValues() {
        val starSigns = listOf<View>(
            binding.aquarius,
            binding.pisces,
            binding.aries,
            binding.taurus,
            binding.gemini,
            binding.cancer,
            binding.leo,
            binding.virgo,
            binding.libra,
            binding.scorpio,
            binding.sagittarius,
            binding.capricorn
        )
        starSigns.forEach {
            it.setOnClickListener(this)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) = ListFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }
}