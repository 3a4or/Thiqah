package com.example.mohamedashour.weatherapp.ui.fragments.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.*
import com.example.mohamedashour.weatherapp.R
import com.example.mohamedashour.weatherapp.data.db.entities.Movie
import com.example.mohamedashour.weatherapp.data.models.Result
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
        setHasOptionsMenu(true)
        presenter = HomePresenter(this)
        bundle = Bundle()
        mainRecycler = rootView.findViewById(R.id.recyclerView) as RecyclerView
        mainRecycler.layoutManager = GridLayoutManager(this.context, 2, LinearLayoutManager.VERTICAL, false)
        presenter.getHomeData()
    }

    companion object {
        fun newInstance() : HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.options, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.popular -> {
                mainRecycler.adapter = null
                presenter.getHomeData()
            }
            R.id.highest -> {
                mainRecycler.adapter = null
                presenter.getHighestMovies()
            }
            R.id.fav -> {
                mainRecycler.adapter = null
                presenter.getFavoriteMovies()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun receiveData(list: List<Result>) {
        mainRecycler.adapter = MoviesAdapter(this.context!!, list,
            object : OnRecyclerClick {
                override fun onItemClick(view: View, position: Int) {
                    bundle.putString(AppTools.PAGE_TYPE, AppTools.MOVIES_DETAILS)
                    bundle.putString("title", list[position].title)
                    bundle.putString("date", list[position].release_date)
                    bundle.putString("rate", list[position].vote_average.toString())
                    bundle.putString("desc", list[position].overview)
                    bundle.putString("image", list[position].poster_path)
                    bundle.putString("id", list[position].id.toString())
                    openBaseActivity(bundle)
                }
            })
    }

    override fun receiveFavorites(list: List<Movie>) {
        mainRecycler.adapter = FavoritesAdapter(this.context!!, list,
            object : OnRecyclerClick {
                override fun onItemClick(view: View, position: Int) {
                    presenter.deleteMovie(list[position])
                }
            }
        )
    }

    override fun movieDeleted() {
        presenter.getFavoriteMovies()
    }
}
