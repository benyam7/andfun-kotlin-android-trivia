package com.example.android.navigation


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    private lateinit var playButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater, R.layout.fragment_title,container, false)
        playButton = binding.playButton.apply {
            setOnClickListener{view:View ->
                view.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
            }
        }
        // tell this fragment that is has "menu"
        setHasOptionsMenu(true)
        return binding.root
        }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // we need to override onOptionsItemSelected to connect it to our NavigationUI.
        return NavigationUI.onNavDestinationSelected(item!!,view!!.findNavController()) || super.onOptionsItemSelected(item)
    }
}



