package com.jphernandez.melichallenge

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jphernandez.melichallenge.repositories.ProductsRepository
import javax.inject.Inject

class ProductListFragment : Fragment() {

    var recyclerView: RecyclerView? = null
    var container: ViewGroup? = null

    @Inject
    lateinit var productsRepository: ProductsRepository

    private val viewModel: ProductListVM by lazy { getViewModel { ProductListVM(productsRepository) } }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        MeliChallengeApplication.appComponent.inject(this)
        val view = inflater.inflate(R.layout.search_product_list_fragment, container, false)
        val layoutManager = GridLayoutManager(activity, 1)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = layoutManager
        this.container = view.findViewById(R.id.container)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.productsLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                Log.e("ProductListFragment", "Null or empty list")
            } else {
                // adapter.setList()
                Log.d("ProductListFragment", it.toString())
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    /**
     * Seteamos el boton de search
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_bar_menu, menu)

        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            setIconifiedByDefault(false)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    // We need to call the viewmodel and make the search
                    query?.let {
                        viewModel.getProducts(it)
                        Log.d("ProductListFragment", "Se presiono ok con el texto: $it")
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.d("ProductListFragment", "El texto cambio a: $query")
                    return true
                }

            })
        }
        super.onCreateOptionsMenu(menu, inflater)

    }

}