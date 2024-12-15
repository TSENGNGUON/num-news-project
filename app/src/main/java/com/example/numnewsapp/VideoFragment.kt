package com.example.numnewsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [VideoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VideoFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var videoAdapter: VideoCardAdapter
    private val videoList = mutableListOf<VideoCardItemData>()
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_video, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewVideo)

        progressBar = view.findViewById(R.id.progressBarVideo)
        progressBar.visibility = View.VISIBLE

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        videoAdapter = VideoCardAdapter(videoList)


        recyclerView.adapter = videoAdapter



        fetchVideo { fetchedVideo ->
            videoList.clear()
            videoList.addAll(fetchedVideo)
            videoAdapter.notifyDataSetChanged()
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExploreFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VideoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun fetchVideo(onResult: (List<VideoCardItemData>) -> Unit) {
        val database = FirebaseDatabase.getInstance()
        val videosRef = database.getReference("video")


        videosRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
               val videoList = mutableListOf<VideoCardItemData>()
                for (videoSnapshot in snapshot.children){
                    val video = videoSnapshot.getValue(VideoCardItemData::class.java)
                    if(video != null){
                        videoList.add(video)
                    }
                }
                progressBar.visibility = View.GONE
                onResult(videoList)

            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to fetch data: ${error.message}")
            }

        })
    }
}