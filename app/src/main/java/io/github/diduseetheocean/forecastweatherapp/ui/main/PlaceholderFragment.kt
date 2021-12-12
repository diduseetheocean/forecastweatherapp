package io.github.diduseetheocean.forecastweatherapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.github.diduseetheocean.forecastweatherapp.R
import io.github.diduseetheocean.forecastweatherapp.databinding.FragmentMainBinding
import io.github.diduseetheocean.forecastweatherapp.domain.SearchState
import io.github.diduseetheocean.forecastweatherapp.domain.SearchState.*
import io.github.diduseetheocean.forecastweatherapp.domain.WeatherUsecase
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root = binding.root

        binding.button.setOnClickListener {
            pageViewModel.onFetchClicked()
        }

        val textView: TextView = binding.sectionLabel
        pageViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val weatherTextView: TextView = binding.weatherLabel
        pageViewModel.searchViewState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Success -> {
                    weatherTextView.text = it.weatherLocationModel.let { model ->
                        "Test for Munich: \n" +
                                "TempDay: ${model.weatherDailyModelList.first().tempDay} °C \n " +
                                "Desc: ${model.weatherDailyModelList.first().description} \n "
                    }
                }
                is Error -> {
                    weatherTextView.text = "Error"
                }
                is Loading -> {
                    weatherTextView.text = "Loading"
                }
                is Empty -> {
                    weatherTextView.text = "Empty"
                }
            }
        })
        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}