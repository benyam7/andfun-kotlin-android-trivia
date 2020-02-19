/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_won, container, false)
        // handle click
        binding.nextMatchButton.setOnClickListener{view: View ->
            view.findNavController().navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }
        // get the arguments from the bundle
        val args = GameWonFragmentArgs.fromBundle(arguments!!)
        // display the values using Toast
        Toast.makeText(context, "You have answered ${args.numOfAnswers} questions correctly out of ${args.numOfQuestions}", Toast.LENGTH_SHORT).show()
        setHasOptionsMenu(true)


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        // check early if the intent your creating resolves to activity with out issue
        if(createShareIntent().resolveActivity(activity!!.packageManager) == null){
            //hide the specific menu item, that caused the issue this give us the flexiblity of hiding the one we choose
            menu?.findItem(R.id.share)?.setVisible(false)
        }

        inflater?.inflate(R.menu.winner_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.share -> shareResults()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun shareResults() {
        startActivity(createShareIntent())
    }

    private fun createShareIntent(): Intent {
        val args = GameWonFragmentArgs.fromBundle(arguments!!)
        // using fluent shareCompat api , by fluent we mean it has method chaining that is easy to read
        return ShareCompat.IntentBuilder.from(activity)
                .setText(getString(R.string.share_success_text, args.numOfAnswers, args.numOfQuestions)) // set our text data
                .setType("text/plain")  // set our data type
                .intent // build the intent

    }

}
