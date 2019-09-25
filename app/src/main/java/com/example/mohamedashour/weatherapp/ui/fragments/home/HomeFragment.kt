package com.example.mohamedashour.weatherapp.ui.fragments.home


import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.EditText
import com.example.mohamedashour.weatherapp.R
import com.example.mohamedashour.weatherapp.data.models.Posts
import com.example.mohamedashour.weatherapp.ui.App
import com.example.mohamedashour.weatherapp.ui.activities.base.mvp.BaseFragment
import com.example.mohamedashour.weatherapp.utils.AppTools
import com.example.mohamedashour.weatherapp.utils.OnRecyclerClick
import com.example.mohamedashour.weatherapp.utils.PaginationScrollListener

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : BaseFragment(), HomeContract.HomeView {

    lateinit var rootView: View
    lateinit var mainRecycler: RecyclerView
    lateinit var mLayoutManager: GridLayoutManager
    lateinit var adapter: PostsAdapter
    lateinit var presenter: HomeContract.HomePresenter
    lateinit var bundle: Bundle
    lateinit var saveBtn: Button
    lateinit var cancelBtn: Button
    lateinit var titleEditText: EditText
    lateinit var bodyEditText: EditText
    lateinit var loadingBar: Dialog
    var counter: Int = 0
    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false)
        init()

        return rootView
    }

    private fun init() {
        setHasOptionsMenu(true)
        presenter = HomePresenter(this)
        bundle = Bundle()
        initPopup()
        mainRecycler = rootView.findViewById(R.id.recyclerView) as RecyclerView
        mLayoutManager = GridLayoutManager(this.context, 1, LinearLayoutManager.VERTICAL, false)
        mainRecycler.layoutManager = mLayoutManager
        counter
        presenter.getHomeData(0, "first")
        initScroll()
    }

    private fun initScroll() {
        mainRecycler?.addOnScrollListener(object : PaginationScrollListener(mLayoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                counter += 15
                presenter.getHomeData(counter, "load")
            }
        })
    }

    private fun initPopup() {
        loadingBar = Dialog(activity, android.R.style.Theme_Black_NoTitleBar)
        loadingBar.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val inflater = LayoutInflater.from(activity)
        val loadingView = inflater.inflate(R.layout.post_popup, null)
        loadingBar.setContentView(loadingView)
        loadingBar.window!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#90000000")))
        loadingBar.setCancelable(true)
        loadingBar.setCanceledOnTouchOutside(true)
        saveBtn = loadingView.findViewById(R.id.btn_save)
        cancelBtn = loadingView.findViewById(R.id.btn_cancel)
        titleEditText = loadingView.findViewById(R.id.et_title)
        bodyEditText = loadingView.findViewById(R.id.et_details)
    }

    companion object {
        fun newInstance() : HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.add_post -> showPopup(0, 1, "", "", "add", 0)
        }
        return super.onOptionsItemSelected(item)
    }
    override fun receiveData(list: ArrayList<Posts>, type: String) {
        if (type == "first") {
            adapter = PostsAdapter(this.context!!, list,
                object : OnRecyclerClick {
                    override fun onItemClick(view: View, position: Int, type: String) {
                        when (type) {
                            "container" -> openPostDetails(list[position].id, list[position].title, list[position].body)
                            "delete" -> presenter.deletePost(list[position].id, list[position])
                            "edit" -> showPopup(list[position].id, list[position].userId, list[position].title, list[position].body, "edit", position)
                        }
                    }
                })
            mainRecycler.adapter = adapter
        } else {
            isLoading = false
            adapter.addData(list)
        }
    }

    private fun showPopup(id: Int, userId: Int, title: String, body: String, type: String, index: Int) {
        titleEditText.setText(title)
        bodyEditText.setText(body)
        saveBtn.setOnClickListener{
            presenter.validation(titleEditText.text.toString(), bodyEditText.text.toString(), id, userId, type, index)
        }
        cancelBtn.setOnClickListener{
            loadingBar.dismiss()
        }
        loadingBar.show()
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

    override fun errorTitle() {
        titleEditText.error = context!!.resources.getString(R.string.msg_title_first)
    }

    override fun errorBody() {
        bodyEditText.error = context!!.resources.getString(R.string.msg_body_first)
    }

    override fun validationDone(id: Int, userId: Int, type: String, index: Int) {
        when (type) {
            "add" -> presenter.addPost(titleEditText.text.toString(), bodyEditText.text.toString(), userId, type)
            "edit" -> presenter.updatePost(titleEditText.text.toString(), bodyEditText.text.toString(), id, userId, type, index)
        }
        loadingBar.dismiss()
    }

    override fun requestFinished(type: String, newModel: Posts, index: Int) {
        if (type == "add") {
            showToastMessage(context!!.resources.getString(R.string.msg_added))
            adapter.list.add(newModel)
            adapter.notifyDataSetChanged()
            mainRecycler.scrollToPosition(adapter.list.size - 1)
        } else if (type == "edit") {
            showToastMessage(context!!.resources.getString(R.string.msg_updated))
            adapter.list[index] = newModel
            adapter.notifyDataSetChanged()
        }
    }
}
