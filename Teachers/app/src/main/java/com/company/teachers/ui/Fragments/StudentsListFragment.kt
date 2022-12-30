package com.company.teachers.ui.Fragments

import android.opengl.Visibility
import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.company.teachers.R
import com.company.teachers.Utilits.replaceFragment
import com.company.teachers.Utilits.showToast
import com.company.teachers.databinding.FragmentStudentsListBinding
import com.company.teachers.dto.Student
import com.company.teachers.ui.Adapters.StudentRecyclerAdapter
import kotlinx.coroutines.*


class StudentsListFragment : BaseFragment(R.layout.fragment_students_list) {

    private var _binding: FragmentStudentsListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentStudentsListBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun fillList(): List<Student> {
        val data = mutableListOf<Student>()
        (0..30).forEach { i -> data.add(Student(i,"Name", "Surname")) }
        return data
    }

    val scope = CoroutineScope(Dispatchers.IO)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.shimmerLayout.startShimmer()
        scope.launch {
            val loginAwait = async {Thread.sleep(2000)
                val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
                requireActivity().runOnUiThread{
                        recyclerView.layoutManager = LinearLayoutManager(context)
                        recyclerView.adapter = StudentRecyclerAdapter(fillList())
                        binding.shimmerLayout.stopShimmer()
                        binding.shimmerLayout.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                }
            }
            loginAwait.await()

        }
        initFunc()
    }

    private fun initFunc() {

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.students_list_save_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.save_students_list_button -> {
                showToast("Saved")
            }
        }
        return super.onOptionsItemSelected(item)
    }

}