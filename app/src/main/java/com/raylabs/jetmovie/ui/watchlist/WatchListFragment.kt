package com.raylabs.jetmovie.ui.watchlist

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WatchListFragment : Fragment() {

//    private var watchListBinding: ContentWatchlistBinding? = null
//    private val binding get() = watchListBinding
//    private val viewModel: WatchListViewModel by viewModels()
//    private lateinit var watchListAdapter: WatchListAdapter
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        watchListBinding = ContentWatchlistBinding.inflate(inflater, container, false)
//        return binding?.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        itemTouchHelper.attachToRecyclerView(binding?.rvWatchList)
//
//        if (activity != null) {
//
//            watchListAdapter = WatchListAdapter()
//
//            showLoading(true)
//            viewModel.getWatchList().observe(viewLifecycleOwner, { result ->
//                if (result.size != 0) {
//                    showLoading(false)
//                    watchListAdapter.submitList(result)
//                } else {
//                    showLoading(false)
//                    showNoData()
//                }
//            })
//            showRecyclerView()
//        }
//    }
//
//    private fun showNoData() {
//        binding?.apply {
//            ivWatchListNoData.isVisible = true
//            tvNoData.isVisible = true
//        }
//    }
//
//    private fun showRecyclerView() {
//        binding?.let {
//            with(it.rvWatchList) {
//                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//                setHasFixedSize(true)
//                addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
//                adapter = watchListAdapter
//            }
//        }
//    }
//
//    private fun showLoading(state: Boolean) {
//        if (state) {
//            binding?.let {
//                with(it.shimmerWatchList) {
//                    isVisible = true
//                    isShimmerVisible
//                    startShimmer()
//                }
//            }
//        } else {
//            binding?.let {
//                with(it) {
//                    shimmerWatchList.stopShimmer()
//                    shimmerWatchList.isGone = true
//                    rvWatchList.isVisible = true
//                }
//            }
//        }
//    }
//
//    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
//        override fun getMovementFlags(
//            recyclerView: RecyclerView,
//            viewHolder: RecyclerView.ViewHolder
//        ): Int =
//            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
//
//        override fun onMove(
//            recyclerView: RecyclerView,
//            viewHolder: RecyclerView.ViewHolder,
//            target: RecyclerView.ViewHolder
//        ): Boolean = true
//
//        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//            val swipedPosition = viewHolder.absoluteAdapterPosition
//            val dataEntity = watchListAdapter.getSwipedData(swipedPosition)
//            dataEntity?.let { viewModel.addToWatchList(it) }
//
//            val snackbar =
//                Snackbar.make(
//                    view as View,
//                    getString(R.string.snackbar_msg_undo),
//                    Snackbar.LENGTH_LONG
//                )
//            snackbar.setAction(R.string.undo) { _ ->
//                dataEntity?.let { viewModel.addToWatchList(it) }
//            }
//            snackbar.show()
//        }
//    })
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        watchListBinding = null
//    }
}