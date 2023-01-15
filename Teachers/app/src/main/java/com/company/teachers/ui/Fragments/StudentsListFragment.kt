package com.company.teachers.ui.Fragments

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.company.teachers.R
import com.company.teachers.Retrofit.RetrofitApi
import com.company.teachers.utils.showToast
import com.company.teachers.databinding.FragmentStudentsListBinding
import com.company.teachers.dto.Student
import com.company.teachers.dto.StudentsPayload
import com.company.teachers.ui.Adapters.StudentRecyclerAdapter
import com.company.teachers.viewModels.FiltersViewModel
import com.company.teachers.viewModels.FiltersViewModelFactory
import kotlinx.coroutines.*


class StudentsListFragment : BaseFragment(R.layout.fragment_students_list) {

    var retrofitApi = RetrofitApi(this)
    private var _binding: FragmentStudentsListBinding? = null
    private val binding get() = _binding!!
    lateinit var studentsRecyclerAdapter: StudentRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val groupId = arguments?.getString("groupId")
        val subjectId = arguments?.getString("subjectId")
        var examType = arguments?.getString("examType")
        val semesterId = arguments?.getString("semesterId")

        if (examType == "Экзамен") {
            examType = "exam"
        } else {
            examType = "credit"
        }

        var studentsPayload =
            StudentsPayload(
                groupId!!.toInt(),
                semesterId!!.toInt(),
                subjectId!!.toInt(),
                examType!!
            )

        binding.shimmerLayout.startShimmer()
        GlobalScope.launch(Dispatchers.Main) {
            try {
                var studentsResponse = retrofitApi.getStudents(studentsPayload)
                val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
                requireActivity().runOnUiThread {
                    try {
                        recyclerView.layoutManager = LinearLayoutManager(context)
                        studentsRecyclerAdapter =
                            studentsResponse?.let { StudentRecyclerAdapter(it) }!!
                        recyclerView.adapter = studentsRecyclerAdapter
                        binding.shimmerLayout.stopShimmer()
                        binding.shimmerLayout.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                    } catch (e: Exception) {
                        Log.i("studentList", "Failure coroutine")
                        binding.shimmerLayout.stopShimmer()
                        binding.shimmerLayout.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                    }
                }
            } catch (e: Exception) {
                Log.i("studentList", "Failure coroutine")
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.students_list_save_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_students_list_button -> {

                GlobalScope.launch(Dispatchers.Main) {
                    try {
                        retrofitApi.updateStudents(studentsRecyclerAdapter?.updatedStudents)
                        showToast("Saved")
                    } catch (e: Exception) {
                        Log.i("studentList", e.toString())
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}