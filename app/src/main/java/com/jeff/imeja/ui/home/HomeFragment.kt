package com.jeff.imeja.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.jeff.imeja.adapters.MenuAdapter
import com.jeff.imeja.databinding.FragmentHomeBinding
import com.jeff.imeja.models.MenuItem
import com.jeff.imeja.utils.Functions
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.fhir.sync.CurrentSyncJobStatus
import com.google.android.fhir.sync.SyncJobStatus
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Setup RecyclerView for ANC
        binding.ancRecyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.ancRecyclerView.adapter =
            MenuAdapter(Functions().getAncMenuItems(), this::handleClick)

        // Setup RecyclerView for PNC
        binding.pncRecyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.pncRecyclerView.adapter =
            MenuAdapter(Functions().getPncMenuItems(), this::handleClick)

        val time = Functions().greetings()
        val name = Functions().userName()
        binding.apply {
            welcomeTextView.apply {
                text = "$time $name"
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Show the user a message when the sync is finished and then refresh the list of patients
                // on the UI by sending a search patient request
                viewModel.pollState.collect { handleSyncJobStatus(it) }
            }
        }
        return root
    }

    private fun handleSyncJobStatus(syncJobStatus: CurrentSyncJobStatus) {
        // Add code to display Toast when sync job  is complete
       

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            viewModel.triggerOneTimeSync()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun handleClick(item: MenuItem) {
        when (item.title) {
            "Patient Registration" -> {
                Toast.makeText(requireContext(), "Patient Registration", Toast.LENGTH_SHORT).show()
            }

            "ANC Checkups" -> {
                viewModel.triggerOneTimeSync()
            }

            "Health Tips" -> {}
            "Newborn Care" -> {}
            "Postpartum Checkups" -> {}
            "PNC Health Tips" -> {}
            else -> {}
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}