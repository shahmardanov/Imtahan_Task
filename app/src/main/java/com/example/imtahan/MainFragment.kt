package com.example.imtahan

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.imtahan.databinding.CustomDialogBinding
import com.example.imtahan.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val myAdapter = TaskAdapter()
    private val taskList = arrayListOf<TaskModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recicleviewtask.adapter = myAdapter
        change()
        binding.buttonelaveet.setOnClickListener {
            customAlert()
        }

        myAdapter.clickDelete = {
            taskList.remove(it)
            myAdapter.updateMyList(taskList)
            change()
        }


    }

    fun customAlert() {
        val dialog = AlertDialog.Builder(requireContext()).create()
        val alertBinding = CustomDialogBinding.inflate(layoutInflater)
        dialog.setView(alertBinding.root)
        alertBinding.buttontestiq.setOnClickListener {
            var name = alertBinding.editTextTexttaskadi.text.toString().trim()
            var cetinlik = Cetinlik.RAHAT

            when (alertBinding.radioqrup.checkedRadioButtonId) {
                R.id.radioButtonrahat -> cetinlik = Cetinlik.RAHAT
                R.id.radioButtonorta -> cetinlik = Cetinlik.ORTA
                else -> cetinlik = Cetinlik.VACIB
            }
            var songun=alertBinding.checkBox.isChecked

            var model=TaskModel(name,songun,cetinlik)

            taskList.add(model)

            myAdapter.updateMyList(taskList)
            Log.e("dsd",taskList.toString())
            change()
            dialog.dismiss()
        }
        dialog.show()
    }

    fun change() {
        if (taskList.isEmpty()) {
            binding.recicleviewtask.visibility = View.GONE
            binding.textViewtask.visibility = View.VISIBLE
        } else {
            binding.recicleviewtask.visibility = View.VISIBLE
            binding.textViewtask.visibility = View.GONE
        }
        binding.textViewtaskcount.text="Task Sayi -> ${taskList.size}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}