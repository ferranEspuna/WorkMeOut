package com.example.workmeout.ui.talk

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workmeout.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.ListenerRegistration
import com.xwray.groupie.Section

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var blogAdapter: DataChatAdapter
    private var isOpen = false

    //variables nuevas vistas


    //variables chat: Firebase Firestore Chat App: Show a List of Users (Ep 3)
    private lateinit var userListenerRegistration: ListenerRegistration
    private var shouldInitRecyclerView = true
    private lateinit var peopleSection: Section

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_talk, container, false)


        //floatting button
        val fabOpen = AnimationUtils.loadAnimation(context, R.anim.fab_open)
        val fabClose = AnimationUtils.loadAnimation(context, R.anim.fab_close)
        val fabRClockwise = AnimationUtils.loadAnimation(context, R.anim.rotate_clockwise)
        val fabRAntiClockwise = AnimationUtils.loadAnimation(context, R.anim.rotate_anticlockwise)

        val add_button = root.findViewById<FloatingActionButton>(R.id.add_button)
        val search_button = root.findViewById<FloatingActionButton>(R.id.search_button)
        val add_people_button = root.findViewById<FloatingActionButton>(R.id.add_people_button)



        add_button.setOnClickListener {

            if (isOpen) {

                search_button.startAnimation(fabClose)
                add_people_button.startAnimation(fabClose)
                add_button.startAnimation(fabRClockwise)

                isOpen = false
            }

            else {

                search_button.startAnimation(fabOpen)
                add_people_button.startAnimation(fabOpen)
                add_button.startAnimation(fabRAntiClockwise)

                search_button.isClickable
                add_people_button.isClickable

                isOpen = true
            }


        }

        search_button.setOnClickListener {
            //Toast.makeText(context, "search button", Toast.LENGTH_SHORT).show()
            val button_ppl : Intent = Intent(root.context,SearchPeople::class.java)
            startActivity(button_ppl)
        }
        add_people_button.setOnClickListener{
            Toast.makeText(context, "random people", Toast.LENGTH_SHORT).show()
        }



        addDataSet()
        initRecycleView(root)

        return root
    }

    private fun addDataSet() {
        val data: ArrayList<DataChat> = DataSource.createDataSet()
        blogAdapter = DataChatAdapter()
        blogAdapter.submitList(data)
    }

    private fun initRecycleView(root:View) {

        root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(root.context)
            adapter = blogAdapter
        }
    }
/*FIREBASE CHAT
    override fun onDestroyView() {
        super.onDestroyView()
        FirestoreUtil.removeListener(userListenerRegistration)
        shouldInitRecyclerView = true
    }

    private fun updateRecyclerView(items: List<Item>) {

        fun init() {
            recycler_view_people.apply {
                layoutManager = LinearLayoutManager(this@NotificationsFragment.context)
                adapter = GroupAdapter<ViewHolder>().apply {
                    peopleSection = Section(items)
                    add(peopleSection)
                    setOnItemClickListener(onItemClick)
                }
            }
            shouldInitRecyclerView = false
        }

        fun updateItems() = peopleSection.update(items)

        if (shouldInitRecyclerView)
            init()
        else
            updateItems()

    }*/
}
