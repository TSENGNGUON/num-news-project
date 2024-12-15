package com.example.numnewsapp



import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.numnewsapp.databinding.FragmentHomePageBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomePageFragment : Fragment() {
    //Declare variable for use
    private lateinit var recyclerView : RecyclerView
    private lateinit var arrayNews: ArrayList<News>
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    //Database reference
    private lateinit var databaseRef: DatabaseReference

    private lateinit var progressBar: ProgressBar





    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_page, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)

        progressBar = view.findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
        //Set Layout of Recycler View
        recyclerView.layoutManager = LinearLayoutManager(null)

        //Make recycler view smooth
        recyclerView.setHasFixedSize(true)

        //Create Instance to use add method
        arrayNews = ArrayList<News>()



        //getNewsData()
        getNewsData()

        return view
    }

    private fun getNewsData() {

        databaseRef = FirebaseDatabase.getInstance().getReference("news")

        databaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){

                    for(newsSnapshot in snapshot.children){

                        val newsItem = newsSnapshot.getValue(News::class.java)
                        arrayNews.add(newsItem!!)
                    }
                    val adapter = RecyclerViewAdapter(arrayNews, requireContext()) {
                            news: News ->
                        //Open DetailsActivity and pass data
                        val intent = Intent(requireContext() , DetailsActivity::class.java)
                        intent.putExtra("title", news.title)
                        intent.putExtra("content", news.content)
                        intent.putExtra("imageUrl", news.imageUrl)
                        startActivity(intent)
                    }

                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    progressBar.visibility = View.GONE


                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })



    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomePageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomePageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}