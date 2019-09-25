package com.example.mohamedashour.weatherapp.ui.fragments.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.*
import com.example.mohamedashour.weatherapp.R
import com.example.mohamedashour.weatherapp.data.models.Posts
import com.example.mohamedashour.weatherapp.ui.App
import com.example.mohamedashour.weatherapp.ui.activities.base.mvp.BaseFragment
import com.example.mohamedashour.weatherapp.utils.AppTools
import com.example.mohamedashour.weatherapp.utils.OnRecyclerClick

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : BaseFragment(), HomeContract.HomeView {

    lateinit var rootView: View
    lateinit var mainRecycler: RecyclerView
    lateinit var adapter: PostsAdapter
    lateinit var presenter: HomeContract.HomePresenter
    lateinit var bundle: Bundle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false)
        init()

        return rootView
    }

    private fun init() {
        presenter = HomePresenter(this)
        bundle = Bundle()
        mainRecycler = rootView.findViewById(R.id.recyclerView) as RecyclerView
        mainRecycler.layoutManager = GridLayoutManager(this.context, 1, LinearLayoutManager.VERTICAL, false)
        presenter.getHomeData(0)
    }

    companion object {
        fun newInstance() : HomeFragment {
            return HomeFragment()
        }
    }

    override fun receiveData(list: ArrayList<Posts>) {
        adapter = PostsAdapter(this.context!!, list,
            object : OnRecyclerClick {
                override fun onItemClick(view: View, position: Int, type: String) {
                    when (type) {
                        "container" -> openPostDetails(list[position].id, list[position].title, list[position].body)
                        "delete" -> presenter.deletePost(list[position].id, list[position])
                    }
                }
            })
        mainRecycler.adapter = adapter
    }

    private fun openPostDetails(id: Int, title: String, body: String) {
        bundle.putString(AppTools.PAGE_TYPE, AppTools.POST_DETAILS)
        bundle.putInt("id", id)
        bundle.putString("title", title)
        bundle.putString("body", body)
        openBaseActivity(bundle)
    }

    override fun postDeleted(item: Posts) {
        showToastMessage(context!!.resources.getString(R.string.msg_deleted))
        adapter.list.remove(item)
        adapter.notifyDataSetChanged()
    }
}
