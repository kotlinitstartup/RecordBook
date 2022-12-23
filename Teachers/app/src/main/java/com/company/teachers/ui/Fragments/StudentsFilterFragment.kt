package com.company.teachers.ui.Fragments

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import com.company.teachers.R
import com.company.teachers.Utilits.replaceFragment
import com.company.teachers.databinding.FragmentStudentsFilterBinding


class StudentsFilterFragment : Fragment() {

    private var _binding: FragmentStudentsFilterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentStudentsFilterBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        var nextButton = binding.filterBtnNext
        nextButton.show()
        nextButton.visibility = View.VISIBLE

        val items = listOf("Item 1", "Item 2", "Item 3", "Item 4")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        var textField = binding.subjectsList
        (textField.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        binding.filterBtnNext.setOnClickListener{
            replaceFragment(StudentsListFragment())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.main_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.main_menu_exit -> {
                replaceFragment(LoginFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }
}