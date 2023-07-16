package com.duhan.satelliteinfo.features.base.data

import com.duhan.satelliteinfo.features.base.domain.ITimeLimitedResource
import com.duhan.satelliteinfo.features.base.domain.RefreshControl
import kotlinx.coroutines.flow.Flow

class ResourceGroup<in Input, in Key, out Output>(
    remoteGroupFetch: suspend (Input) -> List<Output>?,
    localGroupFetch: suspend (Input) -> List<Output>?,
    localGroupStore: suspend (List<Output>) -> Unit,
    private val remoteFetch: (Key, Input) -> Output?,
    private val localFetch: suspend (Key, Input) -> Output?,
    private val localStore: suspend (Output) -> Unit,
    localDelete: suspend () -> Unit,
    private val refreshControl: RefreshControl = RefreshControl()
) : ITimeLimitedResource by refreshControl {
    private val groupResource = Resource(
        remoteGroupFetch,
        localGroupFetch,
        localGroupStore,
        localDelete,
        refreshControl
    )
    private val resourceMap: MutableMap<Key, Resource<Input, Output>> = mutableMapOf()

    fun query(args: Input, force: Boolean = false): Flow<List<Output>?> =
        groupResource.query(args, force)

    fun queryByKey(key: Key, args: Input, force: Boolean = false): Flow<Output?> =
        singleResource(key).query(args, force)

    // Private API
    private fun singleResource(key: Key) =
        resourceMap[key] ?: Resource<Input, Output>(
            { remoteFetch(key, it) },
            { localFetch(key, it) },
            { localStore(it) },
            { }, // Delete will be triggered by parent
            refreshControl.createChild()
        ).also { resourceMap[key] = it }

}
