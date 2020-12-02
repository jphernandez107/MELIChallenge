package com.jphernandez.melichallenge.productList

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jphernandez.melichallenge.*
import com.jphernandez.melichallenge.productDetail.ProductDetailFragment
import com.jphernandez.melichallenge.repositories.ProductsRepository
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar
import javax.inject.Inject

class ProductListFragment : Fragment() {

    @Inject
    lateinit var productsRepository: ProductsRepository
    private val viewModel: ProductListVM by lazy { getViewModel {
        ProductListVM(
            productsRepository
        )
    } }

    private lateinit var adapter: ProductsAdapter
    var recyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        MeliChallengeApplication.appComponent.inject(this)
        val view = inflater.inflate(R.layout.search_product_list_fragment, container, false)
        val layoutManager = GridLayoutManager(activity, 1)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = layoutManager

        adapter =
            ProductsAdapter { product ->
                onProductClick(product)
            }
        recyclerView?.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val emptyListTextView = view.findViewById<TextView>(R.id.empty_list_text_view)
        viewModel.productsLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                adapter.submitList(mutableListOf())
                emptyListTextView.visibility = View.VISIBLE
                Log.e("ProductListFragment", "Empty list")
            } else {
                adapter.submitList(it)
                emptyListTextView.visibility = View.GONE
                Log.d("ProductListFragment", it.toString())
            }
            setLoading(false)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    private fun onProductClick(product: Product) {
        val args = product.id?.let { ProductDetailFragment.getBundle(it) }
        val newFragment = ProductDetailFragment()
        newFragment.arguments = args

        val transaction = activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fragment_container_view, newFragment)
            addToBackStack(null)
        }
        transaction?.commit()
        Log.d("ProductListFragment", "Producto seleccionado: $product")
    }

    fun setLoading(loading: Boolean) {
        activity?.findViewById<CircleProgressBar>(R.id.loading)?.visibility = if(loading) View.VISIBLE else View.GONE
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
                        viewModel.requestProducts(it)
                        setLoading(true)
                        Log.d("ProductListFragment", "Se presiono ok con el texto: $it")
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?) = true

            })
        }
        super.onCreateOptionsMenu(menu, inflater)

    }

}