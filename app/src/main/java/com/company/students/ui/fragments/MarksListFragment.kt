package com.company.students.ui.fragments

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.company.students.R
import com.company.students.databinding.MarkListBinding
import com.company.students.retrofit.RetrofitApi
import com.company.students.ui.adapters.MarkRecyclerAdapter
import com.company.students.viewModels.MarksViewModel
import com.company.students.viewModels.MarksViewModelFactory
import kotlinx.coroutines.*


class MarksListFragment : BaseFragment(R.layout.mark_list) {

    private lateinit var _binding: MarkListBinding

    private lateinit var marksViewModel: MarksViewModel

    private var retrofitApi = RetrofitApi(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.mark_list, container, false)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        marksViewModel = ViewModelProvider(
            this,
            MarksViewModelFactory(application = Application(), this)
        ).get(MarksViewModel::class.java)

        _binding.viewModel = marksViewModel

        val type = arguments?.getString("type")
        val semester = arguments?.getString("semester")

        GlobalScope.launch(Dispatchers.Main) {
            _binding.shimmerLayout.startShimmer()
            try {
                val marks = retrofitApi.getMarks(semester!!.toInt(), type.toString())
                val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
                requireActivity().runOnUiThread{
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.adapter = MarkRecyclerAdapter(marks!!)
                    _binding.shimmerLayout.stopShimmer()
                    _binding.shimmerLayout.visibility = View.GONE
                    _binding.recyclerView.visibility = View.VISIBLE
                }
            }
            catch (e: Exception){
                Log.i("MarksList", "Failure coroutine")
            }

        }


    }

}