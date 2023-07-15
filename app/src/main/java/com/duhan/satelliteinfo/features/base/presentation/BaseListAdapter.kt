package com.duhan.satelliteinfo.features.base.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<M : BaseListItem, DB : ViewDataBinding>(
    private val diffUtilCheckParam: DiffUtilCheckParam<M> = DiffUtilCheckParam(),
    private val itemClickListener: (item: M, position: Int) -> Unit = { _, _ -> }
) :
    RecyclerView.Adapter<BaseListAdapter<M, DB>.BaseViewHolder>() {

    private var itemList: List<M> = emptyList()
    val items: List<M>
        get() = itemList

    abstract val layoutId: Int
    abstract fun setUIState(binding: DB, item: M)

    private fun onCreateViewHolder(binding: DB): BaseViewHolder {
        return BaseViewHolder(binding)
    }

    private fun onBindViewHolder(holder: BaseViewHolder, item: M) {
        holder.bind(item)
    }

    fun setItems(items: List<M>) {
        val diffResult =
            DiffUtil.calculateDiff(DiffCallback(itemList, items.toList(), diffUtilCheckParam))
        diffResult.dispatchUpdatesTo(this)
        itemList = items.toList()
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), layoutId, parent, false
        )
        val viewHolder = onCreateViewHolder(binding as DB)
        binding.root.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                itemClickListener.invoke(itemList[position], position)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = itemList[position]
        onBindViewHolder(holder, item)
    }

    open fun initializeMenu(binding: DB): MenuContainer? {
        return null
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class BaseViewHolder(private val binding: DB) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: M) {
            val menuContainer = initializeMenu(binding)
            if (menuContainer != null) {
                val view = binding.root.findViewById<View>(menuContainer.viewId)
                view.setOnClickListener {
                    with(
                        PopupMenu(
                            binding.root.context,
                            binding.root
                        )
                    ) {
                        setOnMenuItemClickListener(menuContainer.menuItemClickListener)
                        inflate(menuContainer.menu)
                        show()
                    }
                }
            }
            setUIState(binding, item)
        }
    }

    class DiffCallback<T>(
        private val oldList: List<T>,
        private val newList: List<T>,
        private val diffUtilCheckParam: DiffUtilCheckParam<T>
    ) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]
            return diffUtilCheckParam.itemsAreSameChecker(oldItem, newItem)
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]
            return diffUtilCheckParam.itemsContentsAreSameChecker(oldItem, newItem)
        }
    }
}

data class MenuContainer(
    val viewId: Int,
    val menu: Int,
    val menuItemClickListener: PopupMenu.OnMenuItemClickListener
)

class DiffUtilCheckParam<M>(
    val itemsAreSameChecker: (firstItem: M, secondItem: M) -> Boolean =
        { firstItem, secondItem -> firstItem === secondItem },
    val itemsContentsAreSameChecker: (firstItem: M, secondItem: M) -> Boolean =
        { firstItem, secondItem -> firstItem == secondItem }
)
