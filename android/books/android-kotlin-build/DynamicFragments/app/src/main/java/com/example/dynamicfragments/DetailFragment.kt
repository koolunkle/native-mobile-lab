package com.example.dynamicfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dynamicfragments.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val starSignId: Int? by lazy { arguments?.getInt(STAR_SIGN_ID) }

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var currentStarSignId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setValues(starSignId ?: 0)
    }

    fun setValues(starSignId: Int) {
        when (starSignId) {
            R.id.aquarius -> {
                binding.starSign.text = getString(R.string.aquarius)
                binding.symbol.text = getString(R.string.symbol, "Water Carrier")
                binding.dateRange.text = getString(R.string.date_range, "January 20 - February 18")
            }

            R.id.pisces -> {
                binding.starSign.text = getString(R.string.pisces)
                binding.symbol.text = getString(R.string.symbol, "Fish")
                binding.dateRange.text = getString(R.string.date_range, "February 19 - March 20")
            }

            R.id.aries -> {
                binding.starSign.text = getString(R.string.aries)
                binding.symbol.text = getString(R.string.symbol, "Ram")
                binding.dateRange.text = getString(R.string.date_range, "March 21 - April 19")
            }

            R.id.taurus -> {
                binding.starSign.text = getString(R.string.taurus)
                binding.symbol.text = getString(R.string.symbol, "Bull")
                binding.dateRange.text = getString(R.string.date_range, "April 20 - May 20")
            }

            R.id.gemini -> {
                binding.starSign.text = getString(R.string.gemini)
                binding.symbol.text = getString(R.string.symbol, "Twins")
                binding.dateRange.text = getString(R.string.date_range, "May 21 - June 20")
            }

            R.id.cancer -> {
                binding.starSign.text = getString(R.string.cancer)
                binding.symbol.text = getString(R.string.symbol, "Crab")
                binding.dateRange.text = getString(R.string.date_range, "June 21 - July 22")
            }

            R.id.leo -> {
                binding.starSign.text = getString(R.string.leo)
                binding.symbol.text = getString(R.string.symbol, "Lion")
                binding.dateRange.text = getString(R.string.date_range, "July 23 - August 22")
            }

            R.id.virgo -> {
                binding.starSign.text = getString(R.string.virgo)
                binding.symbol.text = getString(R.string.symbol, "Virgin")
                binding.dateRange.text = getString(R.string.date_range, "August 23 - September 22")
            }

            R.id.libra -> {
                binding.starSign.text = getString(R.string.libra)
                binding.symbol.text = getString(R.string.symbol, "Scales")
                binding.dateRange.text = getString(R.string.date_range, "September 23 - October 22")
            }

            R.id.scorpio -> {
                binding.starSign.text = getString(R.string.scorpio)
                binding.symbol.text = getString(R.string.symbol, "Scorpion")
                binding.dateRange.text = getString(R.string.date_range, "October 23 - November 21")
            }

            R.id.sagittarius -> {
                binding.starSign.text = getString(R.string.sagittarius)
                binding.symbol.text = getString(R.string.symbol, "Archer")
                binding.dateRange.text = getString(R.string.date_range, "November 22 - December 21")
            }

            R.id.capricorn -> {
                binding.starSign.text = getString(R.string.capricorn)
                binding.symbol.text = getString(R.string.symbol, "Mountain Goat")
                binding.dateRange.text = getString(R.string.date_range, "December 22 - January 19")
            }

            else -> {
                Toast.makeText(context, getString(R.string.unknown_star_sign), Toast.LENGTH_LONG)
                    .show()
            }
        }
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private const val STAR_SIGN_ID = "STAR_SIGN_ID"

        @JvmStatic
        fun newInstance(starSignId: Int) = DetailFragment().apply {
            arguments = Bundle().apply {
                putInt(STAR_SIGN_ID, starSignId)
            }
        }
    }
}